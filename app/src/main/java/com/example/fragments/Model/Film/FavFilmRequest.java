package com.example.fragments.Model.Film;

import java.util.ArrayList;

public class FavFilmRequest {
    /*public int page;
    public ArrayList<Film> results;

    public int getPage() {
        return page;
    }

    public ArrayList<Film> getResults() {
        return results;
    }

     */
    //@SerializedName("deviceId")
    String media_type;
    int media_id;
    boolean favourite;

    public FavFilmRequest(String media_type, int media_id, boolean favourite) {
        this.media_type = media_type;
        this.media_id = media_id;
        this.favourite = favourite;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public int getMedia_id() {
        return media_id;
    }

    public void setMedia_id(int media_id) {
        this.media_id = media_id;
    }




}
