package edu.css.a3334final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class History extends AppCompatActivity {

    private pitchFirebase pitchFireRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        pitchFireRef = new pitchFirebase();
        pitchFireRef.open();


    }
}
