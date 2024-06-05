package br.com.estoqueapi.estoqueapi.dtos.categoria;

import br.com.estoqueapi.estoqueapi.entities.Categoria;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CategoriasListDTO(
        @NotNull(message = "O campo categoriaNomes é obrigatório")
        List<String> categoriasNome
) {
}
