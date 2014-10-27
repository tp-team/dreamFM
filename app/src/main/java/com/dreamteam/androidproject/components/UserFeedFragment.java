package com.dreamteam.androidproject.components;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.dreamteam.androidproject.R;

public class UserFeedFragment extends Fragment {

    private View mUserFeed;

    public UserFeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mUserFeed = inflater.inflate(R.layout.user_feed, container, false);
        Log.d("in uf create view", "omg");
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
