package com.EVotingSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EVotingSystem.entities.Election;
import com.EVotingSystem.entities.ElectionChoice;
import com.EVotingSystem.entities.User;
import com.EVotingSystem.entities.Vote;
import com.EVotingSystem.repositories.ElectionChoiceRepository;
import com.EVotingSystem.repositories.ElectionRepository;
import com.EVotingSystem.repositories.UserRepository;
import com.EVotingSystem.repositories.VoteRepository;

@Service
public class EVotingService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ElectionRepository electionRepository;

    @Autowired
    ElectionChoiceRepository electionChoiceRepository;

    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addVote(Long userId, Long electionId, Long electionChoiceId) {
        User userDetails = userRepository.findById(userId).get();
        Election electionDetails = electionRepository.findById(electionId).get();
        ElectionChoice electionChoiceDetails = electionChoiceRepository.findById(electionChoiceId).get();

        if(!AlreadyGivenVote(userId,electionId)){
            Vote vote = new Vote();
            userRepository.save(userDetails);
            vote.setUser(userDetails);
            vote.setElection(electionDetails);
            vote.setElectionChoice(electionChoiceDetails);
            voteRepository.save(vote);
        } else throw new RuntimeException("You have already given your vote");
    }

    public void addElection(Election election) {
        electionRepository.save(election);
    }

    public boolean AlreadyGivenVote(Long userId, Long electionId) {
        return voteRepository.existsByUserIdAndElectionId(userId, electionId);
    }

    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }

    public void addElectionChoice(ElectionChoice electionChoice) {
        electionChoiceRepository.save(electionChoice);
    }

    public List<ElectionChoice> getAllElectionChoices() {
        return electionChoiceRepository.findAll();
    }

    public Election findElectionByName(String electionName) {
        return electionRepository.findByName(electionName).orElseThrow();
    }

    public long countTotalVotes() {
        return voteRepository.countTotalVotes();
    }

    public long countVotesByElectionName(String electionName) {
        return voteRepository.countVotesByElectionName(electionName);
    }

    public long choicesByElection(Long electionId) {

        return electionChoiceRepository.countByElection(electionId);
    }

	public ElectionChoice findElectionWinner(String electionName) {
        Election election = electionRepository.findByName(electionName).get();
		return electionChoiceRepository.findElectionChoiceWithMaxVotes(election.getId());
	}
}
