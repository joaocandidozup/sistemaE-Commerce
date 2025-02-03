package com.example.sistemaECommerce.API.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        Map<String, List<String>> response = new HashMap<>();
        response.put("erro", errors);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleProductAlreadyExistsException(ProductAlreadyExistsException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("erro", ex.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("erro", ex.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleClientAlreadyExistsException(ClientAlreadyExistsException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("erro", ex.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }


    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Map<String, String>> handleInsufficientStockException(InsufficientStockException ex) {
        String message = ex.getMessage();
        Map<String, String> response = new HashMap<>();
        response.put("erro", message);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> handleClientNotFound(ClientNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        String response = "Ocorreu um erro interno no servidor. Por favor, tente novamente mais tarde.";
        return ResponseEntity.internalServerError().body(response);
    }

}




