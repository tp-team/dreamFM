package com.dreamteam.androidproject.components;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamteam.androidproject.MainActivity;
import com.dreamteam.androidproject.MusicianActivity;
import com.dreamteam.androidproject.R;

public class MusicianAdapter extends ArrayAdapter<Musician> {

    private Context context;
    private ArrayList<Musician> items;
    private LayoutInflater vi;

    public MusicianAdapter(Context context, ArrayList<Musician> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final Musician i = items.get(position);
        if (i != null) {
            final Musician si = i;
            if (v == null) {
                v = vi.inflate(R.layout.musician_card, null);
            }
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MusicianAdapter.this.context, MusicianActivity.class);

                    intent.putExtra("musician", si);
                    MusicianAdapter.this.context.startActivity(intent);
                }
            });
            v.setOnLongClickListener(null);
            v.setLongClickable(false);

            ImageView musicianImage = (ImageView) v.findViewById(R.id.musician_card_image);
            musicianImage.setImageResource(si.getMusicianImageRes());

            TextView musicianName = (TextView) v.findViewById(R.id.musician_card_name);
            musicianName.setText(si.getMusicianName());

            ArrayList<Musician> similars = si.getSimilarMusicians();
            Musician similar1 = similars.get(0);
            Musician similar2 = similars.get(1);

            TextView musicianSimilars = (TextView) v.findViewById(R.id.musician_card_similars);
            musicianSimilars.setText("Similar to " + similar1.getMusicianName() + " and " +
                similar2.getMusicianName());

            ImageView similarImage1 = (ImageView) v.findViewById(R.id.musician_card_similars_image1);
            similarImage1.setImageResource(similar1.getMusicianImageRes());

            ImageView similarImage2 = (ImageView) v.findViewById(R.id.musician_card_similars_image2);
            similarImage2.setImageResource(similar2.getMusicianImageRes());

        }
        return v;
    }

}