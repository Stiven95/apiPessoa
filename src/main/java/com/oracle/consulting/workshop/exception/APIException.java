package com.oracle.consulting.workshop.exception;

import org.springframework.http.HttpStatus;

import java.util.Objects;

/**
 * Representa exeções para projetos de REST API. Não deve ser herdada por exceções de negócio nem
 * fazer parte de assinaturas de métodos core.
 */
public class APIException extends Exception {

    private static final long serialVersionUID = 1L;

    private final HttpStatus statusCode;

    public APIException(Throwable cause, HttpStatus statusCode) {
        super(cause);
        this.statusCode = Objects.requireNonNull(statusCode);
    }

    public APIException(HttpStatus statusCode) {
        this.statusCode = Objects.requireNonNull(statusCode);
    }

    public HttpStatus getStatus() {
        return statusCode;
    }

}
