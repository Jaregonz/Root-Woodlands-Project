package com.reactorsolutions.woodland.repository;

import com.reactorsolutions.woodland.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    Page<Player> findByActiveTrue(Pageable pageable);
}
