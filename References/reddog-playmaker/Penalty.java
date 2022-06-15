//Penalty class

public class Penalty{

String [] name = new String[33];
int [] yards = new int[33];
char [] side = new char[33];
boolean [] loseDown = new boolean[33];
boolean [] firstDown = new boolean[33];

void setPenalties(){
	// Dead ball fouls
	createPenalty(0, "Delay of Game", 'A', 5, false, false);
	createPenalty(1, "False Start", 'O', 5, false, false);
	createPenalty(2, "Encroachment", 'D', 5, false, false);

	// Common Infractions
	createPenalty(3, "Offsides", 'A', 5 ,false, false);
	createPenalty(4,"Holding", 'O', 10, false, false);
	createPenalty(5,"Holding", 'D', 5, true, false);
	createPenalty(6,"Illegal Contact", 'D', 5, true, false);
	createPenalty(7,"Pass Interference", 'D', 0, true, false);
	createPenalty(8,"Facemask", 'A', 15, true, false);
	createPenalty(9,"Illegal Formation", 'O', 5, false, false);
	createPenalty(10,"Pass Interference", 'O', 10, false, false);
	createPenalty(11,"Tripping", 'A', 10, true, false);
	createPenalty(12,"Illegal Motion", 'O', 5, false, false);
	createPenalty(13,"Illegal Hands to Face", 'D', 5, true, false);
	createPenalty(14,"Illegal Hands to Face", 'O', 10, false, false);
	createPenalty(15, "Block in back", 'A', 10, false, false);
	createPenalty(16,"Illegal Block", 'D', 15, false, false);

	// Uncommon Infractions
	createPenalty(17,"Illegal Shift", 'O', 5, false, false);
	createPenalty(18,"Illegal Substitution",'A',5, false, false);
	createPenalty(19,"Ineligible Receiver Downfield", 'O', 5, false, false);
	createPenalty(20,"Chop Block", 'O', 15, false, false);
	createPenalty(21,"Clipping", 'A', 15, true, false);
	createPenalty(22,"Illegal Forward Pass", 'O', 5, false, true);
	createPenalty(23,"Illegal Use of Hands", 'O', 10, false, false);
	createPenalty(24,"Illegal Use of Hands", 'D', 5, true, false);
	createPenalty(25,"Intentional Grounding", 'O', 10, false, true);
	createPenalty(26,"Spearing", 'A', 15, true, false);


	// Personal Fouls
	createPenalty(27,"Personal Foul", 'A', 15, true, false);
	createPenalty(28,"Unsportsmanlike conduct", 'A', 15, true, false);
	createPenalty(29,"Roughing the Passer", 'D', 15, true, false);
	createPenalty(30,"Horse-Collar Tackle", 'D', 15, false, false);

	// Special Teams Infractions
	createPenalty(31,"Roughing the Kicker", 'D', 15, true, false);
	createPenalty(32,"Running into the Kicker", 'D', 5, false, false);

}


void createPenalty(int idx, String n, char s, int yd, boolean first, boolean lose){
	setName(idx, n);
	setSide(idx,s);
	setYards(idx,yd);
	setAutoFirst(idx,first);
	setLoseDown(idx,lose);
}

void setName(int i, String s){
	name[i] = s;
}

String getName(int i){
	return name[i];
}

void setSide(int i, char c){
	side[i] = c;
}

char getSide(int i){
	return side[i];
}

void setYards(int i, int yd){
	yards[i] = yd;
}

int getYards(int i){
	return yards[i];
}

void setAutoFirst(int i, boolean first){
	firstDown[i] = first;
}

boolean isAutoFirst(int i){
	return firstDown[i];
}

void setLoseDown(int i, boolean lose){
	loseDown[i] = lose;
}

boolean willLoseDown(int i){
	return loseDown[i];
}


}

/*
How penalties will be called

If time runs out before play is called:
Dead Ball Foul = Delay of Game

When plays are being performed:
Array of 100: 16 = False Start(O), 11 = Encroachment(D), 73 = No Penalty
Penalty called here will cause a dead ball

If play continues,
For each matchup needed, underdogs winning will trigger whether a penalty was committed.
Array of 100: 20 = holding, 10 = hands to face, 70 = safe

For plays, if a pass is incomplete or intercepted,
Array of 100: 20 = Illegal Contact, 5 = Pass Interference (O), 5 = Pass Interference (D)

For runs, if a large run is completed,
Array of 100: 15 = Illegal Block, 10 = Block in back, 15 = Holding, 60 = safe

For kicks, if kick is blocked,
Array of 100: 25 = roughing kicker, 15 = running into kicker, 60 = safe

For throwing ball away by QB,
Array of 100: 20 = Intentional Grounding, 5 = Ineligible receiver, 5 = Roughing Passer, 2 = Illegal Forward Pass, 68 = Safe

Remaining Penalties not looked for above will tested as such:
Array of 100: 1 = Each Penalty (17 total), 83 = safe

For dead ball fouls, screen will appear showing what occurred and penalty enforced.

For plays against computer, screen will appear showing penalty and choices to accept or decline penalty.

For plays against player, screen will appear showing penalty and the choice made by CPU.

For in-play penalties, play result will appear first and a "Flag on the play" message will be included.  Clicking on "Continue" (or whatever) will show the above screens.
*/
