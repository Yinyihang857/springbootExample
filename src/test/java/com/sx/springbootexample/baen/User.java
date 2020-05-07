package com.sx.springbootexample.baen;

import java.util.Map;

public class User {
    private String userId;
    private String userName;
    private Map<String, String> userInfo;

    public User() {

    }

    public User(String userId, String userName, Map<String, String> userInfo) {
        this.userId = userId;
        this.userName = userName;
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String, String> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Map<String, String> userInfo) {
        this.userInfo = userInfo;
    }
}
