package com.example.sistemaECommerce.API.services;

import com.example.sistemaECommerce.API.dtos.ProductDTO;
import com.example.sistemaECommerce.API.dtos.PurchaseRequestDTO;
import com.example.sistemaECommerce.API.exceptions.ClientNotFoundException;
import com.example.sistemaECommerce.API.exceptions.ProductNotFoundException;
import com.example.sistemaECommerce.API.models.ClientEntity;
import com.example.sistemaECommerce.API.models.ProductEntity;
import com.example.sistemaECommerce.API.repositories.ClientRepository;
import com.example.sistemaECommerce.API.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PurchaseService {
    private final ClientRepository clientrepository;
    private final ProductRepository productRepository;

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

        List<ProductDTO> produtos = purchaseRequest.produtos();
        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("A lista de produtos não pode estar vazia.");
        }

        for (ProductDTO produto : produtos) {
            if (produto.quantity() <= 0) {
                throw new IllegalArgumentException("A quantidade do produto " + produto.name() + " deve ser maior que zero.");
            }

            ProductEntity productEntity = productRepository.findByNameIgnoreCase(produto.name());
            if (productEntity == null) {
                throw new ProductNotFoundException("Produto não encontrado: " + produto.name());
            }

            if (productEntity.getQuantity() < produto.quantity()) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.name());
            }

            productEntity.setQuantity(productEntity.getQuantity() - produto.quantity());
            productRepository.save(productEntity);

            System.out.println("Produto comprado: " + produto.name() + ", Quantidade: " + produto.quantity());
        }
    }
}
