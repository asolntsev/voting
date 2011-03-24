package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "county")
public class County extends Model {
	String name;
	int population;

	public County(Long id, String name, int population) {
		this.id = id;
		this.name = name;
		this.population = population;
	}

	public County(String name, int population) {
		this.name = name;
		this.population = population;
	}

	public String getName() {
		return name;
	}

	public int getPopulation() {
		return population;
	}
}
