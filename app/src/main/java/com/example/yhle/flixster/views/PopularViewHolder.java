package com.example.yhle.flixster.views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yhle.flixster.R;
import com.example.yhle.flixster.models.Movie;

public class PopularViewHolder extends RecyclerView.ViewHolder {
    Context context;
    ImageView ivPoster;
    public PopularViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        ivPoster = itemView.findViewById(R.id.ivPoster);
        this.context = context;
    }

    public void bind(Movie movie) {
        String imageUrl = movie.getBackdropPath();
        Glide.with(context).load(imageUrl).into(ivPoster);
    }
}
