
package com.etherapy.etherapyproject.controller;

import com.etherapy.etherapyproject.exception.ResourceNotFoundException;
import com.etherapy.etherapyproject.model.RateMusic;
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

    @GetMapping("/rateMusic")
    public Page<RateMusic> getAllMusic(Pageable pageable) {
        return rateMusicRepository.findAll(pageable);
    }

    @PostMapping("/rateMusic")
    public RateMusic createPost(@Valid @RequestBody RateMusic rateMusic) {
        return rateMusicRepository.save(rateMusic);
    }

    @PutMapping("/rateMusic/(rateMusicId}")
    public RateMusic updateMusic(@PathVariable Long rateMusicId, @Valid @RequestBody RateMusic rateMusicRequest) {
        return rateMusicRepository.findById(rateMusicId).map(rateMusic -> {
            rateMusic.setMusicName(rateMusicRequest.getMusicName());
            rateMusic.setRateMusic(rateMusicRequest.getRateMusic());
            return rateMusicRepository.save(rateMusic);
        }).orElseThrow(() -> new ResourceNotFoundException("rateMusicId " + rateMusicId+ " not found"));
    }


    @DeleteMapping("/rateMusic/{rateMusicId}")
    public ResponseEntity<?> deletePost(@PathVariable Long rateMusicId) {
        return rateMusicRepository.findById(rateMusicId).map(rateMusic -> {
            rateMusicRepository.delete(rateMusic);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("rateMusicId " + rateMusicId + " not found"));
    }


}


