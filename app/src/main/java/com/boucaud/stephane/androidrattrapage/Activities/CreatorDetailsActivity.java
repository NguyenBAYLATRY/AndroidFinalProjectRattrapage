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
    private TextView textview_birthdate;
    private TextView textview_place_of_birth;
    private TextView textview_known_for_department;
    private TextView textview_also_known_as;
    private TextView textview_gender;
    private TextView textview_biography;
    private TextView textview_popularity;

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
                    textview_birthdate.setText("Birthday:\n"+person.getBirthday());
                    textview_place_of_birth.setText("Place of birth:\n"+person.getPlace_of_birth());
                    textview_known_for_department.setText("Known for department:\n"+person.getKnown_for_department());
                    textview_also_known_as.setText("Also known as:\n"+person.getAlso_known_as_String());
                    textview_gender.setText("Gender:\n"+Integer.toString(person.getGender()));
                    textview_biography.setText("Biography:\n"+person.getBiography());
                    textview_popularity.setText("Popularity:\n"+Float.toString(person.getPopularity()));

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
        textview_birthdate = findViewById(R.id.birthdate);
        textview_place_of_birth = findViewById(R.id.place_of_birth);
        textview_known_for_department = findViewById(R.id.known_for_department);
        textview_also_known_as = findViewById(R.id.also_known_as);
        textview_gender = findViewById(R.id.gender);
        textview_biography = findViewById(R.id.biography);
        textview_popularity = findViewById(R.id.popularity);

        thumbnail = findViewById(R.id.thumbnail);

        // Load DATA
        controller = new Controller(api_key, language);
        load_person_data();

    }
}
