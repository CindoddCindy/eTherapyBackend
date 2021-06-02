package com.etherapy.etherapyproject.controller;

import com.etherapy.etherapyproject.exception.ResourceNotFoundException;
import com.etherapy.etherapyproject.model.Kristen;
import com.etherapy.etherapyproject.model.Muslim;
import com.etherapy.etherapyproject.repository.KristenRepository;
import com.etherapy.etherapyproject.repository.MuslimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MuslimController {

    @Autowired
    private MuslimRepository muslimRepository;

    @GetMapping("/muslim")
    public Page<Muslim> getAllMuslim(Pageable pageable) {
        return muslimRepository.findAll(pageable);
    }

    @PostMapping("/muslim")
    public Muslim createMuslim(@Valid @RequestBody Muslim muslim) {
        return muslimRepository.save(muslim);
    }

    @PutMapping("/muslim/{muslimId}")
    public Muslim updateMuslim(@PathVariable Long muslimId, @Valid @RequestBody Muslim muslimRequest) {
        return muslimRepository.findById(muslimId).map(muslim-> {
            muslim.setTitle(muslimRequest.getTitle());
            muslim.setDescription(muslimRequest.getDescription());
            muslim.setContent(muslimRequest.getContent());
            return muslimRepository.save(muslim);
        }).orElseThrow(() -> new ResourceNotFoundException("MuslimId " + muslimId + " not found"));
    }


    @DeleteMapping("/muslim/{muslimId}")
    public ResponseEntity<?> deleteMuslim(@PathVariable Long muslimId) {
        return muslimRepository.findById(muslimId).map(muslim-> {
            muslimRepository.delete(muslim);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("MuslimId " + muslimId+ " not found"));
    }
}
