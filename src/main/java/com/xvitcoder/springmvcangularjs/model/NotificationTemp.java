package com.xvitcoder.springmvcangularjs.model;

/**
 * Created by Admin on 20.01.2017.
 */
public class NotificationTemp {
    private int notif_num;
    private int user_id;
    private String template;

    public NotificationTemp(int notif_num,int user_id,String template){
        this.notif_num=notif_num;
        this.user_id=user_id;
        this.template=template;
    }

    public int getNotif_num() {
        return notif_num;
    }

    public void setNotif_num(int notif_num) {
        this.notif_num = notif_num;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public String toString() {
        return "NotificationTemp{" +
                "notif_num=" + notif_num +
                ", user_id=" + user_id +
                ", template='" + template + '\'' +
                '}';
    }
}
