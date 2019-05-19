package com.boucaud.stephane.androidrattrapage.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.boucaud.stephane.androidrattrapage.Controllers.Controller;
import com.boucaud.stephane.androidrattrapage.Models.Movie;
import com.boucaud.stephane.androidrattrapage.Models.MovieDetails;
import com.boucaud.stephane.androidrattrapage.R;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    // General parameters
    private String language = "fr";
    private String api_key = "8e894528fa9c319948a48ce050f28657";

    private Controller controller;

    // View Objects
    private TextView textview_budget;
    private TextView textview_genres;
    private TextView textview_overview;
    private TextView textview_popularity;
    private TextView textview_production_companies;
    private TextView textview_production_countries;
    private TextView textview_release_date;
    private TextView textview_revenue;
    private TextView textview_spoken_languages;
    private TextView textview_status;
    private TextView textview_title;
    private TextView textview_vote_average;
    private TextView textview_vote_count;

    private ImageView thumbnail;

    // Runtime parameters
    private int movie_id;

    // Shared preferences
    SharedPreferences savedMovies;
    String SHARED_PREFS_FILE = "SAVED_MOVIES";


    /**
     * Function to save the movies into Shared preferences
     * @param key
     * @param value
     */
    private void SavePreferences(String key, String value){
        SharedPreferences.Editor editor = savedMovies.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private String LoadPreferences(String key, String default_value){
        return savedMovies.getString(key, default_value) ;
    }

    /**
     * Function to load data from API to describe movie details
     */
    private void load_movie_data(){
        controller.queryMovieDetails(movie_id, api_key, language ,new Callback<MovieDetails>() {
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if (response.isSuccessful()) {
                    //IF SUCCESSFULL API CALL
                    MovieDetails movieDetails = response.body();

                    textview_budget.setText("Budget:\n" + Integer.toString(movieDetails.getBudget()));
                    textview_genres.setText("Genres:\n" + movieDetails.getGenresStringList());
                    textview_title.setText(movieDetails.getTitle());
                    textview_overview.setText("Overview:\n" + movieDetails.getOverview());
                    textview_popularity.setText("Popularity:\n" + Float.toString(movieDetails.getPopularity()));
                    textview_production_companies.setText("Production companies:\n" + movieDetails.getProductionCompaniesStringList());
                    textview_production_countries.setText("Production Countries:\n" + movieDetails.getProductionCountriesStringList());
                    textview_release_date.setText("Release Date:\n" + movieDetails.getRelease_date());
                    textview_revenue.setText("Revenue:\n" + Integer.toString(movieDetails.getRevenue()));
                    textview_spoken_languages.setText("Spoken languages:\n" + movieDetails.getSpokenLanguagesStringList());
                    textview_status.setText("Status:\n" + movieDetails.getStatus());
                    textview_vote_average.setText("Votes average:\n" + Float.toString(movieDetails.getVote_average()));
                    textview_vote_count.setText("Votes count:\n" + Integer.toString(movieDetails.getVote_count()));
                    Glide.with(getApplicationContext()).load(movieDetails.getPosterFullPath()).into(thumbnail);

                    // IF DATA LOADED SUCESSFULLY
                    Movie[] current_saved_movies;
                    Movie[] tmp_current_saved_movies;
                    String current_saved_movies_json;
                    Movie current_movie;

                    Gson gson = new Gson();
                    current_movie = new Movie(movieDetails);

                    // Decode already saved movies
                    current_saved_movies_json = LoadPreferences("current_saved_movies", "[]");
                    current_saved_movies = gson.fromJson(current_saved_movies_json, Movie[].class);

                    // Copy old data and free space for new one
                    if (current_saved_movies.length <= 0) {
                        current_saved_movies = new Movie[1];
                    }
                    else {
                        tmp_current_saved_movies = new Movie[current_saved_movies.length + 1];
                        System.arraycopy(current_saved_movies,0,tmp_current_saved_movies,0,current_saved_movies.length);
                        current_saved_movies = tmp_current_saved_movies;
                    }

                    // Add current movie and encode in JSON
                    current_saved_movies[current_saved_movies.length-1] = current_movie;
                    String json = gson.toJson(current_saved_movies);

                    // Save new visited movie to Shared Preferences
                    SavePreferences("current_saved_movies", json);

                } else {
                    System.out.println(response.errorBody());
                }
            }

            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Get Intent data

        Intent intent = getIntent();
        if (intent != null) {
            movie_id = intent.getIntExtra("movie_id", 0);
            api_key = intent.getStringExtra("api_key");
        }

        // Prepare Shared Preferences to save viewed movies
        savedMovies =  getSharedPreferences(SHARED_PREFS_FILE, getApplicationContext().MODE_PRIVATE);

        // Load View Objects

        textview_budget = findViewById(R.id.budget);
        textview_genres = findViewById(R.id.genres);
        textview_overview = findViewById(R.id.overview);
        textview_popularity = findViewById(R.id.popularity);
        textview_production_companies = findViewById(R.id.production_companies);
        textview_production_countries = findViewById(R.id.production_countries);
        textview_release_date = findViewById(R.id.release_date);
        textview_revenue = findViewById(R.id.revenue);
        textview_spoken_languages = findViewById(R.id.spoken_languages);
        textview_status = findViewById(R.id.status);
        textview_title = findViewById(R.id.title);
        textview_vote_average = findViewById(R.id.vote_average);
        textview_vote_count = findViewById(R.id.vote_count);

        thumbnail = findViewById(R.id.thumbnail);

        // Load DATA

        controller = new Controller(api_key, language);

        load_movie_data();

    }
}
