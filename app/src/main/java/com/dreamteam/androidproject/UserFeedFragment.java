package com.dreamteam.androidproject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.dreamteam.androidproject.R;
import com.dreamteam.androidproject.components.Artist;
import com.dreamteam.androidproject.components.ArtistAdapter;

import java.util.ArrayList;

public class UserFeedFragment extends Fragment {

    private View mUserFeed;

    public UserFeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mUserFeed = inflater.inflate(R.layout.user_feed, container, false);
        Log.d("in uf create view", "omg");

        ArrayList<Artist> items = new ArrayList<Artist>();

        GridView artistsGrid = (GridView) mUserFeed.findViewById(R.id.artists_grid);

        Artist child1 = new Artist("Nigthwish", R.drawable.nightwish, null);
        Artist child2 = new Artist("Epica", R.drawable.epica, null);
        ArrayList<Artist> children = new ArrayList<Artist>();
        children.add(child1);
        children.add(child2);
        Artist parent = new Artist("Evanescence", R.drawable.evanescence, children);

        items.add(parent);
        items.add(parent);
        items.add(parent);
        items.add(parent);
        items.add(parent);
        items.add(parent);
        items.add(parent);

        ArtistAdapter adapter = new ArtistAdapter(getActivity().getActionBar().getThemedContext(), items);

        artistsGrid.setAdapter(adapter);

        return mUserFeed;
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
