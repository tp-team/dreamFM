package com.dreamteam.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamteam.androidproject.components.Musician;

import java.util.ArrayList;

/**
 * Created by Egor on 26.11.2014.
 */
public class MusicianActivity extends Activity {

    public String musicianName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Musician musician = (Musician) intent.getSerializableExtra("musician");

        setContentView(R.layout.activity_musician);
        View v = this.findViewById(R.id.musician_page);
        ImageView musicianImage = (ImageView) v.findViewById(R.id.musician_card_image);
        musicianImage.setImageResource(musician.getMusicianImageRes());

        TextView musicianName = (TextView) v.findViewById(R.id.musician_card_name);
        musicianName.setText(musician.getMusicianName());

        ArrayList<Musician> similars = musician.getSimilarMusicians();
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
}
