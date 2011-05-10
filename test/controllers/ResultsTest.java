package controllers;

import data.PartyVotes;
import data.PartyVotesDAO;
import models.Party;
import models.PartyResults;
import models.VotingResults;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResultsTest {

    @Before
    public void mockVotes() {
        Results.partyVotes = mock(PartyVotesDAO.class);
        when(Results.partyVotes.list()).thenReturn(asList(
                new PartyVotes(new Party("Beer lovers party"), 200),
                new PartyVotes(new Party("Agilists party"), 600)
        ));
        // when(Results.partyVotes.gotTotalVotes()).thenReturn(800);
    }

    @Test
    public void computesMandatesAccordingToVotes() {
        List<PartyResults> partyResults = Results.computeMandates().getPartyResults();

        PartyResults beerLovers = partyResults.get(0);
        assertThat(beerLovers.getVotes(), equalTo(200));
        assertThat(beerLovers.getVotesPercentage(), equalTo(25d));
        assertThat(beerLovers.getMandates(), equalTo(25));

        PartyResults agileLovers = partyResults.get(1);
        assertThat(agileLovers.getVotes(), equalTo(600));
        assertThat(agileLovers.getVotesPercentage(), equalTo(75d));
        assertThat(agileLovers.getMandates(), equalTo(76));
    }

    @Test
    public void summarizesAllVotes() {
        VotingResults votingResults = Results.computeMandates();
        assertThat(votingResults.getTotalVotes(), equalTo(800));
    }

    @Test
    public void totally101MandatesShouldBeShared() {
        VotingResults votingResults = Results.computeMandates();
        List<PartyResults> partyResults = votingResults.getPartyResults();

        assertThat(partyResults.get(0).getMandates() +
                   partyResults.get(1).getMandates(), equalTo(101));
    }
}
