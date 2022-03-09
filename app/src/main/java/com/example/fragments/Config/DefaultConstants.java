package com.example.fragments.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DefaultConstants {

    public static final String API_KEY = "b600e6605b78c1e0846ecd4d57e40ae9" /*"9d3d87dfab03093a4a78ce77f8b3072b"*/;
    public static final String SESSION_ID = "919a1e6fca739322967249671e19562dfb9bb689";
    public static final String ACCOUNT_ID = "martamill3";

    public static final String BASE_IMG_URL = "https://image.tmdb.org/t/p/w500/";

    public static final Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://api.themoviedb.org/3/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

    //TOKEN
    /**eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNjAwZTY2MDViNzhjM
      WUwODQ2ZWNkNGQ1N2U0MGFlOSIsInN1YiI6IjYyMWY5M2U0OTA
      yMDEyMDA0M2I4YjAwMSIsInNjb3BlcyI6WyJhcGlf
      cmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4Wzpi5sQNuJfUKytpAjbW
      1KnRn6QQNZDuTuNze9MB0o
     */
        //Token request
    /*cbe62a471984457d40c018a17cb445986d3703c6*/


}
