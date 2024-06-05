package br.com.estoqueapi.estoqueapi.repositories;

import br.com.estoqueapi.estoqueapi.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository
extends MongoRepository<User, String> {
}
