package com.xvitcoder.springmvcangularjs.model;

/**
 * Created by Admin on 28.01.2017.
 */
public class ErrorText {
    int id_num;
    String error_m;

    public ErrorText(int id_num, String error_m) {
        this.id_num = id_num;
        this.error_m = error_m;
    }

    public int getId_num() {
        return id_num;
    }

    public void setId_num(int id_num) {
        this.id_num = id_num;
    }

    public String getError_m() {
        return error_m;
    }

    public void setError_m(String error_m) {
        this.error_m = error_m;
    }
}
