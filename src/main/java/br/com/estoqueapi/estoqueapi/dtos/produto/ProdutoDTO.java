package br.com.estoqueapi.estoqueapi.dtos.produto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoDTO(
        @NotBlank(message = "O campo nome é obrigatório")
        @Size(message = "Nome inválido")
        String nome,

        String descricao,

        @NotNull(message = "O campo categoriaNomes é obrigatório")
        List<String> categoriaNomes,

        @NotNull(message = "O campo preco é obrigatório")
        @Min(value = 0, message = "O preco deve ser maior ou igual à 0")
        BigDecimal preco,

        @NotNull(message = "O campo quantidade é obrigatório")
        @Min(value = 0, message = "A quantidade deve ser maior ou igual à 0")
        Integer quantidade
) {

}
