package models;

import org.hibernate.annotations.Index;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "vote")
public class Vote extends Model {
	Long countyId;
	Long constituencyId;
	Long candidateId;
	Date date;

	public Vote(Long countyId, Long constituencyId, Long candidateId, Date date) {
		this.countyId = countyId;
		this.constituencyId = constituencyId;
		this.candidateId = candidateId;
		this.date = date;
	}
}
