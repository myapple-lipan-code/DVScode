package com.ljs.callray.controller.vo;

import com.ljs.callray.entity.Account;

import java.io.Serializable;

/**
 * @author LiJinSong
 * @date 2021-01-28
 */
public class LoginVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Account user;

    private String token;

    public LoginVo() {
    }

    public LoginVo(Account user, String token) {
        this.user = user;
        this.token = token;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "user=" + user +
                ", token='" + token + '\'' +
                '}';
    }
}
