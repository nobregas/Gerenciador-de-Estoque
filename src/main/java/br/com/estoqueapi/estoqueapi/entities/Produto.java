package br.com.estoqueapi.estoqueapi.entities;

import br.com.estoqueapi.estoqueapi.dtos.produto.ProdutoDTO;
import br.com.estoqueapi.estoqueapi.exceptions.produtos.QuantidadeInvalidaException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Document(collection = "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    private String id;

    private String nome;

    private String descricao;

    @DBRef
    private List<Categoria> categorias;

    private BigDecimal preco;

    private Integer quantidade;

    private Instant created_at;

    private Instant updated_at;

    public Produto(ProdutoDTO produto, List<Categoria> categorias) {
        this.nome = produto.nome();
        this.descricao = produto.descricao();
        this.categorias = categorias;
        this.preco = produto.preco();
        this.quantidade = produto.quantidade();
        this.created_at = Instant.now();
    }

    public void addQuantidade(Integer quantidade) {
        this.quantidade += quantidade;
        this.updated_at = Instant.now();
    }

    public void diminuirQuantidade(Integer quantidade) {
        if (quantidade > this.quantidade) {
            throw new QuantidadeInvalidaException();
        } else {
            this.quantidade -= quantidade;
            this.updated_at = Instant.now();
        }
    }
}
