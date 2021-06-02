package com.etherapy.etherapyproject.controller;

import com.etherapy.etherapyproject.exception.ResourceNotFoundException;
import com.etherapy.etherapyproject.model.Admin;
import com.etherapy.etherapyproject.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/admin")
    public Page<Admin> getAllAdmin(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }

    @PostMapping("admin")
    public Admin createAdmin(@Valid @RequestBody Admin admin) {
        return adminRepository.save(admin);
    }

    @PutMapping("/admin/{adminId}")
    public Admin updateAdmin(@PathVariable Long adminId, @Valid @RequestBody Admin adminRequest) {
        return adminRepository.findById(adminId).map(admin -> {
            admin.setNama(adminRequest.getNama());
            admin.setEmail(adminRequest.getEmail());
            admin.setPhone(adminRequest.getPhone());
            return adminRepository.save(admin);
        }).orElseThrow(() -> new ResourceNotFoundException("adminId " + adminId + " not found"));
    }


    @DeleteMapping("/admin/{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId) {
        return adminRepository.findById(adminId).map(admin -> {
            adminRepository.delete(admin);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("adminId " + adminId + " not found"));
    }

}
