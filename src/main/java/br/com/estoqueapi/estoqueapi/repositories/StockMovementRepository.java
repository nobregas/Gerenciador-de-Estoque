package br.com.estoqueapi.estoqueapi.repositories;

import br.com.estoqueapi.estoqueapi.entities.StockMovement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockMovementRepository
extends MongoRepository<StockMovement, String> {
}
