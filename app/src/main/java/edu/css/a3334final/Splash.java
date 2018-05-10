package edu.css.a3334final;

/**
 * Simple class for Splash screen
 * @author Murray Butler
 * @version 1.0
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        //Intent call to MainActivity
        startActivity(new Intent(Splash.this, MainActivity.class));
        finish();
    }
}
