package data;

import models.Constituency;
import models.EstonianUniverse;
import models.ParticipationResult;

import java.util.*;

import static java.util.Calendar.DAY_OF_YEAR;
import static models.EstonianUniverse.VOTING_LENGTH_DAYS;

public class ParticipationResultDAO {
	public List<ParticipationResult> list() {
		SortedMap<Long, ParticipationResult> results = new TreeMap<Long, ParticipationResult>();
		List<ParticipationDayResult> dayResults = getParticipationPerDay();
		for (ParticipationDayResult dayResult : dayResults) {
			ParticipationResult result = results.get(dayResult.getConstituency().getId());
			if (result == null) {
				result = new ParticipationResult(dayResult.getConstituency());
				results.put(dayResult.getConstituency().getId(), result);
			}
			result.setDayVotes(dayResult.getDayOfYear(), dayResult.getDayVotes());
		}

		fillMissingDays(results);
		return new ArrayList<ParticipationResult>(results.values());
	}

	private void fillMissingDays(Map<Long, ParticipationResult> results) {
		for (ParticipationResult result : results.values()) {
			Calendar day = EstonianUniverse.getVotingDate();
			for (int i = 0; i < VOTING_LENGTH_DAYS; i++) {
				int dayOfYear = day.get(DAY_OF_YEAR);
				if (!result.getVotesPerDay().containsKey(dayOfYear)) {
					result.setDayVotes(dayOfYear, 0);
				}
				day.add(DAY_OF_YEAR, -1);
			}
		}
	}

	@SuppressWarnings({"unchecked"})
	List<ParticipationDayResult> getParticipationPerDay() {
		return ParticipationDayResult.em().createNamedQuery("getParticipationPerDay").getResultList();
	}

	public ParticipationResult countTotalParticipation(Collection<ParticipationResult> results) {
		Constituency summary = new Constituency("Eesti Vabariik kokku", 0);
		ParticipationResult total = new ParticipationResult(summary);

		for (ParticipationResult result : results) {
			summary.setPopulation(summary.getPopulation() + result.getConstituency().getPopulation());

			for (Map.Entry<Integer, Integer> entry : result.getVotesPerDay().entrySet()) {
				total.addDayVotes(entry.getKey(), entry.getValue());
			}
		}

		return total;
	}
}
