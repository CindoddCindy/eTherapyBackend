package com.etherapy.etherapyproject.controller;


import com.etherapy.etherapyproject.exception.ResourceNotFoundException;
import com.etherapy.etherapyproject.model.PenilaianMuslim;
import com.etherapy.etherapyproject.repository.MuslimRepository;
import com.etherapy.etherapyproject.repository.PenilaianMuslimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class IslamPenilaianController {

    @Autowired
    private PenilaianMuslimRepository penilaianMuslimRepository;

    @Autowired
    private MuslimRepository muslimRepository;

    @GetMapping("/muslim/{muslimId}/penilaianmuslim")
    public Page<PenilaianMuslim> getAllPenilainByMuslimId(@PathVariable (value = "muslimId") Long muslimId,
                                                        Pageable pageable) {
        return penilaianMuslimRepository.findByMuslimId(muslimId, pageable);
    }

    @PostMapping("/muslim/{muslimId}/penilaianmuslim")
    public PenilaianMuslim createPenilaian(@PathVariable (value = "muslimId") Long muslimId,
                                 @Valid @RequestBody PenilaianMuslim penilaianMuslim) {
        return muslimRepository.findById(muslimId).map(muslim -> {
            penilaianMuslim.setMuslim(muslim);
            return penilaianMuslimRepository.save(penilaianMuslim);
        }).orElseThrow(() -> new ResourceNotFoundException("MuslimId " + muslimId + " not found"));
    }

    @PutMapping("/muslim/{muslimId}/penilaianmuslim/{penilaianMuslimId}")
    public PenilaianMuslim updatePenilaianMuslim(@PathVariable (value = "muslimId") Long muslimId,
                                 @PathVariable (value = "penilaianMuslimId") Long penilaianMuslimId,
                                 @Valid @RequestBody PenilaianMuslim penilaianMuslimRequest) {
        if(!muslimRepository.existsById(muslimId)) {
            throw new ResourceNotFoundException("MuslimId " + muslimId + " not found");
        }

        return penilaianMuslimRepository.findById(penilaianMuslimId).map(penilaianMuslim -> {
            penilaianMuslim.setRateM(penilaianMuslim.getRateM());
            return penilaianMuslimRepository.save(penilaianMuslim);
        }).orElseThrow(() -> new ResourceNotFoundException("Penilaian" + penilaianMuslimId + "not found"));
    }

    @DeleteMapping("/muslim/{muslimId}/penilaianmuslim/{penilaianMuslimId}")
    public ResponseEntity<?> deletePenilaianMuslim(@PathVariable (value = "muslimId") Long muslimId,
                                           @PathVariable (value = "penilaianMuslimId") Long penilaianMuslimId) {
        return penilaianMuslimRepository.findByIdAndMuslimId(penilaianMuslimId, muslimId).map(penilaianMuslim -> {
            penilaianMuslimRepository.delete(penilaianMuslim);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Penilaian Muslim not found with id " + penilaianMuslimId + " and postId " + muslimId));
    }
}
