package com.gomro.pojo;

public class LoginData {
    // IsNegative(是否为正向用例) Desc(用例描述) UserName(用户名) Password(密码) ErrorMsg(错误提示期望值)
    private String isNegative;
    private String desc;
    private String userName;
    private String password;
    private String errorMsg;

    public String getIsNegative() {
        return isNegative;
    }

    public String getDesc() {
        return desc;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setIsNegative(String isNegative) {
        this.isNegative = isNegative;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "isNegative='" + isNegative + '\'' +
                ", desc='" + desc + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
