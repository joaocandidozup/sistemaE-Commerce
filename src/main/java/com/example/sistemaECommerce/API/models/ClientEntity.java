package com.example.sistemaECommerce.API.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "O cpf é obrigatório")
    @Pattern(regexp = "\\d{11}",message = "O CPF deve conter exatamente 11 dígitos numéricos")
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    @Column(unique = true)
    private String email;

    public ClientEntity() {
    }

    public ClientEntity(Long id, String name, String cpf, String email) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
