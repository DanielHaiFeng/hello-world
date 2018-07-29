package com.xa.dt.jersey.bootjersey.beans;

public class Token {

    private String result;

    private String token;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Token{" +
                "result='" + result + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
