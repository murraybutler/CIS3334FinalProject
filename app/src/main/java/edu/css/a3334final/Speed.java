package edu.css.a3334final;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Speed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);

        TextView spd = (TextView) findViewById(R.id.spdTxt);
        TextView mph = (TextView) findViewById(R.id.mphTxt);
        Button rtnTmr = (Button) findViewById(R.id.rtnMainBtn);


        double speedBall = getIntent().getDoubleExtra("SPEED_EXTRA",0.0);

        setSpeedAndColor(spd, mph, speedBall);

        rtnTmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });

    }

    private void setSpeedAndColor(TextView spdView, TextView mphView, double speed) {
        spdView.setText(Double.toString(speed));
        if (speed > 80) {
            spdView.setTextColor(Color.RED);
            mphView.setTextColor(Color.RED);
        } else if (speed > 40 && speed < 81) {
            spdView.setTextColor(Color.YELLOW);
            mphView.setTextColor(Color.YELLOW);
        } else {
            spdView.setTextColor(Color.WHITE);
            mphView.setTextColor(Color.WHITE);
        }
    }
}
