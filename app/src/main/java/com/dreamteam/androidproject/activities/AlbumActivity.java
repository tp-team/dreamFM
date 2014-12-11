package com.dreamteam.androidproject.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamteam.androidproject.R;
import com.dreamteam.androidproject.components.Album;
import com.dreamteam.androidproject.components.Musician;

import java.util.ArrayList;

/**
 * Created by Egor on 26.11.2014.
 */
public class AlbumActivity extends Activity {

    public Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        album = (Album) intent.getSerializableExtra("album");

        setContentView(R.layout.activity_album);
        View v = this.findViewById(R.id.album_page);

        ImageView albumImage = (ImageView) v.findViewById(R.id.album_card_image);
        albumImage.setImageResource(album.getAlbumImageRes());

        TextView albumDescription = (TextView) v.findViewById(R.id.album_card_description);
        albumDescription.setText(album.getTitle() + " " + album.getReleaseYear());

        Musician creator = album.getCreator();

        TextView creatorName = (TextView) v.findViewById(R.id.album_card_creator);
        creatorName.setText(creator.getMusicianName());

        ImageView creatorImage = (ImageView) v.findViewById(R.id.album_card_creator_image);
        creatorImage.setImageResource(creator.getMusicianImageRes());
    }
}
