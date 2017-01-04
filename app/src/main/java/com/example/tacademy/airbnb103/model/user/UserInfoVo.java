package com.example.tacademy.airbnb103.model.user;

import java.io.Serializable;

/**
 * 회원가입 시 사용되는 데이터 모델
 */

public class UserInfoVo implements Serializable{
    //기본정보
    String firstName;
    String lastName;
    String password;
    String email;

    //백단 정보
    String phone;
    String token;   //push 때 사용
    String uuid;
    String os_version;
    String device;

    public UserInfoVo() {
    }

    public UserInfoVo(String firstName, String lastName, String password, String email, String phone, String token, String uuid, String os_version, String device) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.token = token;
        this.uuid = uuid;
        this.os_version = os_version;
        this.device = device;
    }

    @Override
    public String toString() {
        return "UserInfoVo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", token='" + token + '\'' +
                ", uuid='" + uuid + '\'' +
                ", os_version='" + os_version + '\'' +
                ", device='" + device + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}