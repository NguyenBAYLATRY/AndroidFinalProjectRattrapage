package com.boucaud.stephane.androidrattrapage.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.boucaud.stephane.androidrattrapage.Models.Movie;
import com.boucaud.stephane.androidrattrapage.Models.TVShow;
import com.boucaud.stephane.androidrattrapage.R;
import com.boucaud.stephane.androidrattrapage.RecyclerViewsClasses.VisitedMoviesAdapter;
import com.boucaud.stephane.androidrattrapage.RecyclerViewsClasses.VisitedTVsAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisitedTVActivity extends AppCompatActivity {

    // General parameters
    private String language = "fr";
    private String api_key;

    // View Objects
    private RecyclerView ListRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    // Runtime parameters
    private String intent_typed_api_key;
    private String intent_default_api_key;
    private List<TVShow> actual_tvs;

    // Shared preferences
    SharedPreferences savedTVs;
    String SHARED_PREFS_FILE = "SAVED_TV";





    private String LoadPreferences(String key, String default_value){
        return savedTVs.getString(key, default_value) ;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visited_tv);

        // Get Intent data
        intent_default_api_key = getIntent().getStringExtra("default_api_key");
        intent_typed_api_key = getIntent().getStringExtra("typed_api_key");
        if (intent_typed_api_key != null && ! intent_typed_api_key.equals(new String(""))){
            api_key = intent_typed_api_key;
        }
        else{
            api_key = intent_default_api_key;
        }

        // Getting all objects from View
        ListRecyclerView = (RecyclerView) findViewById(R.id.tvsList);

        // Getting data from SharedPreferences

        // - Prepare Shared Preferences to save viewed movies
        savedTVs =  getSharedPreferences(SHARED_PREFS_FILE, getApplicationContext().MODE_PRIVATE);

        TVShow[] current_saved_tvs;
        String current_saved_tvs_json;

        Gson gson = new Gson();

        // Decode already saved tvs
        current_saved_tvs_json = LoadPreferences("current_saved_tvs", "[]");
        current_saved_tvs = gson.fromJson(current_saved_tvs_json, TVShow[].class);



        // Transform into list of Movies
        ArrayList<TVShow> actual_tvs = new ArrayList<TVShow>(Arrays.asList(current_saved_tvs));

        // Initialising display data

        // - Recycler View for display result
        // use a grid layout manager
        layoutManager = new GridLayoutManager(this, 3);
        ListRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new VisitedTVsAdapter(actual_tvs, api_key);
        ListRecyclerView.setAdapter(mAdapter);
    }
}