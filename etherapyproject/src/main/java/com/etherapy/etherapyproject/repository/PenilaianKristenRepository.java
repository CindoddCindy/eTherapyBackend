package com.etherapy.etherapyproject.repository;


import com.etherapy.etherapyproject.model.PenilaianKristen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PenilaianKristenRepository extends JpaRepository<PenilaianKristen, Long>{
    Page<PenilaianKristen> findByKristenId(Long kristenId, Pageable pageable);
    Optional<PenilaianKristen> findByIdAndKristenId(Long id, Long kristenId);
}
