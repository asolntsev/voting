package controllers;

import models.Constituency;
import play.mvc.Controller;

import java.util.List;

public class Results extends Controller {
    public static void votingResults() {
        List<Constituency> constituencies = getConstituencies();
        render(Long.valueOf(0L), constituencies);
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
}
