package com.dreamteam.androidproject.components;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamteam.androidproject.R;

public class AlbumAdapter extends ArrayAdapter<Album> {

    private Context context;
    private ArrayList<Album> items;
    private LayoutInflater vi;

    public AlbumAdapter(Context context, ArrayList<Album> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final Album i = items.get(position);
        if (i != null) {
            Album si = (Album) i;
            v = vi.inflate(R.layout.album_card, null);

            v.setOnClickListener(null);
            v.setOnLongClickListener(null);
            v.setLongClickable(false);

            ImageView albumImage = (ImageView) v.findViewById(R.id.album_card_image);
            albumImage.setImageResource(si.getAlbumImageRes());

            TextView albumDescription = (TextView) v.findViewById(R.id.album_card_description);
            albumDescription.setText(si.getTitle() + " " + si.getReleaseYear());

            Musician creator = si.getCreator();

            TextView creatorName = (TextView) v.findViewById(R.id.album_card_creator);
            creatorName.setText(creator.getMusicianName());

            ImageView creatorImage = (ImageView) v.findViewById(R.id.album_card_creator_image);
            creatorImage.setImageResource(creator.getMusicianImageRes());

        }
        return v;
    }

}


