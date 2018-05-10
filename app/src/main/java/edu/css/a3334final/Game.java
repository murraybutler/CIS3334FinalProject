package edu.css.a3334final;

/**
 * Object to hold pitch refs and allow passing data between Activities, implements Parcelable
 * @author Murray Butler
 * @version 1.0
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Game implements Parcelable{

    private String homeTeam;
    private String visitTeam;
    private String gameDate;

    // Added this in the event of adding Game elements to firebase in place of pitches, these are child elements.
    private ArrayList<Pitch> pitches;

    public Game(String home, String visit, String day) {
        homeTeam = home;
        visitTeam = visit;
        gameDate = day;
        pitches = new ArrayList<Pitch>();
    }

    public Game(Parcel inGame) {
        homeTeam = inGame.readString();
        visitTeam = inGame.readString();
        gameDate = inGame.readString();
        pitches = new ArrayList<Pitch>();
    }

    /**
     * Override mthod for content description of parcel
     * @return 0, dummy method
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * method for write to parcel
     * @param destGame Game object for messaging
     * @param flags integer for flags to parcel
     */
    @Override
    public void writeToParcel(Parcel destGame, int flags) {
        destGame.writeString(homeTeam);
        destGame.writeString(visitTeam);
        destGame.writeString(gameDate);
    }

    /**
     * Creator method for parcel creation
     */
    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
        public Game createFromParcel(Parcel inGame) {
            return new Game(inGame);
        }

        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    /**
     * Getter for home team member
     * @return String home team val
     */
    public String getHomeTeam() {
        return homeTeam;
    }

    /**
     * Getter for visiting team
     * @return String visiting team val
     */
    public String getVisitTeam() {
        return visitTeam;
    }

    public void addPitch(Pitch pitch) {
        this.pitches.add(pitch);
    }

    public ArrayList<Pitch> getPitches() {
        return pitches;
    }

    public void setPitches(ArrayList<Pitch> inpitches) {
        this.pitches = inpitches;
    }

    /**
     * Override to generate string of values
     * @return String formatted members of object
     */
    @Override
    public String toString() {
        String retStr = "Game{" + this.gameDate + ":";

        for (Pitch s : this.pitches) {
            retStr += "{" + s.toString() + "}";
        }

        return retStr;
    }
}
