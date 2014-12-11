package com.dreamteam.androidproject.newapi.query;

import com.dreamteam.androidproject.newapi.answer.EventGetInfoAnswer;
import com.dreamteam.androidproject.newapi.answer.UserGetRecommendedEventsAnswer;
import com.dreamteam.androidproject.newapi.answer.VenueAnswer;
import com.dreamteam.androidproject.newapi.connection.SecretData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.dreamteam.androidproject.newapi.template.Common;
import com.dreamteam.androidproject.newapi.template.ObjectList;

/**
 * Created by nap on 12/11/2014.
 */

public class UserGetRecommendedEvents extends Common {
    private String page;
    private String limit;
    private String sign;
    private String sessionKey;

    public UserGetRecommendedEvents(String page, String limit, String sessionKey) {
        this.page = page;
        this.limit = limit;
        this.sessionKey = sessionKey;
        if (this.limit.equals("0")) {
            this.limit = "1";
        }
        if (this.page.equals("0")) {
            this.page = "1";
        }
        this.sign = strToMD5("api_key" + SecretData.KEY + "limit" + this.limit +"methoduser.getRecommendedEvents" + "page" + this.page + "sk" + sessionKey + SecretData.SECRET);
    }

    @Override
    protected UserGetRecommendedEventsAnswer parse(String str) throws JSONException {
        JSONObject obj = new JSONObject(str);
        String status = null;
        try {
            status = getStatus(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserGetRecommendedEventsAnswer answer = new UserGetRecommendedEventsAnswer();
        answer.setStatus(errorToCode(status));
        if (!status.equals("ok")) {
            return answer;
        }
        JSONObject events = obj.getJSONObject("events");
        JSONArray list;
        try {
            list = events.getJSONArray("event");
        } catch (JSONException e) {
            list = new JSONArray();
            list.put(0, events.getJSONObject("event"));
        }
        ObjectList<EventGetInfoAnswer> event = new ObjectList<EventGetInfoAnswer>();
        for (int i = 0; i < list.length(); i++) {
            EventGetInfoAnswer eventAnswer = new EventGetInfoAnswer();
            eventAnswer.setId(list.getJSONObject(i).getString("id"));
            eventAnswer.setTitle(list.getJSONObject(i).getString("title"));
            JSONObject artistsObj = list.getJSONObject(i).getJSONObject("artists");
            JSONArray artistObj;
            try {
                artistObj = artistsObj.getJSONArray("artist");
            } catch (JSONException e) {
                artistObj = new JSONArray();
                artistObj.put(0, artistsObj.getJSONObject("artist"));
            }
            ObjectList<String> artists = new ObjectList<String>();
            for (int j = 0; j < artistObj.length(); j++) {
                artists.add(artistObj.getString(j));
            }
            eventAnswer.setArtists(artists);
            eventAnswer.setHeadliner(artistsObj.getString("headliner"));
            VenueAnswer venue = new VenueAnswer();
            JSONObject venueObj = list.getJSONObject(i).getJSONObject("venue");
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
            eventAnswer.setVenue(venue);
            eventAnswer.setStartdate(list.getJSONObject(i).getString("startDate"));
            eventAnswer.setDescription(list.getJSONObject(i).getString("description"));
            JSONArray image = list.getJSONObject(i).getJSONArray("image");
            JSONObject typeImage;
            typeImage = image.getJSONObject(0);
            eventAnswer.setImagesmall(typeImage.getString("#text"));
            typeImage = image.getJSONObject(1);
            eventAnswer.setImagemedium(typeImage.getString("#text"));
            typeImage = image.getJSONObject(2);
            eventAnswer.setImagelarge(typeImage.getString("#text"));
            typeImage = image.getJSONObject(3);
            eventAnswer.setImageextralarge(typeImage.getString("#text"));
            eventAnswer.setAttendance(list.getJSONObject(i).getString("attendance"));
            eventAnswer.setReviews(list.getJSONObject(i).getString("reviews"));
            eventAnswer.setTag(list.getJSONObject(i).getString("tag"));
            eventAnswer.setUrl(list.getJSONObject(i).getString("url"));
            eventAnswer.setWebsite(list.getJSONObject(i).getString("website"));
            event.add(eventAnswer);
        }
        answer.setEvents(event);
        return answer;
    }

    public UserGetRecommendedEventsAnswer getRecom() {
        if (this.sessionKey.length() == 0) {
            UserGetRecommendedEventsAnswer answer = new UserGetRecommendedEventsAnswer();
            answer.setStatus(errorToCode(EMPTY_STRING));
            return answer;
        }
        String query = "method=user.getRecommendedEvents&format=json" + "&api_key=" + SecretData.KEY + "&page=" + this.page + "&limit=" + this.limit + "&api_sig=" + this.sign + "&sk=" + this.sessionKey;
        try {
            return parse(sendQuery(query));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
