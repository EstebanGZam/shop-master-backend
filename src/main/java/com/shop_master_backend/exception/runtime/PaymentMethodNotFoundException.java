package com.shop_master_backend.exception.runtime;

public class PaymentMethodNotFoundException extends RuntimeException {

    public PaymentMethodNotFoundException(String message) {
        super(message);
    }

}
