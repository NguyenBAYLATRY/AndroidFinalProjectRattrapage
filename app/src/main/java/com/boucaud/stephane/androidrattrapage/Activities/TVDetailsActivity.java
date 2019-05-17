package com.boucaud.stephane.androidrattrapage.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.boucaud.stephane.androidrattrapage.Controllers.Controller;
import com.boucaud.stephane.androidrattrapage.Models.TVShow;
import com.boucaud.stephane.androidrattrapage.Models.TVShowDetails;
import com.boucaud.stephane.androidrattrapage.R;
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

    private ImageView thumbnail;

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

        thumbnail = findViewById(R.id.thumbnail);

        // Load DATA

        controller = new Controller(api_key, language);

        load_tv_data();

    }
}
