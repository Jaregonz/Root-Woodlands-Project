package com.reactorsolutions.woodland.controller;

import com.reactorsolutions.woodland.dto.CreateGameDTO;
import com.reactorsolutions.woodland.dto.GameDTO;
import com.reactorsolutions.woodland.dto.PlayerDTO;
import com.reactorsolutions.woodland.model.Game;
import com.reactorsolutions.woodland.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) { this.gameService = gameService; }

    @PostMapping
    public ResponseEntity<GameDTO> createPlayer(@RequestBody CreateGameDTO createGameDTO) {
        GameDTO newPlayer = gameService.createGame(createGameDTO);
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }
}
