package com.dreamteam.androidproject.newapi.answer;

import com.dreamteam.androidproject.newapi.template.ObjectList;

/**
 * Created by nap on 12/10/2014.
 */

public class ArtistGetSimilarAnswer {
    private String status;
    private ObjectList<ArtistGetInfoAnswer> similarartists = new ObjectList<ArtistGetInfoAnswer>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ObjectList<ArtistGetInfoAnswer> getSimilarartists() {
        return similarartists;
    }

    public void setSimilarartists(ObjectList<ArtistGetInfoAnswer> similarartists) {
        this.similarartists = similarartists;
    }
}
