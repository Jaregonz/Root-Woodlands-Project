package com.reactorsolutions.woodland.repository;

import com.reactorsolutions.woodland.model.Turn;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TurnRepository extends MongoRepository<Turn, String>, TurnRepositorySearch {
    Optional<Turn> findTopByGameIdOrderBySequenceDesc(String gameId);
}
