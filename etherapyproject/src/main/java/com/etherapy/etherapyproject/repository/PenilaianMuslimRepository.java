package com.etherapy.etherapyproject.repository;


import com.etherapy.etherapyproject.model.PenilaianMuslim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PenilaianMuslimRepository extends JpaRepository<PenilaianMuslim, Long> {
    Page<PenilaianMuslim> findByMuslimId(Long muslimId, Pageable pageable);
    Optional<PenilaianMuslim> findByIdAndMuslimId(Long id, Long muslimId);
}
