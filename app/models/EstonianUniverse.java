package models;

import java.util.Calendar;

public class EstonianUniverse {
	public static int VOTING_LENGTH_DAYS = 6;
	public static int MANDATES = 101;

	public static Calendar getVotingDate() {
		Calendar votingDate = Calendar.getInstance();
		votingDate.set(Calendar.YEAR, 2011);
		votingDate.set(Calendar.MONTH, Calendar.MARCH);
		votingDate.set(Calendar.DATE, 11);
		votingDate.set(Calendar.HOUR_OF_DAY, 0);
		votingDate.set(Calendar.MINUTE, 0);
		votingDate.set(Calendar.SECOND, 0);
		votingDate.set(Calendar.MILLISECOND, 0);
		return votingDate;
	}
}
