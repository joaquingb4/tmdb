package com.example.fragments.Config;

import com.example.fragments.Model.Film.FavFilmRequest;
import com.example.fragments.Model.Film.getFavFilmsModel;
import com.example.fragments.Model.Film.searchFilmModel;
import com.example.fragments.Model.List.ListModel;
import com.example.fragments.Model.List.Lista;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCall {
    //Fijate de nunca poner una barra al inicio de la url
    @GET("search/movie?")
    Call<searchFilmModel> getData(@Query("api_key") String api_key, @Query("query") String query);

    @GET("account/{account_id}/favorite/movies")
    Call<getFavFilmsModel> getFavoritesMovies (
            @Path("account_id") int account_id,
            @Query("api_key") String api_key,
            @Query("session_id") String session_id);

    @POST("account/{account_id}/favorite")
    Call<FavFilmRequest> uptadeFavoriteStatus(
            @Path("account_id") int account_id
            , @Header("Content-Type") String content_type
            , @Query("api_key") String api_key
            , @Query("session_id") String session_id
            , @Body FavFilmRequest favFilmRequest );
    @POST("list")
    Call<ListModel> crearLista(
            @Header("Content-Type") String content_type,
            @Query("api_key") String api_key,
            @Query("session_id") String session_id,
            @Body Lista lista
    );
    @GET("account/{account_id}/lists")
        Call<ListModel> getListas(
                @Query("api_key") String api_key,
                @Query("session_id") String session_id
    );
}
//https://api.themoviedb.org/3/account/alumno21/favorite?api_key=b600e6605b78c1e0846ecd4d57e40ae9&session_id=bf49e752495a327e2f4ad9b555edadacc307da6d