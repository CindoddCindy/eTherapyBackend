package com.etherapy.etherapyproject.controller;

import com.etherapy.etherapyproject.exception.ResourceNotFoundException;
import com.etherapy.etherapyproject.model.Kristen;
import com.etherapy.etherapyproject.repository.KristenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class KristenController {
    @Autowired
    private KristenRepository kristenRepository;

    @GetMapping("/kristen")
    public Page<Kristen> getAllKristen(Pageable pageable) {
        return kristenRepository.findAll(pageable);
    }

    @PostMapping("/kristen")
    public Kristen createKristen(@Valid @RequestBody Kristen kristen) {
        return kristenRepository.save(kristen);
    }

    @PutMapping("/kristen/{kristenId}")
    public Kristen updateKristen(@PathVariable Long kristenId, @Valid @RequestBody Kristen kristenRequest) {
        return kristenRepository.findById(kristenId).map(kristen-> {
            kristen.setTitle(kristenRequest.getTitle());
            kristen.setDescription(kristenRequest.getDescription());
            kristen.setContent(kristenRequest.getContent());
            return kristenRepository.save(kristen);
        }).orElseThrow(() -> new ResourceNotFoundException("KristenId " + kristenId + " not found"));
    }


    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deleteKristen(@PathVariable Long kristenId) {
        return kristenRepository.findById(kristenId).map(kristen -> {
            kristenRepository.delete(kristen);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("KristenId " + kristenId+ " not found"));
    }
}
