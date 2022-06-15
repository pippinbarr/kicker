import java.text.*;

// Statistics class (for both team and player)

public class Statistics{

	boolean isTeam = false;

	// Team Statistics
	int [] quarterScoring = new int[5];

	int firstDowns = 0;

	int passingYards = 0;
	int rushingYards = 0;
	int kickReturnYards = 0;	// Use for both player and team
	int puntReturnYards = 0;	// Use for both player and team

	int thirdDownAttempts = 0;
	int thirdDownConversions = 0;

	int fourthDownAttempts = 0;
	int fourthDownConversions = 0;

	int redZoneAttempts = 0;
	int redZoneConversions = 0;

	int turnovers = 0;

	int timeOfPossession = 0;


	// Player Statistics

	// Passer Statistics
	int attempts = 0;
	int completions = 0;
	int interceptions = 0;
	double passerRating = 0.0;
	int passYards = 0;
	int passLong = 0;
	int passTDs = 0;
	int sacks = 0;
	double rating = 0.0;

	// Rusher Statistics
	int rushes = 0;
	int rushYards = 0;
	int rushLong = 0;
	int rushTDs = 0;
	int fumbles = 0;
	int fumblesLost = 0;

	// Receiver Statistics
	int receptions = 0;
	int recYards = 0;
	int recTDs = 0;
	int puntReturns = 0;
	int kickReturns = 0;

	// Kicker/Punter Statistics
	int punts = 0;
	int puntYards = 0;
	int inside20 = 0;	// For punts only
	int longestPunt = 0;
	int kickYards = 0;
	int touchbacks = 0; 	// For kickoffs only
	int fieldGoalAttempts = 0;
	int fieldGoalsMade = 0;
	int fieldGoalLong = 0;
	int extraPointAttempts = 0;
	int extraPointsMade = 0;

	int pointsScored = 0;	// For kicker AND for teams in season mode
	int pointsAgainst = 0;

	public Statistics(boolean teamStats){
		isTeam = teamStats;
	}

	// So that when a new game is played, any remnant information from a previous game is removed
	void initialize(){

		for (int i = 0; i < 5; i++)
			quarterScoring[i] = 0;


		firstDowns = 0;

		rushes = 0;
		attempts = 0;
		completions = 0;
		interceptions = 0;

		passingYards = 0;
		rushingYards = 0;

		puntReturns = 0;
		kickReturns = 0;
		kickReturnYards = 0;
		puntReturnYards = 0;

		thirdDownAttempts = 0;
		thirdDownConversions = 0;

		fourthDownAttempts = 0;
		fourthDownConversions = 0;

		redZoneAttempts = 0;
		redZoneConversions = 0;

		turnovers = 0;

		timeOfPossession = 0;
	}

	void addPointsScored(int p){
		pointsScored += p;
	}

	void addPointsAgainst(int p){
		pointsAgainst += p;
	}

	int getPointsAgainst(){
		return pointsAgainst;
	}

	void addFirstDown(){
		firstDowns++;
	}

	int getFirstDowns(){
		return firstDowns;
	}


	void addPassingYards(int yards){
		if (isTeam)
			passingYards += yards;
		else{
			passYards += yards;
			updateLongestPass(yards);
		}
	}

	int getPassingYards(){
		if (isTeam)
			return passingYards;
		else
			return passYards;
	}

	void addRushingYards(int yards){
		if (isTeam)
			rushingYards += yards;
		else{
			rushYards += yards;
			updateLongestRun(yards);
		}
	}

	int getRushingYards(){
		if (isTeam)
			return rushingYards;
		else
			return rushYards;
	}


	int getTotalOffense(){
		return (rushingYards + passingYards);
	}

	int getTotalYards(){
		return (rushingYards + passingYards + kickReturnYards + puntReturnYards);
	}

	void addKickReturnYards(int yards){
		kickReturnYards += yards;
		kickReturns++;
	}

	int getKickReturns(){
		return kickReturns;
	}

	int getKickReturnYards(){
		return kickReturnYards;
	}

	void addPuntReturnYards(int yards){
		puntReturnYards += yards;
		puntReturns++;
	}

	int getPuntReturnYards(){
		return puntReturnYards;
	}

	int getPuntReturns(){
		return puntReturns;
	}

	void addThirdDownAttempt(){
		thirdDownAttempts++;
	}

	int getThirdDownAttempts(){
		return thirdDownAttempts;
	}

	void addThirdDownConversion(){
		thirdDownConversions++;
	}

	int getThirdDownConversions(){
		return thirdDownConversions;
	}

	void addFourthDownAttempt(){
		fourthDownAttempts++;
	}

	int getFourthDownAttempts(){
		return fourthDownAttempts;
	}

	void addFourthDownConversion(){
		fourthDownConversions++;
	}

	int getFourthDownConversions(){
		return fourthDownConversions;
	}

	void addRedZoneAttempt(){
		redZoneAttempts++;
	}

	int getRedZoneAttempts(){
		return redZoneAttempts;
	}

	void addRedZoneConversion(){
		redZoneConversions++;
	}

	int getRedZoneConversions(){
		return redZoneConversions;
	}

	void addTurnover(){
		turnovers++;
	}

	int getTurnovers(){
		return turnovers;
	}

	void addTimeOfPossession(int sec){
		timeOfPossession += sec;
	}

	String getTimeOfPossession(){
		if (timeOfPossession % 60 < 10)
			return ( (timeOfPossession / 60) + ":0" + (timeOfPossession % 60) );
		else
			return ( (timeOfPossession / 60) + ":" + (timeOfPossession % 60) );

	}

	// Passing Functions

	void addPassAttempt(){
		attempts++;
	}

	void addPassCompletion(){
		completions++;
	}

	void addInterception(){
		interceptions++;
	}

	int getAttempts(){
		return attempts;
	}

	int getCompletions(){
		return completions;
	}

	int getInterceptions(){
		return interceptions;
	}

	String getPassStats(){
		return (completions + "-" + attempts + "-" + interceptions);
	}

	void updateLongestPass(int yards){
		if (yards > passLong)
			passLong = yards;
	}

	int getLongestPass(){
		return passLong;
	}

	void addPassTD(){
		passTDs++;
	}

	int getPassTDs(){
		return passTDs;
	}

	void addSack(){
		sacks++;
	}

	int getSacks(){
		return sacks;
	}

	double getPasserRating(){
		double completionPercentage = 0.0;
		double yardagePercentage = 0.0;
		double touchdownPercentage = 0.0;
		double interceptionPercentage = 0.0;

		if (getAttempts() == 0)
			return 0.0;



		completionPercentage = ( ((double)getCompletions() / (double)getAttempts()) - 0.3) * 5.0;
		yardagePercentage = ( ((double)getPassingYards() / (double)getAttempts()) - 3.0) * 0.25;
		touchdownPercentage = ((double)getPassTDs() / (double)getAttempts()) * 20.0;
		interceptionPercentage = 2.375 - ( ((double)getInterceptions() / (double)getAttempts() ) * 25.0);

	//	System.out.println(completionPercentage);
	//	System.out.println(yardagePercentage);
	//	System.out.println(touchdownPercentage);
	//	System.out.println(interceptionPercentage);

		if (completionPercentage < 0.0)
			completionPercentage = 0.0;
		else if (completionPercentage > 2.375)
			completionPercentage = 2.375;

		if (yardagePercentage < 0.0)
			yardagePercentage = 0.0;
		else if (yardagePercentage > 2.375)
			yardagePercentage = 2.375;

		if (touchdownPercentage < 0.0)
			touchdownPercentage = 0.0;
		else if (touchdownPercentage > 2.375)
			touchdownPercentage = 2.375;

		if (interceptionPercentage < 0.0)
			interceptionPercentage = 0.0;
		else if (interceptionPercentage > 2.375)
			interceptionPercentage = 2.375;

		rating = ( (completionPercentage + yardagePercentage + touchdownPercentage + interceptionPercentage) / 6 ) * 100;

		DecimalFormat oneDForm = new DecimalFormat("#.#");
		return Double.valueOf(oneDForm.format(rating));

	}

	// End Passing functions

	// Rushing functions
	void addRush(){
		rushes++;
	}

	int getRushes(){
		return rushes;
	}

	void updateLongestRun(int yards){
		if (yards > rushLong)
			rushLong = yards;
	}

	void addRushTD(){
		rushTDs++;
	}

	int getRushTDs(){
		return rushTDs;
	}

	void recordFumble(boolean lost){
		fumbles++;
		if (lost)
			fumblesLost++;
	}

	int getFumbles(){
		return fumbles;
	}

	int getFumblesLost(){
		return fumblesLost;
	}

	// End rushing functions

	// Receiving functions

	void addReception(){
		receptions++;
	}

	int getReceptions(){
		return receptions;
	}

	void addReceivingYards(int yards){
		recYards += yards;
	}

	int getReceivingYards(){
		return recYards;
	}

	void addReceivingTD(){
		recTDs++;
	}

	int getReceivingTDs(){
		return recTDs;
	}

	void addPuntReturn(){
		puntReturns++;
	}

	void addKickReturn(){
		kickReturns++;
	}

	// End receiving functions

	// Kicking/Punting Functions

	void addPunt(int yards, boolean inside, boolean touchback){
		punts++;
		puntYards += yards;
		if (inside)
			inside20++;
		if (yards > longestPunt)
			longestPunt = yards;
		if (touchback)
			touchbacks++;
	}

	int getLongestPunt(){
		return longestPunt;
	}

	int getPunts(){
		return punts;
	}

	int getPuntYards(){
		return puntYards;
	}

	int getPuntsInside20(){
		return inside20;
	}

	void addKick(int yards, boolean unreturned){
		kickYards += yards;
		if (unreturned)
			touchbacks++;
	}

	int getKickYards(){
		return kickYards;
	}

	int getTouchbacks(){
		return touchbacks;
	}

	void addFieldGoal(boolean made, int distance){
		fieldGoalAttempts++;
		if (made){
			fieldGoalsMade++;
			pointsScored += 3;
		}
		if (distance > fieldGoalLong && made)
			fieldGoalLong = distance;
	}

	int getFieldGoalAttempts(){
		return fieldGoalAttempts;
	}

	int getFieldGoalsMade(){
		return fieldGoalsMade;
	}

	int getFieldGoalLong(){
		return fieldGoalLong;
	}

	void addExtraPoint(boolean made){
		extraPointAttempts++;
		if (made){
			extraPointsMade++;
			pointsScored++;
		}
	}

	int getExtraPointAttempts(){
		return extraPointAttempts;
	}

	int getExtraPointsMade(){
		return extraPointsMade;
	}

	int getPointsScored(){
		return pointsScored;
	}

	// End Kicking/Punting functions

	// Scoring functions

	void addScoring(int quarter, int points){
		quarterScoring[quarter - 1] += points;
	}

	int getScoring(int quarter){
		return quarterScoring[quarter - 1];
	}

	// End scoring functions
}
