package com.example.sistemaECommerce.API.controllers;

import com.example.sistemaECommerce.API.dtos.ClientDTO;
import com.example.sistemaECommerce.API.dtos.ClientUpdateDTO;
import com.example.sistemaECommerce.API.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO client) {
        ClientDTO clientCreated = service.registerClient(client, client.cpf(), client.email());
        return ResponseEntity.status(HttpStatus.CREATED).body(clientCreated);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable String cpf) {
        ClientDTO client = service.getClient(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable String cpf, @Valid @RequestBody ClientUpdateDTO updatedClient) {
        ClientDTO client = service.updateClient(cpf, updatedClient);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }
}
