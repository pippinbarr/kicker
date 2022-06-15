import java.awt.*;
import javax.swing.*;


public class Team{

	String name = "";
	String nickname = "";
	String location = "";
	String state = "";
	String abbreviation = "";
	String coach = "";
	String stadium = "";

	boolean domed = false;

	String climate = "";
	Climate teamClimate;


	String division = "";

	ImageIcon teamLogo;
	Color primaryColor;
	Color secondaryColor;

	boolean computer = false;

	Statistics statistics = new Statistics(true);

	public Team(){


	}

	public Team(String n, String nick){
		setName(n);
		setNickname(nick);
	}

	public Team(String n, String nick, String loc, String st, String abbr){
		setName(n);
		setNickname(nick);
		setLocation(loc);
		setState(st);
		setAbbreviation(abbr);
	}

	void setName(String n){
		name = n;
	}

	String getName(){
		return name;
	}

	void setNickname(String n){
		nickname = n;
	}

	String getNickname(){
		return nickname;
	}

	void setLocation(String loc){
		location = loc;
	}

	String getLocation(){
		return location;
	}

	void setState(String st){
		state = st;
	}

	String getState(){
		return state;
	}

	void setAbbreviation(String abb){
		abbreviation = abb;
	}

	String getAbbreviation(){
		return abbreviation;
	}

	void setCoach(String c){
		coach = c;
	}

	String getCoach(){
		return coach;
	}

	void setStadium(String stad){
		stadium = stad;
	}

	String getStadium(){
		return stadium;
	}

	void setDomed(boolean b){
		domed = b;
	}

	boolean isDomed(){
		return domed;
	}

	void setClimate(String clim){
		System.out.println("Climate: " + clim);
		climate = clim;
		teamClimate = new Climate(climate);
	}

	String getClimate(){
		return climate;
	}

	Climate getTeamClimate(){
		return teamClimate;
	}

	void setDivision(String div){
		division = div;
	}

	String getDivision(){
		return division;
	}

	void setLogo(ImageIcon img){
		teamLogo = img;
	}

	ImageIcon getLogo(){
		return teamLogo;
	}

	void setPrimaryColor(Color c){
		primaryColor = c;
	}

	Color getPrimaryColor(){
		return primaryColor;
	}

	void setSecondaryColor(Color c){
		secondaryColor = c;
	}

	Color getSecondaryColor(){
		return secondaryColor;
	}

	void setComputer(boolean comp){
		computer = comp;
	}

	boolean isComputer(){
		return computer;
	}

	Statistics getStats(){
		return statistics;
	}


}
