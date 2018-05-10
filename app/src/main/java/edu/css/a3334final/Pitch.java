package edu.css.a3334final;

/**
 * Object class to hold pitch data.
 * @author Murray Butler
 * @version 1.0
 */

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Pitch implements Serializable{

    private Game curgame;
    private String curteam;
    private double curspeed;
    private String ptime;

    public Pitch() {

    }

    public Pitch(String team, double speed) {
        this.curteam = team;
        this.curspeed = speed;
        this.ptime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().getTime());;
    }

    /**
     * Getter for team that created pitch
     * @return String team name
     */
    public String getTeam() {
        return curteam;
    }

    /**
     * Getter for speed of pitch
     * @return double speed
     */
    public double getSpeed() {
        return curspeed;
    }

    /**
     * Getter for time of pitch
     * @return String HH:mm:ss
     */
    public String getTime() {
        return ptime;
    }

    /**
     * Setter for team of pitch
     * @param team team val
     */
    public void setTeam(String team) {
        this.curteam = team;
    }

    /**
     * setter for speed
     * @param speed speed of pitch
     */
    public void setSpeed(double speed) {
        this.curspeed = speed;
    }

    /**
     * Setter for time of pitch
     * @param time String HH:mm:ss
     */
    public void setTime(String time) {
        this.ptime = time;
    }

    /**
     * Override of toString
     * @return String formatted data for pitch
     */
    @Override
    public String toString() {
        return "Pitch{" + "Team=" + curteam + ",Speed=" + Double.toString(curspeed) + ",Time=" + ptime + "}";
    }

}
