package com.example.adeshpa.movies;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by adeshpa on 5/23/16.
 */
public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        String title = this.getIntent().getStringExtra("Title");
        String overview = this.getIntent().getStringExtra("Overview");
        float rating = this.getIntent().getFloatExtra("Rating", 0);
        float popularity = this.getIntent().getFloatExtra("Popularity", 0);

        TextView tvTitle = (TextView) findViewById(R.id.tvOriginalTitle);
        tvTitle.setText (title);

        TextView tvOverview = (TextView) findViewById(R.id.tvOverview);
        tvOverview.setText (overview);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.rtbProductRating);
        ratingBar.setRating(rating);

        TextView tvPopularity = (TextView) findViewById(R.id.tvPopularity);
        tvPopularity.setText (String.valueOf(popularity));
    }

    public void goBack(View v) {
        switchView();
    }

    private void switchView() {
        Intent appInfo = new Intent(DetailActivity.this, MovieActivity.class);
        startActivity(appInfo);
    }
}
