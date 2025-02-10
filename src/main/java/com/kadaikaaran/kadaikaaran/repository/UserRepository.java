package com.kadaikaaran.kadaikaaran.repository;

import com.kadaikaaran.kadaikaaran.dao.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findByEmailId(String emailId);
}
