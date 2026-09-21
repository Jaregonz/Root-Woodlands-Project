package com.reactorsolutions.woodland.controller;

import com.reactorsolutions.woodland.dto.CreateGameDTO;
import com.reactorsolutions.woodland.dto.GameDTO;
import com.reactorsolutions.woodland.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<GameDTO> createPlayer(@RequestBody CreateGameDTO createGameDTO) {
        GameDTO newPlayer = gameService.createGame(createGameDTO);
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<GameDTO> startGame(@PathVariable String id, @RequestParam Long expectedGameVersion) {
        GameDTO gameStarted = gameService.startGame(id, expectedGameVersion);
        return ResponseEntity.ok(gameStarted);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<GameDTO> cancelGame(@PathVariable String id, @RequestParam Long expectedGameVersion) {
        GameDTO gameStarted = gameService.cancelGame(id, expectedGameVersion);
        return ResponseEntity.ok(gameStarted);
    }

}
