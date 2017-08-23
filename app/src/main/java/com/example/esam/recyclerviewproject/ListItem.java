package com.example.esam.recyclerviewproject;

/**
 * Created by Esam on 21/08/2017.
 */

public class ListItem {

    private String head;
    private String desc;
    private String imageUrl;
    private String date;

    public ListItem(String head, String desc, String imageUrl, String date) {
        this.head = head;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.date = date;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
