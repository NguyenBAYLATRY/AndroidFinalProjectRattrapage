package com.boucaud.stephane.androidrattrapage.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.boucaud.stephane.androidrattrapage.Controllers.Controller;
import com.boucaud.stephane.androidrattrapage.Models.Person;
import com.boucaud.stephane.androidrattrapage.R;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatorDetailsActivity extends AppCompatActivity {

    // General parameters
    private String language = "fr";
    private String api_key = "8e894528fa9c319948a48ce050f28657";

    private Controller controller;

    // View Objects
    private TextView textview_name;

    private ImageView thumbnail;

    // Runtime parameters
    private int person_id;


    /**
     * Function to load data from API to describe movie details
     */
    private void load_person_data(){
        controller.queryPersonDetails(person_id, api_key, language ,new Callback<Person>() {
            public void onResponse(Call<Person> call, Response<Person> response) {
                if (response.isSuccessful()) {
                    //IF SUCCESSFULL API CALL
                    Person person = response.body();

                    textview_name.setText(person.getName());

                    Glide.with(getApplicationContext()).load(person.getImageFullPath()).into(thumbnail);

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
        setContentView(R.layout.activity_creator_details);

        // Get Intent data

        Intent intent = getIntent();
        if (intent != null) {
            person_id = intent.getIntExtra("creator_id", 0);
            api_key = intent.getStringExtra("api_key");
        }

        // Load View Objects
        textview_name = findViewById(R.id.name);

        thumbnail = findViewById(R.id.thumbnail);

        // Load DATA
        controller = new Controller(api_key, language);
        load_person_data();

    }
}
