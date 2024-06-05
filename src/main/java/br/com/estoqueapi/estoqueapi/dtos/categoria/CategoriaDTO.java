package br.com.estoqueapi.estoqueapi.dtos.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoriaDTO(
        @NotBlank(message = "O campo nome é obrigatório")
        @Size(min = 2, max = 30, message = "Nome inválido")
        String nome,
        String descricao
) {
}
