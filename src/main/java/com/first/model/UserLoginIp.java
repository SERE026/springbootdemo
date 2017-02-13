package com.first.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_login_ip")
public class UserLoginIp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 登录ip
     */
    @Column(name = "login_ip")
    private String loginIp;

    /**
     * 浏览器类型
     */
    @Column(name = "browser_type")
    private String browserType;

    /**
     * 操作系统类型
     */
    @Column(name = "os_type")
    private String osType;

    /**
     * 移动用户标识
     */
    @Column(name = "IMSI")
    private String imsi;

    /**
     * 移动设备标识
     */
    @Column(name = "IMEI")
    private String imei;

    @Column(name = "login_address")
    private String loginAddress;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 是否为注册
     */
    @Column(name = "is_register")
    private Integer isRegister;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取登录ip
     *
     * @return login_ip - 登录ip
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置登录ip
     *
     * @param loginIp 登录ip
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取浏览器类型
     *
     * @return browser_type - 浏览器类型
     */
    public String getBrowserType() {
        return browserType;
    }

    /**
     * 设置浏览器类型
     *
     * @param browserType 浏览器类型
     */
    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    /**
     * 获取操作系统类型
     *
     * @return os_type - 操作系统类型
     */
    public String getOsType() {
        return osType;
    }

    /**
     * 设置操作系统类型
     *
     * @param osType 操作系统类型
     */
    public void setOsType(String osType) {
        this.osType = osType;
    }

    /**
     * 获取移动用户标识
     *
     * @return IMSI - 移动用户标识
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * 设置移动用户标识
     *
     * @param imsi 移动用户标识
     */
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    /**
     * 获取移动设备标识
     *
     * @return IMEI - 移动设备标识
     */
    public String getImei() {
        return imei;
    }

    /**
     * 设置移动设备标识
     *
     * @param imei 移动设备标识
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * @return login_address
     */
    public String getLoginAddress() {
        return loginAddress;
    }

    /**
     * @param loginAddress
     */
    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    /**
     * 获取登录时间
     *
     * @return login_time - 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取是否为注册
     *
     * @return is_register - 是否为注册
     */
    public Integer getIsRegister() {
        return isRegister;
    }

    /**
     * 设置是否为注册
     *
     * @param isRegister 是否为注册
     */
    public void setIsRegister(Integer isRegister) {
        this.isRegister = isRegister;
    }
}