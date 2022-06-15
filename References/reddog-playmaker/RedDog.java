import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.Vector;
import java.io.*;
import java.util.Date;
import java.text.*;

// Main Game

/**
RedDog main class
This class controls elements in the game in regard to actions
performed using JButtons and the outputting of game results
*/
public class RedDog extends JFrame implements ActionListener, ChangeListener{

	/** Holds all visible information within the JFrame */
	JDesktopPane main = new JDesktopPane();

	/** Displays the background image associated with the user's selected team. */
	BackgroundImage background = new BackgroundImage();

	/** Holds all player files for the home team used in game, to be opened and applied to rosters */
	Vector<String> homeTeamPlayerFiles = new Vector<String>();

	/** Holds all player files for the away team used in game, to be opened and applied to rosters */
	Vector<String> awayTeamPlayerFiles = new Vector<String>();

	/** Holds all plays created in play editor */
	Vector<Play> plays = new Vector<Play>();

	/** Index of team selected as human player with JSpinner at Team Select screen */
	int playerSelection = 0;

	/** Index of home team selected with JSpinner at Team Select screen */
	int homeSelection = 0;

	/** Index of away team selected with JSpinner at Team Select screen */
	int awaySelection = 0;

	/** Tag to determine which team received the opening kickoff in the game (0 = away, 1 = home) */
	int receivedOpeningKickoff = 1;

  int numberOfTeamsAvailable = 0;

	/** Array of each team name that is playable and selectable */
	String [] teams;

	/** Array of each team's background image in the order presented by the teams array */
	ImageIcon [] teamBackgrounds;

	/** Array of each team's sliver images for the scoreboard in the order presented by the teams array */
	ImageIcon [] teamSlivers;

	/** Array of each team's helmet image to be displayed in the Team Select menu. */
	ImageIcon [] teamHelmets;

	/** Array of each team's home stadiums to be displayed in the Game Preview screen */
	ImageIcon [] stadiums;

	/** Image array holding the information for background image icons */
	Image [] backgrounds;

	/** Image array holding the information for sliver image icons */
	Image [] slivers;

	/** Image array holding the information for helmet image icons */
	Image [] helmets;

	/** List model holding list of all team names for selection of home team */
	CyclingSpinnerListModel homeModel;

	/** List model holding list of all team names for selection of away team */
	CyclingSpinnerListModel awayModel;

	/** Spinner used for selection of home team in Team Select */
	JSpinner homeSpinner;

	/** Spinner used for selection of away team in Team Select */
	JSpinner awaySpinner;

	/** Label in Team Select that holds the helmet image of the team currently selected as home */
	JLabel homeValue = new JLabel();

	/** Label in Team Select that holds the helmet image of the team currently selected as away */
	JLabel awayValue = new JLabel();

	/** Radio button to select the home team as the player's team */
	JRadioButton homeSelect = new JRadioButton("Home");

	/** Radio button to select the away team as the player's team */
	JRadioButton awaySelect = new JRadioButton("Away");

	JRadioButton simulationSelect = new JRadioButton();

	/** Button group holding both radio buttons to prevent choosing both options */
	ButtonGroup group = new ButtonGroup();

	/** Tag determining if a scenario is currently being implemented */
	boolean inScenario = false;

	/** Holds the score to be set for the home team */
	JComboBox homeScoreSelect;

	/** Holds the score to be set for the away team */
	JComboBox awayScoreSelect;

	/** Holds the quarter to be set for the scenario */
	JComboBox quarterSelect;

	/** Holds the down number to be set for the scenario */
	JComboBox downSelect;

	/** Holds the yardage remaining to be set for the scenario */
	JComboBox toGoSelect;

	/** Holds the yard line to be set for the scenario */
	JComboBox yardLineSelect;

	/** Holds the minutes remaining in the quarter to be set for the scenario */
	JComboBox minutesLeftSelect;

	/** Holds the seconds remaining in the quarter to be set for the scenario */
	JComboBox secondsLeftSelect;

	/** Radio button indicating that the offense is on their side of the field */
	JRadioButton upfield;

	/** Radio button indicating that the offense is on the defensive side of the field */
	JRadioButton downfield;

	/** Panel holding scenario data */
	JPanel scenarioPanel = new JPanel();

	/** Radio button indicating that the home team has possession of the ball */
	JRadioButton homePossession = new JRadioButton("Home Possess");

	/** Radio button indicating that the away team has possession of the ball */
	JRadioButton awayPossession = new JRadioButton("Away Possess");

	/** Tag determining if a tournament or playoff is currently being implemented */
	boolean inTournament = false;

	/** Combo box to select the number of teams for a tournament*/
	JComboBox numberOfTeams = new JComboBox();

	JComboBox numberOfSeasonTeams = new JComboBox();

	/** List model for selected teams list*/
	DefaultListModel teamListModel = new DefaultListModel();

	/** List of teams to be implemented in tournament*/
	JList teamsToBeUsed;

	/** Tournament variable holding all information regarding seeding, games, and progression */
	Tournament tournament;

	/** Table used to hold both tournament game listings and season game listings */
	JTable gameTable;

	/** Tag determining if a season is currently being implemented */
	boolean inSeason = false;

	/** Season variable holding all information regarding standings, scores, schedules, and season progression */
	Season season;

	/** Array to hold teams by division for season creation */
	JList [] divisionalTeams = new JList[11];

	/** Computer AI to play against the user during the game */
	CPU cpu = new CPU();

	// Attributes of home and away team

	/** Home team attributes, excluding roster information */
	Team homeTeam = new Team();

	/** Away team attributes, excluding roster information */
	Team awayTeam = new Team();

	/** Placeholder filled in by homeTeamRoster or awayTeamRoster when necessary */
	Vector<Player> roster;

	/** Home team roster listing */
	Vector<Player> homeTeamRoster = new Vector<Player>();

	/** Away team roster listing */
	Vector<Player> awayTeamRoster = new Vector<Player>();

	/** In-game field attributes (score, time, down, etc.) */
	Field field = new Field();

	/** Opening menu option to start game */
	JButton playGame = new JButton("Play Game");

	/** Opening menu option to create a scenario */
	JButton playScenario = new JButton("Play Scenario");

	/** Opening menu option to begin a new football season */
	JButton startSeason = new JButton("Start Season");

	/** Opening menu option to load a previously saved football season */
	JButton loadSeason = new JButton("Load Season");

	/** Opening menu option to begin a new football tournament */
	JButton startTournament = new JButton("Start Tournament");

	/** Opening menu option to load a previously saved tournament */
	JButton loadTournament = new JButton("Load Tournament");

	/** Button used to implement a scenario based on the information given*/
	JButton startScenario = new JButton("Start Scenario");

	/** Translucent Panel */
	TranslucentPanel tp = new TranslucentPanel(0.4f);

	/** Panel holding game climate information in game preview screen */
	JPanel climateInformation = new JPanel();

	/** Label displaying the away team's logo in game preview screen */
	JLabel awayTeamLogo = new JLabel();

	/** Label displaying the home team's logo in game preview screen */
	JLabel homeTeamLogo = new JLabel();

	/** Label displaying the home team's home jersey in game preview screen */
	JLabel homeJersey = new JLabel();

	/** Label displaying the away team's away jersey in game preview screen */
	JLabel awayJersey = new JLabel();

	/** Multi-functional button #1 used for selecting kickoffs, side of ball, etc. */
	JButton option1 = new JButton();

	/** Multi-functional button #2 used for selecting kickoffs, side of ball, etc. */
	JButton option2 = new JButton();

	/** Contains sliver indicating away team */
	JLabel awayName = new JLabel();

	/** Contains sliver indicating home team */
	JLabel homeName = new JLabel();

	JPanel [] possessionLabels = new JPanel[2];

	/** Holds images displaying the away team score on scoreboard */
	JLabel [] awayScores = new JLabel[2];

	/** Holds images displaying the home team score on scoreboard */
	JLabel [] homeScores = new JLabel[2];

	/** Holds images displaying the yards to go on scoreboard */
	JLabel [] yardsToGo = new JLabel[2];

	/** Holds images displaying the current yardline on scoreboard */
	JLabel [] yardline = new JLabel[2];

	/** Holds images displaying the game clock on scoreboard */
	JLabel [] gameClock = new JLabel[4];

	/** Holds images displaying the number of timeouts for away team on scoreboard */
	JLabel [] awayTimeouts = new JLabel[3];

	/** Holds images displaying the number of timeouts for home team on scoreboard */
	JLabel [] homeTimeouts = new JLabel[3];

	/** Holds images displaying the down number on scoreboard */
	JLabel downNum = new JLabel();

	/** Holds images displaying the quarter number on scoreboard */
	JLabel qtrNum = new JLabel();

	/** Holds images displaying the field direction (upfield vs downfield) on scoreboard */
	JLabel fieldDirection = new JLabel();

	/** Holds images displaying the play clock time on scoreboard */
	JLabel playClockAmount = new JLabel();

	/** Button to call a time out for the player's team */
	JButton callTimeOut = new JButton("Time Out");

	/** Vector holding all drives performed during the course of a game */
	Vector<Drive> drives = new Vector<Drive>();

	/** Timer used to run and control the main game clock */
	private Timer gameTimer = new Timer(300, new TimerListener());

	/** Value to be displayed of the play clock */
	int playClockRemaining = 40;

	/** Timer used to run and control the game clock */
	private Timer playClock = new Timer(300, new TimerListener());

	/** Tag to determine if the clock has stopped for any reason (score, turnover, time out, etc) */
	boolean clockStopped = false;

	boolean clockStoppedForCPU = false;

	boolean quarterDisplayed = false;

	/** Tag to determine if a scoring drive has finished */
	boolean scoringDriveEnded = false;

	/** Holds currently available formations for play selection */
	JButton [] formations = new JButton[5];

	/** Holds list of all possible plays to select during drive */
	JComboBox playList = new JComboBox();

	JRadioButton [] playerSelect = new JRadioButton[3];

	/** Holds radio buttons for all players that can be selected for the play selected */
	ButtonGroup playerList = new ButtonGroup();

	/** Holds all information pertaining to selecting a play, a player, and implementing the play */
	JPanel playDisplay = new JPanel();

	/** Placeholder for play selected by offensive team, whether it is the user or CPU */
	Play offensivePlay;

	/** Placeholder for play selected by defensive team, whether it is the user or CPU */
	Play defensivePlay;

	/** Placeholder for player selected by offensive team, whether it is the user or CPU */
	Player primaryPlayer;

	/** Placeholder for the current offensive team, whether it is the user or CPU */
	Team offensiveTeam;

	/** Placeholder for the current defensive team, whether it is the user or CPU */
	Team defensiveTeam;

	/** Button to implement the currently selected play */
	JButton usePlay = new JButton("Go");

	/** Tag to determine if the user's team is currently on offense (or offensive special teams role) */
	boolean onOffense = false;

	/** Tag to determine if the user's team is currently on defense (or defensive special teams role) */
	boolean onDefense = false;

	/** Tag to determine if the user's team is currently on special teams (with exception of punting) */
	boolean onSpecialTeams = false;

	/** Tag to determine if the user's team is currently kicking off the ball */
	boolean kickingOff = false;

	/** Tag to determine if the offensive team is currently inside the defense's 20 yard line */
	boolean teamInRedZone = false;

	/** Placeholder for kickoffs to store the kicker's player information */
	Player kicker;

	Player kickReturner;

	/** Stores the distance of a kickoff, punt or field goal */
	int kickDistance = 0;

	/** Stores the accuracy level of a kick, with 50 being a straight kick */
	int kickAccuracy = 0;

	/** Stores the final distance that was attempted for a kick. */
	int attemptDistance = 0;

	/** Stores the distance that was run by the return team for a kick */
	int returnDistance = 0;

	/** Image displayed in back of screen, to be altered based on the type of game being played */
	BackgroundImage mainBackground = new BackgroundImage();

	/**
	Class Constructor
	Initializes all labels, background image, and user playbook
	*/
	public RedDog(){

		// Add desktop pane to frame
		this.setLayout(null);
		main.setBounds(0,0,800,600);
		this.add(main);
		main.setOpaque(false);
		mainBackground.setBounds(0,0,800,570);
		this.add(mainBackground);

		// Set background image bounds to cover all but scoreboard area
		main.setLayout(null);
		background.setBounds(0,100,800,500);

		playClockAmount.setText(String.valueOf(playClockRemaining));

  	  	// Open the image path file and set images
    		setTeamImages();
		homeModel = new CyclingSpinnerListModel(teams);
		awayModel = new CyclingSpinnerListModel(teams);
		homeSpinner = new JSpinner(homeModel);
		awaySpinner = new JSpinner(awayModel);

		// Initialize the playbook
		createPlaybook();

		// Initialize all formation buttons
		for (int i = 0; i < 5; i++){
			formations[i] = new JButton();
			formations[i].addActionListener(this);
		}


		// Add action listeners to game option buttons
		playGame.addActionListener(this);
		playScenario.addActionListener(this);
		startTournament.addActionListener(this);
		startSeason.addActionListener(this);
		option1.addActionListener(this);
		option2.addActionListener(this);
		usePlay.addActionListener(this);
		callTimeOut.addActionListener(this);
      loadSeason.addActionListener(this);
      loadTournament.addActionListener(this);

		// Create change listener for home team JSpinner to update the helmet label
		homeSpinner.addChangeListener(this);

		// Create change listener for away team JSpinner to update the helmet label
		awaySpinner.addChangeListener(this);

		// Initialize game clock labels
		for (int i = 0; i < 4; i++)
			gameClock[i] = new JLabel();

		// Initialize score and scoreboard labels
		for (int i = 0; i < 2; i++){
			awayScores[i] = new JLabel();
			homeScores[i] = new JLabel();
			yardsToGo[i] = new JLabel();
			yardline[i] = new JLabel();
			possessionLabels[i] = new JPanel();

		}

		// Initialize timeouts and player selection lists
		for (int i = 0; i < 3; i++){
			awayTimeouts[i] = new JLabel();
			homeTimeouts[i] = new JLabel();
			playerSelect[i] = new JRadioButton();
			playerList.add(playerSelect[i]);
		}

		// Initialize lists for season creation
		for (int i = 0; i < 11; i++)
			divisionalTeams[i] = new JList(new DefaultListModel());


		// Add radio buttons for user team selection
		group.add(homeSelect);
		group.add(awaySelect);
		group.add(simulationSelect);


		// Start main menu
		mainMenu();
	}
   

	/**
	Loads image path file and adds all paths to the appropriate
  variable for use in the game
	*/
  void setTeamImages(){
    try{
      String filename = "teamImagePaths.txt";
    //  File file = new File(filename);
      java.util.Scanner in = new java.util.Scanner(getClass().getResourceAsStream(file));
      
      numberOfTeamsAvailable = in.nextInt();
      
      // I am only doing this so that the Easter Egg team (not created) is not included
      // This will be dealt with once the team is up and running.
      numberOfTeamsAvailable--;
      
      teams = new String[numberOfTeamsAvailable];
      teamBackgrounds = new ImageIcon[numberOfTeamsAvailable];
      teamSlivers = new ImageIcon[numberOfTeamsAvailable];
      teamHelmets = new ImageIcon[numberOfTeamsAvailable];
      stadiums = new ImageIcon[numberOfTeamsAvailable];
      backgrounds = new Image[numberOfTeamsAvailable];  
      slivers = new Image[numberOfTeamsAvailable];
      helmets = new Image[numberOfTeamsAvailable];
  
      for (int i = 0; i < numberOfTeamsAvailable; i++){
        teams[i] = in.next().replaceAll("_"," ");
        teamBackgrounds[i] = new ImageIcon(getClass.getResource(in.next()));
        teamSlivers[i] = new ImageIcon(getClass.getResource(in.next()));
        teamHelmets[i] = new ImageIcon(getClass.getResource(in.next()));
        stadiums[i] = new ImageIcon(getClass.getResource(in.next()));
      }
  
    // Initialize all team images
		for (int i = 0; i < numberOfTeamsAvailable; i++){
			backgrounds[i] = teamBackgrounds[i].getImage();
			slivers[i] = teamSlivers[i].getImage();
			helmets[i] = teamHelmets[i].getImage();
		}
      
      in.close();
  
    }catch(Exception e){
      e.printStackTrace();
    }
  }

	/**
	Loads playbook file and adds all plays to system
	for use by the player during the game
	*/
	void createPlaybook(){

		// In case of exceptions...
		try{
			// Open playbook
			String filename = "playbook.pb";
	  // 	File file = new File(filename);
			java.util.Scanner in = new java.util.Scanner(getClass().getResourceAsStream(file));

			// Cycle through until there are no more plays to load
			while (in.hasNext()){

				// Create play object
				Play p = new Play();

				// Set play name
				p.setName(in.next());

				// Set side of ball (O = Offense, D = Defense, S = Special Teams)
				p.setSideOfBall(in.next());

				// Set type of play as long as it is not a special teams play
				// Play type: R = run, P = pass, B = blitz, M = man coverage, Z = zone, G = goal line
				if (!p.getSideOfBall().equals("S"))
					p.setType(in.next());

				// If play is offensive, add receiver and formation information
				if (p.getSideOfBall().equals("O")){

					// Add number of receivers
					p.setNumberOfReceivers(in.nextInt());

					// Add player positions
					for (int i = 0; i < p.getReceivers(); i++){
						p.setPlayers(i+1, in.next());
					}

					// Set formation (I-Formation, Splitback, etc)
					p.setFormation(in.next());

					// Set routes for players (long/mid/short/screen pass, in/out run)
					for (int i = 0; i < p.getReceivers(); i++){
						p.setRoutes(i+1, in.next());
					}
				}

				// For defensive plays, set formation (Dime, 3-4, Goal Line, etc)
				else if (p.getSideOfBall().equals("D"))

					p.setFormation(in.next());

				// For special teams, set formation based on the play's name



				else if (p.getSideOfBall().equals("S"))
					p.setFormation(p.getName());

				// Add play to the playbook
				plays.add(p);
			}


		}catch(Exception e){
			// If something goes horribly wrong, tell me where it happened.
			e.printStackTrace();
		}
	}


	/**
	Shows main menu for game.  Options for playing a game, scenario, season, or tournament
	will be available.
	*/
	void mainMenu(){
		// Set background to standard green background
		mainBackground.setImage(new ImageIcon(getClass.getResource("images/system/gameBackground.png")).getImage());

		JLabel gameTitle = new JLabel(new ImageIcon(getClass.getResource("images/system/gameTitle.png")));

		gameTitle.setBounds(175,50,450,250);
		main.add(gameTitle);


		// Add options to start/load game, start/load season, start/load playoff
		// Add "Play Game" feature
		playGame.setBounds(300,250,150,40);
		main.add(playGame);

		playScenario.setBounds(300,310,150,30);
		main.add(playScenario);

		startSeason.setBounds(180,380,150,40);
		main.add(startSeason);
	//	startSeason.setEnabled(false);

  loadSeason.setText("Load Season");
		loadSeason.setBounds(420,380,150,40);
		main.add(loadSeason);
 //  	loadSeason.setEnabled(false);

		startTournament.setBounds(160,450,190,40);
		main.add(startTournament);
	//	startTournament.setEnabled(false);

  loadTournament.setText("Load Tournament");
		loadTournament.setBounds(400,450,190,40);
		main.add(loadTournament);
 //  	loadTournament.setEnabled(false);

		main.setVisible(true);
	}

	/**
	Removes all items listed in the main menu screen
	*/
	void clearMainMenu(){
		main.setVisible(false);
		playGame.setVisible(false);
		playScenario.setVisible(false);
		startSeason.setVisible(false);
		loadSeason.setVisible(false);
		startTournament.setVisible(false);
		loadTournament.setVisible(false);
		main.remove(playGame);
		main.remove(playScenario);
		main.remove(startSeason);
		main.remove(loadSeason);
		main.remove(startTournament);
		main.remove(loadTournament);
		main.removeAll();
		main.setVisible(true);
	}

	/**
	Takes teams selected in Team Select and initializes game with team info and graphics
	@param homeSelected - Name of home team selected by player
	@param awaySelected - Name of away team selected by player
	@param homeIsPlayer - Tag determining if the home team will be player controlled; else, away team is player controlled
	*/
	void loadTeams(String homeSelected, String awaySelected, boolean homeIsPlayer){

		// Determine the indicies of the teams selected to show the sliver corresponding with each.
		for (int i = 0; i < numberOfTeamsAvailable; i++){

			// Set index of home team based on its name
			if (teams[i].replaceAll("\\s","_").equals(homeSelected))
				homeSelection = i;

			// Set index of away team based on its name
			else if (teams[i].replaceAll("\\s","_").equals(awaySelected))
				awaySelection = i;
		}

		// Try-catch block implemented for reading files
		try{

			// Set file for home team based on its name
			String homeFile = "teams/" + homeSelected + ".ft";
  // 		File file = new File(homeFile);

			// Start reading file
			java.util.Scanner in = new java.util.Scanner(getClass().getResourceAsStream(homeFile));

			// Retrieve name, nickname, and all other team information
			homeTeam.setName(in.next());
			homeTeam.setNickname(in.next());
			homeTeam.setLocation(in.next());
			homeTeam.setState(in.next());
			homeTeam.setAbbreviation(in.next());
			homeTeam.setCoach(in.next());
			homeTeam.setStadium(in.next());

			// For domed stadiums, result is 1.  Else, result is 0.
			int isDomed = in.nextInt();

			// Set domed stadium tag
			if (isDomed == 1)
				homeTeam.setDomed(true);
			else
				homeTeam.setDomed(false);


			// Set climate information and division information
  if (inSeason){
    Calendar calendar = season.getGameDay();
    homeTeam.setClimate(in.next(), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), gameTable.getValueAt(gameTable.getSelectedRow(),4));
  }
        else if (inTournament){
           Calendar calendar = tournament.getGameDay();
    homeTeam.setClimate(in.next(), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), gameTable.getValueAt(gameTable.getSelectedRow(),4));
      
      }
  else
			homeTeam.setClimate(in.next());

  if (homeTeam.isDomed()){
    if(inSeason){
        Calendar calendar = season.getGameDay();
    homeTeam.setClimate(in.next(), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), gameTable.getValueAt(gameTable.getSelectedRow(),4));
    }
      else if (inTournament){
           Calendar calendar = tournament.getGameDay();
    homeTeam.setClimate(in.next(), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), gameTable.getValueAt(gameTable.getSelectedRow(),4));
      
      }
    else
				homeTeam.setClimate("Domed");
  }
		 	homeTeam.setDivision(in.next());

			// Set the index of the player controlled team for use in the game background
			if (homeIsPlayer){
				// Home team is player controlled, so set index and CPU.
				homeTeam.setComputer(false);
				for (int i = 0; i < 30; i++){
					if (teams[i].replaceAll("\\s","_").equals(homeSelected))
						playerSelection = i;
				}
			}
			else{
				// Away team is player controlled, so set index and CPU.
				homeTeam.setComputer(true);
				for (int i = 0; i < 30; i++){
					if (teams[i].replaceAll("\\s","_").equals(awaySelected))
						playerSelection = i;
				}
			}

			// Set logo based on link found in the file
			String picFile = in.next();


			homeTeam.setLogo(new ImageIcon(getClass.getResource(picFile)));

			// Retrieve colors for team's primary
			int red, green, blue;
			red = in.nextInt();
			green = in.nextInt();
			blue = in.nextInt();

			// Set team colors
			homeTeam.setPrimaryColor(new Color(red,green,blue));

			// Retrieve colors for team's secondary
			red = in.nextInt();
			green = in.nextInt();
			blue = in.nextInt();
			homeTeam.setSecondaryColor(new Color(red,green,blue));

			// Close file
			in.close();
		}catch(Exception e){
			// In case of a read error, print the stack trace.
			e.printStackTrace();
		}

		// Keeps track of the number of players on the team roster
		int capacity = 0;

		try{
			homeTeamPlayerFiles.clear();

			// Open the team's roster file
			String playerFiles = "teams/" + homeTeam.getName() + "/teamRoster.tr";
	  // 	File file = new File(playerFiles);

			// Begin reading file
			java.util.Scanner in = new java.util.Scanner(getClass().getResourceAsStream(playerFiles));
			
			// Until the file ends for reading, input each file path found in the roster file.
			while(in.hasNext()){
				String playerFile = in.next();

				homeTeamPlayerFiles.add(playerFile);
				capacity++;

			}

			// Close file
			in.close();

			homeTeam.getStats().initialize();
		}catch(Exception e){
			e.printStackTrace();
		}

		// For each file previously inputted, open the file and gather the player data for the team roster
		for(int i = 0; i < capacity; i++){
			try{
				// Set file and open for reading
            String file = "teams/" + homeTeam.getName() + "/" + homeTeamPlayerFiles.elementAt(i).toString());
//				File file = new File("teams/" + homeTeam.getName() + "/" + homeTeamPlayerFiles.elementAt(i).toString());
				java.util.Scanner in = new java.util.Scanner(getClass().getResourceAsStream(file));

				// Set name attributes
				String fName = in.next();
				String lName = in.next();

				// Set position and team attributes
				String position = in.next();
				String team = in.next();
				String nickname = in.next();

				// Set position number, school and personal attributes
				int number = in.nextInt();
				String school = in.next();
				int age = in.nextInt();
				int experience = in.nextInt();

				int height = in.nextInt();
				int weight = in.nextInt();

				// The remaining file information is the individual attributes for the player
				// This determines the player's effectiveness on the field.

				int [] attributes = new int[16];
				for (int j = 0; j < 16; j++){
					attributes[j] = in.nextInt();
				}
				int overall = in.nextInt();

				// Close file
				in.close();


				// Create new Player object
				Player p = new Player();

				// Set all player information based on the attributes gathered above
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

				// Add player to the roster
				homeTeamRoster.add(p);

			}catch(Exception e){
				// Show error location if exception occurs
				e.printStackTrace();
			}
			
		}// Home team is finished being compiled

		try{
			// Set file for away team based on the team's name
			String awayFile = "teams/" + awaySelected + ".ft";
	//		File file = new File(awayFile);

			// Set the file and open for reading
			java.util.Scanner in = new java.util.Scanner(getClass().getResourceAsStream(awayFile));
			awayTeam.setName(in.next());
			awayTeam.setNickname(in.next());
			awayTeam.setLocation(in.next());
			awayTeam.setState(in.next());
			awayTeam.setAbbreviation(in.next());
			awayTeam.setCoach(in.next());
			awayTeam.setStadium(in.next());


			// Set domed stadium information.  However, being the away team this will not impact the game in any way.
			int domed = in.nextInt();

			if (domed == 1)
				awayTeam.setDomed(true);
			else
				awayTeam.setDomed(false);

			// Set climate and division information
			awayTeam.setClimate(in.next());

			if (awayTeam.isDomed())
				awayTeam.setClimate("Domed");

			awayTeam.setDivision(in.next());


			// Set team logo information
			String picFile = in.next();


			awayTeam.setLogo(new ImageIcon(getClass.getResource(picFile)));

			// Retrieve colors for away team's primary
			int red, green, blue;
			red = in.nextInt();
			green = in.nextInt();
			blue = in.nextInt();

			awayTeam.setPrimaryColor(new Color(red,green,blue));

			// Retrieve colors for away team's secondary
			red = in.nextInt();
			green = in.nextInt();
			blue = in.nextInt();

			awayTeam.setSecondaryColor(new Color(red,green,blue));

			// If the home team is the player, set CPU attribute for away team.  Opposite also occurs.
			if (homeIsPlayer)
				awayTeam.setComputer(true);
			else
				awayTeam.setComputer(false);

			// Close the file
			in.close();

			awayTeam.getStats().initialize();

		}catch(Exception e){
			e.printStackTrace();
		}

		// Reset the capacity total
		capacity = 0;

		try{
			awayTeamPlayerFiles.clear();

			// Open file for away team's roster list
			String playerFiles = "teams/" + awayTeam.getName() + "/teamRoster.tr";
		//	File file = new File(playerFiles);
			java.util.Scanner in = new java.util.Scanner(getClass().getResourceAsStream(playerFiles));
			
			// For each line in the file, store the file link into the PlayerFile vector
			while(in.hasNext()){
				String playerFile = in.next();

				awayTeamPlayerFiles.add(playerFile);
				capacity++;

			}

			// Close the roster list file
			in.close();

		}catch(Exception e){
			e.printStackTrace();
		}

		// For each file gathered above, open file and input player attributes
		for(int i = 0; i < capacity; i++){
			try{
				// Open player file
				String file = "teams/" + awayTeam.getName() + "/" + awayTeamPlayerFiles.elementAt(i).toString();
				java.util.Scanner in = new java.util.Scanner(getClass().getResourceAsStream(file));

				// Store player attributes
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

				// Store player skill attributes
				int [] attributes = new int[16];
				for (int j = 0; j < 16; j++){
					attributes[j] = in.nextInt();
				}
				int overall = in.nextInt();

				// Close file
				in.close();

				// Create new player object and set above attributes to player
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

				// Add player to away team roster
				awayTeamRoster.add(p);


			}catch(Exception e){
				e.printStackTrace();
			}	
		} // Away team is finished being compiled

		// Set field attributes for the current game (stadium, location and weather)
		field.setStadium(homeTeam.getStadium());
		field.setLocation(homeTeam.getLocation(), homeTeam.getState());
	}


	/**
	Opens scenario menu, allowing user to set all values of a game.  This includes teams, scores,
	possession, field position, time and quarter
	*/
	void createScenario(){
		simulationSelect.setSelected(true);
		scenarioPanel.setLayout(null);
		scenarioPanel.setBackground(Color.GRAY);

		// Add border to helmet images
		awayValue.setBorder(BorderFactory.createRaisedBevelBorder());
		homeValue.setBorder(BorderFactory.createRaisedBevelBorder());

		homeValue.setOpaque(true);
		awayValue.setOpaque(true);

		homeValue.setBackground(new Color(140,155,140));
		awayValue.setBackground(new Color(140,155,140));

		BackgroundImage scenarioImage = new BackgroundImage();
		ButtonGroup possessionGroup = new ButtonGroup();

		possessionGroup.add(homePossession);
		possessionGroup.add(awayPossession);

		String [] fourArray = {"1", "2", "3", "4"};
		Vector<Integer> minutes = new Vector<Integer>();
		Vector<Integer> seconds = new Vector<Integer>();
		Vector<Integer> scores = new Vector<Integer>();
		Vector<Integer> yards = new Vector<Integer>();
		Vector<Integer> toGoAmounts = new Vector<Integer>();

		for (int i = 0; i <= 15; i++)
			minutes.add(i);

		for (int i = 0; i < 60; i++)
			seconds.add(i);

		for (int i = 0; i <= 59; i++)
			scores.add(i);

		for (int i = 1; i <= 50; i++)
			yards.add(i);

		for (int i = 1; i <= 99; i++)
			toGoAmounts.add(i);

		

		homeScoreSelect = new JComboBox(scores);
		awayScoreSelect = new JComboBox(scores);
		quarterSelect = new JComboBox(fourArray);
		downSelect = new JComboBox(fourArray);
		toGoSelect = new JComboBox(toGoAmounts);
		yardLineSelect = new JComboBox(yards);
		minutesLeftSelect = new JComboBox(minutes);
		secondsLeftSelect = new JComboBox(seconds);

		upfield = new JRadioButton("^");
		downfield = new JRadioButton("V");
		ButtonGroup fieldPosition = new ButtonGroup();
		fieldPosition.add(upfield);
		fieldPosition.add(downfield);

		inScenario = true;

		homeSpinner.setBounds(120,115,150,20);
		awaySpinner.setBounds(120,265,150,20);

		homeValue.setBounds(10,75,100,100);


		awayValue.setBounds(10,225,100,100);

		homeSelect.setBounds(120,135,100,20);
		awaySelect.setBounds(120,285,100,20);


		homeValue.setVisible(true);
		awayValue.setVisible(true);
		homeSpinner.setVisible(true);
		awaySpinner.setVisible(true);
		homeSelect.setVisible(true);
		awaySelect.setVisible(true);

		homePossession.setBounds(240,135,100,20);
		awayPossession.setBounds(240,285,100,20);

		homeScoreSelect.setBounds(350,100,50,30);
		awayScoreSelect.setBounds(350,250,50,30);

		quarterSelect.setBounds(450,100,50,30);
		minutesLeftSelect.setBounds(550,100,50,30);
		secondsLeftSelect.setBounds(650,100,50,30);

		downSelect.setBounds(450,250,50,30);
		toGoSelect.setBounds(550,250,50,30);
		yardLineSelect.setBounds(650,250,50,30);
		upfield.setBounds(700,250,50,20);
		downfield.setBounds(700,280,50,20);

		scenarioImage.setBounds(0,0,800,400);

		scenarioPanel.add(homeSpinner);
		scenarioPanel.add(awaySpinner);

		scenarioPanel.add(homeValue);
		scenarioPanel.add(awayValue);

		scenarioPanel.add(homeSelect);
		scenarioPanel.add(awaySelect);

		scenarioPanel.add(homePossession);
		scenarioPanel.add(awayPossession);

		scenarioPanel.add(homeScoreSelect);
		scenarioPanel.add(awayScoreSelect);

		scenarioPanel.add(quarterSelect);
		scenarioPanel.add(minutesLeftSelect);
		scenarioPanel.add(secondsLeftSelect);

		scenarioPanel.add(downSelect);
		scenarioPanel.add(toGoSelect);
		scenarioPanel.add(yardLineSelect);
		scenarioPanel.add(upfield);
		scenarioPanel.add(downfield);

		// This has to be added LAST because all of the above need to be added first in order to be visible
		scenarioPanel.add(scenarioImage);

		// Action listener is created here to access variables above to keep them public to the function
		startScenario.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				// Determine which team was selected to be the home team
				boolean homeChosen = false;

				if (homeSelect.isSelected() && !awaySelect.isSelected())
					homeChosen = true;
				else if(awaySelect.isSelected() && !homeSelect.isSelected())
					homeChosen = false;


				// Load team information to begin the game
				loadTeams(homeSpinner.getValue().toString().replaceAll("\\s","_"), awaySpinner.getValue().toString().replaceAll("\\s","_"), homeChosen);

				// Set all field attributes
				field.initializeField();
				field.setQuarter(quarterSelect.getSelectedIndex() + 1);
				field.setTimeLeft(minutesLeftSelect.getSelectedIndex() * 60 + secondsLeftSelect.getSelectedIndex());
				field.setDown(downSelect.getSelectedIndex() + 1);
				field.setHomeScore(homeScoreSelect.getSelectedIndex());
				field.setAwayScore(awayScoreSelect.getSelectedIndex());

				// Change yardline to reflect downfield vs upfield
				if (downfield.isSelected())
					field.setYardline(100 - (yardLineSelect.getSelectedIndex() + 1));
				else
					field.setYardline(yardLineSelect.getSelectedIndex() + 1);

				// For "and Goal" situations, make yards remaining reflect this
				if (field.getYardline() + toGoSelect.getSelectedIndex() + 1 > 100)
					field.setYardsToGo(100 - field.getYardline());
				else
					field.setYardsToGo(toGoSelect.getSelectedIndex() + 1);

				// Set possession
				if (awayPossession.isSelected())
					field.setPossession(1);
				else
					field.setPossession(2);

				// Remove all Team Select attributes from view
				scenarioPanel.setVisible(false);
				startScenario.setVisible(false);

				scenarioPanel.removeAll();
		
				main.removeAll();

				setScoreboard();

				usePlay.setText("Go");

				Drive d;
				if (field.getPossession() == 1)
					d = new Drive(awayTeam.getName());
				else
					d = new Drive(homeTeam.getName());

				drives.add(d);

				if ( (field.getPossession() == 1 && !homeChosen) || (field.getPossession() == 2 && homeChosen) ){
					onOffense = true;
					onDefense = false;
					setOffense();
				}
				else{
					onOffense = false;
					onDefense = true;
					setDefense();
				}

				clockStopped = true;
				clockStoppedForCPU = true;
				onSpecialTeams = false;
				kickingOff = false;
			}

		} );

		scenarioPanel.setBounds(0,100,800,400);
		main.add(scenarioPanel);
		scenarioPanel.setVisible(true);

		startScenario.setBounds(400,500,200,50);
		main.add(startScenario);
		startScenario.setVisible(true);

		scenarioImage.setImage(new ImageIcon(getClass.getResource("images/system/scenarioBackground.png")).getImage());

	}

	/**
	Menu for creating a football tournament of 2,4,6,8,12, or 16 teams.
	This menu will allow for selecting number of teams as well as implementing
	a drag and drop feature for selecting teams
	*/
	void createTournament(){
		JList listOfTeams = new JList(teams);

		teamsToBeUsed = new JList(teamListModel);

		numberOfTeams.removeAllItems();
		numberOfTeams.addItem("2");
		numberOfTeams.addItem("4");
		numberOfTeams.addItem("6");
		numberOfTeams.addItem("8");
		numberOfTeams.addItem("12");
		numberOfTeams.addItem("16");

		// Drag and drop transfer handler
		teamsToBeUsed.setTransferHandler(new TransferHandler() {

	       		public boolean canImport(TransferHandler.TransferSupport info) {

				// Return false if data is not a String (for images, Flavor is imageFlavor)
				if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				    return false;
				}

				JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();

				// If the drop location is outside the list, return false
				if (dl.getIndex() == -1) {
				    return false;
				}
				return true;
		    	}

		    	public boolean importData(TransferHandler.TransferSupport info) {
				if (!info.isDrop()) {
				    return false;
				}
				
				// Check for String flavor
				if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				        return false;
				}

				final int numTeams = Integer.parseInt(numberOfTeams.getSelectedItem().toString());

				// Get location for the drop in the JList
				JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();

				// Get the list model for the teamsToBeUsed list
				DefaultListModel listModel = (DefaultListModel)teamsToBeUsed.getModel();

				// Get the index of where the drop will take place
				int index = dl.getIndex();

				// Determine if the drop is an insertion or an overwrite
				boolean insert = dl.isInsert();

				// Get the current string under the drop.
				String value = "";

				// If the list model is not empty or the index is not at the end of the list, get the value at the current index
				if (!listModel.isEmpty() && index < listModel.size())
					value = (String)listModel.getElementAt(index);


				// Get the string that is being dropped.
				Transferable t = info.getTransferable();
				String data;
				try {
				    data = (String)t.getTransferData(DataFlavor.stringFlavor);
				} 
				catch (Exception e) { return false; }
				

				boolean alreadyInList = false;

				// If there are the max number of teams allowed for x team tournament, no extra teams may be added
				boolean maxTeamsReached = (listModel.size() >= numTeams);

				// Check if the value is already listed
				for (int i = 0; i < listModel.size(); i++){
					if (listModel.getElementAt(i).equals(data)){
						alreadyInList = true;
						insert = false;
					}
				}
				if (alreadyInList || maxTeamsReached){
					return false;
				}

				// Perform the actual import.  
				if (insert) {
				    listModel.add(index, data);
				}
				return true;

			    }
		});
		// Allow for inserting teams, not overwriting them
		teamsToBeUsed.setDropMode(DropMode.INSERT);

		// Allow main team list to have elements taken from it
		listOfTeams.setDragEnabled(true);

		option1.setText("Create Tournament");
		option1.setVisible(true);

		JScrollPane teamList = new JScrollPane(listOfTeams);
		JScrollPane selectedTeams = new JScrollPane(teamsToBeUsed);

		numberOfTeams.setBounds(375,100,50,20);
		option1.setBounds(300,400,200,30);
		teamList.setBounds(100,100,200,150);
		selectedTeams.setBounds(500,100,200,150);

		main.add(option1);
		main.add(numberOfTeams);
		main.add(teamList);
		main.add(selectedTeams);
	}

	/**
	Views tournament bracket and current round's games.  Games that have been completed will have the
	final score displayed in the table.  When the tournament ends, an option to exit the tournament will be displayed
	*/
	void viewTournament(){
		// Set background to tournament gold color
		mainBackground.setVisible(false);
		mainBackground.setImage(new ImageIcon(getClass.getResource("images/system/tournamentBackground.png")).getImage());
		mainBackground.setVisible(true);

  loadTournament.setText("Save Tournament");

		// Retrieve bracket from tournament variable
		JPanel bracket = tournament.getTournamentBracket();

		bracket.setBounds(0,50,800,380);
		main.add(bracket);

		// Set game table to list current round's games
		String [] headers = {"  ", "Away","Home","  ", "Time"};
		Vector<String> tableHeaders = new Vector<String>();

		for (int i = 0; i < 5; i++)
			tableHeaders.addElement(headers[i]);

		Vector<Vector<Object>> games = tournament.getCurrentRoundGames();

		gameTable = new JTable(games,tableHeaders);
		JTableHeader gameHeader = gameTable.getTableHeader();
		gameTable.setEnabled(true);

		// Make score columns shorter than the team name columns
		for (int i = 0; i < 4; i++){
			TableColumn col = gameTable.getColumnModel().getColumn(i);

			int width = 100;
			if (i == 0 || i == 3)
				width = 30;
       else if (i == 4)
        width = 60;
			col.setPreferredWidth(width);
		}

  loadTournament.setBounds(100,50,300,30);
		gameHeader.setBounds(270,400,320,gameTable.getRowHeight());
		gameTable.setBounds(270,400 + gameTable.getRowHeight(),320,games.size() * gameTable.getRowHeight());

		// Disable the moving of columns
		gameHeader.setReorderingAllowed(false);

		// If tournament is over, no more games can be selected
		if (tournament.isTournamentComplete())
			option2.setText("Exit Tournament");
		else
			option2.setText("Select Game");

		option2.setBounds(270,400 + (games.size() + 1) * gameTable.getRowHeight(), 320,30);
		option2.setVisible(true);

  main.add(loadTournament);
		main.add(gameTable);
		main.add(gameHeader);
		main.add(option2);

	}

	/**
	Menu for creating a football season for 4,8,16,20,24,28 and 30 teams

	This menu will allow for selecting number of teams as well as implementing
	a drag and drop feature for selecting teams for custom divisions.
	Standard seasons are available with teams already in place.
	*/
	void createSeason(){
		final DefaultListModel teamModel = new DefaultListModel();
		final JList listOfTeams = new JList(teamModel);
		for (int i = 0; i < 30; i++)
			teamModel.addElement(teams[i]);

		if (numberOfSeasonTeams.getItemCount() == 0){
			numberOfSeasonTeams.addItem("4");
			numberOfSeasonTeams.addItem("8");
			numberOfSeasonTeams.addItem("16");
			numberOfSeasonTeams.addItem("20");
			numberOfSeasonTeams.addItem("24");
			numberOfSeasonTeams.addItem("28");
			numberOfSeasonTeams.addItem("30");
		}

		for (int i = 0; i < 11; i++){
			DefaultListModel listModel = (DefaultListModel)divisionalTeams[i].getModel();
			listModel.clear();
		}

		for (int i = 0; i < 11; i++){
			final int idx = i;
			// Drag and drop transfer handler
			divisionalTeams[idx].setTransferHandler(new TransferHandler() {


		       		public boolean canImport(TransferHandler.TransferSupport info) {

					// Return false if data is not a String (for images, Flavor is imageFlavor)
					if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
					    return false;
					}

					JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();

					// If the drop location is outside the list, return false
					if (dl.getIndex() == -1) {
					    return false;
					}
					return true;
			    	}

			    	public boolean importData(TransferHandler.TransferSupport info) {

					if (!info.isDrop()) {
					    return false;
					}
				
					// Check for String flavor
					if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
						return false;
					}

					final int numTeams = Integer.parseInt(numberOfSeasonTeams.getSelectedItem().toString());

					// Get location for the drop in the JList
					JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();

					// Get the list model for the divisional teams list
					DefaultListModel listModel = (DefaultListModel)divisionalTeams[idx].getModel();

					// Get the index of where the drop will take place
					int index = dl.getIndex();


					// Determine if the drop is an insertion or an overwrite
					boolean insert = dl.isInsert();

					// Get the current string under the drop.
					String value = "";

					// If the list model is not empty or the index is not at the end of the list, get the value at the current index
					if (!listModel.isEmpty() && index < listModel.size())
						value = (String)listModel.getElementAt(index);


					// Get the string that is being dropped.
					Transferable t = info.getTransferable();
					String data;
					try {
					    data = (String)t.getTransferData(DataFlavor.stringFlavor);
					} 
					catch (Exception e) { return false; }
				

					boolean alreadyInList = false;

					boolean maxTeamsReached = false;

					int maxTeamsPerDivision = 4;

					if (numTeams == 20 || numTeams == 30 || (numTeams == 28 && (idx == 3 || idx == 4 || idx == 6) ) )
						maxTeamsPerDivision = 5;

					if (listModel.size() == maxTeamsPerDivision)
						maxTeamsReached = true;

					// Check if the value is already listed
					for (int j = 0; j < 11; j++){
						DefaultListModel lModel = (DefaultListModel)divisionalTeams[j].getModel();
						for (int i = 0; i < lModel.size(); i++){
							if (lModel.getElementAt(i).equals(data)){
								alreadyInList = true;
								insert = false;
							}
						}
					}

					if (alreadyInList || maxTeamsReached){
						return false;
					}

					// Perform the actual import.  
					if (insert) {
					    listModel.add(index, data);
					    teamModel.removeElement(data);
					}
					return true;

				    }
			});

			// Allow for inserting teams, not overwriting them
			divisionalTeams[idx].setDropMode(DropMode.INSERT);
		}

		// Allow main team list to have elements taken from it
		listOfTeams.setDragEnabled(true);

		option1.setText("Create Season");
		option1.setVisible(true);

		listOfTeams.setBounds(625,25,150,550);


		numberOfSeasonTeams.setBounds(375,100,50,20);
		option1.setBounds(300,400,200,30);

		divisionalTeams[0].setBounds(225,300,120,85);
		divisionalTeams[1].setBounds(20,150,120,85);
		divisionalTeams[2].setBounds(20,300,120,85);
		divisionalTeams[3].setBounds(180,300,120,85);
		divisionalTeams[4].setBounds(350,300,120,85);
		divisionalTeams[5].setBounds(450,150,120,85);
		divisionalTeams[6].setBounds(450,300,120,85);
		divisionalTeams[7].setBounds(180,300,120,85);
		divisionalTeams[8].setBounds(350,300,120,85);
		divisionalTeams[9].setBounds(20,300,120,85);
		divisionalTeams[10].setBounds(450,150,120,85);

		main.add(option1);
		main.add(numberOfSeasonTeams);
		main.add(listOfTeams);

		for (int i = 0; i < 11; i++){
			main.add(divisionalTeams[i]);
			divisionalTeams[i].setVisible(false);
		}
		divisionalTeams[0].setVisible(true);

		numberOfSeasonTeams.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				for (int i = 0; i < 11; i++){
					divisionalTeams[i].setVisible(false);
					DefaultListModel listModel = (DefaultListModel)divisionalTeams[i].getModel();
					listModel.clear();
				}

				teamModel.clear();
				for (int i = 0; i < numberOfTeamsAvailable; i++)
					teamModel.addElement(teams[i]);

				switch(Integer.parseInt(numberOfSeasonTeams.getSelectedItem().toString())){
					case 4:{
						divisionalTeams[0].setVisible(true);
						break;
					}
					case 8:{
						divisionalTeams[7].setVisible(true);
						divisionalTeams[8].setVisible(true);
						break;
					}
					case 16:{
						divisionalTeams[1].setVisible(true);
						divisionalTeams[9].setVisible(true);
						divisionalTeams[6].setVisible(true);
						divisionalTeams[10].setVisible(true);
						break;
					}
					case 20:{
						divisionalTeams[1].setVisible(true);
						divisionalTeams[9].setVisible(true);
						divisionalTeams[6].setVisible(true);
						divisionalTeams[10].setVisible(true);
						break;
					}
					case 24:{
						divisionalTeams[1].setVisible(true);
						divisionalTeams[2].setVisible(true);
						divisionalTeams[3].setVisible(true);
						divisionalTeams[4].setVisible(true);
						divisionalTeams[5].setVisible(true);
						divisionalTeams[6].setVisible(true);
						break;
					}
					case 28:{
						divisionalTeams[1].setVisible(true);
						divisionalTeams[2].setVisible(true);
						divisionalTeams[3].setVisible(true);
						divisionalTeams[4].setVisible(true);
						divisionalTeams[5].setVisible(true);
						divisionalTeams[6].setVisible(true);
						break;
					}
					case 30:{
						divisionalTeams[1].setVisible(true);
						divisionalTeams[2].setVisible(true);
						divisionalTeams[3].setVisible(true);
						divisionalTeams[4].setVisible(true);
						divisionalTeams[5].setVisible(true);
						divisionalTeams[6].setVisible(true);
						break;
					}
				}
			}
		});

	}


	/**
	Takes values selected for each division in Season Team Select screen and inserts the
	appropriate division name to load into Season variable.  The season menu is called once
	the Season variable is completed and set.
	*/
	void setSeason(){
		int numTeams = Integer.parseInt(numberOfSeasonTeams.getSelectedItem().toString());
		Vector<String> teamList = new Vector<String>();
		Vector<String> divisionList = new Vector<String>();

		String [] divisions = {"", "Atlantic", "Gulf", "Lakes", "Plains", "Mountain", "Pacific", "Eastern", "Western", "Central", "Midwest"};

		for (int i = 0; i < 11; i++){
			if (divisionalTeams[i].isVisible()){
				DefaultListModel model = (DefaultListModel)divisionalTeams[i].getModel();

				for (int j = 0; j < model.size(); j++){
					teamList.addElement(model.elementAt(j).toString());
					divisionList.addElement(divisions[i]);
				}
			}
		}


		season = new Season(teamList, divisionList);
		main.setVisible(false);
		main.removeAll();
		main.setVisible(true);
		viewSeason();
	}


	/**
	After the creation of the season, this displays team standings, statistics and the current week's schedule.
	Tabs to view a specific team's season schedule will also be available
	*/
	void viewSeason(){
		mainBackground.setVisible(false);
		// Set background to season blue color
		mainBackground.setImage(new ImageIcon(getClass.getResource("images/system/seasonBackground.png")).getImage());
		mainBackground.setVisible(true);

		JTabbedPane seasonInfo = new JTabbedPane();

		// Retrieve season standings from season variable
		JPanel standingsPanel = season.getDivisionalStandings();	

		// Retrieve team schedules from season variable
		JPanel schedulePanel = season.getTeamSchedules();

		// Retrieve league leaders in statistics
		JPanel leagueLeaderPanel = season.getLeagueLeaders();

		// Retrieve current week's schedule to place in table
		String [] headers = {"  ", "Away","Home","  ", "Time"};
		Vector<String> tableHeaders = new Vector<String>();

		for (int i = 0; i < 5; i++)
			tableHeaders.addElement(headers[i]);

		Vector<Vector<Object>> games;

		// If season is over, display the final week's games
		if (season.isSeasonComplete())
			games = season.getWeekSchedule(season.getCurrentWeek() - 1);
		else
			games = season.getWeekSchedule(season.getCurrentWeek());

		gameTable = new JTable(games,tableHeaders);
		JTableHeader gameHeader = gameTable.getTableHeader();

		// Set the score columns to be smaller than the team name columns
		for (int i = 0; i < 5; i++){
			TableColumn col = gameTable.getColumnModel().getColumn(i);

			int width = 100;
			if (i == 0 || i == 3)
				width = 30;
      if (i == 4)
        width = 60;
			col.setPreferredWidth(width);
		}

  loadSeason.setText("Save Season");

		// Display the current week to be played
		JLabel weekNumber = new JLabel("Week " + season.getCurrentWeek());
		weekNumber.setForeground(Color.WHITE);
		weekNumber.setHorizontalAlignment(SwingConstants.CENTER);

		// If season is over, display Season Complete and allow user to exit season
		if (season.isSeasonComplete()){
			weekNumber.setText("Season Complete");
			option2.setText("Exit Season");
			gameTable.setEnabled(false);
		}
		else
			option2.setText("Select Game");


  loadSeason.setBounds(100,500,300,30);
		weekNumber.setBounds(450,100,320,40);
		gameHeader.setBounds(450,150,320,gameTable.getRowHeight());
		gameTable.setBounds(450,170,320,gameTable.getRowHeight()* games.size());
		option2.setBounds(450,170 + ((games.size() + 1) * gameTable.getRowHeight()),320,20);
		option2.setVisible(true);

		// Background Image for scroll pane
		BackgroundImage seasonBack = new BackgroundImage();
		BackgroundImage scheduleBack = new BackgroundImage();

		leagueLeaderPanel.setPreferredSize(new Dimension(360,500));
		standingsPanel.setPreferredSize(new Dimension(360,Math.max(45 * season.getNumberOfTeams(), 400) ));
		schedulePanel.setPreferredSize(new Dimension(360,Math.max(35 * season.getNumberOfTeams(), 400) ));


		seasonBack.setBounds(0,0,360,Math.max(45 * season.getNumberOfTeams(), 400));
		standingsPanel.add(seasonBack);
		seasonBack.setImage(new ImageIcon(getClass.getResource("images/system/statsBackground.png")).getImage());
		seasonBack.setStretched(true);



		JScrollPane standingsPane = new JScrollPane(standingsPanel);
		standingsPane.setOpaque(false);

		JScrollPane schedulePane = new JScrollPane(schedulePanel);
		schedulePane.setOpaque(false);

		JScrollPane leagueLeaderPane = new JScrollPane(leagueLeaderPanel);
		leagueLeaderPane.setOpaque(false);


		gameHeader.setReorderingAllowed(false);

		seasonInfo.add(standingsPane, "Standings");
		seasonInfo.add(schedulePane, "Schedules");
		seasonInfo.add(leagueLeaderPane, "League Leaders");

		seasonInfo.setBounds(5,100,385,400);

  main.add(loadSeason);
		main.add(seasonInfo);
		main.add(weekNumber);
		main.add(gameHeader);
		main.add(gameTable);
		main.add(option2);

	}


	/**
	Sets up the Team Select menu containing JSpinners for selecting home and away teams.
	The menu also displays team helmets and buttons for selecting the player controlled team.
	*/
	void selectTeams(){

		simulationSelect.setSelected(true);

		awayValue.setBorder(BorderFactory.createRaisedBevelBorder());
		homeValue.setBorder(BorderFactory.createRaisedBevelBorder());

		homeValue.setIcon(teamHelmets[homeSelection]);
		awayValue.setIcon(teamHelmets[awaySelection]);
		homeSpinner.setValue(teams[homeSelection]);
		awaySpinner.setValue(teams[awaySelection]);

		if (homeSelection == awaySelection){
			homeValue.setIcon(teamHelmets[18]);
			awayValue.setIcon(teamHelmets[3]);
			homeSpinner.setValue(teams[18]);
			awaySpinner.setValue(teams[3]);
		}

		homeValue.setOpaque(true);
		awayValue.setOpaque(true);

		homeValue.setBackground(new Color(140,155,140));
		awayValue.setBackground(new Color(140,155,140));

		// Set button text
		playGame.setText("Start Game");

		Font spinnerFont = new Font("Times New Roman", Font.BOLD, 16);

		// Set bounds of all labels, spinners and buttons
		awayValue.setBounds(80,100,300,280);
		homeValue.setBounds(420,100,300,280);

		awaySpinner.setBounds(80,385,300,30);
		homeSpinner.setBounds(420,385,300,30);

		awaySpinner.setFont(spinnerFont);
		homeSpinner.setFont(spinnerFont);

		JTextField tf = ((JSpinner.DefaultEditor)awaySpinner.getEditor()).getTextField();  

		tf.setBackground(new Color(140,155,140));
		tf.setForeground(Color.WHITE);
		tf.setHorizontalAlignment(SwingConstants.CENTER);

		JTextField tf2 = ((JSpinner.DefaultEditor)homeSpinner.getEditor()).getTextField();  

		tf2.setBackground(new Color(140,155,140));
		tf2.setForeground(Color.WHITE);
		tf2.setHorizontalAlignment(SwingConstants.CENTER);

		awaySelect.setBounds(315,430,100,30);
		homeSelect.setBounds(420,430,100,30);

		awaySelect.setHorizontalTextPosition(SwingConstants.LEFT);
		awaySelect.setForeground(Color.WHITE);
		homeSelect.setForeground(Color.WHITE);

		homeSelect.setOpaque(false);
		awaySelect.setOpaque(false);
		homeSelect.setFont(spinnerFont);
		awaySelect.setFont(spinnerFont);

		playGame.setBounds(300,480,200,30);

		homeValue.setVisible(true);
		awayValue.setVisible(true);
		homeSpinner.setVisible(true);
		awaySpinner.setVisible(true);
		homeSelect.setVisible(true);
		awaySelect.setVisible(true);
		playGame.setVisible(true);

		// If a tournament or season is being played, disable team select spinners
		if (inTournament || inSeason){
			awaySpinner.setEnabled(false);
			homeSpinner.setEnabled(false);
		}
		else{
			awaySpinner.setEnabled(true);
			homeSpinner.setEnabled(true);
		}


		// Add above containers to desktop pane
		main.add(homeValue);
		main.add(awayValue);
		main.add(homeSpinner);
		main.add(awaySpinner);
		main.add(homeSelect);
		main.add(awaySelect);
		main.add(playGame);



	}

	/**
	Takes image and scales it to the desired dimensions and returns as buffered image
	@param image - Image to be scaled
	@param width - x value to set to buffered image
	@param height - y value to set to buffered image
	@return BufferedImage - scaled version of paramater image
	*/
	public BufferedImage resize(Image image, int width, int height) { 

		// TYPE_INT_ARGB reflects the possibility of an AlphaComposite for the image
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); 

		// Create the graphics associated with the newly constructed buffered image
		Graphics2D g = resizedImage.createGraphics();

		// Set the alpha composite for the buffered image graphics
                g.setComposite(AlphaComposite.Src);

		// Draw the image in the designated area
		g.drawImage(image, 0, 0, width, height, null);

		// Clear the graphics element
		g.dispose();

		// Return the buffered image
		return resizedImage;
	}


	/**
	Controls the actions of the JSpinners for team selection
	@param e - ChangeEvent of JSpinners
	*/
	public void stateChanged(ChangeEvent e){
		if (e.getSource() == awaySpinner){
			// For scenarios, scale image to fix in label
			if (inScenario){
				int index = 0;

				// Set index for team currently displayed by JSpinner
				for (int i = 0; i < numberOfTeamsAvailable; i++){
					if (teams[i].equals(awaySpinner.getValue() ) )
						index = i;
				}

				BufferedImage resizedImage = resize(helmets[index],teamHelmets[index].getIconHeight() / 3,100);

				awayValue.setIcon(new ImageIcon(resizedImage));

			}
			else{
				int index = 0;

				// Set index for team currently displayed by JSpinner
				for (int i = 0; i < numberOfTeamsAvailable; i++){
					if (teams[i].equals(awaySpinner.getValue() ) )
						index = i;
				}

				// Set helmet label with proper image
				awayValue.setIcon(teamHelmets[index]);


			}

		}
		else if (e.getSource() == homeSpinner){
			// For scenarios, scale image to fix in label
			if (inScenario){
				int index = 0;

				// Set index for team currently displayed by JSpinner
				for (int i = 0; i < numberOfTeamsAvailable; i++){
					if (teams[i].equals(homeSpinner.getValue() ) )
						index = i;
				}

				BufferedImage resizedImage = resize(helmets[index],teamHelmets[index].getIconHeight() / 3,100);

				homeValue.setIcon(new ImageIcon(resizedImage));

			}
			else{
				int index = 0;

				// Set index for team currently displayed by JSpinner
				for (int i = 0; i < numberOfTeamsAvailable; i++){
					if (teams[i].equals(homeSpinner.getValue() ) )
						index = i;
				}

				// Set helmet label with proper image
				homeValue.setIcon(teamHelmets[index]);
			}
		}
	}


	/**
	Shows the current game selection and view home team's stadium and current weather conditions
	*/
	void previewGame(){
		tp.removeAll();

		tp.setLayout(null);

		// Allows user to return to team select screen
		option1.setText("Back");

		// Starts currently selected game
		option2.setText("Continue");

		option1.setBounds(200,460,150,30);
		option2.setBounds(450,460,150,30);


		tp.setBounds(200,50,400,250);

		Font f = new Font("Times New Roman", Font.BOLD, 16);

		DecimalFormat oneDForm = new DecimalFormat("#.#");

		// Set climate information for game
		climateInformation.removeAll();
		JLabel stadiumInfo = new JLabel(homeTeam.getStadium().replaceAll("_"," "));
		JLabel location = new JLabel(homeTeam.getLocation().replaceAll("_"," ") + ", " + homeTeam.getState()); 
		JLabel temperature = new JLabel("Temperature:    " + oneDForm.format(homeTeam.getTeamClimate().getCurrentTemperature()) + "° F");
		JLabel wind = new JLabel("Wind Speed:    " + oneDForm.format(homeTeam.getTeamClimate().getWindSpeed()) + " mph");
		JLabel weather = new JLabel("Current Weather:  " + homeTeam.getTeamClimate().getWeatherConditions());

		stadiumInfo.setForeground(Color.WHITE);
		location.setForeground(Color.WHITE);
		temperature.setForeground(Color.WHITE);
		wind.setForeground(Color.WHITE);
		weather.setForeground(Color.WHITE);

		stadiumInfo.setFont(f);
		location.setFont(f);
		temperature.setFont(f);
		wind.setFont(f);
		weather.setFont(f);

		stadiumInfo.setHorizontalAlignment(SwingConstants.CENTER);
		location.setHorizontalAlignment(SwingConstants.CENTER);
		temperature.setHorizontalAlignment(SwingConstants.CENTER);
		wind.setHorizontalAlignment(SwingConstants.CENTER);
		weather.setHorizontalAlignment(SwingConstants.CENTER);


		// Set home team's jersey image
		BufferedImage homeJerseyResized = resize(new ImageIcon(getClass.getResource("images/jerseys/" + homeTeam.getNickname() + ".png")).getImage(),200,355);

		// Set away team's jersey image
		BufferedImage awayJerseyResized = resize(new ImageIcon(getClass.getResource("images/jerseys/" + awayTeam.getNickname() + "Away.png")).getImage(),200,355);



		homeJersey.setIcon(new ImageIcon(homeJerseyResized));
		awayJersey.setIcon(new ImageIcon(awayJerseyResized));

		awayTeamLogo.setIcon(awayTeam.getLogo());
		homeTeamLogo.setIcon(homeTeam.getLogo());

		awayTeamLogo.setVerticalAlignment(SwingConstants.CENTER);
		homeTeamLogo.setVerticalAlignment(SwingConstants.CENTER);
		awayTeamLogo.setHorizontalAlignment(SwingConstants.CENTER);
		homeTeamLogo.setHorizontalAlignment(SwingConstants.CENTER);


		JLabel homeStadium = new JLabel(stadiums[homeSelection]);

		homeStadium.setBorder(new LineBorder(Color.WHITE));

		homeJersey.setBounds(600,70,200,355);
		awayJersey.setBounds(0,70,200,355);

		awayTeamLogo.setBounds(0,335,180,200);
		homeStadium.setBounds(0,0,400,250);
		homeTeamLogo.setBounds(600,335, 180,200);

		climateInformation.setBorder(new LineBorder(Color.WHITE, 2));
		climateInformation.setBounds(200,300,400,150);
		climateInformation.setLayout(new GridLayout(5,1,5,5));
		climateInformation.setOpaque(false);

		climateInformation.add(stadiumInfo);
		climateInformation.add(location);
		climateInformation.add(temperature);
		climateInformation.add(wind);
		climateInformation.add(weather);


		main.add(awayTeamLogo);
		tp.add(homeStadium);
		main.add(homeTeamLogo);
		main.add(homeJersey);
		main.add(awayJersey);

		tp.setVisible(true);
		option1.setVisible(true);
		option2.setVisible(true);
		awayTeamLogo.setVisible(true);
		homeTeamLogo.setVisible(true);
		climateInformation.setVisible(true);
		homeJersey.setVisible(true);
		awayJersey.setVisible(true);

		main.add(tp);
		main.add(option1);
		main.add(option2);
		main.add(climateInformation);

	}

	/**
	Controls all button actions performed during the span of the entire game
	@param e Action Event triggered by a button command
	*/
	public void actionPerformed(ActionEvent e){

		// Button to play a new game or start game based on selected attributes
		if (e.getSource() == playGame){
			// If text is set to "Play Game", display the Team Select screen
			if (playGame.getText().equals("Play Game")){
				inScenario = false;
				clearMainMenu();
			 	selectTeams();
			}
			// If text is set to "Start Game", clear Team Select screen, set teams, and start game
			else if (playGame.getText().equals("Start Game")){
				// Determine which team was selected to be the home team
				boolean homeChosen = false;
				boolean simulation = false;



				if (homeSelect.isSelected() && !awaySelect.isSelected())
					homeChosen = true;
				else if(awaySelect.isSelected() && !homeSelect.isSelected())
					homeChosen = false;
				else
					simulation = true;

				// If no team was selected by the user, the computer will simulate the game
				if (simulation){
					// Determine the indicies of the teams selected to show the sliver corresponding with each.
					for (int i = 0; i < numberOfTeamsAvailable; i++){

						// Set index of home team based on its name
						if (teams[i].equals(homeSpinner.getValue().toString() ))
							homeSelection = i;

						// Set index of away team based on its name
						else if (teams[i].equals(awaySpinner.getValue().toString()))
							awaySelection = i;
					}

					// Create simulation and play game
					CPUSimulation cpuSim = new CPUSimulation(homeSpinner.getValue().toString().replaceAll("\\s","_"), awaySpinner.getValue().toString().replaceAll("\\s","_"));

					// Obtain all information from simulation
					homeTeam = cpuSim.getHomeTeam();
					awayTeam = cpuSim.getAwayTeam();
					homeTeamRoster = cpuSim.getHomeTeamRoster();
					awayTeamRoster = cpuSim.getAwayTeamRoster();
					field = cpuSim.getField();
					drives = cpuSim.getDrives();

					// Show results of simulation
					showStatistics();
				}
				// If the user chooses a team to play as, load teams and preview game
				else{

					// Load team information to begin the game
					loadTeams(homeSpinner.getValue().toString().replaceAll("\\s","_"), awaySpinner.getValue().toString().replaceAll("\\s","_"), homeChosen);

					// Remove all Team Select attributes from view
					homeValue.setVisible(false);
					awayValue.setVisible(false);
					homeSpinner.setVisible(false);
					awaySpinner.setVisible(false);
					playGame.setVisible(false);
					homeSelect.setVisible(false);
					awaySelect.setVisible(false);
		
					main.removeAll();

					previewGame();
				}
			}
		}
		// Clear main menu and show scenario menu
		else if (e.getSource() == playScenario){
			clearMainMenu();
		 	createScenario();
		}
		// Clear main menu and show the tournament creation menu
		else if (e.getSource() == startTournament){
			clearMainMenu();

			teamListModel.clear();
			createTournament();
		}
        else if (e.getSource() == loadTournament){
        
        if (loadTournament.getText().equals("Load Tournament")){
        JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Tournament files", "tourn");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showOpenDialog(parent);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
        tournament = new Tournament();
        tournament.loadFile(chooser.getSelectedFile().getName());
        main.setVisible(false);
				main.removeAll();
        viewTournament();
        main.setVisible(true);
    }
  }
    else if (loadTournament.getText().equals("Save Tournament")){
      tournament.saveFile();
    }
        }
		// Clear main menu and show the season creation menu
		else if (e.getSource() == startSeason){
			clearMainMenu();

			inSeason = true;
	//		if (numberOfTeams.getItemCount() > 0)
	//			numberOfTeams.removeAllItems();
//			season = new Season(28);

//			viewSeason();
			createSeason();
		}
        else if (e.getSource() == loadSeason){
        if (loadSeason.getText().equals("Load Season")){
                JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Tournament files", "tourn");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showOpenDialog(parent);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
        season = new Season();
        season.loadFile(chooser.getSelectedFile().getName());
        main.setVisible(false);
				main.removeAll();
        viewSeason();
        main.setVisible(true);
    }
       }
    else if (loadTournament.getText().equals("Save Season")){
      season.saveFile();
    }  
        }
		// Button is displayed when selecting plays during gameplay and returning to menus after game is completed
		else if (e.getSource() == usePlay){
			// Returns user to main menu
			if (usePlay.getText() == "Menu"){
				main.setVisible(false);
				main.removeAll();
				homeTeamRoster.clear();
				awayTeamRoster.clear();
				drives.clear();
				playGame.setText("Play Game");
				playGame.setVisible(true);
				playScenario.setVisible(true);
				startSeason.setVisible(true);
				loadSeason.setVisible(true);
				startTournament.setVisible(true);
				loadTournament.setVisible(true);


				mainMenu();	
			}
			// Takes user to end of game statistics page
			else if (usePlay.getText() == "Stats")
				showStatistics();

			// Takes user to tournament main menu
			else if (usePlay.getText() == "To Tournament"){
				tournament.updateTournament(awayTeam.getName(), homeTeam.getName(), field.getAwayScore(), field.getHomeScore());
				main.setVisible(false);
				main.removeAll();
				homeTeamRoster.clear();
				awayTeamRoster.clear();
				drives.clear();
				main.setVisible(true);
				viewTournament();
			}

			// Takes user to season main menu
			else if (usePlay.getText() == "To Season"){
				season.updateSeason(awayTeam.getName(), homeTeam.getName(), field.getAwayScore(), field.getHomeScore(), awayTeam.getStats(), homeTeam.getStats());
				main.setVisible(false);
				main.removeAll();
				homeTeamRoster.clear();
				awayTeamRoster.clear();
				drives.clear();
				main.setVisible(true);
				viewSeason();
			}
			else
				// Determine the play selected by the player and create play for CPU
				determinePlayChoices();
		}
		
		// Stops clock and reduces time out amounts
		else if (e.getSource() == callTimeOut){
			int timeoutsLeft = 0;
			clockStopped = true;
			clockStoppedForCPU = true;

			if (homeTeam.isComputer())
				timeoutsLeft = field.getTimeouts(1);
			else
				timeoutsLeft = field.getTimeouts(2);

			gameTimer.stop();
			playClock.stop();

			if (homeTeam.isComputer())
				field.timeoutCalled(1);
			else
				field.timeoutCalled(2);

			setTimeouts();

			// If no time outs are left by the user, disable the button
			if (timeoutsLeft == 0)
				callTimeOut.setEnabled(false);
		}

		// Option 1 button is used for coin tosses, creating tournaments and menu navigation
		else if(e.getSource() == option1){
			// Returns user to team selection screen from game preview screen
			if (option1.getText() == "Back"){
				tp.setVisible(false);
				climateInformation.setVisible(false);
				awayJersey.setVisible(false);
				homeJersey.setVisible(false);
				option1.setVisible(false);
				option2.setVisible(false);
				awayTeamLogo.setVisible(false);
				homeTeamLogo.setVisible(false);

				main.remove(tp);
				main.remove(awayTeamLogo);
				main.remove(homeTeamLogo);
				main.remove(climateInformation);
				main.remove(awayJersey);
				main.remove(homeJersey);
				main.remove(option1);
				main.remove(option2);
				selectTeams();
			}
			// Creates tournament based on the values entered by the user in the tournament creation screen
			else if (option1.getText() == "Create Tournament"){
				Vector<String> selections = new Vector<String>();

				for (int i = 0; i < teamListModel.size(); i++){
					selections.addElement(teamListModel.elementAt(i).toString());
				}

				inTournament = true;

				tournament = new Tournament(teamListModel.size(), selections);

				main.setVisible(false);
				main.removeAll();
				main.setVisible(true);
				viewTournament();

			}
			else if (option1.getText() == "Create Season"){
				setSeason();
			}
			// If text is "Heads", player selected the coin toss and results will be gathered
			else if (option1.getText() == "Heads")
				// Determine the coin toss result based on the user's choice
				analyzeCoinToss(0);

			// If text is "Kickoff", player selected not to receive the ball to start game
			else if (option1.getText() == "Kickoff"){

				// If player is away team, give home team first offensive possession
				if( homeTeam.isComputer() ){
					// Output that away team is kicking off
					System.out.println(awayTeam.getName() + " has chosen to kickoff");
					// Set possession to kicking team
					field.setPossession(1);
					// Set indicator of which team received the opening kickoff (needed for 2nd half kickoff)
					receivedOpeningKickoff = 2;
				}
				// If player is home team, give away team first offensive possession
				else{
					// Output that the home team is kicking off
					System.out.println(homeTeam.getName() + " has chosen to kickoff");
					// Set possession to kicking team
					field.setPossession(2);
					// Set indicator of which team received the opening kickoff (needed for 2nd half kickoff)
					receivedOpeningKickoff = 1;
				}

				// For kicking off, team will technically be on offense and special teams
				// Before kickoffs are implemented, tags for defense will be set to true
				kickingOff = true;
				onOffense = true;
				onDefense = false;

				// Set scoreboard information to begin game
				setScoreboard();

				// Set kickoff attributes to start game
				setKickoff();
			}

		}

		// Option 2 button is used for coin tosses, menu navigation
		else if(e.getSource() == option2){
			// For both tournaments and season, this takes selected row from game table and opens team select screen
			if (option2.getText() == "Select Game"){
				main.setVisible(false);

				for (int i = 0; i < numberOfTeamsAvailable; i++){
					if (gameTable.getValueAt(gameTable.getSelectedRow(),1).equals(teams[i]))
						awaySelection = i;
					if (gameTable.getValueAt(gameTable.getSelectedRow(),2).equals(teams[i]))
						homeSelection = i;
				}
				main.removeAll();
				main.setVisible(true);
				selectTeams();
			}
			// Returns user to main menu from tournament main menu
			else if (option2.getText() == "Exit Tournament"){
				inTournament = false;

				main.setVisible(false);
				main.removeAll();
				homeTeamRoster.clear();
				awayTeamRoster.clear();
				drives.clear();
				playGame.setText("Play Game");
				playGame.setVisible(true);
				playScenario.setVisible(true);
				startSeason.setVisible(true);
				loadSeason.setVisible(true);
				startTournament.setVisible(true);
				loadTournament.setVisible(true);

				mainMenu();
			}
			// Returns user to main menu from season main menu
			else if (option2.getText() == "Exit Season"){
				inSeason = false;

				main.setVisible(false);
				main.removeAll();
				homeTeamRoster.clear();
				awayTeamRoster.clear();
				drives.clear();
				playGame.setText("Play Game");
				playGame.setVisible(true);
				playScenario.setVisible(true);
				startSeason.setVisible(true);
				loadSeason.setVisible(true);
				startTournament.setVisible(true);
				loadTournament.setVisible(true);

				mainMenu();
			}
			// Takes current game selected from preview screen and begins game
			else if (option2.getText() == "Continue"){
				tp.setVisible(false);
				climateInformation.setVisible(false);
				option1.setVisible(false);
				option2.setVisible(false);
				awayTeamLogo.setVisible(false);
				homeTeamLogo.setVisible(false);
				awayJersey.setVisible(false);
				homeJersey.setVisible(false);
				main.remove(tp);
				main.remove(awayTeamLogo);
				main.remove(homeTeamLogo);
				main.remove(climateInformation);
				main.remove(awayJersey);
				main.remove(homeJersey);
				main.remove(option1);
				main.remove(option2);
				startGame();
			}
			// If text is "Tails", player selected the coin toss and results will be gathered
			else if (option2.getText() == "Tails")
				// Determine the coin toss result based on the user's choice
				analyzeCoinToss(1);

			// If text is "Receive", player selected to receive the ball to start game
			else if (option2.getText() == "Receive"){

				// If player is away team, give away team first offensive possession
				if( homeTeam.isComputer() ){
					// Output that away team is receiving the opening kickoff
					System.out.println(awayTeam.getName() + " has chosen to receive the kickoff");
					// Set possession to kicking team (For now, it is the team receiving the ball)
					field.setPossession(2);

					// Set indicator of which team received the opening kickoff (needed for 2nd half kickoff)
					receivedOpeningKickoff = 1;
				}
				else{
					// Output that home team is receiving the opening kickoff
					System.out.println(homeTeam.getName() + " has chosen to receive the kickoff");
					// Set possession to kicking team
					field.setPossession(1);
					// Set indicator of which team received the opening kickoff (needed for 2nd half kickoff)
					receivedOpeningKickoff = 2;
				}


				// For receiving kickoffs, team will technically be on defense and special teams
				// Before kickoffs are implemented, tags for offense will be set to true
				kickingOff = true;
				onOffense = false;
				onDefense = true;


				// Set scoreboard information to begin game
				setScoreboard();

				// Set kickoff attributes to start game (before kickoffs are implemented, set the offense attributes)
				setKickoff();
			}
		}

		// For all playbook formation buttons
		else{
			for (int i = 0; i < 5; i++){
				if (e.getSource() == formations[i]){
					usePlay.setEnabled(true);
					Vector<String> playNames = new Vector<String>();


					playList.removeAllItems();

					// Go through all of the plays in the playbook and pull out all of the correct ones
					for (int j = 0; j < plays.size(); j++){
						if (onOffense && !kickingOff){
							if (plays.elementAt(j).getFormation().getFormationName().equals(formations[i].getText()))
								playNames.addElement(plays.elementAt(j).getName());

							if ((plays.elementAt(j).getName().equals("Field_Goal") || plays.elementAt(j).getName().equals("Punt")) && formations[i].getText().equals("Special Teams"))
								playNames.addElement(plays.elementAt(j).getName());
								
						}
						else if (onDefense && !kickingOff){

							if (plays.elementAt(j).getFormation().getFormationName().equals(formations[i].getText()))
								playNames.addElement(plays.elementAt(j).getName());

							if ((plays.elementAt(j).getName().equals("FG_Block") || plays.elementAt(j).getName().equals("Punt_Return")) && formations[i].getText().equals("Special Teams"))
								playNames.addElement(plays.elementAt(j).getName());

						}
						else{
							if (plays.elementAt(j).getName().equals(formations[i].getText())){
								playNames.addElement(plays.elementAt(j).getName());
							}
						}
					}

					for (int j = 0; j < playNames.size(); j++)
						playList.addItem(playNames.elementAt(j));

				}
			}
		}

	}


	/**
	Takes play chosen by user and pairs it with a computer selected play to initiate the current down
	*/
	void determinePlayChoices(){
		Play chosenPlay = new Play();

		// If kicking off, go straight to analyzing a kickoff
		if (kickingOff){
			analyzeKick("Kick");
		}
		else{

			// Go through the playbook and find the play that was selected by the user and set it to appropriate side
			for (int i = 0; i < plays.size(); i++){
				if (plays.elementAt(i).getName().equals(playList.getSelectedItem().toString()))
					if (onOffense){
						System.out.println(plays.elementAt(i).getName() + " " + plays.elementAt(i).getType());
						offensivePlay = plays.elementAt(i);
						chosenPlay = offensivePlay;
					}
					else if (onDefense){
						defensivePlay = plays.elementAt(i);
						chosenPlay = defensivePlay;
					}
			}
		

			// Chooses the roster for the OFFENSIVE team
			if (onOffense){
				if (homeTeam.isComputer()){
					roster = awayTeamRoster;
					offensiveTeam = awayTeam;
					defensiveTeam = homeTeam;
				}
				else{
					roster = homeTeamRoster;
					offensiveTeam = homeTeam;
					defensiveTeam = awayTeam;
				}
			}
			else{
				if (homeTeam.isComputer()){

					roster = homeTeamRoster;
					offensiveTeam = homeTeam;
					defensiveTeam = awayTeam;
				}
				else{
					roster = awayTeamRoster;
					offensiveTeam = awayTeam;
					defensiveTeam = homeTeam;
				}
			}

			

			if (onOffense){		// Select player if human is running offense

				if (chosenPlay.getSideOfBall().equals("S")){
					if (chosenPlay.getName().equals("Punt")){
						analyzeKick("Punt");
						return;
					}
					else if (chosenPlay.getName().equals("Field_Goal"))
						for (int i = 0; i < roster.size(); i++){
							if (roster.elementAt(i).getPosition().equals("K")){
								primaryPlayer = roster.elementAt(i);
								kicker = primaryPlayer;
								break;
							}
						}

				}	// end special teams


				// If it is a run play, choose the primary RB
				else if (chosenPlay.getType().equals("R")){
					for (int i = 0; i < 3; i++){
						if (playerSelect[i].isSelected())
							for (int j = 0; j < roster.size(); j++){

								if (roster.elementAt(j).getName().equals(playerSelect[i].getText() )){
									primaryPlayer = roster.elementAt(j);
									System.out.println("Player: " + primaryPlayer.getName());
								}
							}


					}

				}
				// For pass plays, retrieve the player selected from the radio buttons in play select
				else if (chosenPlay.getType().equals("P") ){
					for (int i = 0; i < 3; i++){
						if (playerSelect[i].isSelected())
							for (int j = 0; j < roster.size(); j++){

								if (roster.elementAt(j).getName().equals(playerSelect[i].getText() )){
									primaryPlayer = roster.elementAt(j);
									System.out.println("Player: " + primaryPlayer.getName());
								}
							}


					}

				}

			}	// end if on offense


			int cpuScore = 0;
			int playerScore = 0;
			if (homeTeam.isComputer()){
				cpuScore = field.getHomeScore();
				playerScore = field.getAwayScore();
			}
			else{
				cpuScore = field.getAwayScore();
				playerScore = field.getHomeScore();
			}

			// Need to create a Play AND offensive player if Computer has the ball.
			cpu.analyzeField(field.getDown(), field.getYardsToGo(), field.getYardline(), field.getTimeLeft(), field.getQuarter(), cpuScore, playerScore, onOffense, onSpecialTeams, false);

			// This is only set IF the CPU is on defense.  The above function will not set a defensive play if not on defense
			defensivePlay = cpu.getDefensivePlay();

			// If user is on defense, set CPU's offensive play
			if (!onOffense){

				// Get the play
				offensivePlay = cpu.getOffensivePlay();

				// Get the player position
				String playerPosition = cpu.getIntendedReceiver();

				// Decide on the specific player to choose for offense
				if (offensivePlay.getSideOfBall().equals("O")){
					if (offensivePlay.getType().equals("R")){
						for (int i = 0; i < roster.size(); i++){
							if (roster.elementAt(i).getPosition().equals(playerPosition)){
								primaryPlayer = roster.elementAt(i);
								System.out.println("Player: " + primaryPlayer.getName());
								}
						}
					}
					// For pass plays, choose a random wide receiver

					else if (offensivePlay.getType().equals("P") ){

						int position = (int)(Math.random() * 2 + 1);
						int location = 1;

						System.out.println("Pass: " + playerPosition + "   " + position + "   " + location);

						for (int i = 0; i < roster.size(); i++){


							if (roster.elementAt(i).getPosition().equals(playerPosition)){
							 	if (location == position){
									primaryPlayer = roster.elementAt(i);
									break;

								}
								else
									location++;
							}
						}
					}
				}

				// For special teams, analyze the kick for punting or choose kicker for field goals
				else if (offensivePlay.getSideOfBall().equals("S")){
					if (offensivePlay.getName().equals("Punt")){
						analyzeKick("Punt");
						return;
					}
					else if (offensivePlay.getName().equals("Field_Goal") ) 
						for (int i = 0; i < roster.size(); i++){
							if (roster.elementAt(i).getPosition().equals("K")){
								primaryPlayer = roster.elementAt(i);
								kicker = primaryPlayer;
								break;
							}
						}

				}

			} // end computer information
			playList.removeAll();

			// Analyze play
			analyzePlay();
		}
	}

	/**
	Initializes field values and performs coin toss
	*/
	void startGame(){
		field.initializeField();
		performCoinToss();
		usePlay.setText("Go");
	}

	/**
	Analyzes computer's choice for coin toss if CPU is away team or gives Heads/Tails options to user
	*/
	void performCoinToss(){
		boolean winCoinToss = false;
		int cpuSelect = 0;
		int coinToss = 0;

		if(awayTeam.isComputer()){
			cpuSelect = (int)(Math.random() * 100);
		
			if (cpuSelect % 2 == 0)
				System.out.println(awayTeam.getName() + " has chosen HEADS");
			else
				System.out.println(awayTeam.getName() + " has chosen TAILS");

			coinToss = (int)(Math.random() * 100);
			coinToss = coinToss % 2;

			if (coinToss == 0)
				System.out.println("The coin lands HEADS");
			else
				System.out.println("The coin lands TAILS");


			if (cpuSelect % 2 == coinToss){		// Computer selects the coin toss.
				System.out.println(awayTeam.getName() + " won the coin toss");
				winCoinToss = true;

			}
			else
				System.out.println(homeTeam.getName() + " won the coin toss");


			// CPU won the coin toss
			if (winCoinToss){
				// User kicks off
				if (cpuSelect % 2 == 0){

					System.out.println(awayTeam.getName() + " has chosen to receive the opening kickoff");
					kickingOff = true;
					onDefense = false;
					onOffense = true;

					setKickoff();

					field.setPossession(2);
					receivedOpeningKickoff = 1;
				}
				// CPU kicks off
				else{
					System.out.println(awayTeam.getName() + " has chosen to kickoff");
					kickingOff = true;
					onOffense = false;
					onDefense = true;

					setKickoff();

					field.setPossession(1);
					receivedOpeningKickoff = 2;


				}

				setScoreboard();

			}
			// If CPU loses coin toss set options for user to Kick or Receive
			else{
				option1.setBounds(200,200,150,50);
				option2.setBounds(400,200,150,50);

				option1.setText("Kickoff");
				option2.setText("Receive");

				option1.setVisible(true);
				option2.setVisible(true);

				main.add(option1);
				main.add(option2);
			}
		} // end CPU coin toss

		// If user is away team, set options for Heads or Tails
		else{
			option1.setBounds(200,200,150,50);
			option2.setBounds(400,200,150,50);

			option1.setText("Heads");
			option2.setText("Tails");

			option1.setVisible(true);
			option2.setVisible(true);

			main.add(option1);
			main.add(option2);
			
		}
	}

	/**
	Analyzes player's selection for coin toss
	@param choice - User's selection of Heads or Tails
	*/
	void analyzeCoinToss(int choice){
		int coinToss = (int)(Math.random() * 100);
		boolean wonCoinToss = false;

		if (coinToss % 2 == choice){
			System.out.println(awayTeam.getName() + " has won the coin toss.");
			wonCoinToss = true;
		}

		else{
			System.out.println(homeTeam.getName() + " has won the coin toss.");
		}

		// If user wins coin toss, give options to kick or receive
		if (wonCoinToss){
			option1.setText("Kickoff");
			option2.setText("Receive");
		}

		// User loses the coin toss
		else{
			option1.setVisible(false);
			option2.setVisible(false);
			setScoreboard();


			int cpuSelect = (int)(Math.random() * 100);

			// CPU decides to return kickoff
			if (cpuSelect % 3 == 0){

				System.out.println(homeTeam.getName() + " has chosen to receive the opening kickoff");
				kickingOff = true;
				onDefense = false;
				onOffense = true;

				setKickoff();

				field.setPossession(1);
				receivedOpeningKickoff = 2;
			}
			// CPU decides to kickoff
			else{
				System.out.println(homeTeam.getName() + " has chosen to kickoff");
				kickingOff = true;
				onOffense = false;
				onDefense = true;

				setKickoff();

				field.setPossession(2);
				receivedOpeningKickoff = 1;
			}

		}
	}


	/**
	Sets locations and images for in-game scoreboard display
	*/
	void setScoreboard(){
		option1.setVisible(false);
		option2.setVisible(false);

		JPanel scoreboard = new JPanel();

		// Holds sliver for away team
		TranslucentPanel awayPanelLeft = new TranslucentPanel(0.60f);

		// Holds away team score
		JPanel awayPanelRight = new JPanel();

		// Holds home team score
		JPanel homePanelLeft = new JPanel();

		// Holds sliver for home team
		TranslucentPanel homePanelRight = new TranslucentPanel(0.60f);

		scoreboard.setBounds(0,0,800,100);
		main.add(scoreboard);

		scoreboard.setLayout(null);

		awayPanelLeft.setLayout(null);
		awayPanelRight.setLayout(null);
		homePanelLeft.setLayout(null);
		homePanelRight.setLayout(null);

		awayName.setBounds(30,7,155,30);
		possessionLabels[0].setBounds(30,37,155,5);

		awayScores[0].setBounds(3,10,20,25);
		awayScores[1].setBounds(27,10,20,25);

		awayPanelLeft.add(awayName);
		awayPanelRight.add(awayScores[0]);
		awayPanelRight.add(awayScores[1]);
		awayPanelLeft.add(possessionLabels[0]);

		awayPanelLeft.setBounds(0,0,300,50);
		awayPanelRight.setBounds(300,0,50,50);

		awayPanelLeft.setBorder(new LineBorder(Color.GRAY));
		awayPanelRight.setBorder(new LineBorder(Color.GRAY));

		possessionLabels[0].setBackground(null);
		awayPanelLeft.setBackground(awayTeam.getPrimaryColor());
		awayPanelRight.setBackground(awayTeam.getSecondaryColor());

		awayName.setIcon(teamSlivers[awaySelection]);
		awayName.setBorder(BorderFactory.createLoweredBevelBorder());

		scoreboard.add(awayPanelLeft);
		scoreboard.add(awayPanelRight);

		homePanelLeft.setLayout(null);
		homePanelRight.setLayout(null);

		homeName.setBounds(110,7,155,30);
		homeScores[0].setBounds(3,10,20,25);
		homeScores[1].setBounds(27,10,20,25);
		possessionLabels[1].setBounds(110,37,155,5);

		homePanelRight.add(homeName);
		homePanelLeft.add(homeScores[0]);
		homePanelLeft.add(homeScores[1]);
		homePanelRight.add(possessionLabels[1]);

		homePanelRight.setBounds(500,0,300,50);
		homePanelLeft.setBounds(450,0,50,50);

		homePanelRight.setBorder(new LineBorder(Color.GRAY));
		homePanelLeft.setBorder(new LineBorder(Color.GRAY));

		possessionLabels[1].setBackground(null);
		homePanelRight.setBackground(homeTeam.getPrimaryColor());
		homePanelLeft.setBackground(homeTeam.getSecondaryColor());

		homeName.setIcon(teamSlivers[homeSelection]);
		homeName.setBorder(BorderFactory.createLoweredBevelBorder());

		scoreboard.add(homePanelLeft);
		scoreboard.add(homePanelRight);

		awayTimeouts[0].setBounds(55,60,25,25);
		awayTimeouts[1].setBounds(90,60,25,25);
		awayTimeouts[2].setBounds(125,60,25,25);

		for (int i = 0; i < 3; i++)
			scoreboard.add(awayTimeouts[i]);


		homeTimeouts[0].setBounds(650,60,25,25);
		homeTimeouts[1].setBounds(685,60,25,25);
		homeTimeouts[2].setBounds(720,60,25,25);

		for (int i = 0; i < 3; i++)
			scoreboard.add(homeTimeouts[i]);


		JLabel quarterLabel = new JLabel("Quarter");
		quarterLabel.setForeground(Color.WHITE);

		quarterLabel.setBounds(370,40,80,13);
		qtrNum.setBounds(390,10,20,25);

		JLabel andLabel = new JLabel("and");
		andLabel.setForeground(Color.WHITE);

		downNum.setBounds(250,60,20,25);
		andLabel.setBounds(275,60,30,25);
		yardsToGo[0].setBounds(310,60,20,25);
		yardsToGo[1].setBounds(330,60,20,25);

		yardline[0].setBounds(370,60,20,25);
		yardline[1].setBounds(390,60,20,25);
		fieldDirection.setBounds(410,60,25,25);

		gameClock[0].setBounds(450,60,20,25);
		gameClock[1].setBounds(470,60,20,25);

		gameClock[2].setBounds(510,60,20,25);
		gameClock[3].setBounds(530,60,20,25);


		scoreboard.add(quarterLabel);
		scoreboard.add(qtrNum);

		scoreboard.add(downNum);
		scoreboard.add(andLabel);
		scoreboard.add(fieldDirection);

		for (int i = 0; i < 2; i++){
			scoreboard.add(yardline[i]);
			scoreboard.add(yardsToGo[i]);
		}

		for (int i = 0; i < 4; i++){
			scoreboard.add(gameClock[i]);

		}

		scoreboard.setBackground(new Color(22,22,22));
		scoreboard.setBorder(BorderFactory.createRaisedBevelBorder());

		updateScoreboard();
		setPlaybook();
		setTimeouts();
	}

	/**
	Applies appropriate images for time out labels.  A lighted block will appear
	if that specific time out is available, an empty block otherwise
	*/
	void setTimeouts(){

		// Away team
		for (int i = 0; i < field.getTimeouts(1); i++)
			awayTimeouts[i].setIcon(new ImageIcon(getClass.getResource("images/system/timeOut.png")));

		for (int i = field.getTimeouts(1); i < 3; i++)
			awayTimeouts[i].setIcon(new ImageIcon(getClass.getResource("images/system/empty.png")));

		for (int i = 0; i < field.getTimeouts(2); i++)
			homeTimeouts[i].setIcon(new ImageIcon(getClass.getResource("images/system/timeOut.png")));

		for (int i = field.getTimeouts(2); i < 3; i++)
			homeTimeouts[i].setIcon(new ImageIcon(getClass.getResource("images/system/empty.png")));

	}

	/**
	Set images, buttons and lists for in-game playbook panel.  This panel includes buttons
	for formations and calling timeout, lists for available plays and play clock
	*/
	void setPlaybook(){
		JPanel playbook = new JPanel();
		final JLabel playImage = new JLabel();

		playbook.setBounds(0,100,800,450);
		playbook.setOpaque(false);
		main.add(playbook);
		playbook.setLayout(null);

		// Line up formation buttons just below where currently selected play will be shown
		for (int i = 0; i < 5; i++){
			formations[i].setBounds(225 + ((i%2)*140) ,355 + (20 * (i/2)), 140, 20);
			playbook.add(formations[i]);
		}

		playDisplay.setBounds(225,75,280,280);
		playDisplay.setOpaque(false);
		playDisplay.setLayout(null);
		

		playList.setBounds(10,10,250,30);
		playDisplay.add(playList);

		playImage.setBounds(10,45,155,100);
		playDisplay.add(playImage);

		// Place player select radio buttons vertically to the right of the play display panel
		for (int i = 0; i < 3; i++){
			playerSelect[i].setBounds(170,45 + (i * 25), 110,20);
			playerSelect[i].setVisible(false);
			playDisplay.add(playerSelect[i]);
		}

		// Add item listener to the play list so that, when a play is selected, the eligible players
		// to select to use will become available for selection
		playList.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){

				// Set up the selection as long as there are players to add
				if (playList.getItemCount() != 0){

					// Find the correct play to retrieve its attributes
					for (int i = 0; i < plays.size(); i++){
						if (plays.elementAt(i).getName().equals(playList.getSelectedItem().toString()) ){

							// Hide all radio buttons for now
							for (int j = 0; j < 3; j++){
								playerSelect[j].setVisible(false);
								playerSelect[j].setText("");
							}

							// For each player to be added...
							for (int j = 0; j < plays.elementAt(i).getReceivers(); j++){

								// Unhide the radio button
								playerSelect[j].setVisible(true);

								Vector<Player> roster;

								// Chooses the roster for the OFFENSIVE team
								if (onOffense){
									if (homeTeam.isComputer())
										roster = awayTeamRoster;
									
									else
										roster = homeTeamRoster;									
								}
								else{
									if (homeTeam.isComputer())
										roster = homeTeamRoster;
									else
										roster = awayTeamRoster;
								}


								for (int k = 0; k < roster.size(); k++){

									// If the player's position matches that of the position needed...
									if (roster.elementAt(k).getPosition().equals(plays.elementAt(i).getPlayer(j+1))){

										//System.out.println(roster.elementAt(k).getName());

										boolean taken = false;

										// If the player has already been taken, ignore it and move on
										for (int l = 0; l < 3; l++)
											if (playerSelect[l].getText().equals(roster.elementAt(k).getName()) && l != j)
												taken = true;

										// If the player has not yet been taken and the current selection is not occupied, add it to the list
										if (!taken && playerSelect[j].getText().length() == 0)
											playerSelect[j].setText(roster.elementAt(k).getName());
									}
								}

							}

							if (plays.elementAt(i).getReceivers() > 0)
								playerSelect[0].setSelected(true);

//							playImage.setIcon(plays.elementAt(i).getFormation().getFormationImage());
						}

					}
				}
			}
		});

		usePlay.setBounds(220,250,60,30);
		playDisplay.add(usePlay);

		callTimeOut.setBounds(35,420,100,25);
		callTimeOut.setVerticalAlignment(SwingConstants.TOP);

		JPanel playClock = new JPanel();

		playClock.setBounds(25,385,150,30);

		playClock.setBackground(Color.BLACK);


		playClock.setLayout(null);
		JLabel playClockLabel = new JLabel("Play Clock");
		playClockLabel.setBounds(0,0,100,30);
		playClockLabel.setForeground(Color.WHITE);
		playClockAmount.setBounds(100,00,30,30);
		playClockAmount.setForeground(Color.WHITE);

		playClock.add(playClockLabel);
		playClock.add(playClockAmount);

		playbook.add(playDisplay);
		playbook.add(callTimeOut);
		playbook.add(playClock);

		main.add(background);

		// Set the playbook background image to the appropriate team background
		background.setImage(backgrounds[playerSelection]);
	}


	/**
	Sets the values of formation buttons to reflect options associated
	with a kickoff
	*/
	void setKickoff(){
		for (int i = 2; i < 5; i++)
			formations[i].setVisible(false);

		for (int i = 0; i < 3; i++)
			playerSelect[i].setVisible(false);

		Vector<String> playNames = new Vector<String>();

		playList.removeAllItems();

		// Message to display when the play list is empty
		String message = "Choose a kickoff play";

		playList.addItem(message);

		usePlay.setEnabled(false);

		if (onOffense){
			formations[0].setText("Kickoff");
			formations[1].setText("Onside_Kick");
			onSpecialTeams = true;

		}
		else{
			formations[0].setText("Kickoff_Return");
			formations[1].setText("Onside_Recover");
			onSpecialTeams = true;
		}
	}

	/**
	Sets the values of formation buttons to reflect options associated with the offense
	*/
	void setOffense(){
		for (int i = 0; i < 5; i++)
			formations[i].setVisible(true);

		for (int i = 0; i < 3; i++)
			playerSelect[i].setVisible(false);

		formations[0].setText("I-Formation");
		formations[1].setText("Splitback");
		formations[2].setText("Shotgun");
		formations[3].setText("GoalLine");
		formations[4].setText("Special Teams");

		playList.removeAllItems();

		String message = "Choose an offensive play";

		playList.addItem(message);

		usePlay.setEnabled(false);
	}

	/**
	Sets the values of formation buttons to reflect options associated with the defense
	*/
	void setDefense(){
		for (int i = 0; i < 5; i++)
			formations[i].setVisible(true);

		for (int i = 0; i < 3; i++)
			playerSelect[i].setVisible(false);

		formations[0].setText("4-3");
		formations[1].setText("Dime");
		formations[2].setText("Quarter");
		formations[3].setText("Goal_Line");
		formations[4].setText("Special Teams");

		playList.removeAllItems();

		String message = "Choose an defensive play";

		playList.addItem(message);

		usePlay.setEnabled(false);
	}


	/**
	Takes the plays selected by both offense and defense, as well as the type of pass attempt selected,
	if applicable, and creates an advantage for either offense or defense based on those attributes.
	Factors such as climate and weather are taken into account as well.
	@param defPlay - Defensive play
	@param offPlay - Offensive play
	@param passDist - String representation of pass attempt ("long", "mid", "short", "screen")
	@return int - Overall advantage by offense or defense (+ for defense, - for offense)
	*/
	int calculateAdvantage(Play defPlay, Play offPlay, String passDist){

		// Determine the advantage based on climate conditions (elevation, temperature differences)
		int advantage = calculateClimateAdvantage();

		// Advantage automatically for home team (On defense and home team or On offense and away team)
		if ( (onDefense && awayTeam.isComputer()) || (onOffense && homeTeam.isComputer()))
			advantage += 5;
		else
			advantage -= 5;


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

		// If the offense is attempting a 2-Point conversion, advantage defense
		if (offPlay.getSideOfBall().equals("O") && onSpecialTeams){
			advantage += 40;
		}

		System.out.println("Advantage: " + advantage);

		return advantage;
	}


	/**
	Gathers climate information from home and away team and gives a numerical value to show the advantage
	the home team has for simply playing in a different climate than the away team
	@return int - Climate and weather advantage for defense
	*/
	int calculateClimateAdvantage(){
		int climateAdvantage = 0;

		// If the home stadium is domed, no weather or climate advantage is available
		if (homeTeam.isDomed())
			return 0;

		// Obtain the current weather conditions for the game
		String weather = homeTeam.getTeamClimate().getWeatherConditions();

		// Determine the defensive advantage for the current weather conditions
		climateAdvantage = determineWeatherAdvantage(weather);

		// Player on offense, is home team OR player on defense, is away team (reduce advantage for defense)
		if ( (onOffense && awayTeam.isComputer()) || (onDefense && homeTeam.isComputer())  ){
			double tempDiff = homeTeam.getTeamClimate().getCurrentTemperature() - awayTeam.getTeamClimate().getCurrentTemperature();
			int elevationDiff = homeTeam.getTeamClimate().getElevation() - awayTeam.getTeamClimate().getElevation();

			if (Math.abs(tempDiff) >= 30.0)
				climateAdvantage -= (int)Math.abs(tempDiff) - 20;

			if (Math.abs(elevationDiff) >= 2500)
				climateAdvantage -= (10 + ((Math.abs(elevationDiff) - 2500) / 400));

		}

		// Player on defense, is home team OR player on offense, is away team (raise advantage for defense)
		else if ((onDefense && awayTeam.isComputer()) || (onOffense && homeTeam.isComputer()) ){
			double tempDiff = homeTeam.getTeamClimate().getCurrentTemperature() - awayTeam.getTeamClimate().getCurrentTemperature();
			int elevationDiff = homeTeam.getTeamClimate().getElevation() - awayTeam.getTeamClimate().getElevation();

			if (Math.abs(tempDiff) >= 30.0)
				climateAdvantage += (int)Math.abs(tempDiff) - 20;

			if (Math.abs(elevationDiff) >= 2500)
				climateAdvantage += 10 + ((Math.abs(elevationDiff) - 2500) / 400);
		}
		
		return climateAdvantage;
	}

	/**
	Takes current weather conditions for the game and determines the advantage for the home team
	@param weather - Current game weather conditions
	@return int - Home team advantage for weather conditions
	*/
	int determineWeatherAdvantage(String weather){
		boolean isHomeTeam = awayTeam.isComputer();
		int weatherAdvantage = 0;

		// For moderate weather precipitation
		if (weather.equals("Light Snow") || weather.equals("Snow Showers") || weather.equals("Showers") || weather.equals("Sleet") || weather.equals("Freezing Rain"))
			// If player is home team/on defense or player is away team/on offense
			if ( (isHomeTeam && onDefense) || (!isHomeTeam && onOffense) ) 
					weatherAdvantage += 5;
			else
					weatherAdvantage -= 5;

		// For heavy to severe weather
		if (weather.equals("Heavy Snow") || weather.equals("Heavy Rain") || weather.equals("Thunderstorms"))
			// If player is home team/on defense or player is away team/on offense
			if ( (isHomeTeam && onDefense) || (!isHomeTeam && onOffense) ) 
					weatherAdvantage += 10;
			else
					weatherAdvantage -= 10;

		// For light weather precipitation
		if (weather.equals("Light Rain") || weather.equals("Wintry Mix"))
			// If player is home team/on defense or player is away team/on offense
			if ( (isHomeTeam && onDefense) || (!isHomeTeam && onOffense) ) 
					weatherAdvantage += 3;
			else
					weatherAdvantage -= 3;

		return weatherAdvantage;
	}


	/**
	Gathers information from both offensive and defensive plays and accesses
	the PlayAnalysis class to determine the appropriate outcome of the current events
	*/
	void analyzePlay(){
		// Retrieve the play type of the offense
		String playType = offensivePlay.getType();
		boolean isSpecialTeams = false;


		// If a pass play is selected, randomly decide the type of pass play being used
		String[] passValues = {"screen", "short", "mid", "long"};
		if (playType.equals("P")){
			int val = (int)(Math.random() * 4);
			playType = passValues[val];
		//	System.out.println(playType);
		}

		// If there is no play type, then the play is part of special teams
		else if (playType.equals("")){
			playType = offensivePlay.getName();
			isSpecialTeams = true;
		}


		// Determine the advantage given to the home team (Positive = Defense holds advantage, Negative = Offense)
		int advantage = calculateAdvantage(defensivePlay, offensivePlay, playType);


		if (onOffense){
			PlayAnalysis analysis;

			// If computer is away team, order rosters as such.  Otherwise, switch them
			if (awayTeam.isComputer())
				analysis = new PlayAnalysis(primaryPlayer, homeTeamRoster, awayTeamRoster, playType, advantage);
			else
				analysis = new PlayAnalysis(primaryPlayer, awayTeamRoster, homeTeamRoster, playType, advantage);

			// This is where the play actually occurs
			analysis.performPlay();

			// Continue as usual if this is not a special teams play
			if (!isSpecialTeams)

				displayResults(analysis);

			// If it is special teams...
			else{
				// Else, check for the field goal attempt's accuracy and continue
				if(playType.equals("Field_Goal") ){
					// Get the distance of the kick
					kickDistance = analysis.getKickDistance();
					kickAccuracy = analysis.getKickAccuracy();
					displayResults(analysis);
				}
			}
		}			
		else if (onDefense){
			PlayAnalysis analysis;

			// If computer is away team, order rosters as such.  Otherwise, switch them
			if (awayTeam.isComputer())
				analysis = new PlayAnalysis(primaryPlayer, awayTeamRoster, homeTeamRoster, playType, advantage);
			else
				analysis = new PlayAnalysis(primaryPlayer, homeTeamRoster, awayTeamRoster, playType, advantage);

			// Perform the play
			analysis.performPlay();

			if (!isSpecialTeams)
				displayResults(analysis);
			else{

				if(playType.equals("Field_Goal")){
					// Get the distance of the kick
					kickDistance = analysis.getKickDistance();
					kickAccuracy = analysis.getKickAccuracy();
					displayResults(analysis);
				}
			}
		}
	}


	/**
	Takes the type of kick and determines the distance kicked as well as analyzes the return, if applicable.
	NOTE: For these kicks, it is assumed that the kicking team is the OFFENSIVE team.
	@param type - Type of kick (Standard Kickoff, Onside Kick, or Punt)
	*/
	void analyzeKick(String type){
		Vector<Player> offensiveRoster;
		Vector<Player> defensiveRoster;

	//	System.out.println("On offense tag is: " + onOffense);

		// For kickoffs
		if (type.equals("Kick")){
			if (onOffense){
				if (homeTeam.isComputer()){
					offensiveRoster = awayTeamRoster;
					defensiveRoster = homeTeamRoster;
					offensiveTeam = awayTeam;
					defensiveTeam = homeTeam;
				}
				else{
					offensiveRoster = homeTeamRoster;
					defensiveRoster = awayTeamRoster;
					offensiveTeam = homeTeam;
					defensiveTeam = awayTeam;
				}
			}
			else{
				if (homeTeam.isComputer()){

					offensiveRoster = homeTeamRoster;
					defensiveRoster = awayTeamRoster;
					offensiveTeam = homeTeam;
					defensiveTeam = awayTeam;
				}
				else{
					offensiveRoster = awayTeamRoster;
					defensiveRoster = homeTeamRoster;
					offensiveTeam = awayTeam;
					defensiveTeam = homeTeam;
				}
			}


			// Obtain the kicker used on the play
			for (int i = 0; i < offensiveRoster.size(); i++){
				if (offensiveRoster.elementAt(i).getPosition().equals("K")){
					kicker = offensiveRoster.elementAt(i);
					break;
				}
			}

			boolean isOnsideKick = false;

			// If player is on offense and chose to kick onside, trigger tag
			if (onOffense){
				if (playList.getSelectedItem().toString().equals("Onside_Kick")){
					isOnsideKick = true;
				//	System.out.println("Test for onside kick");
				}
			}
			// If computer is kicking, it must decide how it wants to kickoff
			else{
				//System.out.println("Computer deciding kickoff");

				int cpuScore = 0;
				int playerScore = 0;

				if (homeTeam.isComputer()){
					cpuScore = field.getHomeScore();
					playerScore = field.getAwayScore();
				}
				else{
					cpuScore = field.getAwayScore();
					playerScore = field.getHomeScore();
				}


				// Need to create a Play AND offensive player if Computer has the ball.
				cpu.analyzeField(field.getDown(), field.getYardsToGo(), field.getYardline(), field.getTimeLeft(), field.getQuarter(), cpuScore, 
					playerScore, onOffense, onSpecialTeams, true);

				// Retrieve the CPU's decision and judge accordingly
				if (cpu.selectKickoffStyle().equals("Kickoff"))
					isOnsideKick = false;
				else
					isOnsideKick = true;
			}

			// If the kick is onside
			if (isOnsideKick){
				//System.out.println("Onside Kick!");

				// Analyze and perform play
				PlayAnalysis analysis = new PlayAnalysis(kicker, offensiveRoster, defensiveRoster, "Onside", 0);
				analysis.performPlay();

				// Obtain the kick distance and who recovered the kick
				kickDistance = analysis.getKickDistance();
				int kickRecovery = analysis.determineKickRecovery();

				System.out.println("Kick Distance: " + kickDistance + "  Recovered by : " + kickRecovery);

				boolean kickingTeamRecovered = false;

				// If the kick was recovered by the kicking team and the ball travelled 10 yards or more...
				if (kickRecovery == 1 && kickDistance >= 10)
					kickingTeamRecovered = true;


				// Set the new yardline to where the ball was recovered
				field.setYardline(field.getYardline() + kickDistance);

				// Determine the time elapsed for the kick
				int timeElapsed = (int)(Math.random() * 3 + 2);


				// If time ran out during the kick, let time elapsed reflect this
				if (field.getTimeLeft() < timeElapsed){
					System.out.println("Play duration: " + field.getTimeLeft() + " seconds");

					if (kickingTeamRecovered)
						offensiveTeam.getStats().addTimeOfPossession(field.getTimeLeft());
					else
						defensiveTeam.getStats().addTimeOfPossession(field.getTimeLeft());
					field.reduceTime(field.getTimeLeft(), false);
				}
				// Otherwise, reduce time and set statistics
				else{
					System.out.println("Play duration: " + timeElapsed + " seconds");

					if (kickingTeamRecovered)
						offensiveTeam.getStats().addTimeOfPossession(timeElapsed);
					else
						defensiveTeam.getStats().addTimeOfPossession(timeElapsed);
					field.reduceTime(timeElapsed, false);
				}

				// Create message to display at end of play
				String recoveryMessage = kicker.getName() + " attempts onside kick for " + kickDistance + " yards.\n";

				// If kicking team recovered, update field and scoreboard
				if (kickingTeamRecovered){
					recoveryMessage += "Kicking team recovers!";
					field.setDown(1);
					field.setYardsToGo(10);
					updateScoreboard();
					kickingOff = false;
				}
				// Otherwise, flip the field and let possession start
				else{
					recoveryMessage += "Return team recovers!";
					field.setDown(1);
					field.setYardsToGo(10);
					field.setYardline(100 - field.getYardline());
					changePossession();
					updateScoreboard();
					kickingOff = false;
				}

				// Display the recovery message
				JOptionPane.showMessageDialog(null, recoveryMessage, "Onside Kick", JOptionPane.PLAIN_MESSAGE);

			}
			else{

				// Standard Kickoff
				PlayAnalysis analysis = new PlayAnalysis(kicker, offensiveRoster, defensiveRoster, "Kickoff", 0);
				analysis.performPlay();

				// Get the kick distance
				kickDistance = analysis.getKickDistance();

				System.out.println("Kick Distance: " + kickDistance);

				// Update field to reflect kick distance
				field.setYardline(field.getYardline() + kickDistance);

				// If a touchback occurs
				if (field.getYardline() >= 100){
					field.setYardline(80);
					displayKickResults(true, false, false);
				}

				// Returning the kickoff
				else{
					// Let a cornerback return the kick
					for (int i = 0; i < defensiveRoster.size(); i++){
						if (defensiveRoster.elementAt(i).getPosition().equals("CB")){
							kickReturner = defensiveRoster.elementAt(i);
							break;
						}
					}

					// Perform play as if it were a simple run play
					PlayAnalysis analysis2 = new PlayAnalysis(kickReturner, defensiveRoster, offensiveRoster, "R", 0);
					analysis2.performPlay();

					// If the ball was fumbled, trigger a turnover
					boolean turnover = false;
					if ((analysis2.ballFumbled() && analysis2.turnoverOccurred()))
						turnover = true;

					// Get return distance and update field to reflect distance
					returnDistance = analysis2.getYardsGained();
					field.setYardline(field.getYardline() - returnDistance);

					// If the return still ends in the endzone, a touchback occurs
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
			if (onOffense){
				if (homeTeam.isComputer()){
					offensiveRoster = awayTeamRoster;
					defensiveRoster = homeTeamRoster;
					offensiveTeam = awayTeam;
					defensiveTeam = homeTeam;
				}
				else{
					offensiveRoster = homeTeamRoster;
					defensiveRoster = awayTeamRoster;
					offensiveTeam = homeTeam;
					defensiveTeam = awayTeam;
				}
			}
			else{
				if (homeTeam.isComputer()){

					offensiveRoster = homeTeamRoster;
					defensiveRoster = awayTeamRoster;
					offensiveTeam = homeTeam;
					defensiveTeam = awayTeam;
				}
				else{
					offensiveRoster = awayTeamRoster;
					defensiveRoster = homeTeamRoster;
					offensiveTeam = awayTeam;
					defensiveTeam = homeTeam;
				}
			}

			// Get the punter for this play
			for (int i = 0; i < offensiveRoster.size(); i++){
				if (offensiveRoster.elementAt(i).getPosition().equals("P")){
					kicker = offensiveRoster.elementAt(i);
					break;
				}
			}


			// Punt
			PlayAnalysis analysis = new PlayAnalysis(kicker, offensiveRoster, defensiveRoster, "Punt", 0);
			analysis.performPlay();

			// Get the punt distance
			kickDistance = analysis.getKickDistance();

			System.out.println("Punt Distance: " + kickDistance);

			// Update field to reflect punt
			field.setYardline(field.getYardline() + kickDistance);

			// If a touchback occurs
			if (field.getYardline() >= 100){
				field.setYardline(80);
				displayKickResults(true, false, false);
			}

			// Returning the punt
			else{
				// A cornerback will return the punt
				for (int i = 0; i < defensiveRoster.size(); i++){
					if (defensiveRoster.elementAt(i).getPosition().equals("CB")){
						kickReturner = defensiveRoster.elementAt(i);
						break;
					}
				}

				// Perform punt return as run play
				PlayAnalysis analysis2 = new PlayAnalysis(kickReturner, defensiveRoster, offensiveRoster, "R", 0);
				analysis2.performPlay();

				// Check for turnovers
				boolean turnover = false;
				if ((analysis2.ballFumbled() && analysis2.turnoverOccurred()))
					turnover = true;

				boolean inside20 = false;

				// If ball lands inside 20, trigger tag to update punter stats
				if (field.getYardline() > 80)
					inside20 = true;

				// Add return yardage to play
				returnDistance = analysis2.getYardsGained();
				field.setYardline(field.getYardline() - returnDistance);

				// For touchbacks, set ball at 20
				if (field.getYardline() >= 100){
					field.setYardline(80);
					displayKickResults(true, turnover, false);
				}
				else
					displayKickResults(false, turnover, inside20);
			}
		}
	}

	/**
	Displays the result of the kick or punt to the screen and updates field and statistics
	@param isTouchback - Did kick result in a touchback?
	@param turnoverOccurred - Did the return result in a turnover?
	@param inside20 - Did kick land inside the 20 yard line?
	*/
	void displayKickResults(boolean isTouchback, boolean turnoverOccurred, boolean inside20){

		// Stop clock
		clockStopped = true;
		gameTimer.stop();
		playClock.stop();

		// Flip field
		field.setYardline(100 - field.getYardline());

		System.out.println("Kick Distance: " + kickDistance + "   Returned: " + returnDistance);


		boolean touchdownScored = false;
		boolean changeOfPossession = false;
		boolean noPlay = false;

		field.setDown(1);
		field.setYardsToGo(10);

		// Get time elapsed for kick and return
		int timeElapsed = (int)(Math.random() * 5 + 5);

		// If a punt occurred and CPU kicked ball and clock was running, make sure the play was able to be performed
		if(kicker.getPosition().equals("P") && onDefense && !clockStoppedForCPU){
			clockStoppedForCPU = true;

			// Get the amount of time the CPU spent in the huddle
			int timeInHuddle = cpu.getTimeInHuddle();

			timeElapsed += timeInHuddle;

			// If time ran out while still in the huddle, no play occurred.
			if (timeInHuddle >= field.getTimeLeft()){
				timeElapsed = field.getTimeLeft();
				noPlay = true;
			}
		}
		
			
		// If no play occurred, update scoreboard but omit any play information
		if (noPlay){
			JOptionPane.showMessageDialog(null, "Quarter over. No Play.", "End of Quarter", JOptionPane.PLAIN_MESSAGE);

			offensiveTeam.getStats().addTimeOfPossession(field.getTimeLeft());
			field.reduceTime(field.getTimeLeft(), false);

			updateScoreboard();
		}
		// If touchdown was scored, set ball at opponent's 3 yd line
		else if (field.getYardline() >= 100){
				field.touchdownScored();
				touchdownScored = true;
				field.setYardsToGo(3);
				field.setYardline(3);
				defensiveTeam.getStats().addScoring(field.getQuarter(), 6);


		}
		// If play ended in the red zone, add stat
		else if (field.getYardline() >= 80){
				defensiveTeam.getStats().addRedZoneAttempt();
				teamInRedZone = true;

			// If ball is inside 10, show the "and goal" situation
			if (field.getYardline() > 90)
				field.setYardsToGo(100 - field.getYardline());
		}


		// If a play did occur, display the results of the play and update the scoreboard
		if (!noPlay){
			changeOfPossession = true;
			onSpecialTeams = false;

			// If a turnover occurred during the return, change of possession does not occur
			if (turnoverOccurred){
				defensiveTeam.getStats().addTurnover();
				changeOfPossession = false;
			}

			String message = "";

			// Append message for kickoff
			if(kicker.getPosition().equals("K")){
				message = kicker.getName() + " kicks off for " + kickDistance + " yards.\n";
			}
			// Punt kicked
			else if(kicker.getPosition().equals("P")){
				message = kicker.getName() + " punts ball for " + kickDistance + " yards.\n";
			}



			// Add stats for the kicker involved
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

				//	System.out.println(defensiveTeam.getName() + " Punt Returns: " + defensiveTeam.getStats().getPuntReturns() + "   Yards: " + defensiveTeam.getStats().getPuntReturnYards());
				}
				else{
					kickReturner.getStats().addKickReturnYards(returnDistance);

					defensiveTeam.getStats().addKickReturnYards(returnDistance);

				//	System.out.println(defensiveTeam.getName() + " Kick Returns: " + defensiveTeam.getStats().getKickReturns() + "   Yards: " + defensiveTeam.getStats().getKickReturnYards());
				}

					message += kickReturner.getName() + " returns ball for " + returnDistance + " yards.";

			}

			// Touchdown scored (not fully implemented yet)
			if (touchdownScored){
			//	kickReturner.getStats().addRushTD();

				// Display opposite team since the possession has already changed (Test only)
				message += "\n" + defensiveTeam.getName() + " scores a touchdown!";

				String playType = "";
				if (kicker.getPosition().equals("P"))
					playType = "PR";
				else
					playType = "KR";

				// Omit this for now!  ( Touchdowns are not fully implemented for kick/punt returns )

				field.setYardline(3);	// Ball at 3 yard line
				onSpecialTeams = true;
			}

			// Ball is fumbled
			if (turnoverOccurred){
				message += "\nFumble!  ";

				message += "Ball recovered by kicking team";
			}

			// Have to move the change in time in case a touchdown was scored

			// Reduce the time
			if (field.getTimeLeft() < timeElapsed){
				System.out.println("Play duration: " + field.getTimeLeft() + " seconds");

				defensiveTeam.getStats().addTimeOfPossession(field.getTimeLeft());
				field.reduceTime(field.getTimeLeft(), touchdownScored);
			}
			else{
				System.out.println("Play duration: " + timeElapsed + " seconds");

				defensiveTeam.getStats().addTimeOfPossession(timeElapsed);
				field.reduceTime(timeElapsed, touchdownScored);
			}

			kickingOff = false;

			if (changeOfPossession)
				changePossession();

			updateScoreboard();

			JOptionPane.showMessageDialog(null, message, "Kick Performed", JOptionPane.PLAIN_MESSAGE);
		}
	}


	/**
	Displays results of the current offensive play
	@param analysis - Play analysis of the current play
	*/
	void displayResults(PlayAnalysis analysis){
		boolean isFirstDown = false;
		boolean touchdownScored = false;
		boolean safetyScored = false;
		boolean fieldGoalScored = false;
		boolean conversionScored = false;
		boolean conversionMissed = false;
		String fieldGoalMissed = "";
		boolean changeOfPossession = false;
		boolean patScored = false;
		boolean patMissed = false;

		boolean turnover = false;

		boolean quarterOver = false;

		// Get CPU time in huddle, if on offense
		int timeInHuddle = cpu.getTimeInHuddle();

		// Retrieve information for the current drive
		Drive currentDrive = drives.elementAt(drives.size() - 1);


		boolean passPlay = false;

		// Get the yards gained on the play
		int yardsGained = analysis.getYardsGained();

		kickingOff = false;

		// If a turnover occurred...
		if (analysis.passIntercepted() || (analysis.ballFumbled() && analysis.turnoverOccurred()))
			turnover = true;


		// If yards gained causes a touchdown, make yards gained reach the goal line
		if (yardsGained + field.getYardline() > 100)
			yardsGained = (100 - field.getYardline());

		// If play was a pass...
		if (offensivePlay.getType().equals("P"))
			passPlay = true;

		Player passer = new Player();

		// Retrieve the quarterback on the play
		for (int i = 0; i < roster.size(); i++){
			if (roster.elementAt(i).getPosition().equals("QB")){
				passer = roster.elementAt(i);
				break;
			}
		}

		// If offensive pass play, update QB and team stats
		if (passPlay && !onSpecialTeams){
			passer.getStats().addPassAttempt();
			offensiveTeam.getStats().addPassAttempt();
		}

		int timeLeft = 0;
		int timeElapsed = analysis.getPlayTime();

		// If time ran out on CPU before running a play, no play occurred
		if (onDefense && !clockStoppedForCPU){
			// Get the amount of time the CPU spent in the huddle
			timeElapsed += timeInHuddle;

			if (timeInHuddle >= field.getTimeLeft()){
				timeElapsed = field.getTimeLeft();
				quarterOver = true;
			}
		}

		// Add time elapsed to stats, but not to the clock (do this AFTER the play is analyzed completely)
		if (!onSpecialTeams){

			if (quarterOver){
				offensiveTeam.getStats().addTimeOfPossession(field.getTimeLeft());
				currentDrive.addTimeElapsed(field.getTimeLeft());
			}
			else if (timeElapsed >= field.getTimeLeft()){
				System.out.println("Play duration: " + field.getTimeLeft() + " seconds");

				timeLeft = 0;

				offensiveTeam.getStats().addTimeOfPossession(field.getTimeLeft());
				currentDrive.addPlay(yardsGained, field.getTimeLeft());
			}
			else{

				System.out.println("Play duration: " + timeElapsed + " seconds");


				timeLeft = field.getTimeLeft() - timeElapsed;

				offensiveTeam.getStats().addTimeOfPossession(timeElapsed);
				currentDrive.addPlay(yardsGained, timeElapsed);
			}
		}

		// If the clock did not run out, get play info
		if (!quarterOver){
		//	System.out.println("Size: " + drives.size() + "  Elapsed: " + currentDrive.getTimeElapsed() + " Yds" + currentDrive.getYardsCovered());
	
			if (!analysis.isBallKicked()){

				// If a first down is made
				if (yardsGained >= field.getYardsToGo()){


					// If touchdown was scored, set ball at opponent's 3 yd line
					if (field.getYardline() + yardsGained >= 100 && !turnover){

						// Add conversion/attempt stats
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

						// If on special teams, a 2-point conversion was scored
						if (onSpecialTeams){
							field.conversionScored();
							conversionScored = true;
							kickingOff = true;
							field.setYardline(35);

							currentDrive.addExtraPointAttempt(true, "2PT", primaryPlayer.getName());
							displayScoringDrive();
						}
						// Otherwise it was a touchdown.
						else{
							// Check for a red zone conversion
							if (field.getYardline() >= 80 && teamInRedZone)
								offensiveTeam.getStats().addRedZoneConversion();

							field.touchdownScored();		// Yardline is taken care of here
							touchdownScored = true;
							field.setYardsToGo(3);
							offensiveTeam.getStats().addScoring(field.getQuarter(), 6);

							// Add scoring drive
							if (passPlay)
								currentDrive.addScoringDrive(field.getQuarter(), timeLeft, "P", passer.getName(), primaryPlayer.getName(), yardsGained, field.getScoringTeamScore(), field.getDefendingTeamScore() );
							else
								currentDrive.addScoringDrive(field.getQuarter(), timeLeft, "R", primaryPlayer.getName(), "", yardsGained, field.getScoringTeamScore(), field.getDefendingTeamScore() );

						}

						isFirstDown = false;

					}

					// If a first down is made
					else{
						if (!turnover)
							isFirstDown = true;

						offensiveTeam.getStats().addFirstDown();

						// Add conversion stats
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

						// Add red zone stats
						if (field.getYardline() >= 80 && !teamInRedZone){
							offensiveTeam.getStats().addRedZoneAttempt();
							teamInRedZone = true;
						}

						System.out.println("Yardline: " + field.getYardline());

						// Set "and goal" attribute, if applicable
						if( field.getYardline() > 90)
							field.setYardsToGo(100 - field.getYardline());
					}
				}
				// If a first down is not made
				else{
					// If this was a 2-point conversion attempt, it failed
					if (onSpecialTeams){
						conversionMissed = true;
						currentDrive.addExtraPointAttempt(false, "2PT", primaryPlayer.getName());
						displayScoringDrive();

						field.setYardline(35);
						kickingOff = true;
					}

					// If safety is scored, reward the defensive team and give defense the ball at the 45
					else if (field.getYardline() + yardsGained <= 0){
						field.safetyScored();
						safetyScored = true;
						field.setYardline(20);

						defensiveTeam.getStats().addScoring(field.getQuarter(), 2);

						Drive safetyDrive = new Drive(defensiveTeam.getName());
						drives.add(safetyDrive);
						currentDrive = drives.elementAt(drives.size() - 1);

						currentDrive.addScoringDrive(field.getQuarter(), timeLeft, "S", "", "", (0 - yardsGained), field.getDefendingTeamScore(), field.getScoringTeamScore() );
						displayScoringDrive();

						kickingOff = true;

					}
					// If not a safety nor a 2-point conversion attempt
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

							field.setYardline(100 - field.getYardline());
							changeOfPossession = true;

							if (field.getYardline() >= 80 && !teamInRedZone){

								defensiveTeam.getStats().addRedZoneAttempt();
								teamInRedZone = true;
							}
						}
					}
				}
			}
			// If the ball was kicked
			else{
			//	if (analysis.kickIsSuccessful()){
					if (analysis.getPlayType().equals("Field_Goal")){

						attemptDistance = 117 - field.getYardline();
						if (kickDistance + field.getYardline() >= 103){		// Omitting the 7 yards between the LOS and kicker
							if (kickAccuracy >= 42 && kickAccuracy <= 58){

								fieldGoalScored = true;
								if (onSpecialTeams){
									field.patScored();		// Yardline taken care of here
									scoringDriveEnded = true;

									currentDrive.addExtraPointAttempt(true, "XP", primaryPlayer.getName());

									displayScoringDrive();
									field.setYardline(35);
									patScored = true;

								}
								else{
									if (field.getYardline() >= 80 && teamInRedZone)
										offensiveTeam.getStats().addRedZoneConversion();

									field.fieldGoalScored();
									field.setYardline(35);
									currentDrive.addScoringDrive(field.getQuarter(), timeLeft, "FG", analysis.getOffensivePlayer().getName(), "", attemptDistance, field.getScoringTeamScore(), field.getDefendingTeamScore() );

									displayScoringDrive();
									scoringDriveEnded = true;

								}

								kickingOff = true;

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

						// If field goal is missed, change possession and move ball back 7 yards
						if (!fieldGoalScored && !onSpecialTeams){
							field.setYardline(100 - (field.getYardline() - 7));
							changeOfPossession = true;
						}
						// If field goal is missed, add info to drive
						else if (!fieldGoalScored && onSpecialTeams){
							patMissed = true;

							currentDrive.addExtraPointAttempt(false, "XP", primaryPlayer.getName());
							displayScoringDrive();
							field.setYardline(35);
							kickingOff = true;
						}
					}
				}

				// If turnover occurred...
				if (analysis.passIntercepted() || (analysis.ballFumbled() && analysis.turnoverOccurred())){

						turnover = true;

						// Add interception stats
						if (analysis.passIntercepted()){
							passer.getStats().addInterception();
							offensiveTeam.getStats().addInterception();
						}
					
						// Flip yardline
						field.setYardline(100 - field.getYardline());

						// Add red zone attempt stats if applicable
						if (field.getYardline() >= 80 && !teamInRedZone){
							defensiveTeam.getStats().addRedZoneAttempt();
							teamInRedZone = true;
						}

						isFirstDown = false;

						offensiveTeam.getStats().addTurnover();
						changeOfPossession = true;
				}
		} // end if quarter isn't over


		// Stop the game timer (for the moment)
		gameTimer.stop();
		playClock.stop();

		clockStopped = false;

		// Reduce time to game clock
		if (!onSpecialTeams){

			if (quarterOver)
				field.reduceTime(field.getTimeLeft(), false);

			else if (timeElapsed >= field.getTimeLeft()){
				field.reduceTime(field.getTimeLeft(), touchdownScored);
				clockStopped = true;


			}
			else{
				field.reduceTime(timeElapsed, touchdownScored);
			}

		}
		else{
			field.reduceTime(0,false);
		}

		boolean scoredInLastQuarter = false;

		// If the next quarter is starting (and team just scored), pretend that it is still the prior quarter
		if (field.getTimeLeft() == 900)
			scoredInLastQuarter = true;

		
		if (!field.isGameOver() && !quarterOver){
			// This is triggered so that the End of Quarter display only appears 1 time
			quarterDisplayed = false;
			updateScoreboard();
		}

		String message = "";

		// If quarter has ended, no play info is needed
		if (quarterOver){
			message = "Quarter over. No Play.";
			updateScoreboard();
		}
		else{

			// Pass Play messages
			if (analysis.getPlayType().equals("screen") || analysis.getPlayType().equals("short") || analysis.getPlayType().equals("mid") || analysis.getPlayType().equals("long"))

				// Incomplete passes
				if (analysis.passIncomplete()){
					message = "Pass to " + primaryPlayer.getName() + " is incomplete.";
					clockStopped = true;
				}

				// Interceptions
				else if (analysis.passIntercepted()){

					message = yardsGained + " yard pass to " + primaryPlayer.getName() + " is intercepted.";

						if (field.getYardline() <= 0){
							field.setYardline(20);
							message += "  Touchback.";
						}

					clockStopped = true;
				}

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

				}
			// Run plays

			else if (analysis.getPlayType().equals("R")){

				if (!onSpecialTeams){
				primaryPlayer.getStats().addRush();
				offensiveTeam.getStats().addRush();

					System.out.println("Run Yards = " + yardsGained);
					primaryPlayer.getStats().addRushingYards(yardsGained);
					offensiveTeam.getStats().addRushingYards(yardsGained);
				}

					message = primaryPlayer.getName() + " runs for " + yardsGained + " yards.";

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
					clockStopped = true;
			}

			// Safety scored
			else if (safetyScored){
				if (field.getPossession() == 1)
					message += "\n" + homeTeam.getName() + " scores a safety!";
				else
					message += "\n" + awayTeam.getName() + " scores a safety!";

				scoringDriveEnded = true;
				clockStopped = true;

			}

			// If a punt of FG is kicked
		//	if (analysis.kickIsSuccessful()){

				// Field Goal kicked
				if (analysis.getOffensivePlayer().getPosition().equals("K") && analysis.getPlayType().equals("Field_Goal") ){


					if (fieldGoalScored){
						// If field goal was actually PAT attempt
						if (patScored){

							message = analysis.getOffensivePlayer().getName() + "\'s PAT attempt is good!";
							if (scoredInLastQuarter)
								offensiveTeam.getStats().addScoring(field.getQuarter() - 1, 1);
							else
								offensiveTeam.getStats().addScoring(field.getQuarter(), 1);
							analysis.getOffensivePlayer().getStats().addExtraPoint(true);

							clockStopped = true;
						}
						else{
							offensiveTeam.getStats().addFieldGoal(fieldGoalScored, attemptDistance);
							message = analysis.getOffensivePlayer().getName() + "\'s " + (attemptDistance) + " yard field goal attempt is good!";

							if (scoredInLastQuarter)
								offensiveTeam.getStats().addScoring(field.getQuarter() - 1, 3);
							else
								offensiveTeam.getStats().addScoring(field.getQuarter(), 3);

							analysis.getOffensivePlayer().getStats().addFieldGoal(true, attemptDistance);

							clockStopped = true;
						}
						scoringDriveEnded = true;
					}
					else{
						if (patMissed){
							message = analysis.getOffensivePlayer().getName() + "\'s yard PAT attempt is no good.";
							analysis.getOffensivePlayer().getStats().addExtraPoint(false);
							scoringDriveEnded = true;
							clockStopped = true;
						}
						else{
							offensiveTeam.getStats().addFieldGoal(fieldGoalScored, attemptDistance);
							message = analysis.getOffensivePlayer().getName() + "\'s " + (attemptDistance) + " yard field goal attempt is " + fieldGoalMissed + ".";
							analysis.getOffensivePlayer().getStats().addFieldGoal(false, attemptDistance);
							clockStopped = true;
						}
					}
				}



			if (conversionScored){
				message += "\n" + offensiveTeam.getName() + " converts for 2 points!";
				clockStopped = true;

				if (scoredInLastQuarter)
					offensiveTeam.getStats().addScoring(field.getQuarter() - 1, 2);
				else
					offensiveTeam.getStats().addScoring(field.getQuarter(), 2);

				scoringDriveEnded = true;
			}

			else if (conversionMissed){
				message += "\n" + offensiveTeam.getName() + "\'s 2-pt Conversion is No Good.";
				scoringDriveEnded = true;
				clockStopped = true;
			}

			// First down
			if (isFirstDown)
				message += "\nFirst Down!";

			// Ball is fumbled
			if (analysis.ballFumbled()){
				message += "\nFumble!  ";

				// Turnover
				if (analysis.turnoverOccurred()){
					message += "Ball recovered by defense";
					clockStopped = true;
				}
				// Fumbled recovered
				else
					message += "Ball recovered by offense";

			}
		}

		JOptionPane.showMessageDialog(null, message, "Play performed", JOptionPane.PLAIN_MESSAGE);

		if (!clockStopped){
			int idx = 0;
			String teamName = "";
			if (awayTeam.isComputer()){
				idx = 1;
				teamName = awayTeam.getName();
			}
			else{
				idx = 2;
				teamName = homeTeam.getName();
			}

			// Determine if the CPU wishes to call a timeout (THIS may be move to when CPU chooses a play before the snap)
			if (field.getTimeouts(idx) > 0 && !field.isGameOver())
				if (cpu.willTakeTimeOut()){

					clockStopped = true;
					field.timeoutCalled(idx);

					setTimeouts();

					JOptionPane.showMessageDialog(null, teamName + " has called time out", "Time Out", JOptionPane.PLAIN_MESSAGE);
				}

		}



		if (clockStopped)
			playClockRemaining = 25;
		else
			playClockRemaining = 40;

		// If the user is on defense, do not implement the clock
		if (onDefense){
			clockStoppedForCPU = false;

			if (clockStopped)
				clockStoppedForCPU = true;

			clockStopped = true;
		}

		if (!clockStopped){
			playClockAmount.setText(String.valueOf(playClockRemaining));
		//	updateScoreboard();

			gameTimer.start();
			playClock.start();
		}


		if (changeOfPossession)
			changePossession();

		if (kickingOff)
			setKickoff();

		if (field.isGameOver())
			updateScoreboard();
	}


	/**
	Changes possession from home team to away team and vise versa.
	*/
	void changePossession(){
		// Stop the clock
		clockStopped = true;
		clockStoppedForCPU = true;
		gameTimer.stop();
		playClock.stop();

		// Set field to 1st and 10
		field.setDown(1);
		field.setYardsToGo(10);

		
		// If current ball position is NOT in the red zone
		if (field.getYardline() < 80)
			teamInRedZone = false;

		// Change possession and show new possession on scoreboard
		field.changePossession();
		if (field.getPossession() == 1){
			possessionLabels[0].setBackground(awayTeam.getSecondaryColor().brighter());
			possessionLabels[1].setBackground(null);
		}
		else{
			possessionLabels[0].setBackground(null);
			possessionLabels[1].setBackground(homeTeam.getSecondaryColor().brighter());
		}

		// Switch offense to defense and defense to offense
		if (onOffense){
			onDefense = true;
			onOffense = false;
			onSpecialTeams = false;
			setDefense();
		}
		else if (onDefense){
			onDefense = false;
			onOffense = true;
			onSpecialTeams = false;
			setOffense();
		}


		// Use the defensive team's name because no plays were made in order to switch the values yet.
		Drive d = new Drive(defensiveTeam.getName());
		drives.add(d);
	

		updateScoreboard();
	}


	/**
	Shows any new change to the game and displays it on the scoreboard
	*/
	void updateScoreboard(){

		// Applies the standard scoreboard images for all numerical values
		applyScoreboardImages();

		// If quarter is over, but game is not over, show end of quarter display
		if (field.isQuarterOver() && !field.isGameOver())
			showEndOfQuarterScore();

		// If it is halftime
		if (field.isQuarterOver() && field.getQuarter() == 3){
			// Stop clock
			clockStopped = true;
			clockStoppedForCPU = true;

			// Get field ready for 2nd half kickoff
			field.setYardline(35);
			field.setDown(1);
			field.setYardsToGo(10);
			setTimeouts();

			onSpecialTeams = false;
			//away team = 1, home team = 2

			// The team that received the opening kickoff will kickoff
			field.setPossession(receivedOpeningKickoff);

			// Computer is home team and they received opening kickoff
			if (homeTeam.isComputer() && receivedOpeningKickoff == 2){
				onDefense = true;
				onOffense = false;
			}

			// Computer is home team and they kicked opening kickoff
			else if (homeTeam.isComputer() && receivedOpeningKickoff == 1){
				onOffense = true;
				onDefense = false;
			}
			// Computer is away team and they received opening kickoff
			else if (awayTeam.isComputer() && receivedOpeningKickoff == 1){
				onOffense = false;
				onDefense = true;
			}
			// Computer is away team and they kicked opening kickoff
			else if (awayTeam.isComputer() && receivedOpeningKickoff == 2){
				onOffense = true;
				onDefense = false;
			}

			kickingOff = true;

			setKickoff();
			applyScoreboardImages();
		}
		// If it is the end of regulation
		else if (field.isQuarterOver() && field.getQuarter() == 5 && !field.isGameOver()){
			// Stop the clock
			clockStopped = true;
			clockStoppedForCPU = true;

			field.setYardline(35);
			field.setDown(1);
			field.setYardsToGo(10);
			setTimeouts();

			//away team = 1, home team = 2


			field.setPossession( (receivedOpeningKickoff % 2) + 1);
			onSpecialTeams = false;

			// Computer is home team and they received opening kickoff
			if (homeTeam.isComputer() && receivedOpeningKickoff == 2){
				onDefense = false;
				onOffense = true;
			}

			// Computer is home team and they kicked opening kickoff
			else if (homeTeam.isComputer() && receivedOpeningKickoff == 1){
				onOffense = false;
				onDefense = true;
			}
			// Computer is away team and they received opening kickoff
			else if (awayTeam.isComputer() && receivedOpeningKickoff == 1){
				onOffense = true;
				onDefense = false;
			}
			// Computer is away team and they kicked opening kickoff
			else if (awayTeam.isComputer() && receivedOpeningKickoff == 2){
				onOffense = false;
				onDefense = true;
			}

			kickingOff = true;
			setKickoff();
			applyScoreboardImages();

		}
		// If game is over, show end of game display
		else if (field.isGameOver()){

			usePlay.setEnabled(true);
			usePlay.setText("Stats");

			showEndOfQuarterScore();
		}
	}


	/**
	Uses numerical image icons to display scoreboard information that needs numbers
	*/
	void applyScoreboardImages(){
		// Image height = 25 px
		ImageIcon [] digits = {new ImageIcon(getClass.getResource("images/scoreboard/scoreboard0.png")), new ImageIcon(getClass.getResource("images/scoreboard/scoreboard1.png")),
					new ImageIcon(getClass.getResource("images/scoreboard/scoreboard2.png")), new ImageIcon(getClass.getResource("images/scoreboard/scoreboard3.png")), 
					new ImageIcon(getClass.getResource("images/scoreboard/scoreboard4.png")), new ImageIcon(getClass.getResource("images/scoreboard/scoreboard5.png")), 
					new ImageIcon(getClass.getResource("images/scoreboard/scoreboard6.png")), new ImageIcon(getClass.getResource("images/scoreboard/scoreboard7.png")), 
					new ImageIcon(getClass.getResource("images/scoreboard/scoreboard8.png")), new ImageIcon(getClass.getResource("images/scoreboard/scoreboard9.png"))};

		ImageIcon [] direction = {new ImageIcon(getClass.getResource("images/scoreboard/upfield.png")), new ImageIcon(getClass.getResource("images/scoreboard/downfield.png")), null};

		int down = field.getDown();
		int toGo = field.getYardsToGo();
		int qtr = field.getQuarter();
		int yard = field.getYardline();
		int minutes = Integer.parseInt(field.getMinutesLeft());
		int seconds = Integer.parseInt(field.getSecondsLeft());
		int away = field.getAwayScore();
		int home = field.getHomeScore();


		downNum.setIcon(digits[down]);
		qtrNum.setIcon(digits[qtr]);

		// If downfield
		if (yard > 50)
			fieldDirection.setIcon(direction[1]);
		// If upfield
		else if (yard < 50)
			fieldDirection.setIcon(direction[0]);
		// If at midfield
		else
			fieldDirection.setIcon(direction[2]);

		// If downfield
		if (yard > 50)
			yard = 100 - yard;

		// If yardline is less than 10
		if (yard < 10){
			yardline[0].setIcon(digits[0]);
			yardline[1].setIcon(digits[yard]);
		}
		else{
			yardline[0].setIcon(digits[yard / 10]);
			yardline[1].setIcon(digits[yard % 10]);
		}

		// If yards to go less than 10
		if (toGo < 10){
			yardsToGo[0].setIcon(digits[0]);
			yardsToGo[1].setIcon(digits[toGo]);
		}
		else{
			yardsToGo[0].setIcon(digits[toGo / 10]);
			yardsToGo[1].setIcon(digits[toGo % 10]);
		}

		// If minutes less than 10
		if (minutes < 10){
			gameClock[0].setIcon(digits[0]);
			gameClock[1].setIcon(digits[minutes]);
		}
		else{
			gameClock[0].setIcon(digits[1]);
			gameClock[1].setIcon(digits[minutes - 10]);
		}

		// If seconds less than 10
		if (seconds < 10){
			gameClock[2].setIcon(digits[0]);
			gameClock[3].setIcon(digits[seconds]);
		}
		else{
			gameClock[2].setIcon(digits[seconds / 10]);
			gameClock[3].setIcon(digits[seconds % 10]);
		}

		// If away score less than 10
		if (away < 10){
			awayScores[0].setIcon(digits[0]);
			awayScores[1].setIcon(digits[away]);
		}
		else{
			awayScores[0].setIcon(digits[away / 10]);
			awayScores[1].setIcon(digits[away % 10]);
		}

		// If home score less than 10
		if (home < 10){
			homeScores[0].setIcon(digits[0]);
			homeScores[1].setIcon(digits[home]);
		}
		else{
			homeScores[0].setIcon(digits[home / 10]);
			homeScores[1].setIcon(digits[home % 10]);
		}
	}


	/**
	Graphically displays the end of quarter or game score
	*/
	void showEndOfQuarterScore(){
		// If this was already displayed, return
		if (quarterDisplayed){
			quarterDisplayed = false;
			return;
		}

		quarterDisplayed = true;
		Font f = new Font("Lucida Sans Regular", Font.BOLD, 22);

		JLayeredPane scoreDisplayPanel = new JLayeredPane();
		JPanel awayMainPanel = new JPanel();
		JPanel awaySecondaryPanel = new JPanel();
		JPanel awayScorePanel = new JPanel();

		JLabel awayIconLabel = new JLabel();
		JLabel awayTeamName = new JLabel(awayTeam.getName().replaceAll("_", " "));
		JLabel awayScoreLabel = new JLabel(String.valueOf(field.getAwayScore()));

		JPanel homeMainPanel = new JPanel();
		JPanel homeSecondaryPanel = new JPanel();
		JPanel homeScorePanel = new JPanel();

		JLabel homeIconLabel = new JLabel();
		JLabel homeTeamName = new JLabel(homeTeam.getName().replaceAll("_", " "));
		JLabel homeScoreLabel = new JLabel(String.valueOf(field.getHomeScore()));

		JPanel quarterMessage = new JPanel();

		scoreDisplayPanel.setPreferredSize(new Dimension(400,200));
		scoreDisplayPanel.setLayout(null);

		awayMainPanel.setBackground(awayTeam.getPrimaryColor());
		awaySecondaryPanel.setBackground(awayTeam.getPrimaryColor().darker());

		homeMainPanel.setBackground(homeTeam.getPrimaryColor());
		homeSecondaryPanel.setBackground(homeTeam.getPrimaryColor().darker());

		awayScorePanel.setBackground(new Color(22,22,22));
		awayScoreLabel.setForeground(Color.WHITE);
		awayScoreLabel.setFont(f);
		awayScorePanel.add(awayScoreLabel, BorderLayout.CENTER);

		homeScorePanel.setBackground(new Color(22,22,22));
		homeScoreLabel.setForeground(Color.WHITE);
		homeScoreLabel.setFont(f);
		homeScorePanel.add(homeScoreLabel, BorderLayout.CENTER);

		// Highlight the winning team's score if game is over
		if (field.isGameOver()){
			if (field.getHomeScore() > field.getAwayScore())
				homeScoreLabel.setForeground(Color.YELLOW.darker());
			else if (field.getAwayScore() > field.getHomeScore())
				awayScoreLabel.setForeground(Color.YELLOW.darker());
		}

		awayTeamName.setForeground(Color.WHITE);
		homeTeamName.setForeground(Color.WHITE);
		awayTeamName.setFont(f);
		homeTeamName.setFont(f);

		quarterMessage.setBorder(new LineBorder(Color.GRAY));
		quarterMessage.setBackground(Color.BLACK);
		quarterMessage.setForeground(Color.WHITE);

		awayMainPanel.setLayout(null);
		homeMainPanel.setLayout(null);

		awayMainPanel.setBounds(0,0,400,100);
		awaySecondaryPanel.setBounds(2,70,396,30);

		homeMainPanel.setBounds(0,100,400,100);
		homeSecondaryPanel.setBounds(2,70,396,30);

		awayTeamName.setBounds(20,20,200,50);
		homeTeamName.setBounds(20,20,200,50);

		awayScorePanel.setBounds(350,10,50,75);
		homeScorePanel.setBounds(350,10,50,75);

		awayIconLabel.setIcon(awayTeam.getLogo());
		awayIconLabel.setBounds(200,0,150,100);

		homeIconLabel.setIcon(homeTeam.getLogo());
		homeIconLabel.setBounds(200,0,150,100);

		quarterMessage.setBounds(50,90,200,20);

		// Set text for middle of panel
		if (field.getQuarter() == 2)
			quarterMessage.add(new JLabel("End of 1st Quarter"));
		else if (field.getQuarter() == 3)
			quarterMessage.add(new JLabel("End of 2nd Quarter"));
		else if (field.getQuarter() == 4 && !field.isGameOver())
			quarterMessage.add(new JLabel("End of 3rd Quarter"));
		else if (field.isGameOver())
			quarterMessage.add(new JLabel("Final Score"));
		else if (field.getQuarter() == 5)
			quarterMessage.add(new JLabel("End of Regulation"));


		awayMainPanel.setBorder(new LineBorder(Color.GRAY));
		homeMainPanel.setBorder(new LineBorder(Color.GRAY));

		awayMainPanel.add(awayTeamName);
		awayMainPanel.add(awayScorePanel);
		awayMainPanel.add(awayIconLabel);
		awayMainPanel.add(awaySecondaryPanel);

		homeMainPanel.add(homeTeamName);
		homeMainPanel.add(homeScorePanel);
		homeMainPanel.add(homeIconLabel);
		homeMainPanel.add(homeSecondaryPanel);


		scoreDisplayPanel.add(awayMainPanel);
		scoreDisplayPanel.add(homeMainPanel);
		scoreDisplayPanel.add(quarterMessage);

		scoreDisplayPanel.moveToFront(quarterMessage);

		JOptionPane.showMessageDialog(null, scoreDisplayPanel, "", JOptionPane.PLAIN_MESSAGE);
	}


	/**
	Gathers all team, player, and drive information to display at end of game
	*/
	void showStatistics(){
		main.setVisible(false);
		main.removeAll();

		JPanel finalScore = new JPanel();

		JLabel homeLogoPanel = new JLabel();
		JLabel homeTeamName = new JLabel();
		JLabel homeScoreLabel = new JLabel();
		JLabel awayScoreLabel = new JLabel();
		JLabel awayTeamName = new JLabel();
		JLabel awayLogoPanel = new JLabel();

		Font f = new Font("Lucida Sans Regular", Font.PLAIN, 24);

		// Final score display at top of screen

		homeTeamName.setFont(f);
		homeScoreLabel.setFont(f);
		awayTeamName.setFont(f);
		awayScoreLabel.setFont(f);

		homeTeamName.setForeground(Color.WHITE);
		homeScoreLabel.setForeground(Color.WHITE);
		awayTeamName.setForeground(Color.WHITE);
		awayScoreLabel.setForeground(Color.WHITE);

		homeTeamName.setHorizontalAlignment(SwingConstants.RIGHT);
		homeScoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		homeTeamName.setText(homeTeam.getName().replaceAll("_", " "));
		homeScoreLabel.setText(String.valueOf(field.getHomeScore()));
		awayTeamName.setText(awayTeam.getName().replaceAll("_", " "));
		awayScoreLabel.setText(String.valueOf(field.getAwayScore()));

		finalScore.setLayout(null);

		awayLogoPanel.setBounds(0,0,60,60);
		awayTeamName.setBounds(70,0,250,60);
		awayScoreLabel.setBounds(335,0,60,60);
		homeScoreLabel.setBounds(405,0,60,60);
		homeTeamName.setBounds(470,0,250,60);
		homeLogoPanel.setBounds(730,0,60,60);

		finalScore.setOpaque(false);
		finalScore.add(homeLogoPanel);
		finalScore.add(homeTeamName);
		finalScore.add(homeScoreLabel);
		finalScore.add(awayScoreLabel);
		finalScore.add(awayTeamName);
		finalScore.add(awayLogoPanel);

		double scale = 0.0;

		// width, then height
		BufferedImage awayImage;

		scale = awayTeam.getLogo().getIconHeight() / 60;
		if (awayTeam.getLogo().getIconWidth() / scale > 60){
			scale = awayTeam.getLogo().getIconWidth() / 60;
			awayImage = resize(awayTeam.getLogo().getImage(),60,(int)(awayTeam.getLogo().getIconHeight() / scale) );
		}
		else
			awayImage = resize(awayTeam.getLogo().getImage(),(int)(awayTeam.getLogo().getIconWidth() / scale),60);

		awayLogoPanel.setIcon(new ImageIcon(awayImage));

		BufferedImage homeImage;

		scale = homeTeam.getLogo().getIconHeight() / 60;
		if (homeTeam.getLogo().getIconWidth() / scale > 60){
			scale = homeTeam.getLogo().getIconWidth() / 60;
			homeImage = resize(homeTeam.getLogo().getImage(),60,(int)(homeTeam.getLogo().getIconHeight() / scale) );
		}
		else
			homeImage = resize(homeTeam.getLogo().getImage(),(int)(homeTeam.getLogo().getIconWidth() / scale),60);

		homeLogoPanel.setIcon(new ImageIcon(homeImage));

		// End final score panel

		// Tabbed pane will hold team stats, player stats, and a scoring summary
		JTabbedPane statsPane = new JTabbedPane();

		JPanel teamStatistics = new JPanel();
		JPanel playerStatistics = new JPanel();
		JPanel scoringSummary = new JPanel();

		teamStatistics.setLayout(null);
		playerStatistics.setLayout(null);
		scoringSummary.setLayout(null);


		Font f2 = new Font("Lucida Sans Regular", Font.PLAIN, 13);
		Font labelFont = new Font("Arial", Font.BOLD, 14);


		// Player Statistics

		// Passing Statistics
		JLabel passingLabel = new JLabel("<html><p><u>Passing&nbsp&nbsp&nbsp&nbsp</u></p></html>");
		passingLabel.setFont(labelFont);
		passingLabel.setForeground(Color.CYAN);

		String [] passHeaders = {"Team", "Name","No.","Comp","Att","Yards","INT","TD","Rush","Yards","TD","Rating"};
		Vector<String> passingHeaders = new Vector<String>();

		for (int i = 0; i < 12; i++)
			passingHeaders.addElement(passHeaders[i]);

		Vector<Vector<Object>> passingStats = new Vector<Vector<Object>>();

		int awayIdx = 0;

		// Away team quarterbacks
		for(int i = 0; i < awayTeamRoster.size(); i++){
			// If player is a quarterback, check if player has statistics.
			if (awayTeamRoster.elementAt(i).getPosition().equals("QB")){
				Player p = awayTeamRoster.elementAt(i);

				// If player has statistics, print information
				if (p.getStats().getPassingYards() != 0 || p.getStats().getRushingYards() != 0){

					Object [] tmp = {p.getTeam().replaceAll("_"," "),p.getName(),p.getNumber(),p.getStats().getCompletions(),p.getStats().getAttempts(),
						p.getStats().getPassingYards(), p.getStats().getInterceptions(), p.getStats().getPassTDs(), p.getStats().getRushes(),
						p.getStats().getRushingYards(), p.getStats().getRushTDs(), p.getStats().getPasserRating()};

					Vector<Object> tmp2 = new Vector<Object>();

					for (int j = 0; j < 12; j++)
						tmp2.addElement(tmp[j]);

					passingStats.addElement(tmp2);

					awayIdx++;

				}
			}
		}

		int homeIdx = 0;

		// Home team quarterbacks
		for(int i = 0; i < homeTeamRoster.size(); i++){
			// If player is a quarterback, check if player has statistics.
			if (homeTeamRoster.elementAt(i).getPosition().equals("QB")){
				Player p = homeTeamRoster.elementAt(i);
			//	System.out.println(p.getName() + "  " + p.getTeam() + "   " + p.getOverall());

				// If player has statistics, print information
				if (p.getStats().getPassingYards() != 0 || p.getStats().getRushingYards() != 0){

					Object [] tmp = {p.getTeam().replaceAll("_"," "),p.getName(),p.getNumber(),p.getStats().getCompletions(),p.getStats().getAttempts(),
						p.getStats().getPassingYards(), p.getStats().getInterceptions(), p.getStats().getPassTDs(), p.getStats().getRushes(),
						p.getStats().getRushingYards(), p.getStats().getRushTDs(), p.getStats().getPasserRating()};

					Vector<Object> tmp2 = new Vector<Object>();

					for (int j = 0; j < 12; j++)
						tmp2.addElement(tmp[j]);

					passingStats.addElement(tmp2);

					homeIdx++;
				}
			}
		}

		JTable passingTable = new JTable(passingStats,passingHeaders);
		JTableHeader passingHeader = passingTable.getTableHeader();

		passingHeader.setFont(f2);
		passingTable.setFont(f2);

		// Disable auto resizing
		passingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < 12; i++){
			TableColumn col = passingTable.getColumnModel().getColumn(i);

			int width = 40;
			if (i == 0)
				width = 100;
			else if (i == 1)
				width = 150;
			else if (i == 11)
				width = 50;
			col.setPreferredWidth(width);
		}

		JPanel passingPanel = new JPanel();
		passingPanel.add(passingHeader, BorderLayout.NORTH);
		passingPanel.add(passingTable, BorderLayout.CENTER);
		passingPanel.setOpaque(false);
		passingTable.setEnabled(false);

		passingLabel.setBounds(20,10,200,30);
		passingPanel.setBounds(20,50,660,passingTable.getRowHeight() * (homeIdx + awayIdx + 2));

		playerStatistics.add(passingLabel);
		playerStatistics.add(passingPanel);
		

		int yValue = passingTable.getRowHeight() * (homeIdx + awayIdx + 2) + 70;

		homeIdx = 0;
		awayIdx = 0;

		// End passing statistics
		// Rushing statistics

		JLabel rushingLabel = new JLabel("<html><p><u>Rushing&nbsp&nbsp&nbsp&nbsp</u></p></html>");
		rushingLabel.setFont(labelFont);
		rushingLabel.setForeground(Color.CYAN);

		String [] runHeaders = {"Team", "Name","No.","Rush","Yards","TD","Rec","Yards","TD","Fum"};
		Vector<String> rushingHeaders = new Vector<String>();

		for (int i = 0; i < 10; i++)
			rushingHeaders.addElement(runHeaders[i]);

		Vector<Vector<Object>> rushingStats = new Vector<Vector<Object>>();

		// Away team running backs
		for(int i = 0; i < awayTeamRoster.size(); i++){
			// If player is a halfback or fullback, check if player has statistics.
			if (awayTeamRoster.elementAt(i).getPosition().equals("HB") || awayTeamRoster.elementAt(i).getPosition().equals("FB")){
				Player p = awayTeamRoster.elementAt(i);

				if (p.getStats().getReceptions() > 0 || p.getStats().getRushes() > 0){
					Object [] tmp = {p.getTeam().replaceAll("_"," "),p.getName(),p.getNumber(),p.getStats().getRushes(),p.getStats().getRushingYards(),
						p.getStats().getRushTDs(), p.getStats().getReceptions(), p.getStats().getReceivingYards(), p.getStats().getReceivingTDs(),
						p.getStats().getFumbles()};

					Vector<Object> tmp2 = new Vector<Object>();

					for (int j = 0; j < 10; j++)
						tmp2.addElement(tmp[j]);

					rushingStats.addElement(tmp2);
					awayIdx++;
				}
			}
		}

		// Home team running backs
		for(int i = 0; i < homeTeamRoster.size(); i++){
			// If player is a halfback or fullback, check if player has statistics.
			if (homeTeamRoster.elementAt(i).getPosition().equals("HB") || homeTeamRoster.elementAt(i).getPosition().equals("FB")){
				Player p = homeTeamRoster.elementAt(i);

				if (p.getStats().getRushes() > 0 || p.getStats().getReceptions() > 0){
					Object [] tmp = {p.getTeam().replaceAll("_"," "),p.getName(),p.getNumber(),p.getStats().getRushes(),p.getStats().getRushingYards(),
						p.getStats().getRushTDs(), p.getStats().getReceptions(), p.getStats().getReceivingYards(), p.getStats().getReceivingTDs(),
						p.getStats().getFumbles()};

					Vector<Object> tmp2 = new Vector<Object>();

					for (int j = 0; j < 10; j++)
						tmp2.addElement(tmp[j]);

					rushingStats.addElement(tmp2);

					homeIdx++;
				}
			}
		}
		JTable rushingTable = new JTable(rushingStats,rushingHeaders);
		JTableHeader rushingHeader = rushingTable.getTableHeader();

		rushingHeader.setFont(f2);
		rushingTable.setFont(f2);

		// Disable auto resizing
		rushingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < 10; i++){
			TableColumn col = rushingTable.getColumnModel().getColumn(i);

			int width = 40;
			if (i == 0)
				width = 100;
			else if (i == 1)
				width = 150;
			col.setPreferredWidth(width);
		}

		JPanel rushingPanel = new JPanel();
		rushingPanel.add(rushingHeader, BorderLayout.NORTH);
		rushingPanel.add(rushingTable, BorderLayout.CENTER);
		rushingPanel.setOpaque(false);

		rushingTable.setEnabled(false);

		rushingLabel.setBounds(20,yValue,200,30);
		rushingPanel.setBounds(20,yValue + 30,570,rushingTable.getRowHeight() * (homeIdx + awayIdx + 2));
		playerStatistics.add(rushingLabel);
		playerStatistics.add(rushingPanel);

		yValue += rushingTable.getRowHeight() * (homeIdx + awayIdx + 2) + 40;

		homeIdx = 0;
		awayIdx = 0;

		// End rushing statistics
		// Receiving statistics

		JLabel receivingLabel = new JLabel("<html><p><u>Receiving&nbsp&nbsp&nbsp&nbsp</u></p></html>");
		receivingLabel.setFont(labelFont);
		receivingLabel.setForeground(Color.CYAN);

		String [] recHeaders = {"Team", "Name","No.","Rec","Yards","TD","Fum"};
		Vector<String> receivingHeaders = new Vector<String>();

		for (int i = 0; i < 7; i++)
			receivingHeaders.addElement(recHeaders[i]);

		Vector<Vector<Object>> receivingStats = new Vector<Vector<Object>>();

		// Away team receivers
		for(int i = 0; i < awayTeamRoster.size(); i++){

			// If player is a wide receiver or tight end, check if player has statistics.
			if (awayTeamRoster.elementAt(i).getPosition().equals("WR") || awayTeamRoster.elementAt(i).getPosition().equals("TE")){
				Player p = awayTeamRoster.elementAt(i);

				if (p.getStats().getReceptions() > 0){
					Object [] tmp = {p.getTeam().replaceAll("_"," "),p.getName(),p.getNumber(),p.getStats().getReceptions(), p.getStats().getReceivingYards(), 
						p.getStats().getReceivingTDs(), p.getStats().getFumbles()};

					Vector<Object> tmp2 = new Vector<Object>();

					for (int j = 0; j < 7; j++)
						tmp2.addElement(tmp[j]);

					receivingStats.addElement(tmp2);

					awayIdx++;
				}
			}
		}

		// Home team receivers
		for(int i = 0; i < homeTeamRoster.size(); i++){
			// If player is a wide receiver or tight end, check if player has statistics.
			if (homeTeamRoster.elementAt(i).getPosition().equals("WR") || homeTeamRoster.elementAt(i).getPosition().equals("TE")){

				Player p = homeTeamRoster.elementAt(i);

				if (p.getStats().getReceptions() > 0){
					Object [] tmp = {p.getTeam().replaceAll("_"," "),p.getName(),p.getNumber(),p.getStats().getReceptions(), p.getStats().getReceivingYards(), 
						p.getStats().getReceivingTDs(), p.getStats().getFumbles()};

					Vector<Object> tmp2 = new Vector<Object>();

					for (int j = 0; j < 7; j++)
						tmp2.addElement(tmp[j]);

					receivingStats.addElement(tmp2);

					homeIdx++;
				}
			}
		}

		JTable receivingTable = new JTable(receivingStats,receivingHeaders);
		JTableHeader receivingHeader = receivingTable.getTableHeader();

		receivingHeader.setFont(f2);
		receivingTable.setFont(f2);

		// Disable auto resizing
		receivingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < 7; i++){
			TableColumn col = receivingTable.getColumnModel().getColumn(i);

			int width = 40;
			if (i == 0)
				width = 100;
			else if (i == 1)
				width = 150;
			col.setPreferredWidth(width);
		}

		JPanel receivingPanel = new JPanel();
		receivingPanel.add(receivingHeader, BorderLayout.NORTH);
		receivingPanel.add(receivingTable, BorderLayout.CENTER);
		receivingPanel.setOpaque(false);
		receivingTable.setEnabled(false);

		receivingLabel.setBounds(20,yValue,200,30);
		receivingPanel.setBounds(20,yValue + 30,450,receivingTable.getRowHeight() * (homeIdx + awayIdx + 2));

		playerStatistics.add(receivingLabel);
		playerStatistics.add(receivingPanel);


		yValue += receivingTable.getRowHeight() * (homeIdx + awayIdx + 2) + 40;

		homeIdx = 0;
		awayIdx = 0;

		// End receiving statistics
		// Punting statistics

		JLabel puntingLabel = new JLabel("<html><p><u>Punting&nbsp&nbsp&nbsp&nbsp</u></p></html>");
		puntingLabel.setFont(labelFont);
		puntingLabel.setForeground(Color.CYAN);

		String [] puntHeaders = {"Team", "Name","No.","Punts","Yards","In 20","TB","Long"};
		Vector<String> puntingHeaders = new Vector<String>();

		for (int i = 0; i < 8; i++)
			puntingHeaders.addElement(puntHeaders[i]);

		Vector<Vector<Object>> puntingStats = new Vector<Vector<Object>>();

		yValue += 10;

		// Away team punters
		for(int i = 0; i < awayTeamRoster.size(); i++){
			// If player is a punter, check if player has statistics.
			if (awayTeamRoster.elementAt(i).getPosition().equals("P") ){
				Player p = awayTeamRoster.elementAt(i);

				// If player has statistics, print information
				if (p.getStats().getPunts() != 0){
					Object [] tmp = {p.getTeam().replaceAll("_"," "),p.getName(),p.getNumber(),p.getStats().getPunts(), p.getStats().getPuntYards(), 
						p.getStats().getPuntsInside20(), p.getStats().getTouchbacks(),p.getStats().getLongestPunt()};

					Vector<Object> tmp2 = new Vector<Object>();

					for (int j = 0; j < 8; j++)
						tmp2.addElement(tmp[j]);

					puntingStats.addElement(tmp2);

					awayIdx++;
				}
			}
		}

		// Home team punters
		for(int i = 0; i < homeTeamRoster.size(); i++){
			// If player is a punter, check if player has statistics.
			if (homeTeamRoster.elementAt(i).getPosition().equals("P") ){
				Player p = homeTeamRoster.elementAt(i);

				// If player has statistics, print information
				if (p.getStats().getPunts() != 0){
					Object [] tmp = {p.getTeam().replaceAll("_"," "),p.getName(),p.getNumber(),p.getStats().getPunts(), p.getStats().getPuntYards(), 
						p.getStats().getPuntsInside20(), p.getStats().getTouchbacks(),p.getStats().getLongestPunt()};

					Vector<Object> tmp2 = new Vector<Object>();

					for (int j = 0; j < 8; j++)
						tmp2.addElement(tmp[j]);

					puntingStats.addElement(tmp2);

					homeIdx++;
				}
			}
		}

		JTable puntingTable = new JTable(puntingStats,puntingHeaders);
		JTableHeader puntingHeader = puntingTable.getTableHeader();

		puntingHeader.setFont(f2);
		puntingTable.setFont(f2);


		// Disable auto resizing
		puntingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < 8; i++){
			TableColumn col = puntingTable.getColumnModel().getColumn(i);

			int width = 40;
			if (i == 0)
				width = 100;
			else if (i == 1)
				width = 150;
			col.setPreferredWidth(width);
		}

		JPanel puntingPanel = new JPanel();
		puntingPanel.add(puntingHeader, BorderLayout.NORTH);
		puntingPanel.add(puntingTable, BorderLayout.CENTER);
		puntingPanel.setOpaque(false);
		puntingTable.setEnabled(false);

		puntingLabel.setBounds(20,yValue,200,30);
		puntingPanel.setBounds(20,yValue+30,490,puntingTable.getRowHeight() * (homeIdx + awayIdx + 2));

		playerStatistics.add(puntingLabel);
		playerStatistics.add(puntingPanel);

		yValue += puntingTable.getRowHeight() * (homeIdx + awayIdx + 2) + 50;

		homeIdx = 0;
		awayIdx = 0;

		// End punting statistics
		// Kicking statistics

		JLabel kickingLabel = new JLabel("<html><p><u>Kicking&nbsp&nbsp&nbsp&nbsp</u></p></html>");
		kickingLabel.setFont(labelFont);
		kickingLabel.setForeground(Color.CYAN);

		String [] kickHeaders = {"Team", "Name","No.","FGM/FGA","Long","XPM/XPA","Points"};
		Vector<String> kickingHeaders = new Vector<String>();

		for (int i = 0; i < 7; i++)
			kickingHeaders.addElement(kickHeaders[i]);

		Vector<Vector<Object>> kickingStats = new Vector<Vector<Object>>();

		// Away team kickers
		for(int i = 0; i < awayTeamRoster.size(); i++){
			// If player is a kicker, check if player has statistics.
			if (awayTeamRoster.elementAt(i).getPosition().equals("K") ){
				Player p = awayTeamRoster.elementAt(i);

				// If player has statistics, print information
				if (p.getStats().getFieldGoalAttempts() != 0 || p.getStats().getExtraPointAttempts() != 0){
					Object [] tmp = {p.getTeam().replaceAll("_"," "),p.getName(),p.getNumber(),p.getStats().getFieldGoalsMade() + " / " + p.getStats().getFieldGoalAttempts(), 
					 	p.getStats().getFieldGoalLong(), p.getStats().getExtraPointsMade() + " / " + p.getStats().getExtraPointAttempts(),
						p.getStats().getPointsScored()};

					Vector<Object> tmp2 = new Vector<Object>();

					for (int j = 0; j < 7; j++)
						tmp2.addElement(tmp[j]);

					kickingStats.addElement(tmp2);

					awayIdx++;
				}
			}
		}

		// Home team kickers
		for(int i = 0; i < homeTeamRoster.size(); i++){
			// If player is a kicker, check if player has statistics.
			if (homeTeamRoster.elementAt(i).getPosition().equals("K") ){
				Player p = homeTeamRoster.elementAt(i);

				// If player has statistics, print information
				if (p.getStats().getFieldGoalAttempts() != 0 || p.getStats().getExtraPointAttempts() != 0){
					Object [] tmp = {p.getTeam().replaceAll("_"," "),p.getName(),p.getNumber(),p.getStats().getFieldGoalsMade() + " / " + p.getStats().getFieldGoalAttempts(), 
					 	p.getStats().getFieldGoalLong(), p.getStats().getExtraPointsMade() + " / " + p.getStats().getExtraPointAttempts(),
						p.getStats().getPointsScored()};

					Vector<Object> tmp2 = new Vector<Object>();

					for (int j = 0; j < 7; j++)
						tmp2.addElement(tmp[j]);

					kickingStats.addElement(tmp2);

					homeIdx++;
				}
			}
		}

		JTable kickingTable = new JTable(kickingStats,kickingHeaders);
		JTableHeader kickingHeader = kickingTable.getTableHeader();

		kickingHeader.setFont(f2);
		kickingTable.setFont(f2);

		// Disable auto resizing
		kickingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// Set the first 2 visible columns to 100 pixels wide
		for (int i = 0; i < 7; i++){
			TableColumn col = kickingTable.getColumnModel().getColumn(i);

			int width = 80;
			if (i == 0)
				width = 100;
			else if (i == 1)
				width = 150;
			else if (i == 2 || i == 4 || i == 6)
				width = 40;

			col.setPreferredWidth(width);
		}

		JPanel kickingPanel = new JPanel();
		kickingPanel.add(kickingHeader, BorderLayout.NORTH);
		kickingPanel.add(kickingTable, BorderLayout.CENTER);
		kickingPanel.setOpaque(false);
		kickingTable.setEnabled(false);

		kickingLabel.setBounds(20,yValue,200,30);
		kickingPanel.setBounds(20,yValue + 30,530,kickingTable.getRowHeight() * (homeIdx + awayIdx + 2));

		playerStatistics.add(kickingLabel);
		playerStatistics.add(kickingPanel);

		yValue += puntingTable.getRowHeight() * (homeIdx + awayIdx + 2) + 50;

		// End kicking statistics
		// End player statistics

		// Team Statistics

		String [] gameheaders = {"", awayTeam.getName().replaceAll("_", " "), homeTeam.getName().replaceAll("_"," ")};
		Vector<String> gameHeaders = new Vector<String>();

		for (int i = 0; i < 3; i++)
			gameHeaders.addElement(gameheaders[i]);

		Vector<Vector<Object>> teamStats = new Vector<Vector<Object>>();


		Vector<Object> row1 = new Vector<Object>();
		Object [] tmp = {"First Downs", awayTeam.getStats().getFirstDowns(), homeTeam.getStats().getFirstDowns()};

		for (int i = 0; i < 3; i++)
			row1.addElement(tmp[i]);

		teamStats.addElement(row1);

		Vector<Object> row2 = new Vector<Object>();
		tmp[0] = "3rd Down Conv";
		tmp[1] = (awayTeam.getStats().getThirdDownConversions() + "/" + awayTeam.getStats().getThirdDownAttempts()); 
		tmp[2] = (homeTeam.getStats().getThirdDownConversions() + "/" + homeTeam.getStats().getThirdDownAttempts());

		for (int i = 0; i < 3; i++)
			row2.addElement(tmp[i]);

		teamStats.addElement(row2);

		Vector<Object> row3 = new Vector<Object>();
		tmp[0] = "4th Down Conv";
		tmp[1] = (awayTeam.getStats().getFourthDownConversions() + "/" + awayTeam.getStats().getFourthDownAttempts()); 
		tmp[2] = (homeTeam.getStats().getFourthDownConversions() + "/" + homeTeam.getStats().getFourthDownAttempts());

		for (int i = 0; i < 3; i++)
			row3.addElement(tmp[i]);

		teamStats.addElement(row3);

		Vector<Object> row4 = new Vector<Object>();
		tmp[0] = "Red Zone Offense";
		tmp[1] = (awayTeam.getStats().getRedZoneConversions() + "/" + awayTeam.getStats().getRedZoneAttempts()); 
		tmp[2] = (homeTeam.getStats().getRedZoneConversions() + "/" + homeTeam.getStats().getRedZoneAttempts());

		for (int i = 0; i < 3; i++)
			row4.addElement(tmp[i]);

		teamStats.addElement(row4);

		Vector<Object> row5 = new Vector<Object>();
		tmp[0] = "Rushes";
		tmp[1] = awayTeam.getStats().getRushes(); 
		tmp[2] = homeTeam.getStats().getRushes();

		for (int i = 0; i < 3; i++)
			row5.addElement(tmp[i]);

		teamStats.addElement(row5);

		Vector<Object> row6 = new Vector<Object>();
		tmp[0] = "Rushing Yards";
		tmp[1] = awayTeam.getStats().getRushingYards(); 
		tmp[2] = homeTeam.getStats().getRushingYards();

		for (int i = 0; i < 3; i++)
			row6.addElement(tmp[i]);

		teamStats.addElement(row6);

		Vector<Object> row7 = new Vector<Object>();
		tmp[0] = "Comp-Att-Int";
		tmp[1] = awayTeam.getStats().getPassStats(); 
		tmp[2] = homeTeam.getStats().getPassStats();

		for (int i = 0; i < 3; i++)
			row7.addElement(tmp[i]);

		teamStats.addElement(row7);

		Vector<Object> row8 = new Vector<Object>();
		tmp[0] = "Passing Yards";
		tmp[1] = awayTeam.getStats().getPassingYards(); 
		tmp[2] = homeTeam.getStats().getPassingYards();

		for (int i = 0; i < 3; i++)
			row8.addElement(tmp[i]);

		teamStats.addElement(row8);

		Vector<Object> row9 = new Vector<Object>();
		tmp[0] = "Turnovers";
		tmp[1] = awayTeam.getStats().getTurnovers(); 
		tmp[2] = homeTeam.getStats().getTurnovers();

		for (int i = 0; i < 3; i++)
			row9.addElement(tmp[i]);

		teamStats.addElement(row9);

		Vector<Object> row10 = new Vector<Object>();
		tmp[0] = "Punt Returns";
		tmp[1] = (awayTeam.getStats().getPuntReturns() + " / " + awayTeam.getStats().getPuntReturnYards());
		tmp[2] = (homeTeam.getStats().getPuntReturns() + " / " + homeTeam.getStats().getPuntReturnYards());

		for (int i = 0; i < 3; i++)
			row10.addElement(tmp[i]);

		teamStats.addElement(row10);

		Vector<Object> row11 = new Vector<Object>();
		tmp[0] = "Kick Returns";
		tmp[1] = (awayTeam.getStats().getKickReturns() + " / " + awayTeam.getStats().getKickReturnYards());
		tmp[2] = (homeTeam.getStats().getKickReturns() + " / " + homeTeam.getStats().getKickReturnYards());

		for (int i = 0; i < 3; i++)
			row11.addElement(tmp[i]);

		teamStats.addElement(row11);

		Vector<Object> row12 = new Vector<Object>();
		tmp[0] = "Total Offense";
		tmp[1] = awayTeam.getStats().getTotalOffense(); 
		tmp[2] = homeTeam.getStats().getTotalOffense();

		for (int i = 0; i < 3; i++)
			row12.addElement(tmp[i]);

		teamStats.addElement(row12);

		Vector<Object> row13 = new Vector<Object>();
		tmp[0] = "Total Yards";
		tmp[1] = awayTeam.getStats().getTotalYards(); 
		tmp[2] = homeTeam.getStats().getTotalYards();

		for (int i = 0; i < 3; i++)
			row13.addElement(tmp[i]);

		teamStats.addElement(row13);

		Vector<Object> row14 = new Vector<Object>();
		tmp[0] = "Time Of Possession";
		tmp[1] = awayTeam.getStats().getTimeOfPossession(); 
		tmp[2] = homeTeam.getStats().getTimeOfPossession();

		for (int i = 0; i < 3; i++)
			row14.addElement(tmp[i]);

		teamStats.addElement(row14);

		JTable teamTable = new JTable(teamStats,gameHeaders);
		JTableHeader teamHeader = teamTable.getTableHeader();

		Font f3 = new Font("Lucida Sans Regular", Font.PLAIN, 14);
		teamTable.setFont(f3);
//		teamHeader.setFont(f3);

		// Disable auto resizing
		teamTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < 3; i++){
			TableColumn col = teamTable.getColumnModel().getColumn(i);

			int width = 100;
			if (i == 0){
				width = 200;
			}
			col.setPreferredWidth(width);
		}

		JPanel teamPanel = new JPanel();
		teamPanel.add(teamHeader, BorderLayout.NORTH);
		teamPanel.add(teamTable, BorderLayout.CENTER);

		teamTable.setEnabled(false);
		teamPanel.setOpaque(false);
		teamPanel.setBounds(170,50,400,teamTable.getRowHeight() * 17);
		teamStatistics.add(teamPanel);



		// For Scoring Summary


		JLabel vs = new JLabel("vs.");
		vs.setForeground(Color.WHITE);

		JPanel teamHeaders = new JPanel();
		teamHeaders.add(new JLabel(teamSlivers[awaySelection]), BorderLayout.WEST);
		teamHeaders.add(vs, BorderLayout.CENTER);
		teamHeaders.add(new JLabel(teamSlivers[homeSelection]), BorderLayout.EAST);

		JPanel boxScore = new JPanel();


		String [] quarters = {"", "1", "2", "3", "4", "OT", "F"};
		Object [][] scoringData = {
				{awayTeam.getName().replaceAll("_"," ") + " " + awayTeam.getNickname().replaceAll("_"," "), awayTeam.getStats().getScoring(1), awayTeam.getStats().getScoring(2), awayTeam.getStats().getScoring(3),
			awayTeam.getStats().getScoring(4), awayTeam.getStats().getScoring(5), field.getAwayScore()},
				{homeTeam.getName().replaceAll("_"," ") + " " + homeTeam.getNickname().replaceAll("_"," "), homeTeam.getStats().getScoring(1), homeTeam.getStats().getScoring(2), homeTeam.getStats().getScoring(3),
			homeTeam.getStats().getScoring(4), homeTeam.getStats().getScoring(5), field.getHomeScore()} };


		// If game ended in regulation, omit the OT information
		if (field.getQuarter() != 5){
			scoringData[0][5] = "--";
			scoringData[1][5] = "--";
		}


		JTable scoringTable = new JTable(scoringData, quarters);
		JTableHeader scoreHeader = scoringTable.getTableHeader();

		scoringTable.setFont(f2);
		scoreHeader.setFont(f2);

		// Disable auto resizing
		scoringTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


		for (int i = 0; i < 7; i++){
			TableColumn col = scoringTable.getColumnModel().getColumn(i);

			int width = 50;
			if (i == 0)
				width = 200;
			col.setPreferredWidth(width);
		}

		scoringTable.setShowGrid(true);

		teamHeaders.setBounds(175,10,350,50);
		teamHeaders.setOpaque(false);


		scoringSummary.add(teamHeaders);
		boxScore.add(scoreHeader, BorderLayout.NORTH);
		boxScore.add(scoringTable, BorderLayout.CENTER);

		boxScore.setBounds(100,70,500,80);
		boxScore.setOpaque(false);
		scoringSummary.add(boxScore);

		int vertValue = 150;
		int currentQuarter = 0;

		// For each drive that was a scoring drive, display its information
		for (int i = 0; i < drives.size(); i++){
			if (drives.elementAt(i).isScoringDrive()){
				Drive drive = drives.elementAt(i);

				if (drive.getQuarterScored() > currentQuarter){
					vertValue += 5;
					currentQuarter = drive.getQuarterScored();

					JLabel quarterLabel = new JLabel();
					switch(currentQuarter){
						case 1:	quarterLabel.setText("<html><p><u>1st Quarter&nbsp&nbsp&nbsp&nbsp</u></p></html>");
							break;
						case 2: quarterLabel.setText("<html><p><u>2nd Quarter&nbsp&nbsp&nbsp&nbsp</u></p></html>");
							break;
						case 3: quarterLabel.setText("<html><p><u>3rd Quarter&nbsp&nbsp&nbsp&nbsp</u></p></html>");
							break;
						case 4: quarterLabel.setText("<html><p><u>4th Quarter&nbsp&nbsp&nbsp&nbsp</u></p></html>");
							break;
						default:
							quarterLabel.setText("<html><p><u>Overtime&nbsp&nbsp&nbsp&nbsp</u></p></html>");
					}
					Font qFont = new Font("Arial", Font.BOLD, 16);
					quarterLabel.setBounds(10,vertValue,200,25);
					quarterLabel.setFont(qFont);
					quarterLabel.setForeground(Color.CYAN);
					scoringSummary.add(quarterLabel);
					vertValue += 35;
				}


				JLabel timeLabel = new JLabel(drive.getTeamScoring() + " - Qtr: " + drive.getQuarterScored() + " Time: " + drive.getTimeOfScoring());
				JLabel gameScoreLabel = new JLabel(drive.getScoringTeamScore() + " - " + drive.getDefendingTeamScore());
				JLabel playLabel = new JLabel(drive.getNumberOfPlays() + " plays for " + drive.getYardsCovered() + " yards.  " + drive.getTimeElapsed() + " elapsed.");
				JLabel scoreLabel = new JLabel();
				JLabel extraPointLabel = new JLabel();

				timeLabel.setForeground(Color.WHITE);
				gameScoreLabel.setForeground(Color.WHITE);
				playLabel.setForeground(Color.WHITE);
				scoreLabel.setForeground(Color.WHITE);
				extraPointLabel.setForeground(Color.WHITE);

				if (drive.getPlayType().equals("run") || drive.getPlayType().equals("field goal") )
					scoreLabel.setText(drive.getScoringDistance() + " yard " + drive.getPlayType() + "  by " + drive.getPrimaryPlayer());
				else if (drive.getPlayType().equals("pass"))
					scoreLabel.setText(drive.getScoringDistance() + " yard " + drive.getPlayType() + "  from " + drive.getPrimaryPlayer() + " to " + drive.getSecondaryPlayer());
				else if (drive.getPlayType().equals("punt return") || drive.getPlayType().equals("kick return"))
					scoreLabel.setText(drive.getScoringDistance() + " yard " + drive.getPlayType() + "  by " + drive.getPrimaryPlayer());
				else if (drive.getPlayType().equals("safety"))
					scoreLabel.setText("Defense scores on safety");

				if (drive.isExtraPointGood())
					extraPointLabel.setText("(" + drive.getExtraPointType() + " by " + drive.getExtraPointPlayer() + ")");
				else
					extraPointLabel.setText("(" + drive.getExtraPointType() + " by " + drive.getExtraPointPlayer() + " is no good)");	


				timeLabel.setBounds(10,vertValue,400,25);
				gameScoreLabel.setBounds(10,vertValue + 25, 80,25);
				playLabel.setBounds(100,vertValue + 25, 400, 25);
				scoreLabel.setBounds(100,vertValue + 50, 400, 25);

				// Add extra point information as long as it is not a field goal, safety, or overtime score
				if (!drive.getPlayType().equals("field goal") && !drive.getPlayType().equals("safety") && drive.getQuarterScored() < 5){
					extraPointLabel.setBounds(100,vertValue + 75, 300, 25);
					scoringSummary.add(extraPointLabel);
					vertValue += 100;
				}
				else
					vertValue += 75;

				scoringSummary.add(timeLabel);
				scoringSummary.add(gameScoreLabel);

				if (!drive.getPlayType().equals("safety"))
					scoringSummary.add(playLabel);

				scoringSummary.add(scoreLabel);

			}

		}

		// Set a background that looks like the image behind the tabbed pane to each panel
		BackgroundImage back1 = new BackgroundImage();
		BackgroundImage back2 = new BackgroundImage();
		BackgroundImage back3 = new BackgroundImage();


		teamStatistics.setPreferredSize(new Dimension(700,teamTable.getRowHeight() * 17 + 20));
		playerStatistics.setPreferredSize(new Dimension(700,yValue + 50));
		scoringSummary.setPreferredSize(new Dimension(700,vertValue));

		back1.setBounds(0,0,750,400);
		back2.setBounds(0,0,750,yValue + 50);
		back3.setBounds(0,0,750,vertValue);

		teamStatistics.add(back1);
		back1.setImage(new ImageIcon(getClass.getResource("images/system/statsBackground.png")).getImage());
		back1.setStretched(true);

		playerStatistics.add(back2);
		back2.setImage(new ImageIcon(getClass.getResource("images/system/statsBackground.png")).getImage());
		back2.setStretched(true);

		scoringSummary.add(back3);
		back3.setImage(new ImageIcon(getClass.getResource("images/system/statsBackground.png")).getImage());
		back3.setStretched(true);

		statsPane.add(new JScrollPane(teamStatistics), "Team Statistics");
		statsPane.add(new JScrollPane(playerStatistics), "Player Statistics");
		statsPane.add(new JScrollPane(scoringSummary), "Scoring Summary");

		statsPane.revalidate();

		finalScore.setBounds(0,5,800,60);
		statsPane.setBounds(10,100,750,400);

		main.add(statsPane);
		main.add(finalScore);
		main.setVisible(true);

		// Set return destination
		if (inTournament)
			usePlay.setText("To Tournament");
		else if (inSeason)
			usePlay.setText("To Season");
		else
			usePlay.setText("Menu");

		usePlay.setBounds(460,505,300,30);
		main.add(usePlay);

	}

	// To be removed
	void displayScoringDrive(){
		Drive scoringDrive = drives.elementAt(drives.size() - 1);

		System.out.println(scoringDrive.getTeamScoring() + " : " + scoringDrive.getQuarterScored() + "  " + scoringDrive.getTimeOfScoring());

		System.out.println(scoringDrive.getNumberOfPlays() + " plays for " + scoringDrive.getYardsCovered() + " yards");

		System.out.println("Type: " + scoringDrive.getPlayType() + "  by " + scoringDrive.getPrimaryPlayer() + "  aided by " + scoringDrive.getSecondaryPlayer() + " for " + scoringDrive.getScoringDistance() + " yards");

		if (scoringDrive.isExtraPointGood())
			System.out.println("Extra Point: " + scoringDrive.getExtraPointType() + " by " + scoringDrive.getExtraPointPlayer());
		else
			System.out.println(scoringDrive.getExtraPointType() + " by " + scoringDrive.getExtraPointPlayer() + " is no good");	

		System.out.println(scoringDrive.getScoringTeamScore() + " - " + scoringDrive.getDefendingTeamScore());	

	}


	/**
	Main function of class
	@param args [] - In-line arguments
	*/
	public static void main(String [] args){

		RedDog p = new RedDog();

		p.setUndecorated(true);
    		p.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

		p.setSize(800,600);
		p.setLocationRelativeTo(null);
		p.setTitle("RedDog Strategy Football");
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.setResizable(false);
		p.setVisible(true);
	}


	/**
	BackgroundImage class
	Allows for images to be displayed behind containers without interfering with them
	*/
	class BackgroundImage extends JPanel{
		private Image image;

		/** Hold value of property stretched. */
		private boolean stretched = true;

		/** Hold value of property xCoordinate */
		private int xCoordinate;

		/** Hold value of property yCoordinate */
		private int yCoordinate;

		/** Construct an empty image viewer */
		public BackgroundImage() {
		}

		/** Construct an image viewer for a specified Image object */
		public BackgroundImage(Image image){
			this.image = image;
		}

		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			if(image != null)
				if(isStretched())
					g.drawImage(image, xCoordinate, yCoordinate, getSize().width, getSize().height, this);
				else
					g.drawImage(image, xCoordinate, yCoordinate, this);
		}

		/** Return value of property image */
		public java.awt.Image getImage(){
			return image;

		}

		/** Set a new value for property image */
		public void setImage(java.awt.Image image){
			this.image = image;
			repaint();
		}
	

		/** Return value of property stretched */
		public boolean isStretched(){
			return stretched;
		}
	
		/** Set a new value for property stretched */
		public void setStretched(boolean stretched){
			this.stretched = stretched;
			repaint();
		}
	
		/** Return value of property xCoordinate */
		public int getXCoordinate(){
			return xCoordinate;
		}

		/** Set a new value for property xCoordinate */
		public void setXCoordinate(int xCoordinate){
			this.xCoordinate = xCoordinate;
			repaint();
		}

		/** Return value of property yCoordinate */
		public int getYCoordinate(){
			return yCoordinate;
		}

		/** Set a new value for property yCoordinate */
		public void setYCoordinate(int yCoordinate){
			this.yCoordinate = yCoordinate;
			repaint();
		}

	}

	/**
	CyclingSpinnerListModel
	Allows for a JSpinner to go from the front of the list to the end in one motion
	*/
	class CyclingSpinnerListModel extends SpinnerListModel {
	    Object firstValue, lastValue;
	    SpinnerModel linkedModel = null;

	    public CyclingSpinnerListModel(Object[] values) {
		super(values);
		firstValue = values[0];
		lastValue = values[values.length - 1];
	    }

	    public void setLinkedModel(SpinnerModel linkedModel) {
		this.linkedModel = linkedModel;
	    }

	    public Object getNextValue() {
		Object value = super.getNextValue();
		if (value == null) {
		    value = firstValue;
		    if (linkedModel != null) {
		        linkedModel.setValue(linkedModel.getNextValue());
		    }
		}
		return value;
	    }


	    public Object getPreviousValue() {


		Object value = super.getPreviousValue();
		if (value == null) {
		    value = lastValue;
		    if (linkedModel != null) {
		        linkedModel.setValue(linkedModel.getPreviousValue());
		    }
		}
		return value;
	    }
	}

	/**
	TimerListener class
	Applies actions for game clock and play clock
	*/
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			// For game timer
			if (e.getSource() == gameTimer){

				// Lower time by 1 second and add to current drive and offensive stats
				field.reduceTime(1,false);
				Drive d = drives.elementAt(drives.size() - 1);
				
				offensiveTeam.getStats().addTimeOfPossession(1);
				d.addTimeElapsed(1);

				// Stop clock if game has ended
				if (field.isGameOver()){
					gameTimer.stop();
					playClock.stop();

					updateScoreboard();
				}

				// Stop clock if quarter has ended
				if (field.getTimeLeft() == 900){
					gameTimer.stop();
					playClock.stop();

					updateScoreboard();
				}
				// Otherwise, update the scoreboard
				else
					updateScoreboard();

				

			}
			// The play clock
			else if (e.getSource() == playClock){

				// Lower play clock by 1 second
				playClockRemaining--;

				// Update display for play clock
				playClockAmount.setText(String.valueOf(playClockRemaining));

				// If play clock has run out, stop both clocks
				if (playClockRemaining == 0){
					gameTimer.stop();
					playClock.stop();
				}

			}
		}
	}
}
