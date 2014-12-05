package com.dreamteam.androidproject.api.template;

import java.util.ArrayList;

/**
 * Created by nap on 12/4/2014.
 */

public class ObjectList<Object> {
    private ArrayList<Object> list = new ArrayList<Object>();

    public void add(Object obj) {
        this.list.add(obj);
    }

    public int getLength() {
        return this.list.size();
    }

    public Object get(int index) {
        return this.list.get(index);
    }
}
