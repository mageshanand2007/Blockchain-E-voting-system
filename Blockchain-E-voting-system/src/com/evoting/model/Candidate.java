package com.evoting.model;

// ENCAPSULATION: all fields are private and accessed via getters/setters
public class Candidate {

    private int candidateId;
    private String candidateName;
    private String partyName;
    private int voteCount;

    // Constructor
    public Candidate(int candidateId, String candidateName, String partyName) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.partyName = partyName;
        this.voteCount = 0; // votes start at 0; counting logic comes in a later stage
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
