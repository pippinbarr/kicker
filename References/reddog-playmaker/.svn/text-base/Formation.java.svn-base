import java.awt.*;
import javax.swing.*;


public class Formation{

	String formationName = "";
	ImageIcon formationImage;

	ImageIcon [] offenseImages = {new ImageIcon(getClass().getResource("images/oForms/iformation.png")), new ImageIcon(getClass().getResource("images/oForms/goalLine.png")), new ImageIcon(getClass().getResource("images/oForms/shotgun.png")), new ImageIcon(getClass().getResource("images/oForms/splitback.png"))};

	ImageIcon [] defenseImages = {new ImageIcon(getClass().getResource("images/dForms/4_3.png")), new ImageIcon(getClass().getResource("images/dForms/dime.png")), new ImageIcon(getClass().getResource("images/dForms/goallineDef.png")), new ImageIcon(getClass().getResource("images/dForms/quarter.png"))};

	ImageIcon [] specialTeamsImages = {new ImageIcon(getClass().getResource("images/stForms/fieldgoal.png")), new ImageIcon(getClass().getResource("images/stForms/fgBlock.png")), new ImageIcon(getClass().getResource("images/stForms/kickoff.png")), new ImageIcon(getClass().getResource("images/stForms/kickoffReturn.png")), new ImageIcon(getClass().getResource("images/stForms/onsideKick.png")), new ImageIcon(getClass().getResource("images/stForms/onsideRecover.png")), new ImageIcon(getClass().getResource("images/stForms/punt.png")), new ImageIcon(getClass().getResource("images/stForms/puntReturn.png"))};


	public Formation(String ball, String form){

		formationName = form;


		if (ball.equals("O") || ball.equals("Offense"))
			setOffenseImage(form);
		else if (ball.equals("D") || ball.equals("Defense"))
			setDefenseImage(form);
		else
			setSpecialTeamsImage(form);
	}

	void setOffenseImage(String name){
		if (name.equals("I-Formation"))
			formationImage = offenseImages[0];
		else if (name.equals("GoalLine"))
			formationImage = offenseImages[1];
		else if (name.equals("Shotgun"))
			formationImage = offenseImages[2];
		else if (name.equals("Splitback"))
			formationImage = offenseImages[3];
	}

	void setDefenseImage(String name){
		if (name.equals("4-3"))
			formationImage = defenseImages[0];
		else if (name.equals("Dime"))
			formationImage = defenseImages[1];
		else if (name.equals("Goal_Line"))
			formationImage = defenseImages[2];
		else if (name.equals("Quarter"))
			formationImage = defenseImages[3];
	}

	void setSpecialTeamsImage(String name){
		if (name.equals("Field Goal"))
			formationImage = specialTeamsImages[0];
		else if (name.equals("FG Block"))
			formationImage = specialTeamsImages[1];
		else if (name.equals("Kickoff"))
			formationImage = specialTeamsImages[2];
		else if (name.equals("Kickoff Return"))
			formationImage = specialTeamsImages[3];
		else if (name.equals("Onside Kick"))
			formationImage = specialTeamsImages[4];
		else if (name.equals("Onside Recover"))
			formationImage = specialTeamsImages[5];
		else if (name.equals("Punt"))
			formationImage = specialTeamsImages[6];
		else if (name.equals("Punt Return"))
			formationImage = specialTeamsImages[7];
	}

	ImageIcon getFormationImage(){
	//	System.out.println("OKAY");
		return formationImage;
	}

	String getFormationName(){
		return formationName;
	}

}
