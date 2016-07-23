package com.example.vanthanh.yourcardvisit.model;

/**
 * Created by Administrator on 7/22/2016.
 */
public class selectedCard {
    String name;
    String urlKey;

    public selectedCard() {
    }

    public selectedCard(String urlKey, String name) {
        this.urlKey = urlKey;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(String urlKey) {
        this.urlKey = urlKey;
    }
}
