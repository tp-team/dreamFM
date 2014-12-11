package com.dreamteam.androidproject.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamteam.androidproject.R;
import com.dreamteam.androidproject.components.Event;
import com.dreamteam.androidproject.components.EventAdapter;
import com.dreamteam.androidproject.components.Musician;
import com.dreamteam.androidproject.components.MusicianAdapter;
import com.dreamteam.androidproject.customViews.ExpandableHeightGridView;
import com.dreamteam.androidproject.customViews.NotifyingScrollView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Egor on 26.11.2014.
 */
public class MusicianActivity extends Activity {

    public Musician mMusician;
    public NotifyingScrollView mMusicianView;

    private Drawable mActionBarBackgroundDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mMusician = (Musician) intent.getSerializableExtra("musician");

        setContentView(R.layout.activity_musician);
        mMusicianView = (NotifyingScrollView) this.findViewById(R.id.musician_page);

        renderActionBar();

        ImageView musicianImage = (ImageView) mMusicianView.findViewById(R.id.musician_image);
        musicianImage.setImageResource(mMusician.getMusicianImageRes());

        setBioSection();

        setSimilarsGrid();

        setEventsGrid();

    }

    public void renderActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setTitle(mMusician.getMusicianName());
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mActionBarBackgroundDrawable = getResources().getDrawable(R.drawable.action_bar_border);
        mActionBarBackgroundDrawable.setAlpha(0);

        actionBar.setBackgroundDrawable(mActionBarBackgroundDrawable);

        mMusicianView.setOnScrollChangedListener(mOnScrollChangedListener);
    }

    private NotifyingScrollView.OnScrollChangedListener mOnScrollChangedListener = new NotifyingScrollView.OnScrollChangedListener() {
        private int headerHeight = 0;

        public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
            if (headerHeight == 0) {
                headerHeight = findViewById(R.id.musician_image).getHeight() - getActionBar().getHeight();
            }
            final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
            final int newAlpha = (int) (ratio * 255);
            mActionBarBackgroundDrawable.setAlpha(newAlpha);
        }
    };

    private void setBioSection() {
        View bioSection = mMusicianView.findViewById(R.id.musician_bio);
        View bioHeader = bioSection.findViewById(R.id.feed_section);
        bioHeader.setBackgroundColor(getResources().getColor(R.color.transparent));

        TextView sectionTitle = (TextView) bioHeader.findViewById(R.id.feed_section_title);
        sectionTitle.setText(R.string.musician_biography);
        sectionTitle.setTextColor(getResources().getColor(R.color.main_red));

        Button fullBioButton = (Button) bioHeader.findViewById(R.id.action_button);
        fullBioButton.setText(R.string.musician_full_bio);

        TextView bioText = (TextView) bioSection.findViewById(R.id.musician_bio_text);
        bioText.setText(R.string.big_text);
    }

    private void setSimilarsGrid() {//пример создания сетки с элементами

        ArrayList<Musician> items = new ArrayList<Musician>();

        View similarsView = mMusicianView.findViewById(R.id.musician_similars);

        View similarsHeader = similarsView.findViewById(R.id.feed_section);
        similarsHeader.setBackgroundColor(getResources().getColor(R.color.transparent));

        TextView sectionTitle = (TextView) similarsHeader.findViewById(R.id.feed_section_title);
        sectionTitle.setText(R.string.musician_similars);
        sectionTitle.setTextColor(getResources().getColor(R.color.main_red));

        ExpandableHeightGridView musiciansGrid = (ExpandableHeightGridView) similarsView.findViewById(R.id.feed_grid);
        musiciansGrid.setBackgroundColor(getResources().getColor(R.color.white));

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

        MusicianAdapter adapter = new MusicianAdapter(MusicianActivity.this.getActionBar().getThemedContext(), items);
        musiciansGrid.setAdapter(adapter);
        musiciansGrid.setExpanded(true);

    }

    private void setEventsGrid() {
        ArrayList<Event> items = new ArrayList<Event>();

        View eventsView = mMusicianView.findViewById(R.id.musician_events);

        View eventsHeader = eventsView.findViewById(R.id.feed_section);
        eventsHeader.setBackgroundColor(getResources().getColor(R.color.transparent));

        TextView sectionTitle = (TextView) eventsHeader.findViewById(R.id.feed_section_title);
        sectionTitle.setText(R.string.musician_events);
        sectionTitle.setTextColor(getResources().getColor(R.color.main_red));

        ExpandableHeightGridView eventsGrid = (ExpandableHeightGridView) eventsView.findViewById(R.id.feed_grid);
        eventsGrid.setBackgroundColor(getResources().getColor(R.color.white));

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

        EventAdapter adapter = new EventAdapter(this.getActionBar().getThemedContext(), items);

        eventsGrid.setAdapter(adapter);
        eventsGrid.setExpanded(true);
        eventsGrid.setNumColumns(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.menu_add_to_library).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_add_to_library:
                Toast.makeText(this, "Adding to library!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
