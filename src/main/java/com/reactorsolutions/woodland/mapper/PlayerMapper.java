package com.reactorsolutions.woodland.mapper;

import com.reactorsolutions.woodland.dto.PlayerDTO;
import com.reactorsolutions.woodland.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {
    public PlayerDTO toDto(Player player) {
        return new PlayerDTO(
                player.getId(),
                player.getDisplayName(),
                player.getAlias(),
                player.getAliasNormalized(),
                player.getCreatedAt(),
                player.getUpdatedAt(),
                player.getVersion()
        );
    }

    public Player toEntity(PlayerDTO playerDTO){
        return new Player(
                playerDTO.getId(),
                playerDTO.getDisplayName(),
                playerDTO.getAlias(),
                playerDTO.getAliasNormalized(),
                playerDTO.getCreatedAt(),
                playerDTO.getUpdatedAt(),
                playerDTO.getVersion()
                );
    }


}
