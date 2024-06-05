package br.com.estoqueapi.estoqueapi.entities;

import br.com.estoqueapi.estoqueapi.dtos.categoria.CategoriaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "categorias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    private String id;

    @Indexed(unique = true)
    private String nome;

    private String descricao;

    private Instant created_at;

    private Instant updated_at;

    public Categoria(CategoriaDTO categoria) {
        this.nome = categoria.nome();
        this.descricao = categoria.descricao();
    }
}
