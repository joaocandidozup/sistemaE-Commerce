package com.example.sistemaECommerce.API.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PurchaseRequestDTO(
        @NotBlank(message = "O CPF é obrigatório")
        String cpf,
        List<ProductDTO> produtos
) {
}
