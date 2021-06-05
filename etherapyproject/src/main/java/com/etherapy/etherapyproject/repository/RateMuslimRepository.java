package com.etherapy.etherapyproject.repository;



import com.etherapy.etherapyproject.model.RateMuslim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateMuslimRepository extends JpaRepository<RateMuslim, Long> {
    Page<RateMuslim> findByMuslimId(Long muslimId, Pageable pageable);
    Optional<RateMuslim> findByIdAndMuslimId(Long id, Long muslimId);
}
