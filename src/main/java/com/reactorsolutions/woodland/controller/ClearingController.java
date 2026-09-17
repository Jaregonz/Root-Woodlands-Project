package com.reactorsolutions.woodland.controller;

import com.reactorsolutions.woodland.dto.ClearingDTO;
import com.reactorsolutions.woodland.service.ClearingService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clearings")
public class ClearingController {

    private final ClearingService clearingService;

    public ClearingController(ClearingService clearingService) {
        this.clearingService = clearingService;
    }

    @GetMapping
    public ResponseEntity<Page<ClearingDTO>> getClearingsWithFilters(
            @RequestParam(required = false) String mapCode,
            @RequestParam(required = false) String suit,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Page<ClearingDTO> result = clearingService.findFiltered(mapCode, suit, page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClearingDTO> findClearingById(@PathVariable String id) {
        return clearingService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
