package com.example.managespending;

import java.io.Serializable;

public class Model_if implements Serializable {
    private int Id;
    private String Product;
    private String Cost;
    private String Local;
    private String Reason;
    private String Currency;
    private String Note;
    private String Time;

    public Model_if() {

    }
    public Model_if(String product, String cost, String local, String reason, String currency, String note, String time) {
        Product = product;
        Cost = cost;
        Local = local;
        Reason = reason;
        Currency = currency;
        Note = note;
        Time = time;
    }
    public Model_if(int id, String product, String cost, String local, String reason, String currency, String note, String time) {
        Id = id;
        Product = product;
        Cost = cost;
        Local = local;
        Reason = reason;
        Currency = currency;
        Note = note;
        Time = time;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
