package models;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ParticipationResultTest {
	Constituency constituency1 = new Constituency(1L, "Baikal", 50);

	@Test
	public void testGetParticipationPerDay() throws Exception {
		ParticipationResult participation = new ParticipationResult(constituency1, new Integer[]{10,20,30,40,50});
		assertArrayEquals(new float[]{20, 40, 60, 80, 100}, participation.getParticipationPerDay(), 0.00001f);
	}
}
