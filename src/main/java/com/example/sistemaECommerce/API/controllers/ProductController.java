package com.example.sistemaECommerce.API.controllers;

import com.example.sistemaECommerce.API.dtos.ProductDTO;
import com.example.sistemaECommerce.API.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO product) {

        ProductDTO productCreated = service.registerProduct(product, product.name());

        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> listProducts = service.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(listProducts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
