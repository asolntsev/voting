package models;

import java.util.List;

public class VotingResults {
    private final int totalVotes;
    private final List<PartyResults> partyResults;

    public VotingResults(int totalVotes, List<PartyResults> partyResults) {
        this.totalVotes = totalVotes;
        this.partyResults = partyResults;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public List<PartyResults> getPartyResults() {
        return partyResults;
    }
}
