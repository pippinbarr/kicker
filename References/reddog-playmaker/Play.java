import javax.swing.*;
import java.awt.*;


public class Play{

	String name = "";
	String sideOfBall = "";
	String type = "";
	int numberOfReceivers = 0;
	String [] players = new String[4];
	Formation formation;
	String [] routes = new String[4];

	void Play(){

	}

	void setName(String n){
		name = n;
	}

	String getName(){
		return name;
	}

	void setSideOfBall(String side){
		sideOfBall = side;
	}

	String getSideOfBall(){
		return sideOfBall;
	}

	void setType(String t){
		type = t;
	}

	String getType(){
		return type;
	}

	void setNumberOfReceivers(int num){
		numberOfReceivers = num;
	}

	int getReceivers(){
		return numberOfReceivers;
	}

	void setPlayers(int id, String pos){
		players[id] = pos;
	}

	String getPlayer(int id){
		return players[id];
	}

	void setFormation(String form){
		formation = new Formation(getSideOfBall(), form);
	}

	Formation getFormation(){
		return formation;
	}

	void setRoutes(int id, String rt){
		routes[id] = rt;
	}


}
