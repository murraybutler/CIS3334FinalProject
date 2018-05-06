package edu.css.a3334final;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Pitch {

    private Game curgame;
    private String curteam;
    private double curspeed;
    private String ptime;

    public Pitch(String team, double speed) {
        curteam = team;
        curspeed = speed;
        ptime = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(Calendar.getInstance().getTime());;
    }

}
