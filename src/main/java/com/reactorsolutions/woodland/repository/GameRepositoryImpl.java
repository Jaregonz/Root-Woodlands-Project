package com.reactorsolutions.woodland.repository;

import com.reactorsolutions.woodland.dto.GameSearchCriteriaDTO;
import com.reactorsolutions.woodland.model.Game;
import com.reactorsolutions.woodland.model.enums.GameStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GameRepositoryImpl implements GameRespositorySearch{
    private final MongoTemplate mongoTemplate;

    public GameRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<Game> search(GameSearchCriteriaDTO criteria, Pageable pageable) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();

        if (criteria.minFinalScore() != null && criteria.status() != null && criteria.status() != GameStatus.FINISHED) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Solo se puede filtrar por minFinalScore con el estado FINISHED"
            );
        }
        if (criteria.status() != null) {
            criteriaList.add(Criteria.where("status").is(criteria.status()));
        }

        List<Criteria> participantFilters = new ArrayList<>();
        if (criteria.playerId() != null) {
            participantFilters.add(Criteria.where("playerId").is(criteria.playerId()));
        }
        if (criteria.factionCode() != null) {
            participantFilters.add(Criteria.where("factionCode").is(criteria.factionCode()));
        }
        if (criteria.minFinalScore() != null) {
            participantFilters.add(Criteria.where("finalScore").gte(criteria.minFinalScore()));
        }

        if (!participantFilters.isEmpty()) {
            Criteria elemMatchCriteria = new Criteria().andOperator(participantFilters.toArray(new Criteria[0]));
            criteriaList.add(Criteria.where("participants").elemMatch(elemMatchCriteria));
        }

        if (criteria.startedFrom() != null && criteria.startedTo() != null) {
            criteriaList.add(Criteria.where("startedAt").gte(criteria.startedFrom()).lte(criteria.startedTo()));
        } else if (criteria.startedFrom() != null) {
            criteriaList.add(Criteria.where("startedAt").gte(criteria.startedFrom()));
        } else if (criteria.startedTo() != null) {
            criteriaList.add(Criteria.where("startedAt").lte(criteria.startedTo()));
        }

        if (criteria.minFinalScore() != null) {
            criteriaList.add(Criteria.where("finalScore").gte(criteria.minFinalScore()));
        }

        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        long count = mongoTemplate.count(query, Game.class);

        query.with(pageable);
        List<Game> games = mongoTemplate.find(query, Game.class);

        return PageableExecutionUtils.getPage(games, pageable, () -> count);
    }
}
