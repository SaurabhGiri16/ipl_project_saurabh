package com.saurabhgiri;

public class Match {
    private String match_Id_From_Match, season , winner , venue;
    public String getMatchIdFromMatch() {
        return match_Id_From_Match;
    }
    public void setMatchIdFromMatch(String matchId) {
        this.match_Id_From_Match = matchId;
    }
    public String getSeason() {
        return season;
    }
    public void setSeason(String season) {
        this.season = season;
    }
    public String getWinner() {
        return winner;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }
    public String getVenue() {
        return venue;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }
}
