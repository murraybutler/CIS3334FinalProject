package edu.css.a3334final;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Game implements Parcelable{

    private String homeTeam;
    private String visitTeam;
    private String gameDate;

    public Game(String home, String visit, String day) {
        homeTeam = home;
        visitTeam = visit;
        gameDate = day;
    }

    public Game(Parcel inGame) {
        homeTeam = inGame.readString();
        visitTeam = inGame.readString();
        gameDate = inGame.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destGame, int flags) {
        destGame.writeString(homeTeam);
        destGame.writeString(visitTeam);
        destGame.writeString(gameDate);
    }

    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
        public Game createFromParcel(Parcel inGame) {
            return new Game(inGame);
        }

        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getVisitTeam() {
        return visitTeam;
    }
}
