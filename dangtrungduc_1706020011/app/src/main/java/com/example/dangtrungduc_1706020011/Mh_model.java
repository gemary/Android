package com.example.dangtrungduc_1706020011;

import java.io.Serializable;

public class Mh_model implements Serializable {
    private String NameMh;
    private String idMh;
    private String SoChi;
    private String fullname;
    private String nganh;
    private String Gv;
    private String MSSV;

    public Mh_model(String nameMh, String idMh, String soChi, String fullname, String nganh, String gv, String MSSV) {
        NameMh = nameMh;
        this.idMh = idMh;
        SoChi = soChi;
        this.fullname = fullname;
        this.nganh = nganh;
        Gv = gv;
        this.MSSV = MSSV;
    }

    public String getNameMh() {
        return NameMh;
    }

    public void setNameMh(String nameMh) {
        NameMh = nameMh;
    }

    public String getIdMh() {
        return idMh;
    }

    public void setIdMh(String idMh) {
        this.idMh = idMh;
    }

    public String getSoChi() {
        return SoChi;
    }

    public void setSoChi(String soChi) {
        SoChi = soChi;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public String getGv() {
        return Gv;
    }

    public void setGv(String gv) {
        Gv = gv;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }
}
