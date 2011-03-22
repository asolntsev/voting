package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "vote")
public class Vote extends Model {
    Long constituencyId;
    Long participantId;
    Date date;

    public Vote(Long constituencyId, Long participantId, Date date) {
        this.constituencyId = constituencyId;
        this.participantId = participantId;
        this.date = date;
    }
}
