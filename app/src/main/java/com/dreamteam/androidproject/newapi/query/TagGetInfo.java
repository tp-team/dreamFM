package com.dreamteam.androidproject.newapi.query;

import com.dreamteam.androidproject.newapi.answer.TagGetInfoAnswer;
import com.dreamteam.androidproject.newapi.connection.SecretData;
import org.json.JSONException;
import org.json.JSONObject;
import com.dreamteam.androidproject.newapi.template.Common;

/**
 * Created by nap on 12/4/2014.
 */

public class TagGetInfo extends Common {
    private String tag;

    public TagGetInfo(String tag) {
        this.tag = tag;
    }

    @Override
    protected TagGetInfoAnswer parse(String str) throws JSONException {
        JSONObject obj = new JSONObject(str);
        String status = null;
        try {
            status = getStatus(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TagGetInfoAnswer answer = new TagGetInfoAnswer();
        answer.setStatus(errorToCode(status));
        if (!status.equals("ok")) {
            return answer;
        }
        JSONObject tag = obj.getJSONObject("tag");
        answer.setName(tag.getString("name"));
        answer.setUrl(tag.getString("url"));
        answer.setReach(tag.getString("reach"));
        answer.setTaggins(tag.getString("taggings"));
        answer.setStreamable(tag.getString("streamable"));
        JSONObject wiki = tag.getJSONObject("wiki");
        answer.setPublished(wiki.getString("published"));
        answer.setSummary(wiki.getString("summary"));
        answer.setContent(wiki.getString("content"));
        return answer;
    }

    public TagGetInfoAnswer info() {
        if (this.tag.length() == 0) {
            TagGetInfoAnswer answer = new TagGetInfoAnswer();
            answer.setStatus(errorToCode(EMPTY_STRING));
            return answer;
        }
        String query = "method=tag.getinfo&format=json" + "&api_key=" + SecretData.KEY + "&tag=" + this.tag;
        try {
            return parse(sendQuery(query));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
