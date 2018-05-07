package edu.css.a3334final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public String gDate = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).format(Calendar.getInstance().getTime());
    private Button gameStartBtn;
    private EditText homeTxt;
    private EditText visitTxt;
    private TextView gameDay;
    private double pdist  = 0.0;
    private Spinner pspinner;
    final private double[] pdistarr = {60.5,46,50,43};
    pitchFirebase pitchFireSrc;
    DatabaseReference pitchRef;
    // Intent speedCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar myAB = getSupportActionBar();
        if (myAB != null) {
            myAB.setDisplayHomeAsUpEnabled(true);
        }

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
                if (homeTxt.toString().isEmpty() || visitTxt.toString().isEmpty() || pdist == 0.0) {
                    Toast.makeText(getApplicationContext(), R.string.incomplete_entry, Toast.LENGTH_LONG).show();
                }else{
                    Game curGame = new Game(homeTxt.getText().toString(),visitTxt.getText().toString(),gDate);
                    Intent speedCall = new Intent(v.getContext(),Ptimer.class);
                    speedCall.putExtra("GAME_EXTRA",curGame);
                    speedCall.putExtra("DISTANCE_EXTRA", pdist);
                    //startActivityForResult(speedCall,1);
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

    private void setupPitchFirebase() {
        pitchFireSrc = new pitchFirebase();
        pitchRef = pitchFireSrc.open();

        pitchRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_CANCELED){
                Log.i("CIS3334", "Ptimer Activity returned");
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
