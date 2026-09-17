package com.reactorsolutions.woodland.service;

import com.reactorsolutions.woodland.dto.ClearingDTO;
import com.reactorsolutions.woodland.mapper.ClearingMapper;
import com.reactorsolutions.woodland.model.Clearing;
import com.reactorsolutions.woodland.repository.ClearingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class ClearingService {
    private final ClearingRepository clearingRepository;
    private final ClearingMapper clearingMapper;

    public ClearingService(ClearingRepository clearingRepository, ClearingMapper clearingMapper) {
        this.clearingRepository = clearingRepository;
        this.clearingMapper = clearingMapper;
    }

    @Transactional(readOnly = true)
    public Page<ClearingDTO> findFiltered(String mapCode, String suit, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        boolean hasMapCode = StringUtils.hasText(mapCode);
        boolean hasSuit = StringUtils.hasText(suit);

        Page<Clearing> entities;

        if (hasMapCode && hasSuit) {
            entities = clearingRepository.findByMapCodeAndSuit(mapCode, suit, pageable);
        } else if (hasMapCode) {
            entities = clearingRepository.findByMapCode(mapCode, pageable);
        } else if (hasSuit) {
            entities = clearingRepository.findBySuit(suit, pageable);
        } else {
            entities = clearingRepository.findAll(pageable);
        }

        return entities.map(clearingMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<ClearingDTO> findById(@PathVariable String id) {
        return clearingRepository.findById(id).map(this.clearingMapper::toDto);
    }
}
