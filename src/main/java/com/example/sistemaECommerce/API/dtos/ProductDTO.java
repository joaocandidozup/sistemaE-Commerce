package com.example.sistemaECommerce.API.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductDTO(
        Long id,
        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço tem que ser maior que zero")
        Double price,


        @NotNull(message = "A quantidade é obrigatória")
        @Min(value = 0, message = "A quantidade deve ser um número inteiro maior ou igual a 0")
        Integer quantity
) {
}
