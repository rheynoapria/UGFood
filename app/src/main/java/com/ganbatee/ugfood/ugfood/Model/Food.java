package com.ganbatee.ugfood.ugfood.Model;

/**
 * Created by enno on 14/11/17.
 */

public class Food {
    private String name, price, description, discount, image, menuid;

    public Food() {
    }

    public Food(String name, String price, String description, String discount, String image, String menuid) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.discount = discount;
        this.image = image;
        this.menuid = menuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }
}
