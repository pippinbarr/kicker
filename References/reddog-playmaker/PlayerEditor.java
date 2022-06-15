import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.Vector;

public class PlayerEditor extends JFrame implements ActionListener{

	JPanel playerInfo = new JPanel();
	JPanel uniform = new JPanel();

	JTextField [] attributes = new JTextField[16];
	JTextField playerFName = new JTextField();
	JTextField playerLName = new JTextField();
	JTextField playerSchool = new JTextField();
	JTextField playerAge = new JTextField(2);
	JTextField playerExperience = new JTextField(2);
	JTextField playerHeightFeet = new JTextField();
	JTextField playerHeightInches = new JTextField();
	JTextField playerWeight = new JTextField(3);

	Vector<String> positions = new Vector<String>();
	JComboBox playerPosition = new JComboBox(positions);

	Vector<Integer> numbers = new Vector<Integer>();
	JComboBox playerNumber = new JComboBox(numbers);

	Vector<String> teams = new Vector<String>();
	JComboBox playerTeam = new JComboBox(teams);

	JLabel teamJersey = new JLabel();
	ImageIcon [] jerseys = new ImageIcon[31];


	JLabel overall = new JLabel();

	JButton savePlayer = new JButton("Save Player");
	JButton loadPlayer = new JButton("Load Player");
	JButton autoCreatePlayers = new JButton("Auto Create Players");
	JInternalFrame options;
	JDesktopPane pane = new JDesktopPane();

	public PlayerEditor(){

		pane.setLayout(null);

		setPlayerInfoPanel();
		setUniformPanel();
		setTeams();
		setJerseys();
		setPositions();

		playerInfo.setBounds(0,0,250,500);
		uniform.setBounds(250,0,250,500);

		pane.add(playerInfo);
		pane.add(uniform);

		teamJersey.setIcon(jerseys[0]);

		playerNumber.setEnabled(false);

		playerPosition.addActionListener(this);
		playerTeam.addActionListener(this);

		this.add(pane);
	}

	void setTeams(){
		teams.add("Free Agents");
		teams.add("Albuquerque Trailblazers");
		teams.add("Anchorage Eskimos");
		teams.add("Dakota Cougars");
		teams.add("Los Angeles Olympians");
		teams.add("Orlando Shrikes");
		teams.add("Portland Blue Jays");
		teams.add("Richmond Marines");
		teams.add("San Antonio Hornets");
		teams.add("Biloxi Firebirds");
		teams.add("Birmingham Ironmen");
		teams.add("Iowa Miners");
		teams.add("Upper Michigan Rabbits");
		teams.add("Wichita Jets");
		teams.add("Delaware Patriots");
		teams.add("Boise Pioneers");
		teams.add("Hawaii Royals");
		teams.add("Tacoma Aces");
		teams.add("Montana Wizards");
		teams.add("Salt Lake Owls");
		teams.add("Colorado Cadets");
		teams.add("Tulsa Road Runners");
		teams.add("New Haven Cobras");
		teams.add("London Thunder");
		teams.add("Dayton Skyhawks");
		teams.add("Lancaster Reds");
		teams.add("Baton Rouge Wildcats");
		teams.add("Amarillo Coyotes");
		teams.add("Oregon Atoms");
		teams.add("Charleston Cardinals");
		teams.add("Tahoe Beavers");
	}

	void setJerseys(){
		jerseys[0] = new ImageIcon("images/jerseys/Free_Agents.png");
		jerseys[1] = new ImageIcon("images/jerseys/Trailblazers.png");
		jerseys[2] = new ImageIcon("images/jerseys/Eskimos.png");
		jerseys[3] = new ImageIcon("images/jerseys/Cougars.png");
		jerseys[4] = new ImageIcon("images/jerseys/Olympians.png");
		jerseys[5] = new ImageIcon("images/jerseys/Shrikes.png");
		jerseys[6] = new ImageIcon("images/jerseys/Blue_Jays.png");
		jerseys[7] = new ImageIcon("images/jerseys/Marines.png");
		jerseys[8] = new ImageIcon("images/jerseys/Hornets.png");
		jerseys[9] = new ImageIcon("images/jerseys/Firebirds.png");
		jerseys[10] = new ImageIcon("images/jerseys/Ironmen.png");
		jerseys[11] = new ImageIcon("images/jerseys/Miners.png");
		jerseys[12] = new ImageIcon("images/jerseys/Rabbits.png");
		jerseys[13] = new ImageIcon("images/jerseys/Jets.png");
		jerseys[14] = new ImageIcon("images/jerseys/Patriots.png");
		jerseys[15] = new ImageIcon("images/jerseys/Pioneers.png");
		jerseys[16] = new ImageIcon("images/jerseys/Royals.png");
		jerseys[17] = new ImageIcon("images/jerseys/Aces.png");
		jerseys[18] = new ImageIcon("images/jerseys/Wizards.png");
		jerseys[19] = new ImageIcon("images/jerseys/Owls.png");
		jerseys[20] = new ImageIcon("images/jerseys/Cadets.png");
		jerseys[21] = new ImageIcon("images/jerseys/Road_Runners.png");
		jerseys[22] = new ImageIcon("images/jerseys/Cobras.png");
		jerseys[23] = new ImageIcon("images/jerseys/Thunder.png");
		jerseys[24] = new ImageIcon("images/jerseys/Skyhawks.png");
		jerseys[25] = new ImageIcon("images/jerseys/Reds.png");
		jerseys[26] = new ImageIcon("images/jerseys/Wildcats.png");
		jerseys[27] = new ImageIcon("images/jerseys/Coyotes.png");
		jerseys[28] = new ImageIcon("images/jerseys/Atoms.png");
		jerseys[29] = new ImageIcon("images/jerseys/Cardinals.png");
		jerseys[30] = new ImageIcon("images/jerseys/Beavers.png");

	}

	void setPositions(){
		positions.add("QB");
		positions.add("RB");
		positions.add("FB");
		positions.add("WR");
		positions.add("TE");
		positions.add("OL");
		positions.add("DL");
		positions.add("LB");
		positions.add("CB");
		positions.add("S");
		positions.add("K");
		positions.add("P");
	}


	public void actionPerformed(ActionEvent e){
		if (e.getSource() == playerPosition){
			createNumberList(false, 0, "");
			playerNumber.setEnabled(true);
		}
		else if (e.getSource() == playerTeam){
			int loc = playerTeam.getSelectedIndex();
			teamJersey.setIcon(jerseys[loc]);
		}
		else if (e.getSource() == savePlayer){
			savePlayer();
		}
		else if (e.getSource() == loadPlayer){

		}
		else if (e.getSource() == autoCreatePlayers){
			autoCreatePlayers();
		}

	}

	void setPlayerInfoPanel(){
		playerInfo.setLayout(null);
		JLabel [] attributeLabels = new JLabel[16];
		for (int i = 0; i < 16; i++){
			attributeLabels[i] = new JLabel();
			attributeLabels[i].setText(getLabelInfo(i));
		}

		for (int i = 0; i < 16; i++){
				attributes[i] = new JTextField();
			if (i < 8){
				attributeLabels[i].setBounds(5,255 + i*20, 40,20);
				attributes[i].setBounds(55,255 + i*20,40,20);
			}
			else{
				attributeLabels[i].setBounds(120,255 + (i-8) *20, 40,20);
				attributes[i].setBounds(170,255 + (i-8)*20,40,20);
			}
			playerInfo.add(attributeLabels[i]);
			playerInfo.add(attributes[i]);
		}

		autoCreatePlayers.setBounds(2,410,200,30);
		savePlayer.setBounds(2,440,120,30);
		loadPlayer.setBounds(127,440,120,30);

		playerInfo.add(savePlayer);
		playerInfo.add(loadPlayer);
		playerInfo.add(autoCreatePlayers);

		savePlayer.addActionListener(this);
		loadPlayer.addActionListener(this);
		autoCreatePlayers.addActionListener(this);

		JLabel firstName = new JLabel("First Name");
		JLabel lastName = new JLabel("Last Name");
		JLabel school = new JLabel("School");
		JLabel age = new JLabel("Age");
		JLabel experience = new JLabel("Experience");
		JLabel heightFeet = new JLabel("\'");
		JLabel heightInches = new JLabel("\"");
		JLabel weight = new JLabel("Wt:");
		JLabel position = new JLabel("Position");	//combobox
		JLabel number = new JLabel("Number");		//combobox

		firstName.setBounds(5,5,100,20);
		playerFName.setBounds(5,30,100,20);

		lastName.setBounds(140,5,100,20);
		playerLName.setBounds(140,30,100,20);

		school.setBounds(5,70,100,20);
		playerSchool.setBounds(5,100,100,20);

		age.setBounds(110,70,30,20);
		playerAge.setBounds(110,100,30,20);

		experience.setBounds(155,70,100,20);
		playerExperience.setBounds(170,100,30,20);

		heightFeet.setBounds(28,140,15,20);
		playerHeightFeet.setBounds(5,140,20,20);

		heightInches.setBounds(78,140,15,20);
		playerHeightInches.setBounds(55,140,20,20);

		weight.setBounds(100,140,30,20);
		playerWeight.setBounds(130,140,30,20);

		position.setBounds(5,190,100,20);
		playerPosition.setBounds(5,215,80,20);

		number.setBounds(155,190,90,20);
		playerNumber.setBounds(155,215,50,20);

		playerInfo.add(firstName);
		playerInfo.add(playerFName);
		playerInfo.add(lastName);
		playerInfo.add(playerLName);
		playerInfo.add(school);
		playerInfo.add(playerSchool);
		playerInfo.add(age);
		playerInfo.add(playerAge);
		playerInfo.add(experience);
		playerInfo.add(playerExperience);
		playerInfo.add(heightFeet);
		playerInfo.add(playerHeightFeet);
		playerInfo.add(heightInches);
		playerInfo.add(playerHeightInches);
		playerInfo.add(weight);
		playerInfo.add(playerWeight);
		playerInfo.add(position);
		playerInfo.add(playerPosition);
		playerInfo.add(number);
		playerInfo.add(playerNumber);

		playerNumber.setEnabled(false);
	}


	void setUniformPanel(){
		uniform.setLayout(null);
		playerTeam.setBounds(5,5,240,20);

		teamJersey.setBounds(0,30,250,470);
		overall.setBounds(300,450,100,50);

		uniform.add(playerTeam);
		uniform.add(teamJersey);
		uniform.add(overall);



	}

	String getLabelInfo(int idx){
		switch(idx){
			case 0: return "SPD";
			case 1: return "STR";
			case 2: return "STA";
			case 3: return "AGI";
			case 4: return "ACC";
			case 5: return "AWA";
			case 6: return "ELU";
			case 7: return "CAT";
			case 8: return "R/B";
			case 9: return "P/B";
			case 10: return "TCK";
			case 11: return "T/P";
			case 12: return "T/A";
			case 13: return "K/P";
			case 14: return "K/A";
			case 15: return "R/I";
		}
	return "";
	}


	void createNumberList(boolean autoCreate, int idx, String tm){
		
		int select = 0;

		if (!autoCreate){
			select = playerPosition.getSelectedIndex();
			System.out.println(select);
		}

		else{
			select = idx;
		}

		numbers.clear();

		switch(select){
			// QB
			case 0:{
				for (int i = 1; i < 20; i++)
					numbers.add(new Integer(i));
			break;
			}
			// RB
			case 1:{
				for (int i = 20; i < 50; i++)
					numbers.add(new Integer(i));
			break;
			}
			// FB
			case 2:{
				for (int i = 20; i < 40; i++)
					numbers.add(new Integer(i));
			break;
			}
			// WR
			case 3:{
				System.out.println("WR");
				for (int i = 10; i < 20; i++)
					numbers.add(new Integer(i));
				
				for (int i = 80; i < 90; i++)
					numbers.add(new Integer(i));
			break;
			}
			// TE
			case 4:{
				for (int i = 40; i < 50; i++)
					numbers.add(new Integer(i));
				for (int i = 80; i < 90; i++)
					numbers.add(new Integer(i));
			break;
			}
			// OL
			case 5:{
				for (int i = 50; i < 80; i++)
					numbers.add(new Integer(i));
			break;
			}
			// DL
			case 6:{
				for (int i = 50; i < 80; i++)
					numbers.add(new Integer(i));
				for (int i = 90; i < 100; i++)
					numbers.add(new Integer(i));
			break;
			}
			// LB
			case 7:{
				for (int i = 50; i < 60; i++)
					numbers.add(new Integer(i));
				for (int i = 90; i < 100; i++)
					numbers.add(new Integer(i));
			break;
			}
			// CB
			case 8:{
				for (int i = 40; i < 50; i++)
					numbers.add(new Integer(i));
			break;
			}
			// S
			case 9:{
				for (int i = 40; i < 50; i++)
					numbers.add(new Integer(i));
			break;
			}
			// K
			case 10:{
				for (int i = 1; i < 20; i++)
					numbers.add(new Integer(i));
			break;
			}
			// P
			case 11:{
				for (int i = 1; i < 20; i++)
					numbers.add(new Integer(i));
			break;
			}



		}
	//	System.out.println(numbers.capacity());


		String team = "";
		if (!autoCreate)
			team = playerTeam.getSelectedItem().toString();
		else
			team = tm;

		String prefix="";

		if (team == "Portland Blue Jays" || team == "Portland")
			prefix = "teams/Portland/";
		else if (team == "Anchorage Eskimos" || team == "Anchorage")
			prefix = "teams/Anchorage/";
		else if (team == "Delaware Patriots" || team == "Delaware")
			prefix = "teams/Delaware/";
		else if (team == "Richmond Marines" || team == "Richmond")
			prefix = "teams/Richmond/";
		else if (team == "Orlando Shrikes" || team == "Orlando")
			prefix = "teams/Orlando/";
		else if (team == "Biloxi Firebirds" || team == "Biloxi")
			prefix = "teams/Biloxi/";
		else if (team == "Birmingham Ironmen" || team == "Birmingham")
			prefix = "teams/Birmingham/";
		else if (team == "Iowa Miners" || team == "Iowa")
			prefix = "teams/Iowa/";
		else if (team == "Upper Michigan Rabbits" || team == "Upper Michigan")
			prefix = "teams/Upper_Michigan/";
		else if (team == "Dakota Cougars" || team == "Dakota")
			prefix = "teams/Dakota/";
		else if (team == "Wichita Jets" || team == "Wichita")
			prefix = "teams/Wichita/";
		else if (team == "San Antonio Hornets" || team == "San Antonio")
			prefix = "teams/San_Antonio/";
		else if (team == "Albuquerque Trailblazers" || team == "Albuquerque")
			prefix = "teams/Albuquerque/";
		else if (team == "Los Angeles Olympians" || team == "Los Angeles")
			prefix = "teams/Los_Angeles/";
		else if (team == "Boise Pioneers" || team == "Boise")
			prefix = "teams/Boise/";
		else if (team == "Hawaii Royals" || team == "Hawaii")
			prefix = "teams/Hawaii/";
		else if (team == "Tacoma Aces" || team == "Tacoma")
			prefix = "teams/Tacoma/";
		else if (team == "Montana Wizards" || team == "Montana")
			prefix = "teams/Montana/";
		else if (team == "Salt Lake Owls" || team == "Salt Lake")
			prefix = "teams/Salt_Lake/";
		else if (team == "Colorado Cadets" || team == "Colorado")
			prefix = "teams/Colorado/";
		else if (team == "Tulsa Road Runners" || team == "Tulsa")
			prefix = "teams/Tulsa/";
		else if (team == "Dayton Skyhawks" || team == "Dayton")
			prefix = "teams/Dayton/";
		else if (team == "London Thunder" || team == "London")
			prefix = "teams/London/";
		else if (team == "New Haven Cobras" || team == "New Haven")
			prefix = "teams/New_Haven/";
		else if (team == "Lancaster Reds" || team == "Lancaster")
			prefix = "teams/Lancaster/";
		else if (team == "Baton Rouge Wildcats" || team == "Baton Rouge")
			prefix = "teams/Baton_Rouge/";
		else if (team == "Amarillo Coyotes" || team == "Amarillo")
			prefix = "teams/Amarillo/";
		else if (team == "Oregon Atoms" || team == "Oregon")
			prefix = "teams/Oregon/";
		else if (team == "Charleston Cardinals" || team == "Charleston")
			prefix = "teams/Charleston/";
		else if (team == "Tahoe Beavers" || team == "Tahoe")
			prefix = "teams/Tahoe/";
		else if (team == "Free Agents" || team == "Agents")
			prefix = "teams/Agents/";
	//	System.out.println(prefix);

		try{
			java.util.Scanner in = new java.util.Scanner(new File(prefix + "players.tr"));

			while(in.hasNext()){
			//	String s = in.next();
				int number = in.nextInt();
		//		System.out.println(number);
			//	String position = in.next();

				if (numbers.contains(number))				
					numbers.removeElement(number);
			}

			in.close();

	//	for (int i = 0; i < numbers.size(); i++)
	//		System.out.println(numbers.elementAt(i));

		}catch(Exception e){e.printStackTrace();}

	}

 void setOverall(){
                double rating = 0.0;

                if (position == "QB"){
                        rating = (attributes[7] + attributes[8]) * 0.20 + (attributes[3] + attributes[6]) * 0.15 + (attributes[2] + attributes[5]) * 0.1;
                        overall = (int)(rating + injuryBonus() + weightPenalty(190,260));
                }
                else if (position == "HB"){
                        rating = (attributes[6] * 0.2) + (attributes[5] + attributes[4] + attributes[3]) * 0.15 + (attributes[0] * 0.1) + (attributes[1] + attributes[2] + attributes[12])
                                        * 0.05 + (attributes[13] + attributes[14]) * 0.025;
                        overall = (int)(rating + injuryBonus() + weightPenalty(180,285));
                }
                else if (position == "FB"){
                        rating = (attributes[3] * 0.2) + (attributes[1] + attributes[13] + attributes[14]) * 0.15 + (attributes[2] + attributes[4] + attributes[11]) * 0.1;
                        overall = (int)(rating + injuryBonus() + weightPenalty(200,305));
                }
                else if (position == "WR"){
                        rating = (attributes[0] + attributes[12]) * 0.2 + (attributes[3] + attributes[4]) * 0.15 + (attributes[5] + attributes[6]) * 0.1 + (attributes[2] * 0.05);
                        overall = (int)(rating + injuryBonus() + weightPenalty(180,250));
                }
                else if (position == "TE"){
                        rating = (attributes[12] + attributes[13] + attributes[14]) * 0.2 + (attributes[1] + attributes[2]) * 0.15 + (attributes[3] * 0.05);
                        overall = (int)(rating + injuryBonus() + weightPenalty(190,280));
                }
                else if (position == "OL"){
                        rating = (attributes[1] * 0.3) + (attributes[13] + attributes[14]) * 0.2 + (attributes[3] * 0.15) + (attributes[2] + attributes[11]) * 0.05;
                        overall = (int)(rating + injuryBonus() + weightPenalty(225,330));
                }
                else if (position == "DL"){
                        rating = (attributes[1] + attributes[14] + attributes[11]) * 0.2 + (attributes[3] * 0.15) + (attributes[2] + attributes[13]) * 0.1;
                        overall = (int)(rating + injuryBonus() + weightPenalty(225,330));
                }
                else if (position == "LB"){
                        rating = (attributes[11] + attributes[3] + attributes[13]) * 0.2 + (attributes[1] * 0.1) + (attributes[0] + attributes[2] + attributes[4] + attributes[12] + attributes[14]) * 0.05;
                        overall = (int)(rating + injuryBonus() + weightPenalty(225,305));
                }
                else if (position == "CB"){
                        rating = (attributes[0] + attributes[13]) * 0.2 + (attributes[11] + attributes[3]) * 0.15 + (attributes[4] * 0.1) + (attributes[12] + attributes[2] + attributes[5]) * 0.05;
                        overall = (int)(rating + injuryBonus() + weightPenalty(180,260));
                }
                else if (position == "S"){
                        rating = (attributes[13] + attributes[3] + attributes[12] + attributes[11]) * 0.15 + (attributes[0] + attributes[4] + attributes[5]) * 0.1 + (attributes[2] * 0.05);
                        overall = (int)(rating + injuryBonus() + weightPenalty(180,250));
                }
                else if (position == "K" || position == "P"){
                        rating = (attributes[9] + attributes[10]) * 0.4 + (attributes[3] * 0.15);
                        overall = (int)(rating + injuryBonus() + weightPenalty(180,240));
                }
        }

        double injuryBonus(){
                if (attributes[15] >= 75)
                        return ( (attributes[15] - 75) / 5);
                else
                        return ( attributes[15] / -15);
        }

        double weightPenalty(int min, int max){
                if (weight > max)
                        return ( ( weight - max ) / -10);
                else if (weight < min)
                        return ( (min - weight) / -10);
                else
                        return 0.0;
        }

void autoCreatePlayers(){
	options = new JInternalFrame();
	pane.add(options);
	JPanel main = new JPanel();
	main.setBackground(Color.WHITE);


	Object [] teams = {new String("Free Agents"), new String("Portland Blue Jays"), new String("Delaware Patriots"), new String("Richmond Marines"), new String("Orlando Shrikes"), new String("Iowa Miners"), new String("Biloxi Firebirds"), new String("Upper Michigan Rabbits"), new String("Birmingham Ironmen"), new String("San Antonio Hornets"), new String("Wichita Jets"), new String("Dakota Cougars"), new String("Albuquerque Trailblazers"), new String("Los Angeles Olympians"), new String("Anchorage Eskimos"), new String("Boise Pioneers"), new String("Hawaii Royals"),  new String("Tacoma Aces"), new String("Montana Wizards"), new String("Salt Lake Owls"), new String("Colorado Cadets"), new String("Tulsa Road Runners"), new String("New Haven Cobras"), new String("London Thunder"), new String("Dayton Skyhawks"), new String("Lancaster Reds"), new String("Baton Rouge Wildcats"), new String("Amarillo Coyotes"), new String("Oregon Atoms"), new String("Charleston Cardinals"), new String("Tahoe Beavers")};

	Object res = JOptionPane.showInputDialog(null, "Choose team to add players", "Choose Team", JOptionPane.PLAIN_MESSAGE, null, teams, teams[0]);

	if (res == null || res.toString().length() == 0){
		System.out.println("Hi");
		return;
	}




	String teamSelected = res.toString();
	System.out.println(teamSelected);
	String team = "";

//	if (teamSelected == "Portland Blue Jays")
//		team = "Portland";
//	else 

		if (teamSelected.equals("Portland Blue Jays"))
			team = "Portland";
		else if (teamSelected.equals("Anchorage Eskimos"))
			team = "Anchorage";
		else if (teamSelected.equals("Delaware Patriots"))
			team = "Delaware";
		else if (teamSelected.equals("Richmond Marines"))
			team = "Richmond";
		else if (teamSelected.equals("Orlando Shrikes"))
			team = "Orlando";
		else if (teamSelected.equals("Biloxi Firebirds"))
			team = "Biloxi";
		else if (teamSelected.equals("Birmingham Ironmen"))
			team = "Birmingham";
		else if (teamSelected.equals("Iowa Miners"))
			team = "Iowa";
		else if (teamSelected.equals("Upper Michigan Rabbits"))
			team = "Upper Michigan";
		else if (teamSelected.equals("Dakota Cougars"))
			team = "Dakota";
		else if (teamSelected.equals("Wichita Jets"))
			team = "Wichita";
		else if (teamSelected.equals("San Antonio Hornets"))
			team = "San Antonio";
		else if (teamSelected.equals("Albuquerque Trailblazers" ))
			team = "Albuquerque";
		else if (teamSelected.equals("Los Angeles Olympians"))
			team = "Los Angeles";
		else if (teamSelected.equals("Boise Pioneers"))
			team = "Boise";
		else if (teamSelected.equals("Hawaii Royals"))
			team = "Hawaii";
		else if (teamSelected.equals("Tacoma Aces"))
			team = "Tacoma";
		else if (teamSelected.equals("Salt Lake Owls"))
			team = "Salt Lake";
		else if (teamSelected.equals("Colorado Cadets"))
			team = "Colorado";
		else if (teamSelected.equals("Tulsa Road Runners"))
			team = "Tulsa";
		else if (teamSelected.equals("London Thunder"))
			team = "London";
		else if (teamSelected.equals("Montana Wizards"))
			team = "Montana";
		else if (teamSelected.equals("New Haven Cobras"))
			team = "New Haven";
		else if (teamSelected.equals("Dayton Skyhawks"))
			team = "Dayton";
		else if (teamSelected.equals("Lancaster Reds"))
			team = "Lancaster";
		else if (teamSelected.equals("Baton Rouge Wildcats"))
			team = "Baton Rouge";
		else if (teamSelected.equals("Amarillo Coyotes"))
			team = "Amarillo";
		else if (teamSelected.equals("Oregon Atoms"))
			team = "Oregon";
		else if (teamSelected.equals("Charleston Cardinals"))
			team = "Charleston";
		else if (teamSelected.equals("Tahoe Beavers"))
			team = "Tahoe";
		else if (teamSelected.equals("Free Agents"))
			team = "Agents";


	System.out.println(team);

	final JLabel [] playerID = new JLabel[12];

	JButton createPlayers = new JButton("Create Players");

	// Might place this as a global variable, rather than local
	final JComboBox [] numPlayers = new JComboBox[12];

	for (int i = 0; i < 12; i++){
		playerID[i] = new JLabel();
		numPlayers[i] = new JComboBox(new Object[]{new String("0"), new String("1"), new String("2"), new String("3"), new String("4"), new String("5")});
	}

	playerID[0].setText("QB");
	playerID[1].setText("HB");
	playerID[2].setText("FB");
	playerID[3].setText("WR");
	playerID[4].setText("TE");
	playerID[5].setText("OL");
	playerID[6].setText("DL");
	playerID[7].setText("LB");
	playerID[8].setText("CB");
	playerID[9].setText("S");
	playerID[10].setText("K");
	playerID[11].setText("P");




	main.setLayout(null);

	for (int i = 0; i < 12; i++){
		if (i < 6){
			playerID[i].setBounds(15, 100 + (40 * i), 50,30);
			numPlayers[i].setBounds(70, 100 + (40 * i), 80, 30);

		}
		else{
			playerID[i].setBounds(200,100 + (40 * (i - 6)), 50, 30);
			numPlayers[i].setBounds(250, 100 + (40 * (i - 6)), 80, 30);
		}
		main.add(playerID[i]);
		main.add(numPlayers[i]);
	}

	createPlayers.setBounds(50,350,200,30);
	main.add(createPlayers);

	options.add(main);

	options.setSize(350,400);
	options.setLocation(0,0);



	final String teamChosen = team;

	createPlayers.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			int total = 0;
			for (int i = 0; i < 12; i++){
				for (int j = 0; j < Integer.parseInt(numPlayers[i].getSelectedItem().toString()); j++){
					String pos = playerID[i].getText();
					// Vector of numbers will be set for this position and team for Vector<Integer> numbers
					createNumberList(true, i, teamChosen);
					PlayerAutoCreate pac = new PlayerAutoCreate(pos, teamChosen, numbers);
				//	String name = pac.getName();
					System.out.println(pos + " has been created for " + teamChosen);
					total++;
				}
			}
			JOptionPane.showMessageDialog(null, "", total + " players have been created for " + teamChosen, JOptionPane.PLAIN_MESSAGE);

			options.setVisible(false);
			options.dispose();
		}
	});
	options.setVisible(true);
	options.requestFocus();

}


	void savePlayer(){
		String prefix = "";

		String team = playerTeam.getSelectedItem().toString();
		int height = (Integer.parseInt(playerHeightFeet.getText()) * 12) + Integer.parseInt(playerHeightInches.getText());

		if (team.equals( "Portland Blue Jays"))
			prefix = "teams/Portland/";
		else if (team.equals( "Anchorage Eskimos"))
			prefix = "teams/Anchorage/";
		else if (team.equals( "Delaware Patriots"))
			prefix = "teams/Delaware/";
		else if (team.equals( "Richmond Marines"))
			prefix = "teams/Richmond/";
		else if (team.equals( "Orlando Shrikes"))
			prefix = "teams/Orlando/";
		else if (team.equals( "Biloxi Firebirds"))
			prefix = "teams/Biloxi/";
		else if (team.equals( "Birmingham Ironmen"))
			prefix = "teams/Birmingham/";
		else if (team.equals( "Iowa Miners"))
			prefix = "teams/Iowa/";
		else if (team.equals( "Upper Michigan Rabbits"))
			prefix = "teams/Upper_Michigan/";
		else if (team.equals( "Dakota Cougars"))
			prefix = "teams/Dakota/";
		else if (team.equals( "Wichita Jets"))
			prefix = "teams/Wichita/";
		else if (team.equals( "San Antonio Hornets"))
			prefix = "teams/San_Antonio/";
		else if (team.equals( "Albuquerque Trailblazers"))
			prefix = "teams/Albuquerque/";
		else if (team.equals( "Los Angeles Olympians"))
			prefix = "teams/Los_Angeles/";
		else if (team.equals( "Boise Pioneers"))
			prefix = "teams/Boise/";
		else if (team.equals( "Hawaii Royals"))
			prefix = "teams/Hawaii/";
		else if (team.equals( "Tacoma Aces"))
			prefix = "teams/Tacoma/";
		else if (team.equals( "Salt Lake Owls"))
			prefix = "teams/Salt_Lake/";
		else if (team.equals( "Colorado Cadets"))
			prefix = "teams/Colorado/";
		else if (team.equals( "London Thunder"))
			prefix = "teams/London/";
		else if (team.equals( "Dayton Skyhawks"))
			prefix = "teams/Dayton/";
		else if (team.equals( "Tulsa Road Runners"))
			prefix = "teams/Tulsa/";
		else if (team.equals( "New Haven Cobras"))
			prefix = "teams/New_Haven/";
		else if (team.equals( "Montana Wizards"))
			prefix = "teams/Montana/";
		else if (team.equals("Lancaster Reds"))
			prefix = "teams/Lancaster/";
		else if (team.equals("Baton Rouge Wildcats"))
			prefix = "teams/Baton_Rouge/";
		else if (team.equals("Amarillo Coyotes"))
			prefix = "teams/Amarillo/";
		else if (team.equals("Oregon Atoms"))
			prefix = "teams/Oregon/";
		else if (team.equals("Charleston Cardinals"))
			prefix = "teams/Charleston/";
		else if (team.equals("Tahoe Beavers"))
			prefix = "teams/Tahoe/";
		else if (team.equals("Free Agents"))
			prefix = "teams/Agents/";


		String filename = playerLName.getText().replaceAll("//s","_") + "-" + playerNumber.getSelectedItem().toString() + "-" + playerPosition.getSelectedItem().toString();

		try{
		File file = new File(prefix + filename + ".fp");

		PrintWriter out = new PrintWriter(file);

		out.println(playerFName.getText().replaceAll("//s","_") + " " + playerLName.getText().replaceAll("//s","_"));
		out.print(playerPosition.getSelectedItem().toString() + " " + playerTeam.getSelectedItem().toString().replaceAll("//s","_") + " ");
		out.println(playerNumber.getSelectedItem().toString());
		out.print(playerSchool.getText().replaceAll("//s","_") + " ");
		out.print(playerAge.getText() + " " + playerExperience.getText() + " ");
		out.println(height + " " + playerWeight.getText());
		for (int i = 0; i < 16; i++){
			out.print(attributes[i].getText() + " ");
		}
//		out.println(getOverall());

		out.close();
		}catch(Exception e){e.printStackTrace();}

		
		File teamFile = new File(prefix + "players.tr");
		boolean inFile = false;

		try{
		java.util.Scanner in = new java.util.Scanner(teamFile);


		while(in.hasNext()){
		//	String s = in.next();
			int num = in.nextInt();
		//	String pos = in.next();
			if (num == playerNumber.getSelectedItem()){
				inFile = true;
				break;
			}
		}
		in.close();

		}catch(Exception e){}


		if (!inFile){
			try{
				FileWriter out2 = new FileWriter(teamFile, true);
				out2.write(playerNumber.getSelectedItem() + "\n");
				out2.close();

			}catch(Exception e){}
		}

		File rosterFile = new File(prefix + "teamRoster.tr");
		inFile = false;

		try{
			java.util.Scanner in2 = new java.util.Scanner(rosterFile);

			while(in2.hasNext()){
				if (in2.next().equals(filename + ".fp"))
					inFile = true;
			}

			in2.close();
		}catch(Exception e){}

		if (!inFile){
			try{
				FileWriter out3 = new FileWriter(rosterFile, true);
				out3.write(filename + ".fp\n");
				out3.close();
			}catch(Exception e){}
		}

	}





	public static void main(String [] args){
		PlayerEditor pe = new PlayerEditor();
		pe.setTitle("Player Editor");
		pe.setSize(520,500);
		pe.setLocationRelativeTo(null);
		pe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pe.setVisible(true);
	}


}
