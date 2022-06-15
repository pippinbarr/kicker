import java.io.*;
import java.util.*;

public class Records{

        boolean newRecordMade = false;
        int [] records = new int[13];
        String [] playerHoldingRecord = new String[13];
        String [] teamHoldingRecord = new String[13];
        String [] recordMadeAgainst = new String[13];
        String [] recordDate = new String[13]
        boolean [] isTeamRecord = new boolean[13];
        String [] recordValues = {"Most Points Scored", "Most Passing Yards in Game", "Most Rushing Yards in Game", "Most Return Yards In Game",
                                                                                "Longest Pass from Scrimmage", "Longest Rush from Scrimmage", "Longest Punt Return from Scrimmage",
                                                                                "Longest Kick Return from Scrimmage", "Longest Punt", "Longest Field Goal Made", "Most Interceptions Caught",
                                                                                "Longest Interception Return", "Longest Fumble Return"};
        String [] months = {"", "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
                                                                                
        public Records(){
                for (int i = 0; i < 13; i++){
                        records[i] = 0;
                        playerHoldingRecord[i] = "";
                        teamHoldingRecord[i] = "";
                        recordMadeAgainst[i] = "":
                        recordDate[i] = "00/00/0000";
                        isTeamRecord[i] = false;
                }
                isTeamRecord[0]  = true;
                
                loadRecords();
        }
        
        void checkForRecord(int idx, int value, String player, String team, String opponent){
                if (value > records[idx]){
                        newRecordMade = true;
                        records[idx] = value;
                        if (idx > 0)
                                playerHoldingRecord[idx] = player;
                        teamHoldingRecord[idx] = team;
                        recordMadeAgainst[idx] = opponent;
                        Calendar now = Calendar.getInstance();
                        
                        String month = now.get(Calendar.MONTH);
                        int monthValue = 0;
                        for (int i = 1; i <= 13; i++)
                                if (months[i].equals(month))
                                        monthValue = i;
                        
                        recordDate[idx] = monthValue + "/" + now.get(Calendar.DAY_OF_MONTH) + "/" + now.get(Calendar.YEAR);
                        
                        saveRecords();
                }
        }
        
        void loadRecords(){
                Scanner in = new Scanner(new File("saveData/records.rec"));
                records[0] = in.nextInt();
                teamHoldingRecord[0] = in.next().replaceAll("_", " ");
                recordMadeAgainst[0] = in.next().replaceAll("_", " ");
                int month = in.nextInt();
                int day = in.nextInt();
                int year = in.nextInt();
                recordDate[i] = month + "/" + day + "/" + year;

                for (int i = 1; i < 13; i++){
                        records[i] = in.nextInt();
                        playerHoldingRecord[i] = in.next().replaceAll("_", " ");
                        teamHoldingRecord[i] = in.next().replaceAll("_", " ");
                        recordMadeAgainst[i] = in.next().replaceAll("_", " ");
                        int month = in.nextInt();
                        int day = in.nextInt();
                        int year = in.nextInt();
                        recordDate[i] = month + "/" + day + "/" + year;
                }
                in.close();
        }
        
        void saveRecords(){
                PrintWriter out = new PrintWriter(new File("saveData/records.rec"));
                out.println(records[0] + " " + teamHoldingRecord[0].replaceAll("\\s", "_") + " " + recordMadeAgainst[0].replaceAll("\\s", "_") + " " + recordDate[0].replaceAll("/", " "));
                
                for (int i = 1; i < 13; i++)
                        out.println(records[i] + " " + playerHoldingRecord[i].replaceAll("\\s", "_") + " " + teamHoldingRecord[i].replaceAll("\\s", "_") + " " + recordMadeAgainst[i].replaceAll("\\s", "_") + " " + recordDate[i].replaceAll("/", " "));
        
                out.close();
        }
        


}
