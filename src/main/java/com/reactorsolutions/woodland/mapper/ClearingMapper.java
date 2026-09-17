package com.reactorsolutions.woodland.mapper;

import com.reactorsolutions.woodland.dto.ClearingDTO;
import com.reactorsolutions.woodland.model.Clearing;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClearingMapper {
    ClearingDTO toDto(Clearing entity);
}
