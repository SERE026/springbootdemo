package com.first.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "news_share")
public class NewsShare {
    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 新闻id
     */
    @Column(name = "news_id")
    private Integer newsId;

    /**
     * 分享人id
     */
    @Column(name = "share_user_id")
    private Integer shareUserId;

    /**
     * 分享人用户名
     */
    @Column(name = "share_username")
    private String shareUsername;

    /**
     * 分享的去向，如微信、qq
     */
    @Column(name = "share_to")
    private String shareTo;

    /**
     * 分享时间
     */
    private Date sharetime;

    /**
     * 获取唯一标识
     *
     * @return id - 唯一标识
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置唯一标识
     *
     * @param id 唯一标识
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取新闻id
     *
     * @return news_id - 新闻id
     */
    public Integer getNewsId() {
        return newsId;
    }

    /**
     * 设置新闻id
     *
     * @param newsId 新闻id
     */
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    /**
     * 获取分享人id
     *
     * @return share_user_id - 分享人id
     */
    public Integer getShareUserId() {
        return shareUserId;
    }

    /**
     * 设置分享人id
     *
     * @param shareUserId 分享人id
     */
    public void setShareUserId(Integer shareUserId) {
        this.shareUserId = shareUserId;
    }

    /**
     * 获取分享人用户名
     *
     * @return share_username - 分享人用户名
     */
    public String getShareUsername() {
        return shareUsername;
    }

    /**
     * 设置分享人用户名
     *
     * @param shareUsername 分享人用户名
     */
    public void setShareUsername(String shareUsername) {
        this.shareUsername = shareUsername;
    }

    /**
     * 获取分享的去向，如微信、qq
     *
     * @return share_to - 分享的去向，如微信、qq
     */
    public String getShareTo() {
        return shareTo;
    }

    /**
     * 设置分享的去向，如微信、qq
     *
     * @param shareTo 分享的去向，如微信、qq
     */
    public void setShareTo(String shareTo) {
        this.shareTo = shareTo;
    }

    /**
     * 获取分享时间
     *
     * @return sharetime - 分享时间
     */
    public Date getSharetime() {
        return sharetime;
    }

    /**
     * 设置分享时间
     *
     * @param sharetime 分享时间
     */
    public void setSharetime(Date sharetime) {
        this.sharetime = sharetime;
    }
}