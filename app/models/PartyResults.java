package models;

public class PartyResults {
    private final Party party;
    private final int mandates;
    private final int votes;
    private final double votesPercentage;

    public PartyResults(Party party, int mandates, int votes, double votesPercentage) {
        this.party = party;
        this.mandates = mandates;
        this.votes = votes;
        this.votesPercentage = votesPercentage;
    }

    public Party getParty() {
        return party;
    }

    public int getMandates() {
        return mandates;
    }

    public int getVotes() {
        return votes;
    }

    public double getVotesPercentage() {
        return votesPercentage;
    }
}
