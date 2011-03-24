package controllers;

import data.ParticipationResultDAO;
import models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.db.jpa.JPA;
import play.db.jpa.Model;
import play.mvc.Controller;

import java.util.Calendar;
import java.util.List;

import static models.EstonianUniverse.VOTING_LENGTH_DAYS;
import static models.EstonianUniverse.getVotingDate;

public class Participation extends Controller {

	private static final int NUMBER_OF_VOTES = 500000;
	private static final Logger log = LoggerFactory.getLogger(Participation.class);

	private static ParticipationResultDAO dao = new ParticipationResultDAO();

	private static void fillData() {
		Party reformierakond = new Party("Reformierakond");
		Party keskerakond = new Party("Keskerakond");
		saveAll(reformierakond, keskerakond);

		new Candidate(reformierakond.getId(), "Andrus Ansip", 507, "Tartu linn").save();
		new Candidate(reformierakond.getId(), "Urmas Paet", 497, "Tallinn").save();
		new Candidate(keskerakond.getId(), "Edgar Savisaar", 609, "Harjumaa").save();
		new Candidate(keskerakond.getId(), "Mihhail Stalnuhhin", 664, "Tartu").save();

		Constituency[] constituencies = new Constituency[]{
				new Constituency("Haabersti , Kristiine, Põhja-Tallinn", 76189 / 1),
				new Constituency("Kesklinn, Lasnamäe, Pirita", 104478 / 1),
				new Constituency("Mustamäe, Nõmme", 69816 / 1),
				new Constituency("Harju, Rapla", 130270 / 1),
				new Constituency("Hiiu, Lääne, Saare", 58583 / 1),
				new Constituency("Lääne-Viru", 48875 / 1),
				new Constituency("Ida-Viru", 67604 / 1),
				new Constituency("Järva, Viljandi", 70092 / 1),
				new Constituency("Jõgeva, Tartu", 67504 / 1),
				new Constituency("Tartu linn", 70968 / 1),
				new Constituency("Võru, Valga, Põlva", 79857 / 1),
				new Constituency("Pärnu", 69110 / 1)};
		saveAll(constituencies);
	}

	private static void fillRandomVotes() {
		long candidates = Candidate.count();
		long counties = County.count();
		long constituencies = Constituency.count();
		long start = System.currentTimeMillis();
		Calendar votingDate = getVotingDate();
		for (int i = 0; i < NUMBER_OF_VOTES; i++) {
			Calendar date = (Calendar) votingDate.clone();
			date.add(Calendar.DATE, -(int) (Math.random() * (VOTING_LENGTH_DAYS - 1)));
			date.add(Calendar.HOUR_OF_DAY, -(int) (Math.random() * 12));
			Vote vote = new Vote(random(counties), random(constituencies), random(candidates), date.getTime());
			vote.save();
			if (i % 100 == 0) {
				JPA.em().getTransaction().commit();
				JPA.em().getTransaction().begin();
			}
		}
		long end = System.currentTimeMillis();
		log.info("Voted in " + (end - start) + " ms.");
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
/*
		List<Object> parties = Party.all().fetch();

		if (parties.isEmpty()) {
			fillData();
			fillRandomVotes();
			JPA.em().getTransaction().commit();
		}
*/
		List<ParticipationResult> participation = dao.list();
		render(participation);
	}
}