package com.example.fragments.Model.List;

import java.util.ArrayList;

public class ListModel {
    //ESTOY CREANDO LA LLAMADA PARA CONSEGUIR TODAS LAS LISTAS CREADAS
    int page;
    ArrayList<Lista> results;
    int total_pages;
    int total_results;

    public int getPage() {
        return page;
    }

    public ArrayList<Lista> getResults() {
        return results;
    }

    public void setResults(ArrayList<Lista> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
