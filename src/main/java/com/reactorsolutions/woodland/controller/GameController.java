package com.reactorsolutions.woodland.controller;

import com.reactorsolutions.woodland.dto.*;
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

    @GetMapping
    public ResponseEntity<ResponseDTO<GameDTO>> searchGamesFiltered(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "20") int size,
                                                                    GameSearchDTO criteria) {
        ResponseDTO<GameDTO> response = gameService.search(page, size, criteria);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> findGameById(@PathVariable String id) {
        return gameService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
