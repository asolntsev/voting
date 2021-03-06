package controllers;

import data.PartyVotes;
import data.PartyVotesDAO;
import data.PartyVotesDAOImpl;
import models.Constituency;
import models.EstonianUniverse;
import models.PartyResults;
import models.VotingResults;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Results extends Controller {
    static PartyVotesDAO partyVotes = new PartyVotesDAOImpl();

    public static void votingResults() {
        List<Constituency> constituencies = getConstituencies();
        VotingResults votingResults = computeMandates();
        render(Long.valueOf(0L), constituencies, votingResults);
    }

    public static void votingResultsPerConstituency(Long constituencyId) {
        if (constituencyId == 0) {
            votingResults();
            return;
        }

        List<Constituency> constituencies = getConstituencies();
        render(constituencyId, constituencies);
    }

    private static List<Constituency> getConstituencies() {
        List<Constituency> constituencies = Constituency.all().fetch();
        constituencies.add(0, getTotalConstituency());
        return constituencies;
    }

    static Constituency getTotalConstituency() {
        List<Constituency> all = Constituency.all().fetch();
        int totalPopulation = 0;
        for (Constituency constituency : all) {
            totalPopulation += constituency.getPopulation();
        }
        return new Constituency(0L, "EESTI VABARIIK kokku", totalPopulation);
    }

    public static VotingResults computeMandates() {
        List<PartyVotes> votes = partyVotes.list();
        int totalVotes = computeTotalVotes(votes); // TODO Can use partyVotes.gotTotalVotes();

        List<PartyResults> results = new ArrayList<PartyResults>(votes.size());
        for (PartyVotes pVotes : votes) {
            int mandates = EstonianUniverse.MANDATES * pVotes.getVotes() / totalVotes;
            double votesPercentage = 100d * pVotes.getVotes() / totalVotes;
            results.add(new PartyResults(pVotes.getParty(), mandates, pVotes.getVotes(), votesPercentage));
        }
        return new VotingResults(totalVotes, results);
    }

    private static int computeTotalVotes(List<PartyVotes> votes) {
        int totalVotes = 0;
        for (PartyVotes pVotes : votes) {
            totalVotes += pVotes.getVotes();
        }
        return totalVotes;
    }
}
