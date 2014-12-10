package com.dreamteam.androidproject.api.query;

import com.dreamteam.androidproject.api.answer.EventGetInfoAnswer;
import com.dreamteam.androidproject.api.answer.VenueAnswer;
import com.dreamteam.androidproject.api.connection.SecretData;
import com.dreamteam.androidproject.api.template.Common;
import com.dreamteam.androidproject.api.template.ObjectList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nap on 12/11/2014.
 */

public class EventGetInfo extends Common {
    private String event;

    public EventGetInfo(String event) {
        this.event = event;
    }

    @Override
    protected EventGetInfoAnswer parse(String str) throws JSONException {
        JSONObject obj = new JSONObject(str);
        String status = null;
        try {
            status = getStatus(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        EventGetInfoAnswer answer = new EventGetInfoAnswer();
        answer.setStatus(errorToCode(status));
        answer.setTextStatus(status);
        if (!status.equals("ok")) {
            return answer;
        }
        JSONObject event = obj.getJSONObject("event");
        answer.setId(event.getString("id"));
        answer.setTitle(event.getString("title"));
        JSONObject artistsObj = event.getJSONObject("artists");
        JSONArray artistObj = artistsObj.getJSONArray("artist");
        ObjectList<String> artists = new ObjectList<String>();
        for (int i = 0; i < artistObj.length(); i++) {
            artists.add(artistObj.getString(i));
        }
        answer.setArtists(artists);
        answer.setHeadliner(artistsObj.getString("headliner"));
        VenueAnswer venue = new VenueAnswer();
        JSONObject venueObj = event.getJSONObject("venue");
        venue.setId(venueObj.getString("id"));
        venue.setName(venueObj.getString("name"));
        JSONObject location = venueObj.getJSONObject("location");
        venue.setCity(location.getString("city"));
        venue.setCountry(location.getString("country"));
        venue.setStreet(location.getString("street"));
        venue.setPostalcode(location.getString("postalcode"));
        JSONObject geopoint = location.getJSONObject("geo:point");
        venue.setGeolat(geopoint.getString("geo:lat"));
        venue.setGeolong(geopoint.getString("geo:long"));
        venue.setUrl(venueObj.getString("url"));
        answer.setVenue(venue);
        answer.setStartdate(event.getString("startDate"));
        answer.setDescription(event.getString("description"));
        JSONArray image = event.getJSONArray("image");
        JSONObject typeImage;
        typeImage = image.getJSONObject(0);
        answer.setImagesmall(typeImage.getString("#text"));
        typeImage = image.getJSONObject(1);
        answer.setImagemedium(typeImage.getString("#text"));
        typeImage = image.getJSONObject(2);
        answer.setImagelarge(typeImage.getString("#text"));
        typeImage = image.getJSONObject(3);
        answer.setImageextralarge(typeImage.getString("#text"));
        answer.setAttendance(event.getString("attendance"));
        answer.setReviews(event.getString("reviews"));
        answer.setTag(event.getString("tag"));
        answer.setUrl(event.getString("url"));
        answer.setWebsite(event.getString("website"));
        return answer;
    }

    public EventGetInfoAnswer info() {
        if (this.event.length() == 0) {
            EventGetInfoAnswer answer = new EventGetInfoAnswer();
            answer.setStatus(errorToCode(EMPTY_STRING));
            return answer;
        }
        String query = "method=event.getInfo&format=json" + "&event=" + this.event + "&api_key=" + SecretData.KEY;
        try {
            return parse(sendQuery(query));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
