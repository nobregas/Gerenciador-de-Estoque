package br.com.estoqueapi.estoqueapi.dtos.movimentacao;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record StockEntryMovementDTO(
        @NotBlank(message = "O campo produtoId é obrigatório")
        String produtoId,

        @NotNull(message = "O campo quantidade é obrigatório")
        @Min(value = 0, message = "A quantidade deve ser maior ou igual à 0")
        Integer quantidade,

        @NotNull(message = "O campo preco é obrigatório")
        @Min(value = 0, message = "O preco deve ser maior ou igual à 0")
        BigDecimal preco
) {
}
