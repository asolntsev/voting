package models;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "constituency")
public class Constituency extends Model {
    String name;

    public Constituency(String name) {
        this.name = name;
    }
}
