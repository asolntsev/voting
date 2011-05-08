package data;

import models.County;
import models.ParticipationResult;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;

public class ParticipationResultDAOTest {
    County county1 = new County(1L, "Baikal", 28);
    County county2 = new County(2L, "Siberia", 36);
    ParticipationResultDAO dao = Mockito.spy(new ParticipationResultDAO());

    @Test
    public void returnsEmptyCollectionIfNoDataFound() {
        doReturn(Collections.<ParticipationByDate>emptyList()).when(dao).getParticipationPerDay(anyInt(), anyInt());
//        when(dao.getParticipationPerDay(anyInt(), anyInt())).thenReturn(Collections.<ParticipationByDate>emptyList());
        List<ParticipationResult> results = dao.list();
        assertThat(results.isEmpty(), is(true));
    }

    @Test
    public void collectsParticipationByDates() {
        doReturn(
                asList(
                        new ParticipationByDate(county1, 11),
                        new ParticipationByDate(county2, 22)
                )
        ).when(dao).getParticipationPerDay(anyInt(), anyInt());

        List<ParticipationResult> participationResults = dao.list();

        assertEquals(2, participationResults.size());
        assertEquals(participationResults.get(0).getVotesPerDate(), asList(11, 11, 11, 11, 11, 11, 11));
        assertEquals(participationResults.get(1).getVotesPerDate(), asList(22, 22, 22, 22, 22, 22, 22));
    }

    @Test
    public void totalCountySummarizesAllCounties() {
        //when(dao.getAllCounties()).thenReturn(asList(county1, county2));
        doReturn(asList(county1, county2)).when(dao).getAllCounties();

        County totalCounty = dao.getTotalCounty();
        assertThat(totalCounty.getName(), equalTo("Eesti Vabariik kokku"));
        assertThat(totalCounty.getPopulation(), equalTo(28+36));
    }
}
