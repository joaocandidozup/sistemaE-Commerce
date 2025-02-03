package com.example.sistemaECommerce.API.controllers;

import com.example.sistemaECommerce.API.dtos.PurchaseRequestDTO;
import com.example.sistemaECommerce.API.services.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<String> createPurchase(@Valid @RequestBody PurchaseRequestDTO purchaseRequest) {
        purchaseService.processPurchase(purchaseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Compra realizada com sucesso!");
    }
}
