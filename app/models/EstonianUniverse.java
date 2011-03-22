package models;

import java.util.Calendar;

public class EstonianUniverse {
	public static int VOTING_LENGTH_DAYS = 6;

	public static Calendar getVotingDate() {
		Calendar votingDate = Calendar.getInstance();
		votingDate.set(Calendar.YEAR, 2011);
		votingDate.set(Calendar.MONTH, 3);
		votingDate.set(Calendar.DATE, 11);
		return votingDate;
	}
}
