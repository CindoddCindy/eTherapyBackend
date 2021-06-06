package com.etherapy.etherapyproject.repository;


import com.etherapy.etherapyproject.model.RateMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RateMusicRepository extends JpaRepository<RateMusic, Long> {

}
