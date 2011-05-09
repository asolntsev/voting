package controllers;

import models.Constituency;
import play.mvc.Controller;

import java.util.List;

public class Results extends Controller {
    public static void list(long constituencyId) {
        List<Constituency> constituencies = Constituency.all().fetch();
        constituencies.add(0, getTotalConstituency());
        render(constituencyId, constituencies);
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
