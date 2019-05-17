package com.boucaud.stephane.androidrattrapage.APIMovieDB;

import com.boucaud.stephane.androidrattrapage.Models.GenresList;
import com.boucaud.stephane.androidrattrapage.Models.Person;
import com.boucaud.stephane.androidrattrapage.Models.TVShowDetails;
import com.boucaud.stephane.androidrattrapage.Models.TVShowsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/*
Every method of an interface represents one possible API call.
It must have a HTTP annotation (GET, POST, etc.) to specify the request type and the relative URL.
The return value wraps the response in a Call object with the type of the expected result.
*/
public interface APIMovieDB {

    @GET("genre/movie/list")
    Call<GenresList> getVideoGenres(@Query("api_key") String api_key, @Query("language") String language);

    @GET("genre/tv/list")
    Call<GenresList> getTVGenres(@Query("api_key") String api_key, @Query("language") String language);

    @GET("search/tv")
    Call<TVShowsList> searchTVShows(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page,
            @Query("include_adult") boolean include_adult,
            @Query("query") String query
    );

    @GET("tv/{tv_id}")
    Call<TVShowDetails> getTVShowDetails(
            @Path("tv_id") int tv_id,
            @Query("api_key") String api_key,
            @Query("language") String language
    );

    @GET("person/{person_id}")
    Call<Person> getPersonDetails(
            @Path("person_id") int person_id,
            @Query("api_key") String api_key,
            @Query("language") String language
    );


    /*
        TO COMPLETE LATER

    @GET("search/movie")
    Call<MoviesList> searchMovies(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page,
            @Query("include_adult") boolean include_adult,
            @Query("query") String query
    );

    @GET("movie/{movie_id}")
    Call<MovieDetails> getMovieDetails(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key,
            @Query("language") String language
    );

    @GET("trending/movie/{period}")
    Call<MoviesList> getMovieTrends(
            @Path("period") String period,
            @Query("api_key") String api_key
    );
    */
}
