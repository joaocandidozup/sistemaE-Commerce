package com.example.sistemaECommerce.API.services;

import com.example.sistemaECommerce.API.dtos.ProductDTO;
import com.example.sistemaECommerce.API.dtos.PurchaseRequestDTO;
import com.example.sistemaECommerce.API.exceptions.ClientNotFoundException;
import com.example.sistemaECommerce.API.exceptions.InsufficientStockException;
import com.example.sistemaECommerce.API.models.ClientEntity;
import com.example.sistemaECommerce.API.models.ProductEntity;
import com.example.sistemaECommerce.API.repositories.ClientRepository;
import com.example.sistemaECommerce.API.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PurchaseService {
    private final ClientRepository clientrepository;
    private final ProductRepository productRepository;
    private final Logger log = LoggerFactory.getLogger(PurchaseService.class);

    public PurchaseService(ClientRepository clientrepository, ProductRepository productRepository) {
        this.clientrepository = clientrepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void processPurchase(PurchaseRequestDTO purchaseRequest) {
        ClientEntity client = clientrepository.findByCpf(purchaseRequest.cpf());
        if (client == null) {
            throw new ClientNotFoundException("Cliente não encontrado com o CPF: " + purchaseRequest.cpf());
        }

        List<ProductDTO> products = purchaseRequest.products();
        List<String> productsInMissing = new ArrayList<>();
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("A lista de produtos não pode estar vazia.");
        }

        for (ProductDTO product : products) {
            ProductEntity productEntity = productRepository.findByNameIgnoreCase(product.name());

            if (productEntity.getQuantity() < product.quantity()) {
                productsInMissing.add(product.name());
            }

            productEntity.setQuantity(productEntity.getQuantity() - product.quantity());
            productRepository.save(productEntity);
        }
        if(!productsInMissing.isEmpty()){
            throw new InsufficientStockException(productsInMissing);
        }
        log.info("Compra processada com sucesso para o Cliente {}", purchaseRequest.cpf());
    }
}
