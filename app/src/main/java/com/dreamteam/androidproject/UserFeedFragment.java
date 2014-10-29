package com.dreamteam.androidproject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

import com.dreamteam.androidproject.components.Album;
import com.dreamteam.androidproject.components.AlbumAdapter;
import com.dreamteam.androidproject.components.Event;
import com.dreamteam.androidproject.components.EventAdapter;
import com.dreamteam.androidproject.components.Musician;
import com.dreamteam.androidproject.components.MusicianAdapter;
import com.dreamteam.androidproject.customViews.ExpandableHeightGridView;

import java.util.ArrayList;
import java.util.Calendar;

public class UserFeedFragment extends Fragment {

    private View mUserFeed;
    private View musicView;
    private ArrayList<Musician> musicList;

    public UserFeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mUserFeed = inflater.inflate(R.layout.user_feed, container, false);
        Log.d("in uf create view", "omg");

        setMusiciansGrid();
        setAlbumsGrid();
        setEventsGrid();

        final Button musicButton = (Button) musicView.findViewById(R.id.more_button);
        musicButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //пример добавления элементов
                ExpandableHeightGridView musiciansGrid = (ExpandableHeightGridView) musicView.findViewById(R.id.feed_grid);

                Musician child1 = new Musician("Nigthwish", R.drawable.nightwish, null);
                Musician child2 = new Musician("Epica", R.drawable.epica, null);
                ArrayList<Musician> children = new ArrayList<Musician>();
                children.add(child1);
                children.add(child2);
                Musician parent = new Musician("Evanescence", R.drawable.evanescence, children);
                musicList.add(parent);
                musicList.add(parent);
                musicList.add(parent);

                MusicianAdapter adapter = new MusicianAdapter(getActivity().getActionBar().getThemedContext(), musicList);

                musiciansGrid.setAdapter(adapter);
                musiciansGrid.setExpanded(true);
            }
        });

        return mUserFeed;
    }

    private void setMusiciansGrid() {//пример создания сетки с элементами

        ArrayList<Musician> items = new ArrayList<Musician>();

        musicView = mUserFeed.findViewById(R.id.feed_music);
        ExpandableHeightGridView musiciansGrid = (ExpandableHeightGridView) musicView.findViewById(R.id.feed_grid);

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

        MusicianAdapter adapter = new MusicianAdapter(getActivity().getActionBar().getThemedContext(), items);
        musicList = items;
        musiciansGrid.setAdapter(adapter);
        musiciansGrid.setExpanded(true);

    }

    private void setAlbumsGrid() {
        ArrayList<Album> items = new ArrayList<Album>();

        View albumsView = mUserFeed.findViewById(R.id.feed_albums);
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

    public void getFocusOn(int id) {
        Log.d("ID IN GET FOCUS ON", " " + id);
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
