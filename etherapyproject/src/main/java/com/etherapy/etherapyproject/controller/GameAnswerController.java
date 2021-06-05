package com.etherapy.etherapyproject.controller;

import com.etherapy.etherapyproject.exception.ResourceNotFoundException;
import com.etherapy.etherapyproject.model.GameAnswer;
import com.etherapy.etherapyproject.repository.AnswerGameRepository;
import com.etherapy.etherapyproject.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GameAnswerController {

    @Autowired
    private AnswerGameRepository answerGameRepository;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/game/{gameId}/answerGame")
    public Page<GameAnswer> getAllCommentsByPostId(@PathVariable (value = "gameId") Long gameId,
                                                   Pageable pageable) {
        return answerGameRepository.findByGameId(gameId, pageable);
    }

    @PostMapping("/game/{gameId}/answerGame")
    public GameAnswer createAnswer(@PathVariable (value = "gameId") Long gameId,
                                 @Valid @RequestBody GameAnswer gameAnswer) {
        return gameRepository.findById(gameId).map(game -> {
            gameAnswer.setGame(game);
            return answerGameRepository.save(gameAnswer);
        }).orElseThrow(() -> new ResourceNotFoundException("GameId " + gameId + " not found"));
    }

    @PutMapping("/game/{gameId}/answerGame/{gameAnswerId}")
    public GameAnswer updateGameAnswer(@PathVariable (value = "gameId") Long gameId,
                                 @PathVariable (value = "gameAnswerIdd") Long gameAnswerId,
                                 @Valid @RequestBody GameAnswer gameAnswerRequest) {
        if(!gameRepository.existsById(gameId)) {
            throw new ResourceNotFoundException("GameId " + gameId + " not found");
        }

        return answerGameRepository.findById(gameAnswerId).map(gameAnswer -> {
            gameAnswer.setAnswerA(gameAnswerRequest.getAnswerA());
            gameAnswer.setAnswerB(gameAnswerRequest.getAnswerB());
            gameAnswer.setAnswerC(gameAnswerRequest.getAnswerC());
            gameAnswer.setAnswerD(gameAnswerRequest.getAnswerD());
            return answerGameRepository.save(gameAnswer);
        }).orElseThrow(() -> new ResourceNotFoundException("GameAnswerId " + gameAnswerId + "not found"));
    }

    @DeleteMapping("/game/{gameId}/answerGame/{gameAnswerId}")
    public ResponseEntity<?> deleteAnswerGame(@PathVariable (value = "gameId") Long gameId,
                                           @PathVariable (value = "gameAnswerId") Long gameAnswerId) {
        return answerGameRepository.findByIdAndGameId(gameAnswerId, gameId).map(gameAnswer -> {
            answerGameRepository.delete(gameAnswer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Answer Game not found with id " + gameAnswerId + " and postId " + gameId));
    }
}
