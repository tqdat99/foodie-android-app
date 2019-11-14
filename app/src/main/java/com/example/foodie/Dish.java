package com.example.foodie;

import java.io.Serializable;

public class Dish implements Serializable {

    private int thumbnail;
    private String name;
    private String id;
    private String type;

    public Dish(int thumbnail, String id, String name) {
        this.thumbnail = thumbnail;
        this.name = name;
        this.id = id;
        if (id.charAt(0) == 'N') type = "Món nước";
        if (id.charAt(0) == 'K') type = "Món khô";
        if (id.charAt(0) == 'C') type = "Món chay";
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
