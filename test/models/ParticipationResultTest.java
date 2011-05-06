package models;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ParticipationResultTest {
	County county1 = new County(1L, "Tartumaa", 50);

	@Test
	public void countsParticipationInPercents() throws Exception {
		Integer[] votesPerDate = {10, 20, 30, 40, 50};
		ParticipationResult participation = new ParticipationResult(county1, votesPerDate);


		float[] expectedParticipationPerDate = {20, 40, 60, 80, 100};
		assertArrayEquals(expectedParticipationPerDate, participation.getParticipationPerDay(), 0.00001f);
	}
}
