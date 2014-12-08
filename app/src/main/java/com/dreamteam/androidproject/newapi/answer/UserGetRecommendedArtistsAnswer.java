package com.dreamteam.androidproject.newapi.answer;

import com.dreamteam.androidproject.newapi.template.ObjectList;

/**
 * Created by nap on 12/4/2014.
 */

public class UserGetRecommendedArtistsAnswer {
    private String status;
    private ObjectList<ArtistGetInfoAnswer> recommendations = new ObjectList<ArtistGetInfoAnswer>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ObjectList<ArtistGetInfoAnswer> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(ObjectList<ArtistGetInfoAnswer> recommendations) {
        this.recommendations = recommendations;
    }
}
