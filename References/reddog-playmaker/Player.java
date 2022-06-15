import java.awt.*;
import javax.swing.*;

public class Player{

	String team = "";

	String firstName = "";
	String lastName = "";
	String school = "";
	int age = 0;
	int experience = 0;
	String position = "";
	int number = 0;
	int [] attributes = new int[16];
	int overall = 0;

	int height = 0;
	int weight = 0;

	Statistics statistics = new Statistics(false);

	public Player(){

	}

	void setName(String first, String last){
		firstName = first;
		lastName = last;
	}

	String getName(){
		return (firstName + " " + lastName);
	}

	void setTeam(String s){
		team = s;
	}

	String getTeam(){
		return team;
	}

	void setSchool(String sch){
		school = sch;
	}

	void setAge(int a){
		age = a;
	}

	void setExperience(int ex){
		experience = ex;
	}

	void setPosition(String pos){
		position = pos;
	}

	String getPosition(){
		return position;
	}

	void setNumber(int num){
		number = num;
	}

	int getNumber(){
		return number;
	}

	void setAttribute(int idx, int val){
		attributes[idx] = val;
	}

	int getAttributeAt(int idx){
		return attributes[idx];
	}

	void setOverall(int o){
		overall = o;
	}

	int getOverall(){
		return overall;
	}

	void setHeight(int h){
		height = h;
	}

	void setWeight(int w){
		weight = w;
	}

	Statistics getStats(){
		return statistics;
	}

}
