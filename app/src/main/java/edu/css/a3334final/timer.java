package edu.css.a3334final;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class timer extends AppCompatActivity {


    private long startTime;
    private long deltaTime;
    private double pdist = 0.0;
    private String curTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        final Button startBtn = (Button)findViewById(R.id.pitchBtn);
        final Button stopBtn = (Button)findViewById(R.id.batterBtn);
        final Button teamBtn = (Button)findViewById(R.id.switchTeamBtn);
        final TextView pitchTeam = (TextView)findViewById(R.id.timeTeamTxt);

        final Game tgame = (Game) getIntent().getParcelableExtra("GAME_EXTRA");
        pdist = (double) getIntent().getDoubleExtra("DISTANCE_EXTRA",60.6);

        stopBtn.setVisibility(View.GONE);
        startBtn.setVisibility(View.VISIBLE);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = SystemClock.uptimeMillis();

                startBtn.setVisibility(View.GONE);
                stopBtn.setVisibility(View.VISIBLE);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deltaTime = SystemClock.uptimeMillis() - startTime;
                double tspeed = calcSpeed(deltaTime);
                Pitch tpitch = new Pitch(curTeam,tspeed);
            }
        });

        teamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pitchTeam.getText().toString() == tgame.getHomeTeam()) {
                    curTeam = tgame.getVisitTeam();
                } else {
                    curTeam = tgame.getHomeTeam();
                }
                pitchTeam.setText(curTeam);
            }
        });
    }

    private double calcSpeed(long timey) {
        double pspeed = ((pdist / (timey/1000.0)) * 0.68181818181);
        return pspeed;
    }



}
