package com.EVotingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.EVotingSystem.entities.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

	@Query(value = "SELECT COUNT(*) FROM vote", nativeQuery = true)
	Long countTotalVotes();

	@Query("SELECT COUNT(v) FROM Vote v WHERE v.election.name = :election")
	Long countVotesByElectionName(@Param("election") String electionName);

	boolean existsByUserIdAndElectionId(Long userId, Long electionId);

}
