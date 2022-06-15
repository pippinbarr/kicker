import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Climate{

	int monthValue = 0;
	int dayValue = 0;
  int yearValue = 0;
	int hourValue = 0;

  double latitude = 0.0;
  double longitude = 0.0;
  int timeOffset = 0;

	int index = 0;

	String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	double [] highTemperature = new double[12];
	double [] lowTemperature = new double[12];
	double [] averageRainfall = new double[12];
	double [] averageSnowfall = new double[12];
	double [] averageWindSpeed = new double[12];
	int elevation = 0;

	boolean isDomed = false;

	double currentTemperature = 0.0;
	double windChill = 0.0;
	double currentPrecipitation = 0;
	String currentWeather = "";
  ImageIcon weatherIcon;

	double currentWindSpeed = 0.0;
	boolean itIsSnowing = false;
	boolean itIsCloudy = false;


	public Climate(String climateName, int... dateValues){
    int totalValues = 0;
     for (int i: dateValues)
       totalValues++;


		for (int i = 0; i < 12; i++){
			highTemperature[i] = 0.0;
			lowTemperature[i] = 0.0;
			averageRainfall[i] = 0.0;
			averageSnowfall[i] = 0.0;
			averageWindSpeed[i] = 0.0;
		}


		createClimate(climateName);

		TimeZone timeZone = TimeZone.getTimeZone("America/New_York");

		Calendar now = Calendar.getInstance(timeZone, Locale.US);
//		monthValue = Calendar.MONTH;

     if(totalValues == 0){
		monthValue = (int)(Math.random() * 12);
		dayValue = (int)(Math.random() * 27 + 1);
		hourValue = (int)(Math.random() * 9 + 9);
   }
     else{
       monthValue = dateValues[0];
       dayValue = dateValues[1];
       hourValue = dateValues[2];
     }
    yearValue = 2011;

//		System.out.println(now.getTime());

  // 	if (monthValue < 2 || monthValue > 6)
	  // 	if (monthValue < 2)
		  // 	index = monthValue + 5;
	  // 	else
		  // 	index = monthValue - 7;

		determineCurrentWeather(monthValue, dayValue, hourValue);
	}


	void createClimate(String filename){

		// File name is gathered from the team's data file.
		filename += ".ci";
		File file = new File("teams/Climates/" + filename);

		try{
			Scanner in = new Scanner(file);

			// The first 5 values are for months 7-11, the last 2 are for 0 and 1

			for (int i = 7; i < 12; i++){
				lowTemperature[i] = in.nextDouble();
				highTemperature[i] = in.nextDouble();
				averageRainfall[i] = in.nextDouble();
				averageSnowfall[i] = in.nextDouble();
				averageWindSpeed[i] = in.nextDouble();
			}

			for (int i = 0; i < 2; i++){
				lowTemperature[i] = in.nextDouble();
				highTemperature[i] = in.nextDouble();
				averageRainfall[i] = in.nextDouble();
				averageSnowfall[i] = in.nextDouble();
				averageWindSpeed[i] = in.nextDouble();
			}

				elevation = in.nextInt();
        latitude = in.nextDouble();
        longitude = in.nextDouble();
        offset = in.nextInt();

			in.close();
		}catch(FileNotFoundException fe){
			fe.printStackTrace();
		}

		insertNonPlayingMonthData();

		if (filename.equals("Domed.ci")){
			isDomed = true;
		}

	}

	void insertNonPlayingMonthData(){
		// For months #2-6

		// Temps for March = February temps + 5%
		lowTemperature[2] = lowTemperature[1] * 1.05;
		highTemperature[2] = highTemperature[1] * 1.05;

		// Temps for April = March temps + 10%
		lowTemperature[3] = lowTemperature[2] * 1.1;
		highTemperature[3] = highTemperature[2] * 1.1;

		// Temps for May = April temps + 5%
		lowTemperature[4] = lowTemperature[3] * 1.05;
		highTemperature[4] = highTemperature[3] * 1.05;

		// Temps for June = May temps + 15%
		lowTemperature[5] = lowTemperature[4] * 1.15;
		highTemperature[5] = highTemperature[4] * 1.15;

		// Temps for July = August temps - 5%
		lowTemperature[6] = lowTemperature[7] * 0.95;
		highTemperature[6] = highTemperature[7] * 0.95;

		// Rainfall for March = February + 20%
		averageRainfall[2] = averageRainfall[1] * 1.2;
		// Rainfall for April = March + 60%
		averageRainfall[3] = averageRainfall[2] * 1.6;
		// Rainfall for May = April - 30%
		averageRainfall[4] = averageRainfall[3] * 1.3;
		// Rainfall for June = May - 10%
		averageRainfall[5] = averageRainfall[4] * 1.1;
		// Rainfall for July = August + 5%
		averageRainfall[6] = averageRainfall[7] * 1.05;


		// Snowfall for March = February - 10%
		averageSnowfall[2] = averageSnowfall[1] * 0.90;
		// Snowfall for April = March - 30%
		averageSnowfall[3] = averageSnowfall[2] * 0.70;
		// Snowfall for May-July = 0.0
		for (int i = 4; i < 7; i++)
			averageSnowfall[i] = 0.0;

		// Wind Speeds for March-May = Previous month + 5-8%
		for (int i = 2; i < 5; i++)
			averageWindSpeed[i] = averageWindSpeed[i-1] * (1.00 + (double)((int)(Math.random() * 3 + 5) / 100));

		// Wind Speeds for June-July = Previous month - 10%
		averageWindSpeed[5] = averageWindSpeed[4] * 0.90;
		averageWindSpeed[6] = averageWindSpeed[5] * 0.90;
	}

	void determineCurrentWeather(int monthIndex, int day, int hour){

		int daysInMonth = 0;

		if (monthIndex != 1 && monthIndex != 3 && monthIndex != 5 && monthIndex != 8 && monthIndex != 10)
			daysInMonth = 31;
		else if (monthIndex != 1)
			daysInMonth = 30;
		else
			daysInMonth = 28;

		double [] dailyHighs = new double[daysInMonth];
		double [] dailyLows = new double[daysInMonth];

		dailyHighs[0] = highTemperature[monthIndex] + (Math.random() * 10 - 5);
		dailyLows[0] = lowTemperature[monthIndex] + (Math.random() * 10 - 5);

		double highValueSum = dailyHighs[0];
		double lowValueSum = dailyLows[0];

		double highAvg = 0.0;
		double lowAvg = 0.0;


		for (int i = 1; i < 5; i++){
			dailyHighs[i] = dailyHighs[i-1] + (Math.random() * 6 - 3);
			dailyLows[i] = dailyLows[i-1] + (Math.random() * 6 - 3);


			highValueSum += dailyHighs[i];
			lowValueSum += dailyLows[i];

			highAvg = highValueSum / (i + 1);
			lowAvg = lowValueSum / (i + 1);
		}

		for (int i = 5; i < daysInMonth; i++){
			if (highAvg > highTemperature[monthIndex])
				dailyHighs[i] = dailyHighs[i-1] + (Math.random() * 6 - 5);
			else if (highAvg < highTemperature[monthIndex])
				dailyHighs[i] = dailyHighs[i-1] + (Math.random() * 6 - 2);
			else
				dailyHighs[i] = dailyHighs[i-1] + (Math.random() * 6 - 3);


			if (lowAvg > lowTemperature[monthIndex])
				dailyLows[i] = dailyLows[i-1] + (Math.random() * 6 - 5);
			else if (lowAvg < lowTemperature[monthIndex])
				dailyLows[i] = dailyLows[i-1] + (Math.random() * 6 - 2);
			else
				dailyLows[i] = dailyLows[i-1] + (Math.random() * 6 - 3);


			highValueSum += dailyHighs[i];
			highAvg = highValueSum / (i + 1);

			lowValueSum += dailyLows[i];
			lowAvg = lowValueSum / (i + 1);

		}

		DecimalFormat oneDForm = new DecimalFormat("#.#");

	//	for (int i = 0; i < daysInMonth; i++){
			System.out.println("Day of Month: " + day + "  High Temp: " + oneDForm.format(dailyHighs[day-1]) + "  Low Temp: " + oneDForm.format(dailyLows[day-1]) );
	//	}


		double tempRange = dailyHighs[day - 1] - dailyLows[day - 1];

		// Games can be played between 9am and 9pm (12 hour range)




		int currentHour = hour;

		if (currentHour < 9)
			currentHour = 9;
		else if (currentHour > 21)
			currentHour = 21;


			if (monthValue > 5 && monthValue <= 8){
			// Summer (July-September)

				// For ease of computations, temperatures will be decided in ranges from 3am-3pm and 3pm-9pm
				if (currentHour <= 15)
					currentTemperature = (tempRange / 12) * (currentHour - 3) + (Math.random() * 3 - 1) + dailyLows[day - 1];

				else if (currentHour >= 16)
					currentTemperature = dailyHighs[day - 1] - ( (tempRange / 15) * (currentHour - 16) ) + (Math.random() * 3 - 2);

			}
			else if (monthValue > 8 && monthValue <= 11){
			// Autumn (October-December)
				// For ease of computations, temperatures will be decided in ranges from 3am-3pm and 3pm-9pm
				if (currentHour <= 14)
					currentTemperature = (tempRange / 9) * (currentHour - 5) + (Math.random() * 3 - 1) + dailyLows[day - 1];

				else if (currentHour >= 15)
					currentTemperature = dailyHighs[day - 1] - ( (tempRange / 7) * (currentHour - 15) ) + (Math.random() * 3 - 2);

			}
			else if (monthValue > 2 && monthValue <= 5){
			// Spring (April-June)
				// For ease of computations, temperatures will be decided in ranges from 3am-3pm and 3pm-9pm
				if (currentHour <= 15)
					currentTemperature = (tempRange / 11) * (currentHour - 4) + (Math.random() * 3 - 1) + dailyLows[day - 1];

				else if (currentHour >= 16)
					currentTemperature = dailyHighs[day - 1] - ( (tempRange / 10) * (currentHour - 16) ) + (Math.random() * 3 - 2);

			}
			else{
			// Winter (January-March)
				// For ease of computations, temperatures will be decided in ranges from 3am-3pm and 3pm-9pm
				if (currentHour <= 14)
					currentTemperature = (tempRange / 10) * (currentHour - 5) + (Math.random() * 3 - 1) + dailyLows[day - 1];

				else if (currentHour >= 15)
					currentTemperature = dailyHighs[day - 1] - ( (tempRange / 6) * (currentHour - 15) ) + (Math.random() * 3 - 2);
			}




			if (currentTemperature > dailyHighs[day - 1])
				currentTemperature = dailyHighs[day - 1];

			else if (currentTemperature < dailyLows[day - 1])
				currentTemperature = dailyLows[day - 1];


			currentWindSpeed = Math.random() * (2 * averageWindSpeed[monthIndex]);

			// Math.pow(num,power)


			if (currentWindSpeed < 3.0 || currentTemperature > 50.0)
				windChill = currentTemperature;
			else
				// Actual NWS formula
				windChill = 35.74 + (0.6215 * currentTemperature) - 35.75 * (Math.pow(currentWindSpeed, 0.16)) + 0.4275 * (currentTemperature * (Math.pow(currentWindSpeed, 0.16))) ;

			if (isDomed){
				currentWindSpeed = 0.0;
				currentTemperature = 72.0;
			}


			System.out.println("System High Average: " + highTemperature[monthIndex] + "   Accrued High Average: " + oneDForm.format(highAvg) );
			System.out.println("System Low Average: " + lowTemperature[monthIndex] + "   Accrued Low Average: " + oneDForm.format(lowAvg) );
			System.out.println("Current Hour: " + currentHour + ":00    Current Temperature: " + oneDForm.format(currentTemperature) + "  Wind Speed: " + oneDForm.format(currentWindSpeed) + " mph   Wind Chill: " + oneDForm.format(windChill) );


			determineCurrentConditions(monthIndex);
	}


	void determineCurrentConditions(int monthIndex){

		boolean snowAvailable = false;
    boolean isNightTime = false;
    
    Sunrise riseSet = new Sunrise(monthValue, dayValue, yearValue, latitude, longitude, offset);
    
    if (riseSet.getSunrise() + 0.5 <= hourValue || riseSet.getSunset() - 0.5 <= hourValue)
      isNightTime = true;

		if (currentTemperature <= 40)
			snowAvailable = true;

		double total = Math.random() * (averageRainfall[monthIndex] + 1) - 1;
		double snowTotal = 0.0;

		if (snowAvailable)
			snowTotal = Math.random() * (averageSnowfall[monthIndex] + 1) - 1;

     if (total < 0){
       currentWeather = "Clear";
       if (isNightTime)
          weatherIcon = new ImageIcon("images/weather/ClearNight.png");
       else
          weatherIcon = new ImageIcon("images/weather/ClearDay.png"); 
     }
     else if (total < 0.5){
			currentWeather = "Cloudy";
         
      if (isNightTime)
          weatherIcon = new ImageIcon("images/weather/CloudyNight.png");
      else
          weatherIcon = new ImageIcon("images/weather/CloudyDay.png"); 
     }
		else if (total < 1.0){

        if (snowTotal > 0.5 && snowTotal < 1.0){
				currentWeather = "Wintry Mix";
        if (isNightTime)
          weatherIcon = new ImageIcon(getClass().getResource("images/weather/WintryMixNight.png"));
       else
          weatherIcon = new ImageIcon(getClass().getResource("images/weather/WintryMixDay.png")); 
        }
        else if (snowTotal > 1.0){
				currentWeather = "Sleet";
        if (isNightTime)
          weatherIcon = new ImageIcon(getClass().getResource("images/weather/SleetNight.png"));
       else
          weatherIcon = new ImageIcon(getClass().getResource("images/weather/SleetDay.png"));     
            
        }
        else{
				currentWeather = "Light Rain";
        if (isNightTime)
          weatherIcon = new ImageIcon(getClass().getResource("images/weather/LightRainNight.png"));
        else
          weatherIcon = new ImageIcon(getClass().getResource("images/weather/LightRainDay.png"));     
            
        }
		}
		else if (total < 2.0){
			if (snowAvailable){
           if (snowTotal > 1.0){
                 currentWeather = "Light Snow";
                 if (isNightTime)
                    weatherIcon = new ImageIcon(getClass().getResource("images/weather/LightSnowNight.png"));
                 else
                    weatherIcon = new ImageIcon(getClass().getResource("images/weather/LightSnowDay.png")); 
               
           }
           else if (snowTotal > 2.0){
                 currentWeather = "Snow Showers";
                 if (isNightTime)
                    weatherIcon = new ImageIcon(getClass().getResource("images/weather/SteadySnowNight.png"));
                 else
                    weatherIcon = new ImageIcon(getClass().getResource("images/weather/SteadySnowDay.png")); 
               
           }
           else{
                 currentWeather = "Freezing Rain";
                 if (isNightTime)
                    weatherIcon = new ImageIcon(getClass().getResource("images/weather/FreezingRainNight.png"));
                 else
                    weatherIcon = new ImageIcon(getClass().getResource("images/weather/FreezingRainDay.png")); 
               
           }
			}
        else{
				currentWeather = "Showers";
            if (isNightTime)
              weatherIcon = new ImageIcon(getClass().getResource("images/weather/SteadyRainNight.png"));
            else
              weatherIcon = new ImageIcon(getClass().getResource("images/weather/SteadyRainDay.png")); 
            
        }
		}
		else{
        if (currentTemperature >= 80){
              currentWeather = "Thunderstorms";
              weatherIcon = new ImageIcon(getClass().getResource("images/weather/Thunderstorm.png"));

              
        }
        else if (snowAvailable && snowTotal > 2.0){
              currentWeather = "Heavy Snow";
              if (isNightTime)
                weatherIcon = new ImageIcon(getClass().getResource("images/weather/SteadySnowNight.png"));
              else
                weatherIcon = new ImageIcon(getClass().getResource("images/weather/SteadySnowDay.png")); 
            
        }
        else{
              currentWeather = "Heavy Rain";
              if (isNightTime)
                weatherIcon = new ImageIcon(getClass().getResource("images/weather/SteadyRainNight.png"));
              else
                weatherIcon = new ImageIcon(getClass().getResource("images/weather/SteadyRainDay.png")); 
        }
		}

     if (isDomed){
          currentWeather = "Clear";
          if (isNightTime)
            weatherIcon = new ImageIcon(getClass().getResource("images/weather/ClearDay.png"));
          else
            weatherIcon = new ImageIcon(getClass().getResource("images/weather/ClearNight.png"));   
     }

		System.out.println("Current Weather : " + currentWeather);


	}
   
  ImageIcon getWeatherIcon(){
    return weatherIcon;
  }

	double getCurrentTemperature(){

		return currentTemperature;
	}

	double getWindSpeed(){

		return currentWindSpeed;
	}

	String getWeatherConditions(){
		return currentWeather;
	}

	int getElevation(){
		return elevation;
	}



}