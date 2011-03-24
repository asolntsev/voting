package models;

import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "candidate")
public class Candidate extends Model {

	Long partyId;
	String name;
	int registerNumber;
	String bornPlace;

	public Candidate(Long partyId, String name, int registerNumber, String bornPlace) {
		this.partyId = partyId;
		this.name = name;
		this.registerNumber = registerNumber;
		this.bornPlace = bornPlace;
	}
}
