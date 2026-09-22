package com.reactorsolutions.woodland.mapper;

import com.reactorsolutions.woodland.dto.CreateTurnDTO;
import com.reactorsolutions.woodland.model.Turn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TurnMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "gameId", ignore = true)
    @Mapping(target = "sequence", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    Turn toEntity(CreateTurnDTO dto);
}
