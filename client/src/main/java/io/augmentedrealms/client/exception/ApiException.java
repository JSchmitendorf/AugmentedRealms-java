package io.augmentedrealms.client.exception;

public class ApiException extends Exception {

    private final ApiExceptionResponse response;

    public ApiException(ApiExceptionResponse response) {
        this.response = response;
    }

    public ApiExceptionResponse getResponse() {
        return response;
    }
}
