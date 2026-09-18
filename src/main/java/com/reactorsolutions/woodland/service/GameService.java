package com.reactorsolutions.woodland.service;

import com.reactorsolutions.woodland.dto.CreateGameDTO;
import com.reactorsolutions.woodland.dto.CreateParticipantDTO;
import com.reactorsolutions.woodland.dto.GameDTO;
import com.reactorsolutions.woodland.mapper.GameMapper;
import com.reactorsolutions.woodland.model.Game;
import com.reactorsolutions.woodland.model.Participant;
import com.reactorsolutions.woodland.model.Player;
import com.reactorsolutions.woodland.repository.GameRepository;
import com.reactorsolutions.woodland.repository.PlayerRepository;
import org.springframework.stereotype.Service;

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
}
