package com.example.adeshpa.movies;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import cz.msebera.android.httpclient.Header;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    ListView lvMovies;
    ArrayList<Movie> movies = new ArrayList<Movie>();
    MovieAdapter moviesAdapter;
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/now_playing?" +
            "api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private final String KEY = "KEY";
    private int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        // Get reference to our ListView
        lvMovies = (ListView) findViewById(R.id.lvMovies);

        // Create arrayAdapter
        moviesAdapter = new MovieAdapter(this, movies);

        // Link adapter to our ListView
        if (lvMovies != null) {
            lvMovies.setAdapter(moviesAdapter);
        }

        AsyncHttpClient client = new AsyncHttpClient();
        client.get (BASE_URL, new JsonHttpResponseHandler(){
            JSONArray movieJsonResults = null;

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    movieJsonResults = response.getJSONArray("results");
                    movies.addAll (Movie.fromJson(movieJsonResults));
                    moviesAdapter.notifyDataSetChanged();
                } catch (JSONException e) {

                }
            }
        });

        setupItemListener();


    }

    private void setupItemListener() {
        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Movie movie = movies.get(position);
                Log.d("DEBUG", String.valueOf(movie.getPopularity()));
                Intent appInfo = new Intent(MovieActivity.this, DetailActivity.class);
                appInfo.putExtra("Title", movie.getOriginalTitle());
                appInfo.putExtra("Overview", movie.getOverview());
                appInfo.putExtra("Rating", movie.getRating());
                appInfo.putExtra("Popularity", movie.getPopularity());
                appInfo.putExtra("Orientation", getResources().getConfiguration().orientation);
                startActivity(appInfo);
            }
        });
    }
}
