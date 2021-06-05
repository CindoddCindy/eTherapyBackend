package com.etherapy.etherapyproject.repository;



import com.etherapy.etherapyproject.model.RateKristen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateKristenRepository extends JpaRepository<RateKristen, Long>{
    Page<RateKristen> findByKristenId(Long kristenId, Pageable pageable);
    Optional<RateKristen> findByIdAndKristenId(Long id, Long kristenId);
}
