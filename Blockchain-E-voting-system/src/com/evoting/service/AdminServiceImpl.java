package com.evoting.service;

import java.util.ArrayList;

import com.evoting.model.Admin;
import com.evoting.model.Candidate;
import com.evoting.exception.InvalidCredentialsException;
import com.evoting.exception.DuplicateCandidateException;

// POLYMORPHISM: This class IMPLEMENTS the AdminService interface.
// Other code can refer to an AdminServiceImpl object using the
// AdminService interface type (see Main.java).
public class AdminServiceImpl implements AdminService {

    private Admin admin; // the single registered admin for this stage
    // COLLECTIONS: ArrayList used to temporarily store candidates
    private ArrayList<Candidate> candidateList;

    public AdminServiceImpl(Admin admin) {
        this.admin = admin;
        this.candidateList = new ArrayList<>();
    }

    // EXCEPTION HANDLING: throws a custom checked exception on bad login
    @Override
    public boolean login(String username, String password) throws InvalidCredentialsException {
        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            return true;
        } else {
            throw new InvalidCredentialsException("Invalid username or password!");
        }
    }

    // EXCEPTION HANDLING: throws a custom checked exception on duplicate ID
    @Override
    public void addCandidate(Candidate candidate) throws DuplicateCandidateException {
        for (Candidate c : candidateList) {
            if (c.getCandidateId() == candidate.getCandidateId()) {
                throw new DuplicateCandidateException(
                        "Candidate with ID " + candidate.getCandidateId() + " already exists!");
            }
        }
        candidateList.add(candidate);
    }

    @Override
    public void viewCandidates() {
        if (candidateList.isEmpty()) {
            System.out.println("No candidates added yet.");
            return;
        }

        System.out.println("\n---------- CANDIDATE LIST ----------");
        System.out.printf("%-10s%-15s%-15s%n", "ID", "Name", "Party");
        for (Candidate c : candidateList) {
            System.out.printf("%-10d%-15s%-15s%n",
                    c.getCandidateId(), c.getCandidateName(), c.getPartyName());
        }
    }

    @Override
    public void removeCandidate(int candidateId) {
        Candidate toRemove = null;
        for (Candidate c : candidateList) {
            if (c.getCandidateId() == candidateId) {
                toRemove = c;
                break;
            }
        }

        if (toRemove != null) {
            candidateList.remove(toRemove);
            System.out.println("Candidate removed successfully!");
        } else {
            System.out.println("Candidate ID not found!");
        }
    }
}
