import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class PlayerAutoCreate{

	String position = "";
	int overall = 0;
	int [] attributes = new int[16];
	int number = 0;
	int height = 0;
	int weight = 0;

	int maxWeight = 0;
	int minWeight = 0;


	String school = "";
	String [] schoolList = {"", "Alabama", "Texas", "USC", "Ohio_State" ,"Penn_State" ,"Nebraska" ,"Michigan" ,"Michigan_State" ,"Iowa" ,"Texas_A&M" ,"Miami" ,
	"Florida" ,"Florida_State" ,"Boise_State" ,"TCU" ,"Texas_Tech" ,"Kansas" ,"Kansas_State" ,"Pittsburgh" ,"Maryland" ,"Syracuse" ,"California" ,"Tennessee" ,
	"West_Virginia" ,"LSU" ,"Missouri" ,"Ole_Miss" ,"Washington" ,"Hawaii" ,"Tulsa" ,"Tulsa_State" ,"Arkansas" ,"Notre_Dame" ,"BYU" ,"Utah" ,"Colorado" ,
	"Minnesota" ,"Wisconsin" ,"Bowling_Green" ,"Grambling" ,"Appalachian_State" ,"Youngstown_State" ,"Villinova" ,"Florida_A&M" ,"UCLA" ,"Massachusetts" ,
	"Eastern_Kentucky" ,"Lehigh" ,"Montana" ,"Richmond" ,"Delaware" ,"James_Madison" ,"NW_Missouri_St." ,"Grand_Valley_St." ,"Minnesota-Duluth" ,"Valdosta_St." ,"North_Dakota" ,
	"Delta_St." ,"Bloomsburg" ,"Kutztown" ,"Indiana_(PA)" ,"Carson-Neuman" ,"Pittsburg_State" ,"Mount_Union" ,
	"Wisconsin-Whitewater" ,"Sioux_Falls" ,"Carroll" ,"St._Francis" ,"Georgetown"};
	int age = 0;
	int experience = 0;
//	Team team;
	String team = "";
	Vector<Integer> availableNumbers = new Vector<Integer>();


	public PlayerAutoCreate(String pos, String team, Vector<Integer> numbers){
		position = pos;
		this.team = team;
		availableNumbers = numbers;
		setNumber();
		setSpecs();
		setSchool();
		setAttributes();
		setOverall();
		savePlayer();
	}


	void setNumber(){
		int total = availableNumbers.size();

		int location = (int)(Math.random() * total);

		number = availableNumbers.elementAt(location);
	}

	void setSpecs(){
		// Height is between 67" (5' 7") and 84" (7' 0")
		height = (int)(Math.random() * 17 + 67);

		getWeightRange();

		// Weight is +/- 25 pounds within weight range
		weight = (int)(Math.random() * (maxWeight - minWeight + 50) + (minWeight - 25));

		// Age range = 21 - 39
		age = (int)(Math.random() * 18 + 21);

		experience = age - 21;

		if (age > 23){
			// Up to 3 years deducted from experience (differences in age when starting)
			int diff = (int)(Math.random() * 3);
			experience -= diff;
		}


	}


	void getWeightRange(){
		if (position == "QB"){
			minWeight = 190;
			maxWeight = 260;
		}
		if (position == "HB"){
			minWeight = 180;
			maxWeight = 285;
		}
		if (position == "FB"){
			minWeight = 200;
			maxWeight = 305;
		}
		if (position == "WR"){
			minWeight = 180;
			maxWeight = 250;
		}
		if (position == "TE"){
			minWeight = 190;
			maxWeight = 280;
		}
		if (position == "OL"){
			minWeight = 225;
			maxWeight = 330;
		}
		if (position == "DL"){
			minWeight = 225;
			maxWeight = 330;
		}
		if (position == "LB"){
			minWeight = 225;
			maxWeight = 305;
		}
		if (position == "CB"){
			minWeight = 180;
			maxWeight = 260;
		}
		if (position == "S"){
			minWeight = 180;
			maxWeight = 250;
		}
		if (position == "K"){
			minWeight = 180;
			maxWeight = 240;
		}
		if (position == "P"){
			minWeight = 180;
			maxWeight = 240;
		}

	}

	void setSchool(){
		// Range = 1 - 70
		int location = (int)(Math.random() * 69 + 1);

		school = schoolList[location];

	}

	// FIX THIS FUNCTION...SWITCH NUMBERS (S, K, P already done)
	void setAttributes(){
		if (position == "QB"){
			attributes[0] = (int)(Math.random() * 20 + 60);
			attributes[1] = (int)(Math.random() * 65 + 10);
			attributes[2] = (int)(Math.random() * 40 + 55);
			attributes[3] = (int)(Math.random() * 45 + 50);
			attributes[4] = (int)(Math.random() * 65 + 10);
			attributes[5] = (int)(Math.random() * 65 + 10);
			attributes[6] = (int)(Math.random() * 40 + 55);
			attributes[7] = (int)(Math.random() * 25 + 70);
			attributes[8] = (int)(Math.random() * 20 + 65);
			attributes[9] = (int)(Math.random() * 65 + 10);
			attributes[10] = (int)(Math.random() * 65 + 10);
			attributes[11] = (int)(Math.random() * 65 + 10);
			attributes[12] = (int)(Math.random() * 65 + 10);
			attributes[13] = (int)(Math.random() * 65 + 10);
			attributes[14] = (int)(Math.random() * 65 + 10);
			attributes[15] = (int)(Math.random() * 39 + 60);
		}
		if (position == "HB"){
			attributes[0] = (int)(Math.random() * 45 + 50);
			attributes[1] = (int)(Math.random() * 65 + 10);
			attributes[2] = (int)(Math.random() * 35 + 60);
			attributes[3] = (int)(Math.random() * 35 + 60);
			attributes[4] = (int)(Math.random() * 25 + 70);
			attributes[5] = (int)(Math.random() * 35 + 60);
			attributes[6] = (int)(Math.random() * 40 + 55);
			attributes[7] = (int)(Math.random() * 65 + 10);
			attributes[8] = (int)(Math.random() * 65 + 10);
			attributes[9] = (int)(Math.random() * 65 + 10);
			attributes[10] = (int)(Math.random() * 65 + 10);
			attributes[11] = (int)(Math.random() * 65 + 10);
			attributes[12] = (int)(Math.random() * 40 + 50);
			attributes[13] = (int)(Math.random() * 40 + 40);
			attributes[14] = (int)(Math.random() * 40 + 40);
			attributes[15] = (int)(Math.random() * 39 + 60);
		}
		if (position == "FB"){
			attributes[0] = (int)(Math.random() * 65 + 10);
			attributes[1] = (int)(Math.random() * 30 + 65);
			attributes[2] = (int)(Math.random() * 30 + 50);
			attributes[3] = (int)(Math.random() * 35 + 60);
			attributes[4] = (int)(Math.random() * 35 + 45);
			attributes[5] = (int)(Math.random() * 30 + 40);
			attributes[6] = (int)(Math.random() * 65 + 10);
			attributes[7] = (int)(Math.random() * 65 + 10);
			attributes[8] = (int)(Math.random() * 65 + 10);
			attributes[9] = (int)(Math.random() * 65 + 10);
			attributes[10] = (int)(Math.random() * 65 + 10);
			attributes[11] = (int)(Math.random() * 65 + 10);
			attributes[12] = (int)(Math.random() * 30 + 45);
			attributes[13] = (int)(Math.random() * 45 + 50);
			attributes[14] = (int)(Math.random() * 45 + 50);
			attributes[15] = (int)(Math.random() * 39 + 60);
		}
		if (position == "WR"){
			attributes[0] = (int)(Math.random() * 29 + 70);
			attributes[1] = (int)(Math.random() * 65 + 10);
			attributes[2] = (int)(Math.random() * 45 + 50);
			attributes[3] = (int)(Math.random() * 45 + 50);
			attributes[4] = (int)(Math.random() * 35 + 60);
			attributes[5] = (int)(Math.random() * 25 + 70);
			attributes[6] = (int)(Math.random() * 45 + 50);
			attributes[7] = (int)(Math.random() * 65 + 10);
			attributes[8] = (int)(Math.random() * 65 + 10);
			attributes[9] = (int)(Math.random() * 65 + 10);
			attributes[10] = (int)(Math.random() * 65 + 10);
			attributes[11] = (int)(Math.random() * 65 + 10);
			attributes[12] = (int)(Math.random() * 35 + 60);
			attributes[13] = (int)(Math.random() * 65 + 10);
			attributes[14] = (int)(Math.random() * 65 + 10);
			attributes[15] = (int)(Math.random() * 39 + 60);
		}
		if (position == "TE"){
			attributes[0] = (int)(Math.random() * 65 + 10);
			attributes[1] = (int)(Math.random() * 50 + 40);
			attributes[2] = (int)(Math.random() * 30 + 50);
			attributes[3] = (int)(Math.random() * 45 + 50);
			attributes[4] = (int)(Math.random() * 65 + 10);
			attributes[5] = (int)(Math.random() * 65 + 10);
			attributes[6] = (int)(Math.random() * 65 + 10);
			attributes[7] = (int)(Math.random() * 65 + 10);
			attributes[8] = (int)(Math.random() * 65 + 10);
			attributes[9] = (int)(Math.random() * 65 + 10);
			attributes[10] = (int)(Math.random() * 65 + 10);
			attributes[11] = (int)(Math.random() * 65 + 10);
			attributes[12] = (int)(Math.random() * 40 + 50);
			attributes[13] = (int)(Math.random() * 35 + 55);
			attributes[14] = (int)(Math.random() * 35 + 55);
			attributes[15] = (int)(Math.random() * 39 + 60);
		}
		if (position == "OL"){
			attributes[0] = (int)(Math.random() * 65 + 10);
			attributes[1] = (int)(Math.random() * 25 + 70);
			attributes[2] = (int)(Math.random() * 40 + 45);
			attributes[3] = (int)(Math.random() * 45 + 50);
			attributes[4] = (int)(Math.random() * 65 + 10);
			attributes[5] = (int)(Math.random() * 65 + 10);
			attributes[6] = (int)(Math.random() * 65 + 10);
			attributes[7] = (int)(Math.random() * 65 + 10);
			attributes[8] = (int)(Math.random() * 65 + 10);
			attributes[9] = (int)(Math.random() * 65 + 10);
			attributes[10] = (int)(Math.random() * 65 + 10);
			attributes[11] = (int)(Math.random() * 65 + 10);
			attributes[12] = (int)(Math.random() * 65 + 10);
			attributes[13] = (int)(Math.random() * 45 + 50);
			attributes[14] = (int)(Math.random() * 45 + 50);
			attributes[15] = (int)(Math.random() * 39 + 60);
		}
		if (position == "DL"){
			attributes[0] = (int)(Math.random() * 65 + 10);
			attributes[1] = (int)(Math.random() * 25 + 70);
			attributes[2] = (int)(Math.random() * 40 + 50);
			attributes[3] = (int)(Math.random() * 35 + 60);
			attributes[4] = (int)(Math.random() * 65 + 10);
			attributes[5] = (int)(Math.random() * 65 + 10);
			attributes[6] = (int)(Math.random() * 65 + 10);
			attributes[7] = (int)(Math.random() * 65 + 10);
			attributes[8] = (int)(Math.random() * 65 + 10);
			attributes[9] = (int)(Math.random() * 65 + 10);
			attributes[10] = (int)(Math.random() * 65 + 10);
			attributes[11] = (int)(Math.random() * 35 + 60);
			attributes[12] = (int)(Math.random() * 65 + 10);
			attributes[13] = (int)(Math.random() * 45 + 50);
			attributes[14] = (int)(Math.random() * 45 + 50);
			attributes[15] = (int)(Math.random() * 39 + 60);
		}
		if (position == "LB"){
			attributes[0] = (int)(Math.random() * 65 + 10);
			attributes[1] = (int)(Math.random() * 25 + 70);
			attributes[2] = (int)(Math.random() * 35 + 55);
			attributes[3] = (int)(Math.random() * 35 + 60);
			attributes[4] = (int)(Math.random() * 45 + 35);
			attributes[5] = (int)(Math.random() * 65 + 10);
			attributes[6] = (int)(Math.random() * 65 + 10);
			attributes[7] = (int)(Math.random() * 65 + 10);
			attributes[8] = (int)(Math.random() * 65 + 10);
			attributes[9] = (int)(Math.random() * 65 + 10);
			attributes[10] = (int)(Math.random() * 65 + 10);
			attributes[11] = (int)(Math.random() * 40 + 55);
			attributes[12] = (int)(Math.random() * 45 + 30);
			attributes[13] = (int)(Math.random() * 35 + 60);
			attributes[14] = (int)(Math.random() * 35 + 50);
			attributes[15] = (int)(Math.random() * 39 + 60);
		}
		if (position == "CB"){
			attributes[0] = (int)(Math.random() * 29 + 70);
			attributes[1] = (int)(Math.random() * 65 + 10);
			attributes[2] = (int)(Math.random() * 35 + 60);
			attributes[3] = (int)(Math.random() * 35 + 60);
			attributes[4] = (int)(Math.random() * 35 + 60);
			attributes[5] = (int)(Math.random() * 25 + 70);
			attributes[6] = (int)(Math.random() * 65 + 10);
			attributes[7] = (int)(Math.random() * 65 + 10);
			attributes[8] = (int)(Math.random() * 65 + 10);
			attributes[9] = (int)(Math.random() * 65 + 10);
			attributes[10] = (int)(Math.random() * 65 + 10);
			attributes[11] = (int)(Math.random() * 35 + 50);
			attributes[12] = (int)(Math.random() * 45 + 50);
			attributes[13] = (int)(Math.random() * 35 + 60);
			attributes[14] = (int)(Math.random() * 65 + 10);
			attributes[15] = (int)(Math.random() * 39 + 60);
		}
		if (position == "S"){
			attributes[0] = (int)(Math.random() * 39 + 60);
			attributes[1] = (int)(Math.random() * 65 + 10);
			attributes[2] = (int)(Math.random() * 45 + 50);
			attributes[3] = (int)(Math.random() * 45 + 50);
			attributes[4] = (int)(Math.random() * 35 + 60);
			attributes[5] = (int)(Math.random() * 25 + 70);
			attributes[6] = (int)(Math.random() * 65 + 10);
			attributes[7] = (int)(Math.random() * 65 + 10);
			attributes[8] = (int)(Math.random() * 65 + 10);
			attributes[9] = (int)(Math.random() * 65 + 10);
			attributes[10] = (int)(Math.random() * 65 + 10);
			attributes[11] = (int)(Math.random() * 35 + 55);
			attributes[12] = (int)(Math.random() * 45 + 50);
			attributes[13] = (int)(Math.random() * 35 + 60);
			attributes[14] = (int)(Math.random() * 65 + 10);
			attributes[15] = (int)(Math.random() * 39 + 60);
		}
		if (position == "K" || position == "P"){
			attributes[0] = (int)(Math.random() * 65 + 10);
			attributes[1] = (int)(Math.random() * 65 + 10);
			attributes[2] = (int)(Math.random() * 65 + 10);
			attributes[3] = (int)(Math.random() * 45 + 50);
			attributes[4] = (int)(Math.random() * 65 + 10);
			attributes[5] = (int)(Math.random() * 65 + 10);
			attributes[6] = (int)(Math.random() * 65 + 10);
			attributes[7] = (int)(Math.random() * 65 + 10);
			attributes[8] = (int)(Math.random() * 65 + 10);
			attributes[9] = (int)(Math.random() * 29 + 70);
			attributes[10] = (int)(Math.random() * 29 + 70);
			attributes[11] = (int)(Math.random() * 65 + 10);
			attributes[12] = (int)(Math.random() * 65 + 10);
			attributes[13] = (int)(Math.random() * 65 + 10);
			attributes[14] = (int)(Math.random() * 65 + 10);
			attributes[15] = (int)(Math.random() * 59 + 40);
		}


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


	void savePlayer(){
		String prefix = "";

	//	String teamName = team.getName();
	//	String teamName = "";

		if (team.equals( "Portland"))
			prefix = "teams/Portland/";
		else if (team.equals( "Anchorage"))
			prefix = "teams/Anchorage/";
		else if (team.equals( "Delaware"))
			prefix = "teams/Delaware/";
		else if (team.equals( "Richmond"))
			prefix = "teams/Richmond/";
		else if (team.equals( "Orlando"))
			prefix = "teams/Orlando/";
		else if (team.equals( "Biloxi"))
			prefix = "teams/Biloxi/";
		else if (team.equals( "Birmingham"))
			prefix = "teams/Birmingham/";
		else if (team.equals( "Iowa"))
			prefix = "teams/Iowa/";
		else if (team.equals( "Upper Michigan")){
			prefix = "teams/Upper_Michigan/";
			team = "Upper_Michigan";
		}
		else if (team.equals( "Dakota"))
			prefix = "teams/Dakota/";
		else if (team.equals( "Wichita"))
			prefix = "teams/Wichita/";
		else if (team.equals( "San Antonio")){
			prefix = "teams/San_Antonio/";
			team = "San_Antonio";
		}
		else if (team.equals( "Albuquerque"))
			prefix = "teams/Albuquerque/";
		else if (team.equals( "Los Angeles")){
			prefix = "teams/Los_Angeles/";
			team = "Los_Angeles";
		}
		else if (team.equals( "Boise"))
			prefix = "teams/Boise/";
		else if (team.equals( "Hawaii"))
			prefix = "teams/Hawaii/";
		else if (team.equals( "Tacoma"))
			prefix = "teams/Tacoma/";
		else if (team.equals( "Salt Lake")){
			prefix = "teams/Salt_Lake/";
			team = "Salt_Lake";
		}
		else if (team.equals( "Colorado"))
			prefix = "teams/Colorado/";
		else if (team.equals( "London"))
			prefix = "teams/London/";
		else if (team.equals( "Dayton"))
			prefix = "teams/Dayton/";
		else if (team.equals( "Tulsa"))
			prefix = "teams/Tulsa/";
		else if (team.equals( "New Haven")){
			prefix = "teams/New_Haven/";
			team = "New_Haven";
		}
		else if (team.equals( "Montana"))
			prefix = "teams/Montana/";
		else if (team.equals("Lancaster"))
			prefix = "teams/Lancaster/";
		else if (team.equals("Baton Rouge")){
			prefix = "teams/Baton_Rouge/";
			team = "Baton_Rouge";
		}
		else if (team.equals("Amarillo"))
			prefix = "teams/Amarillo/";
		else if (team.equals("Oregon"))
			prefix = "teams/Oregon/";
		else if (team.equals("Charleston"))
			prefix = "teams/Charleston/";
		else if (team.equals("Tahoe"))
			prefix = "teams/Tahoe/";
		else if (team.equals("Agents"))
			prefix = "teams/Agents/";


	//	prefix = "teams/Anchorage/";
		System.out.println(prefix);

		String filename = position + "-" + number + "-" + position;

		try{
		File file = new File(prefix + filename + ".fp");



		PrintWriter out = new PrintWriter(file);

		out.println(position + " #" + number);
		out.print(position + " " + team + " 0 ");
		out.println(number);
		out.print(school.replaceAll("//s","_") + " ");
		out.print(age + " " + experience + " ");
		out.println(height + " " + weight);
		for (int i = 0; i < 16; i++){
			out.print(attributes[i] + " ");
		}
		out.println(overall);

		out.close();
		}catch(Exception e){e.printStackTrace();}

		File teamFile = new File(prefix + "players.tr");

		try{
			FileWriter out2 = new FileWriter(teamFile, true);
			out2.write(String.valueOf(number) + " ");
			out2.close();

		}catch(Exception e){}
		
		File rosterFile = new File(prefix + "teamRoster.tr");

		try{
			FileWriter out3 = new FileWriter(rosterFile, true);
			out3.write(filename + ".fp\n");
			out3.close();
		}catch(Exception e){}

	}
}
