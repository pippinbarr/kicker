import java.util.*;

public class PlayAnalysis{

	Player mainPlayer;
   
   // Included for retrieving defensive statistics
   Player defensivePlayer;
   
	Vector<Player> offense;
	Vector<Player> defense;
	int yardsGained = 0;
	int kickAccuracy = 0;
	int kickDistance = 0;
	boolean fumbledBall = false;
	boolean turnover = false;
	int advantage = 0;
	boolean tackled = false;
   
   // Added for defensive and passing stat
   boolean qbSacked = false;
   
	boolean incomplete = false;
	boolean intercepted = false;
	String play = "";
	int timeElapsed = 0;
	boolean ballKicked = false;

	public PlayAnalysis(Player off, Vector<Player> offTeam, Vector<Player> def, String playType, int initialAdvantage){
		mainPlayer = off;
		System.out.println(mainPlayer.getName());
		offense = offTeam;
		defense = def;
		play = playType;
		advantage = initialAdvantage;
		incomplete = false;
		intercepted = false;
		yardsGained = 0;
	}


	Player getOffensivePlayer(){
		return mainPlayer;
	}

  Player getDefensivePlayer(){
  return defensivePlayer;
  }

	int getYardsGained(){
		return yardsGained;
	}

  boolean quarterbackSacked(){
    return qbSacked;
  }

	boolean turnoverOccurred(){
		return turnover;
	}

	boolean passIncomplete(){
		return incomplete;
	}

	boolean ballFumbled(){
		return fumbledBall;
	}

	boolean passIntercepted(){
		return intercepted;
	}


	void performPlay(){
   // This should be changed so that the values can be something other than 0 or 5
		advantage += OLvsDL();

		if (mainPlayer.getPosition().equals("K") || mainPlayer.getPosition().equals("P")){
			ballKicked = true;
		//	if (!kickIsSuccessful())
		//		turnover = true;
		//	else{
				if (play.equals("Field_Goal")){
					timeElapsed = 6;
					determineKickDistance();
					determineKickAccuracy();
				}
				else if (play.equals("Punt") || play.equals("Kickoff") || play.equals("Onside") ){
					determineKickDistance();
				}

		//	}
		}

		if (play.equals("R")){
		//	System.out.println("Rushing...");

			if (mainPlayer.getPosition().equals("CB"))
				returnKick();
			else{

				if (mainPlayer.getPosition().equals("HB"))
					HBvsDL();
				else
					runnervsDL();


				if (!tackled)
					runnervsLB();
				if (!tackled)
					runnervsCB();
			}
		}
		else if (!play.equals("Field_Goal") && !play.equals("Punt") && !play.equals("Kickoff") && !play.equals("Onside") ){
			QBvsDL();
			if (!tackled && !incomplete)
				if (!play.equals("long"))
					receiverVsLinebacker();
				else
					receiverVsCornerback();
			if (!tackled && !incomplete)
				runnervsCB();

		}

	}


	int OLvsDL(){
		float olTotal = 0;
		float dlTotal = 0;

		int olStrength = 0;
		int olPassBlocking = 0;
		int olRunBlocking = 0;

		int dlStrength = 0;
		int dlPassBlocking = 0;
		int dlRunBlocking = 0;


		for (int i = 0; i < offense.size(); i++){
			if (offense.elementAt(i).getPosition().equals("OL")){
				olTotal += (offense.elementAt(i).getAttributeAt(1) + offense.elementAt(i).getAttributeAt(14) + offense.elementAt(i).getAttributeAt(13));
				olStrength += offense.elementAt(i).getAttributeAt(1);
				olPassBlocking += offense.elementAt(i).getAttributeAt(14);
				olRunBlocking += offense.elementAt(i).getAttributeAt(13);
			}
		}	
		for (int i = 0; i < defense.size(); i++){
			if (defense.elementAt(i).getPosition().equals("DL")){
				dlTotal += (defense.elementAt(i).getAttributeAt(1) + defense.elementAt(i).getAttributeAt(14) + defense.elementAt(i).getAttributeAt(13));
				dlStrength += defense.elementAt(i).getAttributeAt(1);
				dlPassBlocking += defense.elementAt(i).getAttributeAt(14);
				dlRunBlocking += defense.elementAt(i).getAttributeAt(13);
			}
		}

		if (olTotal > dlTotal)
			olTotal += 5;
		else if (dlTotal > olTotal)
			dlTotal += 5;


		int trueSlots = (int)((olTotal / (olTotal + dlTotal)) * 100);

		boolean [] success = new boolean[100];

		for (int i = 0; i < 100; i++){
			if (i < trueSlots)
				success[i] = true;
			else
				success[i] = false;
		}

		int wins = 0;

		for (int i = 0; i < 1000; i++){
			int rand = (int)(Math.random() * 100);
			if (success[rand])
				wins++;
		}

		// If OL wins majority of matchups, no advantage given to defense, else 5 point advantage given to DL
		if (wins > 525)
			return 0;
		else
			return 5;

	}

	void HBvsDL(){

		float runnerTotal = mainPlayer.getAttributeAt(1) + mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(4) + mainPlayer.getAttributeAt(12) + mainPlayer.getAttributeAt(3) + mainPlayer.getAttributeAt(0) + mainPlayer.getAttributeAt(5);
		float dlTotal = 0;
		int runBlocking = 0;
		int awareness = 0;
		int passBlocking = 0;
		int tackling = 0;


		int numDefense = 0;

		for (int i = 0; i < defense.size(); i++){
			if (defense.elementAt(i).getPosition().equals("DL")){
				numDefense++;
				dlTotal += ((defense.elementAt(i).getAttributeAt(13) + defense.elementAt(i).getAttributeAt(3)) * 2 + defense.elementAt(i).getAttributeAt(14) + defense.elementAt(i).getAttributeAt(11));
				runBlocking += defense.elementAt(i).getAttributeAt(13);
				awareness += defense.elementAt(i).getAttributeAt(3);
				passBlocking += defense.elementAt(i).getAttributeAt(14);
				tackling += defense.elementAt(i).getAttributeAt(11);

			}
		}

		dlTotal += (advantage * numDefense);

		dlTotal = (dlTotal / numDefense);


		if ((mainPlayer.getAttributeAt(1) + mainPlayer.getAttributeAt(6) / 2) > (runBlocking / 6) )
			runnerTotal += 5;
		else if ((mainPlayer.getAttributeAt(1) + mainPlayer.getAttributeAt(6) / 2) < (runBlocking / 6))
			dlTotal += 5;

		if (mainPlayer.getAttributeAt(4) > awareness)
			runnerTotal += 5;
		else if (awareness > mainPlayer.getAttributeAt(4))
			dlTotal += 5;

		if (mainPlayer.getAttributeAt(12) > passBlocking)
			runnerTotal += 5;
		else if (passBlocking > mainPlayer.getAttributeAt(12))
			dlTotal += 5;

		if (mainPlayer.getAttributeAt(3) > tackling)
			runnerTotal += 5;
		else if (tackling > mainPlayer.getAttributeAt(3))
			dlTotal += 5;

		if (  (mainPlayer.getAttributeAt(0) + mainPlayer.getAttributeAt(5)) > (awareness + runBlocking))
			runnerTotal += 5;
		else if( (mainPlayer.getAttributeAt(0) + mainPlayer.getAttributeAt(5)) < (awareness + runBlocking))
			dlTotal += 5;


		int trueSlots = (int)(((runnerTotal / 7) / ( (runnerTotal / 7) + (dlTotal / 6))) * 100);


		boolean [] success = new boolean[100];

		for (int i = 0; i < 100; i++){
			if (i < trueSlots)
				success[i] = true;
			else
				success[i] = false;
		}

		int wins = 0;

		for (int i = 0; i < 1000; i++){
			int rand = (int)(Math.random() * 100);
			if (success[rand])
				wins++;
		}

	//	System.out.println(wins);

		

		if (wins > 500){
			yardsGained += 5;
			tackled = false;

		}
		else if (wins <= 500){
	//		System.out.println("Gaining yards, tackled");

			tackled = true;
		//	if (advantage != 0)
		//		yardsGained -= 3;

			yardsGained += (wins - 420) / 16;

			if (yardsGained < -3)
					yardsGained = (int)(Math.random() * 4 - 3);


			if (wins < 400){
				if (fumbledBall(trueSlots))
					turnover = true;
			}
         
        if (tackled){
          int nearestFrom = 1000;
          for (int j = 0; j < defense.size(); j++){
            if (defense.elementAt(j).getPosition().equals("DL")){

               dlTotal =   ((defense.elementAt(i).getAttributeAt(13) + defense.elementAt(i).getAttributeAt(3)) * 2 + defense.elementAt(i).getAttributeAt(14) + defense.elementAt(i).getAttributeAt(11));
int trueSlots = (int)(((runnerTotal / 7) / ( (runnerTotal / 7) + (dlTotal / 6))) * 100);


		boolean [] success = new boolean[100];

		for (int i = 0; i < 100; i++){
			if (i < trueSlots)
				success[i] = true;
			else
				success[i] = false;
		}

		int wins2 = 0;

		for (int i = 0; i < 1000; i++){
			int rand = (int)(Math.random() * 100);
			if (success[rand])
				wins2++;
		}
          if (Math.abs(wins2 - wins) < nearestFrom){
        nearestFrom = Math.abs(wins2 - wins);
        defensivePlayer = defense.elementAt(j);
        }
            }
            }
        }
		}

		// For when timer is implemented
		timeElapsed = (int)(Math.random() * 2 + 3);

		System.out.println("Yards Gained: HB vs DL - " + yardsGained + "   Wins: " + wins);

	}

	void runnervsDL(){
		float runnerTotal = (mainPlayer.getAttributeAt(1) * 2) + mainPlayer.getAttributeAt(14) + mainPlayer.getAttributeAt(13) + mainPlayer.getAttributeAt(3) +
					mainPlayer.getAttributeAt(11) + mainPlayer.getAttributeAt(4);
		float dlTotal = 0;

		int runBlocking = 0;
		int awareness = 0;
		int passBlocking = 0;
		int tackling = 0;
		int strength = 0;


		int numDefense = 0;

		for (int i = 0; i < defense.size(); i++){
			if (defense.elementAt(i).getPosition().equals("DL")){
				numDefense++;
				dlTotal += ((defense.elementAt(i).getAttributeAt(13) + defense.elementAt(i).getAttributeAt(14) + defense.elementAt(i).getAttributeAt(1)) * 2 + defense.elementAt(i).getAttributeAt(3) + defense.elementAt(i).getAttributeAt(11));

				runBlocking += defense.elementAt(i).getAttributeAt(13);
				awareness += defense.elementAt(i).getAttributeAt(3);
				passBlocking += defense.elementAt(i).getAttributeAt(14);
				tackling += defense.elementAt(i).getAttributeAt(11);
				strength += defense.elementAt(i).getAttributeAt(1);
			}
		}

		dlTotal += (advantage * numDefense);

		dlTotal = (dlTotal / numDefense);

		if ((mainPlayer.getAttributeAt(1) + mainPlayer.getAttributeAt(14) ) > ((strength + passBlocking) / numDefense) )
			runnerTotal += 5;
		else if ((mainPlayer.getAttributeAt(1) + mainPlayer.getAttributeAt(14)) < ( (strength + passBlocking) / numDefense))
			dlTotal += 5;

		if ((mainPlayer.getAttributeAt(1) + mainPlayer.getAttributeAt(13) ) > ((strength + runBlocking) / numDefense) )
			runnerTotal += 5;
		else if ((mainPlayer.getAttributeAt(1) + mainPlayer.getAttributeAt(13)) < ( (strength + runBlocking) / numDefense))
			dlTotal += 5;

		if ((mainPlayer.getAttributeAt(11) + mainPlayer.getAttributeAt(3) ) > (tackling + awareness) / numDefense)
			runnerTotal += 5;
		else if ((tackling + awareness) / numDefense > (mainPlayer.getAttributeAt(11) + mainPlayer.getAttributeAt(3) ) )
			dlTotal += 5;

		if ((mainPlayer.getAttributeAt(13) + mainPlayer.getAttributeAt(14)) > (strength + tackling) / numDefense)
			runnerTotal += 5;
		else if ((strength + tackling) / numDefense > (mainPlayer.getAttributeAt(13) + mainPlayer.getAttributeAt(14)))
			dlTotal += 5;



		int trueSlots = (int)(((runnerTotal / 7) / ( (runnerTotal / 7) + (dlTotal / 8))) * 100);


		boolean [] success = new boolean[100];

		for (int i = 0; i < 100; i++){
			if (i < trueSlots)
				success[i] = true;
			else
				success[i] = false;
		}

		int wins = 0;

		for (int i = 0; i < 1000; i++){
			int rand = (int)(Math.random() * 100);
			if (success[rand])
				wins++;
		}

//		System.out.println(wins);

		

		if (wins > 480){
			yardsGained += 5;
			tackled = false;
		}
		else if (wins <= 480){
		//	System.out.println("Gaining yards, tackled");

			tackled = true;
		//	if (advantage != 0)
		//		yardsGained -= 3;

			yardsGained += (wins - 420) / 12;

			if (yardsGained < -4){
					if (mainPlayer.getPosition().equals("QB"))
						yardsGained = (int)(Math.random() * 4 - 2);
					else
						yardsGained = (int)(Math.random() * 3 - 2);
				//	System.out.println("Limit triggered.   Wins: " + wins);
			}


			if (wins < 400){
				if (fumbledBall(trueSlots))
					turnover = true;
			}
		}

		// For when timer is implemented
		timeElapsed = (int)(Math.random() * 3 + 4);
		System.out.println("Yards Gained: FB/QB vs DL - " + yardsGained);

	}


	void runnervsLB(){

		float rbTotal = mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(12) + mainPlayer.getAttributeAt(3) + mainPlayer.getAttributeAt(5);
		float lbTotal = 0;

		int numDefense = 0;
		int catching = 0;
		int awareness = 0;
		int passBlocking = 0;
		int tackling = 0;
		int strength = 0;

		for (int i = 0; i < defense.size(); i++){
			if (defense.elementAt(i).getPosition().equals("LB")){
				numDefense++;
				lbTotal += (defense.elementAt(i).getAttributeAt(1) + defense.elementAt(i).getAttributeAt(3) + defense.elementAt(i).getAttributeAt(11) + defense.elementAt(i).getAttributeAt(12) + defense.elementAt(i).getAttributeAt(14));
				catching += defense.elementAt(i).getAttributeAt(12);
				awareness += defense.elementAt(i).getAttributeAt(3);
				passBlocking += defense.elementAt(i).getAttributeAt(14);
				tackling += defense.elementAt(i).getAttributeAt(11);
				strength += defense.elementAt(i).getAttributeAt(1);
			}
		}


		if (mainPlayer.getAttributeAt(5) + mainPlayer.getAttributeAt(6) > (strength + tackling) / numDefense)
			rbTotal += 5;
		else if (mainPlayer.getAttributeAt(5) + mainPlayer.getAttributeAt(6) < (strength + tackling) / numDefense)
			lbTotal += 5;

		if (mainPlayer.getAttributeAt(3) > (awareness + catching) / (2 * numDefense))
			rbTotal += 5;
		else if (mainPlayer.getAttributeAt(3) < (awareness + catching) / (2 * numDefense))
			lbTotal += 5;

		if (mainPlayer.getAttributeAt(12) > passBlocking / numDefense)
			rbTotal += 5;
		else if (mainPlayer.getAttributeAt(12) < passBlocking / numDefense)
			lbTotal += 5;


		int trueSlots = (int)((rbTotal / 4) / ( (rbTotal / 4) + (lbTotal / (5 * numDefense) ) ) * 100);

		boolean [] success = new boolean[100];

		for (int i = 0; i < 100; i++){
			if (i < trueSlots)
				success[i] = true;
			else
				success[i] = false;
		}

		int wins = 0;

		for (int i = 0; i < 1000; i++){
			int rand = (int)(Math.random() * 100);
			if (success[rand])
				wins++;
		}

		if (wins > 550){
			yardsGained += 6;
			tackled = false;
		}
		else if (wins <= 550){
			tackled = true;

			yardsGained += (wins - 450) / 15;
			if (yardsGained < 0)
				yardsGained = (int)(Math.random() * 3 - 2);

			if (wins < 425){
				if (fumbledBall(trueSlots))
					turnover = true;
			}
		}

		// For when timer is implemented
		timeElapsed += (int)(Math.random() * 3 + 1);
		System.out.println("Yards Gained: Runner vs LB - " + yardsGained);

	}


	void runnervsCB(){

		float rbTotal = mainPlayer.getAttributeAt(0) + (mainPlayer.getAttributeAt(6) * 2) + mainPlayer.getAttributeAt(5) + mainPlayer.getAttributeAt(12) + mainPlayer.getAttributeAt(3) + mainPlayer.getAttributeAt(1);
		float cbTotal = 0;

		int numDefense = 0;
		int catching = 0;
		int awareness = 0;
		int passBlocking = 0;
		int tackling = 0;
		int strength = 0;
		int speed = 0;
		int acceleration = 0;
		int agility = 0;

		for (int i = 0; i < defense.size(); i++){
			if (defense.elementAt(i).getPosition().equals("CB") || defense.elementAt(i).getPosition().equals("S")){
				numDefense++;
				cbTotal += (defense.elementAt(i).getAttributeAt(12) + defense.elementAt(i).getAttributeAt(3) + (defense.elementAt(i).getAttributeAt(14) * 2) + defense.elementAt(i).getAttributeAt(11) + defense.elementAt(i).getAttributeAt(1) + defense.elementAt(i).getAttributeAt(0) + defense.elementAt(i).getAttributeAt(4) + defense.elementAt(i).getAttributeAt(5));
				catching += defense.elementAt(i).getAttributeAt(12);
				awareness += defense.elementAt(i).getAttributeAt(3);
				passBlocking += ( defense.elementAt(i).getAttributeAt(14) * 2);
				tackling += defense.elementAt(i).getAttributeAt(11);
				strength += defense.elementAt(i).getAttributeAt(1);
				speed += defense.elementAt(i).getAttributeAt(0);
				acceleration += defense.elementAt(i).getAttributeAt(4);
				agility += defense.elementAt(i).getAttributeAt(5);
			}
		}


		if (mainPlayer.getAttributeAt(0) > speed / numDefense)
			rbTotal += 5;
		else if (mainPlayer.getAttributeAt(0) < speed / numDefense)
			cbTotal += 5;

		if ( (mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(5)) / 2 > (passBlocking + acceleration + agility) / (3 * numDefense))
			rbTotal += 5;
		else if ( (mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(5)) / 2 < (passBlocking + acceleration + agility) / (3 * numDefense))
			cbTotal += 5;

		if (mainPlayer.getAttributeAt(12) > (catching + passBlocking) / (2 * numDefense))
			rbTotal += 5;
		else if (mainPlayer.getAttributeAt(12) < (catching + passBlocking) / (2 * numDefense))
			cbTotal += 5;

		if (mainPlayer.getAttributeAt(3) > awareness / numDefense)
			rbTotal += 5;
		else if (mainPlayer.getAttributeAt(3) < awareness / numDefense)
			cbTotal += 5;

		if (mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(1) > (tackling + strength) / numDefense)
			rbTotal += 5;
		else if (mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(1) < (tackling + strength) / numDefense)
			cbTotal += 5;

		int trueSlots = (int)((rbTotal / 7) / ( (rbTotal / 7) + (cbTotal / (9 * numDefense) ) ) * 100);

		boolean [] success = new boolean[100];

		for (int i = 0; i < 100; i++){
			if (i < trueSlots)
				success[i] = true;
			else
				success[i] = false;
		}

		int wins = 0;
		int losses = 0;

		for (int i = 0; i < 1000; i++){
			int rand = (int)(Math.random() * 100);

			if (losses < 100){
				if (success[rand])
					wins++;
				else
					losses++;
			}
		}

		yardsGained += (wins / 10);

		// For when timer is implemented
		timeElapsed += (int)(Math.random() * 3 + (wins / 40) );

		if (wins >= 350)
			tackled = true;
		else{
			if (fumbledBall(trueSlots))
				turnover = true;
		}

		System.out.println("Yards Gained: Runner vs CB - " + yardsGained);

	}


	void QBvsDL(){
		float qbTotal = 0;
		int awareness = 0;
		int elusiveness = 0;
		int overall = 0;

		for (int i = 0; i < offense.size(); i++){
			if (offense.elementAt(i).getPosition().equals("QB") && offense.elementAt(i).getOverall() > overall){
				overall = offense.elementAt(i).getOverall();
				qbTotal = offense.elementAt(i).getAttributeAt(3) + offense.elementAt(i).getAttributeAt(6) + offense.elementAt(i).getAttributeAt(15);
				awareness = offense.elementAt(i).getAttributeAt(3);
				elusiveness = offense.elementAt(i).getAttributeAt(6);
				break;
			}
		}

		float dlTotal = 0;
		int runBlocking = 0;
		int passBlocking = 0;
		int tackling = 0;
		int strength = 0;
		int numDefense = 0;
		for (int i = 0; i < defense.size(); i++){
			if (defense.elementAt(i).getPosition().equals("DL")){
				numDefense++;
				dlTotal += (defense.elementAt(i).getAttributeAt(1) + defense.elementAt(i).getAttributeAt(11) + defense.elementAt(i).getAttributeAt(14) + defense.elementAt(i).getAttributeAt(13));
				strength += defense.elementAt(i).getAttributeAt(1);
				tackling += defense.elementAt(i).getAttributeAt(11);
				passBlocking += defense.elementAt(i).getAttributeAt(14);
				runBlocking += defense.elementAt(i).getAttributeAt(13);
			}
		}


		if ( (runBlocking + passBlocking) / (2 * numDefense) > awareness)
			dlTotal += 5;
		else if  ( (runBlocking + passBlocking) / (2 * numDefense) < awareness)
			qbTotal += 5;

		if (elusiveness > tackling / numDefense)
			qbTotal += 5;
		else if (elusiveness < tackling / numDefense)
			dlTotal += 5;

		dlTotal += (numDefense * advantage);

		float total = qbTotal / 3 + (dlTotal / (4 * numDefense));
		int trueSlots = (int)((qbTotal / 3) / total * 100);

		boolean [] success = new boolean[100];

		for (int i = 0; i < 100; i++){
			if (i < trueSlots)
				success[i] = true;
			else
				success[i] = false;
		}

		int wins = 0;

		for (int i = 0; i < 1000; i++){
			int rand = (int)(Math.random() * 100);

			if (success[rand])
				wins++;			
		}

		if (wins <= 350)
			if (fumbledBall(trueSlots)){

				if (wins < 300)
					turnover = true;
				else
					turnover = false;

				// For when timer is implemented
				timeElapsed = (int)(Math.random() * 4 + 3);
			}
			else{
				tackled = true;
            qbSacked = true;
		//		if (advantage != 0)
		//			yardsGained -= 6;

				yardsGained -= (wins / 100);

				// For when timer is implemented
				timeElapsed = (int)(Math.random() * 3 + 3);

				System.out.println("QB sacked for " + (0 - yardsGained) + " yards");
				incomplete = true;
			}
		else if (wins <= 375)
			incomplete = true;

	}


	void receiverVsLinebacker(){
		float recTotal = (mainPlayer.getAttributeAt(6) * 2) + mainPlayer.getAttributeAt(0) + mainPlayer.getAttributeAt(3) + mainPlayer.getAttributeAt(4) + mainPlayer.getAttributeAt(12);
		float lbTotal = 0;

		int numDefense = 0;
		int passBlocking = 0;
		int strength = 0;
		int awareness = 0;
		int tackling = 0;

		for (int i = 0; i < defense.size(); i++){
			if (defense.elementAt(i).getPosition().equals("LB")){
				numDefense++;
				lbTotal += (defense.elementAt(i).getAttributeAt(14) * 3) + (defense.elementAt(i).getAttributeAt(3) + defense.elementAt(i).getAttributeAt(1)) * 2 + defense.elementAt(i).getAttributeAt(11);
				awareness += defense.elementAt(i).getAttributeAt(3);
				passBlocking += defense.elementAt(i).getAttributeAt(14);
				tackling += defense.elementAt(i).getAttributeAt(11);
				strength += defense.elementAt(i).getAttributeAt(1);
			}
		}

		if ( (mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(0)) / 2 > (awareness + strength + passBlocking) / (numDefense * 3))
			recTotal += 5;
		else if ( (mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(0)) / 2 < (awareness + strength + passBlocking) / (numDefense * 3))
			lbTotal += 5;

		if ( (mainPlayer.getAttributeAt(12) + mainPlayer.getAttributeAt(3)) > (passBlocking + awareness) / numDefense)
			recTotal += 5;
		else if ( (mainPlayer.getAttributeAt(12) + mainPlayer.getAttributeAt(3)) < (passBlocking + awareness) / numDefense)
			lbTotal += 5;

		if ( mainPlayer.getAttributeAt(4) > passBlocking / numDefense)
			recTotal += 5;
		else if ( mainPlayer.getAttributeAt(4) < passBlocking / numDefense)
			lbTotal += 5;

		if ( (mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(5)) > (strength + tackling) / numDefense)
			recTotal += 5;
		else if ( (mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(5)) < (strength + tackling) / numDefense)
			lbTotal += 5;

		float total = recTotal / 7 + (lbTotal / (8 * numDefense));
		int trueSlots = (int)( ( (recTotal / 7) / total) * 100);

		boolean [] success = new boolean[100];

		for (int i = 0; i < 100; i++){
			if (i < trueSlots)
				success[i] = true;
			else
				success[i] = false;
		}

		int wins = 0;

		for (int i = 0; i < 1000; i++){
			int rand = (int)(Math.random() * 100);

			if (success[rand])
				wins++;			
		}

		determinePass(trueSlots, wins);

	}

	void receiverVsCornerback(){
		float recTotal = (mainPlayer.getAttributeAt(6) * 3) + (mainPlayer.getAttributeAt(5) + mainPlayer.getAttributeAt(0) + mainPlayer.getAttributeAt(3)) * 2 + mainPlayer.getAttributeAt(4) + mainPlayer.getAttributeAt(12);
		float cbTotal = 0;

		int numDefense = 0;
		int passBlocking = 0;
		int strength = 0;
		int awareness = 0;
		int tackling = 0;
		int speed = 0;
		int catching = 0;
		int agility = 0;

		for (int i = 0; i < defense.size(); i++){
			if (defense.elementAt(i).getPosition().equals("CB")){
				numDefense++;
				cbTotal += (defense.elementAt(i).getAttributeAt(3) * 3) + (defense.elementAt(i).getAttributeAt(14) * 4) + (defense.elementAt(i).getAttributeAt(1) + defense.elementAt(i).getAttributeAt(11)) * 2 + defense.elementAt(i).getAttributeAt(0) + defense.elementAt(i).getAttributeAt(12) + defense.elementAt(i).getAttributeAt(5);
				awareness += defense.elementAt(i).getAttributeAt(3);
				passBlocking += defense.elementAt(i).getAttributeAt(14);
				tackling += defense.elementAt(i).getAttributeAt(11);
				strength += defense.elementAt(i).getAttributeAt(1);
				speed += defense.elementAt(i).getAttributeAt(0);
				catching += defense.elementAt(i).getAttributeAt(12);
				agility += defense.elementAt(i).getAttributeAt(5);
			}
		}

		if ( (mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(0)) / 2 > (awareness + strength + passBlocking) / (numDefense * 3))
			recTotal += 5;
		else if ( (mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(0)) / 2 < (awareness + strength + passBlocking) / (numDefense * 3))
			cbTotal += 5;

		if ( mainPlayer.getAttributeAt(4) > passBlocking / numDefense)
			recTotal += 5;
		else if ( mainPlayer.getAttributeAt(4) < passBlocking / numDefense)
			cbTotal += 5;

		if ( (mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(5)) > (strength + tackling) / numDefense)
			recTotal += 5;
		else if ( (mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(5)) < (strength + tackling) / numDefense)
			cbTotal += 5;

		if ( (mainPlayer.getAttributeAt(0) + mainPlayer.getAttributeAt(3)) > (speed + awareness) / numDefense)
			recTotal += 5;
		else if ( (mainPlayer.getAttributeAt(0) + mainPlayer.getAttributeAt(3)) < (speed + awareness) / numDefense)
			cbTotal += 5;

		if ( (mainPlayer.getAttributeAt(12) + mainPlayer.getAttributeAt(3)) / 2 > (passBlocking + catching + awareness) / (3 * numDefense))
			recTotal += 5;
		else if ( (mainPlayer.getAttributeAt(12) + mainPlayer.getAttributeAt(3)) / 2 < (passBlocking + catching + awareness) / (3 * numDefense))
			cbTotal += 5;

		if ( (mainPlayer.getAttributeAt(5) + mainPlayer.getAttributeAt(6)) / 2 > (tackling + passBlocking + agility) / (3 * numDefense))
			recTotal += 5;
		else if ( (mainPlayer.getAttributeAt(5) + mainPlayer.getAttributeAt(6)) / 2 < (tackling + passBlocking + agility) / (3 * numDefense))
			cbTotal += 5;

		float total = recTotal / 11 + (cbTotal / (14 * numDefense));
		int trueSlots = (int)((recTotal / 11) / total * 100);

		boolean [] success = new boolean[100];

		for (int i = 0; i < 100; i++){
			if (i < trueSlots)
				success[i] = true;
			else
				success[i] = false;
		}

		int wins = 0;

		for (int i = 0; i < 1000; i++){
			int rand = (int)(Math.random() * 100);

			if (success[rand])
				wins++;			
		}

		determinePass(trueSlots, wins);

	}


	void determinePass(int trueSlots, int wins){
		System.out.println("Determining Pass: " + wins + " - " + play);

		if (play.equals("long")){
			if (wins > 540){
				yardsGained = (int)(Math.random() * 20 + 25);
				incomplete = false;
				if (wins < 570)
					tackled = true;

			}
			else if (wins > 380){
				incomplete = true;
			//	System.out.println("Incomplete1");
			}
			else{
				if (ballIsIntercepted(trueSlots)){
					yardsGained = (int)(Math.random() * 20 + 25);
					turnover = true;
					intercepted = true;
				}
				else{
					incomplete = true;
			//		System.out.println("Incomplete2");
				}
			}

			timeElapsed += (int)(Math.random() * 3 + 5);
		}
		else if (play.equals("mid")){
			if (wins > 480){
				yardsGained = (int)(Math.random() * 10 + 20);
				incomplete = false;
				if (wins < 520)
					tackled = true;
			}
			else if (wins > 350){
				incomplete = true;
			//	System.out.println("Incomplete3");
			}
			else{
				if (ballIsIntercepted(trueSlots)){
					yardsGained = (int)(Math.random() * 10 + 20);
					turnover = true;
					intercepted = true;
				}
				else{
					incomplete = true;
			//		System.out.println("Incomplete4");
				}
			}
			timeElapsed += (int)(Math.random() * 4 + 3);
		}
		else if (play.equals("short")){
			if (wins > 430){
				yardsGained += (int)(Math.random() * 5 + 10);
				incomplete = false;
				if (wins < 480)
					tackled = true;
			}
			else if (wins >= 330){
				incomplete = true;
			//	System.out.println("Incomplete5");
			}
			else{
				if (ballIsIntercepted(trueSlots)){
					yardsGained += (int)(Math.random() * 5 + 10);
					turnover = true;
					intercepted = true;
				}
				else{
					incomplete = true;
				//	System.out.println("Incomplete6");
				}
			}

			timeElapsed += (int)(Math.random() * 3 + 2);
		}
		else if (play.equals("screen")){

			if (wins > 400){
				yardsGained += (int)((Math.random() * 10)) + 1;
				incomplete = false;
				System.out.println(yardsGained);

				if (wins < 450)
					tackled = true;
			}
			else if (wins > 250){
				incomplete = true;
			//	System.out.println("Incomplete7");
			}
			else{
				if (ballIsIntercepted(trueSlots)){
					yardsGained += (int)(Math.random() * 5);
					turnover = true;
					intercepted = true;
				}
				else{
					incomplete = true;
				//	System.out.println("Incomplete8");
				}
			}
			timeElapsed += (int)(Math.random() * 2 + 2);
		}

		System.out.println("Yards Gained: Pass - " + yardsGained);

	}

	boolean ballIsIntercepted(int trueSlots){
		boolean [] success = new boolean[1000];

		for (int i = 0; i < 1000; i++){
			if (i < trueSlots)
				success[i] = true;
			else
				success[i] = false;
		}

		int wins = 0;

		for (int i = 0; i < 1000; i++){
			int rand = (int)(Math.random() * 1000);

			if (success[rand])
				wins++;			
		}

		if (wins < 250 && (play.equals("short") || play.equals("screen") ) )
			return true;
		else if (wins < 300 && play.equals("mid"))
			return true;
		else if (wins < 350 && play.equals("long"))
			return true;
		else
			return false;


	}

	void returnKick(){
		float recTotal = (mainPlayer.getAttributeAt(6) * 3) + (mainPlayer.getAttributeAt(5) + mainPlayer.getAttributeAt(0) + mainPlayer.getAttributeAt(3)) * 2 + mainPlayer.getAttributeAt(4) + mainPlayer.getAttributeAt(12);
		float cbTotal = 0;

		int numDefense = 0;
		int passBlocking = 0;
		int strength = 0;
		int awareness = 0;
		int tackling = 0;
		int speed = 0;
		int catching = 0;
		int agility = 0;

		for (int i = 0; i < defense.size(); i++){
			if (defense.elementAt(i).getPosition().equals("CB")){
				numDefense++;
				cbTotal += (defense.elementAt(i).getAttributeAt(3) * 3) + (defense.elementAt(i).getAttributeAt(14) * 4) + (defense.elementAt(i).getAttributeAt(1) + defense.elementAt(i).getAttributeAt(11)) * 2 + defense.elementAt(i).getAttributeAt(0) + defense.elementAt(i).getAttributeAt(12) + defense.elementAt(i).getAttributeAt(5);
				awareness += defense.elementAt(i).getAttributeAt(3);
				passBlocking += defense.elementAt(i).getAttributeAt(14);
				tackling += defense.elementAt(i).getAttributeAt(11);
				strength += defense.elementAt(i).getAttributeAt(1);
				speed += defense.elementAt(i).getAttributeAt(0);
				catching += defense.elementAt(i).getAttributeAt(12);
				agility += defense.elementAt(i).getAttributeAt(5);
			}
		}

		if ( (mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(0)) / 2 > (awareness + strength + passBlocking) / (numDefense * 3))
			recTotal += 5;
		else if ( (mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(0)) / 2 < (awareness + strength + passBlocking) / (numDefense * 3))
			cbTotal += 5;

		if ( mainPlayer.getAttributeAt(4) > passBlocking / numDefense)
			recTotal += 5;
		else if ( mainPlayer.getAttributeAt(4) < passBlocking / numDefense)
			cbTotal += 5;

		if ( (mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(5)) > (strength + tackling) / numDefense)
			recTotal += 5;
		else if ( (mainPlayer.getAttributeAt(6) + mainPlayer.getAttributeAt(5)) < (strength + tackling) / numDefense)
			cbTotal += 5;

		if ( (mainPlayer.getAttributeAt(0) + mainPlayer.getAttributeAt(3)) > (speed + awareness) / numDefense)
			recTotal += 5;
		else if ( (mainPlayer.getAttributeAt(0) + mainPlayer.getAttributeAt(3)) < (speed + awareness) / numDefense)
			cbTotal += 5;

		if ( (mainPlayer.getAttributeAt(12) + mainPlayer.getAttributeAt(3)) / 2 > (passBlocking + catching + awareness) / (3 * numDefense))
			recTotal += 5;
		else if ( (mainPlayer.getAttributeAt(12) + mainPlayer.getAttributeAt(3)) / 2 < (passBlocking + catching + awareness) / (3 * numDefense))
			cbTotal += 5;

		if ( (mainPlayer.getAttributeAt(5) + mainPlayer.getAttributeAt(6)) / 2 > (tackling + passBlocking + agility) / (3 * numDefense))
			recTotal += 5;
		else if ( (mainPlayer.getAttributeAt(5) + mainPlayer.getAttributeAt(6)) / 2 < (tackling + passBlocking + agility) / (3 * numDefense))
			cbTotal += 5;

		float total = recTotal / 11 + (cbTotal / (14 * numDefense));
		int trueSlots = (int)((recTotal / 11) / total * 100);

		boolean [] success = new boolean[100];

		for (int i = 0; i < 100; i++){
			if (i < trueSlots)
				success[i] = true;
			else
				success[i] = false;
		}

		int wins = 0;

		for (int i = 0; i < 1000; i++){
			int rand = (int)(Math.random() * 100);

			if (success[rand])
				wins++;			
		}

		if (!play.equals("Punt"))
			yardsGained = (int)(Math.random() * 5 + 5);
		else
			yardsGained = (int)(Math.random() * 5);

		if (wins > 500){
			yardsGained += 6;
			tackled = false;
		}
		else if (wins <= 550){
			tackled = true;

			yardsGained += (wins - 450) / 20;

			if (wins < 425){
				if (fumbledBall(trueSlots))
					turnover = true;
			}

			if (yardsGained < 0)
				yardsGained = 0;
		}

		if (!tackled)
			runnervsLB();
		if (!tackled)
			runnervsCB();

	}


	boolean kickIsSuccessful(){
		float kickerTotal = mainPlayer.getAttributeAt(3);
		float defenseTotal = 0;

		int numDefense = 0;

		for (int i = 0; i < defense.size(); i++){
			if (defense.elementAt(i).getPosition().equals("DL") || defense.elementAt(i).getPosition().equals("CB")){
				numDefense++;
				defenseTotal += (defense.elementAt(i).getAttributeAt(0) + defense.elementAt(i).getAttributeAt(4) + defense.elementAt(i).getAttributeAt(14));
			}
		}

		defenseTotal += (advantage * numDefense);

		if (advantage == 0)
			kickerTotal += 5;

		if (kickerTotal > (defenseTotal / numDefense))
			kickerTotal += 5;
		else if ((defenseTotal / numDefense) > kickerTotal)
			defenseTotal += (5 * numDefense);


		int trueSlots = (int)(kickerTotal / (kickerTotal + (defenseTotal / numDefense)) * 100);

		boolean [] success = new boolean[1000];

		for (int i = 0; i < 1000; i++){
			if (i < trueSlots)
				success[i] = true;
			else
				success[i] = false;
		}

		int wins = 0;

		for (int i = 0; i < 1000; i++){
			int rand = (int)(Math.random() * 100);
			if (success[rand])
				wins++;
		}

		// If kicker wins majority of plays, kick is successful...otherwise it is blocked.
		if (wins > 400)
			return true;
		else
			return false;




	}

	boolean fumbledBall(int trueSlots){
		System.out.println("Testing fumble rules");

		boolean [] success = new boolean[1000];

		for (int i = 0; i < 1000; i++){
			if (i < trueSlots)
				success[i] = true;
			else
				success[i] = false;
		}

		int wins = 0;

		for (int i = 0; i < 1000; i++){
			int rand = (int)(Math.random() * 1000);
			if (success[rand])
				wins++;
		}

  int wildcard = (int)(Math.random() * 100);

		if (wins < 400 || wildcard == 86){
			fumbledBall = true;
			// Ball is fumbled...but was it lost?
			if (wins < 375)
				return true;
			else
				return false;
		}
		else
			return false;

	}




	void determineKickDistance(){
// kp = 9, ka = 10
		if (play.equals("Punt"))
			kickDistance = (int)(Math.random() * 12 + (45 - ( (100 - mainPlayer.getAttributeAt(9) ) / 2) ) );
		else if (play.equals("Field_Goal"))
			kickDistance = (int)(Math.random() * 17 + (45 - ( (100 - mainPlayer.getAttributeAt(9) ) / 2)  ) );
		else if (play.equals("Kickoff"))
			kickDistance = (int)(Math.random() * 20 + (50 - ( (100 - mainPlayer.getAttributeAt(9) ) / 2) ) );
		else if (play.equals("Onside"))
			kickDistance = (int)(Math.random() * 4 + 8);
	}

	void determineKickAccuracy(){
		int sum = 0;
		for (int i = 0; i < 10; i++){
			sum += (int)(Math.random() * (27 + ( (100 - mainPlayer.getAttributeAt(10) ) / 2) ) + 38);
		}

		kickAccuracy = sum / 10;
	}


	// For onside kicks only
	int determineKickRecovery(){
		int kickingTotal = 0;
		int receivingTotal = 0;

		int [] awareness = new int[2];
		int [] catching = new int[2];
		int [] tackling = new int[2];
		int [] passBlocking = new int[2];
		int [] speed = new int[2];
		int [] elusiveness = new int[2];

		int maxPlayers = 0;

		// Need WR, CB, LB for each team and Kicker for kicking team (Total = 11)
		// Attributes determining result = Awareness, Catching, Tackling, Pass Blocking, Speed, Elusiveness

		int total = 0;

		for (int i = 0; i < defense.size(); i++){
			if (defense.elementAt(i).getPosition().equals("WR") || defense.elementAt(i).getPosition().equals("CB") || defense.elementAt(i).getPosition().equals("LB")){
				if (total < 11){
					total++;
					receivingTotal += (defense.elementAt(i).getAttributeAt(0) + defense.elementAt(i).getAttributeAt(3) + defense.elementAt(i).getAttributeAt(6)
					+ defense.elementAt(i).getAttributeAt(11) + defense.elementAt(i).getAttributeAt(12) + defense.elementAt(i).getAttributeAt(14));

					awareness[0] += defense.elementAt(i).getAttributeAt(3);
					speed[0] += defense.elementAt(i).getAttributeAt(0);
					elusiveness[0] += defense.elementAt(i).getAttributeAt(6);
					catching[0] += defense.elementAt(i).getAttributeAt(12);
					tackling[0] += defense.elementAt(i).getAttributeAt(11);
					passBlocking[0] += defense.elementAt(i).getAttributeAt(14);
				}
			}
		}

		if (total < 11)
			maxPlayers = total;

		total = 0;

		for (int i = 0; i < offense.size(); i++){
			if (offense.elementAt(i).getPosition().equals("WR") || offense.elementAt(i).getPosition().equals("CB") || offense.elementAt(i).getPosition().equals("LB")){
				if (total < (maxPlayers - 1)){
					total++;
					kickingTotal += (offense.elementAt(i).getAttributeAt(0) + offense.elementAt(i).getAttributeAt(3) + offense.elementAt(i).getAttributeAt(6)
					+ offense.elementAt(i).getAttributeAt(11) + offense.elementAt(i).getAttributeAt(12) + offense.elementAt(i).getAttributeAt(14));

					awareness[1] += offense.elementAt(i).getAttributeAt(3);
					speed[1] += offense.elementAt(i).getAttributeAt(0);
					elusiveness[1] += offense.elementAt(i).getAttributeAt(6);
					catching[1] += offense.elementAt(i).getAttributeAt(12);
					tackling[1] += offense.elementAt(i).getAttributeAt(11);
					passBlocking[1] += offense.elementAt(i).getAttributeAt(14);
				}
			}
		}

		boolean found = false;

		for (int i = 0; i < offense.size(); i++){
			if (offense.elementAt(i).getPosition().equals("K") && !found){
				found = true;
				kickingTotal += (offense.elementAt(i).getAttributeAt(0) + offense.elementAt(i).getAttributeAt(3) + offense.elementAt(i).getAttributeAt(6)
				+ offense.elementAt(i).getAttributeAt(11) + offense.elementAt(i).getAttributeAt(12) + offense.elementAt(i).getAttributeAt(14));	

				awareness[1] += offense.elementAt(i).getAttributeAt(3);
				speed[1] += offense.elementAt(i).getAttributeAt(0);
				elusiveness[1] += offense.elementAt(i).getAttributeAt(6);
				catching[1] += offense.elementAt(i).getAttributeAt(12);
				tackling[1] += offense.elementAt(i).getAttributeAt(11);
				passBlocking[1] += offense.elementAt(i).getAttributeAt(14);
			}
		}

		// Determinate #1 = Awareness [3] + Catching [12] (20)
		// Determinate #2 = Tackling [11] + Catching (10)
		// Determinate #3 = Speed [0] + Elusiveness [6] (8)
		// Determinate #4 = Pass Blocking [14] + Awareness (8)


		if ( (awareness[0] + catching[0]) > (awareness[1] + catching[1]) )
			receivingTotal += 20;
		else if ( (awareness[0] + catching[0]) < (awareness[1] + catching[1]) )
			kickingTotal += 20;

		if ( (tackling[0] + catching[0]) > (tackling[1] + catching[1]) )
			receivingTotal += 10;
		else if ( (tackling[0] + catching[0]) < (tackling[1] + catching[1]) )
			kickingTotal += 10;

		if ( (speed[0] + elusiveness[0]) > (speed[1] + elusiveness[1]) )
			receivingTotal += 8;
		else if ( (speed[0] + elusiveness[0]) < (speed[1] + elusiveness[1]) )
			kickingTotal += 8;

		if ( (passBlocking[0] + awareness[0]) > (passBlocking[1] + awareness[1]) )
			receivingTotal += 8;
		else if ( (passBlocking[0] + awareness[0]) < (passBlocking[1] + awareness[1]) )
			kickingTotal += 8;


		// Perform standard combinations
		int trueSlots = (int)(kickingTotal / (kickingTotal + receivingTotal) * 100);

		boolean [] success = new boolean[1000];

		for (int i = 0; i < 1000; i++){
			if (i < trueSlots)
				success[i] = true;
			else
				success[i] = false;
		}

		int wins = 0;

		for (int i = 0; i < 1000; i++){
			int rand = (int)(Math.random() * 100);
			if (success[rand])
				wins++;
		}

		// If kicking team wins majority of plays, offense recovers.  Otherwise, return team recovers.
		if (wins > 500)
			return 1;
		else
			return 2;
	}



	int getPlayTime(){
	//	timeElapsed = (int)(Math.random() * 7 + 33);
		return timeElapsed;

		/*
			When the timer system is fully in place, the above will be removed and replaced by a simple return
		*/
	}

	boolean isBallKicked(){
		return ballKicked;
	}

	int getKickAccuracy(){
		return kickAccuracy;
	}

	int getKickDistance(){
		return kickDistance;
	}

	String getPlayType(){
		return play;
	}



}
