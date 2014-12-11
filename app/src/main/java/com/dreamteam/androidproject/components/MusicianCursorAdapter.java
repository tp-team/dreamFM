package com.dreamteam.androidproject.components;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamteam.androidproject.R;
import com.dreamteam.androidproject.storages.database.DataBase;


public class MusicianCursorAdapter extends CursorAdapter {

    private Context context;
    private LayoutInflater vi;

    public MusicianCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        this.context = context;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return  vi.inflate(R.layout.musician_card, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //ImageView musicianImage = (ImageView) view.findViewById(R.id.musician_card_image);
        new DownloadImageTask((ImageView) view.findViewById(R.id.musician_card_image)).execute(cursor.getString(cursor.getColumnIndex(DataBase.ARTISTS_COLUMN_URL_IMG)));
        //musicianImage.setImageResource(si.getMusicianImageRes());

        TextView musicianName = (TextView) view.findViewById(R.id.musician_card_name);
        musicianName.setText(cursor.getString(cursor.getColumnIndex(DataBase.ARTISTS_COLUMN_NAME)));

//        ArrayList<Musician> similars = si.getSimilarMusicians();
//        Musician similar1 = similars.get(0);
//        Musician similar2 = similars.get(1);
//
//        TextView musicianSimilars = (TextView) v.findViewById(R.id.musician_card_similars);
//        musicianSimilars.setText("Similar to " + similar1.getMusicianName() + " and " +
//                similar2.getMusicianName());
//
//        ImageView similarImage1 = (ImageView) v.findViewById(R.id.musician_card_similars_image1);
//        similarImage1.setImageResource(similar1.getMusicianImageRes());
//
//        ImageView similarImage2 = (ImageView) v.findViewById(R.id.musician_card_similars_image2);
//        similarImage2.setImageResource(similar2.getMusicianImageRes());
    }


}
