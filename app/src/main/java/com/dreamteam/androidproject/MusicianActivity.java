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
import com.dreamteam.androidproject.components.MusicianAdapter;
import com.dreamteam.androidproject.customViews.ExpandableHeightGridView;

import java.util.ArrayList;

/**
 * Created by Egor on 26.11.2014.
 */
public class MusicianActivity extends Activity {

    public Musician mMusician;
    public View mMusicianView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mMusician = (Musician) intent.getSerializableExtra("musician");

        renderActionBar();
        setContentView(R.layout.activity_musician);
        mMusicianView = this.findViewById(R.id.musician_page);

        ImageView musicianImage = (ImageView) mMusicianView.findViewById(R.id.musician_image);
        musicianImage.setImageResource(mMusician.getMusicianImageRes());

        setSimilarsGrid();

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

    private void setSimilarsGrid() {//пример создания сетки с элементами

        ArrayList<Musician> items = new ArrayList<Musician>();

        View similarsView = mMusicianView.findViewById(R.id.musician_similars);
        ExpandableHeightGridView musiciansGrid = (ExpandableHeightGridView) similarsView.findViewById(R.id.feed_grid);

        Musician child1 = new Musician("Nigthwish", R.drawable.nightwish, null);
        Musician child2 = new Musician("Epica", R.drawable.epica, null);
        ArrayList<Musician> children = new ArrayList<Musician>();
        children.add(child1);
        children.add(child2);
        Musician parent = new Musician("Evanescence", R.drawable.evanescence, children);
        ArrayList<Musician> children2 = new ArrayList<Musician>();
        children2.add(parent);
        children2.add(child1);
        Musician parent2 = new Musician("Epica", R.drawable.epica, children2);

        items.add(parent);
        items.add(parent2);
        items.add(parent2);
        items.add(parent);
        items.add(parent);
        items.add(parent2);
        items.add(parent2);
        items.add(parent);

        MusicianAdapter adapter = new MusicianAdapter(MusicianActivity.this.getActionBar().getThemedContext(), items);
        musiciansGrid.setAdapter(adapter);
        musiciansGrid.setExpanded(true);

    }

    public View getActionBarView() {
        Window window = getWindow();
        View v = window.getDecorView();
        return v.findViewById(R.id.action_bar);
    }
}
