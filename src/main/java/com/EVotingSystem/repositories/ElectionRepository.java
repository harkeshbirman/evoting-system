package com.EVotingSystem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EVotingSystem.entities.Election;

public interface ElectionRepository extends JpaRepository<Election, Long> {

	Optional<Election> findByName(String electionName);

}
