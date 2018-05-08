package edu.css.a3334final;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class pitchFirebase {

    DatabaseReference pitchRef;
    public static final String dateTag = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).format(Calendar.getInstance().getTime());

    public DatabaseReference open() {
        FirebaseDatabase pitchBase = FirebaseDatabase.getInstance();
        pitchRef = pitchBase.getReference(dateTag);
        return pitchRef;
    }

    public void close() {

    }

    public Pitch createPitch(Pitch nuPitch) {
        String key = pitchRef.child(dateTag).push().getKey();
        pitchRef.child(key).setValue(nuPitch);
        return nuPitch;
    }

    public List<Pitch> getAllPitches(DataSnapshot dSnap) {
        List<Pitch> pitchList = new ArrayList<Pitch>();

        for (DataSnapshot d : dSnap.getChildren()) {
            Pitch p = d.getValue(Pitch.class);
            pitchList.add(p);
        }
        return pitchList;
    }

}
