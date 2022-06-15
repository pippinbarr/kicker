import javax.swing.*;
import java.awt.*;


public class Field{

	String stadium = "";
	String location = "";
	String weather = "";

	int timeRemaining = 0;
	int quarter = 0;
	int down = 0;
	int yardsToGo = 0;
	int yardline = 0;
	int awayTimeOuts = 0;
	int homeTimeOuts = 0;
	int awayScore = 0;
	int homeScore = 0;
	boolean gameOver = false;
	boolean quarterOver = false;
	int possession = 0; // 1 for away, 2 for home

	public Field(){

	}

	void initializeField(){
		timeRemaining = 15 * 60;
		quarter = 1;
		down = 1;
		yardsToGo = 10;
		awayTimeOuts = 3;
		homeTimeOuts = 3;
		yardline = 35;
		gameOver = false;
		quarterOver = false;
		homeScore = 0;
		awayScore = 0;
	}

	void setStadium(String s){
		stadium = s;
	}

	void setLocation(String city, String st){
		location = city + ", " + st;
	}

	void setWeather(String climate){
		// No longer being implemented
		weather = climate;
	}

	void reduceTime(int seconds, boolean scored){
		quarterOver = false;
		timeRemaining -= seconds;

		if (timeRemaining <= 0 && !scored)
			quarterOver();
		else if (timeRemaining <= 0)
			timeRemaining = 0;
	}

	void quarterOver(){
		quarterOver = true;

		if ( (quarter == 4 && awayScore == homeScore) || quarter < 4){
			quarter++;
			timeRemaining = 15 * 60; // 15:00
		}
		else if (quarter == 4 && awayScore != homeScore){
			gameOver = true;
			timeRemaining = 0;
		}
		else if (quarter == 5 && awayScore == homeScore){
			gameOver = true;
			timeRemaining = 0;
		}

		if (quarter == 3){
			awayTimeOuts = 3;
			homeTimeOuts = 3;
		}
		else if (quarter == 5){
			awayTimeOuts = 2;
			homeTimeOuts = 2;
		}
	}

	boolean isQuarterOver(){
		return quarterOver;
	}

	boolean isGameOver(){
		return gameOver;
	}

	void setQuarter(int i){
		quarter = i;
	}

	int getQuarter(){
		return quarter;
	}

	void playPerformed(int yards, int timeElapsed){
		yardsToGo -= yards;
		if (yardsToGo <= 0){
			yardsToGo = 10;
			down = 1;
		}
		else{
			if (down == 4)
				turnover();
			else
				down++;
		}

		yardline += yards;
		if (yardline < 0)
			safetyScored();
		if (yardline >= 100)
			touchdownScored();
	}

	void fgPerformed(boolean made){
		if (made)
			fieldGoalScored();
		else{
			yardline -= 7;
			turnover();
		}

		if (made && quarter == 5)
			gameOver = true;
	}

	void patPerformed(boolean made){
		if (made)
			patScored();
	}

	void kickoff(int yards){
		yardline = 30 + yards;
		if (yardline >= 100)
			yardline = 80;

		turnover();
	}


	void puntPerformed(int yards, int returned){
		yardline = yardline + yards - returned;

		if (yardline >= 100)
			yardline = 80;

		turnover();
	}

	void turnover(){
		if (possession == 1)
			possession = 2;
		else
			possession = 1;

		down = 1;
		yardsToGo = 10;

		yardline = 100 - yardline;
	}

	void timeoutCalled(int team){
		if (team == 1)
			awayTimeOuts -= 1;
		else
			homeTimeOuts -= 1;
	}

	int getTimeouts(int team){
		if (team == 1)
			return awayTimeOuts;
		else
			return homeTimeOuts;
	}


	void touchdownScored(){
		if (possession == 1)
			awayScore += 6;
//			awayScore += 7;
		else
			homeScore += 6;
//			homeScore += 7;

		yardline = 97;

		if (quarter == 5)
			gameOver = true;

	}

	void fieldGoalScored(){
		if (possession == 1)
			awayScore += 3;
		else
			homeScore += 3;

		if (quarter == 5)
			gameOver = true;

	//	yardline = 30;
	}

	void safetyScored(){
		if (possession == 1)
			homeScore += 2;
		else
			awayScore += 2;

	//	yardline = 20;
	}

	void patScored(){
		if (possession == 1)
			awayScore += 1;
		else
			homeScore += 1;

		yardline = 20;
	}

	void conversionScored(){
		if (possession == 1)
			awayScore += 2;
		else
			homeScore += 2;

		yardline = 20;
	}



	String getMinutesLeft(){
		int min = timeRemaining / 60;
		String minutes = String.valueOf(min);
		return minutes;
	}

	String getSecondsLeft(){
		int sec = timeRemaining % 60;
		String seconds = String.valueOf(sec);
		return seconds;
	}

	void setTimeLeft(int time){
		if (time > 900)
			timeRemaining = 900;
		else
			timeRemaining = time;
	}

	int getTimeLeft(){
		return timeRemaining;
	}

	void scoredInOvertime(){
		gameOver = true;
	}

	void setPossession(int p){
		possession = p;
	}

	int getPossession(){
		return possession;
	}

	void setDown(int i){
		down = i;
	}

	int getDown(){
		return down;
	}

	void setYardsToGo(int i){
		yardsToGo = i;
	}

	int getYardsToGo(){
		return yardsToGo;
	}

	void setYardline(int i){
		yardline = i;
	}
	
	int getYardline(){
		return yardline;
	}

	void setHomeScore(int i){
		homeScore = i;
	}

	int getHomeScore(){
		return homeScore;
	}

	void setAwayScore(int i){
		awayScore = i;
	}

	int getAwayScore(){
		return awayScore;
	}

	int getScoringTeamScore(){
		if (possession == 1)
			return getAwayScore();
		else
			return getHomeScore();
	}

	int getDefendingTeamScore(){
		if (possession == 1)
			return getHomeScore();
		else
			return getAwayScore();
	}

	void changePossession(){
		if (possession == 1)
			possession = 2;
		else
			possession = 1;
	}

}
