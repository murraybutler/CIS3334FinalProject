package edu.css.a3334final;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class pitchFirebase {

    public FirebaseDatabase pitchBase = FirebaseDatabase.getInstance();
    public DatabaseReference pitchRef = pitchBase.getReference("Final2");

    
}
