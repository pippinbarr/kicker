


public class Drive{

	// Name of team currently in possession of the ball
	String teamDrive = "";

	// Amount of time elapsed during drive
	int timeElapsed = 0;

	// Number of plays performed during drive
	int numPlays = 0;

	// Number of yards covered to score
	int yardsCovered = 0;

	// If play resulted in a score
	boolean scoringDrive = false;


	// Only available if this is a scoring drive
	int scoringTeamScore = 0;
	int defendingTeamScore = 0;

	// Options are: P - Pass, R - Run, PR - Punt Return
	//		KR - Kick Return, S - Safety, FG - Field Goal
	String scoreType = "";

	int scoringDistance = 0;

	int quarter = 0;
	int timeRemaining = 0;

	// Options are: 2PT - 2 Point Conversion, XP - Extra Point
	String extraPointAttempt = "";
	boolean extraPointGood = false;

	// Rushers/Receivers for TDs, Kickers for FG
	String primaryScorer = "";

	// Quarterbacks for passing TDs
	String secondaryScorer = "";

	// Kicker for XP, Rushers/Receivers for 2PCs
	String extraPointPrimary = "";




	public Drive(String teamDriving){
		teamDrive = teamDriving;
	}


	void addTimeElapsed(int time){
		timeElapsed += time;
	}

	void addPlay(int distance, int time){
		numPlays++;
		yardsCovered += distance;
		addTimeElapsed(time);
	}

	void addScoringDrive(int qtr, int timeLeft, String type, String primary, String secondary, int distance, int offScore, int defScore){
		scoringDrive = true;
		scoreType = type;
		primaryScorer = primary;
		secondaryScorer = secondary;
		scoringDistance = distance;
		quarter = qtr;
		timeRemaining = timeLeft;

		scoringTeamScore = offScore;
		defendingTeamScore = defScore;
	}

	void addExtraPointAttempt(boolean made, String attempt, String primary){
		extraPointGood = made;

		extraPointAttempt = attempt;

		if (extraPointGood)
			if (extraPointAttempt.equals("XP"))
				scoringTeamScore++;
			else
				scoringTeamScore += 2;

		extraPointPrimary = primary;
	}

	String getTeamScoring(){
		return teamDrive;
	}

	int getNumberOfPlays(){
		return numPlays;
	}

	int getYardsCovered(){
		return yardsCovered;
	}

	int getScoringDistance(){
		return scoringDistance;
	}

	String getTimeElapsed(){
		if (timeElapsed % 60 < 10)
			return ( (timeElapsed / 60) + ":0" + (timeElapsed % 60));
		else
			return ( (timeElapsed / 60) + ":" + (timeElapsed % 60));
	}

	String getTimeOfScoring(){
		if (timeRemaining % 60 < 10)
			return ( (timeRemaining / 60) + ":0" + (timeRemaining % 60));
		else
			return ( (timeRemaining / 60) + ":" + (timeRemaining % 60) );
	}

	int getQuarterScored(){
		return quarter;
	}

	int getScoringTeamScore(){
		return scoringTeamScore;
	}

	int getDefendingTeamScore(){
		return defendingTeamScore;
	}


	String getPlayType(){
		if (scoreType.equals("P"))
			return "pass";
		else if (scoreType.equals("R"))
			return "run";
		else if (scoreType.equals("PR"))
			return "punt return";
		else if (scoreType.equals("KR"))
			return "kick return";
		else if (scoreType.equals("S"))
			return "safety";
		else if (scoreType.equals("FG"))
			return "field goal";

		return "";
	}

	String getPrimaryPlayer(){
		return primaryScorer;
	}

	String getSecondaryPlayer(){
		return secondaryScorer;
	}

	boolean isScoringDrive(){
		return scoringDrive;
	}

	boolean isExtraPointGood(){
		return extraPointGood;
	}

	String getExtraPointType(){
		if (extraPointAttempt.equals("2PT"))
			return "2-Point conversion";
		else
			return "PAT";
	}

	String getExtraPointPlayer(){
		return extraPointPrimary;
	}
}
