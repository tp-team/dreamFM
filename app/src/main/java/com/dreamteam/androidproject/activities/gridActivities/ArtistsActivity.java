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
import com.dreamteam.androidproject.storages.database.querys.RecommendedArtistsQuery;

import java.util.concurrent.TimeUnit;


public class ArtistsActivity extends GridActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private int recommendArtistId = -1;
    private RecommendedArtistsQuery artistsDB;
    private SimpleCursorAdapter mAdapter;
    private PreferencesSystem mPrefSystem;
    private int myLastVisiblePos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefSystem = new PreferencesSystem(getApplicationContext());
        String key = mPrefSystem.getText(AuthAnswer.KEY);

        recommendArtistId = getServiceHelper().getRecommendedArtists("1", "10", key);
        artistsDB = new RecommendedArtistsQuery(this);
        artistsDB.open();

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    protected void setGrid() {
        String[] from = new String[] { DataBase.RECOMMEND_COLUMN_URL_IMG, DataBase.RECOMMEND_COLUMN_NAME };
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
                int currentFirstVisPos = view.getFirstVisiblePosition();
                if(currentFirstVisPos > myLastVisiblePos) {
                    Log.d("ARTISTS ACTIVITY", "SCROLL DOWN");
                }
                if(currentFirstVisPos < myLastVisiblePos) {
                    Log.d("ARTISTS ACTIVITY", "SCROLL UP");
                }
                myLastVisiblePos = currentFirstVisPos;
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
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
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
}

