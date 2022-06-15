
public class Sunrise{

	static int day = 0;
	static int month = 0;
	static int year = 0;
	static int offset = 0;
	
	static double latitude = 0.0;
	static double longitude = 0.0;
	
	static int dayOfYear = 0;
	static double longHour = 0.0;

	static double approxTime = 0.0;
	
	static double meanAnomaly = 0.0;
	
	static double trueLongitude = 0.0;
	static double rightAscension = 0.0;
	
	static double sinDec = 0.0;
	static double cosDec = 0.0;
	
	static double localHourAngle = 0.0;
	
	static double localMeanTime = 0.0;
	static double localTime = 0.0;
	
	static boolean neverRises = false;
	static boolean neverSets = false;

	static String sunriseTime = "";
	static String sunsetTime = "";

	public void Sunrise(int m, int d, int yr, int off, double lat, double lng){
		month = m;
		day = d;
		year = yr;
		offset = off;
		latitude = lat;
		longitude = lng;
	}

	double getSunriseTime(){
		calculateSunrise();
		return localTime;
	}

	double getSunsetTime(){
		calculateSunset();
		return localTime;
	}


	static void calculateSunrise(){
		getDayOfYear();
		setApproximateTime(true);
		setMeanAnomaly();
		setTrueLongitude();
		setRightAscension();
		setSunDeclination();
		setSunLocalHourAngle(true);
		setLocalMeanTime();
		setLocalTime();
		calculateTime(true);		

		if (neverRises)
			System.out.println("Sun does not rise on this day");
		else
			System.out.println("Sunrise is at : " + sunriseTime);
	}
	
	static void calculateSunset(){
		getDayOfYear();
		setApproximateTime(false);
		setMeanAnomaly();
		setTrueLongitude();
		setRightAscension();
		setSunDeclination();
		setSunLocalHourAngle(false);
		setLocalMeanTime();
		setLocalTime();
		calculateTime(false);

		if (neverSets)
			System.out.println("Sun does not set on this day");
		else				
			System.out.println("Sunset is at : " + sunsetTime);
	}
	
	static void getDayOfYear(){
		int tmp1 = (275 * month) / 9;
		int tmp2 = (month + 9) / 12;
		int tmp3 = (1 + (year - 4 * (year / 4) + 2) / 3);
		
		dayOfYear = tmp1 - (tmp2 * tmp3) + day - 30;

	}
	
	static void setApproximateTime(boolean sunrise){
		longHour = longitude / 15;
		
		if (sunrise)
			approxTime = dayOfYear + ((6 - longHour) / 24);
		else
			approxTime = dayOfYear + ((18 - longHour) / 24);

	//	System.out.println("t: " + approxTime);			

	}
	
	static void setMeanAnomaly(){
		meanAnomaly = (0.9856 * approxTime) - 3.289;

	//	System.out.println("M: " + meanAnomaly);
	}
	
	static void setTrueLongitude(){
		trueLongitude = (meanAnomaly + (1.916 * Math.sin(Math.toRadians(meanAnomaly))) + (0.020 * Math.sin(Math.toRadians(2 * meanAnomaly))) + 282.634) % 360.0;
	//	System.out.println("L: " + trueLongitude);
	}
	
	static void setRightAscension(){
		rightAscension = Math.toDegrees(Math.atan(0.91764 * Math.tan(Math.toRadians(trueLongitude)) ));

		if (rightAscension < 0)
			rightAscension += 360;
		else if (rightAscension > 360)
			rightAscension -= 360;
	
		int tmpLeft = (int)(trueLongitude / 90) * 90;
		int tmpRight = (int)(rightAscension / 90) * 90;
		
		rightAscension += (tmpLeft - tmpRight);
		
		rightAscension = rightAscension / 15;
		
	//	System.out.println("RA: " + rightAscension);
	}
	
	static void setSunDeclination(){
	//	System.out.println(trueLongitude);
		sinDec = 0.39782 * Math.sin(Math.toRadians(trueLongitude));
		cosDec = Math.cos(Math.asin(sinDec));
		
	//	System.out.println("Sin " + sinDec + "  Cos " + cosDec);
	}
	
	static void setSunLocalHourAngle(boolean sunrise){
		double cosH = (Math.cos(Math.toRadians(90.833)) - (sinDec * Math.sin(Math.toRadians(latitude)))) / (cosDec * Math.cos(Math.toRadians(latitude)));
		
	//	System.out.println("CosH: " + cosH);
		
		if (cosH > 1)
			neverRises = true;
		if (cosH < -1)
			neverSets = true;

		if (neverRises || neverSets)
			return;
			
		if (sunrise)
			localHourAngle = 360 - Math.toDegrees(Math.acos(cosH));
		else
			localHourAngle = Math.toDegrees(Math.acos(cosH));
	
			
		localHourAngle = localHourAngle / 15;
		
	//	System.out.println("H: " + localHourAngle);
	}
	
	static void setLocalMeanTime(){
		localMeanTime = localHourAngle + rightAscension - (0.06571 * approxTime) - 6.622;
	//	System.out.println("T: " + localMeanTime);
	}
	
	static void setLocalTime(){
		double utc = (localMeanTime - longHour);

	//	System.out.println("UTC: " + utc);
		localTime = utc + offset;

		if (localTime < 0)
			localTime += 24.0;
		else if (localTime > 24)
			localTime -= 24.0;
	//	System.out.println("Local Time: " + localTime);
	}

	static void calculateTime(boolean sunrise){
		String second;
		String minute;
		boolean pastNoon = false;

		int hour = (int)localTime;
		int seconds = (int)((localTime - hour) * 3600);
		int minutes = seconds / 60;
		seconds = seconds - (minutes * 60);

	//	System.out.println("Hour: " + hour + "  Seconds: " + seconds);
		if (hour > 12)
			pastNoon = true;

		if (hour == 0)
			hour = 12;
		else if (hour > 12)
			hour -= 12;

		if (seconds >= 10)
			second = String.valueOf(seconds);
		else
			second = "0" + String.valueOf(seconds);

		if (minutes >= 10)
			minute = String.valueOf(minutes);
		else
			minute = "0" + String.valueOf(minutes);

		if (sunrise){
			sunriseTime = hour + ":" + minute + ":" + second;

			if (pastNoon)
				sunriseTime += " PM";
			else
				sunriseTime += " AM";
		}
		else{
			sunsetTime = hour + ":" + minute + ":" + second;

			if (pastNoon)
				sunsetTime += " PM";
			else
				sunsetTime += " AM";
		}

	}
	
	public static void main(String [] args){
		month = Integer.parseInt(args[0]);
		day = Integer.parseInt(args[1]);
		year = Integer.parseInt(args[2]);
		offset = Integer.parseInt(args[3]);
		
		latitude = Double.parseDouble(args[4]);
		longitude = Double.parseDouble(args[5]);
	
		calculateSunrise();
		calculateSunset();
	}

}
