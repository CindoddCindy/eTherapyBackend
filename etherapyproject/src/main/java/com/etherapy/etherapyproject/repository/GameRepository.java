package com.etherapy.etherapyproject.repository;

import com.etherapy.etherapyproject.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
