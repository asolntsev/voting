package data;

import java.util.List;

public class PartyVotesDAOImpl implements PartyVotesDAO {
    public List<PartyVotes> list() {
        return PartyVotes.em()
                .createNamedQuery("getPartyResults")
                .getResultList();
    }

    @Override
    public int gotTotalVotes() {
        return ((Number) (
                PartyVotes.em().createNamedQuery("getTotalVotes").getSingleResult())
                ).intValue();
    }
}
