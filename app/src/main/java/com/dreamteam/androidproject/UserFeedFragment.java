package com.dreamteam.androidproject;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dreamteam.androidproject.components.Album;
import com.dreamteam.androidproject.components.AlbumAdapter;
import com.dreamteam.androidproject.components.Event;
import com.dreamteam.androidproject.components.EventAdapter;
import com.dreamteam.androidproject.components.Musician;
import com.dreamteam.androidproject.components.MusicianAdapter;
import com.dreamteam.androidproject.customViews.ExpandableHeightGridView;
import com.dreamteam.androidproject.storages.database.DataBase;

import java.util.ArrayList;
import java.util.Calendar;

public class UserFeedFragment extends Fragment {

    private View mUserFeed;
    private View musicView;
    private ArrayList<Musician> musicList;
    //private MainActivity mActivity;

    public UserFeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //mActivity = (MainActivity) getActivity();
        mUserFeed = inflater.inflate(R.layout.user_feed, container, false);

        setMusiciansGrid();
        setAlbumsGrid();
        setEventsGrid();

        return mUserFeed;
    }

    private void setMusiciansGrid() {//пример создания сетки с элементами

        ArrayList<Musician> items = new ArrayList<Musician>();

        musicView = mUserFeed.findViewById(R.id.feed_music);

        TextView sectionTitle = (TextView) musicView.findViewById(R.id.feed_section_title);
        sectionTitle.setText(R.string.feed_music);

        setMoreButtonClickListener(musicView, "musician", getResources().getString(R.string.feed_music));

        ExpandableHeightGridView musiciansGrid = (ExpandableHeightGridView) musicView.findViewById(R.id.feed_grid);

//        Musician child1 = new Musician("Nigthwish", R.drawable.nightwish, null);
//        Musician child2 = new Musician("Epica", R.drawable.epica, null);
//        ArrayList<Musician> children = new ArrayList<Musician>();
//        children.add(child1);
//        children.add(child2);
//        Musician parent = new Musician("Evanescence", R.drawable.evanescence, children);
//        ArrayList<Musician> children2 = new ArrayList<Musician>();
//        children2.add(parent);
//        children2.add(child1);
//        Musician parent2 = new Musician("Epica", R.drawable.epica, children2);
//
//        items.add(parent);
//        items.add(parent2);
//        items.add(parent2);
//        items.add(parent);
//        items.add(parent);
//        items.add(parent2);
//        items.add(parent2);
//        items.add(parent);
//
//        MusicianAdapter adapter = new MusicianAdapter(getActivity().getActionBar().getThemedContext(), items);
//        musicList = items;

        String[] from = new String[] { DataBase.RECOMMEND_COLUMN_URL_IMG, DataBase.RECOMMEND_COLUMN_NAME };
        int[] to = new int[] { R.id.musician_card_image, R.id.musician_card_name };

        // создааем адаптер и настраиваем список
        ((MainActivity) getActivity()).setCursorAdapter("artists", new SimpleCursorAdapter(getActivity(), R.layout.musician_card, null, from, to, 0));

        musiciansGrid.setAdapter(((MainActivity) getActivity()).getCursorAdapter("artists"));
        musiciansGrid.setExpanded(true);

    }

    private void setAlbumsGrid() {
        ArrayList<Album> items = new ArrayList<Album>();

        View albumsView = mUserFeed.findViewById(R.id.feed_albums);

        setMoreButtonClickListener(albumsView, "album", getResources().getString(R.string.feed_new_releases));

        TextView sectionTitle = (TextView) albumsView.findViewById(R.id.feed_section_title);
        sectionTitle.setText(R.string.feed_new_releases);

        ExpandableHeightGridView albumsGrid = (ExpandableHeightGridView) albumsView.findViewById(R.id.feed_grid);

        Musician creator = new Musician("Within Temptation", R.drawable.withintemptation, null);
        Album album = new Album("Hydra", 2014, R.drawable.hydra, creator);

        items.add(album);
        items.add(album);
        items.add(album);
        items.add(album);

        AlbumAdapter adapter = new AlbumAdapter(getActivity().getActionBar().getThemedContext(), items);

        albumsGrid.setAdapter(adapter);
        albumsGrid.setExpanded(true);

    }

    private void setEventsGrid() {
        ArrayList<Event> items = new ArrayList<Event>();

        View eventsView = mUserFeed.findViewById(R.id.feed_events_near_me);

        setMoreButtonClickListener(eventsView, "event", getResources().getString(R.string.feed_upcoming_events));

        TextView sectionTitle = (TextView) eventsView.findViewById(R.id.feed_section_title);
        sectionTitle.setText(R.string.feed_upcoming_events);

        ExpandableHeightGridView eventsGrid = (ExpandableHeightGridView) eventsView.findViewById(R.id.feed_grid);

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

        EventAdapter adapter = new EventAdapter(getActivity().getActionBar().getThemedContext(), items);

        eventsGrid.setAdapter(adapter);
        eventsGrid.setExpanded(true);
        eventsGrid.setNumColumns(1);
    }

    private void setMoreButtonClickListener(View v, String type, String title) {
        final Button moreButton = (Button) v.findViewById(R.id.action_button);
        final String gridType = type;
        final String activityTitle = title;
        moreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GridActivity.class);

                intent.putExtra("type", gridType);
                intent.putExtra("title", activityTitle);
                getActivity().startActivity(intent);
            }
        });
    }

    public void getFocusOn(int id) {
        final View targetView = mUserFeed.findViewById(id);
        final ScrollView scrollView = (ScrollView) mUserFeed.findViewById(R.id.scroll_feed);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0, targetView.getTop());
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
