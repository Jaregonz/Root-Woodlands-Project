package com.reactorsolutions.woodland.repository;

import com.reactorsolutions.woodland.model.Clearing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClearingRepository extends MongoRepository<Clearing, String> {
    Page<Clearing> findByMapCodeAndSuit(String mapCode,String suit, Pageable pageable);

    Page<Clearing> findBySuit(String suit, Pageable pageable);

    Page<Clearing> findByMapCode(String mapCode, Pageable pageable);
}
