package edu.css.a3334final;

import java.util.ArrayList;

public class Game {

    private String homeTeam;
    private String visitTeam;
    private String gameDate;

    public Game(String team1, String team2, String date) {
        homeTeam = team1;
        visitTeam = team2;
        gameDate = date;
    }

    public ArrayList<String> getTeams() {
        ArrayList<String> Teams;
        Teams.add(homeTeam);
        Teams.add(visitTeam);

        return Teams;
    }
}
