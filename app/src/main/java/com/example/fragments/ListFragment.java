package com.example.fragments;

import static com.example.fragments.Config.DefaultConstants.API_KEY;
import static com.example.fragments.Config.DefaultConstants.SESSION_ID;
import static com.example.fragments.Config.DefaultConstants.retrofit;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fragments.Config.ApiCall;
import com.example.fragments.Model.List.ListModel;
import com.example.fragments.Model.List.Lista;
import com.example.fragments.Recyclers.AddMovieListsRecyclerViewAdapter;
import com.example.fragments.Recyclers.ShowListsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListFragment extends Fragment {
    RecyclerView recyclerView;
    ShowListsAdapter showListsAdapter;

    public ListFragment() {
        // Required empty public constructor
    }
    //INICIO
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerLists);

        ApiCall apiCall = retrofit.create(ApiCall.class);
        Call<ListModel> call = apiCall.getListas(
                API_KEY,
                SESSION_ID
        );
        call.enqueue(new Callback<ListModel>() {
            @Override
            public void onResponse(Call<ListModel> call, Response<ListModel> response) {
                if (response.code() != 200){
                    Log.i("testApi","No ha funcionado "+response.code());

                }else {
                    Log.i("testApi","Listas obtenidas "+response.body().getResults().size());
                    if (response.body().getResults()!= null) {
                        callRecycler(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<ListModel> call, Throwable t) {
                Log.i("testApi","fallo");
            }
        });
        /*
        ShowListsAdapter showListsAdapter = new ShowListsAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(showListsAdapter);
        */
        FloatingActionButton btnAdd = view.findViewById(R.id.btnAddList);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;
    }

    public void showDialog(){
        View alertCustomdialog = getLayoutInflater().inflate( R.layout.form_add_list, null);

        //initialize alert builder.
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

        //set our custom alert dialog to tha alertdialog builder
        alert.setView(alertCustomdialog);

        final AlertDialog dialog = alert.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
        EditText nombre = alertCustomdialog.findViewById(R.id.txtList);
        EditText description = alertCustomdialog.findViewById(R.id.txtDescription);
        Button btnSaveList = alertCustomdialog.findViewById(R.id.btnSaveList);


        btnSaveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiCall apiCall = retrofit.create(ApiCall.class);
                Call<ListModel> call = apiCall.crearLista("application/json;charset=utf-8",
                        API_KEY,
                        SESSION_ID,
                        new Lista(nombre.getText().toString(), description.getText().toString()));
                call.enqueue(new Callback<ListModel>() {
                    @Override
                    public void onResponse(Call<ListModel> call, Response<ListModel> response) {
                        if (response.code() != 201){
                            Log.i("testApi","No ha funcionado "+response.code());
                        }else {
                            Log.i("testApi","Lista creada");
                        }
                    }

                    @Override
                    public void onFailure(Call<ListModel> call, Throwable t) {
                        Log.i("testApi","fallo");
                    }
                });
                dialog.dismiss();
            }
        });
    }
    public void callRecycler(ArrayList<Lista> listas){

        //RecyclerView recyclerView = alertCustomdialog.findViewById(R.id.recyclerList);
        ShowListsAdapter adapter = new ShowListsAdapter(listas, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }

}