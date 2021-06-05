package com.etherapy.etherapyproject.controller;


import com.etherapy.etherapyproject.exception.ResourceNotFoundException;
import com.etherapy.etherapyproject.model.PenilaianMusic;
import com.etherapy.etherapyproject.music.repository.MusicRepository;
import com.etherapy.etherapyproject.repository.PenilaianMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MusicPenilaianController {

    @Autowired
    private PenilaianMusicRepository penilaianMusicRepository;

    @Autowired
    private MusicRepository musicRepository;

    @GetMapping("/music/{musicId}/penilaianmusic")
    public Page<PenilaianMusic> getAllPenilaianByMusicId(@PathVariable (value = "musicId") String musicId,
                                                Pageable pageable) {
        return penilaianMusicRepository.findByMusicId(musicId, pageable);
    }

    @PostMapping("/music/{musicId}/penilaianmusic")
    public PenilaianMusic createPenilaian(@PathVariable (value = "musicId") String musicId,
                                 @Valid @RequestBody PenilaianMusic penilaianMusic) {
        return musicRepository.findById(musicId).map(music -> {
            penilaianMusic.setMusic(music);
            return penilaianMusicRepository.save(penilaianMusic);
        }).orElseThrow(() -> new ResourceNotFoundException("MusicId " + musicId + " not found"));
    }

    @PutMapping("/music/{musicId}/penilaianmusic/{penilaianMusicId}")
    public PenilaianMusic updatePenilaian(@PathVariable (value = "musicId") String musicId,
                                 @PathVariable (value = "penilaianMusicId") String penilaianMusicId,
                                 @Valid @RequestBody PenilaianMusic penilaianRequest) {
        if(!musicRepository.existsById(musicId)) {
            throw new ResourceNotFoundException("MusicId " + musicId + " not found");
        }

        return penilaianMusicRepository.findById(penilaianMusicId).map(penilaianMusic -> {
            penilaianMusic.setRate(penilaianRequest.getRate());
            return penilaianMusicRepository.save(penilaianMusic);
        }).orElseThrow(() -> new ResourceNotFoundException("PenilaianId " + penilaianMusicId + "not found"));
    }

    @DeleteMapping("/music/{musicId}/penilaianmusic/{penilaianMusicId}")
    public ResponseEntity<?> deletePenilaian(@PathVariable (value = "musicId") String musicId,
                                           @PathVariable (value = "penilaianMusicId") String penilaianMusicId) {
        return penilaianMusicRepository.findByIdAndMusicId(penilaianMusicId, musicId).map(penilaianMusic -> {
            penilaianMusicRepository.delete(penilaianMusic);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Penilaian  not found with id " + penilaianMusicId + " and postId " + musicId));
    }
}
