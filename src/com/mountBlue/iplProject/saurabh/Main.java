package com.mountBlue.iplProject.saurabh;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static final int MATCH_ID_FROM_MATCHDATA = 0;
    public static final int SEASON = 1;
    public static final int VENUE = 14;
    public static final int WINNER = 10;
    public static final int MATCH_ID_FROM_DELIVERYDATA = 0;
    public static final int BATTING_TEAM = 2;
    public static final int BOWLER = 8;
    public static final int EXTRA_RUN = 16;
    public static final int TOTAL_RUN = 17;
    public static final int FIELDER = 20;

    public static void main(String[] args) {

        ArrayList<Match> matchesData = readCsvMatchFile("/home/saurabhgiri/Project_Mountblue/ipl_project_saurabh/sources/matches.csv");
        ArrayList<Delivery> deliveriesData = readCsvDeliveryFile("/home/saurabhgiri/Project_Mountblue/ipl_project_saurabh/sources/deliveries.csv");

        assert matchesData != null;
        findNoOfMatchsPlayedEachYear(matchesData);
        findNoOfMatchesWonByAllTeamOverAllYear(matchesData);
        findExtraRunConductedPerTeamIn2016(matchesData, deliveriesData);
        bestEconomyBowlerIn2015(matchesData, deliveriesData);
        findMatchInEveryVenue(matchesData);
        findPlayerWhoCatchesMostIn2016(matchesData, deliveriesData); // review_question
    }

    public static ArrayList<Match> readCsvMatchFile(String path) {
        ArrayList<Match> rowLinesOfFile = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
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

    public static ArrayList<Delivery> readCsvDeliveryFile(String path) {
        ArrayList<Delivery> rowLineOfFile = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] lineData = line.split(",", -1);
                Delivery delivery = new Delivery();
                delivery.setMatchId(lineData[MATCH_ID_FROM_DELIVERYDATA]);
                delivery.setBattingTeam(lineData[BATTING_TEAM]);
                delivery.setBowler(lineData[BOWLER]);
                delivery.setExtraRun(lineData[EXTRA_RUN]);
                delivery.setTotalRun(lineData[TOTAL_RUN]);
                delivery.setFielder(lineData[FIELDER]);

                rowLineOfFile.add(delivery);
            }
            return rowLineOfFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void findNoOfMatchsPlayedEachYear(ArrayList<Match> matchListData) {
        Map<String, Integer> matchsPerYear = new TreeMap<>();

        for (int i = 1; i < matchListData.size(); i++) {
            matchsPerYear.put(matchListData.get(i).getSeason(), matchsPerYear.getOrDefault(matchListData.get(i).getSeason(), 0) + 1);
        }

        for (Map.Entry<String, Integer> matchPerYear : matchsPerYear.entrySet()) {
            System.out.println(matchPerYear.getKey() + "- " + matchPerYear.getValue());
        }
    }

    public static void findNoOfMatchesWonByAllTeamOverAllYear(ArrayList<Match> matchListData) {
        Map<String, Integer> noOfMatchesWinningAllTeam = new TreeMap<>();

        for (int i = 1; i < matchListData.size(); i++) {
            noOfMatchesWinningAllTeam.put(matchListData.get(i).getWinner(),
                    noOfMatchesWinningAllTeam.getOrDefault(matchListData.get(i).getWinner(), 0) + 1);
        }

        for (Map.Entry<String, Integer> noOfMatchesWonPerTeam : noOfMatchesWinningAllTeam.entrySet()) {
            System.out.println(noOfMatchesWonPerTeam.getKey() + "- " + noOfMatchesWonPerTeam.getValue());
        }
    }

    public static void findExtraRunConductedPerTeamIn2016(ArrayList<Match> matchListData, ArrayList<Delivery> deliveries) {
        Map<String, Integer> listOfExtraRunConductedPerTeam = new TreeMap<>();

        for (int i = 1; i < matchListData.size(); i++) {
            if (matchListData.get(i).getSeason().equals("2016")) {
                String id_m = matchListData.get(i).getMatchIdFromMatch();

                for (int j = 1; j < deliveries.size(); j++) {
                    if (deliveries.get(j).getMatchId().equals(id_m)) {
                        listOfExtraRunConductedPerTeam.put(deliveries.get(j).getBattingTeam(),
                                listOfExtraRunConductedPerTeam.getOrDefault(deliveries.get(j).getBattingTeam(), 0) +
                                        Integer.parseInt(deliveries.get(j).getExtraRun()));
                    }
                }
            }
        }

        for (Map.Entry<String, Integer> extraRunConductedPerTeam : listOfExtraRunConductedPerTeam.entrySet()) {
            System.out.println("Extra_Run_2016 - " + extraRunConductedPerTeam.getKey() + "- " + extraRunConductedPerTeam.getValue());
        }
    }

    public static void findMatchInEveryVenue(ArrayList<Match> matchDataList) {
        Map<String, Integer> matchPerVenue = new TreeMap<>();

        for (int i = 1; i < matchDataList.size(); i++) {
            matchPerVenue.put(matchDataList.get(i).getVenue(), matchPerVenue.getOrDefault(matchDataList.get(i).getVenue(), 0) + 1);
        }

        for (Map.Entry<String, Integer> matchInvenue : matchPerVenue.entrySet()) {
            System.out.println(matchInvenue.getKey() + " - " + matchInvenue.getValue());
        }
    }

    public static void bestEconomyBowlerIn2015(ArrayList<Match> matchDataList, ArrayList<Delivery> deliveriesDataList) {
        Map<String, Integer> ballsPerBowler = new TreeMap<>();
        Map<String, Integer> runsPerBowler = new TreeMap<>();
        Map<String, Double> economyPerBowler = new TreeMap<>();

        for (int i = 1; i < matchDataList.size(); i++) {
            if (matchDataList.get(i).getSeason().equals("2015")) {
                String id_m = matchDataList.get(i).getMatchIdFromMatch();

                for (int j = 1; j < deliveriesDataList.size(); j++) {
                    if (deliveriesDataList.get(j).getMatchId().equals(id_m)) {
                        ballsPerBowler.put(deliveriesDataList.get(j).getBowler(), ballsPerBowler.getOrDefault(deliveriesDataList.get(j).getBowler(), 0) + 1);
                        runsPerBowler.put(deliveriesDataList.get(j).getBowler(), runsPerBowler.getOrDefault(deliveriesDataList.get(j).getBowler(), 0) +
                                Integer.parseInt(deliveriesDataList.get(j).getTotalRun()));
                    }
                }
            }
        }

        double minEconomy = Integer.MAX_VALUE;
        String minEconomyPlayer = "";

        for (Map.Entry<String, Integer> itr : runsPerBowler.entrySet()) {
            double eco = (double) (runsPerBowler.get(itr.getKey()) * 6) / (double) ballsPerBowler.get(itr.getKey());
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

    public static void findPlayerWhoCatchesMostIn2016(ArrayList<Match> matchDataFile, ArrayList<Delivery> deliveriesDtatFile) {
        Map<String, Integer> catchPerPlayer = new TreeMap<>();

        for (Match match : matchDataFile) {
            if (match.getSeason().equals("2016")) {
                String id_m = match.getMatchIdFromMatch();

                for (Delivery delivery : deliveriesDtatFile) {
                    if (delivery.getMatchId().equals(id_m)) {
                        catchPerPlayer.put(delivery.getFielder(), catchPerPlayer.getOrDefault(delivery.getFielder(), 0) + 1);
                    }
                }
            }
        }

        int min_catch = 0;
        String nameOfPlayer = "";

        for (Map.Entry<String, Integer> itr : catchPerPlayer.entrySet()) {
            if (min_catch < itr.getValue() && !itr.getKey().equals("")) {
                min_catch = itr.getValue();
                nameOfPlayer = itr.getKey();
            }
        }
        System.out.println(nameOfPlayer);
    }
}
