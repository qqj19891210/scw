package com.smart.scw.manager.bean;

import com.smart.scw.manager.validationGroup.UserGroup;

import javax.validation.constraints.Pattern;

public class TUser {
    private Integer id;

    @Pattern(regexp = "^[a-zA-Z]\\w{6,16}$",message = "{Pattern.user.loginacct}",groups = {UserGroup.class})
    private String loginacct;

    @Pattern(regexp = "^[a-zA-Z0-9]{6,16}$",message = "{Pattern.user.password}",groups = {UserGroup.class})
    private String userpswd;

    private String username;

    @Pattern(regexp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*",
            message = "{Pattern.user.email}",groups = {UserGroup.class})
    private String email;

    private String createtime;

    private String salt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginacct() {
        return loginacct;
    }

    public void setLoginacct(String loginacct) {
        this.loginacct = loginacct == null ? null : loginacct.trim();
    }

    public String getUserpswd() {
        return userpswd;
    }

    public void setUserpswd(String userpswd) {
        this.userpswd = userpswd == null ? null : userpswd.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    @Override
    public String toString() {
        return "TUser{" +
                "id=" + id +
                ", loginacct='" + loginacct + '\'' +
                ", userpswd='" + userpswd + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", createtime='" + createtime + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }

}