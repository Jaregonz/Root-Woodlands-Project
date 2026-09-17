package com.reactorsolutions.woodland.controller;

import com.reactorsolutions.woodland.dto.PlayerDTO;
import com.reactorsolutions.woodland.dto.PlayerUpdateDTO;
import com.reactorsolutions.woodland.dto.ResponseDTO;
import com.reactorsolutions.woodland.model.Player;
import com.reactorsolutions.woodland.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO) {
        PlayerDTO newPlayer = playerService.save(playerDTO);
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<PlayerDTO>> findAllPlayers(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "20") int size) {
        ResponseDTO<PlayerDTO> response = playerService.findAll(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> findPlayerById(@PathVariable String id) {
        return playerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable String id, @RequestBody PlayerUpdateDTO dto) {
        PlayerDTO actualizado = playerService.updatePlayer(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<PlayerDTO> deactivatePlayer(
            @PathVariable String id,
            @RequestParam Long expectedVersion) {

        PlayerDTO deactivatedPlayer = playerService.deactivate(id, expectedVersion);
        return ResponseEntity.ok(deactivatedPlayer);
    }
}
