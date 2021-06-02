package com.etherapy.etherapyproject.controller;

import com.etherapy.etherapyproject.exception.ResourceNotFoundException;
import com.etherapy.etherapyproject.model.Game;
import com.etherapy.etherapyproject.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/game")
    public Page<Game> getAllGames(Pageable pageable) {
        return gameRepository.findAll(pageable);
    }

    @PostMapping("/game")
    public Game createGame(@Valid @RequestBody Game game) {
        return gameRepository.save(game);
    }

    @PutMapping("/game/{gameId}")
    public Game updateGame(@PathVariable Long postId, @Valid @RequestBody Game gameRequest) {
        return gameRepository.findById(postId).map(game -> {
            game.setTitle(gameRequest.getTitle());
            game.setDescription(gameRequest.getDescription());
            game.setContent(gameRequest.getContent());
            return gameRepository.save(game);
        }).orElseThrow(() -> new ResourceNotFoundException("GameId " + postId + " not found"));
    }


    @DeleteMapping("/game/{gameId}")
    public ResponseEntity<?> deleteGame(@PathVariable Long gameId) {
        return gameRepository.findById(gameId).map(game -> {
            gameRepository.delete(game);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("GameId " + gameId + " not found"));
    }
}
