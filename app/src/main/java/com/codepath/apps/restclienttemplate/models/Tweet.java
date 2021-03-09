package com.codepath.apps.restclienttemplate.models;

import com.codepath.apps.restclienttemplate.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {

    public String body;
    public String createdAt;
    public long id;
    public User user; // Don't forget to @Parcel User model as well as Tweet contains User model

    // Empty constructor is used by the Parceler Library
    public Tweet() {
    }

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user")); // User.fromJson() takes in a JSON obj and returns as User model

        return tweet;
    }

    // takes in json array to get back list of tweets
    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }

        return tweets;
    }

    public String getFormattedTimestamp() {
        String timestamp = TimeFormatter.getTimeDifference(createdAt);

        if (timestamp.contains("Just now")) {
            return timestamp;
        }

        return  timestamp + " ago";
    }
}
