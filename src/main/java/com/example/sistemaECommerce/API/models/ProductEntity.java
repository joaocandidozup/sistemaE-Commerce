package com.example.sistemaECommerce.API.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(unique = true)
    private String name;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço tem que ser maior que zero")
    private Double price;

    @NotNull(message = "A quantidade é obrigatoria")
    private int quantity;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, Double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
