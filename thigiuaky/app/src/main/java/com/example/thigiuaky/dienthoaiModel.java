package com.example.thigiuaky;

public class dienthoaiModel {
    private  int Id;
    private String Product_name;
    private int Price;
    private  String description;
    private  String producer;

    public dienthoaiModel(int id,String product_name, int price, String description, String producer) {
        Id = id;
        Product_name = product_name;
        Price = price;
        this.description = description;
        this.producer = producer;
    }

    public dienthoaiModel() {
    }
    public  int  getId()
    {
        return  Id;
    }
    public String getProduct_name() {
        return Product_name;
    }

    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
    public void  setId(int id)
    {
        Id =id;
    }
}
