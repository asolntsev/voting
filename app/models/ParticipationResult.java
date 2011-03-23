package models;

import java.util.*;

import static java.util.Arrays.asList;

public class ParticipationResult {
	private Constituency constituency;
	private List<Integer> votesPerDate;

	public ParticipationResult(Constituency constituency) {
		this.constituency = constituency;
		this.votesPerDate = new ArrayList<Integer>();
	}

	public ParticipationResult(Constituency constituency, Integer[] votesPerDate) {
		this.constituency = constituency;
		this.votesPerDate = asList(votesPerDate);
	}

	public Constituency getConstituency() {
		return constituency;
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
			result[i] = (float) 100 * votesPerDate.get(i) / constituency.getPopulation();
		}
		return result;
	}
}
