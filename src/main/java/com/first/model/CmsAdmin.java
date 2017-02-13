package com.first.model;

import javax.persistence.*;

@Table(name = "cms_admin")
public class CmsAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String lastloginip;

    private Integer lastlogintime;

    private String email;

    private String realname;

    private Integer status;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return lastloginip
     */
    public String getLastloginip() {
        return lastloginip;
    }

    /**
     * @param lastloginip
     */
    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip;
    }

    /**
     * @return lastlogintime
     */
    public Integer getLastlogintime() {
        return lastlogintime;
    }

    /**
     * @param lastlogintime
     */
    public void setLastlogintime(Integer lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return realname
     */
    public String getRealname() {
        return realname;
    }

    /**
     * @param realname
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}