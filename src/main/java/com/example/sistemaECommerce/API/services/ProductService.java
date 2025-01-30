package com.example.sistemaECommerce.API.services;

import com.example.sistemaECommerce.API.dtos.ProductDTO;
import com.example.sistemaECommerce.API.exceptions.ProductAlreadyExistsException;
import com.example.sistemaECommerce.API.exceptions.ProductNotFoundException;
import com.example.sistemaECommerce.API.models.ProductEntity;
import com.example.sistemaECommerce.API.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;

    }

    public ProductDTO registerProduct(ProductDTO productDTO, String name) {
        ProductEntity existingEntity = repository.findByNameIgnoreCase(name);
        if (existingEntity != null) {
            throw new ProductAlreadyExistsException("Já existe um produto com o nome: " + name);
        }

        ProductEntity entity = new ProductEntity(
                null,
                productDTO.name(),
                productDTO.price(),
                productDTO.quantity());

        ProductEntity savedEntity = repository.save(entity);
        return new ProductDTO(

                savedEntity.getId(),
                savedEntity.getName(),
                savedEntity.getPrice(),
                savedEntity.getQuantity());

    }


    public List<ProductDTO> getAllProducts() {
        return repository.findAll().stream()
                .map(entity -> new ProductDTO(
                        entity.getId(),
                        entity.getName(),
                        entity.getPrice(),
                        entity.getQuantity()))
                .collect(Collectors.toList());
    }

    public void deleteProduct(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado com o ID: " + id));
        repository.deleteById(id);
    }
}

