package edu.css.a3334final;

/**
 * Class to handle pitch objects for use in Views
 * @author Murray Butler
 * @version 1.0
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class pitchAdapter extends ArrayAdapter<Pitch>{

    private List<Pitch> pitchList;
    private Context contxt;
    private int layoutResource;

    public pitchAdapter(Context inCon, int res, List<Pitch> pitchIn){
        super(inCon,res,pitchIn);
        this.contxt = inCon;
        this.layoutResource = res;
        this.pitchList = pitchIn;
    }

    /**
     * getting view to handle display of list
     * @param position int list index
     * @param convertView View context for caller
     * @param parent group view context of parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pitch pitch = pitchList.get(position);
        View view;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.pitch_row_layout, null);

        TextView tvTeam = (TextView) view.findViewById(R.id.txtvwTeam);
        TextView tvSpeed = (TextView) view.findViewById(R.id.txtvwSpeed);
        TextView tvTime = (TextView) view.findViewById(R.id.txtvwTime);

        tvTeam.setText(pitch.getTeam());
        tvSpeed.setText(Double.toString(pitch.getSpeed()));
        tvTime.setText(pitch.getTime());

        return(view);

    }
}
