package com.etherapy.etherapyproject.controller;


import com.etherapy.etherapyproject.exception.ResourceNotFoundException;
import com.etherapy.etherapyproject.model.RateKristen;
import com.etherapy.etherapyproject.repository.KristenRepository;
import com.etherapy.etherapyproject.repository.RateKristenRepository;
import com.etherapy.etherapyproject.repository.RateMuslimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class KristenPenilaianController {

    @Autowired
    private RateKristenRepository rateKristenRepository;

    @Autowired
    private KristenRepository kristenRepository;

    @GetMapping("/kristen/{kristenId}/rateKristen")
    public Page<RateKristen> getAllRateByKristenId(@PathVariable (value = "kristenId") Long kristenId,
                                                        Pageable pageable) {
        return rateKristenRepository.findByKristenId(kristenId, pageable);
    }

    @PostMapping("/kristen/{kristenId}/rateKristen")
    public RateKristen createRateKristen(@PathVariable (value = "kristenId") Long kristenId,
                                 @Valid @RequestBody RateKristen rateKristen) {
        return kristenRepository.findById(kristenId).map(kristen -> {
            rateKristen.setKristen(kristen);
            return rateKristenRepository.save(rateKristen);
        }).orElseThrow(() -> new ResourceNotFoundException("kristenId " + kristenId + " not found"));
    }

    @PutMapping("/kristen/{kristenId}/rateKristen/{rateKristenId}")
    public RateKristen updateRate(@PathVariable (value = "kristenId") Long kristenId,
                                 @PathVariable (value = "rateKristenId") Long rateKristenId,
                                 @Valid @RequestBody RateKristen rateKristenRequest) {
        if(!kristenRepository.existsById(kristenId)) {
            throw new ResourceNotFoundException("KristenId " + kristenId + " not found");
        }

        return rateKristenRepository.findById(rateKristenId).map(rateKristen -> {
            rateKristen.setRateK(rateKristenRequest.getRateK());
            return rateKristenRepository.save(rateKristen);
        }).orElseThrow(() -> new ResourceNotFoundException("Rate Kristen Id " + rateKristenId + "not found"));
    }

    @DeleteMapping("/kristen/{kristenId}/rateKristen/{rateKristenId}")
    public ResponseEntity<?> deletePenilaian(@PathVariable (value = "kristenId") Long kristenId,
                                           @PathVariable (value = "rateKristenId") Long rateKristenId) {
        return rateKristenRepository.findByIdAndKristenId(rateKristenId, kristenId).map(rateKristen -> {
            rateKristenRepository.delete(rateKristen);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Rate not found with id " + rateKristenId + " and kristenId " + kristenId));
    }
}
