package com.reactorsolutions.woodland.mapper;

import com.reactorsolutions.woodland.dto.FactionDTO;
import com.reactorsolutions.woodland.model.Faction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FactionMapper {
    FactionDTO toDto(Faction entity);
}
