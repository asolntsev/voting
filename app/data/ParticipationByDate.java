package data;

import models.County;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "getParticipationByDate",
				query = "SELECT new data.ParticipationByDate(c," +
						" (select count(*) from Vote v where c.id = v.countyId and v.date <= :votingDate) as votes" +
						" ) FROM County c"),
		@NamedQuery(name = "getTotalParticipationByDate",
				query = "select count(*) from Vote v where v.date <= :votingDate) as totalVotes")
})
public class ParticipationByDate extends Model {
	private County county;
	private int votes;

	public ParticipationByDate(County county, Number votes) {
		this.county = county;
		this.votes = votes.intValue();
	}

	public County getCounty() {
		return county;
	}

	public int getVotes() {
		return votes;
	}
}
