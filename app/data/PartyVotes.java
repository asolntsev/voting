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
                        "  from Party party"),
        @NamedQuery(name = "getTotalVotes",
				query = "select count(*) from Vote vote")
})
public class PartyVotes extends Model {
    private final Party party;
    private final int votes;

    public PartyVotes(Party party, Number votes) {
        this.party = party;
        this.votes = votes.intValue();
    }

    public Party getParty() {
        return party;
    }

    public int getVotes() {
        return votes;
    }
}
