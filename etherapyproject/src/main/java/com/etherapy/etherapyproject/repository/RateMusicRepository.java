package com.etherapy.etherapyproject.repository;


import com.etherapy.etherapyproject.model.RateMusic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RateMusicRepository extends JpaRepository<RateMusic, String> {
    Page<RateMusic> findByMusicId(String musicId, Pageable pageable);
    Optional<RateMusic> findByIdAndMusicId(String id, String musicId);
}
