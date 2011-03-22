package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.*;

public class ParticipationResult {
	Constituency constituency;
	Map<Integer, Integer> votesPerDay;

	public ParticipationResult(Constituency constituency) {
		this.constituency = constituency;
		this.votesPerDay = new TreeMap<Integer,Integer>();
	}

	public Constituency getConstituency() {
		return constituency;
	}

	public void setDayVotes(int dayOfYear, int votes) {
		votesPerDay.put(dayOfYear, votes);
	}

	public void addDayVotes(int dayOfYear, int votes) {
		int oldValue = votesPerDay.containsKey(dayOfYear) ? votesPerDay.get(dayOfYear) : 0;
		votesPerDay.put(dayOfYear, oldValue + votes);
	}

	public Map<Integer, Integer> getVotesPerDay() {
		return votesPerDay;
	}

	public float[] getParticipationPerDay() {
		Collection<Integer> votes = votesPerDay.values();
		float[] participationPerDay = new float[votes.size()];
		int i=0;
		for (Integer votesPerDay : votes) {
			participationPerDay[i++] = 100f * votesPerDay / constituency.getPopulation();
		}
		return participationPerDay;
	}

	public float getTotalParticipation() {
		float totalParticipation = 0;
		for (Float participationPerDay : getParticipationPerDay()) {
			totalParticipation += participationPerDay;
		}
		return totalParticipation;
	}
}
