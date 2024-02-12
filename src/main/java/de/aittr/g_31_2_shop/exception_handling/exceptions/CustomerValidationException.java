package de.aittr.g_31_2_shop.exception_handling.exceptions;

public class CustomerValidationException extends RuntimeException {
    public CustomerValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
