package com.dreamteam.androidproject.api.answer;

import android.os.Bundle;
import com.dreamteam.androidproject.api.template.ObjectList;


public class UserGetRecommendedArtistsAnswer {
    public static String STATUS_RECOMMENDED_ARTISTS = "RECOMMENDED_ARTISTS_STATUS";
    public static String TEXT_STATUS                = "TEXT_STATUS";

    private String status;
    private String textStatus;
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

    public String getTextStatus() {
        return textStatus;
    }

    public void setTextStatus(String textStatus) {
        this.textStatus = textStatus;
    }

    public Bundle getBundleObject() {
        Bundle bun = new Bundle();
        bun.putString(STATUS_RECOMMENDED_ARTISTS, status);
        bun.putString(TEXT_STATUS, textStatus);
        return bun;
    }

}
