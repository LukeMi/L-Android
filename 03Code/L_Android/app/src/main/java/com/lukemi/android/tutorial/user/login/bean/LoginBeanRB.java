package com.lukemi.android.tutorial.user.login.bean;

/**
 * 登录Bean
 */
public class LoginBeanRB {
    /**
     *     帐号
     */
    private String account ;
    /**
     *     密码
     */
    private String password ;

    public LoginBeanRB() {
    }

    public LoginBeanRB(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginBeanRB{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
