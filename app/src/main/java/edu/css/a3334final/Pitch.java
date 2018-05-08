package edu.css.a3334final;

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

    public String getTeam() {
        return curteam;
    }

    public double getSpeed() {
        return curspeed;
    }

    public String getTime() {
        return ptime;
    }

    public void setTeam(String team) {
        this.curteam = team;
    }

    public void setSpeed(double speed) {
        this.curspeed = speed;
    }

    public void setTime(String time) {
        this.ptime = time;
    }

    @Override
    public String toString() {
        return "Pitch{" + "Team=" + curteam + ",Speed=" + Double.toString(curspeed) + ",Time=" + ptime + "}";
    }

}
