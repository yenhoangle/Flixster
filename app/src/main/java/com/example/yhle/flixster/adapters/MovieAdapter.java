package com.example.yhle.flixster.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yhle.flixster.R;
import com.example.yhle.flixster.controllers.MainActivity;
import com.example.yhle.flixster.controllers.MovieDetailsActivity;
import com.example.yhle.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

//extends the recyclerview.adapter<> generic class that holds view holder object created from custom class
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    Context context;
    List<Movie> movies;
    View movieView;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    //inflates layout from XML and returning the holder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    //populates data into the item through holder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the movie at the passed in position
        Movie movie = movies.get(position);
        //bind the movie data into the view holder
        holder.bind(movie);
    }


    @Override
    //return total count of items in list
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        View rvView;
        int radius = 30; // corner radius, higher value = more rounded
        int margin = 10; // crop margin, set to 0 for corners with no crop

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById((R.id.tvTitle));
            tvOverview = itemView.findViewById((R.id.tvOverview));
            ivPoster = itemView.findViewById((R.id.ivPoster));
            rvView = itemView.findViewById(R.id.rvMovie);
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;
            itemView.setOnClickListener(this);
            //if phone is in landscape, imageUrl = backdrop, else imageUrl = poster
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.getBackdropPath();
            } else {
                imageUrl = movie.getPosterPath();
            }

            //need to use Glide library to render image using Glide.with.load.into
            //optional: fitcenter().transform() for rounded corners
            Glide.with(context).load(imageUrl).fitCenter().transform(new RoundedCornersTransformation(radius, margin)).into(ivPoster);
        }

        @Override
        public void onClick(View v) {
            //get position of item represented by viewholder with respect to adapter
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) { //if position exists
                //get movie
                Movie movie = movies.get(position);
                //create intent for new activity
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)context,
                                (View)rvView, "movie_player");
                //show activity
                context.startActivity(intent, options.toBundle());

            }
        }
    }
}
