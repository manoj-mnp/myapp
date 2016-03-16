package com.abhimantech.hiree.hireelocal;

import com.google.gson.annotations.Expose;

public class User {

    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private String email;
    @Expose
    private String phone;
    @Expose
    private Object photo;
//    @Expose
//    private String password;
//    @Expose
//    private String salt;
//    @Expose
//    private String activationStr;
    @Expose
    private Integer createdBy;
    @Expose
    private Long createdTime;
    @Expose
    private Integer updatedBy;
    @Expose
    private Long updatedTime;
    @Expose
    private Integer status;
    @Expose
    private Integer type;
    @Expose
    private String custom1;
    @Expose
    private Object custom2;
    @Expose
    private Object custom3;
//    @Expose
//    private String resetPassword;
    @Expose
    private Object imageUrl;
    @Expose
    private Boolean recruiter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    /*public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/

    /*public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }*/

    /*public String getActivationStr() {
        return activationStr;
    }

    public void setActivationStr(String activationStr) {
        this.activationStr = activationStr;
    }*/

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCustom1() {
        return custom1;
    }

    public void setCustom1(String custom1) {
        this.custom1 = custom1;
    }

    public Object getCustom2() {
        return custom2;
    }

    public void setCustom2(Object custom2) {
        this.custom2 = custom2;
    }

    public Object getCustom3() {
        return custom3;
    }

    public void setCustom3(Object custom3) {
        this.custom3 = custom3;
    }

    /*public String getResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(String resetPassword) {
        this.resetPassword = resetPassword;
    }*/

    public Object getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Boolean recruiter) {
        this.recruiter = recruiter;
    }

}
