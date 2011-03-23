package data;

import models.Constituency;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "getParticipationPerDay",
				query = "SELECT new data.ParticipationDayResult(c, count(*), extract(doy from v.date)) " +
						" FROM Constituency c, Vote v" +
						" WHERE c.id = v.constituencyId" +
						" GROUP BY c.id, extract(doy from v.date)" +
						" ORDER BY c.id, extract(doy from v.date)")
})
public class ParticipationDayResult extends Model {
	private Constituency constituency;
	private int dayVotes;
	private int dayOfYear;

	public ParticipationDayResult(Constituency constituency, Number dayVotes, int dayOfYear) {
		this.constituency = constituency;
		this.dayVotes = dayVotes.intValue();
		this.dayOfYear = dayOfYear;
	}

	public Constituency getConstituency() {
		return constituency;
	}

	public int getDayOfYear() {
		return dayOfYear;
	}

	public int getDayVotes() {
		return dayVotes;
	}
}
