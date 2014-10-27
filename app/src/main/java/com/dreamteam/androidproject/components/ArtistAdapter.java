package com.dreamteam.androidproject.components;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamteam.androidproject.NavigationDrawerFragment;
import com.dreamteam.androidproject.R;

public class ArtistAdapter extends ArrayAdapter<Artist> {

    private Context context;
    private ArrayList<Artist> items;
    private LayoutInflater vi;

    public ArtistAdapter(Context context, ArrayList<Artist> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final Artist i = items.get(position);
        if (i != null) {
            Artist si = (Artist)i;
            v = vi.inflate(R.layout.artist_card, null);

            v.setOnClickListener(null);
            v.setOnLongClickListener(null);
            v.setLongClickable(false);

            ImageView artistImage = (ImageView) v.findViewById(R.id.artist_card_image);
            artistImage.setImageResource(si.getArtistImageRes());

            TextView artistName = (TextView) v.findViewById(R.id.artist_card_name);
            artistName.setText(si.getArtistName());

            ArrayList<Artist> similars = si.getSimilarArtists();
            Artist similar1 = similars.get(0);
            Artist similar2 = similars.get(1);

            TextView artistSimilars = (TextView) v.findViewById(R.id.artist_card_similars);
            artistSimilars.setText("Similar to " + similar1.getArtistName() + " and " +
                similar2.getArtistName());

            ImageView similarImage1 = (ImageView) v.findViewById(R.id.artist_card_similars_image1);
            similarImage1.setImageResource(similar1.getArtistImageRes());

            ImageView similarImage2 = (ImageView) v.findViewById(R.id.artist_card_similars_image2);
            similarImage2.setImageResource(similar2.getArtistImageRes());

        }
        return v;
    }

}