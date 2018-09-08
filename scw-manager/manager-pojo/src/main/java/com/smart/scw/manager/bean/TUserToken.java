package com.smart.scw.manager.bean;

public class TUserToken {
    private Integer id;

    private Integer userid;

    private String pswToken;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getPswToken() {
        return pswToken;
    }

    public void setPswToken(String pswToken) {
        this.pswToken = pswToken == null ? null : pswToken.trim();
    }
}