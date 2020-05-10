package com.sx.springbootexample.common.baen;

import javax.validation.constraints.NotNull;

/**
 * 用户信息实例
 * 用户相关信息根据系统需要set对应字段，之后逻辑中需要则依赖此实例
 * 2020-05-08
 */
public class UserInfo {
    //账号
    private String accountNumber;
    //密码
    private String passWord;
    //系统Id
    private String systemId;
    //用户姓名
    @NotNull
    private String userName;
    //用户Id
    private String userId;
    //部门Id
    private String deptId;
    //中心Id或单位Id
    private String centerId;
    //角色Id
    private String rolesId;
    //权限Id
    private String authId;
    //微信Id
    private String openId;
    //头像
    private String userAvatar;

    public UserInfo() {

    }

    public UserInfo(String accountNumber, String passWord, String systemId, String userName, String userId, String deptId, String centerId, String rolesId, String authId, String openId, String userAvatar) {
        this.accountNumber = accountNumber;
        this.passWord = passWord;
        this.systemId = systemId;
        this.userName = userName;
        this.userId = userId;
        this.deptId = deptId;
        this.centerId = centerId;
        this.rolesId = rolesId;
        this.authId = authId;
        this.openId = openId;
        this.userAvatar = userAvatar;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "accountNumber='" + accountNumber + '\'' +
                ", passWord='" + passWord + '\'' +
                ", systemId='" + systemId + '\'' +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", deptId='" + deptId + '\'' +
                ", centerId='" + centerId + '\'' +
                ", rolesId='" + rolesId + '\'' +
                ", authId='" + authId + '\'' +
                ", openId='" + openId + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                '}';
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getRolesId() {
        return rolesId;
    }

    public void setRolesId(String rolesId) {
        this.rolesId = rolesId;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
