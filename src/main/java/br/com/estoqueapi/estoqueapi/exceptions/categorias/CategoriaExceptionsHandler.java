package br.com.estoqueapi.estoqueapi.exceptions.categorias;

import br.com.estoqueapi.estoqueapi.exceptions.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class CategoriaExceptionsHandler {

    @ResponseBody
    @ExceptionHandler(CategoriaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionMessage handleCategoriaNotFound(CategoriaNotFoundException ex) {
        return new ExceptionMessage(
                HttpStatus.NOT_FOUND.value(),
                "Categoria não encontrada",
                Instant.now()
        );
    }

    @ResponseBody
    @ExceptionHandler(CategoriaAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleCategoriaAlreadyExists(CategoriaAlreadyExistsException ex) {
        return new ExceptionMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Já existe uma categoria com esse nome",
                Instant.now()
        );
    }
}
