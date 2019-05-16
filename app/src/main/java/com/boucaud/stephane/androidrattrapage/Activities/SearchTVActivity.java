package com.boucaud.stephane.androidrattrapage.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.boucaud.stephane.androidrattrapage.Controllers.Controller;
import com.boucaud.stephane.androidrattrapage.Models.GenresList;
import com.boucaud.stephane.androidrattrapage.Models.TVShow;
import com.boucaud.stephane.androidrattrapage.Models.TVShowsList;
import com.boucaud.stephane.androidrattrapage.R;
import com.boucaud.stephane.androidrattrapage.RecyclerViewsClasses.SearchTVAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTVActivity extends AppCompatActivity {

    // General parameters
    private String language = "fr";
    private String api_key;

    //For API
    private Controller controller;

    // View Objects
    private Spinner spinner_genres;
    private EditText SearchQuery;
    private RecyclerView listRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    // Runtime parameters
    private String intent_typed_api_key;
    private String intent_default_api_key;

    private GenresList genresList;
    private String Selected_genre;
    private int Selected_genre_id;
    private int actual_page = 1;
    private List<TVShow> actual_tv_shows = new ArrayList<TVShow>();


    /***
     * Fill with values a Spinner (Select list)
     * @param values List of Strings
     * @param spinner Spinner reference
     */
    private void fill_Spinner_Values(List<String> values, Spinner spinner, String value_default){
        values.add(0, "None");
        values.add(0, value_default);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, values);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    /**
     * Function to make a search query on movies
     * @param page
     * @param include_adult
     * @param query
     */
    private void searchTVShows (int page, boolean include_adult, String query) {
        controller.querySearchTVShows(page, include_adult, query, new Callback<TVShowsList>() {
            public void onResponse(Call<TVShowsList> call, Response<TVShowsList> response) {
                if (response.isSuccessful()) {
                    TVShowsList TVShowsList = response.body();
                    if (Selected_genre != "None" && Selected_genre != "Select Genre"){
                        //textview_test.setText(moviesList.getMovies(Selected_genre_id).toString());
                        actual_tv_shows = TVShowsList.getTVShows(Selected_genre_id);
                    }
                    else{
                        //textview_test.setText(moviesList.getMovies().toString());
                        actual_tv_shows = TVShowsList.getTVShows();
                    }

                    // specify an adapter (see also next example)
                    mAdapter = new SearchTVAdapter(actual_tv_shows, api_key);
                    listRecyclerView.setAdapter(mAdapter);

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
        setContentView(R.layout.activity_search_tv);

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
        spinner_genres = (Spinner) findViewById(R.id.spinner_genres);
        SearchQuery = findViewById(R.id.SearchQuery);
        listRecyclerView = (RecyclerView) findViewById(R.id.tv_shows_list);

        // Initialising display data

        controller = new Controller(api_key, language);

        // - Genres initiation
        controller.queryGetGenres(new Callback<GenresList>(){
            public void onResponse(Call<GenresList> call, Response<GenresList> response) {
                if(response.isSuccessful()) {
                    genresList = response.body();
                    fill_Spinner_Values(genresList.getStringList(), spinner_genres, "Select Genre");
                } else {
                    System.out.println(response.errorBody());
                }
            }
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
            }
        });

        // - Recycler View for search result
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        listRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        listRecyclerView.setLayoutManager(layoutManager);


        // Listeners

        // - On Genres selection
        spinner_genres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Selected_genre = (String) parent.getItemAtPosition(position);
                Selected_genre_id = genresList.searchGenreID(Selected_genre);
                searchTVShows (actual_page, false, SearchQuery.getText().toString());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // - Movies Search bar typing
        SearchQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                actual_page = 1;
                if (s.length() >= 0) {
                    searchTVShows (actual_page, false, s.toString());
                } else {
                    searchTVShows (actual_page, false, "");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }
}
