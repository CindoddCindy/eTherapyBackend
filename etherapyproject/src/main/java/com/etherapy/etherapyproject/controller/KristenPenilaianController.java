package com.etherapy.etherapyproject.controller;


import com.etherapy.etherapyproject.exception.ResourceNotFoundException;
import com.etherapy.etherapyproject.model.PenilaianKristen;
import com.etherapy.etherapyproject.repository.KristenRepository;
import com.etherapy.etherapyproject.repository.PenilaianKristenRepository;
import com.etherapy.etherapyproject.repository.PenilaianMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class KristenPenilaianController {

    @Autowired
    private PenilaianKristenRepository penilaianKristenRepository;

    @Autowired
    private KristenRepository kristenRepository;

    @GetMapping("/kristen/{kristenId}/penilaiankristen")
    public Page<PenilaianKristen> getAllPenilaianByKristenId(@PathVariable (value = "kristenId") Long kristenId,
                                                         Pageable pageable) {
        return penilaianKristenRepository.findByKristenId(kristenId, pageable);
    }

    @PostMapping("/kristen/{kristenId}/penilaiankristen")
    public PenilaianKristen createPenilaianKristen(@PathVariable (value = "kristenId") Long kristenId,
                                 @Valid @RequestBody PenilaianKristen penilaianKristen) {
        return kristenRepository.findById(kristenId).map(kristen -> {
            penilaianKristen.setKristen(kristen);
            return penilaianKristenRepository.save(penilaianKristen);
        }).orElseThrow(() -> new ResourceNotFoundException("PenilaianId " + kristenId + " not found"));
    }

    @PutMapping("/kristen/{kristenId}/penilaiankristen/{penilaianKristenId}")
    public PenilaianKristen updatePenilaian(@PathVariable (value = "kristenId") Long kristenId,
                                 @PathVariable (value = "penilaianKristenId") Long penilaianKristenId,
                                 @Valid @RequestBody PenilaianKristen penilaianKristenRequest) {
        if(!kristenRepository.existsById(kristenId)) {
            throw new ResourceNotFoundException("KristenId " + kristenId + " not found");
        }

        return penilaianKristenRepository.findById(penilaianKristenId).map(penilaianKristen -> {
            penilaianKristen.setRateK(penilaianKristenRequest.getRateK());
            return penilaianKristenRepository.save(penilaianKristen);
        }).orElseThrow(() -> new ResourceNotFoundException("Penilaian Kristen Id " + penilaianKristenId + "not found"));
    }

    @DeleteMapping("/kristen/{kristenId}/penilaiankristen/{penilaianKristenId}")
    public ResponseEntity<?> deletePenilaian(@PathVariable (value = "kristenId") Long kristenId,
                                           @PathVariable (value = "penilaianKristenId") Long penilaianKristenId) {
        return penilaianKristenRepository.findByIdAndKristenId(penilaianKristenId, kristenId).map(penilaianKristen -> {
            penilaianKristenRepository.delete(penilaianKristen);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Penilaian not found with id " + penilaianKristenId + " and kristenId " + kristenId));
    }
}
