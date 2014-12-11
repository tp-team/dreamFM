package com.dreamteam.androidproject.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamteam.androidproject.R;
import com.dreamteam.androidproject.components.Album;
import com.dreamteam.androidproject.components.AlbumAdapter;
import com.dreamteam.androidproject.components.Event;
import com.dreamteam.androidproject.components.EventAdapter;
import com.dreamteam.androidproject.components.Musician;
import com.dreamteam.androidproject.components.MusicianAdapter;
import com.dreamteam.androidproject.customViews.NotifyingScrollView;

import java.util.ArrayList;
import java.util.Calendar;

public class GridActivity extends BaseActivity {

    protected GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        ActionBar actionBar = getActionBar();
        actionBar.setTitle(title);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_grid);
        mGridView = (GridView) findViewById(R.id.grid_page);
    }

    protected void setGrid() {
    }

    private void setEventsGrid() {
        ArrayList<Event> items = new ArrayList<Event>();

        ArrayList<Integer> fans = new ArrayList<Integer>();
        fans.add(R.drawable.fan1);
        fans.add(R.drawable.fan2);
        fans.add(R.drawable.fan3);
        Calendar date = Calendar.getInstance();
        date.set(2014, Calendar.NOVEMBER, 1);

        Musician musician = new Musician("Hollywood Undead", R.drawable.hollyundead, null);
        Event event = new Event("Hollywood Undead", "Ray Just Arena, Moscow, Russia",
                R.drawable.hollyundead, date.getTime(), musician, fans);

        items.add(event);
        items.add(event);
        items.add(event);

        EventAdapter adapter = new EventAdapter(getActionBar().getThemedContext(), items);

        mGridView.setAdapter(adapter);
        mGridView.setNumColumns(1);
    }

    private void setAlbumsGrid() {
        ArrayList<Album> items = new ArrayList<Album>();

        Musician creator = new Musician("Within Temptation", R.drawable.withintemptation, null);
        Album album = new Album("Hydra", 2014, R.drawable.hydra, creator);

        items.add(album);
        items.add(album);
        items.add(album);
        items.add(album);

        AlbumAdapter adapter = new AlbumAdapter(getActionBar().getThemedContext(), items);

        mGridView.setAdapter(adapter);
    }

    private void setTracksGrid() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
