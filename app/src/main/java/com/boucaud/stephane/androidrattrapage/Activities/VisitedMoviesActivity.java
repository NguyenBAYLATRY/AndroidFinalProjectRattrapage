package com.boucaud.stephane.androidrattrapage.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.boucaud.stephane.androidrattrapage.Models.Movie;
import com.boucaud.stephane.androidrattrapage.R;
import com.boucaud.stephane.androidrattrapage.RecyclerViewsClasses.VisitedMoviesAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class VisitedMoviesActivity extends AppCompatActivity {

    // General parameters
    private String language = "fr";
    private String api_key;

    // View Objects
    private RecyclerView moviesListRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    // Runtime parameters
    private String intent_typed_api_key;
    private String intent_default_api_key;
    private List<Movie> actual_movies;

    // Shared preferences
    SharedPreferences savedMovies;
    String SHARED_PREFS_FILE = "SAVED_MOVIES";





    private String LoadPreferences(String key, String default_value){
        return savedMovies.getString(key, default_value) ;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visited_movies);

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
        moviesListRecyclerView = (RecyclerView) findViewById(R.id.moviesList);

        // Getting data from SharedPreferences

        // - Prepare Shared Preferences to save viewed movies
        savedMovies =  getSharedPreferences(SHARED_PREFS_FILE, getApplicationContext().MODE_PRIVATE);

        Movie[] current_saved_movies;
        String current_saved_movies_json;

        Gson gson = new Gson();

        // Decode already saved movies
        current_saved_movies_json = LoadPreferences("current_saved_movies", "[]");
        current_saved_movies = gson.fromJson(current_saved_movies_json, Movie[].class);



        // Transform into list of Movies
        ArrayList<Movie> actual_movies = new ArrayList<Movie>(Arrays.asList(current_saved_movies));

        // Initialising display data

        // - Recycler View for display result
        // use a grid layout manager
        layoutManager = new GridLayoutManager(this, 3);
        moviesListRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new VisitedMoviesAdapter(actual_movies, api_key);
        moviesListRecyclerView.setAdapter(mAdapter);
    }
}
