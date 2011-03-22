package models;

import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name = "party")
public class Party extends Model {
    private String name;

    public Party(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Party{" +
                "name='" + name + '\'' +
                '}';
    }
}
