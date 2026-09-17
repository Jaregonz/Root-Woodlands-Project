package com.reactorsolutions.woodland.controller;

import com.reactorsolutions.woodland.dto.FactionDTO;
import com.reactorsolutions.woodland.dto.ResponseDTO;
import com.reactorsolutions.woodland.service.FactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/factions")
public class FactionController {
    private final FactionService factionService;

    @Autowired
    public FactionController(FactionService factionService) {
        this.factionService = factionService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<FactionDTO>> findAllFactions(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "20") int size) {
        ResponseDTO<FactionDTO> response = factionService.findAll(page, size);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FactionDTO> findPlayerById(@PathVariable String id) {
        return factionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
