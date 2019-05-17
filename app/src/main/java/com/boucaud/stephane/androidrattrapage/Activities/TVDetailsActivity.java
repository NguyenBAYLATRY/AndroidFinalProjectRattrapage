package com.boucaud.stephane.androidrattrapage.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.boucaud.stephane.androidrattrapage.Controllers.Controller;
import com.boucaud.stephane.androidrattrapage.Models.TVShow;
import com.boucaud.stephane.androidrattrapage.Models.TVShowDetails;
import com.boucaud.stephane.androidrattrapage.R;
import com.boucaud.stephane.androidrattrapage.RecyclerViewsClasses.HorizontalCreatorsAdapter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVDetailsActivity  extends AppCompatActivity {

    // General parameters
    private String language = "fr";
    private String api_key = "8e894528fa9c319948a48ce050f28657";

    private Controller controller;

    // View Objects
    private TextView textview_name;
    private TextView textview_status;
    private TextView textview_first_air_date;
    private TextView textview_last_air_date;
    private TextView textview_vote_average;
    private TextView textview_vote_count;
    private TextView textview_popularity;
    private TextView textview_overview;
    private TextView textview_genres;
    private TextView textview_production_companies;

    private ImageView thumbnail;

    // Recycler View
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView ListRecyclerView;
    private RecyclerView.Adapter mAdapter;

    // Runtime parameters
    private int tv_id;

    // Shared preferences
    SharedPreferences savedTV;
    String SHARED_PREFS_FILE = "SAVED_TV";


    /**
     * Function to save the movies into Shared preferences
     * @param key
     * @param value
     */
    private void SavePreferences(String key, String value){
        SharedPreferences.Editor editor = savedTV.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private String LoadPreferences(String key, String default_value){
        return savedTV.getString(key, default_value) ;
    }

    /**
     * Function to load data from API to describe movie details
     */
    private void load_tv_data(){
        controller.queryTVShowDetails(tv_id, api_key, language ,new Callback<TVShowDetails>() {
            public void onResponse(Call<TVShowDetails> call, Response<TVShowDetails> response) {
                if (response.isSuccessful()) {
                    //IF SUCCESSFULL API CALL
                    TVShowDetails TVDetails = response.body();

                    textview_name.setText(TVDetails.getName());
                    textview_status.setText("Status:\n" + TVDetails.getStatus());
                    textview_first_air_date.setText("First Air date:\n" + TVDetails.getFirst_air_date());
                    textview_last_air_date.setText("Last Air date:\n" + TVDetails.getLast_air_date());
                    textview_vote_average.setText("Votes average:\n" + Float.toString(TVDetails.getVote_average()));
                    textview_vote_count.setText("Votes count:\n" + Integer.toString(TVDetails.getVote_count()));
                    textview_popularity.setText("Popularity:\n" + Float.toString(TVDetails.getVote_count()));
                    textview_overview.setText("Overview:\n" + TVDetails.getOverview());
                    textview_genres.setText("Genres:\n" + TVDetails.getGenresStringList());
                    textview_production_companies.setText("Prod companies:\n" + TVDetails.getProductionCompaniesStringList());

                    mAdapter = new HorizontalCreatorsAdapter(TVDetails.getCreated_by(), api_key);
                    ListRecyclerView.setAdapter(mAdapter);

                    Glide.with(getApplicationContext()).load(TVDetails.getPosterFullPath()).into(thumbnail);

                    // IF DATA LOADED SUCESSFULLY
                    TVShow[] current_saved_tvs;
                    TVShow[] tmp_current_saved_tvs;
                    String current_saved_tvs_json;
                    TVShow current_tv;

                    Gson gson = new Gson();
                    current_tv = new TVShow(TVDetails);

                    // Decode already saved movies
                    current_saved_tvs_json = LoadPreferences("current_saved_tvs", "[]");
                    current_saved_tvs = gson.fromJson(current_saved_tvs_json, TVShow[].class);

                    // Copy old data and free space for new one
                    if (current_saved_tvs.length <= 0) {
                        current_saved_tvs = new TVShow[1];
                    }
                    else {
                        tmp_current_saved_tvs = new TVShow[current_saved_tvs.length + 1];
                        System.arraycopy(current_saved_tvs,0,tmp_current_saved_tvs,0,current_saved_tvs.length);
                        current_saved_tvs = tmp_current_saved_tvs;
                    }

                    // Add current movie and encode in JSON
                    current_saved_tvs[current_saved_tvs.length-1] = current_tv;
                    String json = gson.toJson(current_saved_tvs);

                    // Save new visited movie to Shared Preferences
                    SavePreferences("current_saved_tvs", json);

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
        setContentView(R.layout.activity_tvdetails);

        // Get Intent data

        Intent intent = getIntent();
        if (intent != null) {
            tv_id = intent.getIntExtra("tv_id", 0);
            api_key = intent.getStringExtra("api_key");
        }

        // Prepare Shared Preferences to save viewed movies
        savedTV =  getSharedPreferences(SHARED_PREFS_FILE, getApplicationContext().MODE_PRIVATE);

        // Load View Objects

        textview_name = findViewById(R.id.name);
        textview_status = findViewById(R.id.status);
        textview_first_air_date = findViewById(R.id.first_air_date);
        textview_last_air_date = findViewById(R.id.last_air_date);
        textview_vote_average = findViewById(R.id.vote_average);
        textview_vote_count = findViewById(R.id.vote_count);
        textview_popularity = findViewById(R.id.popularity);
        textview_overview = findViewById(R.id.overview);
        textview_genres = findViewById(R.id.genres);
        textview_production_companies = findViewById(R.id.production_companies);

        thumbnail = findViewById(R.id.thumbnail);

        ListRecyclerView = (RecyclerView) findViewById(R.id.creatorsList);

        // Load DATA

        controller = new Controller(api_key, language);

        //Prepare recycler view for creators
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        ListRecyclerView.setLayoutManager(layoutManager);

        load_tv_data();

    }
}
