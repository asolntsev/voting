package data;

import models.Constituency;
import models.ParticipationResult;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

public class ParticipationResultDAOTest {
	Constituency constituency1 = new Constituency(1L, "Baikal", 28);
	ParticipationResultDAO dao = Mockito.spy(new ParticipationResultDAO());

	@Test
	public void returnsEmptyCollectionIfNoDataFound() {
		doReturn(Collections.<ParticipationResult>emptyList()).when(dao).getParticipationPerDay();
		List<ParticipationResult> results = dao.list();
		assertThat(results.isEmpty(), is(true));
	}

}
