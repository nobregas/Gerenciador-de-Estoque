package br.com.estoqueapi.estoqueapi.exceptions.produtos;

import br.com.estoqueapi.estoqueapi.exceptions.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ProdutoExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ProdutoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionMessage handleProdutoNotFoundException(ProdutoNotFoundException ex) {
        return new ExceptionMessage(
                HttpStatus.NOT_FOUND.value(),
                "Produto não encontrado",
                Instant.now()
        );
    }

    @ResponseBody
    @ExceptionHandler(QuantidadeInvalidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleQuantidadeInvalidaException(QuantidadeInvalidaException ex) {
        return new ExceptionMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Quantidade inválida",
                Instant.now()
        );
    }
}
