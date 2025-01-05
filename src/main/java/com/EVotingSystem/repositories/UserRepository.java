package com.EVotingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.EVotingSystem.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
