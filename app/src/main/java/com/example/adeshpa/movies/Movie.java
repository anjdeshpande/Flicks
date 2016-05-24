package com.example.adeshpa.movies;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by adeshpa on 5/21/16.
 */
public class Movie implements Serializable {
    private String originalTitle;
    private String overview;
    private String posterPath;
    private String backdropURL;
    private float rating;
    private float popularity;

    private static final String TAG = Movie.class.getName();
    static ArrayList<Movie> moviesList = new ArrayList<Movie>();

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropURL() {
        return String.format("https://image.tmdb.org/t/p/w300/%s", backdropURL);
    }

    public float getRating() {
        return rating;
    }

    public float getPopularity() {
        return popularity;
    }

    public Movie(String originalTitle, String posterPath, String backdropURL, String overview, float rating) {
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.backdropURL = backdropURL;
        this.overview = overview;
        this.rating = rating;
    }

    // Constructor to convert JSON object into a Java class instance
    public Movie(JSONObject object) throws JSONException{
        this.originalTitle = object.getString("original_title");
        this.overview = object.getString("overview");
        this.posterPath = object.getString("poster_path");

        this.backdropURL = object.getString("backdrop_path");
        this.rating = (float) object.getDouble("vote_average");
        this.popularity = (float) object.getDouble("popularity");
    }

    // Factory method to convert an array of JSON objects into a list of objects
    public static ArrayList<Movie> fromJson(JSONArray jsonArray) throws JSONException {
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject o = jsonArray.getJSONObject(i);
            movieList.add(new Movie(o));
        }
        return movieList;
    }
}
