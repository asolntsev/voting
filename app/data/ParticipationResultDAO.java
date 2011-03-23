package data;

import models.Constituency;
import models.EstonianUniverse;
import models.ParticipationResult;

import javax.persistence.Query;
import java.util.*;

public class ParticipationResultDAO {
	private Constituency total = null;

	private Constituency getTotalConstituency() {
		if (total == null) {
			List<Constituency> all = Constituency.all().fetch();
			int totalPopulation = 0;
			for (Constituency constituency : all) {
				totalPopulation += constituency.getPopulation();
			}
			total = new Constituency(1000L+all.size(), "Eesti Vabariik kokku", totalPopulation);
		}

		return total;
	}

	public List<ParticipationResult> list() {
		Map<Long,ParticipationResult> accumulator = new TreeMap<Long, ParticipationResult>();
		loadParticipation(accumulator, Calendar.DATE, -5);
		loadParticipation(accumulator, Calendar.DATE, -4);
		loadParticipation(accumulator, Calendar.DATE, -3);
		loadParticipation(accumulator, Calendar.HOUR_OF_DAY, 12);
		loadParticipation(accumulator, Calendar.HOUR_OF_DAY, 16);
		loadParticipation(accumulator, Calendar.HOUR_OF_DAY, 20);
		loadParticipation(accumulator, Calendar.DATE, 1); // total votes (final result)
		return new ArrayList<ParticipationResult>(accumulator.values());
	}

	private void loadParticipation(Map<Long, ParticipationResult> accumulator, int field, int shift) {
		List<ParticipationByDate> participationPerDay = getParticipationPerDay(field, shift);
		for (ParticipationByDate participationByDate : participationPerDay) {
			accumulate(accumulator, participationByDate);
		}
	}

	private void accumulate(Map<Long, ParticipationResult> accumulator, ParticipationByDate participationByDate) {
		ParticipationResult result = accumulator.get(participationByDate.getConstituency().getId());
		if (result == null) {
			result = new ParticipationResult(participationByDate.getConstituency());
			accumulator.put(participationByDate.getConstituency().getId(), result);
		}
		result.addVotes(participationByDate.getVotes());
	}

	@SuppressWarnings({"unchecked"})
	List<ParticipationByDate> getParticipationPerDay(int field, int shift) {
		Calendar votingDate = EstonianUniverse.getVotingDate();
		votingDate.add(field, shift);

		List<ParticipationByDate> resultList = ParticipationByDate.em()
				.createNamedQuery("getParticipationByDate")
				.setParameter("votingDate", votingDate.getTime())
				.getResultList();

		Number totalVotes = (Number) ParticipationByDate.em()
				.createNamedQuery("getTotalParticipationByDate")
				.setParameter("votingDate", votingDate.getTime())
				.getSingleResult();

		resultList.add(new ParticipationByDate(getTotalConstituency(), totalVotes));

		return resultList;
	}
}
