//Computer Logic for Playcalling

//CPU Class
import java.util.*;

public class CPU{

int currentDown;
int yardsToGo;
int yardline;
int quarter;
int timeLeft;
int cpuScore;
int playerScore;

boolean winning;

// Defensive variables

int firstDownPasses, firstDownRuns;
int secondDownPasses, secondDownRuns;
int thirdDownPasses, thirdDownRuns;
int fourthDownAttempts;

int timeInHuddle = 0;


String playType;	// Run, Pass, Kick
String playAtt;	// Blitz, Man, Zone (D), (Right, Left, Middle) for run, (Long,Mid,Short,Screen) for pass
String playAtt2;	// In,Out,Straight,Screen for passes
String receiver;
boolean hasPossession;
boolean isSpecialTeams;
boolean kickingOff;
Play offensivePlay = new Play();
Play defensivePlay = new Play();

public CPU(){

}

void analyzeField(int down, int yards, int loc, int sec, int quar, int cpu, int player, boolean isOnDefense, boolean onSpecialTeams, boolean kickOff){
	checkDown(down);
	checkYards(yards);
	checkPlacement(loc);
	checkTimeLeft(sec);
	checkQuarter(quar);
	isWinning(cpu, player);
	cpuScore = cpu;
	playerScore = player;
	hasPossession = !isOnDefense;
	isSpecialTeams = onSpecialTeams;
	kickingOff = kickOff;
	String res;


	// Not implemented yet
	if (kickingOff && hasPossession)
		res = selectKickoffStyle();


	if (hasPossession){
		res = selectOffensivePlay();
		setTimeInHuddle();
	}
	else
		res = selectDefensivePlay();

}


void checkDown(int down){
	currentDown = down;
}

void checkYards(int yards){
	yardsToGo = yards;
}

void checkPlacement(int loc){
	yardline = loc;
}

void checkTimeLeft(int sec){
	timeLeft = sec;
}

void checkQuarter(int q){
	quarter = q;
}
void isWinning(int cpu, int player){
	if (cpu >= player)
		winning = true;
	else
		winning = false;
}

boolean willTakeTimeOut(){
	if (quarter == 1 || quarter == 3){
		if (currentDown == 3 && yardsToGo > 15 && timeLeft < 600)
			return true;
		else if (currentDown == 4 && yardsToGo < 3 && yardline > 80){
			int choice = (int)(Math.random() * 40);
			if (choice % 20 == 0)
				return true;
		}
		else
			return false;
	}
	else if (quarter == 2){
		if (hasPossession){
			if (timeLeft < 120 && yardline > 50 && currentDown > 1)
				return true;
			else if (timeLeft < 30 && yardline > 65)
				return true;
		}
		else{
			if (timeLeft < 180 && yardline < 20)
				return true;
			else{
				int choice = (int)(Math.random() * 40);
				if (choice % 20 == 0)
					return true;
			}
		}
	
		return false;
	}
	else if (quarter == 4){
		if (!winning && (playerScore - cpuScore) <= 8 && yardline > 50 && timeLeft < 180 && hasPossession)
			return true;
		else if (playerScore == cpuScore && yardline > 70 && timeLeft < 60)
			return true;
		else if (!winning && (playerScore - cpuScore) <= 14 && !hasPossession && timeLeft > 180 && timeLeft < 300)
			return true;
		else{
			int choice = (int)(Math.random() * 40);
			if (choice % 20 == 0)
				return true;
			else
				return false;
		}
	}
	else if (quarter == 5){
		if (currentDown == 4 && !hasPossession && yardline > 65)
			return true;
		else if (timeLeft < 60 && hasPossession && yardline > 60)
			return true;
		else
			return false;
	}

	return false;
}

void setTimeInHuddle(){
	if (quarter == 1 || quarter == 3)
		timeInHuddle = (int)(Math.random() * 3 + 35);

	else if (quarter == 2){
		if (timeLeft <= 120 && yardline >= 50 && !winning)
			timeInHuddle = (int)(Math.random() * 4 + 15);
		else
			timeInHuddle = (int)(Math.random() * 3 + 35);
	}
	else if (quarter == 4){
		if (!winning && timeLeft <= 300)
			timeInHuddle = (int)(Math.random() * 5 + 18);
		else if (winning)
			timeInHuddle = 39;
		else if (cpuScore == playerScore && timeLeft <= 120){
			if (yardline > 60){
				timeInHuddle = timeLeft - 1;
				if (timeInHuddle >= 40)
					timeInHuddle = 30;
			}
			else
				timeInHuddle = (int)(Math.random() * 6 + 25);
		}
	}
	else if (quarter == 5){
		if (timeLeft > 120)
			timeInHuddle = 38;
		else
			timeInHuddle = (int)(Math.random() * 6 + 25);
	}

}


int getTimeInHuddle(){
	return timeInHuddle;
}

String selectKickoffStyle(){

// This will be used to determine how the computer chooses to kickoff the ball (standard or onside)
	if (quarter == 4){
		if (winning)
			return "Kickoff";
		else if (timeLeft <= 180)
			return "Onside";
		else{
			int num = (int)(Math.random() * 100 + 1);
			if (num % 25 == 0)
				return "Onside";
			else
				return "Kickoff";
		}
	}
	else if (quarter == 2){
		if (!winning && timeLeft < 90)
			return "Onside";
		else
			return "Kickoff";
	}
	else
		return "Kickoff";

	
}

String selectOffensivePlay(){
	chooseOffensivePlay();	// Run, Pass, Kick
	chooseOffensivePlayAttribute();	// Run (left, right, mid), Pass (Long, Mid, Short, Screen / In,Out,Straight), Kick
	chooseIntendedReceiver();

	// Choose a regular offensive play.  Then, if below is true, set CPU to kick FG instead.
	if (isSpecialTeams){


		int rand = (int)(Math.random() * 100);

		if (rand % 11 != 0){
			playType = "S";
			playAtt = "F";
		}
	}



	String res = playType + "_" + playAtt + "_" + receiver;


	offensivePlay.setSideOfBall("O");
	offensivePlay.setType(playType);
	if (playType.equals("S")){
		if (playAtt == "F")
			offensivePlay.setName("Field_Goal");
		else if (playAtt == "P")
			offensivePlay.setName("Punt");
		offensivePlay.setSideOfBall("S");
		offensivePlay.setType("");
	}
	else if (playType.equals("K")){
		offensivePlay.setName("Kickoff");
		offensivePlay.setSideOfBall("S");
		offensivePlay.setType("");
	}

	


	offensivePlay.setNumberOfReceivers(1);
	offensivePlay.setPlayers(0,receiver);
	offensivePlay.setRoutes(0,playAtt + playAtt2);

	return res;
}

Play getOffensivePlay(){
	return offensivePlay;
}

Play getDefensivePlay(){
	return defensivePlay;
}

String selectDefensivePlay(){
	chooseDefensivePlayType();
	chooseDefensivePlayAttribute();

	defensivePlay.setSideOfBall("D");
	defensivePlay.setType(playAtt);

	return playAtt;
}


// Defensive decisions

void chooseDefensivePlayType(){
	switch(currentDown){
		case 1:{
			if (firstDownPasses > firstDownRuns)
				playType = "P";
			else if (firstDownRuns > firstDownPasses)
				playType = "R";
			else{
				int num = (int)(Math.random() * 100 + 1);
				if (num % 2 == 0)
					playType = "P";
				else
					playType = "R";
			}				
			break;
		}
		case 2:{
			if (secondDownPasses > secondDownRuns)
				playType = "P";
			else if (secondDownRuns > secondDownPasses)
				playType = "R";
			else{
				int num = (int)(Math.random() * 100 + 1);
				if (num % 2 == 0)
					playType = "P";
				else
					playType = "R";
			}				
			break;
		}
		case 3:{
			if (secondDownPasses > secondDownRuns)
				playType = "P";
			else if (secondDownRuns > secondDownPasses)
				playType = "R";
			else{
				int num = (int)(Math.random() * 100 + 1);
				if (num % 2 == 0)
					playType = "P";
				else
					playType = "R";
			}				
			break;

		}
		// 4th down
		case 4:{
			// If there are less than 3 minutes left in the game and are in the lead
			if (quarter == 4 && timeLeft < 180 && winning){
				// Pass Defense, Man Coverage
				playType = "P";
				playAtt = "M";
				break;
			}

			// If ball is at the 35 or closer and it is 4th and more than 2
			if (yardline >= 70 && yardsToGo > 2){
				// Field Goal block
				playType = "S";
				playAtt = "F";
			}
			// If ball is at the 35 or closer and it is 4th and 2 or fewer
			else if (yardline >= 70 && yardsToGo <= 2){
				// Run defense, Goal Line coverage
				playType = "R";
				playAtt = "G";
			}
			// If ball is behind the 35 of the defense and it is 4th and more than 2
			else if (yardline < 70 && yardsToGo > 2){
				// Punt return
				playType = "S";
				playAtt = "R";
			}
			else{
				// If it is 4th and 1 or 2, do run defense with goal line coverage
				playType = "R";
				playAtt = "G";
			}

			break;
		}
	}
}

void chooseDefensivePlayAttribute(){
//	if (playAtt == "")
//		return;

	// If ball is in the 5 yard line, do goal line coverage
	if (yardline > 95)
		playAtt = "B";
	// If offense has more than 10 yards to go for a first down, do zone coverage (for pass play)
	else if (playType == "P" && yardsToGo >= 10)
		playAtt = "Z";
	// If offense has 5 or fewer yards to go for a first down, do blitz (for run play)
	else if (playType == "R" && yardsToGo <= 5)
		playAtt = "B";
	// Otherwise, use man coverage
	else
		playAtt = "M";

}


void chooseOffensivePlay(){
	switch(currentDown){
		// 1st Down
		case 1:{
			// If in final 2:00 of either half, pass the ball on 1st if not in the lead
			if (timeLeft <= 120 && (quarter == 4 || quarter == 2) && !winning)
				playType = "P";

			// If in final 2:00 of either half, run the ball on 1st if not in the lead
			else if (timeLeft <= 120 && (quarter == 4 || quarter == 2) && winning)
				playType = "R";

			else if (timeLeft <= 30 && yardline >= 65 && yardline < 90 && (quarter == 4 || quarter == 2)){
				playType = "S";
				// If inside 35 yard line, kick field goal
				playAtt = "F";
			}
			else{
				// Else, randomly choose whether to pass or run (25% chance of choosing a pass)
				int choice = (int)(Math.random() * 20 + 1);
				if (choice % 3 == 0)
					playType = "P";
				else
					playType = "R";
			}
			break;
			}
		case 2:{
			// Pass if 2nd and 10+ OR team is not winning with < 2 minutes left in half
			if (yardsToGo > 10 || (timeLeft <= 120 && (quarter == 2 || quarter == 4) && !winning))
				playType = "P";

			// Run if 2nd and <5 or if 2:00 remain in the game and is winning the game
			else if (yardsToGo < 5 || (timeLeft <= 120 && quarter == 4 && winning))
				playType = "R";

			else if (timeLeft <= 30 && yardline >= 65 && yardline < 90 && (quarter == 4 || quarter == 2)){
				playType = "S";
				// If inside 35 yard line, kick field goal
				playAtt = "F";
			}

			else{
				// Else, randomly choose whether to pass or run (30% chance of choosing a pass)
				int choice = (int)(Math.random() * 20 + 1);
				if (choice % 2 == 0)
					playType = "P";
				else
					playType = "R";
			}
			break;
			}
		case 3:{
			// Pass if 3rd and 7+ OR team is not winning with < 2 minutes left in half
			if (yardsToGo >= 7 || (timeLeft <= 120 && (quarter == 2 || quarter == 4) && !winning))
				playType = "P";

			// Run if 3rd and < 4 or if 2:00 remain in the game and is winning the game
			else if (yardsToGo < 4 || (timeLeft <= 120 && quarter == 4 && winning))
				playType = "R";

			else if (timeLeft <= 30 && yardline >= 65 && yardline < 90 && (quarter == 4 || quarter == 2)){
				playType = "S";
				// If inside 35 yard line, kick field goal
				playAtt = "F";
			}
			else{
				// Else, randomly choose whether to pass or run (70% chance of choosing a pass)
				int choice = (int)(Math.random() * 20 + 1);
				if (choice % 2 != 0)
					playType = "P";
				else
					playType = "R";
			}

			break;
			}

		// 4th Down
		case 4:{
			// 1st, 3rd, OT rules
			if (quarter == 1 || quarter == 3 || quarter == 5){
				playType = "S";	// Special teams

				// If inside 40 yard line, kick field goal
				if (yardline >= 60)
					playAtt = "F";

				// If outside 40 yard line, punt
				else
					playAtt = "P";
			}
			// 2nd quarter rules
			else if (quarter == 2){
				// If less than 2 minutes left in 2nd quarter
				if (timeLeft <= 120){
					// If inside 40 and is 4th and > 5, kick field goal
					if (yardsToGo > 5 && yardline >= 60){
						playType = "S";
						playAtt = "F";
					}
					// If outside 40 and is 4th and > 5, punt ball
					else if (yardsToGo > 5 && yardline < 60){
						playType = "S";
						playAtt = "P";
					}

					// If 4th and < 5 and inside 40 yard line, kick field goal
					else if (yardsToGo <= 5 && (yardline >= 60)){
						playType = "S";
						playAtt = "F";
					}
					// If 4th and < 5 and outside 35, pass ball
					else
						playType = "S";
						playAtt = "P";
				}

				// If more than 2 minutes left in 2nd quarter
				else{
					playType ="S";
					// If inside 40, kick field goal
					if (yardline >= 60)
						playAtt = "F";
					// Else, punt ball
					else
						playAtt = "P";

				}
			}
			// 4th quarter rules
			else{
				// If less than 2 minutes left
				if (timeLeft <= 120){

					// If player is losing by 4-6 points, pass ball
					if ((playerScore - cpuScore) > 3 && (playerScore - cpuScore) < 7)
						playType = "P";

					// If 4th and 5+ inside 40, kick field goal
					else if (yardsToGo >= 5 && yardline >= 60){
						playType = "S";
						playAtt = "F";
					}
					// If 4th and 5+ outside the 40 and cpu is winning by 4-6 points, punt ball
					else if (yardsToGo >= 5 && yardline < 60 && (cpuScore - playerScore > 3 && cpuScore - playerScore < 7)){
						playType = "S";
						playAtt = "P";
					}

					// If 4th and <5 and inside 40 yd line, kick field goal
					else if (yardsToGo < 5 && yardline >= 60){
						playType = "S";
						playAtt = "F";
					}

					// If 4th and <5 outside 40, punt ball
					else{
						playType = "S";
						playAtt = "P";
					}
				}

				// If more than 2 minutes left
				else{
					playType = "S";

					// If inside 40, kick field goal
					if (yardline >= 60)
						playAtt = "F";

					// If outside 40, punt ball
					else
						playAtt = "P";

				}

			}
			break;
		}
	}
System.out.println(currentDown + "   " + quarter + "   " + playType);
}


void chooseOffensivePlayAttribute(){
	// If special teams, return
	if (playType == "S")
		return;

	// If pass play,
	if (playType == "P"){
		// Randomly choose distance of route
		int route = (int)(Math.random() * 20 + 1);

		// Screen pass
		if (route <= 5)
			playAtt = "Z";

		// Short pass
		else if (route <= 10)
			playAtt = "S";

		// Mid-range pass
		else if (route <= 15)
			playAtt = "M";

		// Long pass
		else
			playAtt = "L";

		// Set 2nd attribute for pass

		// If screen pass, set screen route
		if (playAtt == "Z")
			playAtt2 = "Z";
		else{
			// Randomly choose route direction
			int dir = (int)(Math.random() * 18 + 1);

			// In route
			if (dir <= 6)
				playAtt2 = "I";

			// Out route
			else if (dir <= 12)
				playAtt2 = "O";

			// Straight route
			else
				playAtt2 = "S";
		}
	} // end pass play

	// Run play
	else if (playType == "R"){
		// Randomly choose route direction
		int dir = (int)(Math.random() * 18 + 1);

		// Left run route
		if (dir <= 10)
			playAtt = "L";

		// Right run route
		else if (dir <= 20)
			playAtt = "R";

		// Middle run route
		else
			playAtt = "M";

	}// end run

} // end select attributes


void chooseIntendedReceiver(){
	// Special teams
	if (playType == "S"){
		// Punter punts ball
		if (playAtt == "P")
			receiver = "P";

		// Kicker kicks field goal
		else
			receiver = "K";
	}
	// Run play
	else if (playType == "R"){
		// Choose runner from RB, FB, QB (in order of frequency)
		int res = (int)(Math.random() * 45 + 1);

		// Quarterback has 4/45 (8.8%) chance of being runner
		if (res % 11 == 0)
			receiver = "QB";

		// Fullback has 7/45 (15.6%) chance of being runner
		else if (res % 6 == 0)
			receiver = "FB";

		// Runningback has 34/45 (75.6%) chance of being runner
		else
			receiver = "HB";
	}
	// Pass play
	else if (playType == "P"){
		// Choose receiver from WR1, WR2, WR3, TE, RB
		int res= (int)(Math.random() * 20 + 1);
		if (res % 5 == 0)
			receiver = "WR"; //1";
		else if (res % 5 == 1)
			receiver = "WR"; //2";
		else if (res % 5 == 2)
			receiver = "WR"; //3";
		else if (res % 5 == 3)
			receiver = "TE";
		else if (res % 5 == 4)
			receiver = "HB";
	}
//	System.out.println(receiver);

}// end choose intended receiver

String getIntendedReceiver(){
		return receiver;
}

}
