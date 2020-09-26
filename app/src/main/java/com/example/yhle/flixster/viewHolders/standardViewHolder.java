package com.example.yhle.flixster.viewHolders;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yhle.flixster.R;
import com.example.yhle.flixster.models.Movie;

public class standardViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle;
    TextView tvOverview;
    ImageView ivPoster;
    Context context;
    public standardViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvOverview = itemView.findViewById(R.id.tvOverview);
        ivPoster = itemView.findViewById(R.id.ivPoster);
        this.context = context;
    }

    public void bind(Movie movie) {
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        String imageUrl;
        //if phone is in landscape, imageUrl = backdrop, else imageUrl = poster
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageUrl = movie.getBackdropPath();
        } else {
            imageUrl = movie.getPosterPath();
        }

        //need to use Glide library to render image
        //Glide.with.load.into
        Glide.with(context).load(imageUrl).into(ivPoster);
    }
}
