import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*;
import java.util.*;
import java.text.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.plaf.*;
import javax.swing.UIManager.*;

public class HeadOffice extends JFrame implements ActionListener{

	JPanel teamEditorPane = new JPanel();
	JPanel playerEditorPane = new JPanel();
	JPanel playEditorPane = new JPanel();
		
	JButton saveTeam = new JButton("Save Team");
	JButton savePlayer = new JButton("Save Player");
	JButton savePlay = new JButton("Save Play");
	
	JButton loadTeam = new JButton("Load Team");
	JButton loadPlayer = new JButton("Load Player");
	
	String [] attributes = new String[16];
	JFormattedTextField [] attributeValues = new JFormattedTextField[16];
	JTextField [] playerInformation = new JTextField[7];
	JComboBox<String> playerPosition;
	JComboBox<Integer> playerNumber;

	public HeadOffice(){
		this.setLayout(null);
		JPanel main = new JPanel();
		main.setBounds(0,0,800,600);
		this.add(main);
		main.setLayout(null);
		
		initializeValues();
	
	
		JTabbedPane editorPane = new JTabbedPane();
		
		teamEditorPane.setLayout(null);
		playerEditorPane.setLayout(null);
		playEditorPane.setLayout(null);
		
		saveTeam.setBounds(10,480,200,20);
		loadTeam.setBounds(570,480,200,20);
		teamEditorPane.add(saveTeam);
		teamEditorPane.add(loadTeam);
		
		savePlayer.setBounds(10,480,200,20);
		loadPlayer.setBounds(570,480,200,20);
		playerEditorPane.add(savePlayer);
		playerEditorPane.add(loadPlayer);
	
		savePlay.setBounds(10,480,200,20);
		playEditorPane.add(savePlay);
		
		saveTeam.addActionListener(this);
		loadTeam.addActionListener(this);
		savePlayer.addActionListener(this);
		loadPlayer.addActionListener(this);
		savePlay.addActionListener(this);
		
		setTeamEditor();
		setPlayerEditor();
		setPlayEditor();
		
		editorPane.add("Create/Update Team", teamEditorPane);
		editorPane.add("Create/Update Players", playerEditorPane);
		editorPane.add("Create/Edit Playbook", playEditorPane);
	
		editorPane.setBounds(2,2,796,536);
		main.add(editorPane);
	}
	
	void initializeValues(){
		for (int i = 0; i < 16; i++){
			attributes[i] = new String();
			try{
				attributeValues[i] = new JFormattedTextField(new MaskFormatter("#*"));
			}catch(ParseException e){ e.printStackTrace();}
		}
		
		for (int i = 0; i < 7; i++)
			playerInformation[i] = new JTextField();
		
		attributes[0] = "SPD";
		attributes[1] = "STR";
		attributes[2] = "STA";
		attributes[3] = "AGI";
		attributes[4] = "ACC";
		attributes[5] = "AWA";
		attributes[6] = "ELU";
		attributes[7] = "CAT";
		attributes[8] = "R/B";
		attributes[9] = "P/B";
		attributes[10] = "TCK";
		attributes[11] = "T/P";
		attributes[12] = "T/A";
		attributes[13] = "K/P";
		attributes[14] = "K/A";
		attributes[15] = "R/I";
		
		String [] positions = {"QB", "HB", "FB", "WR", "TE", "OL", "DE", "LB", "CB", "S", "K", "P"};
		playerPosition = new JComboBox<String>(positions);
		
		Integer [] values = new Integer[100];
		for (int i = 0; i < 100; i++)
			values[i] = new Integer(i);
		
		playerNumber = new JComboBox<Integer>(values);
	}
	
	void setTeamEditor(){
	// Holds the basic team information
	JPanel information = new JPanel();
	
	// Holds jersey, helmet, logo and color data
	JPanel display = new JPanel();

	// Team information fields for input
	JTextField teamLocation = new JTextField();
	JTextField teamNickname = new JTextField();
	JTextField teamCity = new JTextField();
	JTextField teamState = new JTextField();
	JTextField teamAbbrev = new JTextField(3);
	JTextField teamCoach = new JTextField();
	JTextField teamStadium = new JTextField();
	JButton teamLogo = new JButton();
	JButton primaryColor = new JButton();
	JButton secondaryColor = new JButton();
	JCheckBox stadiumDomed = new JCheckBox("Domed?");
	JTextField teamClimate = new JTextField();
	Vector<String> divisions = new Vector<String>();
	JComboBox teamDivision = new JComboBox(divisions);

	JLabel helmetDisplay = new JLabel();
	JLabel logo = new JLabel();
	JLabel jersey = new JLabel();
	
	JButton saveTeam = new JButton("Save Team");
	JButton loadTeam = new JButton("Load Team");

	divisions.add("Eastern - Atlantic");
    divisions.add("Eastern - Gulf");
    divisions.add("Eastern - Lakes");
    divisions.add("Western - Plains");
    divisions.add("Western - Pacific");
    divisions.add("Western - Mountain");

	information.setLayout(null);
    JLabel locationLabel = new JLabel("Location:");
    JLabel nicknameLabel = new JLabel("Nickname:");
    JLabel cityLabel = new JLabel("City:");
    JLabel stateLabel = new JLabel("State:");
    JLabel abbreviationLabel = new JLabel("Abbr:");
    JLabel coachLabel = new JLabel("Coach:");
    JLabel stadiumLabel = new JLabel("Stadium:");
    JLabel climateLabel = new JLabel("Climate:");
    JLabel divisionLabel = new JLabel("Default Division:");

    locationLabel.setBounds(5,20,120,20);
    nicknameLabel.setBounds(130,20,120,20);

    teamLocation.setBounds(5,40,120,20);
    teamNickname.setBounds(130,40,120,20);

    cityLabel.setBounds(5,80,50,20);
    stateLabel.setBounds(130,80,50,20);
    abbreviationLabel.setBounds(190,80,80,20);

    teamCity.setBounds(5,120,120,20);
    teamState.setBounds(130,120,30,20);
    teamAbbrev.setBounds(190,120,30,20);

    coachLabel.setBounds(5,170,80,20);

    teamCoach.setBounds(5,190,200,20);

    stadiumLabel.setBounds(5,220,150,20);

    teamStadium.setBounds(5,240,200,20);

    stadiumDomed.setBounds(5,260,100,20);

    climateLabel.setBounds(5,320,80,20);
    divisionLabel.setBounds(5,370,80,20);

    teamClimate.setBounds(5,350,200,20);
    teamDivision.setBounds(5,390,200,20);

    information.add(locationLabel);
    information.add(nicknameLabel);
    information.add(teamLocation);
    information.add(teamNickname);
    information.add(cityLabel);
    information.add(stateLabel);
    information.add(abbreviationLabel);
    information.add(teamCity);
    information.add(teamState);
    information.add(teamAbbrev);
    information.add(coachLabel);
    information.add(teamCoach);
    information.add(stadiumLabel);
    information.add(teamStadium);
    information.add(stadiumDomed);
    information.add(climateLabel);
    information.add(divisionLabel);
    information.add(teamClimate);
    information.add(teamDivision);	
	
	display.setLayout(null);
	
	JLabel primaryLabel = new JLabel("Primary Color:");
    JLabel secondaryLabel = new JLabel("Secondary Color:");
    JLabel logoLabel = new JLabel("Logo:");

//    primaryColor.addActionListener(this);
//    secondaryColor.addActionListener(this);
//    teamLogo.addActionListener(this);

    primaryLabel.setBounds(5,10,150,20);
    primaryColor.setBounds(160,10,80,20);

    secondaryLabel.setBounds(5,50,150,20);
    secondaryColor.setBounds(160,50,80,20);

    logoLabel.setBounds(5,100,100,20);
    teamLogo.setBounds(160,100,200,200);

    primaryColor.setBackground(Color.BLACK);
    secondaryColor.setBackground(Color.WHITE);

    saveTeam.setBounds(110,460,120,20);
    loadTeam.setBounds(240,460,120,20);

    helmetDisplay.setBounds(100,200,250,250);
	
//    saveTeam.addActionListener(this);

    display.add(saveTeam);
    display.add(loadTeam);
    display.add(primaryColor);
    display.add(secondaryColor);
    display.add(primaryLabel);
    display.add(secondaryLabel);
    display.add(logoLabel);
    display.add(teamLogo);
	display.add(helmetDisplay);

	information.setBounds(0,0,375,500);
	display.setBounds(0,0,375,500);
	teamEditorPane.add(information);
	teamEditorPane.add(display);	
	}
	
	void setPlayerEditor(){
		JLabel name = new JLabel("Name");
		JLabel school = new JLabel("School");
		JLabel age = new JLabel("Age");
		JLabel experience = new JLabel("Experience");
		JLabel height = new JLabel("Height");
		JLabel weight = new JLabel("Weight");
		JLabel position = new JLabel("Position");
		JLabel number = new JLabel("Number");
		
		// Name information
		name.setBounds(20,20,60,25);
		playerInformation[0].setBounds(85,20,100,25);
		playerInformation[1].setBounds(190,20,100,25);
		
		school.setBounds(20,50,60,25);
		playerInformation[2].setBounds(85,50,205,25);
		
		age.setBounds(20,80,40,25);
		playerInformation[3].setBounds(85,80,40,25);
		experience.setBounds(165,80,80,25);
		playerInformation[4].setBounds(250,80,40,25);
		
		weight.setBounds(20,110,60,25);
		playerInformation[5].setBounds(85,110,40,25);
		height.setBounds(165,110,110,25);
		playerInformation[6].setBounds(250,110,40,25);
		
		position.setBounds(20,140,60,25);
		playerPosition.setBounds(85,140,60,25);
		number.setBounds(165,140,60,25);
		playerNumber.setBounds(240,140,50,25);
	
		for (int i = 0; i < 16; i++){
			JLabel valueLabel = new JLabel(attributes[i]);
			if (i %2 == 0){
				valueLabel.setBounds(35, 220 + (25 * (i / 2)), 40,25);
				attributeValues[i].setBounds(85,220 + (25 * (i / 2)),40,25);
			}
			else{
				valueLabel.setBounds(145, 220 + (25 * (i - 1)/2), 40,25);
				attributeValues[i].setBounds(195,220 + (25 * (i - 1)/2),40,25);
			}
		
			playerEditorPane.add(attributeValues[i]);
			playerEditorPane.add(valueLabel);
		}
		playerEditorPane.add(name);
		playerEditorPane.add(school);
		playerEditorPane.add(age);
		playerEditorPane.add(experience);
		playerEditorPane.add(height);
		playerEditorPane.add(weight);
		playerEditorPane.add(position);
		playerEditorPane.add(playerPosition);
		playerEditorPane.add(number);
		playerEditorPane.add(playerNumber);
		
		
		for (int i = 0; i < 7; i++)
			playerEditorPane.add(playerInformation[i]);
	}
	
	void setPlayEditor(){
	
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == saveTeam){
			System.out.println("Saving a team");
		}
		else if (e.getSource() == loadTeam){
			System.out.println("Team is being loaded");
		}
		else if (e.getSource() == savePlayer){
			System.out.println("Saving a player");
		}
		else if (e.getSource() == loadPlayer){
			System.out.println("Player is being loaded");
		}
		else if (e.getSource() == savePlay){
			System.out.println("Saving a play");
		}
	
	}

	public static void main(String [] args){
		try{
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
				if ("Nimbus".equals(info.getName())){
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}catch(Exception e){System.out.println("Did not work");}
		HeadOffice h = new HeadOffice();
		
		h.setSize(800,600);
		h.setLocationRelativeTo(null);
		h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		h.setTitle("RedDog SF Head Office Editor");
		h.setVisible(true);
	}

}