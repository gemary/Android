package com.baucua;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Models {
    public long num1;
    public long num2;
    public long num3;

    public Models() {
    }

    public Models(long num1, long num2, long num3) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }
}
