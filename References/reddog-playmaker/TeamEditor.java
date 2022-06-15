import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Vector;

public class TeamEditor extends JFrame implements ActionListener{

JPanel information = new JPanel();
JPanel helmet = new JPanel();
JPanel colors = new JPanel();

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
Vector<String> climates = new Vector<String>();
JComboBox teamClimate = new JComboBox(climates);
Vector<String> divisions = new Vector<String>();
JComboBox teamDivision = new JComboBox(divisions); //"Eastern - Atlantic", "Eastern - Central", "Western - Pacific", "Western - Midwest");

JLabel helmetDisplay = new JLabel(new ImageIcon("clearHelmet.gif"));
JLabel logo = new JLabel();

JButton saveTeam = new JButton("Save Team");
JButton loadTeam = new JButton("Load Team");


public TeamEditor(){

	this.setLayout(null);

	information.setBounds(0,0,250,500);
	helmet.setBounds(250,250,250,250);
	colors.setBounds(250,0,250,250);

	setInformationPanel();
	setHelmetPanel();
	setColorPanel();

	addClimates();
	addDivisions();

	this.add(information);
	this.add(helmet);
	this.add(colors);


}


void addClimates(){
	climates.add("New England");
	climates.add("Arctic");
	climates.add("Atlantic");
	climates.add("Pacific");
	climates.add("Mid-Atlantic");
	climates.add("Mid-West");
	climates.add("Gulf Coast");
	climates.add("Desert");
	climates.add("Pacific Northwest");
	climates.add("Domed");
}

void addDivisions(){
	divisions.add("Eastern - Atlantic");
	divisions.add("Eastern - Gulf");
	divisions.add("Eastern - Lakes");
	divisions.add("Western - Plains");
	divisions.add("Western - Pacific");
	divisions.add("Western - Mountain");
}

void setInformationPanel(){
	information.setLayout(null);

	JLabel locationLabel = new JLabel("Team Name:");
	JLabel nicknameLabel = new JLabel("Nickname:");
	JLabel cityLabel = new JLabel("City:");
	JLabel stateLabel = new JLabel("State:");
	JLabel abbreviationLabel = new JLabel("Abbrev.:");
	JLabel coachLabel = new JLabel("Coach:");
	JLabel stadiumLabel = new JLabel("Stadium:");
	JLabel climateLabel = new JLabel("Climate:");
	JLabel divisionLabel = new JLabel("Division:");

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
}


public void actionPerformed(ActionEvent e){
	if (e.getSource() == teamLogo){
		JFileChooser fileChooser = new JFileChooser();
		File selectedFile;
        	int returnValue = fileChooser.showOpenDialog(null);
        	if (returnValue == JFileChooser.APPROVE_OPTION){
        		selectedFile = fileChooser.getSelectedFile();
			ImageIcon picture = new ImageIcon(selectedFile.toString());
			setHelmetLogo(picture);
			teamLogo.setIcon(picture);
        	}
	}
	else if (e.getSource() == saveTeam){
		saveInformation();
	}
	else if (e.getSource() == loadTeam){
		loadInformation();
	}
	else if (e.getSource() == primaryColor){

		Color c = JColorChooser.showDialog(this, "Choose a color", primaryColor.getBackground());

		if (c != null)
			primaryColor.setBackground(c);			

	}
	else if (e.getSource() == secondaryColor){

		Color c = JColorChooser.showDialog(this, "Choose a color", secondaryColor.getBackground());

		if (c != null){
			secondaryColor.setBackground(c);			
			helmet.setBackground(secondaryColor.getBackground());
		}
	}

}
  
void setHelmetColor(){
	helmet.setBackground(primaryColor.getBackground());
}

void setHelmetLogo(ImageIcon img){
	logo.setIcon(img);
}


void setHelmetPanel(){
	helmet.setLayout(null);

	helmetDisplay.setBounds(0,0,250,250);
	logo.setBounds(45,65,80,80);


	helmet.add(helmetDisplay);
	helmet.add(logo);



	helmet.setBackground(secondaryColor.getBackground());
}

void setColorPanel(){
	colors.setLayout(null);

	JLabel primaryLabel = new JLabel("Primary Color:");
	JLabel secondaryLabel = new JLabel("Secondary Color:");
	JLabel logoLabel = new JLabel("Logo:");

	primaryColor.addActionListener(this);
	secondaryColor.addActionListener(this);
	teamLogo.addActionListener(this);

	primaryLabel.setBounds(5,10,150,20);

	primaryColor.setBounds(160,10,80,20);

	secondaryLabel.setBounds(5,50,150,20);

	secondaryColor.setBounds(160,50,80,20);

	logoLabel.setBounds(5,100,100,20);

	teamLogo.setBounds(160,100,70,70);

	primaryColor.setBackground(Color.BLACK);
	secondaryColor.setBackground(Color.WHITE);

	saveTeam.setBounds(2,230,120,20);
	loadTeam.setBounds(122,230,120,20);

	saveTeam.addActionListener(this);

	colors.add(saveTeam);
	colors.add(loadTeam);
	colors.add(primaryColor);
	colors.add(secondaryColor);
	colors.add(primaryLabel);
	colors.add(secondaryLabel);
	colors.add(logoLabel);
	colors.add(teamLogo);

}


void saveInformation(){

	String location = teamLocation.getText().replaceAll("\\s", "_");
	String nickname = teamNickname.getText().replaceAll("\\s", "_");
	String city = teamCity.getText().replaceAll("\\s", "_");
	String state = teamState.getText().replaceAll("\\s", "_");
	String abbrev = teamAbbrev.getText();
	String coach = teamCoach.getText().replaceAll("\\s", "_");
	String stadium = teamStadium.getText().replaceAll("\\s", "_");
	String climate = teamClimate.getSelectedItem().toString().replaceAll("\\s", "_");
	String division = teamDivision.getSelectedItem().toString().replaceAll("\\s", "_");

	if (teamLocation.getText().length() == 0 || teamNickname.getText().length() == 0 || teamCity.getText().length() == 0 || teamState.getText().length() == 0 
				|| teamAbbrev.getText().length() == 0 || teamCoach.getText().length() == 0 || teamStadium.getText().length() == 0){
		JOptionPane.showMessageDialog(null, "Incomplete", "All fields are required", JOptionPane.ERROR_MESSAGE);
		System.out.println("TRUE");
		return;
	} 
	else{

		String filename = teamLocation.getText().replaceAll("\\s", "_");
		File file = new File(filename + ".ft");
		try{
		PrintWriter out = new PrintWriter("teams/" + file);


		int domed = 0;
		if (stadiumDomed.isSelected())
			domed = 1;

		String logoFile = "";
		Color primary = primaryColor.getBackground();
		Color secondary = secondaryColor.getBackground();

		if (teamLogo.getIcon() == null)
			logoFile = "null";
		else
			logoFile = teamLogo.getIcon().toString();

		out.print(location + " ");
		out.println(nickname);
		out.println(city + " " + state + " " + abbrev);
		out.print(coach + " " + stadium + " " + domed + " " + climate + " ");
		out.println(division);
		out.println(logoFile);
		out.println(primary.getRed() + " " + primary.getGreen() + " " + primary.getBlue() + " ");
		out.println(secondary.getRed() + " " + secondary.getGreen() + " " + secondary.getBlue());

		out.close();
		System.out.println(filename + " has been saved\n");


		File directoryFile = new File("teams/teamListing.ft");
		boolean present = false;

		java.util.Scanner in = new java.util.Scanner(directoryFile);

		while(in.hasNext()){
			if (in.next() == (filename + ".ft")){
				present = true;
				break;
			}
		}
		in.close();

		if (!present){
			FileWriter out2 = new FileWriter(directoryFile, true);
			out2.write(filename + ".ft\n");
			out2.close();
		}
	}catch(Exception e){e.printStackTrace();}
	}
}


void loadInformation(){
	JFileChooser fileChooser = new JFileChooser();
	File selectedFile;
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION){
        	selectedFile = fileChooser.getSelectedFile();
		try{
		java.util.Scanner in = new java.util.Scanner(selectedFile);

		teamLocation.setText(in.next());
		teamNickname.setText(in.next());
		teamCity.setText(in.next());
		teamState.setText(in.next());
		teamAbbrev.setText(in.next());
		teamCoach.setText(in.next());
		teamStadium.setText(in.next());
		int domed = in.nextInt();
		if (domed == 1)
			stadiumDomed.setSelected(true);
		else
			stadiumDomed.setSelected(false);

		// Finishing these later...
		//teamClimate
		//teamDivision
		//teamLogo
		//teamPrimary
		//teamSecondary

	in.close();
	}catch(Exception e){}

	}
}

public static void main(String [] args){
	TeamEditor te = new TeamEditor();
	te.setTitle("Team Editor");
	te.setSize(500,500);
	te.setLocationRelativeTo(null);
	te.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	te.setVisible(true);
}


}
