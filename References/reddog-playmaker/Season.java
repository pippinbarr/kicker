import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.Vector;
import java.util.HashMap;
import java.util.*;
import java.io.*;
import java.text.*;
import java.util.Calendar;

public class Season{

	String filename = "";
	boolean gameLoaded = false;
	boolean gameSaved = false;

	int currentWeek = 1;
	int numberOfWeeks;
	int numberOfTeams;

	// Number of teams per conference
	int maxPlayoffTeams = 0;

	boolean seasonComplete = false;
	boolean regularSeasonComplete = false;
	boolean playoffsSet = false;
	Vector<String> teams = new Vector<String>();

	// Associative Array to find team nicknames by team name
	Map<String,String> teamMap = new HashMap<String,String>();

//	String [] divisions = {"", "Atlantic", "Gulf", "Lakes", "Plains", "Mountain", "Pacific", "Eastern", "Western", "Central", "Midwest"};
	String [] divisions = {"", "Atlantic", "Gulf", "Lakes", "Central", "Midwest", "Plains", "Mountain", "Pacific", "Eastern", "Western"};
	Vector<Integer> teamDivisions = new Vector<Integer>();

	// Value[0] = Wins, Value[1] = Losses, Value[2] = Ties
	Vector<Integer[]> teamRecords = new Vector<Integer[]>();

	// Same layout as above, but for divisional records
	Vector<Integer[]> divisionRecords = new Vector<Integer[]>();

	// Same layout as above, but for conference records
	Vector<Integer[]> conferenceRecords = new Vector<Integer[]>();

	// Holds the divisional, conference, and playoff rankings for all teams
	Vector<Integer[]> rankings = new Vector<Integer[]>();

	Vector<Statistics> teamStats = new Vector<Statistics>();

	// Value = Team ID, Value[x] = Week (-1), Value[Week][0] = OpponentID, Value[Week][1] = Away/Home, Value[Week][2] = Not Played/Played, Value{Week][3] = Time of Game
	Vector<Integer[][]> schedule = new Vector<Integer[][]>();

	// Hold each week's date ([x][0] = Month, [x][1] = Day)
	int[][] seasonSchedule;
	int[][] playoffSchedule;

	int numPlayoffGames = 0;	// if (playoffRounds == 1) numPlayoffGames = 1; else numPlayoffGames = maxPlayoffTeams * 2 - 1;
	int playoffRounds = 0;
	int currentRound = 0;
	int [][] playoffScores;	// = new int[numPlayoffGames[4];	//0=A, 1=H, 2=0/1 3= Round
	String [][] playoffGames;	// = new String[numPlayoffGames][5]; // 0=A, 1=H, 2=When, 3=Away Rank, 4=Home Rank
	String champion = "NULL";

	String [] teamInWaiting = new String[2];
	int [] waitingTeamSeed = new int[2];

	// Value = Team ID, Value[x] = Week (-1), Value[Week][0] = Team's score, Value[Week][1] = Opponent's Score
	Vector<Integer[][]> scores = new Vector<Integer[][]>();

	JPanel standingsPanel;
	JPanel displayPanel;
	int yLocation = 0;

	public Season(Vector<String> teamList, Vector<String> divisionList){
		determineNumberOfWeeks(teamList.size());
		numberOfTeams = teamList.size();

		if (playoffRounds == 1)
			numPlayoffGames = 1;
		else
			numPlayoffGames = maxPlayoffTeams * 2 - 1;

		seasonSchedule = new int[numberOfWeeks][2];

		playoffScores = new int[numPlayoffGames][4];
		playoffGames = new String[numPlayoffGames][5];
		playoffSchedule = new int[playoffRounds][2];

		teamInWaiting[0] = "NULL";
		teamInWaiting[1] = "NULL";
		waitingTeamSeed[0] = 0;
		waitingTeamSeed[1] = 0;

		for (int i = 0; i < numPlayoffGames; i++){
			for (int j = 0; j < 4; j++)
				playoffScores[i][j] = 0;

			playoffGames[i][0] = "NULL";
			playoffGames[i][1] = "NULL";
			playoffGames[i][2] = "N:00";
			playoffGames[i][3] = "NA";
			playoffGames[i][4] = "NA";
		}

		for (int i = 0; i < playoffRounds; i++){
			playoffSchedule[i][0] = 0;
			playoffSchedule[i][1] = 0;
		}

		for (int i = 0; i < teamList.size(); i++){
			teams.addElement(teamList.elementAt(i));
			teamRecords.addElement(new Integer[3]);
			divisionRecords.addElement(new Integer[3]);
			conferenceRecords.addElement(new Integer[3]);
			rankings.addElement(new Integer[3]);

			schedule.addElement(new Integer[numberOfWeeks][4]);
			scores.addElement(new Integer[numberOfWeeks][2]);
			teamStats.addElement(new Statistics(true));
			teamStats.elementAt(i).initialize();

			for (int j = 0; j < 11; j++){
				if (divisionList.elementAt(i).equals(divisions[j]))
					teamDivisions.addElement(j);
			}
		}

		int monthStart = 7; // Actually 8 for August
		int dayStart = 16;

		initializeRecords();
		setTeamMap();
		setSeasonSchedule(monthStart, dayStart);
		setTeamSchedules();
	}

	public Season(int numberOfTeams){
		numberOfTeams = numberOfTeams;
		determineNumberOfWeeks(numberOfTeams);
	//	numberOfWeeks = 1;

		if (playoffRounds == 1)
			numPlayoffGames = 1;
		else
			numPlayoffGames = maxPlayoffTeams * 2 - 1;

		playoffScores = new int[numPlayoffGames][4];
		playoffGames = new String[numPlayoffGames][5];
		playoffSchedule = new int[playoffRounds][2];

		seasonSchedule = new int[numberOfWeeks][2];

		teamInWaiting[0] = "NULL";
		teamInWaiting[1] = "NULL";
		waitingTeamSeed[0] = 0;
		waitingTeamSeed[1] = 0;

		for (int i = 0; i < numPlayoffGames; i++){
			for (int j = 0; j < 4; j++)
				playoffScores[i][j] = 0;

			playoffGames[i][0] = "NULL";
			playoffGames[i][1] = "NULL";
			playoffGames[i][2] = "N:00";
			playoffGames[i][3] = "NA";
			playoffGames[i][4] = "NA";
		}

		for (int i = 0; i < playoffRounds; i++){
			playoffSchedule[i][0] = 0;
			playoffSchedule[i][1] = 0;
		}

		setTeams();


		for (int i = 0; i < numberOfTeams; i++){
			teamStats.addElement(new Statistics(true));
			teamStats.elementAt(i).initialize();

			teamRecords.addElement(new Integer[3]);
			divisionRecords.addElement(new Integer[3]);
			conferenceRecords.addElement(new Integer[3]);
			rankings.addElement(new Integer[3]);

			schedule.addElement(new Integer[numberOfWeeks][4]);
			scores.addElement(new Integer[numberOfWeeks][2]);

		}

		int monthStart = 7; // Actually 8 for August
		int dayStart = 16;

		initializeRecords();
		setTeamMap();
		setSeasonSchedule(monthStart, dayStart);
		setTeamSchedules();
	}

	public Season(){

		setTeamMap();
	}

	void initializeRecords(){

		for (int i = 0; i < numberOfTeams; i++){
			for (int j = 0; j < 3; j++){
				teamRecords.elementAt(i)[j] = 0;
				divisionRecords.elementAt(i)[j] = 0;
				conferenceRecords.elementAt(i)[j] = 0;
				rankings.elementAt(i)[j] = 0;
			}
		}
	}
	
	void setTeams(){
		switch(numberOfTeams){
			case 2:{
				teams.addElement("Portland");
				teams.addElement("Anchorage");

				teamDivisions.addElement(0);
				teamDivisions.addElement(0);
				break;}
			case 4:{
				teams.addElement("Portland");
				teams.addElement("Anchorage");
				teams.addElement("Dakota");
				teams.addElement("Richmond");

				teamDivisions.addElement(0);
				teamDivisions.addElement(0);
				teamDivisions.addElement(0);
				teamDivisions.addElement(0);
				break;}
			case 8:{
				teams.addElement("Portland");
				teams.addElement("Richmond");
				teams.addElement("Orlando");
				teams.addElement("San Antonio");

				teams.addElement("Dakota");
				teams.addElement("Albuquerque");
				teams.addElement("Anchorage");
				teams.addElement("Los Angeles");

				teamDivisions.addElement(9);
				teamDivisions.addElement(9);
				teamDivisions.addElement(9);
				teamDivisions.addElement(9);
				teamDivisions.addElement(10);
				teamDivisions.addElement(10);
				teamDivisions.addElement(10);
				teamDivisions.addElement(10);
				break;}
			case 16:{
				teams.addElement("Portland");
				teams.addElement("Richmond");
				teams.addElement("Delaware");
				teams.addElement("Orlando");
				teams.addElement("Biloxi");
				teams.addElement("Upper Michigan");
				teams.addElement("Iowa");
				teams.addElement("Birmingham");

				teams.addElement("Dakota");
				teams.addElement("Wichita");
				teams.addElement("Albuquerque");
				teams.addElement("San Antonio");
				teams.addElement("Anchorage");
				teams.addElement("Boise");
				teams.addElement("Hawaii");
				teams.addElement("Los Angeles");

				teamDivisions.addElement(1);
				teamDivisions.addElement(1);
				teamDivisions.addElement(1);
				teamDivisions.addElement(1);
				teamDivisions.addElement(4);
				teamDivisions.addElement(4);
				teamDivisions.addElement(4);
				teamDivisions.addElement(4);
				teamDivisions.addElement(5);
				teamDivisions.addElement(5);
				teamDivisions.addElement(5);
				teamDivisions.addElement(5);
				teamDivisions.addElement(8);
				teamDivisions.addElement(8);
				teamDivisions.addElement(8);
				teamDivisions.addElement(8);
				break;}
			case 20:{
				teams.addElement("Portland");
				teams.addElement("Rhode Island");
				teams.addElement("Richmond");
				teams.addElement("Delaware");
				teams.addElement("Orlando");

				teams.addElement("Biloxi");
				teams.addElement("Upper Michigan");
				teams.addElement("Iowa");
				teams.addElement("Dayton");
				teams.addElement("Birmingham");

				teams.addElement("Dakota");
				teams.addElement("Wichita");
				teams.addElement("Tulsa");
				teams.addElement("Albuquerque");
				teams.addElement("San Antonio");

				teams.addElement("Anchorage");
				teams.addElement("Boise");
				teams.addElement("Tacoma");
				teams.addElement("Hawaii");
				teams.addElement("Los Angeles");

				teamDivisions.addElement(1);
				teamDivisions.addElement(1);
				teamDivisions.addElement(1);
				teamDivisions.addElement(1);
				teamDivisions.addElement(1);
				teamDivisions.addElement(4);
				teamDivisions.addElement(4);
				teamDivisions.addElement(4);
				teamDivisions.addElement(4);
				teamDivisions.addElement(4);
				teamDivisions.addElement(5);
				teamDivisions.addElement(5);
				teamDivisions.addElement(5);
				teamDivisions.addElement(5);
				teamDivisions.addElement(5);
				teamDivisions.addElement(8);
				teamDivisions.addElement(8);
				teamDivisions.addElement(8);
				teamDivisions.addElement(8);
				teamDivisions.addElement(8);

				break;}
			case 24:{
				teams.addElement("Portland");
				teams.addElement("Rhode Island");
				teams.addElement("Richmond");
				teams.addElement("Delaware");

				teams.addElement("Orlando");
				teams.addElement("Biloxi");
				teams.addElement("Birmingham");
				teams.addElement("San Antonio");

				teams.addElement("Upper Michigan");
				teams.addElement("Iowa");
				teams.addElement("Dayton");
				teams.addElement("London");

				teams.addElement("Dakota");
				teams.addElement("Wichita");
				teams.addElement("Tulsa");
				teams.addElement("Albuquerque");

				teams.addElement("Boise");
				teams.addElement("Wyoming");
				teams.addElement("Salt Lake");
				teams.addElement("Montana");

				teams.addElement("Anchorage");
				teams.addElement("Tacoma");
				teams.addElement("Hawaii");
				teams.addElement("Los Angeles");

				teamDivisions.addElement(1);
				teamDivisions.addElement(1);
				teamDivisions.addElement(1);
				teamDivisions.addElement(1);
				teamDivisions.addElement(2);
				teamDivisions.addElement(2);
				teamDivisions.addElement(2);
				teamDivisions.addElement(2);
				teamDivisions.addElement(3);
				teamDivisions.addElement(3);
				teamDivisions.addElement(3);
				teamDivisions.addElement(3);
				teamDivisions.addElement(5);
				teamDivisions.addElement(5);
				teamDivisions.addElement(5);
				teamDivisions.addElement(5);
				teamDivisions.addElement(6);
				teamDivisions.addElement(6);
				teamDivisions.addElement(6);
				teamDivisions.addElement(6);
				teamDivisions.addElement(7);
				teamDivisions.addElement(7);
				teamDivisions.addElement(7);
				teamDivisions.addElement(7);

				break;}

			case 30:{
				teams.addElement("Portland");
				teams.addElement("Rhode Island");
				teams.addElement("Richmond");
				teams.addElement("Delaware");
				teams.addElement("Lancaster");

				teams.addElement("Orlando");
				teams.addElement("Biloxi");
				teams.addElement("Birmingham");
				teams.addElement("San Antonio");
				teams.addElement("Arkansas");

				teams.addElement("Upper Michigan");
				teams.addElement("Iowa");
				teams.addElement("Dayton");
				teams.addElement("London");
				teams.addElement("Charleston");

				teams.addElement("Dakota");
				teams.addElement("Wichita");
				teams.addElement("Tulsa");
				teams.addElement("Albuquerque");
				teams.addElement("Amarillo");

				teams.addElement("Boise");
				teams.addElement("Wyoming");
				teams.addElement("Salt Lake");
				teams.addElement("Montana");
				teams.addElement("Tahoe");

				teams.addElement("Anchorage");
				teams.addElement("Tacoma");
				teams.addElement("Hawaii");
				teams.addElement("Los Angeles");
				teams.addElement("Oregon");

				teamDivisions.addElement(1);
				teamDivisions.addElement(1);
				teamDivisions.addElement(1);
				teamDivisions.addElement(1);
				teamDivisions.addElement(1);
				teamDivisions.addElement(2);
				teamDivisions.addElement(2);
				teamDivisions.addElement(2);
				teamDivisions.addElement(2);
				teamDivisions.addElement(2);
				teamDivisions.addElement(3);
				teamDivisions.addElement(3);
				teamDivisions.addElement(3);
				teamDivisions.addElement(3);
				teamDivisions.addElement(3);
				teamDivisions.addElement(5);
				teamDivisions.addElement(5);
				teamDivisions.addElement(5);
				teamDivisions.addElement(5);
				teamDivisions.addElement(5);
				teamDivisions.addElement(6);
				teamDivisions.addElement(6);
				teamDivisions.addElement(6);
				teamDivisions.addElement(6);
				teamDivisions.addElement(6);
				teamDivisions.addElement(7);
				teamDivisions.addElement(7);
				teamDivisions.addElement(7);
				teamDivisions.addElement(7);
				teamDivisions.addElement(7);

				break;}
		}
	}

	void setTeamMap(){

		// How to put them in
		teamMap.put("Portland", "BlueJays");
		teamMap.put("Rhode Island", "Griffins");
		teamMap.put("Delaware", "Patriots");
		teamMap.put("Lancaster", "Reds");
		teamMap.put("Richmond", "Marines");
		teamMap.put("Orlando", "Shrikes");
		teamMap.put("Biloxi", "Firebirds");
		teamMap.put("Birmingham", "Ironmen");
		teamMap.put("Arkansas", "Bucks");
		teamMap.put("San Antonio", "Hornets");
		teamMap.put("Upper Michigan", "Rabbits");
		teamMap.put("London", "Thunder");
		teamMap.put("Dayton", "Skyhawks");
		teamMap.put("Iowa", "Miners");
		teamMap.put("Charleston", "Cardinals");
		teamMap.put("Dakota", "Cougars");
		teamMap.put("Wichita", "Jets");
		teamMap.put("Tulsa", "RoadRunners");
		teamMap.put("Amarillo", "Coyotes");
		teamMap.put("Albuquerque", "Trailblazers");
		teamMap.put("Wyoming", "Oilers");
		teamMap.put("Montana", "Wizards");
		teamMap.put("Boise", "Pioneers");
		teamMap.put("Salt Lake", "Owls");
		teamMap.put("Tahoe", "Beavers");
		teamMap.put("Anchorage", "Eskimos");
		teamMap.put("Tacoma", "Aces");
		teamMap.put("Oregon", "Atoms");
		teamMap.put("Los Angeles", "Olympians");
		teamMap.put("Hawaii", "Royals");


		// How to get them out
	//	String result = teamMap.get("Portland");

	}

	String getNickname(String name){
		return teamMap.get(name);
	}

	void determineNumberOfWeeks(int numberOfTeams){
		switch(numberOfTeams){
			case 2:{ numberOfWeeks = 2; maxPlayoffTeams = 2; playoffRounds = 1; break;}
			case 4:{ numberOfWeeks = 6; maxPlayoffTeams = 2; playoffRounds = 1; break;}
			case 8:{ numberOfWeeks = 8; maxPlayoffTeams = 2; playoffRounds = 2; break;}
			case 16:{ numberOfWeeks = 13; maxPlayoffTeams = 4; playoffRounds = 3; break;}
			case 20:{ numberOfWeeks = 14; maxPlayoffTeams = 4; playoffRounds = 3; break;}
			case 24:{ numberOfWeeks = 14; maxPlayoffTeams = 6; playoffRounds = 4; break;}
			case 28:{ numberOfWeeks = 15; maxPlayoffTeams = 6; playoffRounds = 4; break;}
			case 30:{ numberOfWeeks = 17; maxPlayoffTeams = 6; playoffRounds = 4; break;}
		}

		if (playoffRounds == 1)
			numPlayoffGames = 1;
		else
			numPlayoffGames = maxPlayoffTeams * 2 - 1;

		playoffScores = new int[numPlayoffGames][4];
		playoffGames = new String[numPlayoffGames][5];
		playoffSchedule = new int[playoffRounds][2];

		teamInWaiting[0] = "NULL";
		teamInWaiting[1] = "NULL";
		waitingTeamSeed[0] = 0;
		waitingTeamSeed[1] = 0;

		for (int i = 0; i < numPlayoffGames; i++){
			for (int j = 0; j < 4; j++)
				playoffScores[i][j] = 0;

			playoffGames[i][0] = "NULL";
			playoffGames[i][1] = "NULL";
			playoffGames[i][2] = "N:00";
			playoffGames[i][3] = "NA";
			playoffGames[i][4] = "NA";
		}

		for (int i = 0; i < playoffRounds; i++){
			playoffSchedule[i][0] = 0;
			playoffSchedule[i][1] = 0;
		}

	}

	int getCurrentWeek(){
		return currentWeek;
	}

	int getCurrentRoundNum(){
		return currentRound;
	}

	int getNumberOfWeeks(){
		return numberOfWeeks;
	}


	boolean isChampionshipGame(){
		return (playoffRounds - currentRound == 1 && playoffsSet);
	}

	String getCurrentRound(){
		System.out.println(playoffRounds + " " + currentRound);
		if (playoffRounds - currentRound == 1)
			return "Championship";
		else if (playoffRounds - currentRound == 2)
			if (maxPlayoffTeams < 4)
				return "Semi-Finals";
			else
				return "Conference Championship";
		else if (playoffRounds - currentRound == 3)
			return "Divisional Round";
		else
			return "Wildcard Round";
	}

	String getCurrentRound(int round){
		if (playoffRounds - round == 1)
			return "Championship";
		else if (playoffRounds - round == 2)
			if (maxPlayoffTeams < 4)
				return "Semi-Finals";
			else
				return "Conference Championship";
		else if (playoffRounds - round == 3)
			return "Divisional Round";
		else
			return "Wildcard Round";
	}

	String getPlayoffRoundShort(int roundNum, int maxRounds){
		if (maxRounds - roundNum == 1)
			return "F";
		else if (maxRounds - roundNum == 2)
			if (maxPlayoffTeams < 4)
				return "SF";
			else
				return "CC";
		else if (maxRounds - roundNum == 3)
			return "DV";
		else
			return "WC";
	}

	int getNumberOfTeams(){
		return numberOfTeams;
	}

	String getChampion(){
		return champion;
	}

	void setSeasonSchedule(int monthStart, int dayStart){
		Calendar calendar = Calendar.getInstance();
		calendar.set(2011,monthStart,dayStart);

		for(int i = 0; i < numberOfWeeks; i++){
			seasonSchedule[i][0] = calendar.get(Calendar.MONTH);
			seasonSchedule[i][1] = calendar.get(Calendar.DATE);

			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 7);
		}

		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 7);
    
		for (int i = 0; i < playoffRounds; i++){
			playoffSchedule[i][0] = calendar.get(Calendar.MONTH);
			playoffSchedule[i][1] = calendar.get(Calendar.DATE);

			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 7);
		}

		// Set each game's time to play
		// Randomly assigns a time to play each game in the season
		// Times do not have to match between competing teams, as just the home team's information will be gathered.
		for (int i = 0; i < numberOfWeeks; i++){
			for (int j = 0; j < numberOfTeams; j++){
				schedule.elementAt(j)[i][3] = (int)(Math.random() * 10 + 10);
			}
		}

		for (int i = 0; i < numPlayoffGames; i++){
			playoffGames[i][2] = (int)(Math.random() * 4 + 16) + ":00";
		}
	}

	Calendar getGameday(){
		Calendar calendar = Calendar.getInstance();

		if (regularSeasonComplete)
			if (playoffSchedule[currentRound][0] < 3)
				calendar.set(2012, playoffSchedule[currentRound][0], playoffSchedule[currentRound][1]);
			else
				calendar.set(2011, playoffSchedule[currentRound][0], playoffSchedule[currentRound][1]);
		else
			calendar.set(2011, seasonSchedule[currentWeek-1][0], seasonSchedule[currentWeek-1][1]);

		return calendar;
	}

	void setTeamSchedules(){

		try{

			boolean correctNumber = false;
			java.util.Scanner in = new java.util.Scanner(getClass().getResourceAsStream("ScheduleAlgorithm.pmd"));

			do{
				int firstValue = in.nextInt();

				if (firstValue == numberOfTeams){
					correctNumber = true;

					int numberOfWeeks = in.nextInt();


					for (int i = 0; i < numberOfTeams; i++){
						for (int j = 0; j < numberOfWeeks; j++){
							schedule.elementAt(i)[j][0] = in.nextInt();
							schedule.elementAt(i)[j][1] = in.nextInt();
							scores.elementAt(i)[j][0] = 0;
							scores.elementAt(i)[j][1] = 0;

							// If the team has a BYE, set the game as being played
							if (schedule.elementAt(i)[j][0] == -1)
								schedule.elementAt(i)[j][2] = 1;
							else
								schedule.elementAt(i)[j][2] = 0;

						}
					}
					in.close();
					break;
				}
				else{
					int cycles = in.nextInt();
					for (int i = 0; i < (2 * firstValue * cycles); i++){
						int filler = in.nextInt();
					}
				}
			}while(!correctNumber);

			in.close();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	void setPlayoffSchedule(){
		for (int i = 0; i < numPlayoffGames; i++){
			playoffGames[i][2] = (int)(Math.random() * 4 + 16) + ":00";
		}

		switch (maxPlayoffTeams){
			case 2:{
				if (numberOfTeams == 2){
					if(rankings.elementAt(0)[2] == 1){
						playoffGames[0][1] = teams.elementAt(0);
						playoffGames[0][0] = teams.elementAt(1);
						playoffGames[0][3] = String.valueOf(2);
						playoffGames[0][4] = String.valueOf(1);
					}
					else{
						playoffGames[0][1] = teams.elementAt(1);
						playoffGames[0][0] = teams.elementAt(0);
						playoffGames[0][3] = String.valueOf(1);
						playoffGames[0][4] = String.valueOf(2);
					}
					playoffScores[0][3] = 0;
				}
				else if (numberOfTeams == 4){
			//		System.out.println("In");
					for (int i = 0; i < numberOfTeams; i++){
						if (rankings.elementAt(i)[1] == 1){
							playoffGames[0][1] = teams.elementAt(i);
							playoffGames[0][4] = String.valueOf(1);
						}
						else if (rankings.elementAt(i)[1] == 2){
							playoffGames[0][0] = teams.elementAt(i);
							playoffGames[0][3] = String.valueOf(2);
						}
					}

					playoffScores[0][3] = 0;
				//	System.out.println(playoffGames[0][0] + " at " + playoffGames[0][1]);
				}
				else{
					for (int i = 0; i < numberOfTeams / 2; i++){
						if (rankings.elementAt(i)[1] == 1){
							playoffGames[0][1] = teams.elementAt(i);
							playoffGames[0][4] = String.valueOf(1);
						}
						else if (rankings.elementAt(i)[1] == 2){
							playoffGames[0][0] = teams.elementAt(i);
							playoffGames[0][3] = String.valueOf(2);
						}
					}
					for (int i = numberOfTeams / 2; i < numberOfTeams; i++){
						if (rankings.elementAt(i)[1] == 1){
							playoffGames[1][1] = teams.elementAt(i);
							playoffGames[1][4] = String.valueOf(1);
						}
						else if (rankings.elementAt(i)[1] == 2){
							playoffGames[1][0] = teams.elementAt(i);
							playoffGames[1][3] = String.valueOf(2);
						}
					}
					playoffScores[0][3] = 0;
					playoffScores[1][3] = 0;
					playoffScores[2][3] = 1;
				}
				break;
			}
			case 4:{
				int ranking = 0;
				int rankingID = 0;
				for (int i = 0; i < numberOfTeams / 2; i++){
					if (rankings.elementAt(i)[1] == 1){
						playoffGames[0][1] = teams.elementAt(i);
						playoffGames[0][4] = String.valueOf(1);
					}
					else if (rankings.elementAt(i)[2] == 2){
						playoffGames[1][1] = teams.elementAt(i);
						playoffGames[1][4] = String.valueOf(2);
					}
					else if (rankings.elementAt(i)[2] == 1){
						if (rankings.elementAt(i)[1] > ranking){
							if (ranking == 0){
								ranking = rankings.elementAt(i)[1];
								rankingID = i;
							}
							else{
								playoffGames[0][0] = teams.elementAt(i);
								playoffGames[0][3] = String.valueOf(4);
								playoffGames[1][0] = teams.elementAt(rankingID);
								playoffGames[1][3] = String.valueOf(3);
							}
						}
						else{
							playoffGames[1][0] = teams.elementAt(i);
							playoffGames[1][3] = String.valueOf(3);
							playoffGames[0][0] = teams.elementAt(rankingID);
							playoffGames[0][3] = String.valueOf(4);
						}
					}	
				}
				ranking = 0;
				rankingID = 0;
				for (int i = numberOfTeams / 2; i < numberOfTeams; i++){
					if (rankings.elementAt(i)[1] == 1){
						playoffGames[2][1] = teams.elementAt(i);
						playoffGames[2][4] = String.valueOf(1);
					}
					else if (rankings.elementAt(i)[2] == 2){
						playoffGames[3][1] = teams.elementAt(i);
						playoffGames[3][4] = String.valueOf(2);
					}
					else if (rankings.elementAt(i)[2] == 1){
						if (rankings.elementAt(i)[1] > ranking){
							if (ranking == 0){
								ranking = rankings.elementAt(i)[1];
								rankingID = i;
							}
							else{
								playoffGames[2][0] = teams.elementAt(i);
								playoffGames[2][3] = String.valueOf(4);
								playoffGames[3][0] = teams.elementAt(rankingID);
								playoffGames[3][3] = String.valueOf(3);
							}
						}
						else{
							playoffGames[3][0] = teams.elementAt(i);
							playoffGames[3][3] = String.valueOf(3);
							playoffGames[2][0] = teams.elementAt(rankingID);
							playoffGames[2][3] = String.valueOf(4);
						}
					}	
				}
				for (int i = 0; i < 4; i++)
					playoffScores[i][3] = 0;

				playoffScores[4][3] = 1;
				playoffScores[5][3] = 1;
				playoffScores[6][3] = 2;

				break;
			}
			case 6:{
				int[] ranking = new int[3];
				int[] rankingID = new int[3];
				ranking[0] = 0;
				ranking[1] = 0;
				ranking[2] = 0;
				rankingID[0] = 0;
				rankingID[1] = 0;
				rankingID[2] = 0;
				for (int i = 0; i < numberOfTeams / 2; i++){
					if (rankings.elementAt(i)[1] == 1){
						playoffGames[4][1] = teams.elementAt(i);	// May need to be switched
						playoffGames[4][4] = String.valueOf(1);
					//	System.out.println("#1 seed " + teams.elementAt(i));
					}
					else if (rankings.elementAt(i)[2] == 3 && rankings.elementAt(i)[1] != 1){
						playoffGames[5][1] = teams.elementAt(i);	// May need to be switched
						playoffGames[5][4] = String.valueOf(2);
					//	System.out.println("#2 seed " + teams.elementAt(i));
					}
					else if (rankings.elementAt(i)[2] == 2){
						playoffGames[0][1] = teams.elementAt(i);
						playoffGames[0][4] = String.valueOf(3);
					//	System.out.println("#3 seed " + teams.elementAt(i));
					}
					else if (rankings.elementAt(i)[2] == 1){
						if (ranking[0] == 0){
							ranking[0] = rankings.elementAt(i)[1];
							rankingID[0] = i;
						}
						else if (ranking[1] == 0){
							if (ranking[0] > rankings.elementAt(i)[1]){
								ranking[1] = ranking[0];
								rankingID[1] = rankingID[0];
								ranking[0] = rankings.elementAt(i)[1];
								rankingID[0] = i;
							}
							else{
								ranking[1] = rankings.elementAt(i)[1];
								rankingID[1] = i;
							}
						}
						else{
							if (ranking[0] > rankings.elementAt(i)[1]){
								ranking[2] = ranking[1];
								rankingID[2] = rankingID[1];
								ranking[1] = ranking[0];
								rankingID[1] = rankingID[0];
								ranking[0] = rankings.elementAt(i)[1];
								rankingID[0] = i;
							}
							else if (ranking[1] > rankings.elementAt(i)[1]){
								ranking[2] = ranking[1];
								rankingID[2] = rankingID[1];
								ranking[1] = rankings.elementAt(i)[1];
								rankingID[1] = i;
							}
							else{
								ranking[2] = rankings.elementAt(i)[1];
								rankingID[2] = i;
							}
							playoffGames[0][0] = teams.elementAt(rankingID[2]);
							playoffGames[0][3] = String.valueOf(6);
							playoffGames[1][0] = teams.elementAt(rankingID[1]);
							playoffGames[1][3] = String.valueOf(5);
							playoffGames[1][1] = teams.elementAt(rankingID[0]);
							playoffGames[1][4] = String.valueOf(4);

						//	System.out.println("#4 seed" + teams.elementAt(rankingID[0]));
						//	System.out.println("#5 seed" + teams.elementAt(rankingID[1]));
						//	System.out.println("#6 seed" + teams.elementAt(rankingID[2]));
						}
					}
				}
				ranking[0] = 0;
				ranking[1] = 0;
				ranking[2] = 0;
				rankingID[0] = 0;
				rankingID[1] = 0;
				rankingID[2] = 0;
				for (int i = numberOfTeams / 2; i < numberOfTeams; i++){
					if (rankings.elementAt(i)[1] == 1){
						playoffGames[6][1] = teams.elementAt(i);	// May need to be switched
						playoffGames[6][4] = String.valueOf(1);
					//	System.out.println("#1 seed " + teams.elementAt(i));
					}
					else if (rankings.elementAt(i)[2] == 3 && rankings.elementAt(i)[1] != 1){
						playoffGames[7][1] = teams.elementAt(i);	// May need to be switched
						playoffGames[7][4] = String.valueOf(2);
					//	System.out.println("#2 seed " + teams.elementAt(i));
					}
					else if (rankings.elementAt(i)[2] == 2){
						playoffGames[2][1] = teams.elementAt(i);
						playoffGames[2][4] = String.valueOf(3);
					//	System.out.println("#3 seed " + teams.elementAt(i));
					}
					else if (rankings.elementAt(i)[2] == 1){
						if (ranking[0] == 0){
							ranking[0] = rankings.elementAt(i)[1];
							rankingID[0] = i;
						}
						else if (ranking[1] == 0){
							if (ranking[0] > rankings.elementAt(i)[1]){
								ranking[1] = ranking[0];
								rankingID[1] = rankingID[0];
								ranking[0] = rankings.elementAt(i)[1];
								rankingID[0] = i;
							}
							else{
								ranking[1] = rankings.elementAt(i)[1];
								rankingID[1] = i;
							}
						}
						else{
							if (ranking[0] > rankings.elementAt(i)[1]){
								ranking[2] = ranking[1];
								rankingID[2] = rankingID[1];
								ranking[1] = ranking[0];
								rankingID[1] = rankingID[0];
								ranking[0] = rankings.elementAt(i)[1];
								rankingID[0] = i;
							}
							else if (ranking[1] > rankings.elementAt(i)[1]){
								ranking[2] = ranking[1];
								rankingID[2] = rankingID[1];
								ranking[1] = rankings.elementAt(i)[1];
								rankingID[1] = i;
							}
							else{
								ranking[2] = rankings.elementAt(i)[1];
								rankingID[2] = i;
							}
							playoffGames[2][0] = teams.elementAt(rankingID[2]);
							playoffGames[2][3] = String.valueOf(6);
							playoffGames[3][0] = teams.elementAt(rankingID[1]);
							playoffGames[3][3] = String.valueOf(5);
							playoffGames[3][1] = teams.elementAt(rankingID[0]);
							playoffGames[3][4] = String.valueOf(4);

						//	System.out.println("#4 seed" + teams.elementAt(rankingID[0]));
						//	System.out.println("#5 seed" + teams.elementAt(rankingID[1]));
						//	System.out.println("#6 seed" + teams.elementAt(rankingID[2]));
						}
					}
				}
				for (int i = 0; i < 4; i++)
					playoffScores[i][3] = 0;

				for (int i = 4; i < 8; i++)
					playoffScores[i][3] = 1;

				playoffScores[8][3] = 2;
				playoffScores[9][3] = 2;
				playoffScores[10][3] = 3;

				break;
			}
		}
	}

	Vector<Vector<Object>> getPlayoffSchedule(int round){
		Vector<Vector<Object>> playoffSchedule = new Vector<Vector<Object>>();
		playoffSchedule.clear();

		if (seasonComplete)
			round--;

		for (int i = 0; i < numPlayoffGames; i++){
			if (playoffScores[i][3] == round){
				Object [] tmp = {"", playoffGames[i][0], playoffGames[i][1], "", playoffGames[i][2]};

				if (playoffScores[i][2] == 1){
					tmp[0] = String.valueOf(playoffScores[i][0]);
					tmp[3] = String.valueOf(playoffScores[i][1]);
				}

				Vector<Object> tmp2 = new Vector<Object>();
				for (int j = 0; j < 5; j++)
					tmp2.addElement(tmp[j]);

				playoffSchedule.add(tmp2);
			}
		}

		return playoffSchedule;
	}

	Vector<Vector<Object>> getWeekSchedule(int week){
		Vector<Vector<Object>> weekSchedule = new Vector<Vector<Object>>();
		weekSchedule.clear();

		if (regularSeasonComplete && week > numberOfWeeks )
			return getPlayoffSchedule(currentRound);
				

		week--;

		for (int i = 0; i < teams.size(); i++){
			// If the current week's schedule is home
			if (schedule.elementAt(i)[week][1] == 1 && schedule.elementAt(i)[week][0] != -1){

				Object [] tmp = {"", teams.elementAt(schedule.elementAt(i)[week][0]), teams.elementAt(i), "", schedule.elementAt(i)[week][3] + ":00"};

				// If game has already been played, retrieve the scores
				if (schedule.elementAt(i)[week][2] == 1){
					tmp[0] = String.valueOf(scores.elementAt(i)[week][1]);
					tmp[3] = String.valueOf(scores.elementAt(i)[week][0]);
				}

				Vector<Object> tmp2 = new Vector<Object>();
				for (int j = 0; j < 5; j++)
					tmp2.addElement(tmp[j]);

				weekSchedule.addElement(tmp2);
			}
		}

		return weekSchedule;
	}

	JPanel getTeamSchedules(){
		final JPanel teamSchedule = new JPanel();
		teamSchedule.setLayout(null);
	//	teamSchedule.setOpaque(false);
		teamSchedule.setBackground(Color.BLACK);
		final int playoffGamesIn;
		int tmp = 0;

		final JComboBox<String> teamListing = new JComboBox<String>();

		for (int i = 0; i < numberOfTeams; i++)
			teamListing.addItem(teams.elementAt(i));

		for (int i = 0; i < playoffRounds; i++){
			if (playoffGames[i][0].equals(teams.elementAt(0)) || playoffGames[i][1].equals(teams.elementAt(0)))
				tmp++;
		}
		playoffGamesIn = tmp;

		final JLabel [] weekNumber = new JLabel[numberOfWeeks + playoffRounds];
		final JLabel [] homeAway = new JLabel[numberOfWeeks + playoffRounds];
		final JLabel [] opponents = new JLabel[numberOfWeeks + playoffRounds];
		final JLabel [] winLossTie = new JLabel[numberOfWeeks + playoffRounds];
		final JLabel [] gameScore = new JLabel[numberOfWeeks + playoffRounds];

		for (int i = 0; i < numberOfWeeks; i++){
			weekNumber[i] = new JLabel(String.valueOf(i + 1));
			homeAway[i] = new JLabel();
			opponents[i] = new JLabel();
			winLossTie[i] = new JLabel();
			gameScore[i] = new JLabel();


			weekNumber[i].setForeground(Color.WHITE);
			homeAway[i].setForeground(Color.WHITE);
			opponents[i].setForeground(Color.WHITE);
			winLossTie[i].setForeground(Color.WHITE);
			gameScore[i].setForeground(Color.WHITE);
		}

		for(int i = 0; i < playoffRounds; i++){
			weekNumber[i+numberOfWeeks] = new JLabel(getPlayoffRoundShort(i,playoffRounds));  // New function
			homeAway[i+numberOfWeeks] = new JLabel();
			opponents[i+numberOfWeeks] = new JLabel();
			winLossTie[i+numberOfWeeks] = new JLabel();
			gameScore[i+numberOfWeeks] = new JLabel();


			weekNumber[i+numberOfWeeks].setForeground(Color.WHITE);
			homeAway[i+numberOfWeeks].setForeground(Color.WHITE);
			opponents[i+numberOfWeeks].setForeground(Color.WHITE);
			winLossTie[i+numberOfWeeks].setForeground(Color.WHITE);
			gameScore[i+numberOfWeeks].setForeground(Color.WHITE);
		}

		for (int i = 0; i < numberOfWeeks; i++){
			weekNumber[i].setBounds(5,30 + (i * 35), 25, 30);

			// If the current game is a HOME game...
			if (schedule.elementAt(0)[i][1] == 1)
				homeAway[i].setText("vs");
			else
				homeAway[i].setText("at");

			homeAway[i].setBounds(45,30 + (i * 35), 25,30);

			// Insert the opposing team's sliver onto the panel (or display BYE otherwise)
			if (schedule.elementAt(0)[i][0] == -1){
				opponents[i].setText("BYE");
				homeAway[i].setText("");
			}
			else
				opponents[i].setIcon(new ImageIcon(getClass().getResource("images/slivers/" + teamMap.get(teams.elementAt(schedule.elementAt(0)[i][0])) + "_Sliver.png")) );

			opponents[i].setBounds(80,30 + (i * 35), 150,30);

			teamSchedule.add(weekNumber[i]);
			teamSchedule.add(homeAway[i]);
			teamSchedule.add(opponents[i]);


			// If the game has been played
			if (schedule.elementAt(0)[i][2] == 1 && schedule.elementAt(0)[i][0] != -1){
				// If the team WON its game
				if (scores.elementAt(0)[i][0] > scores.elementAt(0)[i][1])
					winLossTie[i].setText("W");
				else if (scores.elementAt(0)[i][0] < scores.elementAt(0)[i][1])
					winLossTie[i].setText("L");
				else
					winLossTie[i].setText("T");


				gameScore[i].setText(String.valueOf(scores.elementAt(0)[i][0]) + "-" + String.valueOf(scores.elementAt(0)[i][1]));

				winLossTie[i].setBounds(250, 30 + (i * 35), 20,30);
				gameScore[i].setBounds(285,30 + (i * 35), 100,30);

				teamSchedule.add(winLossTie[i]);
				teamSchedule.add(gameScore[i]);
			}
		}

		boolean firstGameFound = false;
		int tmp2 = playoffGamesIn;
		for (int i = numberOfWeeks; i < numberOfWeeks + tmp2; i++){
			weekNumber[i].setBounds(5,30 + (i * 35), 25, 30);
			homeAway[i].setBounds(45,30 + (i * 35), 25,30);
			opponents[i].setBounds(80,30 + (i * 35), 150, 30);
			int side = 0;

			for (int j = 0; j < numPlayoffGames; j++){
				if (playoffScores[j][3] == i - numberOfWeeks){
					boolean found = false;
					if (playoffGames[j][1].equals(teams.elementAt(0))){
						if (i - numberOfWeeks == 0)
							firstGameFound = true;
						found = true;
						side = 1;
						homeAway[i].setText("vs");
						if (playoffGames[j][0].equals("NULL"))
							opponents[i].setIcon(new ImageIcon(getClass().getResource("images/slivers/TBD_Sliver.png")));
						else
							opponents[i].setIcon(new ImageIcon(getClass().getResource("images/slivers/" + teamMap.get(playoffGames[j][0]) + "_Sliver.png")));
					}
					else if (playoffGames[j][0].equals(teams.elementAt(0))){
						if (i - numberOfWeeks == 0)
							firstGameFound = true;
						found = true;
						side = 0;
						homeAway[i].setText("at");
						opponents[i].setIcon(new ImageIcon(getClass().getResource("images/slivers/" + teamMap.get(playoffGames[j][1]) + "_Sliver.png")));
					}

					if (found){
						teamSchedule.add(weekNumber[i]);
						teamSchedule.add(homeAway[i]);
						teamSchedule.add(opponents[i]);

						if (playoffScores[j][2] == 1){
							if (playoffScores[j][side] > playoffScores[j][(side + 1)%2])
								winLossTie[i].setText("W");
							else
								winLossTie[i].setText("L");

							gameScore[i].setText(String.valueOf(playoffScores[j][side]) + "-" + String.valueOf(playoffScores[j][(side + 1)%2]) );

							winLossTie[i].setBounds(250, 30 + (i * 35), 20,30);
							gameScore[i].setBounds(285,30 + (i * 35), 100,30);

							teamSchedule.add(winLossTie[i]);
							teamSchedule.add(gameScore[i]);

						}
					}
				}
			}
				if (i - numberOfWeeks == 0 && !firstGameFound)
					tmp2++;

		}


		teamListing.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				for (int i = 0; i < numberOfWeeks; i++){
					homeAway[i].setText("");
					opponents[i].setText("");
					opponents[i].setIcon(null);
					winLossTie[i].setText("");
					gameScore[i].setText("");
				}
				for (int i = numberOfWeeks; i < numberOfWeeks + playoffRounds; i++){
					weekNumber[i].setText("");
					homeAway[i].setText("");
					opponents[i].setText("");
					opponents[i].setIcon(null);
					winLossTie[i].setText("");
					gameScore[i].setText("");
				}

				int playoffGamesPlayedIn = 0;

				int teamIndex = 0;
				for (int i = 0; i < numberOfTeams; i++)
					if (teamListing.getSelectedItem().toString().equals(teams.elementAt(i)))
						teamIndex = i;

				for (int i = 0; i < numPlayoffGames; i++){
					if (playoffGames[i][0].equals(teams.elementAt(teamIndex)) || playoffGames[i][1].equals(teams.elementAt(teamIndex)))
						playoffGamesPlayedIn++;
				}
			//	System.out.println("Playoff Games In: " + playoffGamesPlayedIn);

				for(int i = 0; i < playoffRounds; i++){
					weekNumber[i+numberOfWeeks] = new JLabel(getPlayoffRoundShort(i,playoffRounds));  // New function
					homeAway[i+numberOfWeeks] = new JLabel();
					opponents[i+numberOfWeeks] = new JLabel();
					winLossTie[i+numberOfWeeks] = new JLabel();
					gameScore[i+numberOfWeeks] = new JLabel();


					weekNumber[i+numberOfWeeks].setForeground(Color.WHITE);
					homeAway[i+numberOfWeeks].setForeground(Color.WHITE);
					opponents[i+numberOfWeeks].setForeground(Color.WHITE);
					winLossTie[i+numberOfWeeks].setForeground(Color.WHITE);
					gameScore[i+numberOfWeeks].setForeground(Color.WHITE);
				}

				for (int i = 0; i < numberOfWeeks; i++){
					weekNumber[i].setBounds(5,30 + (i * 35), 25, 30);

					// If the current game is a HOME game...
					if (schedule.elementAt(teamIndex)[i][1] == 1)
						homeAway[i].setText("vs");
					else
						homeAway[i].setText("at");

					homeAway[i].setBounds(45,30 + (i * 35), 25,30);

					// Insert the opposing team's sliver onto the panel (or display BYE otherwise)
					if (schedule.elementAt(teamIndex)[i][0] == -1){
						opponents[i].setText("BYE");
						homeAway[i].setText("");
					}
					else
						opponents[i].setIcon(new ImageIcon(getClass().getResource("images/slivers/" + teamMap.get(teams.elementAt(schedule.elementAt(teamIndex)[i][0])) + "_Sliver.png")) );

					opponents[i].setBounds(80,30 + (i * 35), 150,30);

					teamSchedule.add(weekNumber[i]);
					teamSchedule.add(homeAway[i]);
					teamSchedule.add(opponents[i]);

					// If the game has been played
					if (schedule.elementAt(teamIndex)[i][2] == 1 && schedule.elementAt(teamIndex)[i][0] != -1){
						// If the team WON its game
						if (scores.elementAt(teamIndex)[i][0] > scores.elementAt(teamIndex)[i][1])
							winLossTie[i].setText("W");
						else if (scores.elementAt(teamIndex)[i][0] < scores.elementAt(teamIndex)[i][1])
							winLossTie[i].setText("L");
						else
							winLossTie[i].setText("T");


						gameScore[i].setText(String.valueOf(scores.elementAt(teamIndex)[i][0]) + "-" + String.valueOf(scores.elementAt(teamIndex)[i][1]));

						winLossTie[i].setBounds(250, 30 + (i * 35), 20,30);
						gameScore[i].setBounds(285,30 + (i * 35), 100,30);

						teamSchedule.add(winLossTie[i]);
						teamSchedule.add(gameScore[i]);
					}
				}

				boolean firstGameFound = false;
				for (int i = numberOfWeeks; i < numberOfWeeks + playoffGamesPlayedIn; i++){
					weekNumber[i].setBounds(5,30 + (i * 35), 25, 30);
					homeAway[i].setBounds(45,30 + (i * 35), 25,30);
					opponents[i].setBounds(80,30 + (i * 35), 150, 30);
					int side = 0;

					for (int j = 0; j < numPlayoffGames; j++){
						if (playoffScores[j][3] == i - numberOfWeeks){
							boolean found = false;
							if (playoffGames[j][1].equals(teams.elementAt(teamIndex))){
								if (i - numberOfWeeks == 0)
									firstGameFound = true;
								found = true;
								side = 1;
								homeAway[i].setText("vs");
								if (playoffGames[j][0].equals("NULL"))
									opponents[i].setIcon(new ImageIcon(getClass().getResource("images/slivers/TBD_Sliver.png")));
								else
									opponents[i].setIcon(new ImageIcon(getClass().getResource("images/slivers/" + teamMap.get(playoffGames[j][0]) + "_Sliver.png")));
							}
							else if (playoffGames[j][0].equals(teams.elementAt(teamIndex))){
								if (i - numberOfWeeks == 0)
									firstGameFound = true;
								found = true;
								side = 0;
								homeAway[i].setText("at");
								opponents[i].setIcon(new ImageIcon(getClass().getResource("images/slivers/" + teamMap.get(playoffGames[j][1]) + "_Sliver.png")));
							}

							if (found){
								teamSchedule.add(weekNumber[i]);
								teamSchedule.add(homeAway[i]);
								teamSchedule.add(opponents[i]);

								if (playoffScores[j][2] == 1){
									if (playoffScores[j][side] > playoffScores[j][(side + 1)%2])
										winLossTie[i].setText("W");
									else
										winLossTie[i].setText("L");

									gameScore[i].setText(String.valueOf(playoffScores[j][side]) + "-" + String.valueOf(playoffScores[j][(side + 1)%2]) );

									winLossTie[i].setBounds(250, 30 + (i * 35), 20,30);
									gameScore[i].setBounds(285,30 + (i * 35), 100,30);

									teamSchedule.add(winLossTie[i]);
									teamSchedule.add(gameScore[i]);

								}
							}

						}
					}
					if (i - numberOfWeeks == 0 && !firstGameFound)
						playoffGamesPlayedIn++;

				}				
			}
		});

		teamListing.setBounds(105,2,150,20);
		teamSchedule.add(teamListing);

		return teamSchedule;

	}

	JPanel getLeagueLeaders(){
		JPanel leagueLeaders = new JPanel();
		leagueLeaders.setLayout(null);
		leagueLeaders.setBackground(Color.BLACK);

		// Doing Top 5 (4 if only 4 teams) of PPG, PAG, Pass YPG, Rush YPG, First Downs
		Object [][] ppg = new Object[30][2];
		Object [][] pag = new Object[30][2];
		Object [][] passYpg = new Object[30][2];
		Object [][] rushYpg = new Object[30][2];
		Object [][] firstDowns = new Object[30][2];


		// Gather the information
		for (int i = 0; i < numberOfTeams; i++){

			int gamesPlayed = 0;
			for (int j = 0; j < numberOfWeeks; j++){
				if (schedule.elementAt(i)[j][2] == 1 && schedule.elementAt(i)[j][0] != -1)
					gamesPlayed++;
			}
			int totalPointsScored = teamStats.elementAt(i).getPointsScored();
			int totalPointsAgainst = teamStats.elementAt(i).getPointsAgainst();
			int passingYards = teamStats.elementAt(i).getPassingYards();
			int rushingYards = teamStats.elementAt(i).getRushingYards();

			if (gamesPlayed == 0)
				gamesPlayed = 1;

			ppg[i][0] = teams.elementAt(i);
			ppg[i][1] = (double)((double)totalPointsScored / gamesPlayed);

			pag[i][0] = teams.elementAt(i);
			pag[i][1] = (double)((double)totalPointsAgainst / gamesPlayed);

			passYpg[i][0] = teams.elementAt(i);
			passYpg[i][1] = (double)((double)passingYards / gamesPlayed);

			rushYpg[i][0] = teams.elementAt(i);
			rushYpg[i][1] = (double)((double)rushingYards / gamesPlayed);

			firstDowns[i][0] = teams.elementAt(i);
			firstDowns[i][1] = teamStats.elementAt(i).getFirstDowns();
		}

		for (int i = 0; i < numberOfTeams - 1; i++){
			for (int j = i + 1; j < numberOfTeams; j++){
				if (Double.parseDouble(ppg[j][1].toString()) > Double.parseDouble(ppg[i][1].toString())){
					double tmpDouble = Double.parseDouble(ppg[i][1].toString());
					String tmpStr = ppg[i][0].toString();

					ppg[i][1] = Double.parseDouble(ppg[j][1].toString());
					ppg[i][0] = ppg[j][0].toString();

					ppg[j][1] = tmpDouble;
					ppg[j][0] = tmpStr;
				}

				if (Double.parseDouble(pag[j][1].toString()) < Double.parseDouble(pag[i][1].toString())){
					double tmpDouble = Double.parseDouble(pag[i][1].toString());
					String tmpStr = pag[i][0].toString();

					pag[i][1] = Double.parseDouble(pag[j][1].toString());
					pag[i][0] = pag[j][0].toString();

					pag[j][1] = tmpDouble;
					pag[j][0] = tmpStr;
				}

				if (Double.parseDouble(passYpg[j][1].toString()) > Double.parseDouble(passYpg[i][1].toString())){
					double tmpDouble = Double.parseDouble(passYpg[i][1].toString());
					String tmpStr = passYpg[i][0].toString();

					passYpg[i][1] = Double.parseDouble(passYpg[j][1].toString());
					passYpg[i][0] = passYpg[j][0].toString();

					passYpg[j][1] = tmpDouble;
					passYpg[j][0] = tmpStr;
				}

				if (Double.parseDouble(rushYpg[j][1].toString()) > Double.parseDouble(rushYpg[i][1].toString())){
					double tmpDouble = Double.parseDouble(rushYpg[i][1].toString());
					String tmpStr = rushYpg[i][0].toString();

					rushYpg[i][1] = Double.parseDouble(rushYpg[j][1].toString());
					rushYpg[i][0] = rushYpg[j][0].toString();

					rushYpg[j][1] = tmpDouble;
					rushYpg[j][0] = tmpStr;
				}

				if (Integer.parseInt(firstDowns[j][1].toString()) > Integer.parseInt(firstDowns[i][1].toString())){
					int tmpInt = Integer.parseInt(firstDowns[i][1].toString());
					String tmpStr = firstDowns[i][0].toString();

					firstDowns[i][1] = Integer.parseInt(firstDowns[j][1].toString());
					firstDowns[i][0] = firstDowns[j][0].toString();

					firstDowns[j][1] = tmpInt;
					firstDowns[j][0] = tmpStr;
				}
			}
		}

		// Take out only the top 5 of the array and use that in a new array
		Vector<Vector<Object>> pointsPerGame = new Vector<Vector<Object>>();
		Vector<Vector<Object>> pointsAgainstPerGame = new Vector<Vector<Object>>();
		Vector<Vector<Object>> passingYardsAverage = new Vector<Vector<Object>>();
		Vector<Vector<Object>> rushingYardsAverage = new Vector<Vector<Object>>();
		Vector<Vector<Object>> firstDownTotals = new Vector<Vector<Object>>();

		DecimalFormat oneDForm = new DecimalFormat("0.0");

		for (int i = 0; i < Math.min(numberOfTeams, 5); i++){
			Vector<Object> ppgTmp = new Vector<Object>();
			ppgTmp.addElement(ppg[i][0]);
			ppgTmp.addElement(oneDForm.format(ppg[i][1]));
			pointsPerGame.addElement(ppgTmp);

			Vector<Object> pagTmp = new Vector<Object>();
			pagTmp.addElement(pag[i][0]);
			pagTmp.addElement(oneDForm.format(pag[i][1]));
			pointsAgainstPerGame.addElement(pagTmp);

			Vector<Object> pyaTmp = new Vector<Object>();
			pyaTmp.addElement(passYpg[i][0]);
			pyaTmp.addElement(oneDForm.format(passYpg[i][1]));
			passingYardsAverage.addElement(pyaTmp);

			Vector<Object> ryaTmp = new Vector<Object>();
			ryaTmp.addElement(rushYpg[i][0]);
			ryaTmp.addElement(oneDForm.format(rushYpg[i][1]));
			rushingYardsAverage.addElement(ryaTmp);

			Vector<Object> fdTmp = new Vector<Object>();
			fdTmp.addElement(firstDowns[i][0]);
			fdTmp.addElement(firstDowns[i][1]);
			firstDownTotals.addElement(fdTmp);
		}

		String [] ppgHeaders = {"Team", "PPG"};
		String [] pagHeaders = {"Team", "PAG"};
		String [] pyaHeaders = {"Team", "Pass YPG"};
		String [] ryaHeaders = {"Team", "Rush YPG"};
		String [] fdHeaders = {"Team", "1st Downs"};

		Vector<String> pointsPerHeaders = new Vector<String>();
		Vector<String> pointsAgainstHeaders = new Vector<String>();
		Vector<String> passYardHeaders = new Vector<String>();
		Vector<String> rushYardHeaders = new Vector<String>();
		Vector<String> firstDownHeaders = new Vector<String>();


		for (int i = 0; i < 2; i++){
			pointsPerHeaders.addElement(ppgHeaders[i]);
			pointsAgainstHeaders.addElement(pagHeaders[i]);
			passYardHeaders.addElement(pyaHeaders[i]);
			rushYardHeaders.addElement(ryaHeaders[i]);
			firstDownHeaders.addElement(fdHeaders[i]);
		}


		JTable ppgTable = new JTable(pointsPerGame, pointsPerHeaders);
		JTable pagTable = new JTable(pointsAgainstPerGame, pointsAgainstHeaders);
		JTable pyaTable = new JTable(passingYardsAverage, passYardHeaders);
		JTable ryaTable = new JTable(rushingYardsAverage, rushYardHeaders);
		JTable firstDownTable = new JTable(firstDownTotals,firstDownHeaders);

		JTableHeader ppgHeader = ppgTable.getTableHeader();
		JTableHeader pagHeader = pagTable.getTableHeader();
		JTableHeader pyaHeader = pyaTable.getTableHeader();
		JTableHeader ryaHeader = ryaTable.getTableHeader();
		JTableHeader firstDownHeader = firstDownTable.getTableHeader();

		int yValue = 0;

		ppgHeader.setBounds(5,10,180,20);		
		ppgTable.setBounds(5,35,180,ppgTable.getRowHeight() * Math.min(5, numberOfTeams));

		pagHeader.setBounds(200,10, 180,20);
		pagTable.setBounds(200,35, 180, pagTable.getRowHeight() * Math.min(5, numberOfTeams));

		yValue += 40 + (ppgTable.getRowHeight() * Math.min(5, numberOfTeams));


		pyaHeader.setBounds(5,yValue, 180,20);
		pyaTable.setBounds(5,yValue + 25, 180,pyaTable.getRowHeight() * Math.min(5, numberOfTeams));

		ryaHeader.setBounds(200,yValue, 180,20);
		ryaTable.setBounds(200,yValue + 25, 180,ryaTable.getRowHeight() * Math.min(5, numberOfTeams));

		yValue += 40 + (ryaTable.getRowHeight() * Math.min(5,numberOfTeams));

		firstDownHeader.setBounds(5,yValue, 180,20);
		firstDownTable.setBounds(5,yValue + 25, 180,firstDownTable.getRowHeight() * Math.min(5, numberOfTeams));

		leagueLeaders.add(ppgHeader);
		leagueLeaders.add(ppgTable);
		leagueLeaders.add(pagHeader);
		leagueLeaders.add(pagTable);
		leagueLeaders.add(pyaHeader);
		leagueLeaders.add(pyaTable);
		leagueLeaders.add(ryaHeader);
		leagueLeaders.add(ryaTable);
		leagueLeaders.add(firstDownHeader);
		leagueLeaders.add(firstDownTable);


		return leagueLeaders;
	}

	void updateSeason(String awayTeam, String homeTeam, int awayScore, int homeScore, Statistics awayStats, Statistics homeStats){
		gameSaved = false;

		if (regularSeasonComplete){
			updatePlayoffs(awayTeam, homeTeam, awayScore, homeScore);
			return;
		}

		int awayID = 0;
		int homeID = 0;

		for (int i = 0; i < teams.size(); i++){
			if (teams.elementAt(i).equals(awayTeam.replaceAll("_"," ")))
				awayID = i;
			if (teams.elementAt(i).equals(homeTeam.replaceAll("_"," ")))
				homeID = i;
		}

		// Mark that the game has been played
		schedule.elementAt(awayID)[currentWeek - 1][2] = 1;
		schedule.elementAt(homeID)[currentWeek - 1][2] = 1;

		// Record the score for the away team
		scores.elementAt(awayID)[currentWeek - 1][0] = awayScore;
		scores.elementAt(awayID)[currentWeek - 1][1] = homeScore;

		// Record the score for the home team
		scores.elementAt(homeID)[currentWeek - 1][0] = homeScore;
		scores.elementAt(homeID)[currentWeek - 1][1] = awayScore;

		boolean divisionalGame = false;
		boolean conferenceGame = false;

		if (teamDivisions.elementAt(awayID) == teamDivisions.elementAt(homeID))
			divisionalGame = true;

		if ( (awayID < (teams.size() / 2) && homeID < (teams.size() / 2)) || (awayID >= (teams.size() / 2) && homeID >= (teams.size() / 2)))
			conferenceGame = true;

		// If home team wins, add win to home's record and loss to away's record
		if (homeScore > awayScore){
			teamRecords.elementAt(homeID)[0]++;
			teamRecords.elementAt(awayID)[1]++;

			if (divisionalGame){
				divisionRecords.elementAt(homeID)[0]++;
				divisionRecords.elementAt(awayID)[1]++;
			}

			if (conferenceGame){
				conferenceRecords.elementAt(homeID)[0]++;
				conferenceRecords.elementAt(awayID)[1]++;
			}
		}
		// If away team wins, add win to away's record and loss to home's record
		else if (awayScore > homeScore){
			teamRecords.elementAt(awayID)[0]++;
			teamRecords.elementAt(homeID)[1]++;

			if (divisionalGame){
				divisionRecords.elementAt(homeID)[1]++;
				divisionRecords.elementAt(awayID)[0]++;
			}

			if (conferenceGame){
				conferenceRecords.elementAt(homeID)[1]++;
				conferenceRecords.elementAt(awayID)[0]++;
			}
		}
		// If no one won, it was a tie.
		else{
			teamRecords.elementAt(awayID)[2]++;
			teamRecords.elementAt(homeID)[2]++;

			if (divisionalGame){
				divisionRecords.elementAt(homeID)[2]++;
				divisionRecords.elementAt(awayID)[2]++;
			}

			if (conferenceGame){
				conferenceRecords.elementAt(homeID)[2]++;
				conferenceRecords.elementAt(awayID)[2]++;
			}
		}

		for (int i = 0; i < awayStats.getFirstDowns(); i++)
			teamStats.elementAt(awayID).addFirstDown();

		teamStats.elementAt(awayID).addPassingYards(awayStats.getPassingYards());
		teamStats.elementAt(awayID).addRushingYards(awayStats.getRushingYards());

		teamStats.elementAt(awayID).addPointsScored(awayScore);
		teamStats.elementAt(awayID).addPointsAgainst(homeScore);

		for (int i = 0; i < homeStats.getFirstDowns(); i++)
			teamStats.elementAt(homeID).addFirstDown();

		teamStats.elementAt(homeID).addPassingYards(homeStats.getPassingYards());
		teamStats.elementAt(homeID).addRushingYards(homeStats.getRushingYards());

		teamStats.elementAt(homeID).addPointsScored(homeScore);
		teamStats.elementAt(homeID).addPointsAgainst(awayScore);


		checkForWeekCompletion();
	}

	// Function for updating the playoffs here
	void updatePlayoffs(String awayTeam, String homeTeam, int awayScore, int homeScore){
		int currentGame = 0;
		int seed = 0;
		String winnerName = "";
		boolean firstConference = false; // Only for wildcard round


		for (int i = 0; i < numPlayoffGames; i++){
			if (playoffGames[i][0].equals(awayTeam.replaceAll("_"," ")) && playoffGames[i][1].equals(homeTeam.replaceAll("_"," ") ))
				currentGame = i;
			if (homeScore > awayScore){
				winnerName = homeTeam.replaceAll("_"," ");
				seed = Integer.parseInt(playoffGames[currentGame][4]);
			}
			else{
				winnerName = awayTeam.replaceAll("_"," ");
				seed = Integer.parseInt(playoffGames[currentGame][3]);
			}
		}
	//	System.out.println("Winner is : " + winnerName + " Seed : " + seed);

		if (currentGame < 2)
			firstConference = true;

		playoffScores[currentGame][2] = 1;
		playoffScores[currentGame][0] = awayScore;
		playoffScores[currentGame][1] = homeScore;

		if (currentRound == 0 && maxPlayoffTeams == 6){
			if (firstConference){
				if (seed < 6 && seed > 3){
					// #1 seed is in the first game of the 2nd round (#4 and #6), #2 seed in second game of 2nd round (#5 and #7)
					// If this is the first playoff game finished
					if (playoffGames[4][0].equals("NULL") && playoffGames[5][0].equals("NULL")){
						teamInWaiting[0] = winnerName;	// waiting index will be [1] for the other conference
						waitingTeamSeed[0] = seed;
					}
					else{
						if (playoffGames[4][0].equals("NULL")){
							playoffGames[4][0] = winnerName;
							playoffGames[4][3] = String.valueOf(seed);
						}
						else{
							playoffGames[5][0] = winnerName;
							playoffGames[5][3] = String.valueOf(seed);
						}
					}
				}
				else if (seed == 6){
				//	System.out.println("6th seed won");
					playoffGames[4][0] = winnerName;
					playoffGames[4][3] = String.valueOf(seed);

					if (!teamInWaiting[0].equals("NULL")){
						playoffGames[5][0] = teamInWaiting[0];
						playoffGames[5][3] = String.valueOf(waitingTeamSeed[0]);
					}
				}
				else{ // #3 seed
				//	System.out.println("3rd seed won");
					playoffGames[5][0] = winnerName;
					playoffGames[5][3] = String.valueOf(seed);
				//	System.out.println(playoffGames[5][0] + " " + playoffGames[5][3]);
					if (!teamInWaiting[0].equals("NULL")){
						playoffGames[4][0] = teamInWaiting[0];
						playoffGames[4][3] = String.valueOf(waitingTeamSeed[0]);
					}
				}
			}
			else{
				if (seed < 6 && seed > 3){
					// #1 seed is in the first game of the 2nd round (#4 and #6), #2 seed in second game of 2nd round (#5 and #7)
					// If this is the first playoff game finished
					if (playoffGames[6][0].equals("NULL") && playoffGames[7][0].equals("NULL")){
						teamInWaiting[1] = winnerName;	// waiting index will be [1] for the other conference
						waitingTeamSeed[1] = seed;
					}
					else{
						if (playoffGames[6][0].equals("NULL")){
							playoffGames[6][0] = winnerName;
							playoffGames[6][3] = String.valueOf(seed);
						}
						else{
							playoffGames[7][0] = winnerName;
							playoffGames[7][3] = String.valueOf(seed);
						}
					}
				}
				else if (seed == 6){
					playoffGames[6][0] = winnerName;
					playoffGames[6][3] = String.valueOf(seed);
					if (!teamInWaiting[1].equals("NULL")){
						playoffGames[7][0] = teamInWaiting[1];
						playoffGames[7][3] = String.valueOf(waitingTeamSeed[1]);
					}
				}
				else{ // #3 seed
					playoffGames[7][0] = winnerName;
					playoffGames[7][3] = String.valueOf(seed);
					if (!teamInWaiting[1].equals("NULL")){
						playoffGames[6][0] = teamInWaiting[1];
						playoffGames[6][3] = String.valueOf(waitingTeamSeed[1]);
					}
				}
			}
		}
		// 8 Teams left
		else if ((maxPlayoffTeams == 4 && currentRound == 0) || (currentRound == 1 && maxPlayoffTeams == 6)){
	//		System.out.println("test");
			if (maxPlayoffTeams == 4){
				if (currentGame < 2){
					if (playoffGames[4][0].equals("NULL")){
						playoffGames[4][0] = winnerName;
						playoffGames[4][3] = String.valueOf(seed);
					}
					else{
						playoffGames[4][1] = winnerName;
						playoffGames[4][4] = String.valueOf(seed);
					}
				}
				else{
					if (playoffGames[5][0].equals("NULL")){
						playoffGames[5][0] = winnerName;
						playoffGames[5][3] = String.valueOf(seed);
					}
					else{
						playoffGames[5][1] = winnerName;
						playoffGames[5][4] = String.valueOf(seed);
					}
				}

			}// end if maxteams = 4
			else{
				if (currentGame < 6){
					if (playoffGames[8][0].equals("NULL")){
						playoffGames[8][0] = winnerName;
						playoffGames[8][3] = String.valueOf(seed);
					}
					else{
						playoffGames[8][1] = winnerName;
						playoffGames[8][4] = String.valueOf(seed);
					}
				}
				else{
					if (playoffGames[9][0].equals("NULL")){
						playoffGames[9][0] = winnerName;
						playoffGames[9][3] = String.valueOf(seed);
					}
					else{
						playoffGames[9][1] = winnerName;
						playoffGames[9][4] = String.valueOf(seed);
					}
				}


			} // end if maxteams = 6
		}
		// 4 Teams Left
		else if ((maxPlayoffTeams == 2 && currentRound == 0 && numberOfTeams == 8) || (maxPlayoffTeams == 4 && currentRound == 1) || (maxPlayoffTeams == 6 && currentRound == 2)){
			if (maxPlayoffTeams == 2){
				if (playoffGames[2][0].equals("NULL")){
					playoffGames[2][0] = winnerName;
					playoffGames[2][3] = String.valueOf(seed);
				}
				else{
					playoffGames[2][1] = winnerName;
					playoffGames[2][4] = String.valueOf(seed);
				}
			}// end teams = 2
			else if (maxPlayoffTeams == 4){
				if (playoffGames[6][0].equals("NULL")){
					playoffGames[6][0] = winnerName;
					playoffGames[6][3] = String.valueOf(seed);
				}
				else{
					playoffGames[6][1] = winnerName;
					playoffGames[6][4] = String.valueOf(seed);
				}
			}// end teams = 4
			else if (maxPlayoffTeams == 6){
				if (playoffGames[10][0].equals("NULL")){
					playoffGames[10][0] = winnerName;
					playoffGames[10][3] = String.valueOf(seed);
				}
				else{
					playoffGames[10][1] = winnerName;
					playoffGames[10][4] = String.valueOf(seed);
				}
			} // end teams = 6
		}
		// Championship
		else{
			champion = winnerName;
		}

		for (int i = 0; i < numPlayoffGames; i++){
		//	System.out.println(playoffGames[i][0] + " " + playoffGames[i][3] + " at " + playoffGames[i][1] + " " + playoffGames[i][4]);
			if (playoffScores[i][2] == 0 && (playoffGames[i][3] != "NA" && playoffGames[i][4] != "NA")){
				if (Integer.parseInt(playoffGames[i][3]) < Integer.parseInt(playoffGames[i][4])){
		//			System.out.println(playoffGames[i][0] + " " + playoffGames[i][3] + " at " + playoffGames[i][1] + " " + playoffGames[i][4]);
					String tmpName = playoffGames[i][1];
					String tmpSeed = playoffGames[i][4];
					playoffGames[i][1] = playoffGames[i][0];
					playoffGames[i][4] = playoffGames[i][3];
					playoffGames[i][3] = tmpSeed;
					playoffGames[i][0] = tmpName;
				}
			}
		}

		// Check for round completion
		checkRoundCompletion();

	}

	void checkForWeekCompletion(){
		for (int i = 0; i < teams.size(); i++){
			// If a game has not been played, stop looking
			if (schedule.elementAt(i)[currentWeek - 1][2] == 0)
				return;
		}

		currentWeek++;
		if (currentWeek > numberOfWeeks){
			regularSeasonComplete = true;	// This will switch to regularSeasonComplete = true;
		}
	}

	void checkRoundCompletion(){
		// Go through each game in the round and if all played, move onto next round.
		boolean roundOver = true;
		for (int i = 0; i < numPlayoffGames; i++){
			if (playoffScores[i][3] == currentRound)
				if (playoffScores[i][2] == 0){
					roundOver = false;
					break;
				}
		}
		if (roundOver)
			currentRound++;
		
		if (currentRound == playoffRounds)
			seasonComplete = true;
	}


// Problem with this function.  It involves moving onto the next tiebreaker function
String getStatisticalLeader(int size, String[] teams, double[] values){
	int leaderIndex = 0;
	int numTie = 0;
	int maxTies = 0;
	
	for (int i = 0; i < size; i++){
		maxTies = numTie;
		numTie = 0;
		for (int j = i+1; j < size; j++){
			if (values[j] > values[i]){
				leaderIndex = j;
				i = j;
				maxTies = numTie;
				numTie = 0;
			}
			else if (values[j] < values[i]){
				leaderIndex = i;
				maxTies = numTie;
				numTie = 0;
			}
			else{
				leaderIndex = i;
				numTie++;
				System.out.println("Team: " + i + " Tied: " + numTie);
			}
		}
	}

	numTie = maxTies;
	if (numTie == 0)
		return teams[leaderIndex];
	else if (numTie + 1 == size)
		return "null";
		
	if (numTie == 1){
		if (size == 2)
			return "null";
		else{
			int tieIndex = -1;
			for (int i = 0; i < size; i++){
				if (i != leaderIndex && values[i] == values[leaderIndex])
					tieIndex = i;
			}
			
			return perform2TeamTiebreaker(teams[leaderIndex], teams[tieIndex]);
		}
	}
	else if (numTie == 2){
		if (size == 3)
			return "null";
		else{
			int [] tieIndex = new int[2];
			int idx = 0;
			for (int i = 0; i < 2; i++)
				tieIndex[i] = -1;	
				
			for (int i = 0; i < size; i++){
				if (i != leaderIndex && values[i] == values[leaderIndex]){
					tieIndex[idx] = i;
					idx++;
				}
			}	
		
			return perform3TeamTiebreaker(teams[leaderIndex], teams[tieIndex[0]], teams[tieIndex[1]] );
		}
	}
	else if (numTie == 3){
		if (size == 4)
			return "null";
		else{
			int [] tieIndex = new int[3];
			int idx = 0;
			for (int i = 0; i < 3; i++)
				tieIndex[i] = -1;

			for (int i = 0; i < size; i++){
				if (i != leaderIndex && values[i] == values[leaderIndex]){
					tieIndex[idx] = i;
					idx++;
				}
			}					
			return perform4TeamTiebreaker(teams[leaderIndex], teams[tieIndex[0]], teams[tieIndex[1]], teams[tieIndex[2]]);
		}
	}
	// If numTie == 4 and size = 5
	else
		return "null";
}

String perform2TeamTiebreaker(String team1, String team2){
	System.out.println("2-Team Tiebreaker");
	String [] teamNames = new String[2];
	int [] indices = new int[2];
	double [] values = new double[2];
	
	for (int i =0; i < 2; i++){
		values[i] = 0.0;
		indices[i] = -1;
	}

	teamNames[0] = team1;
	teamNames[1] = team2;
	
	for (int i = 0; i < teams.size(); i++){
		if (teams.elementAt(i).equals(team1.replaceAll("_"," ")))
			indices[0] = i;
		if (teams.elementAt(i).equals(team2.replaceAll("_"," ")))
			indices[1] = i;
	}

	// Same win percentage, different win totals
	values[0] = teamRecords.elementAt(indices[0])[0];
	values[1] = teamRecords.elementAt(indices[1])[0];
	String result = getStatisticalLeader(2, teamNames, values);
	
	// Same win percentage, different loss totals
	if (result.equals("null")){
		values[0] = 0 - teamRecords.elementAt(indices[0])[1];
		values[1] = 0 - teamRecords.elementAt(indices[1])[1];
		result = getStatisticalLeader(2, teamNames, values);
	}
	// Head-to-head tiebreaker
	if (result.equals("null")){
		int h2hAdvantage = 0;
		for (int i = 0; i < numberOfWeeks; i++){
			// If teams have played each other
			if (schedule.elementAt(indices[0])[i][0] == indices[1]){
			
				if (scores.elementAt(indices[0])[i][0] > scores.elementAt(indices[0])[i][1])
					h2hAdvantage++;
				else if (scores.elementAt(indices[0])[i][0] < scores.elementAt(indices[0])[i][1])
					h2hAdvantage--;
			}
		}
		if (h2hAdvantage > 0)
			result = teamNames[0];
		else if (h2hAdvantage < 0)
			result = teamNames[1];
	}
	// Divisional record tiebreaker
	if (result.equals("null")){
		int gamesPlayed1 = divisionRecords.elementAt(indices[0])[0] + divisionRecords.elementAt(indices[0])[1] + divisionRecords.elementAt(indices[0])[2];
		int gamesPlayed2 = divisionRecords.elementAt(indices[1])[0] + divisionRecords.elementAt(indices[1])[1] + divisionRecords.elementAt(indices[1])[2];
		
		if (gamesPlayed1 == 0)
			values[0] = 0.0;
		else{
			values[0] =  (double)( (double)(divisionRecords.elementAt(indices[0])[0] + (1/2) * (double)(divisionRecords.elementAt(indices[0])[1])) / gamesPlayed1);
		}
			
		if (gamesPlayed2 == 0)
			values[1] = 0.0;
		else{
			values[1] =  (double)( (double)(divisionRecords.elementAt(indices[1])[0] + (1/2) * (double)(divisionRecords.elementAt(indices[1])[1])) / gamesPlayed2);		
		}
		
		result = getStatisticalLeader(2, teamNames, values);
		
		if (result.equals("null")){
			if (divisionRecords.elementAt(indices[0])[1] == 0 && divisionRecords.elementAt(indices[0])[0] != 0){
				values[0] = divisionRecords.elementAt(indices[0])[0];
				values[1] = divisionRecords.elementAt(indices[1])[0];
				result = getStatisticalLeader(2, teamNames, values);
			}
			else if (divisionRecords.elementAt(indices[0])[0] == 0 && divisionRecords.elementAt(indices[0])[1] != 0){
				values[0] = 0 - divisionRecords.elementAt(indices[0])[1];
				values[1] = 0 - divisionRecords.elementAt(indices[1])[1];			
				result = getStatisticalLeader(2, teamNames, values);
			}
		}
		
		
	}
	// Conference record tiebreaker
	if (result.equals("null")){
		int gamesPlayed1 = conferenceRecords.elementAt(indices[0])[0] + conferenceRecords.elementAt(indices[0])[1] + conferenceRecords.elementAt(indices[0])[2];
		int gamesPlayed2 = conferenceRecords.elementAt(indices[1])[0] + conferenceRecords.elementAt(indices[1])[1] + conferenceRecords.elementAt(indices[1])[2];
		
		if (gamesPlayed1 == 0)
			values[0] = 0.0;
		else{
			values[0] =  (double)( (double)(conferenceRecords.elementAt(indices[0])[0] + (1/2) * (double)(conferenceRecords.elementAt(indices[0])[1])) / gamesPlayed1);
		}
			
		if (gamesPlayed2 == 0)
			values[1] = 0.0;
		else{
			values[1] =  (double)( (double)(conferenceRecords.elementAt(indices[1])[0] + (1/2) * (double)(conferenceRecords.elementAt(indices[1])[1])) / gamesPlayed2);		
		}
		
		result = getStatisticalLeader(2, teamNames, values);
		
		if (result.equals("null")){
			if (conferenceRecords.elementAt(indices[0])[1] == 0 && conferenceRecords.elementAt(indices[0])[0] != 0){
				values[0] = conferenceRecords.elementAt(indices[0])[0];
				values[1] = conferenceRecords.elementAt(indices[1])[0];
				result = getStatisticalLeader(2, teamNames, values);
			}
			else if (conferenceRecords.elementAt(indices[0])[0] == 0 && conferenceRecords.elementAt(indices[0])[1] != 0){
				values[0] = 0 - conferenceRecords.elementAt(indices[0])[1];
				values[1] = 0 - conferenceRecords.elementAt(indices[1])[1];			
				result = getStatisticalLeader(2, teamNames, values);
			}
		}
	}
	// Point differential tiebreaker 
	if (result.equals("null")){
		values[0] = teamStats.elementAt(indices[0]).getPointsScored() - teamStats.elementAt(indices[0]).getPointsAgainst();
		values[1] = teamStats.elementAt(indices[1]).getPointsScored() - teamStats.elementAt(indices[1]).getPointsAgainst();
	
		result = getStatisticalLeader(2, teamNames, values);
		if (result.equals("null")){
			values[0] = teamStats.elementAt(indices[0]).getPointsScored();
			values[1] = teamStats.elementAt(indices[1]).getPointsScored();		
			result = getStatisticalLeader(2, teamNames, values);
		}
		if (result.equals("null")){
			values[0] = 0 - teamStats.elementAt(indices[0]).getPointsAgainst();
			values[1] = 0 - teamStats.elementAt(indices[1]).getPointsAgainst();			
			result = getStatisticalLeader(2, teamNames, values);
		}
		
	}
	// Coin flip
	if (result.equals("null")){
		int flip = (int)(Math.random() * 1000);
		if (flip % 2 == 0)
			result = teamNames[0];
		else
			result = teamNames[1];
	}		

	return result;
}

String perform3TeamTiebreaker(String team1, String team2, String team3){
	System.out.println("3-Team Tiebreaker");
	String [] teamNames = new String[3];
	int [] indices = new int[3];
	double [] values = new double[3];
	
	for (int i =0; i < 3; i++){
		values[i] = 0.0;
		indices[i] = -1;
	}

	teamNames[0] = team1;
	teamNames[1] = team2;
	teamNames[2] = team3;
	
	for (int i = 0; i < teams.size(); i++){
		if (teams.elementAt(i).equals(team1.replaceAll("_"," ")))
			indices[0] = i;
		if (teams.elementAt(i).equals(team2.replaceAll("_"," ")))
			indices[1] = i;
		if (teams.elementAt(i).equals(team3.replaceAll("_"," ")))
			indices[2] = i;
	}


	// Same win percentage, different win totals
	values[0] = teamRecords.elementAt(indices[0])[0];
	values[1] = teamRecords.elementAt(indices[1])[0];
	values[2] = teamRecords.elementAt(indices[2])[0];

	String result = getStatisticalLeader(3, teamNames, values);
	
	// Same win percentage, different loss totals
	if (result.equals("null")){
		values[0] = 0 - teamRecords.elementAt(indices[0])[1];
		values[1] = 0 - teamRecords.elementAt(indices[1])[1];
		values[2] = 0 - teamRecords.elementAt(indices[2])[1];
		result = getStatisticalLeader(3, teamNames, values);
	}
	// Head-to-head tiebreaker
	if (result.equals("null")){
		int [] wins = new int[3];
		int [] losses = new int[3];
		int [] ties = new int[3];
		
		for (int i = 0; i < 3; i++){
			wins[i] = 0;
			losses[i] = 0;
			ties[i] = 0;
		}
		
		for (int i = 0; i < currentWeek + 1; i++){
			// If teams have played each other (this is right)
			if (schedule.elementAt(indices[0])[i][0] == indices[1]){			
				if (scores.elementAt(indices[0])[i][0] > scores.elementAt(indices[0])[i][1]){
					wins[0]++;
					losses[1]++;
				}
				else if (scores.elementAt(indices[0])[i][0] < scores.elementAt(indices[0])[i][1]){
					losses[0]++;
					wins[1]++;
				}
				else{
					ties[0]++;
					ties[1]++;
				}
			}
			if (schedule.elementAt(indices[0])[i][0] == indices[2]){			
				if (scores.elementAt(indices[0])[i][0] > scores.elementAt(indices[0])[i][1]){
					wins[0]++;
					losses[2]++;
				}
				else if (scores.elementAt(indices[0])[i][0] < scores.elementAt(indices[0])[i][1]){
					losses[0]++;
					wins[2]++;
				}
				else{
					ties[0]++;
					ties[2]++;
				}
			}
			if (schedule.elementAt(indices[1])[i][0] == indices[2]){			
				if (scores.elementAt(indices[1])[i][0] > scores.elementAt(indices[1])[i][1]){
					wins[1]++;
					losses[2]++;
				}
				else if (scores.elementAt(indices[1])[i][0] < scores.elementAt(indices[1])[i][1]){
					losses[1]++;
					wins[2]++;
				}
				else{
					ties[1]++;
					ties[2]++;
				}
			}
		}
		
		int gamesPlayed1 = wins[0] + losses[0] + ties[0];		
		int gamesPlayed2 = wins[1] + losses[1] + ties[1];
		int gamesPlayed3 = wins[2] + losses[2] + ties[2];
		
		if (gamesPlayed1 == 0)
			values[0] = 0.0;
		else
			values[0] =  (double)( (double)(wins[0] + (1/2) * (double)(ties[0]) / gamesPlayed1));
			
		if (gamesPlayed2 == 0)
			values[1] = 0.0;
		else
			values[1] =  (double)( (double)(wins[1] + (1/2) * (double)(ties[1]) / gamesPlayed2));
			
		if (gamesPlayed3 == 0)
			values[2] = 0.0;
		else
			values[2] =  (double)( (double)(wins[2] + (1/2) * (double)(ties[2]) / gamesPlayed3));
		
		result = getStatisticalLeader(3, teamNames, values);
	}
	// Divisional record tiebreaker
	if (result.equals("null")){
		int gamesPlayed1 = divisionRecords.elementAt(indices[0])[0] + divisionRecords.elementAt(indices[0])[1] + divisionRecords.elementAt(indices[0])[2];
		int gamesPlayed2 = divisionRecords.elementAt(indices[1])[0] + divisionRecords.elementAt(indices[1])[1] + divisionRecords.elementAt(indices[1])[2];
		int gamesPlayed3 = divisionRecords.elementAt(indices[2])[0] + divisionRecords.elementAt(indices[2])[1] + divisionRecords.elementAt(indices[2])[2];
		
		if (gamesPlayed1 == 0)
			values[0] = 0.0;
		else{
			values[0] =  (double)( (double)(divisionRecords.elementAt(indices[0])[0] + (1/2) * (double)(divisionRecords.elementAt(indices[0])[1])) / gamesPlayed1);
		}
			
		if (gamesPlayed2 == 0)
			values[1] = 0.0;
		else{
			values[1] =  (double)( (double)(divisionRecords.elementAt(indices[1])[0] + (1/2) * (double)(divisionRecords.elementAt(indices[1])[1])) / gamesPlayed2);		
		}
		
		if (gamesPlayed3 == 0)
			values[2] = 0.0;
		else
			values[2] = (double)( (double)(divisionRecords.elementAt(indices[2])[0] + (1/2) * (double)(divisionRecords.elementAt(indices[2])[1])) / gamesPlayed3);
		
		result = getStatisticalLeader(3, teamNames, values);
		
		if (result.equals("null")){
			if (divisionRecords.elementAt(indices[0])[1] == 0 && divisionRecords.elementAt(indices[0])[0] != 0){
				values[0] = divisionRecords.elementAt(indices[0])[0];
				values[1] = divisionRecords.elementAt(indices[1])[0];
				values[2] = divisionRecords.elementAt(indices[2])[0];
				result = getStatisticalLeader(3, teamNames, values);
			}
			else if (divisionRecords.elementAt(indices[0])[0] == 0 && divisionRecords.elementAt(indices[0])[1] != 0){
				values[0] = 0 - divisionRecords.elementAt(indices[0])[1];
				values[1] = 0 - divisionRecords.elementAt(indices[1])[1];			
				values[2] = 0 - divisionRecords.elementAt(indices[2])[1];
				result = getStatisticalLeader(3, teamNames, values);
			}
		}
		
		
	}
	// Conference record tiebreaker
	if (result.equals("null")){
		int gamesPlayed1 = conferenceRecords.elementAt(indices[0])[0] + conferenceRecords.elementAt(indices[0])[1] + conferenceRecords.elementAt(indices[0])[2];
		int gamesPlayed2 = conferenceRecords.elementAt(indices[1])[0] + conferenceRecords.elementAt(indices[1])[1] + conferenceRecords.elementAt(indices[1])[2];
		int gamesPlayed3 = conferenceRecords.elementAt(indices[2])[0] + conferenceRecords.elementAt(indices[2])[1] + conferenceRecords.elementAt(indices[2])[2];
		
		if (gamesPlayed1 == 0)
			values[0] = 0.0;
		else{
			values[0] =  (double)( (double)(conferenceRecords.elementAt(indices[0])[0] + (1/2) * (double)(conferenceRecords.elementAt(indices[0])[1])) / gamesPlayed1);
		}
			
		if (gamesPlayed2 == 0)
			values[1] = 0.0;
		else{
			values[1] =  (double)( (double)(conferenceRecords.elementAt(indices[1])[0] + (1/2) * (double)(conferenceRecords.elementAt(indices[1])[1])) / gamesPlayed2);		
		}
		
		if (gamesPlayed3 == 0)
			values[2] = 0.0;
		else
			values[2] = (double)( (double)(conferenceRecords.elementAt(indices[2])[0] + (1/2) * (double)(conferenceRecords.elementAt(indices[2])[1])) / gamesPlayed3);
		
		result = getStatisticalLeader(3, teamNames, values);
		
		if (result.equals("null")){
			if (conferenceRecords.elementAt(indices[0])[1] == 0 && conferenceRecords.elementAt(indices[0])[0] != 0){
				values[0] = conferenceRecords.elementAt(indices[0])[0];
				values[1] = conferenceRecords.elementAt(indices[1])[0];
				values[2] = conferenceRecords.elementAt(indices[2])[0];
				result = getStatisticalLeader(3, teamNames, values);
			}
			else if (conferenceRecords.elementAt(indices[0])[0] == 0 && conferenceRecords.elementAt(indices[0])[1] != 0){
				values[0] = 0 - conferenceRecords.elementAt(indices[0])[1];
				values[1] = 0 - conferenceRecords.elementAt(indices[1])[1];			
				values[2] = 0 - conferenceRecords.elementAt(indices[2])[1];
				result = getStatisticalLeader(3, teamNames, values);
			}
		}
	}
	// Point differential tiebreaker 
	if (result.equals("null")){
		values[0] = teamStats.elementAt(indices[0]).getPointsScored() - teamStats.elementAt(indices[0]).getPointsAgainst();
		values[1] = teamStats.elementAt(indices[1]).getPointsScored() - teamStats.elementAt(indices[1]).getPointsAgainst();
		values[2] = teamStats.elementAt(indices[2]).getPointsScored() - teamStats.elementAt(indices[2]).getPointsAgainst();
	
		result = getStatisticalLeader(3, teamNames, values);
		if (result.equals("null")){
			values[0] = teamStats.elementAt(indices[0]).getPointsScored();
			values[1] = teamStats.elementAt(indices[1]).getPointsScored();		
			values[2] = teamStats.elementAt(indices[2]).getPointsScored();
			result = getStatisticalLeader(3, teamNames, values);
		}
		if (result.equals("null")){
			values[0] = 0 - teamStats.elementAt(indices[0]).getPointsAgainst();
			values[1] = 0 - teamStats.elementAt(indices[1]).getPointsAgainst();			
			values[2] = 0 - teamStats.elementAt(indices[2]).getPointsAgainst();
			result = getStatisticalLeader(3, teamNames, values);
		}
		
	}
	// Coin flip
	if (result.equals("null")){
		int flip = (int)(Math.random() * 1000);
		if (flip % 3 == 0)
			result = teamNames[0];
		else if (flip % 3 == 1)
			result = teamNames[1];
		else
			result = teamNames[2];
	}		

	return result;
}

String perform4TeamTiebreaker(String team1, String team2, String team3, String team4){
	System.out.println("4-Team Tiebreaker");
	String [] teamNames = new String[4];
	int [] indices = new int[4];
	double [] values = new double[4];
	
	for (int i =0; i < 4; i++){
		values[i] = 0.0;
		indices[i] = -1;
	}

	teamNames[0] = team1;
	teamNames[1] = team2;
	teamNames[2] = team3;
	teamNames[3] = team4;
	
	for (int i = 0; i < teams.size(); i++){
		if (teams.elementAt(i).equals(team1.replaceAll("_"," ")))
			indices[0] = i;
		if (teams.elementAt(i).equals(team2.replaceAll("_"," ")))
			indices[1] = i;
		if (teams.elementAt(i).equals(team3.replaceAll("_"," ")))
			indices[2] = i;
		if (teams.elementAt(i).equals(team4.replaceAll("_"," ")))
			indices[3] = i;
	}

	// Same win percentage, different win totals
	values[0] = teamRecords.elementAt(indices[0])[0];
	values[1] = teamRecords.elementAt(indices[1])[0];
	values[2] = teamRecords.elementAt(indices[2])[0];
	values[3] = teamRecords.elementAt(indices[3])[0];
	String result = getStatisticalLeader(4, teamNames, values);
	
	// Same win percentage, different loss totals
	if (result.equals("null")){
		values[0] = 0 - teamRecords.elementAt(indices[0])[1];
		values[1] = 0 - teamRecords.elementAt(indices[1])[1];
		values[2] = 0 - teamRecords.elementAt(indices[2])[1];
		values[3] = 0 - teamRecords.elementAt(indices[3])[1];
		result = getStatisticalLeader(4, teamNames, values);
	}
	// Head-to-head tiebreaker
	if (result.equals("null")){
		int [] wins = new int[4];
		int [] losses = new int[4];
		int [] ties = new int[4];
		
		for (int i = 0; i < 4; i++){
			wins[i] = 0;
			losses[i] = 0;
			ties[i] = 0;
		}		
		
		for (int i = 0; i < currentWeek + 1; i++){
			// If teams have played each other (this is right)
			if (schedule.elementAt(indices[0])[i][0] == indices[1]){			
				if (scores.elementAt(indices[0])[i][0] > scores.elementAt(indices[0])[i][1]){
					wins[0]++;
					losses[1]++;
				}
				else if (scores.elementAt(indices[0])[i][0] < scores.elementAt(indices[0])[i][1]){
					losses[0]++;
					wins[1]++;
				}
				else{
					ties[0]++;
					ties[1]++;
				}
			}
			if (schedule.elementAt(indices[0])[i][0] == indices[2]){			
				if (scores.elementAt(indices[0])[i][0] > scores.elementAt(indices[0])[i][1]){
					wins[0]++;
					losses[2]++;
				}
				else if (scores.elementAt(indices[0])[i][0] < scores.elementAt(indices[0])[i][1]){
					losses[0]++;
					wins[2]++;
				}
				else{
					ties[0]++;
					ties[2]++;
				}
			}
			if (schedule.elementAt(indices[0])[i][0] == indices[3]){			
				if (scores.elementAt(indices[0])[i][0] > scores.elementAt(indices[0])[i][1]){
					wins[0]++;
					losses[3]++;
				}
				else if (scores.elementAt(indices[0])[i][0] < scores.elementAt(indices[0])[i][1]){
					losses[0]++;
					wins[3]++;
				}
				else{
					ties[0]++;
					ties[3]++;
				}
			}
			if (schedule.elementAt(indices[1])[i][0] == indices[2]){			
				if (scores.elementAt(indices[1])[i][0] > scores.elementAt(indices[1])[i][1]){
					wins[1]++;
					losses[2]++;
				}
				else if (scores.elementAt(indices[1])[i][0] < scores.elementAt(indices[1])[i][1]){
					losses[1]++;
					wins[2]++;
				}
				else{
					ties[1]++;
					ties[2]++;
				}
			}
			if (schedule.elementAt(indices[1])[i][0] == indices[3]){			
				if (scores.elementAt(indices[1])[i][0] > scores.elementAt(indices[1])[i][1]){
					wins[1]++;
					losses[3]++;
				}
				else if (scores.elementAt(indices[1])[i][0] < scores.elementAt(indices[1])[i][1]){
					losses[1]++;
					wins[3]++;
				}
				else{
					ties[1]++;
					ties[3]++;
				}
			}
			if (schedule.elementAt(indices[2])[i][0] == indices[3]){			
				if (scores.elementAt(indices[2])[i][0] > scores.elementAt(indices[2])[i][1]){
					wins[2]++;
					losses[3]++;
				}
				else if (scores.elementAt(indices[2])[i][0] < scores.elementAt(indices[2])[i][1]){
					losses[2]++;
					wins[3]++;
				}
				else{
					ties[2]++;
					ties[3]++;
				}
			}
		}
		
		int gamesPlayed1 = wins[0] + losses[0] + ties[0];		
		int gamesPlayed2 = wins[1] + losses[1] + ties[1];
		int gamesPlayed3 = wins[2] + losses[2] + ties[2];
		int gamesPlayed4 = wins[3] + losses[3] + ties[3];
		
		if (gamesPlayed1 == 0)
			values[0] = 0.0;
		else
			values[0] =  (double)( (double)(wins[0] + (1/2) * (double)(ties[0]) / gamesPlayed1));
			
		if (gamesPlayed2 == 0)
			values[1] = 0.0;
		else
			values[1] =  (double)( (double)(wins[1] + (1/2) * (double)(ties[1]) / gamesPlayed2));
			
		if (gamesPlayed3 == 0)
			values[2] = 0.0;
		else
			values[2] =  (double)( (double)(wins[2] + (1/2) * (double)(ties[2]) / gamesPlayed3));
			
		if (gamesPlayed4 == 0)
			values[3] = 0.0;
		else
			values[3] =  (double)( (double)(wins[3] + (1/2) * (double)(ties[3]) / gamesPlayed4));
		
		result = getStatisticalLeader(4, teamNames, values);
	}
	// Divisional record tiebreaker
	if (result.equals("null")){
		int gamesPlayed1 = divisionRecords.elementAt(indices[0])[0] + divisionRecords.elementAt(indices[0])[1] + divisionRecords.elementAt(indices[0])[2];
		int gamesPlayed2 = divisionRecords.elementAt(indices[1])[0] + divisionRecords.elementAt(indices[1])[1] + divisionRecords.elementAt(indices[1])[2];
		int gamesPlayed3 = divisionRecords.elementAt(indices[2])[0] + divisionRecords.elementAt(indices[2])[1] + divisionRecords.elementAt(indices[2])[2];
		int gamesPlayed4 = divisionRecords.elementAt(indices[3])[0] + divisionRecords.elementAt(indices[3])[1] + divisionRecords.elementAt(indices[3])[2];
		
		if (gamesPlayed1 == 0)
			values[0] = 0.0;
		else{
			values[0] =  (double)( (double)(divisionRecords.elementAt(indices[0])[0] + (1/2) * (double)(divisionRecords.elementAt(indices[0])[1])) / gamesPlayed1);
		}
			
		if (gamesPlayed2 == 0)
			values[1] = 0.0;
		else{
			values[1] =  (double)( (double)(divisionRecords.elementAt(indices[1])[0] + (1/2) * (double)(divisionRecords.elementAt(indices[1])[1])) / gamesPlayed2);		
		}
		
		if (gamesPlayed3 == 0)
			values[2] = 0.0;
		else
			values[2] = (double)( (double)(divisionRecords.elementAt(indices[2])[0] + (1/2) * (double)(divisionRecords.elementAt(indices[2])[1])) / gamesPlayed3);
			
		if (gamesPlayed4 == 0)
			values[3] = 0.0;
		else
			values[3] = (double)( (double)(divisionRecords.elementAt(indices[3])[0] + (1/2) * (double)(divisionRecords.elementAt(indices[3])[1])) / gamesPlayed4);
		
		result = getStatisticalLeader(4, teamNames, values);
		
		if (result.equals("null")){
			if (divisionRecords.elementAt(indices[0])[1] == 0 && divisionRecords.elementAt(indices[0])[0] != 0){
				values[0] = divisionRecords.elementAt(indices[0])[0];
				values[1] = divisionRecords.elementAt(indices[1])[0];
				values[2] = divisionRecords.elementAt(indices[2])[0];
				values[3] = divisionRecords.elementAt(indices[3])[0];
				result = getStatisticalLeader(4, teamNames, values);
			}
			else if (divisionRecords.elementAt(indices[0])[0] == 0 && divisionRecords.elementAt(indices[0])[1] != 0){
				values[0] = 0 - divisionRecords.elementAt(indices[0])[1];
				values[1] = 0 - divisionRecords.elementAt(indices[1])[1];			
				values[2] = 0 - divisionRecords.elementAt(indices[2])[1];
				values[3] = 0 - divisionRecords.elementAt(indices[3])[1];
				result = getStatisticalLeader(4, teamNames, values);
			}
		}
		
		
	}
	// Conference record tiebreaker
	if (result.equals("null")){
		int gamesPlayed1 = conferenceRecords.elementAt(indices[0])[0] + conferenceRecords.elementAt(indices[0])[1] + conferenceRecords.elementAt(indices[0])[2];
		int gamesPlayed2 = conferenceRecords.elementAt(indices[1])[0] + conferenceRecords.elementAt(indices[1])[1] + conferenceRecords.elementAt(indices[1])[2];
		int gamesPlayed3 = conferenceRecords.elementAt(indices[2])[0] + conferenceRecords.elementAt(indices[2])[1] + conferenceRecords.elementAt(indices[2])[2];
		int gamesPlayed4 = conferenceRecords.elementAt(indices[3])[0] + conferenceRecords.elementAt(indices[3])[1] + conferenceRecords.elementAt(indices[3])[2];
		
		if (gamesPlayed1 == 0)
			values[0] = 0.0;
		else{
			values[0] =  (double)( (double)(conferenceRecords.elementAt(indices[0])[0] + (1/2) * (double)(conferenceRecords.elementAt(indices[0])[1])) / gamesPlayed1);
		}
			
		if (gamesPlayed2 == 0)
			values[1] = 0.0;
		else{
			values[1] =  (double)( (double)(conferenceRecords.elementAt(indices[1])[0] + (1/2) * (double)(conferenceRecords.elementAt(indices[1])[1])) / gamesPlayed2);		
		}
		
		if (gamesPlayed3 == 0)
			values[2] = 0.0;
		else
			values[2] = (double)( (double)(conferenceRecords.elementAt(indices[2])[0] + (1/2) * (double)(conferenceRecords.elementAt(indices[2])[1])) / gamesPlayed3);
	
		if (gamesPlayed4 == 0)
			values[3] = 0.0;
		else
			values[3] = (double)( (double)(conferenceRecords.elementAt(indices[3])[0] + (1/2) * (double)(conferenceRecords.elementAt(indices[3])[1])) / gamesPlayed4);
			
		result = getStatisticalLeader(4, teamNames, values);
		
		if (result.equals("null")){
			if (conferenceRecords.elementAt(indices[0])[1] == 0 && conferenceRecords.elementAt(indices[0])[0] != 0){
				values[0] = conferenceRecords.elementAt(indices[0])[0];
				values[1] = conferenceRecords.elementAt(indices[1])[0];
				values[2] = conferenceRecords.elementAt(indices[2])[0];
				values[3] = conferenceRecords.elementAt(indices[3])[0];
				result = getStatisticalLeader(4, teamNames, values);
			}
			else if (conferenceRecords.elementAt(indices[0])[0] == 0 && conferenceRecords.elementAt(indices[0])[1] != 0){
				values[0] = 0 - conferenceRecords.elementAt(indices[0])[1];
				values[1] = 0 - conferenceRecords.elementAt(indices[1])[1];			
				values[2] = 0 - conferenceRecords.elementAt(indices[2])[1];
				values[3] = 0 - conferenceRecords.elementAt(indices[3])[1];
				result = getStatisticalLeader(4, teamNames, values);
			}
		}
	}
	// Point differential tiebreaker 
	if (result.equals("null")){
		values[0] = teamStats.elementAt(indices[0]).getPointsScored() - teamStats.elementAt(indices[0]).getPointsAgainst();
		values[1] = teamStats.elementAt(indices[1]).getPointsScored() - teamStats.elementAt(indices[1]).getPointsAgainst();
		values[2] = teamStats.elementAt(indices[2]).getPointsScored() - teamStats.elementAt(indices[2]).getPointsAgainst();
		values[3] = teamStats.elementAt(indices[3]).getPointsScored() - teamStats.elementAt(indices[3]).getPointsAgainst();
	
		result = getStatisticalLeader(4, teamNames, values);
		if (result.equals("null")){
			values[0] = teamStats.elementAt(indices[0]).getPointsScored();
			values[1] = teamStats.elementAt(indices[1]).getPointsScored();		
			values[2] = teamStats.elementAt(indices[2]).getPointsScored();
			values[3] = teamStats.elementAt(indices[3]).getPointsScored();
			result = getStatisticalLeader(4, teamNames, values);
		}
		if (result.equals("null")){
			values[0] = 0 - teamStats.elementAt(indices[0]).getPointsAgainst();
			values[1] = 0 - teamStats.elementAt(indices[1]).getPointsAgainst();			
			values[2] = 0 - teamStats.elementAt(indices[2]).getPointsAgainst();
			values[3] = 0 - teamStats.elementAt(indices[3]).getPointsAgainst();
			result = getStatisticalLeader(4, teamNames, values);
		}
		
	}
	// Coin flip
	if (result.equals("null")){
		int flip = (int)(Math.random() * 1000);
		if (flip % 4 == 0)
			result = teamNames[0];
		else if (flip % 4 == 1)
			result = teamNames[1];
		else if (flip % 4 == 2)
			result = teamNames[2];
		else
			result = teamNames[3];
	}		

	return result;
}

String perform5TeamTiebreaker(String team1, String team2, String team3, String team4, String team5){
	System.out.println("5-Team Tiebreaker");
	String [] teamNames = new String[5];
	int [] indices = new int[5];
	double [] values = new double[5];
	
	for (int i =0; i < 5; i++){
		values[i] = 0.0;
		indices[i] = -1;
	}

	teamNames[0] = team1;
	teamNames[1] = team2;
	teamNames[2] = team3;
	teamNames[3] = team4;
	teamNames[4] = team5;
	
	for (int i = 0; i < teams.size(); i++){
		if (teams.elementAt(i).equals(team1.replaceAll("_"," ")))
			indices[0] = i;
		if (teams.elementAt(i).equals(team2.replaceAll("_"," ")))
			indices[1] = i;
		if (teams.elementAt(i).equals(team3.replaceAll("_"," ")))
			indices[2] = i;
		if (teams.elementAt(i).equals(team4.replaceAll("_"," ")))
			indices[3] = i;
		if (teams.elementAt(i).equals(team5.replaceAll("_"," ")))
			indices[4] = i;
	}

	// Same win percentage, different win totals
	values[0] = teamRecords.elementAt(indices[0])[0];
	values[1] = teamRecords.elementAt(indices[1])[0];
	values[2] = teamRecords.elementAt(indices[2])[0];
	values[3] = teamRecords.elementAt(indices[3])[0];
	values[4] = teamRecords.elementAt(indices[4])[0];
	String result = getStatisticalLeader(5, teamNames, values);
	
	// Same win percentage, different loss totals
	if (result.equals("null")){
		values[0] = 0 - teamRecords.elementAt(indices[0])[1];
		values[1] = 0 - teamRecords.elementAt(indices[1])[1];
		values[2] = 0 - teamRecords.elementAt(indices[2])[1];
		values[3] = 0 - teamRecords.elementAt(indices[3])[1];
		values[4] = 0 - teamRecords.elementAt(indices[4])[1];
		result = getStatisticalLeader(5, teamNames, values);
	}
	// Head-to-head tiebreaker
	if (result.equals("null")){
		int [] wins = new int[5];
		int [] losses = new int[5];
		int [] ties = new int[5];
		
		for (int i = 0; i < 5; i++){
			wins[i] = 0;
			losses[i] = 0;
			ties[i] = 0;
		}		
		
		for (int i = 0; i < currentWeek + 1; i++){
			// If teams have played each other (this is right)
			if (schedule.elementAt(indices[0])[i][0] == indices[1]){			
				if (scores.elementAt(indices[0])[i][0] > scores.elementAt(indices[0])[i][1]){
					wins[0]++;
					losses[1]++;
				}
				else if (scores.elementAt(indices[0])[i][0] < scores.elementAt(indices[0])[i][1]){
					losses[0]++;
					wins[1]++;
				}
				else{
					ties[0]++;
					ties[1]++;
				}
			}
			if (schedule.elementAt(indices[0])[i][0] == indices[2]){			
				if (scores.elementAt(indices[0])[i][0] > scores.elementAt(indices[0])[i][1]){
					wins[0]++;
					losses[2]++;
				}
				else if (scores.elementAt(indices[0])[i][0] < scores.elementAt(indices[0])[i][1]){
					losses[0]++;
					wins[2]++;
				}
				else{
					ties[0]++;
					ties[2]++;
				}
			}
			if (schedule.elementAt(indices[0])[i][0] == indices[3]){			
				if (scores.elementAt(indices[0])[i][0] > scores.elementAt(indices[0])[i][1]){
					wins[0]++;
					losses[3]++;
				}
				else if (scores.elementAt(indices[0])[i][0] < scores.elementAt(indices[0])[i][1]){
					losses[0]++;
					wins[3]++;
				}
				else{
					ties[0]++;
					ties[3]++;
				}
			}
			if (schedule.elementAt(indices[0])[i][0] == indices[4]){			
				if (scores.elementAt(indices[0])[i][0] > scores.elementAt(indices[0])[i][1]){
					wins[0]++;
					losses[4]++;
				}
				else if (scores.elementAt(indices[0])[i][0] < scores.elementAt(indices[0])[i][1]){
					losses[0]++;
					wins[4]++;
				}
				else{
					ties[0]++;
					ties[4]++;
				}
			}
			if (schedule.elementAt(indices[1])[i][0] == indices[2]){			
				if (scores.elementAt(indices[1])[i][0] > scores.elementAt(indices[1])[i][1]){
					wins[1]++;
					losses[2]++;
				}
				else if (scores.elementAt(indices[1])[i][0] < scores.elementAt(indices[1])[i][1]){
					losses[1]++;
					wins[2]++;
				}
				else{
					ties[1]++;
					ties[2]++;
				}
			}
			if (schedule.elementAt(indices[1])[i][0] == indices[3]){			
				if (scores.elementAt(indices[1])[i][0] > scores.elementAt(indices[1])[i][1]){
					wins[1]++;
					losses[3]++;
				}
				else if (scores.elementAt(indices[1])[i][0] < scores.elementAt(indices[1])[i][1]){
					losses[1]++;
					wins[3]++;
				}
				else{
					ties[1]++;
					ties[3]++;
				}
			}
			if (schedule.elementAt(indices[1])[i][0] == indices[4]){			
				if (scores.elementAt(indices[1])[i][0] > scores.elementAt(indices[1])[i][1]){
					wins[1]++;
					losses[4]++;
				}
				else if (scores.elementAt(indices[1])[i][0] < scores.elementAt(indices[1])[i][1]){
					losses[1]++;
					wins[4]++;
				}
				else{
					ties[1]++;
					ties[4]++;
				}
			}
			if (schedule.elementAt(indices[2])[i][0] == indices[3]){			
				if (scores.elementAt(indices[2])[i][0] > scores.elementAt(indices[2])[i][1]){
					wins[2]++;
					losses[3]++;
				}
				else if (scores.elementAt(indices[2])[i][0] < scores.elementAt(indices[2])[i][1]){
					losses[2]++;
					wins[3]++;
				}
				else{
					ties[2]++;
					ties[3]++;
				}
			}
			if (schedule.elementAt(indices[2])[i][0] == indices[4]){			
				if (scores.elementAt(indices[2])[i][0] > scores.elementAt(indices[2])[i][1]){
					wins[2]++;
					losses[4]++;
				}
				else if (scores.elementAt(indices[2])[i][0] < scores.elementAt(indices[2])[i][1]){
					losses[2]++;
					wins[4]++;
				}
				else{
					ties[2]++;
					ties[4]++;
				}
			}
			if (schedule.elementAt(indices[3])[i][0] == indices[4]){			
				if (scores.elementAt(indices[3])[i][0] > scores.elementAt(indices[3])[i][1]){
					wins[3]++;
					losses[4]++;
				}
				else if (scores.elementAt(indices[3])[i][0] < scores.elementAt(indices[3])[i][1]){
					losses[3]++;
					wins[4]++;
				}
				else{
					ties[3]++;
					ties[4]++;
				}
			}
		}
		
		int gamesPlayed1 = wins[0] + losses[0] + ties[0];		
		int gamesPlayed2 = wins[1] + losses[1] + ties[1];
		int gamesPlayed3 = wins[2] + losses[2] + ties[2];
		int gamesPlayed4 = wins[3] + losses[3] + ties[3];
		int gamesPlayed5 = wins[4] + losses[4] + ties[4];
		
		if (gamesPlayed1 == 0)
			values[0] = 0.0;
		else
			values[0] =  (double)( (double)(wins[0] + (1/2) * (double)(ties[0]) / gamesPlayed1));
			
		if (gamesPlayed2 == 0)
			values[1] = 0.0;
		else
			values[1] =  (double)( (double)(wins[1] + (1/2) * (double)(ties[1]) / gamesPlayed2));
			
		if (gamesPlayed3 == 0)
			values[2] = 0.0;
		else
			values[2] =  (double)( (double)(wins[2] + (1/2) * (double)(ties[2]) / gamesPlayed3));
			
		if (gamesPlayed4 == 0)
			values[3] = 0.0;
		else
			values[3] =  (double)( (double)(wins[3] + (1/2) * (double)(ties[3]) / gamesPlayed4));
			
		if (gamesPlayed5 == 0)
			values[4] = 0.0;
		else
			values[4] =  (double)( (double)(wins[4] + (1/2) * (double)(ties[4]) / gamesPlayed5));
		
		result = getStatisticalLeader(5, teamNames, values);
	}
	// Divisional record tiebreaker
	if (result.equals("null")){
		int gamesPlayed1 = divisionRecords.elementAt(indices[0])[0] + divisionRecords.elementAt(indices[0])[1] + divisionRecords.elementAt(indices[0])[2];
		int gamesPlayed2 = divisionRecords.elementAt(indices[1])[0] + divisionRecords.elementAt(indices[1])[1] + divisionRecords.elementAt(indices[1])[2];
		int gamesPlayed3 = divisionRecords.elementAt(indices[2])[0] + divisionRecords.elementAt(indices[2])[1] + divisionRecords.elementAt(indices[2])[2];
		int gamesPlayed4 = divisionRecords.elementAt(indices[3])[0] + divisionRecords.elementAt(indices[3])[1] + divisionRecords.elementAt(indices[3])[2];
		int gamesPlayed5 = divisionRecords.elementAt(indices[4])[0] + divisionRecords.elementAt(indices[4])[1] + divisionRecords.elementAt(indices[4])[2];
		
		if (gamesPlayed1 == 0)
			values[0] = 0.0;
		else{
			values[0] =  (double)( (double)(divisionRecords.elementAt(indices[0])[0] + (1/2) * (double)(divisionRecords.elementAt(indices[0])[1])) / gamesPlayed1);
		}
			
		if (gamesPlayed2 == 0)
			values[1] = 0.0;
		else{
			values[1] =  (double)( (double)(divisionRecords.elementAt(indices[1])[0] + (1/2) * (double)(divisionRecords.elementAt(indices[1])[1])) / gamesPlayed2);		
		}
		
		if (gamesPlayed3 == 0)
			values[2] = 0.0;
		else
			values[2] = (double)( (double)(divisionRecords.elementAt(indices[2])[0] + (1/2) * (double)(divisionRecords.elementAt(indices[2])[1])) / gamesPlayed3);
			
		if (gamesPlayed4 == 0)
			values[3] = 0.0;
		else
			values[3] = (double)( (double)(divisionRecords.elementAt(indices[3])[0] + (1/2) * (double)(divisionRecords.elementAt(indices[3])[1])) / gamesPlayed4);

		if (gamesPlayed5 == 0)
			values[4] = 0.0;
		else
			values[4] = (double)( (double)(divisionRecords.elementAt(indices[4])[0] + (1/2) * (double)(divisionRecords.elementAt(indices[4])[1])) / gamesPlayed5);
			
		result = getStatisticalLeader(5, teamNames, values);
		
		if (result.equals("null")){
			if (divisionRecords.elementAt(indices[0])[1] == 0 && divisionRecords.elementAt(indices[0])[0] != 0){
				values[0] = divisionRecords.elementAt(indices[0])[0];
				values[1] = divisionRecords.elementAt(indices[1])[0];
				values[2] = divisionRecords.elementAt(indices[2])[0];
				values[3] = divisionRecords.elementAt(indices[3])[0];
				values[4] = divisionRecords.elementAt(indices[4])[0];
				result = getStatisticalLeader(5, teamNames, values);
			}
			else if (divisionRecords.elementAt(indices[0])[0] == 0 && divisionRecords.elementAt(indices[0])[1] != 0){
				values[0] = 0 - divisionRecords.elementAt(indices[0])[1];
				values[1] = 0 - divisionRecords.elementAt(indices[1])[1];			
				values[2] = 0 - divisionRecords.elementAt(indices[2])[1];
				values[3] = 0 - divisionRecords.elementAt(indices[3])[1];
				values[4] = 0 - divisionRecords.elementAt(indices[4])[1];
				result = getStatisticalLeader(5, teamNames, values);
			}
		}
		
		
	}
	// Conference record tiebreaker
	if (result.equals("null")){
		int gamesPlayed1 = conferenceRecords.elementAt(indices[0])[0] + conferenceRecords.elementAt(indices[0])[1] + conferenceRecords.elementAt(indices[0])[2];
		int gamesPlayed2 = conferenceRecords.elementAt(indices[1])[0] + conferenceRecords.elementAt(indices[1])[1] + conferenceRecords.elementAt(indices[1])[2];
		int gamesPlayed3 = conferenceRecords.elementAt(indices[2])[0] + conferenceRecords.elementAt(indices[2])[1] + conferenceRecords.elementAt(indices[2])[2];
		int gamesPlayed4 = conferenceRecords.elementAt(indices[3])[0] + conferenceRecords.elementAt(indices[3])[1] + conferenceRecords.elementAt(indices[3])[2];
		int gamesPlayed5 = conferenceRecords.elementAt(indices[4])[0] + conferenceRecords.elementAt(indices[4])[1] + conferenceRecords.elementAt(indices[4])[2];
		
		if (gamesPlayed1 == 0)
			values[0] = 0.0;
		else{
			values[0] =  (double)( (double)(conferenceRecords.elementAt(indices[0])[0] + (1/2) * (double)(conferenceRecords.elementAt(indices[0])[1])) / gamesPlayed1);
		}
			
		if (gamesPlayed2 == 0)
			values[1] = 0.0;
		else{
			values[1] =  (double)( (double)(conferenceRecords.elementAt(indices[1])[0] + (1/2) * (double)(conferenceRecords.elementAt(indices[1])[1])) / gamesPlayed2);		
		}
		
		if (gamesPlayed3 == 0)
			values[2] = 0.0;
		else
			values[2] = (double)( (double)(conferenceRecords.elementAt(indices[2])[0] + (1/2) * (double)(conferenceRecords.elementAt(indices[2])[1])) / gamesPlayed3);
	
		if (gamesPlayed4 == 0)
			values[3] = 0.0;
		else
			values[3] = (double)( (double)(conferenceRecords.elementAt(indices[3])[0] + (1/2) * (double)(conferenceRecords.elementAt(indices[3])[1])) / gamesPlayed4);

		if (gamesPlayed5 == 0)
			values[4] = 0.0;
		else
			values[4] = (double)( (double)(conferenceRecords.elementAt(indices[4])[0] + (1/2) * (double)(conferenceRecords.elementAt(indices[4])[1])) / gamesPlayed5);

			
		result = getStatisticalLeader(5, teamNames, values);
		
		if (result.equals("null")){
			if (conferenceRecords.elementAt(indices[0])[1] == 0 && conferenceRecords.elementAt(indices[0])[0] != 0){
				values[0] = conferenceRecords.elementAt(indices[0])[0];
				values[1] = conferenceRecords.elementAt(indices[1])[0];
				values[2] = conferenceRecords.elementAt(indices[2])[0];
				values[3] = conferenceRecords.elementAt(indices[3])[0];
				values[4] = conferenceRecords.elementAt(indices[4])[0];
				result = getStatisticalLeader(5, teamNames, values);
			}
			else if (conferenceRecords.elementAt(indices[0])[0] == 0 && conferenceRecords.elementAt(indices[0])[1] != 0){
				values[0] = 0 - conferenceRecords.elementAt(indices[0])[0];
				values[1] = 0 - conferenceRecords.elementAt(indices[1])[0];			
				values[2] = 0 - conferenceRecords.elementAt(indices[2])[0];
				values[3] = 0 - conferenceRecords.elementAt(indices[3])[0];
				values[4] = 0 - conferenceRecords.elementAt(indices[4])[0];
				result = getStatisticalLeader(5, teamNames, values);
			}
		}
	}
	// Point differential tiebreaker 
	if (result.equals("null")){
		values[0] = teamStats.elementAt(indices[0]).getPointsScored() - teamStats.elementAt(indices[0]).getPointsAgainst();
		values[1] = teamStats.elementAt(indices[1]).getPointsScored() - teamStats.elementAt(indices[1]).getPointsAgainst();
		values[2] = teamStats.elementAt(indices[2]).getPointsScored() - teamStats.elementAt(indices[2]).getPointsAgainst();
		values[3] = teamStats.elementAt(indices[3]).getPointsScored() - teamStats.elementAt(indices[3]).getPointsAgainst();
		values[4] = teamStats.elementAt(indices[4]).getPointsScored() - teamStats.elementAt(indices[4]).getPointsAgainst();
	
		result = getStatisticalLeader(5, teamNames, values);
		if (result.equals("null")){
			values[0] = teamStats.elementAt(indices[0]).getPointsScored();
			values[1] = teamStats.elementAt(indices[1]).getPointsScored();		
			values[2] = teamStats.elementAt(indices[2]).getPointsScored();
			values[3] = teamStats.elementAt(indices[3]).getPointsScored();
			values[4] = teamStats.elementAt(indices[4]).getPointsScored();
			result = getStatisticalLeader(5, teamNames, values);
		}
		if (result.equals("null")){
			values[0] = 0 - teamStats.elementAt(indices[0]).getPointsAgainst();
			values[1] = 0 - teamStats.elementAt(indices[1]).getPointsAgainst();			
			values[2] = 0 - teamStats.elementAt(indices[2]).getPointsAgainst();
			values[3] = 0 - teamStats.elementAt(indices[3]).getPointsAgainst();
			values[4] = 0 - teamStats.elementAt(indices[4]).getPointsAgainst();
			result = getStatisticalLeader(5, teamNames, values);
		}
		
	}
	// Coin flip
	if (result.equals("null")){
		int flip = (int)(Math.random() * 1000);
		if (flip % 5 == 0)
			result = teamNames[0];
		else if (flip % 5 == 1)
			result = teamNames[1];
		else if (flip % 5 == 2)
			result = teamNames[2];
		else if (flip % 5 == 3)
			result = teamNames[3];
		else
			result = teamNames[4];
	}		

	return result;
}


/* // New version.  Needs to be tested further
        void setConferenceRankings(){
                // Goes through each team in the conference and ranks them 1-n
                boolean twoConferences = true;

                // Set this two 8 because an 8 team league has 2 divisions, not conferences
                if (numberOfTeams <= 8)
                        twoConferences = false;

                if (twoConferences){
                        Object[][] conferenceTeams = new Object[numberOfTeams][6];

                        int idx = 0;

                        // Gather team names and team records to perform the sort
                        for (int j = 0; j < teams.size(); j++){
                                conferenceTeams[idx][0] = String.valueOf(teams.elementAt(j));
                                conferenceTeams[idx][1] = Integer.valueOf(teamRecords.elementAt(j)[0]);
                                conferenceTeams[idx][2] = Integer.valueOf(teamRecords.elementAt(j)[1]);
                                conferenceTeams[idx][3] = Integer.valueOf(teamRecords.elementAt(j)[2]);
                                conferenceTeams[idx][4] = Integer.valueOf(rankings.elementAt(j)[0]);

                                int sumGames = teamRecords.elementAt(j)[0] + teamRecords.elementAt(j)[1] + teamRecords.elementAt(j)[2];

                                // In case no games have yet been played...
                                if (sumGames == 0)
                                        sumGames = 1;


                                // Set the win percentage as a label of "0.000"
                                DecimalFormat threeDForm = new DecimalFormat("0.000");
                                conferenceTeams[idx][5] = Double.valueOf( threeDForm.format((Double.parseDouble(teamRecords.elementAt(j)[0].toString()) + (Double.parseDouble(teamRecords.elementAt(j)[2].toString()) / 2) ) / sumGames ));

                                idx++;
                        }

                        // Perform the sort on each conference (separately).  First to just get the basic order, then to get the tiebreakers figured out
                        // Conference 1
                        for (int j = 0; j < numberOfTeams / 2; j++){
                                for (int k = j + 1; k < numberOfTeams / 2; k++){
                                        if (Double.parseDouble(conferenceTeams[k][5].toString()) > Double.parseDouble(conferenceTeams[j][5].toString())){
                                                Object[] tmp = conferenceTeams[j];
                                                conferenceTeams[j] = conferenceTeams[k];
                                                conferenceTeams[k] = tmp;
                                        }
                                        else if (Integer.parseInt(conferenceTeams[k][4].toString()) < Integer.parseInt(conferenceTeams[k][4].toString())){
                                                        Object[] tmp = conferenceTeams[j];
                                                        conferenceTeams[j] = conferenceTeams[k];
                                                        conferenceTeams[k] = tmp;
                                        }

                                }
                        }
						
						// Perform the tirebreakers here.
						for (int j = 0; j < numberOfTeams / 2; j++){
							int ties = 0;
							Vector<String> tiedTeams = new Vector<String>();
							for (int k = j + 1; k < numberOfTeams / 2; k++){
								if (Double.parseDouble(conferenceTeams[k][4].toString()) == Double.parseDouble(conferenceTeams[j][4].toString())){
									ties++;
									tiedTeams.add(conferenceTeams[k][0].toString());
								}
							}
							

							if (ties > 1)
								ties = 1;
								
							String winningTeam = "";
							switch(ties){
								case 1:{
									winningTeam = perform2TeamTiebreaker(conferenceTeams[j][0].toString(), tiedTeams.elementAt(0));
									break;
								}
								case 2:{
									winningTeam = perform3TeamTiebreaker(conferenceTeams[j][0].toString(), tiedTeams.elementAt(0), tiedTeams.elementAt(1));
									break;
								}
								case 3:{
									winningTeam = perform4TeamTiebreaker(conferenceTeams[j][0].toString(), tiedTeams.elementAt(0), tiedTeams.elementAt(1), tiedTeams.elementAt(2));
									break;
								}
								case 4:{
									winningTeam = perform5TeamTiebreaker(conferenceTeams[j][0].toString(), tiedTeams.elementAt(0), tiedTeams.elementAt(1), tiedTeams.elementAt(2), tiedTeams.elementAt(3));
									break;
								}
								default:{break;}
							}
							
							if (winningTeam != ""){
								int topIndex = -1;
								for (int k = 0; k < numberOfTeams / 2; k++){
									if (conferenceTeams[k][0].toString().equals(winningTeam))
										topIndex = k;
								}
								
								Object[] tmp = conferenceTeams[j];
                                conferenceTeams[j] = conferenceTeams[topIndex];
                                conferenceTeams[topIndex] = tmp;
							
							}						
						
						
						
						}

                        // Conference 2
                        for (int j = numberOfTeams / 2; j < numberOfTeams; j++){
                                for (int k = j + 1; k < numberOfTeams; k++){
                                        if (Double.parseDouble(conferenceTeams[k][5].toString()) > Double.parseDouble(conferenceTeams[j][5].toString())){
                                                Object[] tmp = conferenceTeams[j];
                                                conferenceTeams[j] = conferenceTeams[k];
                                                conferenceTeams[k] = tmp;
                                        }
                                        else if (Integer.parseInt(conferenceTeams[k][4].toString()) < Integer.parseInt(conferenceTeams[k][4].toString())){
                                                Object[] tmp = conferenceTeams[j];
                                                conferenceTeams[j] = conferenceTeams[k];
                                                conferenceTeams[k] = tmp;
                                        }
                                }
                        }
						
						// Perform the tirebreakers here.
						for (int j = numberOfTeams / 2; j < numberOfTeams; j++){
							int ties = 0;
							Vector<String> tiedTeams = new Vector<String>();
							for (int k = j + 1; k < numberOfTeams / 2; k++){
								if (Double.parseDouble(conferenceTeams[k][4].toString()) == Double.parseDouble(conferenceTeams[j][4].toString())){
									ties++;
									tiedTeams.add(conferenceTeams[k][0].toString());
								}
							}
							
							// Should be ties > 4
							if (ties > 1)
								ties = 1;
								
							String winningTeam = "";
							switch(ties){
								case 1:{
									winningTeam = perform2TeamTiebreaker(conferenceTeams[j][0].toString(), tiedTeams.elementAt(0));
									break;
								}
								case 2:{
									winningTeam = perform3TeamTiebreaker(conferenceTeams[j][0].toString(), tiedTeams.elementAt(0), tiedTeams.elementAt(1));
									break;
								}
								case 3:{
									winningTeam = perform4TeamTiebreaker(conferenceTeams[j][0].toString(), tiedTeams.elementAt(0), tiedTeams.elementAt(1), tiedTeams.elementAt(2));
									break;
								}
								case 4:{
									winningTeam = perform5TeamTiebreaker(conferenceTeams[j][0].toString(), tiedTeams.elementAt(0), tiedTeams.elementAt(1), tiedTeams.elementAt(2), tiedTeams.elementAt(3));
									break;
								}
								default:{break;}
							}
							
							if (winningTeam != ""){
								int topIndex = -1;
								for (int k = numberOfTeams / 2; k < numberOfTeams; k++){
									if (conferenceTeams[k][0].toString().equals(winningTeam))
										topIndex = k;
								}
								
								Object[] tmp = conferenceTeams[j];
                                conferenceTeams[j] = conferenceTeams[topIndex];
                                conferenceTeams[topIndex] = tmp;
							
							}							
						
						
						
						}

                        for(int i = 0; i < numberOfTeams / 2; i++){
                                for (int j = 0; j < numberOfTeams / 2; j++){
                                        if (teams.elementAt(j).equals(conferenceTeams[i][1].toString()))
                                                rankings.elementAt(j)[1] = i + 1;
                                }
                        }

                        for(int i = numberOfTeams / 2; i < numberOfTeams; i++){
                                for (int j = 0; j < numberOfTeams; j++){
                                        if (teams.elementAt(j).equals(conferenceTeams[i][1].toString()))
                                                rankings.elementAt(j)[1] = i - (numberOfTeams / 2) + 1;
                                }
                        }
                }
                else{
                        // If there are not two conference, simply set the conference rank to the division rank
                        for (int i = 0; i < numberOfTeams; i++){
                                rankings.elementAt(i)[1] = rankings.elementAt(i)[0];
                        }
                }
        }
*/

	JPanel getStandings(){
		standingsPanel = new JPanel();
		standingsPanel.removeAll();
		standingsPanel.setLayout(null);
	//	standingsPanel.setOpaque(false);
		standingsPanel.setBackground(Color.BLACK);

		JPanel choicePanel = new JPanel();
		choicePanel.setLayout(null);
		choicePanel.setOpaque(false);

		displayPanel = getDivisionalStandings();


		final JComboBox<String> standingsList = new JComboBox<String>();
		standingsList.addItem("Divisional Standings");
		standingsList.addItem("Playoff Standings");

		standingsList.setBounds(0,0,300,30);
		choicePanel.add(standingsList);


		standingsList.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if (standingsList.getSelectedItem().equals("Divisional Standings")){
					standingsPanel.remove(displayPanel);
					standingsPanel.setVisible(false);
					displayPanel.setVisible(false);
					displayPanel = null;
					displayPanel = getDivisionalStandings();
					displayPanel.setBounds(0,35,350,yLocation);
					standingsPanel.add(displayPanel);
					standingsPanel.setVisible(true);
					standingsPanel.setBackground(Color.BLACK);

				}
				else{
					standingsPanel.remove(displayPanel);
					standingsPanel.setVisible(false);
					displayPanel.setVisible(false);
					displayPanel = null;
					displayPanel = displayPlayoffStandings();
					displayPanel.setBounds(0,35,350,yLocation);
					standingsPanel.add(displayPanel);
					standingsPanel.setVisible(true);
					standingsPanel.setBackground(Color.BLACK);
				}
			}
		});

		choicePanel.setBounds(0,0,350,35);
		displayPanel.setBounds(0,35,350,yLocation);

		standingsPanel.add(choicePanel);
		standingsPanel.add(displayPanel);

		return standingsPanel;

	}

/*	// This is the new version.  Needs to be tested further
	JPanel getDivisionalStandings(){
		JPanel divStandings = new JPanel();
		divStandings.setLayout(null);
		divStandings.setOpaque(false);

		int yLocation = 0;
		boolean skewedSetup = false;
		boolean swapped = false;
		if (teams.size() == 16 || teams.size() == 20)
			skewedSetup = true;

		for (int i = 0; i < 11; i++){
			int teamsInDivision = 0;

			if (i == 6 && skewedSetup && !swapped)
				i = 9;
			if (i == 10 && skewedSetup && !swapped){
				i = 6;
				swapped = true;
			}
			if (i == 9 && skewedSetup && swapped)
				i = 10;

			for (int j = 0; j < teams.size(); j++){
				if (teamDivisions.elementAt(j) == i)
					teamsInDivision++;
			}

			if (teamsInDivision == 0)
				continue;

			JLabel divisionLabel = new JLabel(divisions[i] + " Division");
			JLabel winLossLabel = new JLabel("W       L       T       %");

			divisionLabel.setForeground(Color.WHITE);
			winLossLabel.setForeground(Color.WHITE);

			divisionLabel.setBounds(5,yLocation,300,30);
			winLossLabel.setBounds(177,yLocation + 20,200,20);


			divStandings.add(divisionLabel);
			divStandings.add(winLossLabel);

			yLocation += 50;

			Object[][] divisionalTeams = new Object[teamsInDivision][6];

			int idx = 0;
			// Gather team names and team records to perform the sort
			for (int j = 0; j < teams.size(); j++){
				if (teamDivisions.elementAt(j) == i){
					divisionalTeams[idx][0] = String.valueOf(teams.elementAt(j));
					divisionalTeams[idx][1] = Integer.valueOf(teamRecords.elementAt(j)[0]);
					divisionalTeams[idx][2] = Integer.valueOf(teamRecords.elementAt(j)[1]);
					divisionalTeams[idx][3] = Integer.valueOf(teamRecords.elementAt(j)[2]);
					divisionalTeams[idx][5] = "";

					int sumGames = teamRecords.elementAt(j)[0] + teamRecords.elementAt(j)[1] + teamRecords.elementAt(j)[2];

					// In case no games have yet been played...
					if (sumGames == 0)
						sumGames = 1;


					// Set the win percentage as a label of "0.000"
					DecimalFormat threeDForm = new DecimalFormat("0.000");
					divisionalTeams[idx][4] = Double.valueOf( threeDForm.format((Double.parseDouble(teamRecords.elementAt(j)[0].toString()) + (Double.parseDouble(teamRecords.elementAt(j)[2].toString()) / 2) ) / sumGames ));



					idx++;
				}
			}

			// Perform the sort on the divisional teams and place them in order
			for (int j = 0; j < teamsInDivision; j++){
				for (int k = j + 1; k < teamsInDivision; k++){
					if (Double.parseDouble(divisionalTeams[k][4].toString()) > Double.parseDouble(divisionalTeams[j][4].toString())){
						Object[] tmp = divisionalTeams[j];
						divisionalTeams[j] = divisionalTeams[k];
						divisionalTeams[k] = tmp;
					}

				}
			}
						// Start second for loop to perform all tiebreakers necessary
						for (int j = 0; j < teamsInDivision; j++){
							int ties = 0;
							Vector<String> tiedTeams = new Vector<String>();
							for (int k = j + 1; k < teamsInDivision; k++){
								if (Double.parseDouble(divisionalTeams[k][4].toString()) == Double.parseDouble(divisionalTeams[j][4].toString())){
									ties++;
									tiedTeams.add(divisionalTeams[k][0].toString());
								}
							}
							
							String winningTeam = "";


							if (ties > 1)
								ties = 1;

							switch(ties){
								case 1:{
									winningTeam = perform2TeamTiebreaker(divisionalTeams[j][0].toString(), tiedTeams.elementAt(0));
									break;
								}
								case 2:{
									winningTeam = perform3TeamTiebreaker(divisionalTeams[j][0].toString(), tiedTeams.elementAt(0), tiedTeams.elementAt(1));
									break;
								}
								case 3:{
									winningTeam = perform4TeamTiebreaker(divisionalTeams[j][0].toString(), tiedTeams.elementAt(0), tiedTeams.elementAt(1), tiedTeams.elementAt(2));
									break;
								}
								case 4:{
									winningTeam = perform5TeamTiebreaker(divisionalTeams[j][0].toString(), tiedTeams.elementAt(0), tiedTeams.elementAt(1), tiedTeams.elementAt(2), tiedTeams.elementAt(3));
									break;
								}
								default:{break;}
							}
							
							if (winningTeam != ""){
								int topIndex = -1;
								for (int k = 0; k < teamsInDivision; k++){
									if (divisionalTeams[k][0].toString().equals(winningTeam))
										topIndex = k;
								}
								
								Object[] tmp = divisionalTeams[j];
                                divisionalTeams[j] = divisionalTeams[topIndex];
                                divisionalTeams[topIndex] = tmp;
							
							}
							
						}


			for (int j = 0; j < teamsInDivision; j++){
				String placeName = divisionalTeams[j][0].toString();

				for (int k = 0; k < numberOfTeams; k++){
					if (teams.elementAt(k).equals(placeName)){
						rankings.elementAt(k)[0] = j + 1;

					}
				}
			}

		//	if (!gameLoaded || !inPlayoffs()){
				setConferenceRankings();
				setPlayoffRankings();
		//	}

		//	for (int k = 0; k < numberOfTeams; k++)
		//		System.out.println(teams.elementAt(k).toString() + " CR = " + rankings.elementAt(k)[1] + " PR = " + rankings.elementAt(k)[2]);

			for (int j = 0; j < teamsInDivision; j++){

				// Set labels to be displayed on the panel and add to panel
				JLabel teamName = new JLabel( new ImageIcon(getClass().getResource("images/slivers/" + teamMap.get(divisionalTeams[j][0].toString()) + "_Sliver.png")) );
				JLabel teamWins = new JLabel(divisionalTeams[j][1].toString());
				JLabel teamLosses = new JLabel(divisionalTeams[j][2].toString());
				JLabel teamTies = new JLabel(divisionalTeams[j][3].toString());
				JLabel teamPercentage = new JLabel(divisionalTeams[j][4].toString());

				int teamIndex = 0;
				for (int k = 0; k < numberOfTeams; k++){
					if (rankings.elementAt(k)[0] == (j + 1) && teamDivisions.elementAt(k) == i)
						teamIndex = k;
				}

				if (rankings.elementAt(teamIndex)[2] == 1)
					divisionalTeams[j][5] = "x";
				else if (rankings.elementAt(teamIndex)[2] == 2)
					divisionalTeams[j][5] = "y";
				else if (rankings.elementAt(teamIndex)[2] == 3)
					divisionalTeams[j][5] = "z";
				else if (rankings.elementAt(teamIndex)[2] == 99)
					divisionalTeams[j][5] = "e";

				JLabel playoffIndicator = new JLabel(divisionalTeams[j][5].toString());


				playoffIndicator.setBounds(5,yLocation,10,20);
				teamName.setBounds(15,yLocation,150,20);
				teamWins.setBounds(190,yLocation,30,20);
				teamLosses.setBounds(230,yLocation,30,20);
				teamTies.setBounds(270,yLocation,30,20);
				teamPercentage.setBounds(310,yLocation,100,20);

				playoffIndicator.setForeground(Color.WHITE);
				teamWins.setForeground(Color.WHITE);
				teamLosses.setForeground(Color.WHITE);
				teamTies.setForeground(Color.WHITE);
				teamPercentage.setForeground(Color.WHITE);

				divStandings.add(playoffIndicator);
				divStandings.add(teamName);
				divStandings.add(teamWins);
				divStandings.add(teamLosses);
				divStandings.add(teamTies);
				divStandings.add(teamPercentage);

				yLocation += 30;
			}


		}

		if (regularSeasonComplete && !playoffsSet){
			setPlayoffSchedule();
			playoffsSet = true;
		}

		return divStandings;
	}
*/
JPanel displayPlayoffStandings(){

	// Display current ranking, team sliver, w-l-t
	// Division leaders are automatically 1, 2, 3
	// Add a separator between division leaders and wildcards; do same to last playoff seed and remainder of conference
	
	JPanel playoffStandings = new JPanel();
	playoffStandings.setLayout(null);
	playoffStandings.setOpaque(false);
	
	int [] playoffStandings1 = new int[numberOfTeams / 2];
	int [] playoffStandings2 = new int[numberOfTeams / 2];
	int [] tempList1 = new int[numberOfTeams / 2];
	int [] tempList2 = new int[numberOfTeams / 2];
	
	for (int i = 0; i < numberOfTeams / 2; i++){
		playoffStandings1[i] = -1;
		playoffStandings2[i] = -1;
		tempList1[i] = i;
		tempList2[i] = (numberOfTeams / 2)  + i;
	}
	
	// First, get the division leaders (in order by conference rank)
	// Second, get everybody else by conference rank and order them
	int numDivLeaders = 0;
	for (int i = 0; i < numberOfTeams / 2; i++){
		if (rankings.elementAt(i)[0] == 1)
			numDivLeaders++;
	}	

	int [] divisionLeaders1 = new int[numDivLeaders];
	int [] divisionLeaders2 = new int[numDivLeaders];
	int idx = 0;
	for (int i = 0; i < numberOfTeams / 2; i++){
		if (rankings.elementAt(i)[0] == 1){
			divisionLeaders1[idx] = i;
			idx++;
		}
	}	

	// Get the indices of the division leaders in order and in the first n positions
	for (int j = 0; j < numDivLeaders; j++){
		for (int k = j + 1; k < numDivLeaders; k++){
			if (rankings.elementAt(divisionLeaders1[k])[1] < rankings.elementAt(divisionLeaders1[j])[1]){
				int tmp = divisionLeaders1[j];
				divisionLeaders1[j] = divisionLeaders1[k];		
				divisionLeaders1[k] = tmp;
			}
		}
		playoffStandings1[j] = divisionLeaders1[j];
	}
	
	// Order all indices based on conference rank (including those above)
	for (int i = 0; i < numberOfTeams / 2; i++){
		for (int j = i + 1; j < numberOfTeams / 2; j++){
			if (rankings.elementAt(tempList1[j])[1] < rankings.elementAt(tempList1[i])[1]){
				int tmp = tempList1[j];
				tempList1[j] = tempList1[i];
				tempList1[i] = tmp;
			}	
		}
	}

//	for (int i = 0; i < numberOfTeams / 2; i++)
//		System.out.println(tempList1[i]);
	
	int index = numDivLeaders;
	for (int i = 0; i < numberOfTeams / 2; i++){
	System.out.println("Index: " + index);
		if (playoffStandings1[index] == -1){
			// Check if the current index is not one of the division leaders
			boolean isLeader = false;
			for (int j = 0; j < numDivLeaders; j++)
				if (divisionLeaders1[j] == tempList1[i])
					isLeader = true;
					
			// If not, insert the index
			if (!isLeader){
				playoffStandings1[index] = tempList1[i];
				index++;
				if (index == numberOfTeams / 2)
					index--;
			}
		}
	}
	
	
	// Conference 2
	idx = 0;
	for (int i = numberOfTeams / 2; i < numberOfTeams; i++){
		if (rankings.elementAt(i)[0] == 1){
			divisionLeaders2[idx] = i;
			idx++;
		}
	}	

	// Get the indices of the division leaders in order and in the first n positions
	for (int j = 0; j < numDivLeaders; j++){
		for (int k = j + 1; k < numDivLeaders; k++){
			if (rankings.elementAt(divisionLeaders2[k])[1] < rankings.elementAt(divisionLeaders2[j])[1]){
				int tmp = divisionLeaders2[j];
				divisionLeaders2[j] = divisionLeaders2[k];
				divisionLeaders2[k] = tmp;
			}
		}
		playoffStandings2[j] = divisionLeaders2[j];
	}
	
	// Order all indices based on conference rank (including those above)
	for (int i = 0; i < numberOfTeams / 2; i++){
		for (int j = i + 1; j < numberOfTeams / 2; j++){
			if (rankings.elementAt(tempList2[j])[1] < rankings.elementAt(tempList2[i])[1]){
				int tmp = tempList2[j];
				tempList2[j] = tempList2[i];
				tempList2[i] = tmp;
			}	
		}
	}
	
	index = numDivLeaders;
	for (int i = 0; i < numberOfTeams / 2; i++){
		if (playoffStandings2[index] == -1){
			// Check if the current index is not one of the division leaders
			boolean isLeader = false;
			for (int j = 0; j < numDivLeaders; j++)
				if (divisionLeaders2[j] == tempList2[i])
					isLeader = true;
					
			// If not, insert the index
			if (!isLeader){
				playoffStandings2[index] = tempList2[i];
				index++;
				if (index == numberOfTeams / 2)
					index--;
			}
		}
	}
	
	Object[][] conference1Teams = new Object[numberOfTeams / 2][4];
	Object[][] conference2Teams = new Object[numberOfTeams / 2][4];
	
	for (int i = 0; i < numberOfTeams / 2; i++){
		System.out.println(i);
		conference1Teams[i][0] = String.valueOf(teams.elementAt(playoffStandings1[i]));
		conference1Teams[i][1] = Integer.valueOf(teamRecords.elementAt(playoffStandings1[i])[0]);
		conference1Teams[i][2] = Integer.valueOf(teamRecords.elementAt(playoffStandings1[i])[1]);
		conference1Teams[i][3] = Integer.valueOf(teamRecords.elementAt(playoffStandings1[i])[2]);
		
		conference2Teams[i][0] = String.valueOf(teams.elementAt(playoffStandings2[i]));
		conference2Teams[i][1] = Integer.valueOf(teamRecords.elementAt(playoffStandings2[i])[0]);
		conference2Teams[i][2] = Integer.valueOf(teamRecords.elementAt(playoffStandings2[i])[1]);
		conference2Teams[i][3] = Integer.valueOf(teamRecords.elementAt(playoffStandings2[i])[2]);		
	}
	
	yLocation = 0;

	// Conference 1
	for (int i = 0; i < numberOfTeams / 2; i++){
		JLabel teamRank = new JLabel(String.valueOf(i + 1));
		JLabel teamName = new JLabel(new ImageIcon(getClass().getResource("images/slivers/" + teamMap.get(conference1Teams[i][0].toString()) + "_Sliver.png")));
		JLabel teamWins = new JLabel(conference1Teams[i][1].toString());
		JLabel teamLosses = new JLabel(conference1Teams[i][2].toString());
		JLabel teamTies = new JLabel(conference1Teams[i][3].toString());
	
		teamRank.setBounds(5,yLocation,20,20);
		teamName.setBounds(30,yLocation,150,20);
		teamWins.setBounds(190,yLocation,30,20);
		teamLosses.setBounds(230,yLocation,30,20);
		teamTies.setBounds(270,yLocation,30,20);

		teamWins.setForeground(Color.WHITE);
		teamLosses.setForeground(Color.WHITE);
		teamTies.setForeground(Color.WHITE);

		playoffStandings.add(teamRank);
		playoffStandings.add(teamName);
		playoffStandings.add(teamWins);
		playoffStandings.add(teamLosses);
		playoffStandings.add(teamTies);

		yLocation += 30;
		if (i + 1 == numDivLeaders){
			JLabel playoffTeamSeparator = new JLabel("____________________________________");		
			playoffTeamSeparator.setBounds(5,yLocation,300,10);
			playoffStandings.add(playoffTeamSeparator);
		}
		if (i + 1 == maxPlayoffTeams){
			JLabel divisionLeaderSeparator = new JLabel("__________________________________");
			divisionLeaderSeparator.setBounds(5,yLocation,300,10);
			playoffStandings.add(divisionLeaderSeparator);
			yLocation+= 20;
		}

	}
	
	yLocation += 20;
	
	// Conference 2
	for (int i = 0; i < numberOfTeams / 2; i++){
		JLabel teamRank = new JLabel(String.valueOf(i + 1));
		JLabel teamName = new JLabel(new ImageIcon(getClass().getResource("images/slivers/" + teamMap.get(conference2Teams[i][0].toString()) + "_Sliver.png")));
		JLabel teamWins = new JLabel(conference2Teams[i][1].toString());
		JLabel teamLosses = new JLabel(conference2Teams[i][2].toString());
		JLabel teamTies = new JLabel(conference2Teams[i][3].toString());
	
		teamRank.setBounds(5,yLocation,20,20);
		teamName.setBounds(30,yLocation,150,20);
		teamWins.setBounds(190,yLocation,30,20);
		teamLosses.setBounds(230,yLocation,30,20);
		teamTies.setBounds(270,yLocation,30,20);

		teamWins.setForeground(Color.WHITE);
		teamLosses.setForeground(Color.WHITE);
		teamTies.setForeground(Color.WHITE);

		playoffStandings.add(teamRank);
		playoffStandings.add(teamName);
		playoffStandings.add(teamWins);
		playoffStandings.add(teamLosses);
		playoffStandings.add(teamTies);

		yLocation += 30;
		if (i + 1 == numDivLeaders){
			JLabel playoffTeamSeparator = new JLabel("____________________________________");		
			playoffTeamSeparator.setBounds(5,yLocation,300,10);
			playoffStandings.add(playoffTeamSeparator);
		}
		if (i + 1 == maxPlayoffTeams){
			JLabel divisionLeaderSeparator = new JLabel("__________________________________");
			divisionLeaderSeparator.setBounds(5,yLocation,300,10);
			playoffStandings.add(divisionLeaderSeparator);
			yLocation+= 20;
		}

	}
	
	return playoffStandings;
}

	void setPlayoffRankings(){
		int numberOfWeeksRemaining = numberOfWeeks - currentWeek + 1;
		boolean containsBye = false;

		if (numberOfTeams >= 16)
			containsBye = true;

		boolean twoConferences = false;
		if (numberOfTeams > 4)
			twoConferences = true;

		for (int i = 0; i < numberOfTeams; i++){
			// If division leader
			if (rankings.elementAt(i)[0] == 1 && rankings.elementAt(i)[2] < 2){
				int teamsDivision = teamDivisions.elementAt(i);


				for (int j = 0; j < numberOfTeams; j++){

					// If team is 2nd in division in same division
					if (rankings.elementAt(j)[0] == 2 && teamDivisions.elementAt(j) == teamsDivision){

						int teamWins = teamRecords.elementAt(j)[0];
						teamWins += (numberOfWeeks - (teamWins + teamRecords.elementAt(j)[1] + teamRecords.elementAt(j)[2]));

						if (teamWins + teamRecords.elementAt(j)[1] + teamRecords.elementAt(j)[2] > numberOfWeeks)
							teamWins--;
						
						if (teamWins + teamRecords.elementAt(j)[1] + teamRecords.elementAt(j)[2] == numberOfWeeks && containsBye)
							teamWins--;
						

						// If 2nd place team still trails in division after winning out rest of games, 1st place team won division
						if (teamWins < teamRecords.elementAt(i)[0] || numberOfWeeksRemaining == 0)
							rankings.elementAt(i)[2] = 2;
					}
				}
			}       // End division leader check

			// Checking for playoff berths
			if (rankings.elementAt(i)[2] == 0 && rankings.elementAt(i)[1] <= maxPlayoffTeams && twoConferences){
				boolean firstConference = false;
				if (i < numberOfTeams / 2)
					firstConference = true;

				int startValue = 0;
				int teamWins = 0;

				if (!firstConference){
					for (int j = numberOfTeams / 2; j < numberOfTeams; j++){
						if (rankings.elementAt(j)[1] == maxPlayoffTeams + 1){
					//		System.out.println("Team out of playoffs: " + teams.elementAt(j) + " CR: " + rankings.elementAt(j)[1]);
							teamWins = teamRecords.elementAt(j)[0];
							teamWins += (numberOfWeeks - (teamWins + teamRecords.elementAt(j)[1] + teamRecords.elementAt(j)[2]));

							if (teamWins + teamRecords.elementAt(j)[1] + teamRecords.elementAt(j)[2] > numberOfWeeks)
								teamWins--;
							if (teamWins + teamRecords.elementAt(j)[1] + teamRecords.elementAt(j)[2] == numberOfWeeks && containsBye)
								teamWins--;
						}
					}
					if (teamWins < teamRecords.elementAt(i)[0] || (teamWins <= teamRecords.elementAt(i)[0] && numberOfWeeksRemaining == 0)){
						rankings.elementAt(i)[2] = 1;
						System.out.println("triggered1 " + teamWins + " " + maxPlayoffTeams + " " + numberOfWeeks + " " + teamRecords.elementAt(i)[0] );
					}
				}
				else{
					for (int j = 0; j < numberOfTeams / 2; j++){
						if (rankings.elementAt(j)[1] == maxPlayoffTeams + 1){
					//		System.out.println("Team out of playoffs: " + teams.elementAt(j));
							teamWins = teamRecords.elementAt(j)[0];
							teamWins += (numberOfWeeks - (teamWins + teamRecords.elementAt(j)[1] + teamRecords.elementAt(j)[2]));

							if (teamWins + teamRecords.elementAt(j)[1] + teamRecords.elementAt(j)[2] > numberOfWeeks)
								teamWins--;
							if (teamWins + teamRecords.elementAt(j)[1] + teamRecords.elementAt(j)[2] == numberOfWeeks && containsBye)
								teamWins--;
						}
					}
					if (teamWins < teamRecords.elementAt(i)[0] || (teamWins <= teamRecords.elementAt(i)[0] && numberOfWeeksRemaining == 0)){
						rankings.elementAt(i)[2] = 1;
						System.out.println("triggered2 " + teamWins + " " + maxPlayoffTeams + " " + numberOfWeeks + " " + teamRecords.elementAt(i)[0] );
					}
				}



			} 
			else if (rankings.elementAt(i)[2] == 0 && rankings.elementAt(i)[1] <= maxPlayoffTeams && !twoConferences){
				int startValue = 0;
				int teamWins = 0;

				for (int j = 0; j < numberOfTeams; j++){
					if (rankings.elementAt(j)[1] == maxPlayoffTeams + 1){
						teamWins = teamRecords.elementAt(j)[0];
						teamWins += (numberOfWeeks - (teamWins + teamRecords.elementAt(j)[1] + teamRecords.elementAt(j)[2]));

						if (teamWins + teamRecords.elementAt(j)[1] + teamRecords.elementAt(j)[2] > numberOfWeeks)
							teamWins--;
						if (teamWins + teamRecords.elementAt(j)[1] + teamRecords.elementAt(j)[2] == numberOfWeeks && containsBye)
							teamWins--;
					}
				}
				if (teamWins < teamRecords.elementAt(i)[0] || (teamWins <= teamRecords.elementAt(i)[0] && numberOfWeeksRemaining == 0)){
					rankings.elementAt(i)[2] = 1;
					System.out.println("triggered3");
				}

			}      // End playoff berth check

			// Checking for eliminated teams
			if (rankings.elementAt(i)[2] == 0 && rankings.elementAt(i)[1] > maxPlayoffTeams && twoConferences){
				int teamWins = teamRecords.elementAt(i)[0];
				int betterTeamWins = 0;

				teamWins += (numberOfWeeks - (teamWins + teamRecords.elementAt(i)[1] + teamRecords.elementAt(i)[2]));

				if (teamWins + teamRecords.elementAt(i)[1] + teamRecords.elementAt(i)[2] > numberOfWeeks)
					teamWins--;
				if (teamWins + teamRecords.elementAt(i)[1] + teamRecords.elementAt(i)[2] == numberOfWeeks && containsBye)
					teamWins--;


				boolean firstConference = false;
				if (i < numberOfTeams / 2)
					firstConference = true;

				int startValue = 0;

				if (!firstConference){
					for (int j = numberOfTeams / 2; j < numberOfTeams; j++){
						if (rankings.elementAt(j)[1] == maxPlayoffTeams ){
							betterTeamWins = teamRecords.elementAt(j)[0];
						}
					}
				}
				else{
					for (int j = 0; j < numberOfTeams / 2; j++){
						if (rankings.elementAt(j)[1] == maxPlayoffTeams){
							betterTeamWins = teamRecords.elementAt(j)[0];
						}
					}
				}

				// If team winning out still cannot pass team...
				if (teamWins < betterTeamWins)
					rankings.elementAt(i)[2] = 99;

				// Last week of the season and are tied.
				else if (teamWins <= betterTeamWins && numberOfWeeksRemaining == 0)
					rankings.elementAt(i)[2] = 99;

			}
			else if(rankings.elementAt(i)[2] == 0 && rankings.elementAt(i)[1] > maxPlayoffTeams && !twoConferences){
				int teamWins = teamRecords.elementAt(i)[0];
				int betterTeamWins = 0;

				teamWins += (numberOfWeeks - (teamWins + teamRecords.elementAt(i)[1] + teamRecords.elementAt(i)[2]));

				if (teamWins + teamRecords.elementAt(i)[1] + teamRecords.elementAt(i)[2] > numberOfWeeks)
					teamWins--;
				if (teamWins + teamRecords.elementAt(i)[1] + teamRecords.elementAt(i)[2] == numberOfWeeks && containsBye)
					teamWins--;

				for (int j = 0; j < numberOfTeams; j++){
					if (rankings.elementAt(j)[1] == maxPlayoffTeams ){
						betterTeamWins = teamRecords.elementAt(j)[0];
					}
				}

				// If team winning out still cannot pass team...
				if (teamWins < betterTeamWins)
					rankings.elementAt(i)[2] = 99;

				// Last week of the season and are tied.
				else if (teamWins <= betterTeamWins && numberOfWeeksRemaining == 0)
					rankings.elementAt(i)[2] = 99;


			} // End eliminated team check


			// Check for first round byes (for 20+ team leagues only)
			if (rankings.elementAt(i)[2] == 2 && numberOfTeams > 20){
				boolean firstConference = false;
				if (i < numberOfTeams / 2)
					firstConference = true;

				int byesLeft = 2;

				if (!firstConference){
					for (int j = numberOfTeams / 2; j < numberOfTeams; j++){
						if (rankings.elementAt(j)[2] == 3 )
							byesLeft--;
					}
				}
				else{
					for (int j = 0; j < numberOfTeams / 2; j++){
						if (rankings.elementAt(j)[2] == 3)
							byesLeft--;
					}
				}

				if (byesLeft > 0){
					int lowestDivisionLeaderID = 0;
					int lowestDivisionLeaderWins = 99;


					if (!firstConference){
						for (int j = numberOfTeams / 2; j < numberOfTeams; j++){
							if ((rankings.elementAt(j)[2] == 1 || rankings.elementAt(j)[2] == 2) && rankings.elementAt(j)[0] == 1 
								&& teamRecords.elementAt(j)[0] < lowestDivisionLeaderWins && j != i){
								lowestDivisionLeaderID = j;
								lowestDivisionLeaderWins = teamRecords.elementAt(j)[0];
							}
						}
					}
					else{
						for (int j = 0; j < numberOfTeams / 2; j++){
							if ((rankings.elementAt(j)[2] == 1 || rankings.elementAt(j)[2] == 2) && rankings.elementAt(j)[0] == 1
								&& teamRecords.elementAt(j)[0] < lowestDivisionLeaderWins && j != i){
								lowestDivisionLeaderID = j;
								lowestDivisionLeaderWins = teamRecords.elementAt(j)[0];
							}
						}
					}



					lowestDivisionLeaderWins += numberOfWeeksRemaining;

					if (lowestDivisionLeaderWins + teamRecords.elementAt(lowestDivisionLeaderID)[1] + teamRecords.elementAt(lowestDivisionLeaderID)[2] > numberOfWeeks)
						lowestDivisionLeaderWins--;
					if (lowestDivisionLeaderWins + teamRecords.elementAt(lowestDivisionLeaderID)[1] + teamRecords.elementAt(lowestDivisionLeaderID)[2] == numberOfWeeks)
						lowestDivisionLeaderWins--;

					// Team has too much of a lead to be taken over...
					if (teamRecords.elementAt(i)[0] > lowestDivisionLeaderWins)
						rankings.elementAt(i)[2] = 3;

					// Teams are tied at the end of the season, but the current team has a better conference ranking...
					else if (teamRecords.elementAt(i)[0] == lowestDivisionLeaderWins && numberOfWeeksRemaining == 0 && rankings.elementAt(i)[1] < rankings.elementAt(lowestDivisionLeaderID)[1])
						rankings.elementAt(i)[2] = 3;


				}       // End if there are byes left

			} // End first round bye check

		}       // End for loop
	}

	// Old Version (stable)
	JPanel getDivisionalStandings(){
		JPanel divStandings = new JPanel();
		divStandings.setLayout(null);
		divStandings.setOpaque(false);

		yLocation = 0;

		DecimalFormat threeDForm = new DecimalFormat("0.000");

		for (int i = 0; i < 11; i++){
			int teamsInDivision = 0;


			for (int j = 0; j < teams.size(); j++){
				if (teamDivisions.elementAt(j) == i)
					teamsInDivision++;
			}

			if (teamsInDivision == 0)
				continue;

			JLabel divisionLabel = new JLabel(divisions[i] + " Division");
			JLabel winLossLabel = new JLabel("W       L       T       %");

			divisionLabel.setForeground(Color.WHITE);
			winLossLabel.setForeground(Color.WHITE);

			divisionLabel.setBounds(5,yLocation,300,30);
			winLossLabel.setBounds(177,yLocation + 20,200,20);


			divStandings.add(divisionLabel);
			divStandings.add(winLossLabel);

			yLocation += 50;

			Object[][] divisionalTeams = new Object[teamsInDivision][6];

			int idx = 0;
			// Gather team names and team records to perform the sort
			for (int j = 0; j < teams.size(); j++){
				if (teamDivisions.elementAt(j) == i){
					divisionalTeams[idx][0] = String.valueOf(teams.elementAt(j));
					divisionalTeams[idx][1] = Integer.valueOf(teamRecords.elementAt(j)[0]);
					divisionalTeams[idx][2] = Integer.valueOf(teamRecords.elementAt(j)[1]);
					divisionalTeams[idx][3] = Integer.valueOf(teamRecords.elementAt(j)[2]);
					divisionalTeams[idx][5] = "";

					int sumGames = teamRecords.elementAt(j)[0] + teamRecords.elementAt(j)[1] + teamRecords.elementAt(j)[2];

					// In case no games have yet been played...
					if (sumGames == 0)
						sumGames = 1;


					// Set the win percentage as a label of "0.000"

					divisionalTeams[idx][4] = Double.valueOf( threeDForm.format((Double.parseDouble(teamRecords.elementAt(j)[0].toString()) + (Double.parseDouble(teamRecords.elementAt(j)[2].toString()) / 2) ) / sumGames ));



					idx++;
				}
			}

			// Perform the sort on the divisional teams and place them in order
			for (int j = 0; j < teamsInDivision; j++){
				for (int k = j + 1; k < teamsInDivision; k++){
					if (Double.parseDouble(divisionalTeams[k][4].toString()) > Double.parseDouble(divisionalTeams[j][4].toString())){
						Object[] tmp = divisionalTeams[j];
						divisionalTeams[j] = divisionalTeams[k];
						divisionalTeams[k] = tmp;
					}
					else if (Double.parseDouble(divisionalTeams[k][4].toString()) == Double.parseDouble(divisionalTeams[j][4].toString())){
						if (performTiebreaker(String.valueOf(divisionalTeams[j][0]), String.valueOf(divisionalTeams[k][0]))){
							Object[] tmp = divisionalTeams[j];
							divisionalTeams[j] = divisionalTeams[k];
							divisionalTeams[k] = tmp;
						}
					}
				}
			}


			for (int j = 0; j < teamsInDivision; j++){
				String placeName = divisionalTeams[j][0].toString();

				for (int k = 0; k < numberOfTeams; k++){
					if (teams.elementAt(k).equals(placeName)){
						rankings.elementAt(k)[0] = j + 1;

					}
				}
			}

		//	if (!gameLoaded || !inPlayoffs()){
				setConferenceRankings();
				setPlayoffRankings();
		//	}

		//	for (int k = 0; k < numberOfTeams; k++)
		//		System.out.println(teams.elementAt(k).toString() + " CR = " + rankings.elementAt(k)[1] + " PR = " + rankings.elementAt(k)[2]);

			for (int j = 0; j < teamsInDivision; j++){

				// Set labels to be displayed on the panel and add to panel
				JLabel teamName = new JLabel( new ImageIcon(getClass().getResource("images/slivers/" + teamMap.get(divisionalTeams[j][0].toString()) + "_Sliver.png")) );
				JLabel teamWins = new JLabel(divisionalTeams[j][1].toString());
				JLabel teamLosses = new JLabel(divisionalTeams[j][2].toString());
				JLabel teamTies = new JLabel(divisionalTeams[j][3].toString());
				JLabel teamPercentage = new JLabel(threeDForm.format(Double.parseDouble(divisionalTeams[j][4].toString())));

				int teamIndex = 0;
				for (int k = 0; k < numberOfTeams; k++){
					if (rankings.elementAt(k)[0] == (j + 1) && teamDivisions.elementAt(k) == i)
						teamIndex = k;
				}

				if (rankings.elementAt(teamIndex)[2] == 1)
					divisionalTeams[j][5] = "x";
				else if (rankings.elementAt(teamIndex)[2] == 2)
					divisionalTeams[j][5] = "y";
				else if (rankings.elementAt(teamIndex)[2] == 3)
					divisionalTeams[j][5] = "z";
				else if (rankings.elementAt(teamIndex)[2] == 99)
					divisionalTeams[j][5] = "e";

				JLabel playoffIndicator = new JLabel(divisionalTeams[j][5].toString());


				playoffIndicator.setBounds(5,yLocation,10,20);
				teamName.setBounds(15,yLocation,150,20);
				teamWins.setBounds(190,yLocation,30,20);
				teamLosses.setBounds(230,yLocation,30,20);
				teamTies.setBounds(270,yLocation,30,20);
				teamPercentage.setBounds(310,yLocation,100,20);

				playoffIndicator.setForeground(Color.WHITE);
				teamWins.setForeground(Color.WHITE);
				teamLosses.setForeground(Color.WHITE);
				teamTies.setForeground(Color.WHITE);
				teamPercentage.setForeground(Color.WHITE);

				divStandings.add(playoffIndicator);
				divStandings.add(teamName);
				divStandings.add(teamWins);
				divStandings.add(teamLosses);
				divStandings.add(teamTies);
				divStandings.add(teamPercentage);

				yLocation += 30;
			}


		}

		if (regularSeasonComplete && !playoffsSet){
			setPlayoffSchedule();
			playoffsSet = true;
		}

		return divStandings;
	}

	// OLD VERSION (stable)
	void setConferenceRankings(){
		// Goes through each team in the conference and ranks them 1-n
     		boolean twoConferences = true;

		// Set this two 8 because an 8 team league has 2 divisions, not conferences
		if (numberOfTeams <= 8)
			twoConferences = false;

		if (twoConferences){
			Object[][] conferenceTeams = new Object[numberOfTeams][6];

			int idx = 0;

			// Gather team names and team records to perform the sort
			for (int j = 0; j < teams.size(); j++){
				conferenceTeams[j][0] = String.valueOf(teams.elementAt(j));
				conferenceTeams[j][1] = Integer.valueOf(teamRecords.elementAt(j)[0]);
				conferenceTeams[j][2] = Integer.valueOf(teamRecords.elementAt(j)[1]);
				conferenceTeams[j][3] = Integer.valueOf(teamRecords.elementAt(j)[2]);
				conferenceTeams[j][4] = Integer.valueOf(rankings.elementAt(j)[0]);


				int sumGames = teamRecords.elementAt(j)[0] + teamRecords.elementAt(j)[1] + teamRecords.elementAt(j)[2];

				// In case no games have yet been played...
				if (sumGames == 0)
					sumGames = 1;


				// Set the win percentage as a label of "0.000"
				DecimalFormat threeDForm = new DecimalFormat("0.000");
				conferenceTeams[j][5] = Double.valueOf( threeDForm.format((Double.parseDouble(teamRecords.elementAt(j)[0].toString()) + (Double.parseDouble(teamRecords.elementAt(j)[2].toString()) / 2) ) / sumGames ));


		
			}

			// Perform the sort on each conference (separately)
			// Conference 1
			for (int j = 0; j < numberOfTeams / 2; j++){
				for (int k = j + 1; k < numberOfTeams / 2; k++){
				//	System.out.println(conferenceTeams[k][0] + " " + Integer.parseInt(conferenceTeams[k][4].toString()) + " " + conferenceTeams[j][0] + " " + Integer.parseInt(conferenceTeams[j][4].toString()));

					if (Double.parseDouble(conferenceTeams[k][5].toString()) > Double.parseDouble(conferenceTeams[j][5].toString())){
					//	System.out.println("Better record");
						Object[] tmp = conferenceTeams[j];
						conferenceTeams[j] = conferenceTeams[k];
						conferenceTeams[k] = tmp;
					}
					else if (Integer.parseInt(conferenceTeams[k][4].toString()) < Integer.parseInt(conferenceTeams[j][4].toString()) 
						&& (Double.parseDouble(conferenceTeams[k][5].toString()) == Double.parseDouble(conferenceTeams[j][5].toString()))){
					//	System.out.println("Better ranking");
						Object[] tmp = conferenceTeams[j];
						conferenceTeams[j] = conferenceTeams[k];
						conferenceTeams[k] = tmp;
					}
					// Same record AND same division ranking
					else if (Double.parseDouble(conferenceTeams[k][5].toString()) == Double.parseDouble(conferenceTeams[j][5].toString()) &&
						(Integer.parseInt(conferenceTeams[k][4].toString()) == Integer.parseInt(conferenceTeams[j][4].toString()))){
					//	System.out.println("Tiebreaker");
						if (performTiebreaker(String.valueOf(conferenceTeams[j][0]), String.valueOf(conferenceTeams[k][0]))){
							Object[] tmp = conferenceTeams[j];
							conferenceTeams[j] = conferenceTeams[k];
							conferenceTeams[k] = tmp;
						}
					}
				}
			}

			// Conference 2
			for (int j = numberOfTeams / 2; j < numberOfTeams; j++){
				for (int k = j + 1; k < numberOfTeams; k++){
				//	System.out.println(conferenceTeams[k][0] + " " + Integer.parseInt(conferenceTeams[k][4].toString()) + " " + conferenceTeams[j][0] + " " + Integer.parseInt(conferenceTeams[j][4].toString()));
					if (Double.parseDouble(conferenceTeams[k][5].toString()) > Double.parseDouble(conferenceTeams[j][5].toString())){
				//		System.out.println("Better record");
						Object[] tmp = conferenceTeams[j];
						conferenceTeams[j] = conferenceTeams[k];
						conferenceTeams[k] = tmp;
					}
					else if (Integer.parseInt(conferenceTeams[k][4].toString()) < Integer.parseInt(conferenceTeams[j][4].toString()) 
						&& (Double.parseDouble(conferenceTeams[k][5].toString()) == Double.parseDouble(conferenceTeams[j][5].toString()))){
				//		System.out.println("Better ranking");
						Object[] tmp = conferenceTeams[j];
						conferenceTeams[j] = conferenceTeams[k];
						conferenceTeams[k] = tmp;
					}
					// Same record AND same division ranking
					else if (Double.parseDouble(conferenceTeams[k][5].toString()) == Double.parseDouble(conferenceTeams[j][5].toString()) &&
						(Integer.parseInt(conferenceTeams[k][4].toString()) == Integer.parseInt(conferenceTeams[j][4].toString()))){
				//		System.out.println("Tiebreaker");
						if (performTiebreaker(String.valueOf(conferenceTeams[j][0]), String.valueOf(conferenceTeams[k][0]))){
							Object[] tmp = conferenceTeams[j];
							conferenceTeams[j] = conferenceTeams[k];
							conferenceTeams[k] = tmp;
						}
					}
				}
			}

			for(int i = 0; i < numberOfTeams / 2; i++){
				String name = conferenceTeams[i][0].toString();

				for (int j = 0; j < numberOfTeams / 2; j++){
					if (teams.elementAt(j).equals(name))
						rankings.elementAt(j)[1] = i + 1;
				}
			}

			for(int i = numberOfTeams / 2; i < numberOfTeams; i++){
				String name = conferenceTeams[i][0].toString();

				for (int j = numberOfTeams / 2; j < numberOfTeams; j++){
					if (teams.elementAt(j).equals(name))
						rankings.elementAt(j)[1] = i - (numberOfTeams / 2) + 1;
				}
			}
		//	for (int j = 0; j < numberOfTeams; j++)
		//	System.out.println(teams.elementAt(j) + " CR: " + rankings.elementAt(j)[1] + " DR: " + rankings.elementAt(j)[0]);
		}
		else{
			// If there are not two conference, simply set the conference rank to the division rank
			for (int i = 0; i < numberOfTeams; i++){
				rankings.elementAt(i)[1] = rankings.elementAt(i)[0];
			}
		}
	}
	// OLD VERSION (stable)
	// If true, switch teams
	boolean performTiebreaker(String team1, String team2){
		int leadingTeamIndex = 0;
		int trailingTeamIndex = 0;

	//	System.out.println("Tiebreak - " + team1 + " vs " + team2);

		for (int i = 0; i < teams.size(); i++){
			if (teams.elementAt(i).equals(team1.replaceAll("_"," ")))
				leadingTeamIndex = i;
			if (teams.elementAt(i).equals(team2.replaceAll("_"," ")))
				trailingTeamIndex = i;
		}

		// Tiebreaker #1 -> Record difference (2-0 beats 1-0, 0-1 beats 0-2)

	//	System.out.println("Tiebreak - Win advantage");
		// Team has more wins
		if (teamRecords.elementAt(leadingTeamIndex)[0] > teamRecords.elementAt(trailingTeamIndex)[0])
			return false;
		else if (teamRecords.elementAt(leadingTeamIndex)[0] < teamRecords.elementAt(trailingTeamIndex)[0])
			return true;

	//	System.out.println("Tiebreak - Loss Advantage");
		// Team has less losses
		if (teamRecords.elementAt(leadingTeamIndex)[1] < teamRecords.elementAt(trailingTeamIndex)[1])
			return false;
		else if (teamRecords.elementAt(leadingTeamIndex)[1] > teamRecords.elementAt(trailingTeamIndex)[1])
			return true;
		// End Tiebreaker #1


	//	System.out.println("Tiebreak - Head to Head");
		// Tiebreaker #2 -> head to head
		int h2hAdvantage = 0;
		for (int i = 0; i < numberOfWeeks; i++){
			// If teams have played each other
			if (schedule.elementAt(leadingTeamIndex)[i][0] == trailingTeamIndex){
	//			System.out.println("Teams have played");
				if (scores.elementAt(leadingTeamIndex)[i][0] > scores.elementAt(leadingTeamIndex)[i][1])
					h2hAdvantage++;
				else if (scores.elementAt(leadingTeamIndex)[i][0] < scores.elementAt(leadingTeamIndex)[i][1])
					h2hAdvantage--;
			}
		}
		if (h2hAdvantage > 0)
			return false;
		else if (h2hAdvantage < 0)
			return true;
		// End tiebreaker #2


		// Tiebreaker #3 -> Divisional records
		double gamesPlayed = divisionRecords.elementAt(leadingTeamIndex)[0] + divisionRecords.elementAt(leadingTeamIndex)[1] + divisionRecords.elementAt(leadingTeamIndex)[2];
		if (gamesPlayed < 1)
			gamesPlayed = 1.0;

		double leadingTeamDivRecord = (double)( (double)(divisionRecords.elementAt(leadingTeamIndex)[0] + (1/2) * (double)(divisionRecords.elementAt(leadingTeamIndex)[1])) / 
					gamesPlayed);

		gamesPlayed = divisionRecords.elementAt(trailingTeamIndex)[0] + divisionRecords.elementAt(trailingTeamIndex)[1] + divisionRecords.elementAt(trailingTeamIndex)[2];
		if (gamesPlayed < 1)
			gamesPlayed = 1.0;

		double trailingTeamDivRecord = (double)( (double)(divisionRecords.elementAt(trailingTeamIndex)[0] + (1/2) * (double)(divisionRecords.elementAt(trailingTeamIndex)[1])) / 
					gamesPlayed);

	//	System.out.println("Div Record Difference");
		if (leadingTeamDivRecord > trailingTeamDivRecord)
			return false;
		else if (leadingTeamDivRecord < trailingTeamDivRecord)
			return true;

		// Check for difference in games played (2-0 vs 1-0 or 0-1 vs 0-2)
		else{
	//		System.out.println("Div Record Wins");
			// No losses, just wins
			if (divisionRecords.elementAt(leadingTeamIndex)[1] == 0 && divisionRecords.elementAt(leadingTeamIndex)[0] != 0){
				if (divisionRecords.elementAt(leadingTeamIndex)[0] > divisionRecords.elementAt(trailingTeamIndex)[0])
					return false;
				else if (divisionRecords.elementAt(leadingTeamIndex)[0] < divisionRecords.elementAt(trailingTeamIndex)[0])
					return true;
			}

	//		System.out.println("Div Record Losses");
			// No wins, just losses
			if (divisionRecords.elementAt(leadingTeamIndex)[0] == 0 && divisionRecords.elementAt(leadingTeamIndex)[1] != 0){
				if (divisionRecords.elementAt(leadingTeamIndex)[1] < divisionRecords.elementAt(trailingTeamIndex)[1])
					return false;
				else if (divisionRecords.elementAt(leadingTeamIndex)[1] > divisionRecords.elementAt(trailingTeamIndex)[1])
					return true;
			}
		}
		// End Tiebreaker #3

	//	System.out.println("Tiebreak - Conference");
		// Tiebreaker #4 -> Conference records
		gamesPlayed = conferenceRecords.elementAt(leadingTeamIndex)[0] + conferenceRecords.elementAt(leadingTeamIndex)[1] + conferenceRecords.elementAt(leadingTeamIndex)[2];
		if (gamesPlayed < 1)
			gamesPlayed = 1.0;

		double leadingTeamConfRecord = (double)( (double)(conferenceRecords.elementAt(leadingTeamIndex)[0] + (1/2) * (double)(conferenceRecords.elementAt(leadingTeamIndex)[1])) / 
					gamesPlayed);

		gamesPlayed = conferenceRecords.elementAt(trailingTeamIndex)[0] + conferenceRecords.elementAt(trailingTeamIndex)[1] + conferenceRecords.elementAt(trailingTeamIndex)[2];
		if (gamesPlayed < 1)
			gamesPlayed = 1.0;

		double trailingTeamConfRecord = (double)( (double)(conferenceRecords.elementAt(trailingTeamIndex)[0] + (1/2) * (double)(conferenceRecords.elementAt(trailingTeamIndex)[1])) / 
					gamesPlayed);

		if (leadingTeamConfRecord > trailingTeamConfRecord)
			return false;
		else if (leadingTeamConfRecord < trailingTeamConfRecord)
			return true;

		// Check for difference in games played (2-0 vs 1-0 or 0-1 vs 0-2)
		else{
			// No losses, just wins
			if (conferenceRecords.elementAt(leadingTeamIndex)[1] == 0 && conferenceRecords.elementAt(leadingTeamIndex)[0] != 0)
				if (conferenceRecords.elementAt(leadingTeamIndex)[0] > conferenceRecords.elementAt(trailingTeamIndex)[0])
					return false;
				else if (conferenceRecords.elementAt(leadingTeamIndex)[0] < conferenceRecords.elementAt(trailingTeamIndex)[0])
					return true;

			// No wins, just losses
			if (conferenceRecords.elementAt(leadingTeamIndex)[0] == 0 && conferenceRecords.elementAt(leadingTeamIndex)[1] != 0)
				if (conferenceRecords.elementAt(leadingTeamIndex)[1] < conferenceRecords.elementAt(trailingTeamIndex)[1])
					return false;
				else if (conferenceRecords.elementAt(leadingTeamIndex)[1] > conferenceRecords.elementAt(trailingTeamIndex)[1])
					return true;
		}
		// End Tiebreaker #4


		// Tiebreak #5 -> Points	(Same number of games played and same record overall)

		int leadingDifferential = teamStats.elementAt(leadingTeamIndex).getPointsScored() - teamStats.elementAt(leadingTeamIndex).getPointsAgainst();
		int trailingDifferential = teamStats.elementAt(trailingTeamIndex).getPointsScored() - teamStats.elementAt(trailingTeamIndex).getPointsAgainst();

	//	System.out.println("Tiebreak - Point Differential");
		if (leadingDifferential > trailingDifferential)
			return false;
		else if (leadingDifferential < trailingDifferential)
			return true;

	//	System.out.println("Tiebreak - Points Scored");
		if (teamStats.elementAt(leadingTeamIndex).getPointsScored() > teamStats.elementAt(trailingTeamIndex).getPointsScored())
			return false;
		else if (teamStats.elementAt(leadingTeamIndex).getPointsScored() < teamStats.elementAt(trailingTeamIndex).getPointsScored())
			return true;

	//	System.out.println("Tiebreak - Points Against");
		if (teamStats.elementAt(leadingTeamIndex).getPointsAgainst() < teamStats.elementAt(trailingTeamIndex).getPointsAgainst())
			return false;
		else if (teamStats.elementAt(leadingTeamIndex).getPointsAgainst() > teamStats.elementAt(trailingTeamIndex).getPointsAgainst())
			return true;
		// End Tiebreaker #5

	//	System.out.println("Tiebreak - Coin Flip");
		// Tiebreaker #6 -> Coin Flip
		int result = (int)(Math.random() * 1000);

		if (result % 2 == 0)
			return false;
		else
			return true;
		// End Tiebreaker #6
	}


	boolean isSeasonComplete(){
		return seasonComplete;
	}

	boolean inPlayoffs(){
		return (regularSeasonComplete && !seasonComplete);
	}

	boolean isGameSaved(){
		return gameSaved;
	}

	void saveSeason(){

		String filepath = JOptionPane.showInputDialog(null, "Select the file name to save season as:", "Save Season", JOptionPane.PLAIN_MESSAGE);

		File f = new File("saveData/" + filepath + ".sea");
		try{
			PrintWriter out = new PrintWriter(f);

			out.println(numberOfTeams + " " + currentWeek);


			int playoffIndicator = 0;
			int seasonCompleteIndicator = 0;
			if (inPlayoffs())
				playoffIndicator = 1;
			if (isSeasonComplete())
				seasonCompleteIndicator = 1;

			out.println(playoffIndicator + " " + seasonCompleteIndicator + " " + currentRound);

			for (int i = 0; i < numberOfWeeks; i++){
				out.println(seasonSchedule[i][0] + " " + seasonSchedule[i][1]);
			}


			// Insert team name, division, record (W/L/T), and stats
			for (int i = 0; i < numberOfTeams; i++){
				out.println(teams.elementAt(i).replaceAll("\\s","_") + " " + teamDivisions.elementAt(i) + " " + teamRecords.elementAt(i)[0] + " " + teamRecords.elementAt(i)[1] + " "
						+ teamRecords.elementAt(i)[2]);
				out.println(divisionRecords.elementAt(i)[0] + " " + divisionRecords.elementAt(i)[1] + " " + divisionRecords.elementAt(i)[2]);
				out.println(conferenceRecords.elementAt(i)[0] + " " + conferenceRecords.elementAt(i)[1] + " " + conferenceRecords.elementAt(i)[2]);
				out.println(rankings.elementAt(i)[0] + " " + rankings.elementAt(i)[1] + " " + rankings.elementAt(i)[2]);
				out.println(teamStats.elementAt(i).getPointsScored() + " " + teamStats.elementAt(i).getPointsAgainst() + " " + teamStats.elementAt(i).getPassingYards() 
						+ " " + teamStats.elementAt(i).getRushingYards() + " " + teamStats.elementAt(i).getFirstDowns());
			}

			// Insert all game scores
			for (int i = 0; i < numberOfTeams; i++){
				for (int j = 0; j < numberOfWeeks; j++){
					out.println(scores.elementAt(i)[j][0] + " " + scores.elementAt(i)[j][1] + " " + schedule.elementAt(i)[j][3]);
				}
			}

			for (int i = 0; i < numPlayoffGames; i++){
				if (playoffGames[i][3].equals("NA"))
					playoffGames[i][3] = "0";
				if (playoffGames[i][4].equals("NA"))
					playoffGames[i][4] = "0";

				out.println(playoffGames[i][0].replaceAll("\\s","_") + " " + playoffGames[i][1].replaceAll("\\s","_") + " " + playoffGames[i][2] + " " + playoffGames[i][3] + " " + playoffGames[i][4]);
			}

			for (int i = 0; i < numPlayoffGames; i++){
				out.println(playoffScores[i][0] + " " + playoffScores[i][1] + " " + playoffScores[i][2] + " " + playoffScores[i][3]);
			}

			out.println(teamInWaiting[0].replaceAll("\\s","_") + " " + teamInWaiting[1].replaceAll("\\s","_") + " " + waitingTeamSeed[0] + " " + waitingTeamSeed[1] + " " + champion);


			out.close();
			gameSaved = true;
		}catch(Exception e){}
	}

	void loadSeason(String file){
		File filename = new File("saveData/" + file);
		gameLoaded = true;

		try{
			Scanner in = new Scanner(filename);
			numberOfTeams = in.nextInt();
			determineNumberOfWeeks(numberOfTeams);
			currentWeek = in.nextInt();
			seasonSchedule = new int[numberOfWeeks][2];
			teamDivisions.clear();

			int indicator = in.nextInt();
			if (indicator == 1){
				regularSeasonComplete = true;
				playoffsSet = true;
				seasonComplete = false;
			}
			else{
				regularSeasonComplete = false;
				seasonComplete = false;
			}

			indicator = in.nextInt();
			if (indicator == 1){
				regularSeasonComplete = true;
				seasonComplete = true;
			}
			else{
				seasonComplete = false;
			}

			currentRound = in.nextInt();

			for (int i = 0; i < numberOfWeeks; i++){
				seasonSchedule[i][0] = in.nextInt();
				seasonSchedule[i][1] = in.nextInt();
			}

			// Initialize Vectors
			for (int i = 0; i < numberOfTeams; i++){
				teamRecords.addElement(new Integer[3]);
				divisionRecords.addElement(new Integer[3]);
				conferenceRecords.addElement(new Integer[3]);
				schedule.addElement(new Integer[numberOfWeeks][4]);
				scores.addElement(new Integer[numberOfWeeks][2]);
				teamStats.addElement(new Statistics(true));
				rankings.addElement(new Integer[3]);
				rankings.elementAt(i)[0] = 0;
				rankings.elementAt(i)[1] = 0;
				rankings.elementAt(i)[2] = 0;
				teamStats.elementAt(i).initialize();
			}

			playoffScores = new int[numPlayoffGames][4];
			playoffGames = new String[numPlayoffGames][5];
			playoffSchedule = new int[playoffRounds][2];

			teamInWaiting[0] = "NULL";
			teamInWaiting[1] = "NULL";
			waitingTeamSeed[0] = 0;
			waitingTeamSeed[1] = 0;

			for (int i = 0; i < numPlayoffGames; i++){
				for (int j = 0; j < 4; j++)
					playoffScores[i][j] = 0;

				playoffGames[i][0] = "NULL";
				playoffGames[i][1] = "NULL";
				playoffGames[i][2] = "N:00";
				playoffGames[i][3] = "NA";
				playoffGames[i][4] = "NA";

			}

			for (int i = 0; i < playoffRounds; i++){
				playoffSchedule[i][0] = 0;
				playoffSchedule[i][1] = 0;
			}

			// Add names, divisions, records and stats of ALL teams
			for (int i = 0; i < numberOfTeams; i++){
				teams.addElement(in.next().replaceAll("_"," "));
			//	System.out.println(teams.elementAt(i));
				teamDivisions.addElement(in.nextInt());
			//	System.out.println(teamDivisions.elementAt(i));

				// Wins, losses, ties
				teamRecords.elementAt(i)[0] = in.nextInt();
				teamRecords.elementAt(i)[1] = in.nextInt();
				teamRecords.elementAt(i)[2] = in.nextInt();

			//	System.out.println("Team Records: " + teamRecords.elementAt(i)[0] + " " + teamRecords.elementAt(i)[1] + " " + teamRecords.elementAt(i)[2]);

				for(int j = 0; j < 3; j++)
					divisionRecords.elementAt(i)[j] = in.nextInt();

				for(int j = 0; j < 3; j++)
					conferenceRecords.elementAt(i)[j] = in.nextInt();

				for(int j = 0; j < 3; j++)
					rankings.elementAt(i)[j] = in.nextInt();

			//	System.out.println("Div Records: " + divisionRecords.elementAt(i)[0] + " " + divisionRecords.elementAt(i)[1] + " " + divisionRecords.elementAt(i)[2]);
			//	System.out.println("Conf Records: " + conferenceRecords.elementAt(i)[0] + " " + conferenceRecords.elementAt(i)[1] + " " + conferenceRecords.elementAt(i)[2]);
			//	System.out.println("Rankings: " + rankings.elementAt(i)[0] + " " + rankings.elementAt(i)[1] + " " + rankings.elementAt(i)[2]);

				// Statistics
				teamStats.elementAt(i).addPointsScored(in.nextInt());
				teamStats.elementAt(i).addPointsAgainst(in.nextInt());
				teamStats.elementAt(i).addPassingYards(in.nextInt());
				teamStats.elementAt(i).addRushingYards(in.nextInt());
				int firstDowns = in.nextInt();
				for (int j = 0; j < firstDowns; j++)
					teamStats.elementAt(i).addFirstDown();
			}


			setTeamSchedules();


			// For each team, set schedule to show that games have already been played if applicable
			for (int i = 0; i < numberOfTeams; i++){
				int gamesPlayed = teamRecords.elementAt(i)[0] + teamRecords.elementAt(i)[1] + teamRecords.elementAt(i)[2];
				boolean hadBye = false;

				for (int j = 0; j < gamesPlayed; j++){
					if (schedule.elementAt(i)[j][0] == -1)
						hadBye = true;
				}

				if (hadBye)
					gamesPlayed++;

				for (int j = 0; j < gamesPlayed; j++){
					schedule.elementAt(i)[j][2] = 1;
				}
			}

			for (int i = 0; i < numberOfTeams; i++){
				for (int j = 0; j < numberOfWeeks; j++){
					scores.elementAt(i)[j][0] = in.nextInt();
					scores.elementAt(i)[j][1] = in.nextInt();
					schedule.elementAt(i)[j][3] = in.nextInt();
				}
			}


			for (int i = 0; i < numPlayoffGames; i++){

				playoffGames[i][0] = in.next().replaceAll("_"," ");
				playoffGames[i][1] = in.next().replaceAll("_"," ");
				playoffGames[i][2] = in.next();

				int seed = in.nextInt();

				if (seed == 0)
					playoffGames[i][3] = "NA";
				else
					playoffGames[i][3] = String.valueOf(seed);

				seed = in.nextInt();

				if (seed == 0)
					playoffGames[i][4] = "NA";
				else
					playoffGames[i][4] = String.valueOf(seed);

			}

			for (int i = 0; i < numPlayoffGames; i++){
				playoffScores[i][0] = in.nextInt();
				playoffScores[i][1] = in.nextInt();
				playoffScores[i][2] = in.nextInt();
				playoffScores[i][3] = in.nextInt();
			}

			teamInWaiting[0] = in.next();
			teamInWaiting[1] = in.next();
			waitingTeamSeed[0] = in.nextInt();
			waitingTeamSeed[1] = in.nextInt();
			champion = in.next();

			in.close();
			gameSaved = true;

		}catch(Exception e){
			e.printStackTrace();};
	}

}
