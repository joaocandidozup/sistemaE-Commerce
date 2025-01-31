package com.example.sistemaECommerce.API.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClientDTO(
        Long id,
        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O cpf é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos")
        String cpf,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "O email deve ser válido")
        String email


) {
}

