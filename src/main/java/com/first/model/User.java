package com.first.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "user")
public class User implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -830226373886561976L;

	/**
     * 标识id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户名
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 城市
     */
    private Integer city;

    /**
     * 省份或者直辖市
     */
    private Integer province;

    /**
     * 角色
     */
    private Long roles;

    /**
     * 用户状态 ：0;未认证 0未实名 1 实名通过 2实名失败 3 审核中
     */
    private Integer status;

    /**
     * 新浪微博uid
     */
    @Column(name = "weibo_uid")
    private String weiboUid;

    /**
     * 新浪微博AccessToken
     */
    @Column(name = "weibo_access_token")
    private String weiboAccessToken;

    /**
     * 腾讯uid
     */
    @Column(name = "qq_uid")
    private String qqUid;

    /**
     * 腾讯AccessToken
     */
    @Column(name = "qq_access_token")
    private String qqAccessToken;

    /**
     * 注册来源
     */
    private String source;

    /**
     * 推荐人，存储推荐人用户名
     */
    private String referee;

    /**
     * 上回登录时间
     */
    @Column(name = "last_login_date")
    private Date lastLoginDate;

    /**
     * 当前登录日期
     */
    @Column(name = "cur_login_date")
    private Date curLoginDate;

    /**
     * 密码强度等级
     */
    @Column(name = "passwd_level")
    private Integer passwdLevel;

    /**
     * 获取标识id
     *
     * @return id - 标识id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置标识id
     *
     * @param id 标识id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取用户名
     *
     * @return nickname - 用户名
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置用户名
     *
     * @param nickname 用户名
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取真实姓名
     *
     * @return realname - 真实姓名
     */
    public String getRealname() {
        return realname;
    }

    /**
     * 设置真实姓名
     *
     * @param realname 真实姓名
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * 获取身份证号
     *
     * @return idcard - 身份证号
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 设置身份证号
     *
     * @param idcard 身份证号
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public Integer getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(Integer city) {
        this.city = city;
    }

    /**
     * 获取省份或者直辖市
     *
     * @return province - 省份或者直辖市
     */
    public Integer getProvince() {
        return province;
    }

    /**
     * 设置省份或者直辖市
     *
     * @param province 省份或者直辖市
     */
    public void setProvince(Integer province) {
        this.province = province;
    }

    /**
     * 获取角色
     *
     * @return roles - 角色
     */
    public Long getRoles() {
        return roles;
    }

    /**
     * 设置角色
     *
     * @param roles 角色
     */
    public void setRoles(Long roles) {
        this.roles = roles;
    }

    /**
     * 获取用户状态 ：0;未认证 0未实名 1 实名通过 2实名失败 3 审核中
     *
     * @return status - 用户状态 ：0;未认证 0未实名 1 实名通过 2实名失败 3 审核中
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置用户状态 ：0;未认证 0未实名 1 实名通过 2实名失败 3 审核中
     *
     * @param status 用户状态 ：0;未认证 0未实名 1 实名通过 2实名失败 3 审核中
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取新浪微博uid
     *
     * @return weibo_uid - 新浪微博uid
     */
    public String getWeiboUid() {
        return weiboUid;
    }

    /**
     * 设置新浪微博uid
     *
     * @param weiboUid 新浪微博uid
     */
    public void setWeiboUid(String weiboUid) {
        this.weiboUid = weiboUid;
    }

    /**
     * 获取新浪微博AccessToken
     *
     * @return weibo_access_token - 新浪微博AccessToken
     */
    public String getWeiboAccessToken() {
        return weiboAccessToken;
    }

    /**
     * 设置新浪微博AccessToken
     *
     * @param weiboAccessToken 新浪微博AccessToken
     */
    public void setWeiboAccessToken(String weiboAccessToken) {
        this.weiboAccessToken = weiboAccessToken;
    }

    /**
     * 获取腾讯uid
     *
     * @return qq_uid - 腾讯uid
     */
    public String getQqUid() {
        return qqUid;
    }

    /**
     * 设置腾讯uid
     *
     * @param qqUid 腾讯uid
     */
    public void setQqUid(String qqUid) {
        this.qqUid = qqUid;
    }

    /**
     * 获取腾讯AccessToken
     *
     * @return qq_access_token - 腾讯AccessToken
     */
    public String getQqAccessToken() {
        return qqAccessToken;
    }

    /**
     * 设置腾讯AccessToken
     *
     * @param qqAccessToken 腾讯AccessToken
     */
    public void setQqAccessToken(String qqAccessToken) {
        this.qqAccessToken = qqAccessToken;
    }

    /**
     * 获取注册来源
     *
     * @return source - 注册来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置注册来源
     *
     * @param source 注册来源
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取推荐人，存储推荐人用户名
     *
     * @return referee - 推荐人，存储推荐人用户名
     */
    public String getReferee() {
        return referee;
    }

    /**
     * 设置推荐人，存储推荐人用户名
     *
     * @param referee 推荐人，存储推荐人用户名
     */
    public void setReferee(String referee) {
        this.referee = referee;
    }

    /**
     * 获取上回登录时间
     *
     * @return last_login_date - 上回登录时间
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * 设置上回登录时间
     *
     * @param lastLoginDate 上回登录时间
     */
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * 获取当前登录日期
     *
     * @return cur_login_date - 当前登录日期
     */
    public Date getCurLoginDate() {
        return curLoginDate;
    }

    /**
     * 设置当前登录日期
     *
     * @param curLoginDate 当前登录日期
     */
    public void setCurLoginDate(Date curLoginDate) {
        this.curLoginDate = curLoginDate;
    }

    /**
     * 获取密码强度等级
     *
     * @return passwd_level - 密码强度等级
     */
    public Integer getPasswdLevel() {
        return passwdLevel;
    }

    /**
     * 设置密码强度等级
     *
     * @param passwdLevel 密码强度等级
     */
    public void setPasswdLevel(Integer passwdLevel) {
        this.passwdLevel = passwdLevel;
    }
}