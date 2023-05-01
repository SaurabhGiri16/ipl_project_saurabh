package com.mountBlue.iplProject.saurabh;

public class Delivery {
    private String matchIdFromDelivery, battingTeam, bowler , extraRun , totalRun , fielder;

    public String getFielder() {
        return fielder;
    }

    public void setFielder(String fielder) {
        this.fielder = fielder;
    }

    public String getMatchId() {
        return matchIdFromDelivery;
    }
    public void setMatchId(String matchId) {
        this.matchIdFromDelivery = matchId;
    }
    public String getBattingTeam() {
        return battingTeam;
    }
    public void setBattingTeam(String battingTeam) {
        this.battingTeam = battingTeam;
    }
    public String getBowler() {
        return bowler;
    }
    public void setBowler(String bowler) {
        this.bowler = bowler;
    }
    public String getExtraRun() {
        return extraRun;
    }
    public void setExtraRun(String extraRun) {
        this.extraRun = extraRun;
    }
    public String getTotalRun() {
        return totalRun;
    }
    public void setTotalRun(String totalRun) {
        this.totalRun = totalRun;
    }
}
