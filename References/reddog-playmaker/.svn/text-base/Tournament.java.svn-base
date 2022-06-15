import java.util.Vector;
import javax.swing.*;
import java.awt.*;


public class Tournament{

	int numberOfTeams;
	Vector<String> competingTeams;
	int totalRounds;
	int currentRound;
  int [][] roundSchedule;
	boolean [] roundCompleted;
	int [][] gameScores;
  JLabel [][] gameScoreLabels;
	String [][] games;
	int numberOfGames;
	boolean [] gameCompleted;
	boolean tournamentComplete = false;
	String tournamentChampion = "";
	JLayeredPane tournamentBracket = new JLayeredPane();

	JLabel [] teamNames = new JLabel[31];



	public Tournament(int numTeams, Vector<String> teamsList){
		currentRound = 0;
		numberOfTeams = numTeams;
		competingTeams = teamsList;

		for (int i = 0; i < 31; i++){
			teamNames[i] = new JLabel();
			teamNames[i].setForeground(Color.WHITE);
		}

		determineNumberOfRounds();

		numberOfGames = competingTeams.size() - 1;
    roundSchedule = new int[numberOfRounds][2];

		gameCompleted = new boolean[numberOfGames];
		gameScores = new int[numberOfGames][2];
    gameScoreLabels = new JLabel[numberOfGames][2];
		games = new String[numberOfGames][3];
      
    for (int i = 0; i < numberOfGames; i++){
      gameScores[i][0] = 0;
      gameScores[i][1] = 0;
      gameScoreLabels[i][0] = new JLabel();
      gameScoreLabels[i][1] = new JLabel();
   }

  int monthStart = 4; // Actually 5 for May
  int dayStart = 10;

		initializeGames();
    setTournamentSchedule(monthStart, dayStart);
	}
   
  public Tournament(){
  
  }

	void determineNumberOfRounds(){
		if (numberOfTeams < 4)
			totalRounds = 1;
		else if (numberOfTeams < 6)
			totalRounds = 2;
		else if (numberOfTeams < 12)
			totalRounds = 3;
		else
			totalRounds = 4;

		roundCompleted = new boolean[totalRounds];
	}

	void initializeGames(){
		switch(numberOfTeams){
			case 2:{
				games[0][0] = competingTeams.elementAt(0);
				games[0][1] = competingTeams.elementAt(1);

				break;
			}
			case 4:{
				games[0][0] = competingTeams.elementAt(0);
				games[0][1] = competingTeams.elementAt(1);
				games[1][0] = competingTeams.elementAt(2);
				games[1][1] = competingTeams.elementAt(3);
				break;
			}
			case 6:{
				// First Round
				games[0][0] = competingTeams.elementAt(1);
				games[0][1] = competingTeams.elementAt(2);
				games[1][0] = competingTeams.elementAt(4);
				games[1][1] = competingTeams.elementAt(5);

				// Teams with byes
				games[2][0] = competingTeams.elementAt(0);
				games[3][0] = competingTeams.elementAt(3);
				break;
			}
			case 8:{
				games[0][0] = competingTeams.elementAt(0);
				games[0][1] = competingTeams.elementAt(1);
				games[1][0] = competingTeams.elementAt(2);
				games[1][1] = competingTeams.elementAt(3);
				games[2][0] = competingTeams.elementAt(4);
				games[2][1] = competingTeams.elementAt(5);
				games[3][0] = competingTeams.elementAt(6);
				games[3][1] = competingTeams.elementAt(7);
				break;
			}
			case 12:{
				games[0][0] = competingTeams.elementAt(2);
				games[0][1] = competingTeams.elementAt(3);
				games[1][0] = competingTeams.elementAt(4);
				games[1][1] = competingTeams.elementAt(5);
				games[2][0] = competingTeams.elementAt(8);
				games[2][1] = competingTeams.elementAt(9);
				games[3][0] = competingTeams.elementAt(10);
				games[3][1] = competingTeams.elementAt(11);

				games[4][1] = competingTeams.elementAt(0);
				games[5][0] = competingTeams.elementAt(1);
				games[6][1] = competingTeams.elementAt(6);
				games[7][0] = competingTeams.elementAt(7);
				break;
			}
			case 16:{
				games[0][0] = competingTeams.elementAt(0);
				games[0][1] = competingTeams.elementAt(1);
				games[1][0] = competingTeams.elementAt(2);
				games[1][1] = competingTeams.elementAt(3);
				games[2][0] = competingTeams.elementAt(4);
				games[2][1] = competingTeams.elementAt(5);
				games[3][0] = competingTeams.elementAt(6);
				games[3][1] = competingTeams.elementAt(7);
				games[4][0] = competingTeams.elementAt(8);
				games[4][1] = competingTeams.elementAt(9);
				games[5][0] = competingTeams.elementAt(10);
				games[5][1] = competingTeams.elementAt(11);
				games[6][0] = competingTeams.elementAt(12);
				games[6][1] = competingTeams.elementAt(13);
				games[7][0] = competingTeams.elementAt(14);
				games[7][1] = competingTeams.elementAt(15);

				break;
			}
		}
	}


  void setTournamentSchedule(int monthStart, int dayStart){
    Calendar calendar = Calendar.getInstance();
    calendar.set(2011,monthStart,dayStart);
    
    for (int i = 0; i < numberOfRounds; i++){
      roundSchedule[i][0] = calendar.get(Calendar.MONTH);
      roundSchedule[i][1] = calendar.get(Calendar.DATE);
      
      calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 7);
    }
  
    for (int i = 0; i < numberOfGames; i++){
      games[i][2] = String.valueOf( (int)(Math.random() * 8 + 10) + ":00");
    }
  
  }
  
  Calendar getGameDay(){
    Calendar calendar = Calendar.getInstance();
    calendar.set(2011, roundSchedule[currentRound][0], roundSchedule[currentRound][1]);
    
    return calendar;
  }

	Vector<Vector<Object>> getCurrentRoundGames(){
		Vector<Vector<Object>> currentRoundGames = new Vector<Vector<Object>>();
		currentRoundGames.clear();

		switch(currentRound){
			case 0:{
				if (numberOfTeams == 2){
					Object [] tmp = {"", games[0][0], games[0][1], "", games[0][2]};
					if (gameCompleted[0]){
						tmp[0] = String.valueOf(gameScores[0][0]);
						tmp[3] = String.valueOf(gameScores[0][1]);
					}
					Vector<Object> tmp2 = new Vector<Object>();
					for (int i = 0; i < 5; i++)
						tmp2.addElement(tmp[i]);
					currentRoundGames.addElement(tmp2);
				}
				else if (numberOfTeams == 4 || numberOfTeams == 6){
					for (int i = 0; i < 2; i++){
						Object [] tmp = {"", games[i][0], games[i][1], "", games[i][2]};
						if (gameCompleted[i]){
							tmp[0] = String.valueOf(gameScores[i][0]);
							tmp[3] = String.valueOf(gameScores[i][1]);
						}
						Vector<Object> tmp2 = new Vector<Object>();
						for (int j = 0; j < 5; j++)
							tmp2.addElement(tmp[j]);
						currentRoundGames.addElement(tmp2);
					}
				}
				else if (numberOfTeams == 8 || numberOfTeams == 12){
					for (int i = 0; i < 4; i++){
						Object [] tmp = {"", games[i][0], games[i][1], "", games[i][2]};
						if (gameCompleted[i]){
							tmp[0] = String.valueOf(gameScores[i][0]);
							tmp[3] = String.valueOf(gameScores[i][1]);
						}
						Vector<Object> tmp2 = new Vector<Object>();
						for (int j = 0; j < 5; j++)
							tmp2.addElement(tmp[j]);
						currentRoundGames.addElement(tmp2);
					}
				}
				else if (numberOfTeams == 16){
					for (int i = 0; i < 8; i++){
						Object [] tmp = {"", games[i][0], games[i][1], "", games[i][2]};
						if (gameCompleted[i]){
							tmp[0] = String.valueOf(gameScores[i][0]);
							tmp[3] = String.valueOf(gameScores[i][1]);
						}
						Vector<Object> tmp2 = new Vector<Object>();
						for (int j = 0; j < 5; j++)
							tmp2.addElement(tmp[j]);
						currentRoundGames.addElement(tmp2);
					}
				}
				break;
			}
			case 1:{
				if (numberOfTeams == 4){
					Object [] tmp = {"", games[2][0], games[2][1], "", games[2][2]};
					if (gameCompleted[2]){
						tmp[0] = String.valueOf(gameScores[2][0]);
						tmp[3] = String.valueOf(gameScores[2][1]);
					}
					Vector<Object> tmp2 = new Vector<Object>();
					for (int i = 0; i < 5; i++)
						tmp2.addElement(tmp[i]);
					currentRoundGames.addElement(tmp2);
				}
				else if (numberOfTeams == 6){
					for (int i = 2; i < 4; i++){
						Object [] tmp = {"", games[i][0], games[i][1], "", games[i][2]};
						if (gameCompleted[i]){
							tmp[0] = String.valueOf(gameScores[i][0]);
							tmp[3] = String.valueOf(gameScores[i][1]);
						}
						Vector<Object> tmp2 = new Vector<Object>();
						for (int j = 0; j < 5; j++)
							tmp2.addElement(tmp[j]);
						currentRoundGames.addElement(tmp2);
					}
				}
				else if (numberOfTeams == 8){
					for (int i = 4; i < 6; i++){
						Object [] tmp = {"", games[i][0], games[i][1], "", games[i][2]};
						if (gameCompleted[i]){
							tmp[0] = String.valueOf(gameScores[i][0]);
							tmp[3] = String.valueOf(gameScores[i][1]);
						}
						Vector<Object> tmp2 = new Vector<Object>();
						for (int j = 0; j < 5; j++)
							tmp2.addElement(tmp[j]);
						currentRoundGames.addElement(tmp2);
					}
				}
				else if (numberOfTeams == 12){
					for (int i = 4; i < 8; i++){
						Object [] tmp = {"", games[i][0], games[i][1], "", games[i][2]};
						if (gameCompleted[i]){
							tmp[0] = String.valueOf(gameScores[i][0]);
							tmp[3] = String.valueOf(gameScores[i][1]);
						}
						Vector<Object> tmp2 = new Vector<Object>();
						for (int j = 0; j < 5; j++)
							tmp2.addElement(tmp[j]);
						currentRoundGames.addElement(tmp2);
					}
				}
				else if (numberOfTeams == 16){
					for (int i = 8; i < 12; i++){
						Object [] tmp = {"", games[i][0], games[i][1], "", games[i][2]};
						if (gameCompleted[i]){
							tmp[0] = String.valueOf(gameScores[i][0]);
							tmp[3] = String.valueOf(gameScores[i][1]);
						}
						Vector<Object> tmp2 = new Vector<Object>();
						for (int j = 0; j < 5; j++)
							tmp2.addElement(tmp[j]);
						currentRoundGames.addElement(tmp2);
					}
				}
				break;
			}
			case 2:{
				// Final round for 6/8 team tournament, semifinals for 12/16 team tournaments
				if (numberOfTeams == 6){
					Object [] tmp = {"", games[4][0], games[4][1], "", games[4][2]};
					if (gameCompleted[4]){
						tmp[0] = String.valueOf(gameScores[4][0]);
						tmp[3] = String.valueOf(gameScores[4][1]);
					}
					Vector<Object> tmp2 = new Vector<Object>();
					for (int i = 0; i < 5; i++)
						tmp2.addElement(tmp[i]);
					currentRoundGames.addElement(tmp2);
				}
				else if (numberOfTeams == 8){
					Object [] tmp = {"", games[6][0], games[6][1], "", games[6][2]};
					if (gameCompleted[6]){
						tmp[0] = String.valueOf(gameScores[6][0]);
						tmp[3] = String.valueOf(gameScores[6][1]);
					}
					Vector<Object> tmp2 = new Vector<Object>();
					for (int i = 0; i < 5; i++)
						tmp2.addElement(tmp[i]);
					currentRoundGames.addElement(tmp2);
				}
				else if (numberOfTeams == 12){
					for (int i = 8; i < 10; i++){
						Object [] tmp = {"", games[i][0], games[i][1], "", games[i][2]};
						if (gameCompleted[i]){
							tmp[0] = String.valueOf(gameScores[i][0]);
							tmp[3] = String.valueOf(gameScores[i][1]);
						}
						Vector<Object> tmp2 = new Vector<Object>();
						for (int j = 0; j < 5; j++)
							tmp2.addElement(tmp[j]);
						currentRoundGames.addElement(tmp2);
					}
				}
				else if (numberOfTeams == 16){
					for (int i = 12; i < 14; i++){
						Object [] tmp = {"", games[i][0], games[i][1], "", games[i][2]};
						if (gameCompleted[i]){
							tmp[0] = String.valueOf(gameScores[i][0]);
							tmp[3] = String.valueOf(gameScores[i][1]);
						}
						Vector<Object> tmp2 = new Vector<Object>();
						for (int j = 0; j < 5; j++)
							tmp2.addElement(tmp[j]);
						currentRoundGames.addElement(tmp2);
					}
				}
				break;
			}
			case 3:{
				// Final round for 12/16 team tournaments
				if (numberOfTeams == 12){
					Object [] tmp = {"", games[10][0], games[10][1], "", games[10][2]};
					if (gameCompleted[10]){
						tmp[0] = String.valueOf(gameScores[10][0]);
						tmp[3] = String.valueOf(gameScores[10][1]);
					}
					Vector<Object> tmp2 = new Vector<Object>();
					for (int i = 0; i < 5; i++)
						tmp2.addElement(tmp[i]);
					currentRoundGames.addElement(tmp2);
				}
				else if (numberOfTeams == 16){
					Object [] tmp = {"", games[14][0], games[14][1], "", games[14][2]};
					if (gameCompleted[14]){
						tmp[0] = String.valueOf(gameScores[14][0]);
						tmp[3] = String.valueOf(gameScores[14][1]);
					}
					Vector<Object> tmp2 = new Vector<Object>();
					for (int i = 0; i < 5; i++)
						tmp2.addElement(tmp[i]);
					currentRoundGames.addElement(tmp2);
				}
				break;
			}


		}


		return currentRoundGames;
	}

	void updateTournament(String team1, String team2, int score1, int score2){
		int gameIndex = 0;



		for (int i = 0; i < numberOfGames; i++){

			if (games[i][0].equals(team1.replaceAll("_"," ")) && games[i][1].equals(team2.replaceAll("_"," "))){

				gameIndex = i;
				break;
			}
		}

		gameScores[gameIndex][0] = score1;
		gameScores[gameIndex][1] = score2;

		String winner = "";

		if (gameScores[gameIndex][0] > gameScores[gameIndex][1])
			winner = team1.replaceAll("_"," ");
		else
			winner = team2.replaceAll("_"," ");

		gameCompleted[gameIndex] = true;
		checkForRoundCompletion();
		updateForNextRound(gameIndex, winner);

	}

	void updateForNextRound(int gameIndex, String winner){
		if (tournamentComplete){
			tournamentChampion = winner.replaceAll("_"," ");
			return;
		}
		else{
			switch(numberOfTeams){
				case 4:{
					if (gameIndex == 0)
						games[2][0] = winner;
					else if (gameIndex == 1)
						games[2][1] = winner;
					break;
				}
				case 6:{
					if (gameIndex == 0)
						games[2][1] = winner;
					else if (gameIndex == 1)
						games[3][1] = winner;
					else if (gameIndex == 2)
						games[4][0] = winner;
					else if (gameIndex == 3)
						games[4][1] = winner;

					break;
				}
				case 8:{
					if (gameIndex == 0)
						games[4][0] = winner;
					else if (gameIndex == 1)
						games[4][1] = winner;
					else if (gameIndex == 2)
						games[5][0] = winner;
					else if (gameIndex == 3)
						games[5][1] = winner;
					else if (gameIndex == 4)
						games[6][0] = winner;
					else if (gameIndex == 5)
						games[6][1] = winner;

					break;
				}
				case 12:{
					if (gameIndex == 0)
						games[4][0] = winner;
					else if (gameIndex == 1)
						games[5][1] = winner;
					else if (gameIndex == 2)
						games[6][0] = winner;
					else if (gameIndex == 3)
						games[7][1] = winner;
					else if (gameIndex == 4)
						games[8][0] = winner;
					else if (gameIndex == 5)
						games[8][1] = winner;
					else if (gameIndex == 6)
						games[9][0] = winner;
					else if (gameIndex == 7)
						games[9][1] = winner;
					else if (gameIndex == 8)
						games[10][0] = winner;
					else if (gameIndex == 9)
						games[10][1] = winner;

					break;
				}
				case 16:{
					if (gameIndex == 0)
						games[8][0] = winner;
					else if (gameIndex == 1)
						games[8][1] = winner;
					else if (gameIndex == 2)
						games[9][0] = winner;
					else if (gameIndex == 3)
						games[9][1] = winner;
					else if (gameIndex == 4)
						games[10][0] = winner;
					else if (gameIndex == 5)
						games[10][1] = winner;
					else if (gameIndex == 6)
						games[11][0] = winner;
					else if (gameIndex == 7)
						games[11][1] = winner;
					else if (gameIndex == 8)
						games[12][0] = winner;
					else if (gameIndex == 9)
						games[12][1] = winner;
					else if (gameIndex == 10)
						games[13][0] = winner;
					else if (gameIndex == 11)
						games[13][1] = winner;
					else if (gameIndex == 12)
						games[14][0] = winner;
					else if (gameIndex == 13)
						games[14][1] = winner;

					break;
				}
			}
		}
	}


	void checkForRoundCompletion(){
		switch(numberOfTeams){
			case 2:{
				roundCompleted[0] = true;
				tournamentComplete = true;
				break;
			}
			case 4:{
				if (gameCompleted[0] && gameCompleted[1]){
					roundCompleted[0] = true;
					currentRound = 1;
				}
				if (gameCompleted[2]){
					roundCompleted[1] = true;
					tournamentComplete = true;
				}
				break;
			}
			case 6:{
				if (gameCompleted[0] && gameCompleted[1]){
					roundCompleted[0] = true;
					currentRound = 1;
				}
				if (gameCompleted[2] && gameCompleted[3]){
					roundCompleted[1] = true;
					currentRound = 2;
				}
				if (gameCompleted[4]){
					roundCompleted[2] = true;
					tournamentComplete = true;
				}
				break;
			}
			case 8:{
				if (gameCompleted[6]){
					roundCompleted[2] = true;
					tournamentComplete = true;
				}
				else if(gameCompleted[5] && gameCompleted[4]){
					roundCompleted[1] = true;
					currentRound = 2;
				}
				else if(gameCompleted[0] && gameCompleted[1] && gameCompleted[2] && gameCompleted[3]){
					roundCompleted[0] = true;
					currentRound = 1;
				}
				else
					currentRound = 0;

				break;
			}
			case 12:{
				if (gameCompleted[10]){
					roundCompleted[3] = true;
					tournamentComplete = true;
				}
				else if (gameCompleted[8] && gameCompleted[9]){
					roundCompleted[2] = true;
					currentRound = 3;
				}
				else if (gameCompleted[4] && gameCompleted[5] && gameCompleted[6] && gameCompleted[7]){
					roundCompleted[1] = true;
					currentRound = 2;
				}
				else if (gameCompleted[0] && gameCompleted[1] && gameCompleted[2] && gameCompleted[3]){
					roundCompleted[0] = true;
					currentRound = 1;
				}
				else
					currentRound = 0;
				break;
			}
			case 16:{
				if (gameCompleted[14]){
					roundCompleted[3] = true;
					tournamentComplete = true;
				}
				else if (gameCompleted[12] && gameCompleted[13]){
					roundCompleted[2] = true;
					currentRound = 3;
				}
				else if (gameCompleted[8] && gameCompleted[9] && gameCompleted[10] && gameCompleted[11]){
					roundCompleted[1] = true;
					currentRound = 2;
				}
				else if (gameCompleted[0] && gameCompleted[1] && gameCompleted[2] && gameCompleted[3] &&
					gameCompleted[4] && gameCompleted[5] && gameCompleted[6] && gameCompleted[7]){
					roundCompleted[0] = true;
					currentRound = 1;
				}
				else
					currentRound = 0;
				break;
			}
		}
	}

	boolean isTournamentComplete(){
		return tournamentComplete;
	}

	JPanel getTournamentBracket(){
		tournamentBracket.setPreferredSize(new Dimension(800,380));
		tournamentBracket.setLayout(null);
		tournamentBracket.removeAll();

		ImageIcon [] brackets = {null, null, new ImageIcon(getClass().getResource("images/system/2teamTournament.png")), null, new ImageIcon(getClass().getResource("images/system/4teamTournament.png")), null,
			new ImageIcon(getClass().getResource("images/system/6teamTournament.png")), null, new ImageIcon(getClass().getResource("images/system/8teamTournament.png")), null, null, null, 
			new ImageIcon(getClass().getResource("images/system/12teamTournament.png")), null, null, null, new ImageIcon(getClass().getResource("images/system/16teamTournament.png"))};

		JLabel bracketImage = new JLabel(brackets[numberOfTeams]);


		bracketImage.setBounds(0,0,800,380);

		tournamentBracket.add(bracketImage);

		setBracketValues();

		tournamentBracket.moveToBack(bracketImage);

		tournamentBracket.setOpaque(false);

		JPanel bracket = new JPanel();
		bracket.add(tournamentBracket, BorderLayout.CENTER);
		bracket.setOpaque(false);

		return bracket;
	}

	void setBracketValues(){
     for (int i = 0; i < numberOfGames; i++){
       if (gameCompleted[i]){
         gameScoreLabels[i][0].setText(gameScores[i][0]);
         gameScoreLabels[i][1].setText(gameScores[i][1]);
       }
     }
   
		switch(numberOfTeams){
			case 2:{
				// Final
				teamNames[0].setText(games[0][0]);				
				teamNames[1].setText(games[0][1]);

				teamNames[2].setText(tournamentChampion);

				teamNames[0].setBounds(180,160,115,15);
        gameScoreLabels[0][0].setBounds(220,180,30,20);
				teamNames[1].setBounds(505,160,115,15);
        gameScoreLabels[0][1].setBounds(545,180,30,20);
        
				teamNames[2].setBounds(345,195,115,15);
            

				for (int i = 0; i < 3; i++){
					tournamentBracket.add(teamNames[i]);
				}

        tournamentBracket.add(gameScoreLabels[0][0]);
        tournamentBracket.add(gameScoreLabels[0][1]);

				break;
			}
			case 4:{
				// Semi-Final 1
				teamNames[0].setText(games[0][0]);				
				teamNames[1].setText(games[0][1]);

				// Semi-Final 2
				teamNames[2].setText(games[1][0]);				
				teamNames[3].setText(games[1][1]);

				// Final
				teamNames[4].setText(games[2][0]);				
				teamNames[5].setText(games[2][1]);

				teamNames[6].setText(tournamentChampion);


				teamNames[0].setBounds(150,75,115,15);
        gameScoreLabels[0][0].setBounds(190,95,30,15);
				teamNames[1].setBounds(150,240,115,15);
        gameScoreLabels[0][1].setBounds(190,220,30,15);  
				teamNames[2].setBounds(535,75,115,15);
        gameScoreLabels[1][0].setBounds(575,95,30,15);  
				teamNames[3].setBounds(535,240,115,15);
        gameScoreLabels[1][1].setBounds(575,220,30,15);  
				teamNames[4].setBounds(270,160,115,15);
        gameScoreLabels[2][0].setBounds(310,180,30,15); 
				teamNames[5].setBounds(415,160,115,15);
        gameScoreLabels[2][1].setBounds(455,180,30,15);
        
				teamNames[6].setBounds(345,275,115,15);


				for (int i = 0; i < 7; i++)
					tournamentBracket.add(teamNames[i]);
               
  for (int i = 0; i < 3; i++){
    tournamentBracket.add(gameScoreLabels[i][0]);
    tournamentBracket.add(gameScoreLabels[i][1]);
  }

				break;
			}
			case 6:{
				// Quarterfinal 1
				teamNames[0].setText(games[0][0]);				
				teamNames[1].setText(games[0][1]);

				// Quarterfinal 2
				teamNames[2].setText(games[1][0]);				
				teamNames[3].setText(games[1][1]);

				// Semi-Final 1
				teamNames[4].setText(games[2][0]);				
				teamNames[5].setText(games[2][1]);

				// Semi-Final 2
				teamNames[6].setText(games[3][0]);				
				teamNames[7].setText(games[3][1]);

				// Final
				teamNames[8].setText(games[4][0]);				
				teamNames[9].setText(games[4][1]);

				teamNames[10].setText(tournamentChampion);

				teamNames[0].setBounds(30,200,115,15);
				teamNames[1].setBounds(30,280,115,15);
				teamNames[2].setBounds(660,200,115,15);
				teamNames[3].setBounds(660,280,115,15);
				teamNames[4].setBounds(150,75,115,15);
				teamNames[5].setBounds(150,240,115,15);
				teamNames[6].setBounds(535,75,115,15);
				teamNames[7].setBounds(535,240,115,15);
				teamNames[8].setBounds(270,160,115,15);
				teamNames[9].setBounds(415,160,115,15);
				teamNames[10].setBounds(345,275,115,15);

				for (int i = 0; i < 11; i++)
					tournamentBracket.add(teamNames[i]);

				break;
			}
			case 8:{
				// Quarterfinal 1
				teamNames[0].setText(games[0][0]);				
				teamNames[1].setText(games[0][1]);

				// Quarterfinal 2
				teamNames[2].setText(games[1][0]);				
				teamNames[3].setText(games[1][1]);

				// Quarterfinal 3
				teamNames[4].setText(games[2][0]);				
				teamNames[5].setText(games[2][1]);

				// Quarterfinal 4
				teamNames[6].setText(games[3][0]);				
				teamNames[7].setText(games[3][1]);

				// Semi-Final 1
				teamNames[8].setText(games[4][0]);				
				teamNames[9].setText(games[4][1]);

				// Semi-Final 2
				teamNames[10].setText(games[5][0]);				
				teamNames[11].setText(games[5][1]);

				// Final
				teamNames[12].setText(games[6][0]);				
				teamNames[13].setText(games[6][1]);

				teamNames[14].setText(tournamentChampion);


				teamNames[0].setBounds(30,40,115,15);
				teamNames[1].setBounds(30,120,115,15);
				teamNames[2].setBounds(30,200,115,15);
				teamNames[3].setBounds(30,280,115,15);
				teamNames[4].setBounds(660,40,115,15);
				teamNames[5].setBounds(660,120,115,15);
				teamNames[6].setBounds(660,200,115,15);
				teamNames[7].setBounds(660,280,115,15);
				teamNames[8].setBounds(150,75,115,15);
				teamNames[9].setBounds(150,240,115,15);
				teamNames[10].setBounds(535,75,115,15);
				teamNames[11].setBounds(535,240,115,15);
				teamNames[12].setBounds(270,160,115,15);
				teamNames[13].setBounds(415,160,115,15);
				teamNames[14].setBounds(345,275,115,15);

				for (int i = 0; i < 15; i++)
					tournamentBracket.add(teamNames[i]);

				break;
			}
			case 12:{
				// Opening Round 1
				teamNames[0].setText(games[0][0]);				
				teamNames[1].setText(games[0][1]);

				// Opening Round 2
				teamNames[2].setText(games[1][0]);				
				teamNames[3].setText(games[1][1]);

				// Opening Round 3
				teamNames[4].setText(games[2][0]);				
				teamNames[5].setText(games[2][1]);

				// Opening Round 4
				teamNames[6].setText(games[3][0]);				
				teamNames[7].setText(games[3][1]);

				// Quarterfinal 1
				teamNames[8].setText(games[4][0]);				
				teamNames[9].setText(games[4][1]);

				// Quarterfinal 2
				teamNames[10].setText(games[5][0]);				
				teamNames[11].setText(games[5][1]);

				// Quarterfinal 3
				teamNames[12].setText(games[6][0]);				
				teamNames[13].setText(games[6][1]);

				// Quarterfinal 4
				teamNames[14].setText(games[7][0]);				
				teamNames[15].setText(games[7][1]);

				// Semi-Final 1
				teamNames[16].setText(games[8][0]);				
				teamNames[17].setText(games[8][1]);

				// Semi-Final 2
				teamNames[18].setText(games[9][0]);				
				teamNames[19].setText(games[9][1]);

				// Final
				teamNames[20].setText(games[10][0]);				
				teamNames[21].setText(games[10][1]);

				teamNames[22].setText(tournamentChampion);


				teamNames[0].setBounds(20,20,115,15);
				teamNames[1].setBounds(20,60,115,15);
				teamNames[2].setBounds(20,260,115,15);
				teamNames[3].setBounds(20,305,115,15);
				teamNames[4].setBounds(665,20,115,15);
				teamNames[5].setBounds(665,60,115,15);
				teamNames[6].setBounds(665,260,115,15);
				teamNames[7].setBounds(665,305,115,15);
				teamNames[8].setBounds(145,40,115,15);
				teamNames[9].setBounds(145,120,115,15);
				teamNames[10].setBounds(145,200,115,15);
				teamNames[11].setBounds(145,280,115,15);
				teamNames[12].setBounds(540,40,115,15);
				teamNames[13].setBounds(540,120,115,15);
				teamNames[14].setBounds(540,200,115,15);
				teamNames[15].setBounds(540,280,115,15);
				teamNames[16].setBounds(270,75,115,15);
				teamNames[17].setBounds(270,240,115,15);
				teamNames[18].setBounds(415,75,115,15);
				teamNames[19].setBounds(415,240,115,15);
				teamNames[20].setBounds(270,160,115,15);
				teamNames[21].setBounds(420,160,115,15);
				teamNames[22].setBounds(345,315,115,15);

				for (int i = 0; i < 23; i++)
					tournamentBracket.add(teamNames[i]);
				break;
			}
			case 16:{
				// Opening Round 1
				teamNames[0].setText(games[0][0]);				
				teamNames[1].setText(games[0][1]);

				// Opening Round 2
				teamNames[2].setText(games[1][0]);				
				teamNames[3].setText(games[1][1]);

				// Opening Round 3
				teamNames[4].setText(games[2][0]);				
				teamNames[5].setText(games[2][1]);

				// Opening Round 4
				teamNames[6].setText(games[3][0]);				
				teamNames[7].setText(games[3][1]);

				// Opening Round 5
				teamNames[8].setText(games[4][0]);				
				teamNames[9].setText(games[4][1]);

				// Opening Round 6
				teamNames[10].setText(games[5][0]);				
				teamNames[11].setText(games[5][1]);

				// Opening Round 7
				teamNames[12].setText(games[6][0]);				
				teamNames[13].setText(games[6][1]);

				// Opening Round 8
				teamNames[14].setText(games[7][0]);				
				teamNames[15].setText(games[7][1]);

				// Quarterfinal 1
				teamNames[16].setText(games[8][0]);				
				teamNames[17].setText(games[8][1]);

				// Quarterfinal 2
				teamNames[18].setText(games[9][0]);				
				teamNames[19].setText(games[9][1]);

				// Quarterfinal 3
				teamNames[20].setText(games[10][0]);				
				teamNames[21].setText(games[10][1]);

				// Quarterfinal 4
				teamNames[22].setText(games[11][0]);				
				teamNames[23].setText(games[11][1]);

				// Semi-Final 1
				teamNames[24].setText(games[12][0]);				
				teamNames[25].setText(games[12][1]);

				// Semi-Final 2
				teamNames[26].setText(games[13][0]);				
				teamNames[27].setText(games[13][1]);

				// Final
				teamNames[28].setText(games[14][0]);				
				teamNames[29].setText(games[14][1]);

				teamNames[30].setText(tournamentChampion);


				teamNames[0].setBounds(20,20,115,15);
				teamNames[1].setBounds(20,60,115,15);
				teamNames[2].setBounds(20,100,115,15);
				teamNames[3].setBounds(20,140,115,15);
				teamNames[4].setBounds(20,180,115,15);
				teamNames[5].setBounds(20,220,115,15);
				teamNames[6].setBounds(20,260,115,15);
				teamNames[7].setBounds(20,305,115,15);
				teamNames[8].setBounds(665,20,115,15);
				teamNames[9].setBounds(665,60,115,15);
				teamNames[10].setBounds(665,100,115,15);
				teamNames[11].setBounds(665,140,115,15);
				teamNames[12].setBounds(665,180,115,15);
				teamNames[13].setBounds(665,220,115,15);
				teamNames[14].setBounds(665,260,115,15);
				teamNames[15].setBounds(665,305,115,15);
				teamNames[16].setBounds(145,40,115,15);
				teamNames[17].setBounds(145,120,115,15);
				teamNames[18].setBounds(145,200,115,15);
				teamNames[19].setBounds(145,280,115,15);
				teamNames[20].setBounds(540,40,115,15);
				teamNames[21].setBounds(540,120,115,15);
				teamNames[22].setBounds(540,200,115,15);
				teamNames[23].setBounds(540,280,115,15);
				teamNames[24].setBounds(270,75,115,15);
				teamNames[25].setBounds(270,240,115,15);
				teamNames[26].setBounds(415,75,115,15);
				teamNames[27].setBounds(415,240,115,15);
				teamNames[28].setBounds(270,160,115,15);
				teamNames[29].setBounds(420,160,115,15);
				teamNames[30].setBounds(345,315,115,15);

				for (int i = 0; i < 31; i++)
					tournamentBracket.add(teamNames[i]);

				break;
			}
		}
	}

void saveTournament(){

        String filepath = JOptionPane.showInputDialog(null, "Select the file name to save tournament as:", "Save Tournament", JOptionPane.PLAIN_MESSAGE);
        
        try{
                File f = new File("saveData/" + filepath + ".tourn");
                PrintWriter out = new PrintWriter(f);
        
                out.println(numberOfTeams + " " + numberOfGames + " " + totalRounds + " " + currentRound);
                
                for (int i = 0; i < totalRounds; i++){
                        int complete = 0;
                        if (roundCompleted[i])
                                complete = 1;
                        out.print(complete + " ");              
                }
                
                for (int i = 0; i < totalRounds; i++)
                  out.println(roundSchedule[i][0] + " " + roundSchedule[i][1]);
                
                for (int i = 0; i < numberOfGames; i++){
                        int complete = 0;
                        if (gameCompleted[i])
                                complete = 1;
                
                        out.println(games[i][0] + " " + games[i][1] + " " + games[i][2] + gameScores[i][0] + " " + gameScores[i][1] + " " + complete);
                }
        
                out.close();
        }catch(Exception e){}
}

void loadTournament(String file){
        String filename = file;
        
        try{
                Scanner in = new Scanner(filename);
                numberOfTeams = in.nextInt();
                numberOfGames = in.nextInt();
                totalRounds = in.nextInt();
                currentRound = in.nextInt();
                
                
                for (int i = 0; i < totalRounds; i++){
                        if (in.nextInt() == 1)
                                roundComplete[i] = true;
                        else
                                roundComplete[i] = false;
                }
                
          for (int i = 0; i < totalRounds; i++){
            roundSchedule[i][0] = in.nextInt();
            roundSchedule[i][1] = in.nextInt();
          }
                
                for (int i = 0; i < numberOfGames; i++){
                        games[i][0] = in.next();
                        games[i][1] = in.next();
                        games[i][2] = in.next();
                        gameScores[i][0] = in.nextInt();
                        gameScores[i][1] = in.nextInt();
                
                        if (in.nextInt() == 1)
                                gameCompleted[i] = true;
                        else
                                gameCompleted[i] = false
                
                }
                
                in.close();
        }catch(Exception e){}

  }
}
