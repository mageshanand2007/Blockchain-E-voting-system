package com.evoting.service;

import com.evoting.model.Candidate;
import com.evoting.exception.InvalidCredentialsException;
import com.evoting.exception.DuplicateCandidateException;

// INTERFACE: defines a contract for admin-related operations
// This enables POLYMORPHISM - any class implementing this can be
// referenced as an AdminService
public interface AdminService {

    boolean login(String username, String password) throws InvalidCredentialsException;

    void addCandidate(Candidate candidate) throws DuplicateCandidateException;

    void viewCandidates();

    void removeCandidate(int candidateId);
}
