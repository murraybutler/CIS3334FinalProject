package edu.css.a3334final;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Timer extends AppCompatActivity {


    private long startTime;
    private long deltaTime;
    private double pdist = 0.0;
    private String curTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        final ImageButton startBtn = (ImageButton)findViewById(R.id.pitchBtn);
        final ImageButton stopBtn = (ImageButton)findViewById(R.id.batterBtn);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.NewGame) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
