package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "getParticipation",
				query = "SELECT new data.ParticipationResult(c,\n" +
						" (select count(*) from vote v where c.id = v.constituencyId and v.date <= :votingDate - 6),\n" +
						" (select count(*) from vote v where c.id = v.constituencyId and v.date <= :votingDate - 5),\n" +
						" (select count(*) from vote v where c.id = v.constituencyId and v.date <= :votingDate - 4),\n" +
						" (select count(*) from vote v where c.id = v.constituencyId and v.date <= :votingDate + 0.5),\n" +
						" (select count(*) from vote v where c.id = v.constituencyId and v.date <= :votingDate + 0.6),\n" +
						" (select count(*) from vote v where c.id = v.constituencyId and v.date <= :votingDate + 0.8),\n" +
						" (select count(*) from vote v where c.id = v.constituencyId)\n" +
						" ) FROM constituency c")
})
public class ParticipationResult extends Model {
	private Constituency constituency;
	private int votes6DaysBefore;
	private int votes5DaysBefore;
	private int votes4DaysBefore;
	private int votesAt12OClock;
	private int votesAt16OClock;
	private int votesAt20OClock;
	private int totalVotes;

	public ParticipationResult(Constituency constituency, int votes6DaysBefore, int votes5DaysBefore, int votes4DaysBefore, int votesAt12OClock, int votesAt16OClock, int votesAt20OClock, int totalVotes) {
		this.constituency = constituency;
		this.votes6DaysBefore = votes6DaysBefore;
		this.votes5DaysBefore = votes5DaysBefore;
		this.votes4DaysBefore = votes4DaysBefore;
		this.votesAt12OClock = votesAt12OClock;
		this.votesAt16OClock = votesAt16OClock;
		this.votesAt20OClock = votesAt20OClock;
		this.totalVotes = totalVotes;
	}

	public Constituency getConstituency() {
		return constituency;
	}

	public int getVotes6DaysBefore() {
		return votes6DaysBefore;
	}

	public int getVotes5DaysBefore() {
		return votes5DaysBefore;
	}

	public int getVotes4DaysBefore() {
		return votes4DaysBefore;
	}

	public int getVotesAt12OClock() {
		return votesAt12OClock;
	}

	public int getVotesAt16OClock() {
		return votesAt16OClock;
	}

	public int getVotesAt20OClock() {
		return votesAt20OClock;
	}

	public int getTotalVotes() {
		return totalVotes;
	}

	public float[] getParticipationPerDay() {
		return new float[]{
				votes6DaysBefore / constituency.getPopulation(),
				votes5DaysBefore / constituency.getPopulation(),
				votes4DaysBefore / constituency.getPopulation(),
				votesAt12OClock / constituency.getPopulation(),
				votesAt16OClock / constituency.getPopulation(),
				votesAt20OClock / constituency.getPopulation(),
				totalVotes / constituency.getPopulation()
		};
	}
}
