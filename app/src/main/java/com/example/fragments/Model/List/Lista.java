package com.example.fragments.Model.List;

public class Lista {
    public String name;
    public String description;

    public Lista(String title, String description) {
        this.name = title;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
