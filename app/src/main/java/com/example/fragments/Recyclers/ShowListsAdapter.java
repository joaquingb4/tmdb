package com.example.fragments.Recyclers;

import static com.example.fragments.Config.DefaultConstants.BASE_IMG_URL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragments.Config.GlideApp;
import com.example.fragments.Model.List.Lista;
import com.example.fragments.R;

import java.util.ArrayList;

public class ShowListsAdapter extends RecyclerView.Adapter<ShowListsAdapter.ViewHolder> {

    ArrayList<Lista> listas;
    Context context;

    public ShowListsAdapter(ArrayList<Lista> listas, Context c){
        this.listas = listas;
        Context context = c;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistmenu, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(listas.get(position).getName());
        holder.description.setText(listas.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return listas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.listas_name);
            description = itemView.findViewById(R.id.listas_description);
        }
    }
}
