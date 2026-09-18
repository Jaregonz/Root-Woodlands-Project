package com.reactorsolutions.woodland.mapper;

import com.reactorsolutions.woodland.dto.CreateGameDTO;
import com.reactorsolutions.woodland.dto.GameDTO;
import com.reactorsolutions.woodland.dto.ParticipantDTO;
import com.reactorsolutions.woodland.model.Game;
import com.reactorsolutions.woodland.model.Participant;
import com.reactorsolutions.woodland.model.enums.GameStatus;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Map;

@Mapper(componentModel = "spring", imports = {GameStatus.class})
public interface GameMapper {
    Game toEntity(GameDTO dto);

    GameDTO toDto(Game entity);

    default ParticipantDTO toParticipantDto(Participant participant) {
        if (participant == null) {
            return null;
        }
        return new ParticipantDTO(
                participant.playerId(),
                participant.displayNameSnapshot(),
                participant.factionCode(),
                participant.finalScore()
        );
    }

    default Participant toParticipantEntity(ParticipantDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Participant(
                dto.playerId(),
                dto.displayNameSnapshot(),
                dto.factionCode(),
                dto.finalScore()
        );
    }
}
