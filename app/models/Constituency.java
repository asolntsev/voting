package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "constituency")
public class Constituency extends Model {
	String name;
	int population;

	public Constituency(String name, int population) {
		this.name = name;
		this.population = population;
	}

	public Constituency(Long id, String name, int population) {
		this(name, population);
		this.id = id;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
}
