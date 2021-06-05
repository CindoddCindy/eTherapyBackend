package com.etherapy.etherapyproject.repository;

import com.etherapy.etherapyproject.model.GameAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerGameRepository extends JpaRepository<GameAnswer, Long> {
    Page<GameAnswer> findByGameId(Long gameId, Pageable pageable);
    Optional<GameAnswer> findByIdAndGameId(Long id, Long gameId);
}
