package edu.css.a3334final;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public String gDate = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).format(Calendar.getInstance().getTime());
    private Button gameStartBtn;
    private EditText homeTxt;
    private EditText visitTxt;
    private TextView gameDay;
    private double pdist  = 0;
    private Spinner pspinner;
    final private double[] pdistarr = {60.5,46,50,43};
    // Intent speedCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pspinner = (Spinner) findViewById(R.id.distSpinner);
        gameStartBtn = (Button) findViewById(R.id.startBtn);
        homeTxt = (EditText)findViewById(R.id.homeTeamTxt);
        visitTxt = (EditText)findViewById(R.id.visitTeamTxt);
        gameDay = (TextView)findViewById(R.id.gameDate);

        ArrayAdapter<CharSequence> padapter = ArrayAdapter.createFromResource(this,R.array.distances,android.R.layout.simple_spinner_item);
        padapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pspinner.setAdapter(padapter);

        gameDay.setText(gDate);

        gameStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((homeTxt.toString().isEmpty() || visitTxt.toString().isEmpty()) && pdist != 0) {
                    Toast.makeText(getApplicationContext(), R.string.incomplete_entry, Toast.LENGTH_LONG).show();
                }else{
                    Game curGame = new Game(homeTxt.getText().toString(),visitTxt.getText().toString(),gDate);
                    Intent speedCall = new Intent(getApplicationContext(),Timer.class);
                    speedCall.putExtra("GAME_EXTRA",curGame);
                    speedCall.putExtra("DISTANCE_EXTRA", pdist);
                    startActivity(speedCall);
                }
            }
        });

        pspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pdist = pdistarr[position];
                Log.i("CIS3334", "pdist: " + pdist);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("CIS3334","No distance selected");
            }
        });
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
            case R.id.NewGame:
                // database write to end game?
                Intent nGame = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(nGame);
                return true;

            case R.id.ViewHistory:
                Intent nHist = new Intent(getApplicationContext(), History.class);
                startActivity(nHist);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
