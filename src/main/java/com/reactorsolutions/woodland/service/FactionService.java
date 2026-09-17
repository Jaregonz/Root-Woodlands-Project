package com.reactorsolutions.woodland.service;

import com.reactorsolutions.woodland.dto.FactionDTO;
import com.reactorsolutions.woodland.dto.ResponseDTO;
import com.reactorsolutions.woodland.mapper.FactionMapper;
import com.reactorsolutions.woodland.model.Faction;
import com.reactorsolutions.woodland.repository.FactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FactionService {
    private final FactionRepository factionRepository;
    private final FactionMapper factionMapper;

    public FactionService(FactionRepository factionRepository, FactionMapper factionMapper) {
        this.factionRepository = factionRepository;
        this.factionMapper = factionMapper;
    }

    @Transactional(readOnly = true)
    public ResponseDTO<FactionDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<FactionDTO> factionsPage = factionRepository.findByEnabledTrue(pageable)
                .map(factionMapper::toDto);
        return new ResponseDTO<>(
                factionsPage.getContent(),
                factionsPage.getNumber(),
                factionsPage.getSize(),
                factionsPage.getTotalElements(),
                factionsPage.getTotalPages()
        );
    }
}
