package br.com.estoqueapi.estoqueapi.repositories;

import br.com.estoqueapi.estoqueapi.entities.Categoria;
import br.com.estoqueapi.estoqueapi.entities.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProdutoRepository
extends MongoRepository<Produto, String> {
    List<Produto> findAllByCategoriasIn(List<Categoria> categorias);
}
