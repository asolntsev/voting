package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "participant")
public class Participant extends Model {
    Long partyId;
    String name;
    int registerNumber;
    String bornPlace;

    public Participant(Long partyId, String name, int registerNumber, String bornPlace) {
        this.partyId = partyId;
        this.name = name;
        this.registerNumber = registerNumber;
        this.bornPlace = bornPlace;
    }
}
