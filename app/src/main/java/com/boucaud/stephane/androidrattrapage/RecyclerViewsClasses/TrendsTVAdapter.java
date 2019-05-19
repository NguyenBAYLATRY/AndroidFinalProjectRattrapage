package com.boucaud.stephane.androidrattrapage.RecyclerViewsClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boucaud.stephane.androidrattrapage.Models.Movie;
import com.boucaud.stephane.androidrattrapage.Models.TVShow;
import com.boucaud.stephane.androidrattrapage.R;

import java.util.List;

public class TrendsTVAdapter extends RecyclerView.Adapter<TrendsTVViewHolder> {
    private List<TVShow> tv_shows;
    private String api_key;

    // Provide a suitable constructor (depends on the kind of dataset)
    public TrendsTVAdapter(List<TVShow> tv_shows, String api_key) {
        this.api_key = api_key;
        this.tv_shows = tv_shows;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public TrendsTVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_trends_tvs, parent, false);

        return new TrendsTVViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TrendsTVViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bind(tv_shows.get(position), this.api_key);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tv_shows.size();
    }
}
