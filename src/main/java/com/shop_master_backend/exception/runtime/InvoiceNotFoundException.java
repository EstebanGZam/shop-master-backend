package com.shop_master_backend.exception.runtime;

public class InvoiceNotFoundException extends RuntimeException {

    public InvoiceNotFoundException(String message) {
        super(message);
    }

}
