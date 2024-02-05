package de.aittr.g_31_2_shop.exception_handling;

public class Response {
    protected String message;

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
