package com.dreamteam.androidproject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamteam.androidproject.components.Musician;

import java.util.ArrayList;

/**
 * Created by Egor on 26.11.2014.
 */
public class MusicianActivity extends Activity {

    public Musician mMusician;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mMusician = (Musician) intent.getSerializableExtra("musician");

        renderActionBar();
        setContentView(R.layout.activity_musician);
        View v = this.findViewById(R.id.musician_page);

        ImageView musicianImage = (ImageView) v.findViewById(R.id.musician_image);
        musicianImage.setImageResource(mMusician.getMusicianImageRes());

    }

    public void renderActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.action_bar);

        View actionBarView = getActionBarView();

        TextView actionBarTitle = (TextView) actionBarView .findViewById(R.id.action_bar_title);
        actionBarTitle.setText(mMusician.getMusicianName());

        final Button backButton = (Button) actionBarView.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //пример добавления элементов
                onBackPressed();
            }
        });
    }

    public View getActionBarView() {
        Window window = getWindow();
        View v = window.getDecorView();
        return v.findViewById(R.id.action_bar);
    }
}
