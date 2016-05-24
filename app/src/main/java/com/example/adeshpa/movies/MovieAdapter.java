package com.example.adeshpa.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by adeshpa on 5/21/16.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {
    Context m_context;

    // View lookup cache
    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView movieImage;
        ImageView backdropImage;
    }

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.item_movie, movies);
        m_context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);

            // Find image view
            viewHolder.movieImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            viewHolder.backdropImage = (ImageView) convertView.findViewById(R.id.ivBackdropImage);

            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvDesc);

            if (viewHolder.movieImage != null) {
                Picasso.with(m_context).load(movie.getPosterPath()).error(R.drawable.progress_animation)
                        .transform(new RoundedCornersTransformation(10, 10))
                        .placeholder(R.drawable.progress_animation).into(viewHolder.movieImage);
            }
            if (viewHolder.backdropImage != null) {
                Picasso.with(m_context)
                        .load(movie.getBackdropURL())
                        .transform(new RoundedCornersTransformation(10, 10))
                        .error(R.drawable.progress_animation)
                        .placeholder(R.drawable.progress_animation)
                        .into(viewHolder.backdropImage);
            }

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());
        viewHolder.movieImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
        viewHolder.backdropImage = (ImageView) convertView.findViewById(R.id.ivBackdropImage);
        if (viewHolder.movieImage != null) {
            Picasso.with(m_context)
                    .load(movie.getPosterPath())
                    .transform(new RoundedCornersTransformation(10, 10))
                    .error(R.drawable.progress_animation)
                    .placeholder(R.drawable.progress_animation)
                    .into(viewHolder.movieImage);
        }
        if (viewHolder.backdropImage != null) {
            Picasso.with(m_context)
                    .load(movie.getBackdropURL())
                    .transform(new RoundedCornersTransformation(10, 10))
                    .error(R.drawable.progress_animation)
                    .placeholder(R.drawable.progress_animation)
                    .into(viewHolder.backdropImage);
        }

        // Return the completed view to render on screen
        return convertView;
    }

}
