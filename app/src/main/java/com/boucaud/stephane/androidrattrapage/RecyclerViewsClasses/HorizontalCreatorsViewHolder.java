package com.boucaud.stephane.androidrattrapage.RecyclerViewsClasses;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.boucaud.stephane.androidrattrapage.Activities.CreatorDetailsActivity;
import com.boucaud.stephane.androidrattrapage.Models.Creator;
import com.boucaud.stephane.androidrattrapage.R;
import com.bumptech.glide.Glide;

public class HorizontalCreatorsViewHolder extends RecyclerView.ViewHolder {
    private TextView name;

    public HorizontalCreatorsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.name);
    }

    public void bind(final Creator creator, final String api_key) {
        name.setText(creator.getName());

        // Create Listener for each ViewHolder, then if we click, we can see video details
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, CreatorDetailsActivity.class);
                intent.putExtra("creator_id", creator.getId());
                intent.putExtra("api_key", api_key);
                context.startActivity(intent);
            }
        });
    }
}