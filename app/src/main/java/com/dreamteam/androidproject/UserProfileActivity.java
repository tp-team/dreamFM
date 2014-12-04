package com.dreamteam.androidproject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamteam.androidproject.components.Event;
import com.dreamteam.androidproject.components.EventAdapter;
import com.dreamteam.androidproject.components.Musician;
import com.dreamteam.androidproject.components.MusicianAdapter;
import com.dreamteam.androidproject.components.Track;
import com.dreamteam.androidproject.components.TrackAdapter;
import com.dreamteam.androidproject.components.User;
import com.dreamteam.androidproject.customViews.ExpandableHeightGridView;
import com.dreamteam.androidproject.customViews.NotifyingScrollView;

import java.util.ArrayList;
import java.util.Calendar;


public class UserProfileActivity extends Activity {

    public User mUser;
    public NotifyingScrollView mUserView;

    private Drawable mActionBarBackgroundDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mUser = (User) intent.getSerializableExtra("user");

        setContentView(R.layout.activity_profile);
        mUserView = (NotifyingScrollView) this.findViewById(R.id.profile_page);

        renderActionBar();

        ImageView userBgImage = (ImageView) mUserView.findViewById(R.id.user_bg_image);
        userBgImage.setImageResource(mUser.getUserBgImageRes());

        ImageView userPhoto = (ImageView) mUserView.findViewById(R.id.user_photo);
        userPhoto.setImageResource(mUser.getUserPhotoRes());

        TextView userName = (TextView) mUserView.findViewById(R.id.user_name);
        userName.setText(mUser.getUserName());

        TextView playsCount = (TextView) mUserView.findViewById(R.id.plays_count);
        playsCount.setText(mUser.getPlaysCount());

        setRecentTracks();

        setLibraryGrid();
    }

    public void renderActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Profile");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mActionBarBackgroundDrawable = getResources().getDrawable(R.drawable.action_bar_border);
        mActionBarBackgroundDrawable.setAlpha(0);

        actionBar.setBackgroundDrawable(mActionBarBackgroundDrawable);

        mUserView.setOnScrollChangedListener(mOnScrollChangedListener);
    }

    private NotifyingScrollView.OnScrollChangedListener mOnScrollChangedListener = new NotifyingScrollView.OnScrollChangedListener() {
        private int headerHeight = 0;

        public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
            if (headerHeight == 0) {
                headerHeight = findViewById(R.id.user_bg_image).getHeight() - getActionBar().getHeight();
            }
            final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
            final int newAlpha = (int) (ratio * 255);
            mActionBarBackgroundDrawable.setAlpha(newAlpha);
        }
    };

    private void setRecentTracks() {
        ArrayList<Track> items = new ArrayList<Track>();

        View rtSection = mUserView.findViewById(R.id.recent_tracks);
        View rtHeader = rtSection.findViewById(R.id.feed_section);
        rtHeader.setBackgroundColor(getResources().getColor(R.color.white));

        TextView sectionTitle = (TextView) rtHeader.findViewById(R.id.feed_section_title);
        sectionTitle.setText(R.string.user_recent_tracks);
        sectionTitle.setTextColor(getResources().getColor(R.color.main_red));

        ExpandableHeightGridView rtGrid = (ExpandableHeightGridView) rtSection.findViewById(R.id.feed_grid);
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (1*scale + 0.5f);
        rtGrid.setHorizontalSpacing(dpAsPixels);
        rtGrid.setVerticalSpacing(dpAsPixels);
        rtGrid.setPadding(0, dpAsPixels, 0, dpAsPixels);
        rtGrid.setBackgroundColor(Color.parseColor("#E1E1E1"));

        Musician musician1 = new Musician("Nigthwish", R.drawable.nightwish, null);
        Musician musician2 = new Musician("Epica", R.drawable.epica, null);
        Track track1 = new Track("Nemo", "11:15", musician1);
        Track track2 = new Track("Wishmaster", "09:05", musician1);
        Track track3 = new Track("The Essence of Silence", "05:01", musician2);

        items.add(track1);
        items.add(track2);
        items.add(track3);

        TrackAdapter adapter = new TrackAdapter(getActionBar().getThemedContext(), items);
        rtGrid.setAdapter(adapter);
        rtGrid.setExpanded(true);
        rtGrid.setNumColumns(1);
    }

    private void setLibraryGrid() {

        ArrayList<Musician> items = new ArrayList<Musician>();

        View libraryView = mUserView.findViewById(R.id.user_library);

        View libraryHeader = libraryView.findViewById(R.id.feed_section);
        libraryHeader.setBackgroundColor(getResources().getColor(R.color.white));

        TextView sectionTitle = (TextView) libraryHeader.findViewById(R.id.feed_section_title);
        sectionTitle.setText(mUser.getUserName() + "'" + getResources().getString(R.string.user_library));
        sectionTitle.setTextColor(getResources().getColor(R.color.main_red));

        Button moreButton = (Button) libraryHeader.findViewById(R.id.action_button);
        moreButton.setText(100 + " " + getResources().getString(R.string.more_button));

        ExpandableHeightGridView musiciansGrid = (ExpandableHeightGridView) libraryView.findViewById(R.id.feed_grid);
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
        items.add(parent);
        items.add(parent2);
        items.add(parent);
        items.add(parent2);

        MusicianAdapter adapter = new MusicianAdapter(getActionBar().getThemedContext(), items);
        musiciansGrid.setAdapter(adapter);
        musiciansGrid.setExpanded(true);

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
            case R.id.menu_add_to_library:
                Toast.makeText(this, "Adding to library!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}