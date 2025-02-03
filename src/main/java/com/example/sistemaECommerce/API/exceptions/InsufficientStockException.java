package com.example.sistemaECommerce.API.exceptions;

import java.util.List;

public class InsufficientStockException extends RuntimeException {
    private List<String> errorMessages;

    public InsufficientStockException(List<String> errorMessages) {
        super(formatMessage(errorMessages));
        this.errorMessages = errorMessages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    private static String formatMessage(List<String> errorMessages){
        if(errorMessages == null || errorMessages.isEmpty()){
            return null;
        }
        return "Produto em falta: [ " + String.join(", ",errorMessages) + "]";
    }
}
