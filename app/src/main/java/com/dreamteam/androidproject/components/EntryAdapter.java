package com.dreamteam.androidproject.components;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dreamteam.androidproject.NavigationDrawerFragment;
import com.dreamteam.androidproject.R;

public class EntryAdapter extends ArrayAdapter<Item> {

    private Context context;
    private ArrayList<Item> items;
    private LayoutInflater vi;

    public EntryAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final Item i = items.get(position);
        if (i != null) {
            if(i.isSection()){
                SectionItem si = (SectionItem)i;
                if (v == null) {
                    v = vi.inflate(R.layout.section_item, null);
                }

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                final TextView sectionView = (TextView) v.findViewById(R.id.section_item_text);
                sectionView.setText(si.getTitle());

            }else{
                EntryItem ei = (EntryItem)i;
                if (v == null) {
                    v = vi.inflate(R.layout.entry_item, null);
                }

                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                final TextView title = (TextView)v.findViewById(R.id.entry_item_text);

                if (title != null)
                    title.setText(ei.title);
            }
        }
        return v;
    }

}
