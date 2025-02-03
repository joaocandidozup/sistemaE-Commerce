package com.example.sistemaECommerce.API.repositories;

import com.example.sistemaECommerce.API.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    ClientEntity findByEmailIgnoreCase(String email);

    ClientEntity findByCpf(String cpf);
}
