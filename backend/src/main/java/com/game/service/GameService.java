package com.game.service;

import com.game.model.Game;
import com.game.model.GameStatus;
import com.game.model.User;
import com.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {
    
    private final GameRepository gameRepository;
    private final Random random = new Random();
    
    @Transactional
    public Game startNewGame(User user, int difficulty) {
        if (difficulty < 2 || difficulty > 4) {
            throw new IllegalArgumentException("Difficulty must be between 2 and 4");
        }
        
        int targetNumber = generateNumber(difficulty);
        
        Game game = new Game();
        game.setUser(user);
        game.setDifficulty(difficulty);
        game.setTargetNumber(targetNumber);
        game.setAttemptsCount(0);
        game.setStatus(GameStatus.IN_PROGRESS);
        
        log.info("Starting new game for user {} with difficulty {}", user.getEmail(), difficulty);
        return gameRepository.save(game);
    }
    
    private int generateNumber(int difficulty) {
        int min = (int) Math.pow(10, difficulty - 1);
        int max = (int) Math.pow(10, difficulty) - 1;
        return random.nextInt(max - min + 1) + min;
    }
    
    @Transactional
    public Game submitGuess(Long gameId, int guess) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));
        
        if (game.getStatus() != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Game is already completed");
        }
        
        game.setAttemptsCount(game.getAttemptsCount() + 1);
        
        int maxAttempts = getMaxAttempts(game.getDifficulty());
        
        if (guess == game.getTargetNumber()) {
            game.setStatus(GameStatus.WON);
            game.setScore(calculateScore(game.getAttemptsCount()));
            game.setCompletedAt(LocalDateTime.now());
            log.info("Game {} won in {} attempts", gameId, game.getAttemptsCount());
        } else if (game.getAttemptsCount() >= maxAttempts) {
            game.setStatus(GameStatus.LOST);
            game.setScore(0);
            game.setCompletedAt(LocalDateTime.now());
            log.info("Game {} lost after {} attempts", gameId, game.getAttemptsCount());
        }
        
        return gameRepository.save(game);
    }
    
    private int getMaxAttempts(int difficulty) {
        return switch (difficulty) {
            case 2 -> 7;
            case 3 -> 10;
            case 4 -> 12;
            default -> 10;
        };
    }
    
    private int calculateScore(int attempts) {
        return Math.max(0, 100 - (attempts * 5));
    }
    
    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }
    
    public List<Game> getUserGames(User user) {
        return gameRepository.findByUserOrderByCreatedAtDesc(user);
    }
    
    public String getHint(Game game, int guess) {
        if (game.getAttemptsCount() < 2) {
            return "Hints available after 2 attempts";
        }
        
        if (guess > game.getTargetNumber()) {
            return "The number is lower than your guess";
        } else {
            return "The number is higher than your guess";
        }
    }
}
