package com.reactorsolutions.woodland.controller;

import com.reactorsolutions.woodland.dto.*;
import com.reactorsolutions.woodland.service.GameService;
import com.reactorsolutions.woodland.service.TurnService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;
    private final TurnService turnService;

    @Autowired
    public GameController(GameService gameService, TurnService turnService) {
        this.gameService = gameService;
        this.turnService = turnService;
    }

    @PostMapping
    public ResponseEntity<GameDTO> createGame(@RequestBody CreateGameDTO createGameDTO) {
        GameDTO newGame = gameService.createGame(createGameDTO);
        return new ResponseEntity<>(newGame, HttpStatus.CREATED);
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
                                                                    GameSearchCriteriaDTO criteria) {
        ResponseDTO<GameDTO> response = gameService.search(page, size, criteria);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> findGameById(@PathVariable String id) {
        return gameService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GameDTO> deleteDraft(
            @PathVariable String id,
            @RequestParam Long expectedVersion) {

        GameDTO deletedDraft = gameService.deleteDraft(id, expectedVersion);
        return ResponseEntity.ok(deletedDraft);
    }

    @PostMapping("/{idGame}/turns")
    public ResponseEntity<TurnDTO> createTurn(@PathVariable String idGame,
                                              @Valid @RequestBody CreateTurnDTO createTurnDTO) {
        return new ResponseEntity<>(turnService.createTurn(idGame, createTurnDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{idGame}/turns")
    public ResponseEntity<ResponseDTO<TurnDTO>> searchGameTurnsFiltered(@PathVariable String idGame,
                                                                        @RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "20") int size,
                                                                        @RequestBody TurnSearchCriteriaDTO criteria) {
        ResponseDTO<TurnDTO> response = turnService.search(idGame, page, size, criteria);
        return ResponseEntity.ok(response);
    }
}
