package com.etherapy.etherapyproject.repository;

import com.etherapy.etherapyproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
