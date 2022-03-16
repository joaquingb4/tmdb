package com.example.fragments;

import static com.example.fragments.Config.DefaultConstants.API_KEY;
import static com.example.fragments.Config.DefaultConstants.SESSION_ID;
import static com.example.fragments.Config.DefaultConstants.retrofit;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fragments.Config.ApiCall;
import com.example.fragments.Model.Film.FavFilmRequest;
import com.example.fragments.Model.Film.Film;
import com.example.fragments.Model.Film.searchFilmModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListFragment extends Fragment {

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        FloatingActionButton btnAdd = view.findViewById(R.id.btnAddList);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        //____
        ApiCall apiCall = retrofit.create(ApiCall.class);
        Call<FavFilmRequest> call = apiCall.getFavoritesMovies(API_KEY, SESSION_ID);

        call.enqueue(new Callback<FavFilmRequest>(){
            @Override
            public void onResponse(Call<FavFilmRequest> call, Response<FavFilmRequest> response) {
                //<<<<<<<<<<<<<<<<<<<ESTOY AQUÍ>>>>>>>>>>>>>>>>>>>>>>
            }

            @Override
            public void onFailure(Call<FavFilmRequest> call, Throwable t) {

            }

            @Override
            public void onResponse(Call<searchFilmModel> call, Response<searchFilmModel> response) {
                if(response.code()!=200){
                    Log.i("testApi", "checkConnection");
                    return;
                }else {
                    ArrayList<Film> arraySearch = new ArrayList<>();
                    arraySearch = response.body().getResults();
                    callRecycler(arraySearch);
                }
            }
                @Override
                public void onFailure(Call<searchFilmModel> call, Throwable t) {

                }
            });
        return view;
    }
        //____


    public void showDialog(){
        View alertCustomdialog = getLayoutInflater().inflate( R.layout.form_add_list, null);

        //initialize alert builder.
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

        //set our custom alert dialog to tha alertdialog builder
        alert.setView(alertCustomdialog);

        final AlertDialog dialog = alert.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();

        Button btnSaveList = alertCustomdialog.findViewById(R.id.btnSaveList);

        btnSaveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}