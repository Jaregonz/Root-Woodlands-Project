package com.reactorsolutions.woodland.repository;

import com.reactorsolutions.woodland.dto.GameSearchDTO;
import com.reactorsolutions.woodland.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GameRespositorySearch {
    Page<Game> search(GameSearchDTO criteria, Pageable pageable);
}
