package com.xvitcoder.springmvcangularjs.mail;

/**
 * Created by Admin on 27.01.2017.
 */
public class Templates {
    String temp_1;
    String id;
    String num;

    public String getTemp_1() {
        return temp_1;
    }

    public void setTemp_1(String temp_1) {
        this.temp_1 = temp_1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Templates{" +
                "temp_1='" + temp_1 + '\'' +
                ", id='" + id + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
