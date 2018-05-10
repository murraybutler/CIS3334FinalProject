package edu.css.a3334final;

/**
 * Firebase abstraction class
 * @author Murray Butler
 * @version 1.0
 */

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

    /**
     * Open method for firebase instance and reference
     * @return DatabaseRef for firebase instance
     */
    public DatabaseReference open() {
        FirebaseDatabase pitchBase = FirebaseDatabase.getInstance();
        pitchRef = pitchBase.getReference(dateTag);
        return pitchRef;
    }

    /**
     * dummy close method
     */
    public void close() {

    }

    /**
     * element creation method for database
     * @param nuPitch Pitch object for insert
     * @return Pitch object inserted return for verify
     */
    public Pitch createPitch(Pitch nuPitch) {
        String key = pitchRef.child(dateTag).push().getKey();
        pitchRef.child(key).setValue(nuPitch);
        return nuPitch;
    }

    /**
     * table scan method for all current entries
     * @param dSnap DAtaSnapshot of table
     * @return List of Pitch objects
     */
    public List<Pitch> getAllPitches(DataSnapshot dSnap) {
        List<Pitch> pitchList = new ArrayList<Pitch>();

        for (DataSnapshot d : dSnap.getChildren()) {
            Pitch p = d.getValue(Pitch.class);
            pitchList.add(p);
        }
        return pitchList;
    }

}
