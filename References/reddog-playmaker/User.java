import java.io.*;
import java.util.*;

public class User{

	String userName = "Default";

	int gamesPlayed = 0;
	int wins = 0;
	int losses = 0;
	int ties = 0;
   
  int achievementPoints = 0;
  String achievementRating = "";
  int [] achievements = new int[20];

	int streak = 0;
	String streakType = "W"; // Will be 'W', 'L' or 'T'

	double pointsPerGame = 0.0;
	int maxPointsScored = 0;
	double pointsAgainstPerGame = 0.0;
	int maxPointsAgainst = 0;

	double overallYardsPerGame = 0.0;
	double passYardsPerGame = 0.0;
	double rushYardsPerGame = 0.0;

	double overallYardsAgainstPerGame = 0.0;
	double passYardsAgainstPerGame = 0.0;
	double rushYardsAgainstPerGame = 0.0;

	// Frequency values
	int [] teamUseFrequency = new int[31];
	String [] teamList;
	String favoriteTeam = "NULL";


	public User(String [] teams){
		teamList = teams;
		for (int i = 0; i < 31; i++)
			teamUseFrequency[i] = 0;
         
    for (int i = 0; i < 20; i++)
      achievements[i] = 0;
	}
	
	void resetValues(){
		for (int i = 0; i < 31; i++)
			teamUseFrequency[i] = 0;
         
    for (int i = 0; i < 20; i++)
      achievements[i] = 0;
			
		userName = "Default";

    achievementScore = 0;
    achievementRating = "";

		gamesPlayed = 0;
		wins = 0;
		losses = 0;
		ties = 0;

		streak = 0;
		streakType = "W";

		pointsPerGame = 0.0;
		maxPointsScored = 0;
		pointsAgainstPerGame = 0.0;
		maxPointsAgainst = 0;

		overallYardsPerGame = 0.0;
		passYardsPerGame = 0.0;
		rushYardsPerGame = 0.0;

		overallYardsAgainstPerGame = 0.0;
		passYardsAgainstPerGame = 0.0;
		rushYardsAgainstPerGame = 0.0;
		
		favoriteTeam = "NULL";
	}
	
	void setUserName(String name){
		userName = name;
	}
	
	String getUserName(){
		return userName;
	}
   
  int getAchievementScore(){
    return achievementScore;
  }
  
  void setAchievementScore(int score){
    achievementScore = score;
  }
   
  String getAchievementRating(){
    return achievementRating;
  }

  void setAchievementRating(String rating){
    achievementRating = rating;
  }

	void addGame(int winLoss){
		gamesPlayed++;
		if (winLoss == 1){
			wins++
			if (streakType.equals("W"))
				streak++;
			else{
				streakType = "W";
				streak = 1;
			}
		}
		else if (winLoss == 0){
			losses++
			if (streakType.equals("L"))
				streak++;
			else{
				streakType = "L";
				streak = 1;
			}
		}
		else{
			ties++
			if (streakType.equals("T"))
				streak++;
			else{
				streakType = "T";
				streak = 1;
			}
		}
	}

	int getGamesPlayed(){
		return gamesPlayed;
	}
	
	int getUserWins(){
		return wins;
	}
	
	int getUserLosses(){
		return losses;
	}
	
	int getUserTies(){
		return ties;
	}
	
	double getWinPercentage(){
		return (wins + (double)(ties / 2)) / (wins + losses + ties);
	}
	
	String getStreak(){
		return streakType + String.valueOf(streak);
	}
	
	void setPoints(int userPoints, int opponentPoints){
		if (userPoints > maxPointsScored)
			maxPointsScored = userPoints;
		if (opponentPoints > maxPointsAgainst)
			maxPointsAgainst = opponentPoints;
			
		int totalPoints = (int)(pointsPerGame * (gamesPlayed - 1)) + userPoints;
		pointsPerGame = (double)(totalPoints / gamesPlayed);
	
		totalPoints = (int)(pointsAgainstPerGame * (gamesPlayed - 1)) + opponentPoints;
		pointsAgainstPerGame = (double)(totalPoints / gamesPlayed);
	
	}
	
	double getPointsPerGame(){
		return pointsPerGame;
	}
	
	double getPointsAgainstPerGame(){
		return pointsAgainstPerGame;
	}
	
	int getMaxPointsScored(){
		return maxPointsScored;
	}
	
	int getMaxPointsAgainst(){
		return maxPointsAgainst;
	}
	
	void setOverallYards(int userYards, int opponentYards){
		int totalYards = (int)(overallYardsPerGame * (gamesPlayed - 1)) + userYards;
		overallYardsPerGame = (double)(totalYards / gamesPlayed);

		totalYards = (int)(overallYardsAgainstPerGame * (gamesPlayed - 1)) + opponentYards;
		overallYardsAgainstPerGame = (double)(totalYards / gamesPlayed);
	}
	
	double getOverallYardsPerGame(){
		return overallYardsPerGame;
	}
	
	double getOverallYardsAgainstPerGame(){
		return overallYardsAgainstPerGame;
	}

	void setPassingYards(int userPassYards, int opponentPassYards){
		int totalYards = (int)(passYardsPerGame * (gamesPlayed - 1)) + userPassYards;
		passYardsPerGame = (double)(totalYards / gamesPlayed);

		totalYards = (int)(passYardsAgainstPerGame * (gamesPlayed - 1)) + opponentPassYards;
		passYardsAgainstPerGame = (double)(totalYards / gamesPlayed);
	}
	
	double getPassYardsPerGame(){
		return passYardsPerGame;
	}
	
	double getPassYardsAgainstPerGame(){
		return passYardsAgainstPerGame;
	}
	
	void setRushingYards(int userRushYards, int opponentRushYards){
		int totalYards = (int)(rushYardsPerGame * (gamesPlayed - 1)) + userRushYards;
		rushYardsPerGame = (double)(totalYards / gamesPlayed);
		
		totalYards = (int)(rushYardsAgainstPerGame * (gamesPlayed - 1)) + opponentRushYards;
		rushYardsAgainstPerGame = (double)(totalYards / gamesPlayed);
	}
	
	double getRushYardsPerGame(){
		return rushYardsPerGame;
	}
	
	double getRushYardsAgainstPerGame(){
		return rushYardsAgainstPerGame;
	}

	void increaseTeamFrequency(String teamName){
		int teamIndex = 0;
		for (int i = 0; i < 31; i++){
			if (teamList[i].equals(teamName)){
				teamUseFrequency[i]++;
				teamIndex = i;
			}
		}
		
		int currentHighIdx = -1;
		for (int i = 0; i < 31; i++){
			if (teamList[i].equals(favoriteTeam))
				currentHighIdx = i;
		}
		
		if (currentHighIdx == -1){
			favoriteTeam = teamList[teamIndex];
			return;	// Without this, the below if statement will throw an OutOfBounds Exception
		}
		
		if (teamUseFrequency[teamIndex] > teamUseFrequency[currentHighIdx])
			favoriteTeam = teamList[teamIndex];
	}
	
	String getFavoriteTeam(){
		return favoriteTeam;
	}
	
	void saveUser(){
		try{
			FileWriter out = new FileWriter(new File("saveData/users/" + userName + ".usr"));
			
			out.println(gamesPlayed + " " + wins + " " + losses + " " ties + " " + streak + " " + streakType);

			out.println(pointsPerGame + " " + maxPointsScored + " " + pointsAgainstPerGame + " " + maxPointsAgainst);
			
			out.println(overallYardsPerGame + " " + passYardsPerGame + " " + rushYardsPerGame);
			
			out.println(overallYardsAgainstPerGame + " " + passYardsAgainstPerGame + " " + rushYardsAgainstPerGame);
			
			for (int i = 0; i < 31; i++)
				out.print(teamUseFrequency[i] + " ");
            
      for (int i = 0; i < 20; i++)
        out.print(achievements[i] + " ");
			
			out.println(favoriteTeam);
			
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	void loadUser(String user){
		resetValues();
		userName = user;
		File file = new File("saveData/users/" + userName + ".usr");
		
		try{
			Scanner in = new Scanner(file);
			
			gamesPlayed = in.nextInt();
			wins = in.nextInt();
			losses = in.nextInt();
			ties = in.nextInt();
			streak = in.nextInt();
			streakType = in.next();
			
			pointsPerGame = in.nextDouble();
			maxPointsScored = in.nextInt();
			pointsAgainstPerGame = in.nextDouble();
			maxPointsAgainst = in.nextInt();
			
			overallYardsPerGame = in.nextDouble();
			passYardsPerGame = in.nextDouble();
			rushYardsPerGame = in.nextDouble();
			
			overallYardsAgainstPerGame = in.nextDouble();
			passYardsAgainstPerGame = in.nextDouble();
			rushYardsAgainstPerGame = in.nextDouble();
			
			for (int i = 0; i < 31; i++)
				teamUseFrequency[i] = in.nextInt();
            
      for (int i = 0; i < 20; i++)
        achivements[i] = in.nextInt();
				
			favoriteTeam = in.next();
			
			in.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}

}