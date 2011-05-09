package data;

import java.util.List;

public class PartyVotesDAOImpl implements PartyVotesDAO {
    public List<PartyVotes> list() {
        return PartyVotes.em()
                .createNamedQuery("getPartyResults")
                .getResultList();
    }
}
