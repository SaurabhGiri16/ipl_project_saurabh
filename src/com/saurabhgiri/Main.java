package com.saurabhgiri;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static final int MATCH_ID_FROM_MATCHDATA = 0;
    public static final int SEASON = 1;
    public static final int VENUE = 14;
    public static final int WINNER = 10;
    public static final int SETIDINDELIVERIES = 0;
    public static final int BATTINGTEAM = 2;
    public static final int BOWLER = 8;
    public static final int EXTRA_RUN = 16;
    public static final int TOTAL_RUN = 17;
    public static final int FIELDER = 20;

    public static void main(String[] args) {

        ArrayList<Match> matchesData = readCsvMatchFile("/home/saurabhgiri/Project_Mountblue/ipl_project_saurabh/sources/matches.csv");
        ArrayList<Delivery>  deliveriesData = readCsvDeliveriesFile("/home/saurabhgiri/Project_Mountblue/ipl_project_saurabh/sources/deliveries.csv");

        noOfMatchsPlayedEachYear(matchesData);
        noOfMatchesWonAllTeamOverAllYear(matchesData);
        extraRunConductedPerTeamIn2016(matchesData, deliveriesData);
        bestEconomyBowlerIn2015(matchesData , deliveriesData);
        matchInEveryVenue(matchesData);
        playerWhoCatchesMostin2016(matchesData , deliveriesData); // review_question 
    }

    public static ArrayList<Match> readCsvMatchFile(String path) {
        ArrayList<Match> rowLinesOfFile = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] lineData = line.split(",", -1);
                Match match = new Match();
                match.setMatchIdFromMatch(lineData[MATCH_ID_FROM_MATCHDATA]);
                match.setSeason(lineData[SEASON]);
                match.setVenue(lineData[VENUE]);
                match.setWinner(lineData[WINNER]);

                rowLinesOfFile.add(match);
            }
            return rowLinesOfFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static ArrayList<Delivery> readCsvDeliveriesFile(String path) {
        ArrayList<Delivery> rowLinesOfFile = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] lineData = line.split("," , -1);
                Delivery delivery = new Delivery();
                delivery.setMatchId(lineData[SETIDINDELIVERIES]);
                delivery.setBattingTeam(lineData[BATTINGTEAM]);
                delivery.setBowler(lineData[BOWLER]);
                delivery.setExtraRun(lineData[EXTRA_RUN]);
                delivery.setTotalRun(lineData[TOTAL_RUN]);
                delivery.setFielder(lineData[FIELDER]);

                rowLinesOfFile.add(delivery);
            }
            return rowLinesOfFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void noOfMatchsPlayedEachYear(ArrayList<Match> matchListData) {
        HashMap<String, Integer> matchsPerYear = new HashMap();
        for (int i = 1; i < matchListData.size(); i++) {
            matchsPerYear.put(matchListData.get(i).getSeason(), matchsPerYear.getOrDefault(matchListData.get(i).getSeason(), 0) + 1);
        }
        for (Map.Entry<String, Integer> matchperyear : matchsPerYear.entrySet()) {
            System.out.println(matchperyear.getKey() + "- " + matchperyear.getValue());
        }
    }

    public static void noOfMatchesWonAllTeamOverAllYear(ArrayList<Match> matchListData) {
        HashMap<String, Integer> noOfMatchesWinningAllTeam = new HashMap();
        for (int i = 1; i < matchListData.size(); i++) {
            noOfMatchesWinningAllTeam.put(matchListData.get(i).getWinner(), noOfMatchesWinningAllTeam.getOrDefault(matchListData.get(i).getWinner(), 0) + 1);
        }
        for (Map.Entry<String, Integer> noOfMatchesWonPerTeam : noOfMatchesWinningAllTeam.entrySet()) {
            System.out.println(noOfMatchesWonPerTeam.getKey() + "- " + noOfMatchesWonPerTeam.getValue());
        }
    }

    public static void extraRunConductedPerTeamIn2016(ArrayList<Match> matchListData, ArrayList<Delivery> deliveries) {
        HashMap<String, Integer> listOfExtraRunConductedPerTeam = new HashMap();
        for (int i = 1; i < matchListData.size(); i++) {
            if (matchListData.get(i).getSeason().equals("2016")) {
                String id_m = matchListData.get(i).getMatchIdFromMatch();
                for (int j = 1; j < deliveries.size(); j++) {
                    if (deliveries.get(j).getMatchId().equals(id_m)) {
                        listOfExtraRunConductedPerTeam.put(deliveries.get(j).getBattingTeam(), listOfExtraRunConductedPerTeam.getOrDefault(deliveries.get(j).getBattingTeam(), 0) + Integer.valueOf(deliveries.get(j).getExtraRun()));
                    }
                }
            }
        }
        for (Map.Entry<String, Integer> extraRunConductedPerTeam : listOfExtraRunConductedPerTeam.entrySet()) {
            System.out.println(" Extra_Run_2016 - " + extraRunConductedPerTeam.getKey() + "- " + extraRunConductedPerTeam.getValue());
        }
    }

    public static void matchInEveryVenue(ArrayList<Match> matchDataList) {
        HashMap<String, Integer> matchPerVenue = new HashMap();
        for (int i = 1; i < matchDataList.size(); i++) {
            matchPerVenue.put(matchDataList.get(i).getVenue(), matchPerVenue.getOrDefault(matchDataList.get(i).getVenue(), 0) + 1);
        }
        for (Map.Entry<String, Integer> matchInvenue : matchPerVenue.entrySet()) {
            System.out.println(matchInvenue.getKey() + " - " + matchInvenue.getValue());
        }
    }

    public static void bestEconomyBowlerIn2015(ArrayList<Match> matchDataList, ArrayList<Delivery> deliveriesDataList) {
        HashMap<String, Integer> ballsPerBowler = new HashMap<>();
        HashMap<String, Integer> runsperBowler = new HashMap<>();
        HashMap<String, Double> economyPerBowler = new HashMap<>();
        for (int i = 1; i < matchDataList.size(); i++) {
            if (matchDataList.get(i).getSeason().equals("2015")) {
                String id_m = matchDataList.get(i).getMatchIdFromMatch();
                for (int j = 1; j < deliveriesDataList.size(); j++) {
                    if (deliveriesDataList.get(j).getMatchId().equals(id_m)) {
                        ballsPerBowler.put(deliveriesDataList.get(j).getBowler(), ballsPerBowler.getOrDefault(deliveriesDataList.get(j).getBowler(), 0) + 1);
                        runsperBowler.put(deliveriesDataList.get(j).getBowler(), runsperBowler.getOrDefault(deliveriesDataList.get(j).getBowler(), 0) + Integer.valueOf(deliveriesDataList.get(j).getTotalRun()));

                    }
                }
            }
        }
        double minEconomy = Integer.MAX_VALUE;
        String minEconomyPlayer = "";
        for (Map.Entry<String, Integer> itr : runsperBowler.entrySet()) {
            double eco = (double) (runsperBowler.get(itr.getKey()) * 6) / (double) ballsPerBowler.get(itr.getKey());
            economyPerBowler.put(itr.getKey(), eco);
        }
        for (Map.Entry<String, Double> bestEconomyBowler : economyPerBowler.entrySet()) {
            if (minEconomy > bestEconomyBowler.getValue()) {
                minEconomy = bestEconomyBowler.getValue();
                minEconomyPlayer = bestEconomyBowler.getKey();
            }

        }
        System.out.println(minEconomyPlayer + " - " + minEconomy);
    }

    public static void playerWhoCatchesMostin2016(ArrayList<Match> matchDataFile , ArrayList<Delivery> deliveriesDtatFile){
        HashMap<String , Integer> catchPerPlayer = new HashMap<>();
        for(int i =0 ; i<matchDataFile.size(); i++){
            if(matchDataFile.get(i).getSeason().equals("2016")){
                String id_m = matchDataFile.get(i).getMatchIdFromMatch();
                for(int j =0 ; j<deliveriesDtatFile.size() ; j++){
                    if(deliveriesDtatFile.get(j).getMatchId().equals(id_m)){
                       catchPerPlayer.put(deliveriesDtatFile.get(j).getFielder() , catchPerPlayer.getOrDefault(deliveriesDtatFile.get(j).getFielder() , 0)+1);
                    }
                }
            }
        }
        int min_catch = 0;
        String nameOfPlayer = "";
        for(Map.Entry<String , Integer> itr : catchPerPlayer.entrySet()){
            if(min_catch < itr.getValue() && !itr.getKey().equals("")){
                min_catch = itr.getValue();
                nameOfPlayer = itr.getKey();
            }
        }
        System.out.println(nameOfPlayer);
    }
// -->Review_question  --
//    public static void rotate90() {
//        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//
//        int[][] result = new int[3][3];
//        for (int row = 0; row < matrix.length; row++) {
//            for (int column = 0; column < matrix.length; column++) {
//                result[column][row] = matrix[row][column];
//            }
//        }
//
//        for (int row = 0; row < result.length; row++) {
//            for (int column = 0; column < result.length / 2; column++) {
//                int temp = result[row][column];
//                result[row][column] = result[row][result.length - 1 - column];
//                result[row][result.length - 1 - column] = temp;
//            }
//        }
//
//        for (int i = 0; i < result.length; i++) {
//            for (int j = 0; j < result.length; j++) {
//                System.out.print(result[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
}
