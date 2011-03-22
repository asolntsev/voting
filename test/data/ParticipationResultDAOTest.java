package data;

import models.Constituency;
import models.ParticipationResult;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Calendar.DAY_OF_YEAR;
import static models.EstonianUniverse.VOTING_LENGTH_DAYS;
import static models.EstonianUniverse.getVotingDate;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

public class ParticipationResultDAOTest {
	Constituency constituency1 = new Constituency(1L, "Baikal", 28);
	ParticipationResultDAO dao = Mockito.spy(new ParticipationResultDAO());

	@Test
	public void returnsEmptyCollectionIfNoDataFound() {
		doReturn(Collections.<ParticipationDayResult>emptyList()).when(dao).getParticipationPerDay();
		List<ParticipationResult> results = dao.list();
		assertThat(results.isEmpty(), is(true));
	}

	@Test
	public void fillsMissingDays() {
		int day = getVotingDate().get(DAY_OF_YEAR) - VOTING_LENGTH_DAYS + 1;

		List<ParticipationDayResult> mockedVotesPerDay = Arrays.asList(
				new ParticipationDayResult(constituency1, 1, day),
				new ParticipationDayResult(constituency1, 2, day + 1),
				new ParticipationDayResult(constituency1, 4, day + 3)
		);

		doReturn(mockedVotesPerDay).when(dao).getParticipationPerDay();

		List<ParticipationResult> results = dao.list();
		ParticipationResult firstResult = results.get(0);

		assertThat(firstResult.getTotalParticipation(), equalTo(7f / 28 * 100));
		assertThat(firstResult.getVotesPerDay().size(), equalTo(VOTING_LENGTH_DAYS));
		assertThat(firstResult.getVotesPerDay().values().toArray(), equalTo(new Object[] {1, 2, 0, 4, 0, 0}));
	}
}
