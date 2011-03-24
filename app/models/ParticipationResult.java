package models;

import java.util.*;

import static java.util.Arrays.asList;

public class ParticipationResult {
	private County county;
	private List<Integer> votesPerDate;

	public ParticipationResult(County county) {
		this.county = county;
		this.votesPerDate = new ArrayList<Integer>();
	}

	public ParticipationResult(County county, Integer[] votesPerDate) {
		this.county = county;
		this.votesPerDate = asList(votesPerDate);
	}

	public County getCounty() {
		return county;
	}

	public void addVotes(int votes) {
		votesPerDate.add(votes);
	}

	public List<Integer> getVotesPerDate() {
		return votesPerDate;
	}

	public float[] getParticipationPerDay() {
		float[] result = new float[votesPerDate.size()];
		for (int i = 0; i<votesPerDate.size(); i++) {
			result[i] = (float) 100 * votesPerDate.get(i) / county.getPopulation();
		}
		return result;
	}
}
