package com.example.fragments;

import static com.example.fragments.Config.DefaultConstants.ACCOUNT_ID;
import static com.example.fragments.Config.DefaultConstants.API_KEY;
import static com.example.fragments.Config.DefaultConstants.BASE_IMG_URL;
import static com.example.fragments.Config.DefaultConstants.SESSION_ID;
import static com.example.fragments.Config.DefaultConstants.retrofit;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fragments.Config.ApiCall;
import com.example.fragments.Config.GlideApp;
import com.example.fragments.Model.Film.FavFilmRequest;
import com.example.fragments.Model.Film.Film;
import com.example.fragments.Model.Film.getFavFilmsModel;
import com.example.fragments.Model.List.Lista;
import com.example.fragments.Recyclers.AddMovieListsRecyclerViewAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailFragment extends Fragment {


    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = getArguments();
        Film film = (Film) bundle.getSerializable("Film");

        TextView txtDetailTitle = view.findViewById(R.id.txtDetailTitle);
        TextView txtDetailDesc = view.findViewById(R.id.txtDetailDesc);
        ImageView imgDetail = view.findViewById(R.id.imgDetail);
        ImageButton btnFav = view.findViewById(R.id.btnFav);
        ImageButton btnAddtoList = view.findViewById(R.id.btnAddtoList);

        txtDetailTitle.setText(film.getOriginal_title());
        txtDetailDesc.setText(film.getOverview());

        GlideApp.with(getContext())
                .load(BASE_IMG_URL + film.getPoster_path())
                .centerCrop()
                .into(imgDetail);
        //Aqu?? comprobamos si la pel??cula est?? ya en favoritos
        //____________
        //Llamamos a la api
        ApiCall apiCall = retrofit.create(ApiCall.class);
        Call<getFavFilmsModel> call = apiCall.getFavoritesMovies(ACCOUNT_ID
        , API_KEY, SESSION_ID);
        call.enqueue(new Callback<getFavFilmsModel>() {
            @Override
            public void onResponse(Call<getFavFilmsModel> call, Response<getFavFilmsModel> response) {
                if (response.code()!=200){
                    Log.i("testApi", "checkConnection  " + response.code());

                }else{
                    Log.i("testApi", "Change Succesfull");
                    ArrayList<Film> peliculas = response.body().getResults();
                    for (Film x: peliculas) {
                        if (x.getId()==film.getId()){
                            btnFav.setImageResource(R.drawable.ic_fav_on);
                            Log.i("testApi","id pel??cula favorita "+ x + "el id de la pel??cula selecionada "+ film.getId());
                        }
                    }
                }
                return;
            }

            @Override
            public void onFailure(Call<getFavFilmsModel> call, Throwable t) {
                Log.i("testApi", "getFavFilms ha fallado");

            }
        });
        //____________
        //FAVORITOS AQU??
        btnFav.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                btnFav.setImageResource(R.drawable.ic_fav_on);
                //_____
                    FavFilmRequest favFilmRequest = new FavFilmRequest("movie", film.getId(), true);
                    ApiCall apiCall = retrofit.create(ApiCall.class);
                    Call<FavFilmRequest> call = apiCall.uptadeFavoriteStatus(
                            ACCOUNT_ID
                            ,"application/json;charset=utf-8"
                            ,API_KEY,SESSION_ID
                            , favFilmRequest);

                    call.enqueue(new Callback<FavFilmRequest>(){
                        @Override
                        public void onResponse(Call<FavFilmRequest> call, Response<FavFilmRequest> response) {
                            if(response.code()!=201){
                                Log.i("testApi", response
                                        +"checkConnection "+response.code()+" : "+film.getId());
                                return;
                            }else {
                                Log.i("testApi", "Change Succesfull");
                            }
                        }

                        @Override
                        public void onFailure(Call<FavFilmRequest> call, Throwable t) {
                            Log.i("testApi", "FALLO");

                        }
                    });
                }
                //-------------

        });

        btnAddtoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;
    }

    public void showDialog(){
        View alertCustomdialog = getLayoutInflater().inflate( R.layout.form_movie_to_list, null);

        //initialize alert builder.
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

        //set our custom alert dialog to tha alertdialog builder
        alert.setView(alertCustomdialog);

        final AlertDialog dialog = alert.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();


        ArrayList<Lista> arrayList = new ArrayList<Lista>();
        /*arrayList.add(new Lista("Comedia", 8));
        arrayList.add(new Lista("Ci??ncia", 8));
        arrayList.add(new Lista("Terror", 8));
        arrayList.add(new Lista("Comedia", 8));
        arrayList.add(new Lista("Ci??ncia", 8));
        arrayList.add(new Lista("Terror", 8));
        arrayList.add(new Lista("Comedia", 8));
        arrayList.add(new Lista("Ci??ncia", 8));
        arrayList.add(new Lista("Terror", 8));


         */

        RecyclerView recyclerView = alertCustomdialog.findViewById(R.id.recyclerList);
        AddMovieListsRecyclerViewAdapter adapter = new AddMovieListsRecyclerViewAdapter(arrayList, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}