package com.reactorsolutions.woodland.repository;

import com.reactorsolutions.woodland.dto.TurnSearchCriteriaDTO;
import com.reactorsolutions.woodland.model.Turn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TurnRepositorySearch {
    Page<Turn> search(String idGame, TurnSearchCriteriaDTO criteria, Pageable pageable);
}
