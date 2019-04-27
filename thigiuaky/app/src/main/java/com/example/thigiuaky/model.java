package com.example.thigiuaky;

public class model {
    private  int Id;
    private  String Hoten;
    private  String Mssv;
    private  String Lop;
    private  int Tuoi;
    private  String Email;

    public model(int id, String hoten, String mssv, String lop, int tuoi, String email) {
        Id = id;
        Hoten = hoten;
        Mssv = mssv;
        Lop = lop;
        Tuoi = tuoi;
        Email = email;
    }

    public model() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getMssv() {
        return Mssv;
    }

    public void setMssv(String mssv) {
        Mssv = mssv;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String lop) {
        Lop = lop;
    }

    public int getTuoi() {
        return Tuoi;
    }

    public void setTuoi(int tuoi) {
        Tuoi = tuoi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
