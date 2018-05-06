package edu.css.a3334final;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public String gameDate = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).format(Calendar.getInstance().getTime());
    public FirebaseDatabase pitchBase = FirebaseDatabase.getInstance();
    public DatabaseReference pitchRef = pitchBase.getReference("Final2");
    private Button gameStartBtn;
    private EditText homeTxt;
    private EditText visitTxt;
    private TextView gameDay;
    Intent speedCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gameStartBtn = (Button) findViewById(R.id.startBtn);
        homeTxt = (EditText)findViewById(R.id.homeTeamTxt);
        visitTxt = (EditText)findViewById(R.id.visitTeamTxt);
        gameDay = (TextView)findViewById(R.id.gameDate);

        gameDay.setText(gameDate);

        gameStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (homeTxt.toString().isEmpty() || visitTxt.toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.incomplete_entry, Toast.LENGTH_LONG).show();
                }else{
                    Game curGame = new Game(homeTxt.toString(),visitTxt.toString(),gameDate);

                    speedCall = Intent(this,timer.class);
                    speedCall.putExtra("GAME_EXTRA",curGame);
                    startActivity(speedCall);
                }
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.NewGame) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
