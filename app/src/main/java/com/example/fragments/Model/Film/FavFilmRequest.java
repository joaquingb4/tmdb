package com.example.fragments.Model.Film;


import com.google.gson.annotations.SerializedName;

public class FavFilmRequest {
    private String media_type;
    private int media_id;
    private boolean favorite;

    public FavFilmRequest(String media_type, int media_id, boolean favourite) {
        this.media_type = media_type;
        this.media_id = media_id;
        this.favorite = favourite;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public boolean isFavourite() {
        return favorite;
    }

    public void setFavourite(boolean favourite) {
        this.favorite = favourite;
    }

    public int getMedia_id() {
        return media_id;
    }

    public void setMedia_id(int media_id) {
        this.media_id = media_id;
    }




}
