package com.reactorsolutions.woodland.service;

import com.reactorsolutions.woodland.dto.*;
import com.reactorsolutions.woodland.mapper.TurnMapper;
import com.reactorsolutions.woodland.model.Action;
import com.reactorsolutions.woodland.model.Game;
import com.reactorsolutions.woodland.model.Turn;
import com.reactorsolutions.woodland.repository.GameRepository;
import com.reactorsolutions.woodland.repository.TurnRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class TurnService {
    private final TurnRepository turnRepository;
    private final GameRepository gameRepository;
    private final TurnMapper turnMapper;

    public TurnService(TurnRepository turnRepository, GameRepository gameRepository, TurnMapper turnMapper) {
        this.turnRepository = turnRepository;
        this.gameRepository = gameRepository;
        this.turnMapper = turnMapper;
    }

    public TurnDTO createTurn(String gameId, CreateTurnDTO dto) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new NoSuchElementException("Partida no encontrada con ID: " + gameId));
        if (dto.getExpectedGameVersion() == null || !dto.getExpectedGameVersion().equals(game.getVersion())) {
            throw new OptimisticLockingFailureException("Conflicto de versión. La versión esperada (" + dto.getExpectedGameVersion() + ") no coincide con la versión actual (" + game.getVersion() + ")");
        }

        Optional<Turn> previousTurn = turnRepository.findTopByGameIdOrderBySequenceDesc(gameId);
        validateOccurredAt(game, previousTurn.orElse(null), dto.getOccurredAt());
        int sequence = previousTurn.map(Turn::getSequence).map(lastSequence -> lastSequence + 1).orElse(1);
        for (Action action : dto.getActions()) {
            action.setActionId(UUID.randomUUID().toString());
        }

        Turn turn = turnMapper.toEntity(dto);
        turn.setId(UUID.randomUUID().toString());
        turn.setGameId(gameId);
        turn.setSequence(sequence);
        turn.setCreatedAt(Instant.now());
        turn.setUpdatedAt(turn.getCreatedAt());

        gameRepository.save(game);
        return turnMapper.toDto(turnRepository.save(turn));
    }

    public ResponseDTO<TurnDTO> search(String idGame, int page, int size, TurnSearchCriteriaDTO criteria) {
        Pageable pageable = PageRequest.of(page, size);
        Optional<Game> game = gameRepository.findById(idGame);
        if(game.isPresent()){
            Page<TurnDTO> gamesPage = turnRepository.search(idGame, criteria, pageable).map(turnMapper::toDto);
            return new ResponseDTO<>(gamesPage.getContent(), gamesPage.getNumber(), gamesPage.getSize(), gamesPage.getTotalElements(), gamesPage.getTotalPages());
        }else{
            throw new NoSuchElementException("La partida con id "+ idGame + " no existe");
        }
    }

    private void validateOccurredAt(Game game, Turn previousTurn, Instant occurredAt) {
        if (game.getStartedAt() == null) {
            throw new IllegalStateException("No se puede crear un turno en una partida que no ha comenzado");
        }

        Instant now = Instant.now();
        if (occurredAt.isBefore(game.getStartedAt()) || occurredAt.isAfter(now)) {
            throw new IllegalArgumentException("occurredAt debe estar entre el inicio de la partida y el instante actual");
        }

        if (previousTurn != null && occurredAt.isBefore(previousTurn.getOccurredAt())) {
            throw new IllegalArgumentException("occurredAt no puede ser anterior al turno previo");
        }
    }
}
