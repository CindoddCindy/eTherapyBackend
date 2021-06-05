package com.etherapy.etherapyproject.controller;


import com.etherapy.etherapyproject.exception.ResourceNotFoundException;
import com.etherapy.etherapyproject.model.RateMusic;
import com.etherapy.etherapyproject.music.repository.MusicRepository;
import com.etherapy.etherapyproject.repository.RateMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RateMusicController {

    @Autowired
    private RateMusicRepository rateMusicRepository;

    @Autowired
    private MusicRepository musicRepository;

    @GetMapping("/music/{musicId}/rateMusic")
    public Page<RateMusic> getAllRateByMusicId(@PathVariable (value = "musicId") String musicId,
                                                    Pageable pageable) {
        return rateMusicRepository.findByMusicId(musicId, pageable);
    }

    @PostMapping("/music/{musicId}/rateMusic")
    public RateMusic createRate(@PathVariable (value = "musicId") String musicId,
                                 @Valid @RequestBody RateMusic rateMusic) {
        return musicRepository.findById(musicId).map(music -> {
            rateMusic.setMusic(music);
            return rateMusicRepository.save(rateMusic);
        }).orElseThrow(() -> new ResourceNotFoundException("MusicId " + musicId + " not found"));
    }

    @PutMapping("/music/{musicId}/rateMusic/{rateMusicId}")
    public RateMusic updateRate(@PathVariable (value = "musicId") String musicId,
                                 @PathVariable (value = "rateMusicId") String rateMusicId,
                                 @Valid @RequestBody RateMusic rateMusicRequest) {
        if(!musicRepository.existsById(musicId)) {
            throw new ResourceNotFoundException("MusicId " + musicId + " not found");
        }

        return rateMusicRepository.findById(rateMusicId).map(penilaianMusic -> {
            penilaianMusic.setRate(rateMusicRequest.getRate());
            return rateMusicRepository.save(penilaianMusic);
        }).orElseThrow(() -> new ResourceNotFoundException("RateId " + rateMusicId + "not found"));
    }

    @DeleteMapping("/music/{musicId}/rateMusic/{rateMusicId}")
    public ResponseEntity<?> deleteRate(@PathVariable (value = "musicId") String musicId,
                                           @PathVariable (value = "rateMusicId") String rateMusicId) {
        return rateMusicRepository.findByIdAndMusicId(rateMusicId, musicId).map(rateMusic ->  {
            rateMusicRepository.delete(rateMusic);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Penilaian  not found with id " + rateMusicId + " and postId " + musicId));
    }
}
