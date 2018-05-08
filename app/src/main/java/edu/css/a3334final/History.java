package edu.css.a3334final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import javax.sql.DataSource;

public class History extends AppCompatActivity {

    pitchFirebase pitchDataSource;
    DatabaseReference pitchFireRef;
    List<Pitch> pitchList;
    ArrayAdapter<Pitch> pAdapter;
    ListView pitchViewList;
    int posSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        pitchDataSource = new pitchFirebase();
        pitchFireRef = pitchDataSource.open();

        setupFirebaseDataChange();
        setupListView();

    }

    private void setupFirebaseDataChange() {
        pitchDataSource = new pitchFirebase();
        pitchFireRef = pitchDataSource.open();
        pitchFireRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("CIS3334", "Starting onDataChange()");        // debugging log
                pitchList = pitchDataSource.getAllPitches(dataSnapshot);
                // Instantiate a custom adapter for displaying each fish
                pAdapter = new pitchAdapter(History.this, android.R.layout.simple_list_item_single_choice, pitchList);
                // Apply the adapter to the list
                pitchViewList.setAdapter(pAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("CIS3334", "onCancelled: ");
            }
        });
    }

    private void setupListView() {
        pitchViewList = (ListView) findViewById(R.id.PitchViewList);
        pitchViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posSelected = position;
                Log.i("CIS3334", "Pitch selected at pos: " + posSelected);
            }
        });
    }
}
