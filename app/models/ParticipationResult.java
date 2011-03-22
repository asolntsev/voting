package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "getParticipation",
                query = "SELECT new models.ParticipationResult(c, count(*)) FROM Constituency c, Vote v WHERE c.id = v.constituencyId GROUP BY c.id")
})
public class ParticipationResult extends Model {
    Constituency constituency;
    //float[] votesPerDay;
    float totalVotes;

    public ParticipationResult(Constituency constituency, Number totalVotes) {
        this.constituency = constituency;
        this.totalVotes = totalVotes.floatValue();
    }
}
