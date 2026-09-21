package com.reactorsolutions.woodland.service;

import com.reactorsolutions.woodland.dto.*;
import com.reactorsolutions.woodland.mapper.GameMapper;
import com.reactorsolutions.woodland.model.Game;
import com.reactorsolutions.woodland.model.Participant;
import com.reactorsolutions.woodland.model.Player;
import com.reactorsolutions.woodland.model.enums.GameStatus;
import com.reactorsolutions.woodland.repository.GameRepository;
import com.reactorsolutions.woodland.repository.PlayerRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final GameMapper gameMapper;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.gameMapper = gameMapper;
    }

    public GameDTO createGame(CreateGameDTO dto) {
        List<String> playerIds = dto.getParticipants().stream()
                .map(CreateParticipantDTO::playerId)
                .toList();

        Map<String, String> playerNamesMap = playerRepository.findAllById(playerIds).stream()
                .collect(Collectors.toMap(Player::getId, Player::getDisplayName));

        if (playerNamesMap.size() != playerIds.size()) {
            throw new IllegalArgumentException("Uno o más participantes no existen en el sistema.");
        }
        List<Participant> participants = dto.getParticipants().stream()
                .map(p -> new Participant(
                        p.playerId(),
                        playerNamesMap.get(p.playerId()),
                        p.factionCode(),
                        null
                ))
                .toList();

        Game game = Game.createNew(
                dto.getName(),
                dto.getMapCode(),
                dto.getClearingIds(),
                participants
        );

        return gameMapper.toDto(gameRepository.save(game));
    }

    public GameDTO startGame(String id, Long expectedGameVersion) {
        Game gameFound = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partida no encontrada con ID: " + id));

        if (expectedGameVersion == null || !expectedGameVersion.equals(gameFound.getVersion())) {
            throw new OptimisticLockingFailureException(
                    "Conflicto de versión. La versión esperada (" + expectedGameVersion
                            + ") no coincide con la versión actual (" + gameFound.getVersion() + ")"
            );
        }

        gameFound.setStatus(GameStatus.IN_PROGRESS);
        gameFound.setStartedAt(Instant.now());

        Game guardado = gameRepository.save(gameFound);
        return gameMapper.toDto(guardado);
    }

    public GameDTO cancelGame(String id, Long expectedGameVersion) {
        Game gameFound = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partida no encontrada con ID: " + id));

        if (expectedGameVersion == null || !expectedGameVersion.equals(gameFound.getVersion())) {
            throw new OptimisticLockingFailureException(
                    "Conflicto de versión. La versión esperada (" + expectedGameVersion
                            + ") no coincide con la versión actual (" + gameFound.getVersion() + ")"
            );
        }

        gameFound.setStatus(GameStatus.CANCELLED);
        gameFound.setCancelledAt(Instant.now());

        Game guardado = gameRepository.save(gameFound);
        return gameMapper.toDto(guardado);
    }
}
