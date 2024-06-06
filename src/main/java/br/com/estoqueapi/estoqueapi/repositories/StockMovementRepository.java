package br.com.estoqueapi.estoqueapi.repositories;

import br.com.estoqueapi.estoqueapi.entities.Produto;
import br.com.estoqueapi.estoqueapi.entities.StockMovement;
import br.com.estoqueapi.estoqueapi.entities.enums.MovementType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StockMovementRepository
extends MongoRepository<StockMovement, String> {
    List<StockMovement> findAllByProdutoIn(Produto produto);
    List<StockMovement> findAllByMovementType(MovementType movementType);
}
