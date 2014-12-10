package com.dreamteam.androidproject;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;
import android.support.v4.app.LoaderManager.LoaderCallbacks;

import com.dreamteam.androidproject.api.answer.AuthAnswer;
import com.dreamteam.androidproject.api.answer.UserGetRecommendedArtistsAnswer;
import com.dreamteam.androidproject.api.answer.UserInfoAnswer;
import com.dreamteam.androidproject.api.template.Common;
import com.dreamteam.androidproject.components.User;
import com.dreamteam.androidproject.handlers.BaseCommand;
import com.dreamteam.androidproject.storages.PreferencesSystem;
import com.dreamteam.androidproject.storages.database.querys.NewReleasesQuery;
import com.dreamteam.androidproject.storages.database.querys.RecommendedArtistsQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, LoaderCallbacks<Cursor> {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    static String userFeedTag = "USER_FEED_TAG";
    private SharedPreferences mSharedPreferences;
    RecommendedArtistsQuery artistsDB;
    NewReleasesQuery releasesDB;
    private Map<String, SimpleCursorAdapter> mAdapters;

    private User mUser;

    private PreferencesSystem prefSystem;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private int recommendArtistId = -1;
    private int newReleasesId = -2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefSystem = new PreferencesSystem(getApplicationContext());

        String key = prefSystem.getText(AuthAnswer.KEY);
        Log.d("Tag_MAIN_ACTIVITY", key);

        mUser = new User(prefSystem.getText(UserInfoAnswer.REALNAME), prefSystem.getText(UserInfoAnswer.NICKNAME), prefSystem.getText(UserInfoAnswer.USER_PHOTO_RES),
                R.drawable.mail2, prefSystem.getText(UserInfoAnswer.PLAYS_COUNT), prefSystem.getText(UserInfoAnswer.REGISTERED));

        mAdapters = new HashMap<String, SimpleCursorAdapter>();

        recommendArtistId = getServiceHelper().getRecommendedArtists("1", "6", key);
        newReleasesId = getServiceHelper().getNewReleases(mUser.getNickName());

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("ADDRESS", mSharedPreferences.getString("address", ""));

        artistsDB = new RecommendedArtistsQuery(this);
        artistsDB.open();
        releasesDB = new NewReleasesQuery(this);
        releasesDB.open();

        FragmentManager fragmentManager = getFragmentManager();

        UserFeedFragment userFeedFragment = (UserFeedFragment) fragmentManager.findFragmentByTag(userFeedTag);
        if (userFeedFragment == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.container, new UserFeedFragment(), userFeedTag)
                    .commit();
        }

        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                fragmentManager.findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        getSupportLoaderManager().initLoader(0, null, this);
        getSupportLoaderManager().initLoader(1, null, this);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        int id = 0;
        switch (position) {
//            case 1:
//                id = R.id.feed_music;
//                break;
//            case 2:
//                id = R.id.feed_albums;
//                break;
//            case 3:
//                id = R.id.feed_releases;
//                break;
//            case 5:
//                id = R.id.feed_my_events;
//                break;
//            case 6:
//                id = R.id.feed_your_recs;
//                break;
//            case 7:
//                id = R.id.feed_events_near_me;
//                break;
            case 9:
                Intent intent = new Intent(MainActivity.this, PreferencesActivity.class);
                startActivity(intent);
        }
//
//        UserFeedFragment userFeedFragment = (UserFeedFragment) getFragmentManager().findFragmentByTag(userFeedTag);
//
//        if (id != 0 && userFeedFragment != null && userFeedFragment.getView() != null) {
//            Log.d("use feed frg state", userFeedFragment.toString());
//            userFeedFragment.getFocusOn(id);
//        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(false);
        //actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);

            // Get the SearchView and set the searchable configuration
            //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            //SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
            // Assumes current activity is the searchable activity
            //searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            //searchView.setIconifiedByDefault(false);

            MenuItem mi = menu.add(0, 1, 0, "Preferences");
            mi.setIntent(new Intent(this, PreferencesActivity.class));
            restoreActionBar();
            return true;
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public User getmUser() {
        return mUser;
    }


    @Override
    public void onServiceCallback(int requestId, Intent requestIntent, int resultCode, Bundle resultData) {
        super.onServiceCallback(requestId, requestIntent, resultCode, resultData);

        if (MainActivity.this.recommendArtistId == requestId) {
            callbackRecommendArtist(requestIntent, resultCode, resultData);
        }

    }

    public void callbackRecommendArtist(Intent requestIntent, int resultCode, Bundle resultData) {
        switch (resultCode) {
            case BaseCommand.RESPONSE_SUCCESS: {
                String status = resultData.getString(UserGetRecommendedArtistsAnswer.STATUS_RECOMMENDED_ARTISTS);
                if (status.equals(Common.STATUS_OK)) {

                }
                else {
                    Toast.makeText(this, resultData.getString(UserGetRecommendedArtistsAnswer.TEXT_STATUS), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case BaseCommand.RESPONSE_FAILURE: {
                Toast.makeText(this, resultData.getString(UserGetRecommendedArtistsAnswer.TEXT_STATUS), Toast.LENGTH_SHORT).show();
                break;
            }
        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bndl) {
        switch (id) {
            case 0:
                return new ArtistsCursorLoader(this, artistsDB);
            case 1:
                return new ReleasesCursorLoader(this, releasesDB);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()){
            case 0:
                mAdapters.get("artists").swapCursor(cursor);
                break;
            case 1:
                mAdapters.get("releases").swapCursor(cursor);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    public void setCursorAdapter(String type, SimpleCursorAdapter adapter) {
        mAdapters.put(type, adapter);
    }

    public SimpleCursorAdapter getCursorAdapter(String type) {
        return mAdapters.get(type);
    }

    static class ArtistsCursorLoader extends CursorLoader {

        RecommendedArtistsQuery db;

        public ArtistsCursorLoader(Context context, RecommendedArtistsQuery db) {
            super(context);
            this.db = db;
        }

        @Override
        public Cursor loadInBackground() {
            Cursor cursor = db.getTable();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return cursor;
        }

    }

    static class ReleasesCursorLoader extends CursorLoader {

        NewReleasesQuery db;

        public ReleasesCursorLoader(Context context, NewReleasesQuery db) {
            super(context);
            this.db = db;
        }

        @Override
        public Cursor loadInBackground() {
            Cursor cursor = db.getTable();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return cursor;
        }

    }

}
