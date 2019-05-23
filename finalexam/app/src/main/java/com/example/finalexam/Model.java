package com.example.finalexam;

import com.google.firebase.database.Exclude;

public class Model {
    @Exclude
    private long price;
    private String description;
    private long id;
    private String producer;
    private String product_name;
    private String Key;

    public Model() {
    }

    public Model(long price, String description, long id, String producer, String product_name) {
        this.price = price;
        this.description = description;
        this.id = id;
        this.producer = producer;
        this.product_name = product_name;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
