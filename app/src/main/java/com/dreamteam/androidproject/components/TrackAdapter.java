package com.dreamteam.androidproject.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamteam.androidproject.R;

import java.util.ArrayList;


public class TrackAdapter extends ArrayAdapter<Track> {
    private Context context;
    private ArrayList<Track> items;
    private LayoutInflater vi;

    public TrackAdapter(Context context, ArrayList<Track> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final Track i = items.get(position);
        if (i != null) {
            final Track si = i;
            if (v == null) {
                v = vi.inflate(R.layout.track_card, null);
            }
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(MusicianAdapter.this.context, MusicianActivity.class);
//
//                    intent.putExtra("musician", si);
//                    MusicianAdapter.this.context.startActivity(intent);
//                }
//            });
            v.setOnLongClickListener(null);
            v.setLongClickable(false);

            ImageView musicianImage = (ImageView) v.findViewById(R.id.track_card_image);
            musicianImage.setImageResource(si.getMusician().getMusicianImageRes());

            TextView trackName = (TextView) v.findViewById(R.id.track_card_name);
            trackName.setText(si.getTrackName());

            TextView musicianName = (TextView) v.findViewById(R.id.track_card_musician);
            musicianName.setText(si.getMusician().getMusicianName());

        }
        return v;
    }

}
