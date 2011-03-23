package data;

import models.Constituency;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "getParticipationByDate",
				query = "SELECT new data.ParticipationByDate(c," +
						" (select count(*) from Vote v where c.id = v.constituencyId and v.date <= :votingDate) as votes" +
						" ) FROM Constituency c"),
		@NamedQuery(name = "getTotalParticipationByDate",
				query = "select count(*) from Vote v where v.date <= :votingDate) as totalVotes")
})
public class ParticipationByDate extends Model {
	private Constituency constituency;
	private int votes;

	public ParticipationByDate(Constituency constituency, Number votes) {
		this.constituency = constituency;
		this.votes = votes.intValue();
	}

	public Constituency getConstituency() {
		return constituency;
	}

	public int getVotes() {
		return votes;
	}
}
