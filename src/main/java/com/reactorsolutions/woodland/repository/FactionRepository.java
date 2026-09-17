package com.reactorsolutions.woodland.repository;

import com.reactorsolutions.woodland.model.Faction;
import com.reactorsolutions.woodland.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FactionRepository extends MongoRepository<Faction, String> {
    Page<Player> findByEnabledTrue(Pageable pageable);
}
