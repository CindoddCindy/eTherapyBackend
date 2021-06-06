package com.etherapy.etherapyproject.controller;


import com.etherapy.etherapyproject.exception.ResourceNotFoundException;
import com.etherapy.etherapyproject.model.RateMuslim;
import com.etherapy.etherapyproject.repository.MuslimRepository;
import com.etherapy.etherapyproject.repository.RateMuslimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class IslamPenilaianController {

    @Autowired
    private RateMuslimRepository rateMuslimRepository;

    @Autowired
    private MuslimRepository muslimRepository;

    @GetMapping("/muslim/{muslimId}/rateMuslim")
    public Page<RateMuslim> getAllRateByMuslimId(@PathVariable (value = "muslimId") Long muslimId,
                                                     Pageable pageable) {
        return rateMuslimRepository.findByMuslimId(muslimId, pageable);
    }

    @PostMapping("/muslim/{muslimId}/rateMuslim")
    public RateMuslim createRate(@PathVariable (value = "muslimId") Long muslimId,
                                 @Valid @RequestBody RateMuslim rateMuslim) {
        return muslimRepository.findById(muslimId).map(muslim -> {
            rateMuslim.setMuslim(muslim);
            return rateMuslimRepository.save(rateMuslim);
        }).orElseThrow(() -> new ResourceNotFoundException("MuslimId " + muslimId + " not found"));
    }

    @PutMapping("/muslim/{muslimId}/rateMuslim/{rateMuslimId}")
    public RateMuslim updateRateMuslim(@PathVariable (value = "muslimId") Long muslimId,
                                 @PathVariable (value = "rateMuslimId") Long rateMuslimId,
                                 @Valid @RequestBody RateMuslim rateMuslimRequest) {
        if(!muslimRepository.existsById(muslimId)) {
            throw new ResourceNotFoundException("MuslimId " + muslimId + " not found");
        }

        return rateMuslimRepository.findById(rateMuslimId).map(rateMuslim -> {
            rateMuslim.setRateM(rateMuslimRequest.getRateM());
            return rateMuslimRepository.save(rateMuslim);
        }).orElseThrow(() -> new ResourceNotFoundException("Rate" + rateMuslimId + "not found"));
    }

    @DeleteMapping("/muslim/{muslimId}/rateMuslim/{rateMuslimId}")
    public ResponseEntity<?> deleteRateMuslim(@PathVariable (value = "muslimId") Long muslimId,
                                           @PathVariable (value = "rateMuslimId") Long rateMuslimId) {
        return rateMuslimRepository.findByIdAndMuslimId(rateMuslimId, muslimId).map(rateMuslim -> {
            rateMuslimRepository.delete(rateMuslim);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Rate Muslim not found with id " + rateMuslimId + " and postId " + muslimId));
    }
}
