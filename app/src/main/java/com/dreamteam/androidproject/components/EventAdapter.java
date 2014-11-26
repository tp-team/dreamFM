package com.dreamteam.androidproject.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamteam.androidproject.R;

import java.util.ArrayList;


public class EventAdapter extends ArrayAdapter<Event> {

    private Context context;
    private ArrayList<Event> items;
    private LayoutInflater vi;

    public EventAdapter(Context context, ArrayList<Event> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final Event i = items.get(position);
        if (i != null) {
            Event si = i;
            if (v == null) {
                v = vi.inflate(R.layout.event_card, null);
            }
            v.setOnClickListener(null);
            v.setOnLongClickListener(null);
            v.setLongClickable(false);

            ImageView eventImage = (ImageView) v.findViewById(R.id.event_card_image);
            eventImage.setImageResource(si.getImageRes());

            TextView eventName = (TextView) v.findViewById(R.id.event_card_name);
            eventName.setText(si.getName());

            TextView eventPlace = (TextView) v.findViewById(R.id.event_card_place);
            eventPlace.setText(si.getPlace());

            TextView day = (TextView) v.findViewById(R.id.event_card_day);
            day.setText(si.getDay());

            TextView month = (TextView) v.findViewById(R.id.event_card_month);
            month.setText(si.getMonth());

            TextView eventAttendance = (TextView) v.findViewById(R.id.event_card_attendance);
            eventAttendance.setText(si.getAttendance());

            ArrayList<Integer> fans = si.getFansImages();
            Integer fan1 = fans.get(0);
            Integer fan2 = fans.get(1);
            Integer fan3 = fans.get(2);

            ImageView fanImage1 = (ImageView) v.findViewById(R.id.event_card_fan1);
            fanImage1.setImageResource(fan1);

            ImageView fanImage2 = (ImageView) v.findViewById(R.id.event_card_fan2);
            fanImage2.setImageResource(fan2);

            ImageView fanImage3 = (ImageView) v.findViewById(R.id.event_card_fan3);
            fanImage3.setImageResource(fan3);

        }
        return v;
    }

}