package com.dreamteam.androidproject.activities.gridActivities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.dreamteam.androidproject.R;
import com.dreamteam.androidproject.activities.GridActivity;
import com.dreamteam.androidproject.api.answer.AuthAnswer;
import com.dreamteam.androidproject.api.answer.UserGetRecommendedArtistsAnswer;
import com.dreamteam.androidproject.api.template.Common;
import com.dreamteam.androidproject.components.DownloadImageTask;
import com.dreamteam.androidproject.handlers.BaseCommand;
import com.dreamteam.androidproject.storages.PreferencesSystem;
import com.dreamteam.androidproject.storages.database.DataBase;
import com.dreamteam.androidproject.storages.database.querys.ArtistsQuery;

import java.util.concurrent.TimeUnit;


public class ArtistsActivity extends GridActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private int recommendArtistId = -1;
    private ArtistsQuery artistsDB;
    private SimpleCursorAdapter mAdapter;
    private PreferencesSystem mPrefSystem;
    private String mKey;
    private int myLastVisiblePos;
    private int mElementsCount = -1;
    private int mPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefSystem = new PreferencesSystem(getApplicationContext());
        mKey = mPrefSystem.getText(AuthAnswer.KEY);

        recommendArtistId = getServiceHelper().getRecommendedArtists(Integer.toString(mPage), "10", mKey);
        mPage++;
        artistsDB = new ArtistsQuery(this);
        artistsDB.open();

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    protected void setGrid() {
        String[] from = new String[] { DataBase.ARTISTS_COLUMN_URL_IMG, DataBase.ARTISTS_COLUMN_NAME };
        int[] to = new int[] { R.id.musician_card_image, R.id.musician_card_name };

        if (mAdapter == null) {
            Log.d("USER FEED FRAGMENT", "CREATING NEW ADAPTER");
            // создааем адаптер и настраиваем список
            mAdapter = new SimpleCursorAdapter(ArtistsActivity.this, R.layout.musician_card, null, from, to, 0);

            mAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                    if (view.getId() == R.id.musician_card_image) {
                        
                        ImageView v = (ImageView) view;
                        new DownloadImageTask(v).execute(cursor.getString(columnIndex));
                        return true;
                    }
                    return false;
                }
            });
        }

        mGridView.setAdapter(mAdapter);

        myLastVisiblePos = mGridView.getFirstVisiblePosition();
        mGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                int currentLastVisPos = view.getLastVisiblePosition();
                if (mElementsCount != -1 && currentLastVisPos == mElementsCount - 1) {
                    recommendArtistId = getServiceHelper().getRecommendedArtists(Integer.toString(mPage), "10", mKey);
                    mPage++;
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView v, int i) {

            }
        });
    }

    @Override
    public void onServiceCallback(int requestId, Intent requestIntent, int resultCode, Bundle resultData) {
        super.onServiceCallback(requestId, requestIntent, resultCode, resultData);

        if (ArtistsActivity.this.recommendArtistId == requestId) {
            callbackRecommendArtist(requestIntent, resultCode, resultData);
        }

    }

    public void callbackRecommendArtist(Intent requestIntent, int resultCode, Bundle resultData) {
        switch (resultCode) {
            case BaseCommand.RESPONSE_SUCCESS: {
                String status = resultData.getString(UserGetRecommendedArtistsAnswer.STATUS_RECOMMENDED_ARTISTS);
                if (status.equals(Common.STATUS_OK)) {
//                    if (mAdapter != null) {
//                        mAdapter.notifyDataSetChanged();
//                    }
                    setGrid();
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
        return new ArtistsCursorLoader(this, artistsDB);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mElementsCount = cursor.getCount();
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    static class ArtistsCursorLoader extends CursorLoader {

        ArtistsQuery db;

        public ArtistsCursorLoader(Context context, ArtistsQuery db) {
            super(context);
            this.db = db;
        }

        @Override
        public Cursor loadInBackground() {
            Cursor cursor = db.getRecommended();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return cursor;
        }

    }
}

