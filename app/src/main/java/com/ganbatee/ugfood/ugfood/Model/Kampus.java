package com.ganbatee.ugfood.ugfood.Model;

/**
 * Created by enno on 27/10/17.
 */

public class Kampus {
    private String Name;
    private String Image;

    public Kampus() {
    }

    public Kampus(String name, String image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}

