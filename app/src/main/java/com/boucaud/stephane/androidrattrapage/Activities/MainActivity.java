package com.boucaud.stephane.androidrattrapage.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.boucaud.stephane.androidrattrapage.R;

public class MainActivity extends AppCompatActivity {

    // General parameters
    private String language = "fr";
    private String api_key = "8e894528fa9c319948a48ce050f28657";

    // View Objects
    private EditText typed_api_key;
    private Button search_movies_button;
    private Button last_visited_button;
    private Button trend_movies_button;
    private Button search_tv_button;

    // Runtime parameters
    private String actual_api_key = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting all objects from View
        typed_api_key = findViewById(R.id.typed_api_key);
        search_movies_button = findViewById(R.id.search_movies_button);
        last_visited_button = findViewById(R.id.last_visited_button);
        trend_movies_button = findViewById(R.id.trend_movies_button);
        search_tv_button = findViewById(R.id.search_tv_button);

        // Initialising display data




        // Listeners

        // - API key typing
        typed_api_key.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                actual_api_key = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // - Search TV Shows button
        search_tv_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SearchTVActivity.class);
                intent.putExtra("default_api_key", api_key);
                intent.putExtra("typed_api_key", actual_api_key);
                startActivity(intent);
            }
        });


        /*

            TO COMPLETE LATER

        // - Search button
        search_movies_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SearchActivity.class);
                intent.putExtra("default_api_key", api_key);
                intent.putExtra("typed_api_key", actual_api_key);
                startActivity(intent);
            }
        });

        // - Last visited button
        last_visited_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), VisitedActivity.class);
                intent.putExtra("default_api_key", api_key);
                intent.putExtra("typed_api_key", actual_api_key);
                startActivity(intent);
            }
        });

        // - Trend videos button
        trend_movies_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TrendsActivity.class);
                intent.putExtra("default_api_key", api_key);
                intent.putExtra("typed_api_key", actual_api_key);
                startActivity(intent);
            }
        });
        */

    }
}
