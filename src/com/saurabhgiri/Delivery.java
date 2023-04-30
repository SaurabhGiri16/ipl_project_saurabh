package com.saurabhgiri;

public class Delivery {
    private String match_Id_From_Delivery, batting_Team, bowler , extraRun , totalRun , fielder;

    public String getFielder() {
        return fielder;
    }

    public void setFielder(String fielder) {
        this.fielder = fielder;
    }

    public String getMatchId() {
        return match_Id_From_Delivery;
    }
    public void setMatchId(String matchId) {
        this.match_Id_From_Delivery = matchId;
    }
    public String getBattingTeam() {
        return batting_Team;
    }
    public void setBattingTeam(String battingTeam) {
        this.batting_Team = battingTeam;
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
