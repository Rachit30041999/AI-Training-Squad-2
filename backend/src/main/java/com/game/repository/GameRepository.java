package com.game.repository;

import com.game.model.Game;
import com.game.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByUser(User user);
    List<Game> findByUserOrderByCreatedAtDesc(User user);
}
