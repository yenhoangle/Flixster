package com.example.yhle.flixster.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yhle.flixster.R;
import com.example.yhle.flixster.models.Movie;
import com.example.yhle.flixster.views.PopularViewHolder;
import com.example.yhle.flixster.views.RegularViewHolder;

import java.util.List;

public class ComplexMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Movie> movies;
    Context context;
    public ComplexMovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder;

        switch(viewType) {
            case 1:
                View popularView = inflater.inflate(R.layout.item_movie_popular, parent, false);
                viewHolder = new PopularViewHolder(popularView, context);
                break;
            default:
                View regularView = inflater.inflate(R.layout.item_movie, parent, false);
                viewHolder = new RegularViewHolder(regularView, context);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        switch(holder.getItemViewType()) {
            case 1:
                PopularViewHolder popularHolder = (PopularViewHolder) holder;
                popularHolder.bind(movie);
                break;
            default:
                RegularViewHolder regularHolder = (RegularViewHolder) holder;
                regularHolder.bind(movie);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    //1 is returned when the movie is considered popular (average ratings > 5)
    @Override
    public int getItemViewType(int position) {
        Movie movie = movies.get(position);
        double vote_average = movie.getVoteAverage();
        return (vote_average > 5 ? 1:0);
    }
}
