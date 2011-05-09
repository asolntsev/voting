package controllers;

import data.PartyVotes;
import data.PartyVotesDAO;
import data.PartyVotesDAOImpl;
import models.Constituency;
import models.PartyResults;
import play.mvc.Controller;

import java.util.Collections;
import java.util.List;

public class Results extends Controller {
    static PartyVotesDAO partyVotes = new PartyVotesDAOImpl();

    public static void votingResults() {
        List<Constituency> constituencies = getConstituencies();
        List<PartyVotes> results = partyVotes.list();
        render(Long.valueOf(0L), constituencies, results);
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

    public static List<PartyResults> computeMandates() {
        return Collections.emptyList();
    }
}
