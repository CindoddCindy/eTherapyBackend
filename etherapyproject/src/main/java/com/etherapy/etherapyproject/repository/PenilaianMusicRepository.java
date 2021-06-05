package com.etherapy.etherapyproject.repository;

import com.etherapy.etherapyproject.model.PenilaianMusic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PenilaianMusicRepository extends JpaRepository<PenilaianMusic, String> {
    Page<PenilaianMusic> findByMusicId(String musicId, Pageable pageable);
    Optional<PenilaianMusic> findByIdAndMusicId(String id, String musicId);
}
