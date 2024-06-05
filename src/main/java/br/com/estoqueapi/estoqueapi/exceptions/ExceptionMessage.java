package br.com.estoqueapi.estoqueapi.exceptions;

import java.time.Instant;

public record ExceptionMessage(
        int status,
        String message,
        Instant timestamp
) {
}