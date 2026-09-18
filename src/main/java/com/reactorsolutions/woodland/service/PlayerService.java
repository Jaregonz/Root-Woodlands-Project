package com.reactorsolutions.woodland.service;

import com.reactorsolutions.woodland.dto.PlayerUpdateDTO;
import com.reactorsolutions.woodland.dto.ResponseDTO;
import com.reactorsolutions.woodland.repository.PlayerRepository;
import com.reactorsolutions.woodland.dto.PlayerDTO;
import com.reactorsolutions.woodland.mapper.PlayerMapper;
import com.reactorsolutions.woodland.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    public PlayerDTO save(PlayerDTO playerDTO) {
        Player player = playerMapper.toEntity(playerDTO);
        if (player.getId() == null || player.getId().isBlank()) {
            player.setId(UUID.randomUUID().toString());
        }
        player.setAliasNormalized(player.getAlias().toLowerCase(Locale.ROOT));
        player.setActive(true);
        Player savedPlayer = playerRepository.insert(player);
        return playerMapper.toDto(savedPlayer);
    }

    public Optional<PlayerDTO> findById(@PathVariable String id) {
        return playerRepository.findById(id).map(this.playerMapper::toDto);
    }

    public ResponseDTO<PlayerDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PlayerDTO> playerPage = playerRepository.findByActiveTrue(pageable)
                .map(playerMapper::toDto);

        return new ResponseDTO<>(
                playerPage.getContent(),
                playerPage.getNumber(),
                playerPage.getSize(),
                playerPage.getTotalElements(),
                playerPage.getTotalPages()
        );
    }

    public PlayerDTO updatePlayer(String id, PlayerUpdateDTO dto) {
        Player playerFound = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con ID: " + id));

        if (dto.getExpectedVersion() == null || !dto.getExpectedVersion().equals(playerFound.getVersion())) {
            throw new OptimisticLockingFailureException(
                    "Conflicto de versión. La versión esperada (" + dto.getExpectedVersion()
                            + ") no coincide con la versión actual (" + playerFound.getVersion() + ")"
            );
        }

        playerFound.setDisplayName(dto.getDisplayName());
        playerFound.setAlias(dto.getAlias());
        if (dto.getAlias() != null) {
            playerFound.setAliasNormalized(dto.getAlias().toLowerCase(Locale.ROOT).trim());
        }

        Player guardado = playerRepository.save(playerFound);
        return playerMapper.toDto(guardado);
    }

    public PlayerDTO deactivate(String id, Long expectedVersion) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Jugador no encontrado con ID: " + id));

        if (!player.isActive()) {
            throw new IllegalStateException("El jugador ya se encuentra desactivado");
        }

        if (expectedVersion == null || !expectedVersion.equals(player.getVersion())) {
            throw new OptimisticLockingFailureException(
                    "Conflicto de versión al desactivar. Versión esperada: " + expectedVersion
                            + ", Versión actual en BBDD: " + player.getVersion()
            );
        }
        player.setActive(false);
        Player desactivado = playerRepository.save(player);

        return playerMapper.toDto(desactivado);
    }

}
