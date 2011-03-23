package data;

import models.EstonianUniverse;
import models.ParticipationResult;

import javax.persistence.Query;
import java.util.List;

public class ParticipationResultDAO {
	public List<ParticipationResult> list() {
		return getParticipationPerDay();
	}

	@SuppressWarnings({"unchecked"})
	List<ParticipationResult> getParticipationPerDay() {
		Query query = ParticipationResult.em().createNamedQuery("getParticipation");
		query.setParameter("votingDate", EstonianUniverse.getVotingDate().getTime());
		return query.getResultList();
	}
}
