package com.etherapy.etherapyproject.music.repository;

import com.etherapy.etherapyproject.music.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, String> {
}
