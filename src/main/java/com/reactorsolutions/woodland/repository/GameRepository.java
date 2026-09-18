package com.reactorsolutions.woodland.repository;

import com.reactorsolutions.woodland.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {
}
