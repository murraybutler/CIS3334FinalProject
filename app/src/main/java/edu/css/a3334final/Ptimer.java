package edu.css.a3334final;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class Ptimer extends AppCompatActivity {


    private long startTime;
    private long deltaTime;
    private double pdist = 0.0;
    private String curTeam;
    private pitchFirebase pitchFireRef;
    static final int RES_CODE = 1;

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

        pitchFireRef = new pitchFirebase();
        pitchFireRef.open();

        stopBtn.setVisibility(View.GONE);
        startBtn.setVisibility(View.VISIBLE);
        pitchTeam.setText(tgame.getHomeTeam());

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
                pitchFireRef.createPitch(tpitch);
                tgame.addPitch(tpitch);
                stopBtn.setVisibility(View.GONE);
                startBtn.setVisibility(View.VISIBLE);
                Intent speedInt = new Intent(v.getContext(),Speed.class);
                speedInt.putExtra("SPEED_EXTRA",tspeed);
                startActivityForResult(speedInt,RES_CODE);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_CANCELED){
                Log.i("CIS3334", "Speed Activity returned");
            }
        }
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
        switch (item.getItemId()) {
            /** case R.id.NewGame:
             // database write to end game?
             finish();
             Intent nGame = new Intent(this, MainActivity.class);
             startActivity(nGame);
             return true; **/

            case R.id.ViewHistory:
                finish();
                Intent nHist = new Intent(this, History.class);
                startActivity(nHist);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
