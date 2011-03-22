package controllers;

import models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.db.jpa.JPA;
import play.db.jpa.Model;
import play.mvc.Controller;

import java.util.Calendar;
import java.util.List;

public class Participation extends Controller {

    private static final int NUMBER_OF_VOTES = 100;
    private static final Logger log = LoggerFactory.getLogger(Participation.class);

    private static void fillData() {
        Party reformierakond = new Party("Reformierakond");
        Party keskerakond = new Party("Keskerakond");
        saveAll(reformierakond, keskerakond);

        new Participant(reformierakond.getId(), "Andrus Ansip", 507, "Tartu linn").save();
        new Participant(reformierakond.getId(), "Urmas Paet", 497, "Tallinn").save();
        new Participant(keskerakond.getId(), "Edgar Savisaar", 609, "Harjumaa").save();
        new Participant(keskerakond.getId(), "Mihhail Stalnuhhin", 664, "Tartu").save();

        Constituency[] constituencies = new Constituency[]{
                new Constituency("Haabersti , Kristiine, Põhja-Tallinn"),
                new Constituency("Kesklinn, Lasnamäe, Pirita"),
                new Constituency("Mustamäe, Nõmme"),
                new Constituency("Harju, Rapla"),
                new Constituency("Hiiu, Lääne, Saare"),
                new Constituency("Lääne-Viru"),
                new Constituency("Ida-Viru"),
                new Constituency("Ida-Viru"),
                new Constituency("Jõgeva, Tartu"),
                new Constituency("Tartu linn"),
                new Constituency("Võru, Valga, Põlva"),
                new Constituency("Pärnu")};
        saveAll(constituencies);
    }

    private static void fillRandomVotes() {
        long participants = Participant.count();
        long constituencies = Constituency.count();
        long start = System.currentTimeMillis();
        Calendar votingDate = getVotingDate();
        for (int i = 0; i < NUMBER_OF_VOTES; i++) {
            Calendar date = (Calendar) votingDate.clone();
            date.add(Calendar.DATE, -(int) (Math.random() * 6));
            Vote vote = new Vote(random(constituencies), random(participants), date.getTime());
            vote.save();
        }
        long end = System.currentTimeMillis();
        log.info("Voted in " + (end - start) + " ms.");
    }

    private static Calendar getVotingDate() {
        Calendar votingDate = Calendar.getInstance();
        votingDate.set(Calendar.YEAR, 2011);
        votingDate.set(Calendar.MONTH, 3);
        votingDate.set(Calendar.DATE, 11);
        return votingDate;
    }

    private static void saveAll(Model... objects) {
        for (Model object : objects) {
            object.save();
        }
    }

    private static long random(long max) {
        double result = Math.random() * max;
        return (long) result;
    }

    public static void list() {
        List<Object> parties = Party.all().fetch();

        if (parties.isEmpty()) {
            fillData();
            fillRandomVotes();
            JPA.em().flush();
            JPA.em().getTransaction().commit();

            parties = Party.all().fetch();
        }
        List<Object> participants = Participant.all().fetch();

        List<Object> votes = Vote.all().fetch();
        List<ParticipationResult> participation = ParticipationResult.em()
                .createNamedQuery("getParticipation").getResultList();

        render(parties, participants, participation);
    }
}