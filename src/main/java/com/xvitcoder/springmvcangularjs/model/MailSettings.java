package com.xvitcoder.springmvcangularjs.model;

/**
 * Created by Stas on 28.01.2017.
 */
public class MailSettings {

    private String host;
    private String socketFactoryPort;
    private String socketFactoryClass;
    private String auth = "true";
    private String port;
    private String from;
    private String password;


    public MailSettings(){}

    public MailSettings(String host, String socketFactoryPort, String socketFactoryClass, String auth, String port, String from, String password) {
        this.host = host;
        this.socketFactoryPort = socketFactoryPort;
        this.socketFactoryClass = socketFactoryClass;
        this.auth = auth;
        this.port = port;
        this.from = from;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSocketFactoryPort() {
        return socketFactoryPort;
    }

    public void setSocketFactoryPort(String socketFactoryPort) {
        this.socketFactoryPort = socketFactoryPort;
    }

    public String getSocketFactoryClass() {
        return socketFactoryClass;
    }

    public void setSocketFactoryClass(String socketFactoryClass) {
        this.socketFactoryClass = socketFactoryClass;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MailSettings{" +
                "host='" + host + '\'' +
                ", socketFactoryPort='" + socketFactoryPort + '\'' +
                ", socketFactoryClass='" + socketFactoryClass + '\'' +
                ", auth='" + auth + '\'' +
                ", port='" + port + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
