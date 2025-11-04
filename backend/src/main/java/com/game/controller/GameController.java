package com.game.controller;

import com.game.model.Game;
import com.game.model.User;
import com.game.service.GameService;
import com.game.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GameController {
    
    private final GameService gameService;
    private final UserService userService;
    
    @PostMapping("/start")
    public ResponseEntity<?> startGame(@RequestBody Map<String, Object> request) {
        try {
            Long userId = Long.valueOf(request.get("userId").toString());
            Integer difficulty = Integer.valueOf(request.get("difficulty").toString());
            
            User user = userService.getUserById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            
            Game game = gameService.startNewGame(user, difficulty);
            
            // Don't expose the target number to the client
            Map<String, Object> response = Map.of(
                    "id", game.getId(),
                    "difficulty", game.getDifficulty(),
                    "attemptsCount", game.getAttemptsCount(),
                    "status", game.getStatus(),
                    "createdAt", game.getCreatedAt()
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/{id}/guess")
    public ResponseEntity<?> submitGuess(@PathVariable Long id, 
                                          @RequestBody Map<String, Integer> request) {
        try {
            Integer guess = request.get("guess");
            
            if (guess == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Guess is required"));
            }
            
            Game game = gameService.submitGuess(id, guess);
            
            String hint = gameService.getHint(game, guess);
            
            Map<String, Object> response = Map.of(
                    "id", game.getId(),
                    "attemptsCount", game.getAttemptsCount(),
                    "status", game.getStatus(),
                    "score", game.getScore() != null ? game.getScore() : 0,
                    "hint", hint,
                    "correct", game.getStatus().name().equals("WON")
            );
            
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getGame(@PathVariable Long id) {
        return gameService.getGameById(id)
                .map(game -> ResponseEntity.ok(Map.of(
                        "id", game.getId(),
                        "difficulty", game.getDifficulty(),
                        "attemptsCount", game.getAttemptsCount(),
                        "status", game.getStatus(),
                        "score", game.getScore() != null ? game.getScore() : 0
                )))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Game>> getUserGames(@PathVariable Long userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        return ResponseEntity.ok(gameService.getUserGames(user));
    }
}
