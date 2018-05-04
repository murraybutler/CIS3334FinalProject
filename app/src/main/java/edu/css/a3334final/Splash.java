package edu.css.a3334final;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance){
        startActivity(new Intent(Splash.this, MainActivity.class));
        finish();
    }
}
