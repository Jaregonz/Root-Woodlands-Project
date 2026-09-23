package com.reactorsolutions.woodland.repository;

import com.reactorsolutions.woodland.dto.TurnSearchCriteriaDTO;
import com.reactorsolutions.woodland.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TurnRepositoryImpl implements TurnRepositorySearch {
    private final MongoTemplate mongoTemplate;

    public TurnRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<Turn> search(String idGame, TurnSearchCriteriaDTO criteria, Pageable pageable) {
        Query query = new Query();

        query.addCriteria(Criteria.where("gameId").is(idGame));

        if (criteria.factionCode() != null && !criteria.factionCode().isBlank()) {
            query.addCriteria(Criteria.where("factionCode").is(criteria.factionCode()));
        }

        if (criteria.actionType() != null) {
            String className = switch (criteria.actionType()) {
                case BATTLE -> BattleAction.class.getName();
                case MOVE -> MoveAction.class.getName();
                case BUILD -> BuildAction.class.getName();
                case RECRUIT -> RecruitAction.class.getName();
                case CRAFT -> CraftAction.class.getName();
                case SCORE_POINTS -> ScorePointsAction.class.getName();
                case PLACE_TOKEN -> PlaceTokenAction.class.getName();
                case REMOVE_TOKEN -> RemoveTokenAction.class.getName();
            };
            query.addCriteria(Criteria.where("actions._class").is(className));
        }

        long count = mongoTemplate.count(query, Turn.class);

        query.with(Sort.by(Sort.Direction.ASC, "sequence"));
        query.with(pageable);

        List<Turn> turns = mongoTemplate.find(query, Turn.class);

        return PageableExecutionUtils.getPage(turns, pageable, () -> count);

    }

}
