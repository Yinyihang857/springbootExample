package com.sx.springbootexample.common.baen;

import javax.validation.constraints.NotNull;

/**
 * 用户信息实例
 * 用户相关信息根据系统需要set对应字段，之后逻辑中需要则依赖此实例
 * 参数校验例子在下面
 * 2020-05-08
 */
public class UserInfo {
    //账号
    private String accountNumber;
    //密码
    //@NotNull(message = "密码不能为空")
    private String passWord;
    //系统Id
    private String systemId;
    //用户姓名
    //@NotNull(message = "用户名不能为空")
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

/**
 * 常用的注解主要有以下几个，作用及内容如下所示
 * @Null，标注的属性值必须为空
 *
 * @NotNull，标注的属性值不能为空
 *
 * @AssertTrue，标注的属性值必须为true
 *
 * @AssertFalse，标注的属性值必须为false
 *
 * @Min，标注的属性值不能小于min中指定的值
 *
 * @Max，标注的属性值不能大于max中指定的值
 *
 * @DecimalMin，小数值，同上
 *
 * @DecimalMax，小数值，同上
 *
 * @Negative，负数
 *
 * @NegativeOrZero，0或者负数
 *
 * @Positive，整数
 *
 * @PositiveOrZero，0或者整数
 *
 * @Size，指定字符串长度，注意是长度，有两个值，min以及max，用于指定最小以及最大长度
 *
 * @Digits，内容必须是数字
 *
 * @Past，时间必须是过去的时间
 *
 * @PastOrPresent，过去或者现在的时间
 *
 * @Future，将来的时间
 *
 * @FutureOrPresent，将来或者现在的时间
 *
 * @Pattern，用于指定一个正则表达式
 *
 * @NotEmpty，字符串内容非空
 *
 * @NotBlank，字符串内容非空且长度大于0
 *
 * @Email，邮箱
 *
 * @Range，用于指定数字，注意是数字的范围，有两个值，min以及max
 * ————————————————
 */
