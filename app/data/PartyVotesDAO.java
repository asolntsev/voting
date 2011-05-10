package data;

import java.util.List;

public interface PartyVotesDAO {
    List<PartyVotes> list();
    int gotTotalVotes();
}
