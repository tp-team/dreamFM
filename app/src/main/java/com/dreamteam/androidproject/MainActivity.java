package com.dreamteam.androidproject;

import android.app.Activity;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.SearchView;

import com.dreamteam.androidproject.api.answer.UserInfoAnswer;
import com.dreamteam.androidproject.components.User;
import com.dreamteam.androidproject.storages.PreferencesSystem;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    static String userFeedTag = "USER_FEED_TAG";
    private SharedPreferences mSharedPreferences;

    public final static String EXTRA_MESSAGE = "com.dreamteam.androidproject.MESSAGE";

    private User mUser;

    private PreferencesSystem prefSystem;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefSystem = new PreferencesSystem(getApplicationContext());


        prefSystem = new PreferencesSystem(getApplicationContext());
        mUser = new User(prefSystem.getText(UserInfoAnswer.REALNAME), prefSystem.getText(UserInfoAnswer.USER_PHOTO_RES),
                R.drawable.mail2, prefSystem.getText(UserInfoAnswer.PLAYS_COUNT), prefSystem.getText(UserInfoAnswer.REGISTERED));

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("ADDRESS", mSharedPreferences.getString("address", ""));

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
}
