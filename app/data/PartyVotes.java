package data;

import models.Party;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "getPartyResults",
				query = "select new data.PartyVotes(party," +
                        "       (select count(*) from Vote vote where candidateId in (" +
                        "           select id from Candidate candidate where candidate.partyId = party.id)" +
                        "       )" +
                        ")" +
                        "  from Party party")
})
public class PartyVotes extends Model {
    Party party;
    int votes;

    public PartyVotes(Party party, Number votes) {
        this.party = party;
        this.votes = votes.intValue();
    }
}
