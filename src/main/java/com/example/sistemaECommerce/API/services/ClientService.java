package com.example.sistemaECommerce.API.services;

import com.example.sistemaECommerce.API.dtos.ClientDTO;
import com.example.sistemaECommerce.API.dtos.ClientUpdateDTO;
import com.example.sistemaECommerce.API.exceptions.ClientAlreadyExistsException;
import com.example.sistemaECommerce.API.exceptions.ClientNotFoundException;
import com.example.sistemaECommerce.API.models.ClientEntity;
import com.example.sistemaECommerce.API.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public ClientDTO registerClient(ClientDTO clientDTO, String cpf, String email) {
        ClientEntity existingEmail = repository.findByEmailIgnoreCase(email);
        if (existingEmail != null) {
            throw new ClientAlreadyExistsException("Já existe um cliente com o e-mail: " + email);
        }

        ClientEntity existingCpf = repository.findByCpf(cpf);
        if (existingCpf != null) {
            throw new ClientAlreadyExistsException("Já existe um cliente com o CPF: " + cpf);
        }

        ClientEntity entity = new ClientEntity(
                null,
                clientDTO.name(),
                clientDTO.cpf(),
                clientDTO.email()
        );

        ClientEntity savedEntity = repository.save(entity);

        return new ClientDTO(
                savedEntity.getId(),
                savedEntity.getName(),
                savedEntity.getCpf(),
                savedEntity.getEmail()
        );
    }

    public ClientDTO getClient(String cpf) {
        ClientEntity client = findClientByCpf(cpf);
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getCpf(),
                client.getEmail()
        );
    }

    public ClientDTO updateClient(String cpf, ClientUpdateDTO updatedClient) {
        ClientEntity client = findClientByCpf(cpf);
        client.setName(updatedClient.name());
        client.setEmail(updatedClient.email());
        ClientEntity savedEntity = repository.save(client);
        return new ClientDTO(
                savedEntity.getId(),
                savedEntity.getName(),
                savedEntity.getCpf(),
                savedEntity.getEmail()
        );
    }
    private ClientEntity findClientByCpf(String cpf) {
        ClientEntity client = repository.findByCpf(cpf);
        if (client == null) {
            throw new ClientNotFoundException("Cliente não encontrado com o CPF: " + cpf);
        }
        return client;
    }
}
