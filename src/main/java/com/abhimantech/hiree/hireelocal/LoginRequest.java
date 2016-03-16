package com.abhimantech.hiree.hireelocal;


import com.google.gson.annotations.Expose;

public class LoginRequest {

    @Expose
    private String email;
    @Expose
    private String password;
    @Expose
    private String device;
    @Expose
    private String imei;

    public LoginRequest(String email, String password, String device, String imei) {
        this.email = email;
        this.password = password;
        this.device = device;
        this.imei = imei;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

}
