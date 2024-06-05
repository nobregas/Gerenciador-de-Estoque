package br.com.estoqueapi.estoqueapi.repositories;

import br.com.estoqueapi.estoqueapi.entities.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoriaRepository
    extends MongoRepository<Categoria, String>
{
    Optional<Categoria> findByNome(String nome);
    boolean existsByNome(String nome);
}
