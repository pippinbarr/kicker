import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Vector;

public class CPUSimulation{

	int teamID1 = 0;
	int teamID2 = 0;

	String team1;
	String team2;

	Team homeTeam = new Team();
	Team awayTeam = new Team();

	Team offensiveTeam;
	Team defensiveTeam;

	Vector<Player> homeTeamRoster = new Vector<Player>();
	Vector<Player> awayTeamRoster = new Vector<Player>();

	Vector<Player> offensiveRoster;
	Vector<Player> defensiveRoster;

	Player kicker;
	Player kickReturner;

	CPU offenseCPU = new CPU();
	CPU defenseCPU = new CPU();

	boolean teamInRedZone = false;
	boolean onSpecialTeams = false;
	boolean kickingOff = false;

	boolean kickingExtraPoint = false;
	Play offensivePlay;

	boolean clockStopped = false;

	int kickDistance = 0;
	int kickAccuracy = 0;
	int returnDistance = 0;
	int attemptDistance = 0;

	int minutesElapsed = 0;
	int timeElapsed = 0;

	int currentDown = 1;
	int yardsToGo = 10;
	int yardline = 20;
	int quarter = 1;

	int homeScore = 0;
	int awayScore = 0;

	Vector<Drive> drives = new Vector<Drive>();


	// Hold all player files for each team used in game, to be opened and applied to rosters
	Vector<String> homeTeamPlayerFiles = new Vector<String>();
	Vector<String> awayTeamPlayerFiles = new Vector<String>();

	Field field = new Field();

	String [] teamNames = {null, "Portland", "New_Haven", "Delaware", "Lancaster", "Richmond",
					"Orlando", "Birmingham", "Biloxi", "Baton_Rouge", "San_Antonio",
					"Dayton", "London", "Upper_Michigan", "Iowa", "Charleston",
					"Dakota", "Wichita", "Tulsa", "Amarillo", "Albuquerque",
					"Montana", "Colorado", "Boise", "Salt_Lake", "Tahoe",
					"Anchorage", "Tacoma", "Oregon", "Los_Angeles", "Hawaii"};


	public CPUSimulation(String tm1, String tm2){
		System.out.println("Simulation started");

		homeTeamRoster.clear();
		awayTeamRoster.clear();
		drives.clear();
		homeTeamPlayerFiles.clear();
		awayTeamPlayerFiles.clear();

		team1 = tm1;
		team2 = tm2;

		try{
		teamID1 = Integer.parseInt(tm1);
		}catch(NumberFormatException nfe){}
		try{
		teamID2 = Integer.parseInt(tm2);
		}catch(NumberFormatException nfe){}
		

		if (teamID1 != 0)
			team1 = teamNames[teamID1];
		if (teamID2 != 0)
			team2 = teamNames[teamID2];

		boolean found1 = false;
		boolean found2 = false;
		for (int i = 0; i < 31; i++){
			if (team1.equals(teamNames[i]))
				found1 = true;
			if (team2.equals(teamNames[i]))
				found2 = true;
		}

		if (!found1 || !found2)
			System.out.println("Matchup cannot be applied");
		else{
			loadTeams(team1, team2);
			simulateGame();
		//	printTeamStatistics();
		//	displayFinalScore();
		}

	}

	void loadTeams(String homeSelected, String awaySelected){

		// For final product, allow players to select which team will play in the game.
		// For testing, it will be pre-selected until the gameplay is working properly
		try{
			String homeFile = "teams/" + homeSelected + ".ft";

			File file = new File(homeFile);

			java.util.Scanner in = new java.util.Scanner(file);
			homeTeam.setName(in.next());
			homeTeam.setNickname(in.next());
			homeTeam.setLocation(in.next());
			homeTeam.setState(in.next());
			homeTeam.setAbbreviation(in.next());
			homeTeam.setCoach(in.next());
			homeTeam.setStadium(in.next());

			int isDomed = in.nextInt();

			if (isDomed == 1)
				homeTeam.setDomed(true);
			else
				homeTeam.setDomed(false);

		//	int climate = in.nextInt();
			homeTeam.setClimate(in.next());

			if (homeTeam.isDomed())
				homeTeam.setClimate("Domed");

		//	int division = in.nextInt();
		 	homeTeam.setDivision(in.next());


			String picFile = in.next();
			homeTeam.setLogo(new ImageIcon(picFile));
			//homeTeam.setPrimaryColor(new Color(in.next()));
		//	homeTeam.setSecondaryColor(new Color(in.next()));

			System.out.println("Home team set to : " + homeTeam.getName() + " " + homeTeam.getNickname());

			in.close();
		}catch(Exception e){e.printStackTrace();
		}

		int capacity = 0;

		try{
			String playerFiles = "teams/" + homeTeam.getName() + "/teamRoster.tr";
			File file = new File(playerFiles);
			java.util.Scanner in = new java.util.Scanner(file);
			
			while(in.hasNext()){
				String playerFile = in.next();

				homeTeamPlayerFiles.add(playerFile);
				capacity++;

				//System.out.println(playerFile);
			}

			in.close();
		}catch(Exception e){}

//		System.out.println("Capacity is: " + capacity);

		for(int i = 0; i < capacity; i++){
			try{
				File file = new File("teams/" + homeTeam.getName() + "/" + homeTeamPlayerFiles.elementAt(i).toString());
				java.util.Scanner in = new java.util.Scanner(file);
				String fName = in.next();
				String lName = in.next();
				String position = in.next();
				String team = in.next();
				String nickname = in.next();
				int number = in.nextInt();
				String school = in.next();
				int age = in.nextInt();
				int experience = in.nextInt();

				int height = in.nextInt();
				int weight = in.nextInt();

				int [] attributes = new int[16];
				for (int j = 0; j < 16; j++){
					attributes[j] = in.nextInt();
				}
				int overall = in.nextInt();
				in.close();


				Player p = new Player();
				p.setName(fName, lName);
				p.setPosition(position);
				p.setTeam(team);
				p.setNumber(number);
				p.setSchool(school);
				p.setAge(age);
				p.setExperience(experience);
				p.setHeight(height);
				p.setWeight(weight);

				for (int j = 0; j < 16; j++)
					p.setAttribute(j, attributes[j]);
				p.setOverall(overall);


				homeTeamRoster.add(p);


		//		System.out.println(p.getName() + " has been added to the " + p.getTeam() + " roster.");
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}// end setting home team

		try{
			String awayFile = "teams/" + awaySelected + ".ft";
			File file = new File(awayFile);

			java.util.Scanner in = new java.util.Scanner(file);
			awayTeam.setName(in.next());
			awayTeam.setNickname(in.next());
			awayTeam.setLocation(in.next());
			awayTeam.setState(in.next());
			awayTeam.setAbbreviation(in.next());
			awayTeam.setCoach(in.next());
			awayTeam.setStadium(in.next());

			int domed = in.nextInt();
			if (domed == 1)
				awayTeam.setDomed(true);
			else
				awayTeam.setDomed(false);

			awayTeam.setClimate(in.next());

			if (awayTeam.isDomed())
				awayTeam.setClimate("Domed");

			awayTeam.setDivision(in.next());

			String picFile = in.next();
			awayTeam.setLogo(new ImageIcon(picFile));

			int red, green, blue;

			red = in.nextInt();
			green = in.nextInt();
			blue = in.nextInt();

			awayTeam.setPrimaryColor(new Color(red,green,blue));

			red = in.nextInt();
			green = in.nextInt();
			blue = in.nextInt();

			awayTeam.setSecondaryColor(new Color(red,green,blue));



			System.out.println("Away team set to : " + awayTeam.getName() + " " + awayTeam.getNickname());

			in.close();
		}catch(Exception e){}

		capacity = 0;

		try{
			String playerFiles = "teams/" + awayTeam.getName() + "/teamRoster.tr";
			File file = new File(playerFiles);
			java.util.Scanner in = new java.util.Scanner(file);
			
			while(in.hasNext()){
				String playerFile = in.next();

				awayTeamPlayerFiles.add(playerFile);
				capacity++;

				//System.out.println(playerFile);
			}


			in.close();
		}catch(Exception e){}

	//	System.out.println("Capacity is: " + capacity);

		for(int i = 0; i < capacity; i++){
			try{
				File file = new File("teams/" + awayTeam.getName() + "/" + awayTeamPlayerFiles.elementAt(i).toString());
				java.util.Scanner in = new java.util.Scanner(file);

				String fName = in.next();
				String lName = in.next();
				String position = in.next();
				String team = in.next();
				String nickname = in.next();
				int number = in.nextInt();
				String school = in.next();
				int age = in.nextInt();
				int experience = in.nextInt();

				int height = in.nextInt();
				int weight = in.nextInt();

				int [] attributes = new int[16];
				for (int j = 0; j < 16; j++){
					attributes[j] = in.nextInt();
				}
				int overall = in.nextInt();
				in.close();


				Player p = new Player();
				p.setName(fName, lName);
				p.setPosition(position);
				p.setTeam(team);
				p.setNumber(number);
				p.setSchool(school);
				p.setAge(age);
				p.setExperience(experience);
				p.setHeight(height);
				p.setWeight(weight);

				for (int j = 0; j < 16; j++)
					p.setAttribute(j, attributes[j]);
				p.setOverall(overall);


				awayTeamRoster.add(p);


	//			System.out.println(p.getName() + " has been added to the " + p.getTeam() + " roster.");
			}catch(Exception e){
				e.printStackTrace();
			}	
		}// end creating away team

		field.setStadium(homeTeam.getStadium());
		field.setLocation(homeTeam.getLocation(), homeTeam.getState());
		field.setWeather(homeTeam.getClimate());

	}

	void simulateGame(){

		// This is a purely simple test.
		field.setPossession(1);
		offensiveTeam = awayTeam;
		defensiveTeam = homeTeam;

		field.initializeField();

		kickingOff = true;

		// Use "timeElapsed" in place of minutesElapsed (stores seconds)
		// While timeElapsed < 1800...
		// determinePlayChoices(possession)
		while(timeElapsed < 900 || kickingExtraPoint){
			determinePlayChoices(field.getPossession());
			if (!onSpecialTeams)
				timeElapsed = (900 - field.getTimeLeft());

			if (timeElapsed >= 900){
				if (!field.isGameOver() || field.getQuarter() == 5)
					timeElapsed = 0;
			}	

			if (field.getQuarter() == 3 && timeElapsed == 0){
				field.setPossession(2);
				offensiveTeam = homeTeam;
				defensiveTeam = awayTeam;
				kickingOff = true;
				field.setDown(1);
				field.setYardline(35);
				field.setYardsToGo(10);
			}
			if (field.getQuarter() == 5 && timeElapsed == 0 && !field.isGameOver()){
				field.setPossession(1);
				offensiveTeam = awayTeam;
				defensiveTeam = homeTeam;
				kickingOff = true;
				field.setDown(1);
				field.setYardline(35);
				field.setYardsToGo(10);
			}

			if (field.isGameOver()){
				timeElapsed = 900;
				if (field.getQuarter() == 5)
					kickingExtraPoint = false;
			}
		}
		

	}

	void determinePlayChoices(int possession){
		if (kickingOff)
			analyzeSpecialTeams("Kick");
		else{

			Play chosenPlay = new Play();
			Player primaryPlayer = new Player();



			// Determine offensive roster
			if(possession == 1){
				offensiveRoster = awayTeamRoster;
				defensiveRoster = homeTeamRoster;
			}
			else{
				offensiveRoster = homeTeamRoster;
				defensiveRoster = awayTeamRoster;
			}

			int offenseScore = 0;
			int defenseScore = 0;

			if (possession == 0){
				offenseScore = awayScore;
				defenseScore = homeScore;
			}
			else{
				offenseScore = homeScore;
				defenseScore = awayScore;
			}

			int timeLeft = 1500 - (timeElapsed % 1500);
			boolean specialTeams = false;
			if (onSpecialTeams)
				specialTeams = true;

			// Need to create a Play AND offensive player if Computer has the ball. (Need to remove the field attributes and special teams tag)
			offenseCPU.analyzeField(field.getDown(), field.getYardsToGo(), field.getYardline(), field.getTimeLeft(), field.getQuarter(), offenseScore, defenseScore, false, specialTeams, false);
			defenseCPU.analyzeField(field.getDown(), field.getYardsToGo(), field.getYardline(), field.getTimeLeft(), field.getQuarter(), offenseScore, defenseScore, true, specialTeams, false);

			offensivePlay = offenseCPU.getOffensivePlay();
			Play defensivePlay = defenseCPU.getDefensivePlay();


				String playerPosition = offenseCPU.getIntendedReceiver();
				if (offensivePlay.getSideOfBall().equals("O")){
					if (offensivePlay.getType().equals("R")){
						for (int i = 0; i < offensiveRoster.size(); i++){
							if (offensiveRoster.elementAt(i).getPosition().equals(playerPosition)){
								primaryPlayer = offensiveRoster.elementAt(i);
								System.out.println("Player: " + primaryPlayer.getName());
								}
						}
					}
					// For pass plays, choose a random wide receiver
					else if (offensivePlay.getType().equals("P") ){

						int position = (int)(Math.random() * 2 + 1);
						int location = 1;

						System.out.println("Pass: " + playerPosition + "   " + position + "   " + location);

						for (int i = 0; i < offensiveRoster.size(); i++){


							if (offensiveRoster.elementAt(i).getPosition().equals(playerPosition)){
							 	if (location == position){
									primaryPlayer = offensiveRoster.elementAt(i);
									break;

								}
								else
									location++;
							}
						}
					}
				}
				else if (offensivePlay.getSideOfBall().equals("S")){
					if (offensivePlay.getName().equals("Punt"))
						for (int i = 0; i < offensiveRoster.size(); i++){
							analyzeSpecialTeams("Punt");
							return;
						}
					else if (offensivePlay.getName().equals("Field_Goal"))
						for (int i = 0; i < offensiveRoster.size(); i++){
							if (offensiveRoster.elementAt(i).getPosition().equals("K")){
								primaryPlayer = offensiveRoster.elementAt(i);
								break;
							}
						}
					// Actually, this is for the human player when returning...not the CPU
					else if (offensivePlay.getName().contains("Return") || offensivePlay.getName().equals("Onside_Recover") ){

					
					}
				} // end offensive play select


			analyzePlay(offensivePlay, defensivePlay, primaryPlayer);

		}

	}


	void analyzePlay(Play offensivePlay, Play defensivePlay, Player primaryPlayer){
		String playType = offensivePlay.getType();
		boolean isSpecialTeams = false;



		String[] passValues = {"screen", "short", "mid", "long"};
		if (playType.equals("P")){

			int val = (int)(Math.random() * 4);
			playType = passValues[val];

		}

		// If there is no play type, then the play is part of special teams
		else if (playType.equals("")){
			playType = offensivePlay.getName();
			isSpecialTeams = true;
		}


	//	System.out.println("Play Type is " + playType + ". Special teams is " + isSpecialTeams);

		int advantage = calculateAdvantage(defensivePlay, offensivePlay, playType);


			PlayAnalysis analysis = new PlayAnalysis(primaryPlayer, offensiveRoster, defensiveRoster, playType, advantage);
			analysis.performPlay();

			// Continue as usual if this is not a special teams play
			if (!isSpecialTeams)
				displayResults(analysis);

			// If it is special teams...
			else{

				// If this is a punt or kickoff, update the field and change possession
				if (playType.equals("Punt")){
					// Get the distance of the kick
					kickDistance = analysis.getKickDistance();
					displayResults(analysis);



				}
				// Else, check for the field goal attempt's accuracy and continue
				else if(playType.equals("Field_Goal") ){
					// Get the distance of the kick

					kickDistance = analysis.getKickDistance();
					kickAccuracy = analysis.getKickAccuracy();
					displayResults(analysis);
				}

			}

	}

	void analyzeSpecialTeams(String type){
		if (type.equals("Kick")){
			if(field.getPossession() == 1){
				offensiveRoster = awayTeamRoster;
				defensiveRoster = homeTeamRoster;
			}
			else{
				offensiveRoster = homeTeamRoster;
				defensiveRoster = awayTeamRoster;
			}


			for (int i = 0; i < offensiveRoster.size(); i++){
				if (offensiveRoster.elementAt(i).getPosition().equals("K")){
					kicker = offensiveRoster.elementAt(i);
					break;
				}
			}

			boolean isOnsideKick = false;

			int offenseScore = 0;
			int defenseScore = 0;

			if (field.getPossession() == 1){
				offenseScore = awayScore;
				defenseScore = homeScore;
			}
			else{
				offenseScore = homeScore;
				defenseScore = awayScore;
			}

			// Need to create a Play AND offensive player if Computer has the ball.
			offenseCPU.analyzeField(field.getDown(), field.getYardsToGo(), field.getYardline(), field.getTimeLeft(), field.getQuarter(), offenseScore, 
				defenseScore, false, onSpecialTeams, true);

			if (offenseCPU.selectKickoffStyle().equals("Kickoff"))
				isOnsideKick = false;
			else
				isOnsideKick = true;
			


			if (isOnsideKick){

				PlayAnalysis analysis = new PlayAnalysis(kicker, offensiveRoster, defensiveRoster, "Onside", 0);
				analysis.performPlay();
				kickDistance = analysis.getKickDistance();
				int kickRecovery = analysis.determineKickRecovery();

				System.out.println("Kick Distance: " + kickDistance + "  Recovered by : " + kickRecovery);

				boolean kickingTeamRecovered = false;

				if (kickRecovery == 1 && kickDistance >= 10)
					kickingTeamRecovered = true;


				field.setYardline(field.getYardline() + kickDistance);

				int timeElapsed = (int)(Math.random() * 3 + 2);

				if (field.getTimeLeft() < timeElapsed){
					System.out.println("Play duration: " + field.getTimeLeft() + " seconds");

					if (kickingTeamRecovered)
						offensiveTeam.getStats().addTimeOfPossession(field.getTimeLeft());
					else
						defensiveTeam.getStats().addTimeOfPossession(field.getTimeLeft());
					field.reduceTime(field.getTimeLeft(), false);
				}
				else{
					System.out.println("Play duration: " + timeElapsed + " seconds");

					if (kickingTeamRecovered)
						offensiveTeam.getStats().addTimeOfPossession(timeElapsed);
					else
						defensiveTeam.getStats().addTimeOfPossession(timeElapsed);
					field.reduceTime(timeElapsed, false);
				}

				String recoveryMessage = kicker.getName() + " attempts onside kick for " + kickDistance + " yards.\n";

				if (kickingTeamRecovered){
					recoveryMessage += "Kicking team recovers!";

					kickingOff = false;
				}
				else{
					recoveryMessage += "Return team recovers!";
					field.setDown(1);
					field.setYardsToGo(10);
			//		field.setYardline(100 - field.getYardline());
					changePossession();

					kickingOff = false;
				}


				System.out.println(recoveryMessage);

			}
			else{
				// Kickoff
				PlayAnalysis analysis = new PlayAnalysis(kicker, offensiveRoster, defensiveRoster, "Kickoff", 0);
				analysis.performPlay();
				kickDistance = analysis.getKickDistance();

				System.out.println("Kick Distance: " + kickDistance);

				field.setYardline(field.getYardline() + kickDistance);

				// If a touchback occurs
				if (field.getYardline() >= 100){
					field.setYardline(80);
					displayKickResults(true, false, false);
				}

				// Returning the kickoff
				else{
					for (int i = 0; i < defensiveRoster.size(); i++){
						if (defensiveRoster.elementAt(i).getPosition().equals("CB")){
							kickReturner = defensiveRoster.elementAt(i);
							break;
						}
					}
					PlayAnalysis analysis2 = new PlayAnalysis(kickReturner, defensiveRoster, offensiveRoster, "R", 0);
					analysis2.performPlay();

					boolean turnover = false;
					if ((analysis2.ballFumbled() && analysis2.turnoverOccurred()))
						turnover = true;

					returnDistance = analysis2.getYardsGained();
					field.setYardline(field.getYardline() - returnDistance);

					if (field.getYardline() >= 100){
						field.setYardline(80);
						displayKickResults(true, turnover, false);
					}
					else
						displayKickResults(false, turnover, false);
				}
			}
		}
		else if (type.equals("Punt")){
			if(field.getPossession() == 1){
				offensiveRoster = awayTeamRoster;
				defensiveRoster = homeTeamRoster;
			}
			else{
				offensiveRoster = homeTeamRoster;
				defensiveRoster = awayTeamRoster;
			}


			for (int i = 0; i < offensiveRoster.size(); i++){
				if (offensiveRoster.elementAt(i).getPosition().equals("P")){
					kicker = offensiveRoster.elementAt(i);
					break;
				}
			}


			// Punt
			PlayAnalysis analysis = new PlayAnalysis(kicker, offensiveRoster, defensiveRoster, "Punt", 0);
			analysis.performPlay();
			kickDistance = analysis.getKickDistance();

			System.out.println("Punt Distance: " + kickDistance);

			field.setYardline(field.getYardline() + kickDistance);

			// If a touchback occurs
			if (field.getYardline() >= 100){
				field.setYardline(80);
				displayKickResults(true, false, false);
			}

			// Returning the kickoff
			else{
				for (int i = 0; i < defensiveRoster.size(); i++){
					if (defensiveRoster.elementAt(i).getPosition().equals("CB")){
						kickReturner = defensiveRoster.elementAt(i);
						break;
					}
				}
				PlayAnalysis analysis2 = new PlayAnalysis(kickReturner, defensiveRoster, offensiveRoster, "R", 0);
				analysis2.performPlay();

				boolean turnover = false;
				if ((analysis2.ballFumbled() && analysis2.turnoverOccurred()))
					turnover = true;

				boolean inside20 = false;

				if (field.getYardline() > 80)
					inside20 = true;

				returnDistance = analysis2.getYardsGained();
				field.setYardline(field.getYardline() - returnDistance);

				if (field.getYardline() >= 100){
					field.setYardline(80);
					displayKickResults(true, turnover, false);
				}
				else
					displayKickResults(false, turnover, inside20);
			}


		}
	}

	int calculateAdvantage(Play defPlay, Play offPlay, String passDist){

		int advantage = calculateClimateAdvantage();

		// A simple formula will be applied for alpha testing (as follows)

		// If a defense blitzes and the offense performs a run play or short/screen pass, advantage defense
		if (defPlay.getType().equals("B") && (offPlay.getType().equals("R") || passDist.equals("short") || passDist.equals("screen") ) )
			advantage += 20;

		// If a defense uses zone coverage against a long pass play, advantage defense
		else if (defPlay.getType().equals("Z") && passDist.equals("long"))
			advantage += 25;

		// If a defense uses man coverage against a short or screen pass play, advantage defense
		else if (defPlay.getType().equals("M") && (passDist.equals("short") || passDist.equals("screen")))
			advantage += 10;

		// If a defense blitzes and offense runs ball to outside, advantage offense
		else if (defPlay.getType().equals("B") && offPlay.getType().equals("R"))
			advantage -= 5;

		// If a defense uses zone coverage and offense runs ball to inside or uses short/screen pass, advantage offense
		else if (defPlay.getType().equals("Z") && (passDist.equals("short") || passDist.equals("screen") || offPlay.getType().equals("R")))
			advantage -= 8;

		// If a defense uses man coverage against a long pass, advantage offense
		else if (defPlay.getType().equals("M") && passDist.equals("long"))
			advantage += 5;


		if (offPlay.getSideOfBall().equals("O") && onSpecialTeams){
			advantage += 40;
		}

	//	System.out.println("Advantage: " + advantage);

		return advantage;
	}


	// Amount will be negative to benefit the offense, positive to benefit the defense
	// This SO FAR takes into consideration the current temperature and the elevation.
		// It does NOT take into consideration the current weather patterns or wind
	int calculateClimateAdvantage(){
		int climateAdvantage = 0;

		if (homeTeam.isDomed())
			return 0;

		String weather = homeTeam.getTeamClimate().getWeatherConditions();

		climateAdvantage = determineWeatherAdvantage(weather);

		// If home team has possession, away team has DISadvantage
		if ( field.getPossession() == 2  ){
			double tempDiff = homeTeam.getTeamClimate().getCurrentTemperature() - awayTeam.getTeamClimate().getCurrentTemperature();
			int elevationDiff = homeTeam.getTeamClimate().getElevation() - awayTeam.getTeamClimate().getElevation();

			if (Math.abs(tempDiff) >= 30.0)
				climateAdvantage -= (int)Math.abs(tempDiff) - 20;

			if (Math.abs(elevationDiff) >= 2500)
				climateAdvantage -= (10 + ((Math.abs(elevationDiff) - 2500) / 400));

		}

		// If away team has possession, home team has ADVANTAGE
		else{
			double tempDiff = homeTeam.getTeamClimate().getCurrentTemperature() - awayTeam.getTeamClimate().getCurrentTemperature();
			int elevationDiff = homeTeam.getTeamClimate().getElevation() - awayTeam.getTeamClimate().getElevation();

			if (Math.abs(tempDiff) >= 30.0)
				climateAdvantage += (int)Math.abs(tempDiff) - 20;

			if (Math.abs(elevationDiff) >= 2500)
				climateAdvantage += 10 + ((Math.abs(elevationDiff) - 2500) / 400);
		}
		
		return climateAdvantage;
	}

	// Positive = defense adv, Negative = offense adv.

	// Away team has ball ==> Possession = 1, Home team has ball ==> Possession = 2
	int determineWeatherAdvantage(String weather){
		boolean isHomeTeam = awayTeam.isComputer();
		int weatherAdvantage = 0;

		if (weather.equals("Light Snow") || weather.equals("Snow Showers") || weather.equals("Showers") || weather.equals("Sleet") || weather.equals("Freezing Rain"))
			if (field.getPossession() == 1)	// Away team has possession
					weatherAdvantage += 5;
			else
					weatherAdvantage -= 5;

		if (weather.equals("Heavy Snow") || weather.equals("Heavy Rain") || weather.equals("Thunderstorms"))
			if (field.getPossession() == 1)
					weatherAdvantage += 10;
			else
					weatherAdvantage -= 10;

		if (weather.equals("Light Rain") || weather.equals("Wintry Mix"))
			if (field.getPossession() == 1)
					weatherAdvantage += 3;
			else
					weatherAdvantage -= 3;

		return weatherAdvantage;
	}

	void displayKickResults(boolean isTouchback, boolean turnoverOccurred, boolean inside20){
		// Uses kickDistance and returnDistance
		// Also uses Kicker and KickReturner Player objects

		System.out.println("Kick Distance: " + kickDistance + "   Returned: " + returnDistance);


		boolean touchdownScored = false;
		boolean changeOfPossession = false;

		int yardsGained = returnDistance;

		field.setDown(1);
		field.setYardsToGo(10);

		int timeElapsed = (int)(Math.random() * 5 + 5);
		int timeInHuddle = offenseCPU.getTimeInHuddle();
		boolean quarterEnded = false;

		if (!clockStopped){
			timeElapsed += timeInHuddle;
			if (timeInHuddle >= field.getTimeLeft())
				quarterEnded = true;
		}

		clockStopped = true;

		if (!quarterEnded){

			// If touchdown was scored, set ball at opponent's 3 yd line
			if (field.getYardline() <= 0){

					field.touchdownScored();
					touchdownScored = true;
					field.setYardsToGo(3);
					field.setYardline(3);
					defensiveTeam.getStats().addScoring(field.getQuarter(), 6);


			}
			else if (field.getYardline() <= 20){
					defensiveTeam.getStats().addRedZoneAttempt();
					teamInRedZone = true;

				if(field.getYardline() < 10)
					field.setYardsToGo(field.getYardline());
			}

			
		
			changeOfPossession = true;


			// If a turnover occurred during the return, change of possession does not occur
			if (turnoverOccurred){

				defensiveTeam.getStats().addTurnover();
				changeOfPossession = false;

			}

		}


		String message = "";

		if (!quarterEnded){

			if(kicker.getPosition().equals("K")){
				message = kicker.getName() + " kicks off for " + kickDistance + " yards.";
			}
				// Punt kicked
			else if(kicker.getPosition().equals("P")){
				message = kicker.getName() + " punts ball for " + kickDistance + " yards.";
			}



			if (kicker.getPosition().equals("P"))
				kicker.getStats().addPunt(kickDistance, inside20, isTouchback);
			else
				kicker.getStats().addKick(kickDistance, isTouchback);


			if (isTouchback){
				message += " Touchback.";



			}
			// Returning ball
			else{

				if (kicker.getPosition().equals("P")){
					kickReturner.getStats().addPuntReturnYards(returnDistance);

					defensiveTeam.getStats().addPuntReturnYards(returnDistance);
				}
				else{
					kickReturner.getStats().addKickReturnYards(returnDistance);

					defensiveTeam.getStats().addKickReturnYards(returnDistance);
				}


			

					message = kickReturner.getName() + " returns ball for " + returnDistance + " yards.";

			}
			// Touchdown scored
			if (touchdownScored){
			//	kickReturner.getStats().addRushTD();

				// Display opposite team since the possession has already changed (Test only)
				message += "\n" + defensiveTeam.getName() + " scores a touchdown!";
				field.setYardline(3);	// Ball at 3 yard line
				onSpecialTeams = true;
			}



			// Ball is fumbled
			if (turnoverOccurred){
				message += "\nFumble!  ";

				message += "Ball recovered by kicking team";
			}
		}
		else
			message = "Quarter ended. No Play.";

		System.out.println(defensiveTeam.getName() + ":   " + message);

		if (quarterEnded){
			System.out.println("Play duration: " + field.getTimeLeft() + " seconds");

			// Has to be offensive team because the play never happened
			offensiveTeam.getStats().addTimeOfPossession(field.getTimeLeft());
			field.reduceTime(field.getTimeLeft(), false);
		}
		else if (field.getTimeLeft() < timeElapsed){
			System.out.println("Play duration: " + field.getTimeLeft() + " seconds");

			defensiveTeam.getStats().addTimeOfPossession(field.getTimeLeft());
			field.reduceTime(field.getTimeLeft(), touchdownScored);
		}
		else{
			System.out.println("Play duration: " + timeElapsed + " seconds");

			defensiveTeam.getStats().addTimeOfPossession(timeElapsed);
			field.reduceTime(timeElapsed, touchdownScored);
		}

		if (!quarterEnded){
			changePossession();
			kickingOff = false;
		}
	}

	void displayResults(PlayAnalysis analysis){
		boolean isFirstDown = false;
		boolean touchdownScored = false;
		boolean safetyScored = false;
		boolean fieldGoalScored = false;
		boolean conversionScored = false;
		boolean conversionMissed = false;
		String fieldGoalMissed = "";
		boolean changeOfPossession = false;
		boolean quarterEnded = false;

		kickingExtraPoint = false;

		Drive currentDrive = drives.elementAt(drives.size() - 1);

		int yardsGained = analysis.getYardsGained();

		if (field.getYardline() + yardsGained > 100)
			yardsGained = (100 - field.getYardline());

		Player primaryPlayer = analysis.getOffensivePlayer();


		boolean passPlay = false;

		if (offensivePlay.getType().equals("P"))
			passPlay = true;

		Player passer = new Player();

		for (int i = 0; i < offensiveRoster.size(); i++){
			if (offensiveRoster.elementAt(i).getPosition().equals("QB") && offensiveRoster.elementAt(i).getOverall() > passer.getOverall()){
				passer = offensiveRoster.elementAt(i);
			}
		}

		if (passPlay && !onSpecialTeams){
			passer.getStats().addPassAttempt();
			offensiveTeam.getStats().addPassAttempt();
		}

		int timeLeft = 0;
		int timeElapsed = analysis.getPlayTime();
		int timeInHuddle = 0;

		if (!clockStopped){
			timeInHuddle = offenseCPU.getTimeInHuddle();
			timeElapsed += timeInHuddle;
		}

		if (timeInHuddle > field.getTimeLeft() && !clockStopped)
			quarterEnded = true;

		clockStopped = false;

		// Change the amount of time left
		if (!onSpecialTeams){

			if (quarterEnded){
				currentDrive.addTimeElapsed(field.getTimeLeft());
				offensiveTeam.getStats().addTimeOfPossession(field.getTimeLeft());
			}
			else if (timeElapsed > field.getTimeLeft()){
				System.out.println("Play duration: " + field.getTimeLeft() + " seconds");

				timeLeft = 0;

				offensiveTeam.getStats().addTimeOfPossession(field.getTimeLeft());

				currentDrive.addPlay(yardsGained, field.getTimeLeft());


			//	field.reduceTime(field.getTimeLeft(), false);
			}
			else{

				System.out.println("Play duration: " + timeElapsed + " seconds");

				timeLeft = field.getTimeLeft() - timeElapsed;

				offensiveTeam.getStats().addTimeOfPossession(timeElapsed);
			//	field.reduceTime(timeElapsed, false);

				if (!quarterEnded)
					currentDrive.addPlay(yardsGained, timeElapsed);
				else
					currentDrive.addTimeElapsed(timeInHuddle);

			}

		}




		if (!quarterEnded){

			System.out.println("Size: " + drives.size() + "  Elapsed: " + currentDrive.getTimeElapsed() + " Yds" + currentDrive.getYardsCovered());
			if (!analysis.isBallKicked()){

				// If a first down is made
				if (yardsGained >= field.getYardsToGo() && !analysis.passIntercepted()){
					isFirstDown = true;

	
					// If touchdown was scored, set ball at opponent's 3 yd line
					if (field.getYardline() + yardsGained >= 100){

						if (field.getDown() == 3){
							offensiveTeam.getStats().addThirdDownConversion();
							offensiveTeam.getStats().addThirdDownAttempt();
						}
						else if (field.getDown() == 4){
							offensiveTeam.getStats().addFourthDownConversion();
							offensiveTeam.getStats().addFourthDownAttempt();
						}

						field.setDown(1);
						field.setYardsToGo(10);
					//	System.out.println(field.getPossession());

						if (onSpecialTeams){
							field.conversionScored();
							conversionScored = true;
							//changeOfPossession = true;
							field.setYardline(35);
							clockStopped = true;

							currentDrive.addExtraPointAttempt(true, "2PT", primaryPlayer.getName());
						}
						else{
							if (field.getYardline() >= 80)
								offensiveTeam.getStats().addRedZoneConversion();

							field.touchdownScored();		// Yardline is taken care of here
							touchdownScored = true;
							kickingExtraPoint = true;
							clockStopped = true;
							field.setYardsToGo(3);
							offensiveTeam.getStats().addScoring(field.getQuarter(), 6);

							if (passPlay)
								currentDrive.addScoringDrive(field.getQuarter(), timeLeft, "P", passer.getName(), primaryPlayer.getName(), yardsGained, field.getScoringTeamScore(), field.getDefendingTeamScore() );
							else
								currentDrive.addScoringDrive(field.getQuarter(), timeLeft, "R", primaryPlayer.getName(), "", yardsGained, field.getScoringTeamScore(), field.getDefendingTeamScore() );
						}

						isFirstDown = false;

					}

					// If a first down is made
					else{
						offensiveTeam.getStats().addFirstDown();

						if (field.getDown() == 3){
							offensiveTeam.getStats().addThirdDownAttempt();					
							offensiveTeam.getStats().addThirdDownConversion();
						}
						else if (field.getDown() == 4){
							offensiveTeam.getStats().addFourthDownAttempt();
							offensiveTeam.getStats().addFourthDownConversion();
						}

						field.setDown(1);
						field.setYardsToGo(10);
						field.setYardline(field.getYardline() + yardsGained);

						if (field.getYardline() >= 80 && !teamInRedZone){
							offensiveTeam.getStats().addRedZoneAttempt();
							teamInRedZone = true;
						}

						System.out.println("Yardline: " + field.getYardline());
						if( field.getYardline() > 90)
							field.setYardsToGo(100 - field.getYardline());
					}
				}
				// If a first down is not made
				else{
					if (onSpecialTeams){
						conversionMissed = true;
						clockStopped = true;
						currentDrive.addExtraPointAttempt(false, "2PT", primaryPlayer.getName());
						field.setYardline(35);
					//	changeOfPossession = true;

					}

					// If safety is scored, reward the defensive team and give defense the ball at the 45
					else if (field.getYardline() + yardsGained <= 0){
						field.safetyScored();
						clockStopped = true;
						safetyScored = true;
						field.setYardline(20);
					//	changeOfPossession = true;
						kickingOff = true;
						defensiveTeam.getStats().addScoring(field.getQuarter(), 2);
						// scoringDriveEnded = true;

						Drive safetyDrive = new Drive(defensiveTeam.getName());
						drives.add(safetyDrive);
						currentDrive = drives.elementAt(drives.size() - 1);

						currentDrive.addScoringDrive(field.getQuarter(), timeLeft, "S", "", "", (0 - yardsGained), field.getDefendingTeamScore(), field.getScoringTeamScore() );

					}
					else{
						// Move down marker
						field.setYardsToGo(field.getYardsToGo() - yardsGained);
	
						// Move ball
						field.setYardline(field.getYardline() + yardsGained);
						System.out.println("Yardline: " + field.getYardline());

						if (field.getDown() == 3)
							offensiveTeam.getStats().addThirdDownAttempt();
						else if (field.getDown() == 4)
							offensiveTeam.getStats().addFourthDownAttempt();

						// If it was not fourth down, increase the down number
						if (field.getDown() != 4)
							field.setDown(field.getDown() + 1);

						// If it is fourth down, change possession
						else{
					//		field.setYardline(100 - field.getYardline());
							changeOfPossession = true;
							clockStopped = true;
						//	if (field.getYardline() >= 80 && !teamInRedZone){
						//		defensiveTeam.getStats().addRedZoneAttempt();
						//		teamInRedZone = true;
						//	}
						}
					}
				}
			}
			// If the ball was kicked
			else{
			//	if (analysis.kickIsSuccessful()){
					if (analysis.getPlayType().equals("Field_Goal")){
						clockStopped = true;

						attemptDistance = 117 - field.getYardline();
						System.out.println("Kick attempt distance: " + attemptDistance + "  Kick distance: " + kickDistance);
						if (kickDistance + field.getYardline() >= 103){		// Omitting the 7 yards between the LOS and kicker
							if (kickAccuracy >= 42 && kickAccuracy <= 58){

								fieldGoalScored = true;
								if (onSpecialTeams){
									field.patScored();		// Yardline taken care of here
									field.setYardline(35);

									currentDrive.addExtraPointAttempt(true, "XP", primaryPlayer.getName());

								}
								else{
									if (field.getYardline() >= 80)
										offensiveTeam.getStats().addRedZoneConversion();

									field.fieldGoalScored();
									field.setYardline(35);
									currentDrive.addScoringDrive(field.getQuarter(), timeLeft, "FG", analysis.getOffensivePlayer().getName(), "", attemptDistance, field.getScoringTeamScore(), field.getDefendingTeamScore() );

								}

							//	changeOfPossession = true;

							}
							else{
								if (kickAccuracy < 42)
									fieldGoalMissed = "wide left";
								else
									fieldGoalMissed = "wide right";
							}
						}
						else{
							fieldGoalMissed = "short";
						}


						if (!fieldGoalScored && !onSpecialTeams){
							field.setYardline(field.getYardline() - 7);
							changeOfPossession = true;
						}
						else if (!fieldGoalScored && onSpecialTeams){
							field.setYardline(35);
							currentDrive.addExtraPointAttempt(false, "XP", primaryPlayer.getName());
						//	changeOfPossession = true;
							kickingOff = true;
						}

					}
					else if (analysis.getPlayType().equals("Punt")){

						if ((field.getYardline() + analysis.getKickDistance()) >= 100)
							field.setYardline(80);
						else
							field.setYardline(field.getYardline() + analysis.getKickDistance());

						changeOfPossession = true;	

					}
			/*		else if (analysis.getPlayType().equals("Kickoff")){
						if ((field.getYardline() + analysis.getKickDistance()) >= 100)
							field.setYardline(80);
						else
							field.setYardline(field.getYardline() + analysis.getKickDistance());

						changeOfPossession = true;	

					}
			*/

				}

				if (analysis.passIntercepted() || (analysis.ballFumbled() && analysis.turnoverOccurred())){
						clockStopped = true;

						if (analysis.passIntercepted()){
							passer.getStats().addInterception();
							offensiveTeam.getStats().addInterception();

							if (field.getYardline() >= 100)
								field.setYardline(80);

						}
					
						isFirstDown = false;

						offensiveTeam.getStats().addTurnover();
						changeOfPossession = true;
				}
		}	// end if quarter has not ended

		if (!onSpecialTeams){

			if (timeInHuddle >= field.getTimeLeft())
				field.reduceTime(timeInHuddle, false);

			else if (timeElapsed >= field.getTimeLeft()){

				field.reduceTime(field.getTimeLeft(), false);
			}
			else{
				field.reduceTime(timeElapsed, touchdownScored);
			}

		}
		else{
			field.reduceTime(0,true);
		}

		boolean scoredInLastQuarter = false;

		if (field.getTimeLeft() == 900)
			scoredInLastQuarter = true;

		String message = "";

		if (quarterEnded){
			message = "Quarter has ended.  No play.";
			changeOfPossession = false;
		}

		else{
			// Pass Play messages
			if (analysis.getPlayType().equals("screen") || analysis.getPlayType().equals("short") || analysis.getPlayType().equals("mid") || analysis.getPlayType().equals("long"))

				// Incomplete passes
				if (analysis.passIncomplete())
					message = "Pass to " + primaryPlayer.getName() + " is incomplete.";

				// Interceptions
				else if (analysis.passIntercepted())
					message = "Pass to " + primaryPlayer.getName() + " is intercepted.";

				// Caught passes
				else{

					if (!onSpecialTeams){
					passer.getStats().addPassCompletion();
					offensiveTeam.getStats().addPassCompletion();
					primaryPlayer.getStats().addReception();

					System.out.println("Pass Yards = " + yardsGained);
						passer.getStats().addPassingYards(yardsGained);
						offensiveTeam.getStats().addPassingYards(yardsGained);
						primaryPlayer.getStats().addReceivingYards(yardsGained);
						passer.getStats().updateLongestPass(yardsGained);
					}

						message = primaryPlayer.getName() + " completes pass for " + yardsGained + " yards.";
				//	}






				}
			// Run plays
			else if (analysis.getPlayType().equals("R")){

				if (!onSpecialTeams){
				primaryPlayer.getStats().addRush();
				offensiveTeam.getStats().addRush();

					primaryPlayer.getStats().addRushingYards(yardsGained);
					offensiveTeam.getStats().addRushingYards(yardsGained);
				}

					message = primaryPlayer.getName() + " runs for " + analysis.getYardsGained() + " yards.";
			//	}
			}
			// Touchdown scored
			if (touchdownScored){
				if (passPlay){
					passer.getStats().addPassTD();
					primaryPlayer.getStats().addReceivingTD();
				}
				else{
					primaryPlayer.getStats().addRushTD();
				}

				// Display opposite team since the possession has already changed (Test only)
					message += "\n" + offensiveTeam.getName() + " scores a touchdown!";
					field.setYardline(97);	// Ball at 3 yard line
					onSpecialTeams = true;
			}

			// Safety scored
			else if (safetyScored){
				if (field.getPossession() == 1)
					message += "\n" + homeTeam.getName() + " scores a safety!";
				else
					message += "\n" + awayTeam.getName() + " scores a safety!";



			}

			// If a punt of FG is kicked
		//	if (analysis.kickIsSuccessful()){

				// Field Goal kicked
				if (analysis.getOffensivePlayer().getPosition().equals("K") && analysis.getPlayType().equals("Field_Goal") ){
					offensiveTeam.getStats().addFieldGoal(fieldGoalScored, attemptDistance);

					if (fieldGoalScored){
						if (onSpecialTeams){

							message = analysis.getOffensivePlayer().getName() + "\'s PAT attempt is good!";

							if (scoredInLastQuarter){
								offensiveTeam.getStats().addScoring(field.getQuarter() - 1, 1);
								field.reduceTime(0,false);
							}
							else
								offensiveTeam.getStats().addScoring(field.getQuarter(), 1);

							analysis.getOffensivePlayer().getStats().addExtraPoint(true);
							onSpecialTeams = false;
							kickingOff = true;
						}
						else{
							message = analysis.getOffensivePlayer().getName() + "\'s " + (attemptDistance) + " yard field goal attempt is good!";

							if (scoredInLastQuarter)
								offensiveTeam.getStats().addScoring(field.getQuarter() - 1, 3);
							else
								offensiveTeam.getStats().addScoring(field.getQuarter(), 3);

							analysis.getOffensivePlayer().getStats().addFieldGoal(true, attemptDistance);
							onSpecialTeams = false;
							kickingOff = true;
						}

					}
					else{
						if (onSpecialTeams){
							message = analysis.getOffensivePlayer().getName() + "\'s yard PAT attempt is no good.";
							field.reduceTime(0,false);
							analysis.getOffensivePlayer().getStats().addExtraPoint(false);
							onSpecialTeams = false;
							kickingOff = true;
						}
						else{
							message = analysis.getOffensivePlayer().getName() + "\'s " + (attemptDistance) + " yard field goal attempt is " + fieldGoalMissed + ".";
							analysis.getOffensivePlayer().getStats().addFieldGoal(false, attemptDistance);
							onSpecialTeams = false;
						}
					}
				}

				// Punt kicked
				else if(analysis.getOffensivePlayer().getPosition().equals("P")){
					message = analysis.getOffensivePlayer().getName() + " punts ball for " + analysis.getKickDistance() + " yards.";
				}
		

			if (conversionScored){
				message += "\n" + offensiveTeam.getName() + " converts for 2 points!";

				if (scoredInLastQuarter)
					offensiveTeam.getStats().addScoring(field.getQuarter() - 1, 2);
				else
					offensiveTeam.getStats().addScoring(field.getQuarter(), 2);
				field.reduceTime(0,false);
				onSpecialTeams = false;
				kickingOff = true;
			}
			else if (conversionMissed){
				message += "\n" + offensiveTeam.getName() + "\'s 2-pt Conversion is No Good.";
				field.reduceTime(0,false);
				onSpecialTeams = false;
				kickingOff = true;
			}

			// First down
			if (isFirstDown)
				message += "\nFirst Down!";

			// Ball is fumbled
			if (analysis.ballFumbled()){
				message += "\nFumble!  ";

				// Turnover
				if (analysis.turnoverOccurred())
					message += "Ball recovered by defense";
				// Fumbled recovered
				else
					message += "Ball recovered by offense";
			}

			if (!clockStopped){

				// Check if offense wants to call time out
				if (field.getTimeouts(field.getPossession()) > 0)
					if (offenseCPU.willTakeTimeOut()){
						clockStopped = true;
						field.timeoutCalled(field.getPossession());	
						System.out.println("Offense has called time out");
					}
				// Check if defense wants to call time out
				if (field.getTimeouts((field.getPossession() % 2) + 1) > 0 && !clockStopped)
					if (defenseCPU.willTakeTimeOut()){
						clockStopped = true;
						field.timeoutCalled((field.getPossession() % 2) + 1);	
						System.out.println("Defense has called time out");
					}
			}

		}	// end if quarter has NOT ended


		System.out.println(offensiveTeam.getName() + ":   " + message);

		if (changeOfPossession)
			changePossession();
		

	}

	void changePossession(){
		field.turnover();

		if (field.getYardline() < 80)
			teamInRedZone = false;

		if (field.getYardline() >= 80 && !teamInRedZone){
			defensiveTeam.getStats().addRedZoneAttempt();
			teamInRedZone = true;
		}

		if (field.getPossession() == 1){
			offensiveTeam = awayTeam;
			defensiveTeam = homeTeam;
		}
		else{
			offensiveTeam = homeTeam;
			defensiveTeam = awayTeam;
		}

		// Use the defensive team's name because no plays were made in order to switch the values yet.
		Drive d = new Drive(offensiveTeam.getName());
		drives.add(d);

	}


	void displayFinalScore(){
		System.out.println("Final Score\n" + homeTeam.getName() + ":  " + field.getHomeScore() + "\n" + awayTeam.getName() + ":  " + field.getAwayScore());
	}

	void printTeamStatistics(){
		System.out.println("Game Statistics:  " + homeTeam.getName() + " " + homeTeam.getNickname() + "  vs.  " + awayTeam.getName() + " " + awayTeam.getNickname() +
			"\n\n\n");

		if (field.getQuarter() == 5){
			System.out.println("                        1   2   3   4   OT    F\n");

			System.out.println(homeTeam.getName() + "   " + homeTeam.getStats().getScoring(1) + "   " + homeTeam.getStats().getScoring(2) + "    " +
				homeTeam.getStats().getScoring(3) + "    " + homeTeam.getStats().getScoring(4) + "    " + homeTeam.getStats().getScoring(5) + "   " + field.getHomeScore() + "\n\n");

			System.out.println(awayTeam.getName() + "   " + awayTeam.getStats().getScoring(1) + "   " + awayTeam.getStats().getScoring(2) + "    " +
				awayTeam.getStats().getScoring(3) + "    " + awayTeam.getStats().getScoring(4) + "    " + awayTeam.getStats().getScoring(5) + "   " + field.getAwayScore() + "\n\n");
		}
		else{
			System.out.println("                        1   2   3   4       F\n");

			System.out.println(homeTeam.getName() + "   " + homeTeam.getStats().getScoring(1) + "   " + homeTeam.getStats().getScoring(2) + "    " +
				homeTeam.getStats().getScoring(3) + "    " + homeTeam.getStats().getScoring(4) + "    " + field.getHomeScore() + "\n\n");

			System.out.println(awayTeam.getName() + "   " + awayTeam.getStats().getScoring(1) + "   " + awayTeam.getStats().getScoring(2) + "    " +
				awayTeam.getStats().getScoring(3) + "    " + awayTeam.getStats().getScoring(4) + "    " + field.getAwayScore() + "\n\n");
		}


		System.out.println("                      " + homeTeam.getName() + "             " + awayTeam.getName() + "\n\n");

		System.out.println("First Downs        " + homeTeam.getStats().getFirstDowns() + "        " + awayTeam.getStats().getFirstDowns() + "\n");
		System.out.println("3rd Down Conv      " + homeTeam.getStats().getThirdDownConversions() + "/" + homeTeam.getStats().getThirdDownAttempts() + "       " +
				awayTeam.getStats().getThirdDownConversions() + "/" + awayTeam.getStats().getThirdDownAttempts() + "\n");
		System.out.println("4th Down Conv      " + homeTeam.getStats().getFourthDownConversions() + "/" + homeTeam.getStats().getFourthDownAttempts() + "       " +
				awayTeam.getStats().getFourthDownConversions() + "/" + awayTeam.getStats().getFourthDownAttempts() + "\n");
		System.out.println("Red Zone Offense   " + homeTeam.getStats().getRedZoneConversions() + "/" + homeTeam.getStats().getRedZoneAttempts() + "       " +
				awayTeam.getStats().getRedZoneConversions() + "/" + awayTeam.getStats().getRedZoneAttempts() + "\n");
		System.out.println("Rushes             " + homeTeam.getStats().getRushes() + "         " + awayTeam.getStats().getRushes() + "\n");
		System.out.println("Rushing Yds        " + homeTeam.getStats().getRushingYards() + "       " + awayTeam.getStats().getRushingYards() + "\n");
		System.out.println("Comp-Att-Int       " + homeTeam.getStats().getPassStats() + "           " + awayTeam.getStats().getPassStats() + "\n");
		System.out.println("Passing Yds        " + homeTeam.getStats().getPassingYards() + "          " + awayTeam.getStats().getPassingYards() + "\n");
		System.out.println("Turnovers          " + homeTeam.getStats().getTurnovers() + "          " + awayTeam.getStats().getTurnovers() + "\n");
		System.out.println("Punt Returns       " + homeTeam.getStats().getPuntReturns() + " / " + homeTeam.getStats().getPuntReturnYards() + "        "
			 + awayTeam.getStats().getPuntReturns() + " / " + awayTeam.getStats().getPuntReturnYards() + "\n");
		System.out.println("Kick Returns       " + homeTeam.getStats().getKickReturns() + " / " + homeTeam.getStats().getKickReturnYards() + "        "
			 + awayTeam.getStats().getKickReturns() + " / " + awayTeam.getStats().getKickReturnYards() + "\n");

		System.out.println("Total Offense      " + homeTeam.getStats().getTotalOffense() + "           " + awayTeam.getStats().getTotalOffense() + "\n");
		System.out.println("Total Yards        " + homeTeam.getStats().getTotalYards() + "            " + awayTeam.getStats().getTotalYards() + "\n");
		System.out.println("Time of Possession " + homeTeam.getStats().getTimeOfPossession() + "           " + awayTeam.getStats().getTimeOfPossession() + "\n");

	}

	Team getHomeTeam(){
		return homeTeam;
	}

	Team getAwayTeam(){
		return awayTeam;
	}

	Vector<Player> getHomeTeamRoster(){
		return homeTeamRoster;
	}

	Vector<Player> getAwayTeamRoster(){
		return awayTeamRoster;
	}

	Field getField(){
		return field;
	}

	Vector<Drive> getDrives(){
		return drives;
	}

	public static void main(String [] args){
		try{
			CPUSimulation sim = new CPUSimulation(args[0], args[1]);
		}
		catch(ArrayIndexOutOfBoundsException ex){
			System.out.println("Simulation requires 2 arguments: " + args[0] + " " + args[1]);
		}

	}

}
