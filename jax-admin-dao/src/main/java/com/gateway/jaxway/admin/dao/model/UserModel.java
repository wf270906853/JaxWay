package com.gateway.jaxway.admin.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.core.style.ToStringCreator;

import java.util.Date;

public class UserModel {
    @JsonIgnore
    private Integer id;

    private String userName;

    @JsonIgnore
    private String psw;

    private String avatar;

    private String email;

    private Integer roleType;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("username",getUserName())
                .append("psw",getPsw())
                .append("email",getEmail())
                .append("roleType",getRoleType())
                .toString();
    }
}