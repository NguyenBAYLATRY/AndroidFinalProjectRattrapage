package com.boucaud.stephane.androidrattrapage.RecyclerViewsClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boucaud.stephane.androidrattrapage.Models.Creator;
import com.boucaud.stephane.androidrattrapage.R;

import java.util.List;

public class HorizontalCreatorsAdapter extends RecyclerView.Adapter<HorizontalCreatorsViewHolder> {
    private List<Creator> creators;
    private String api_key;

    // Provide a suitable constructor (depends on the kind of dataset)
    public HorizontalCreatorsAdapter(List<Creator> creators, String api_key) {
        this.api_key = api_key;
        this.creators = creators;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public HorizontalCreatorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_horizontal_creators, parent, false);

        return new HorizontalCreatorsViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(HorizontalCreatorsViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bind(creators.get(position), this.api_key);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return creators.size();
    }
}
