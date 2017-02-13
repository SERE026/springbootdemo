package com.first.model;

import javax.persistence.*;

@Table(name = "advertise_area_content")
public class AdvertiseAreaContent {
    /**
     * 区域内容ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 区域名称ID号
     */
    @Column(name = "ad_area_id")
    private Integer adAreaId;

    /**
     * 内容标题
     */
    private String title;

    /**
     * 内容缩略图
     */
    private String thumb;

    /**
     * 内容链接地址
     */
    private String url;

    /**
     * 内容对应新闻ID号
     */
    @Column(name = "news_id")
    private Integer newsId;

    /**
     * 内容排序规则
     */
    private Integer order;

    /**
     * 内容状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Integer createtime;

    /**
     * 更新时间
     */
    private Integer updatetime;

    /**
     * 获取区域内容ID
     *
     * @return id - 区域内容ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置区域内容ID
     *
     * @param id 区域内容ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取区域名称ID号
     *
     * @return ad_area_id - 区域名称ID号
     */
    public Integer getAdAreaId() {
        return adAreaId;
    }

    /**
     * 设置区域名称ID号
     *
     * @param adAreaId 区域名称ID号
     */
    public void setAdAreaId(Integer adAreaId) {
        this.adAreaId = adAreaId;
    }

    /**
     * 获取内容标题
     *
     * @return title - 内容标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置内容标题
     *
     * @param title 内容标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取内容缩略图
     *
     * @return thumb - 内容缩略图
     */
    public String getThumb() {
        return thumb;
    }

    /**
     * 设置内容缩略图
     *
     * @param thumb 内容缩略图
     */
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    /**
     * 获取内容链接地址
     *
     * @return url - 内容链接地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置内容链接地址
     *
     * @param url 内容链接地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取内容对应新闻ID号
     *
     * @return news_id - 内容对应新闻ID号
     */
    public Integer getNewsId() {
        return newsId;
    }

    /**
     * 设置内容对应新闻ID号
     *
     * @param newsId 内容对应新闻ID号
     */
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    /**
     * 获取内容排序规则
     *
     * @return order - 内容排序规则
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * 设置内容排序规则
     *
     * @param order 内容排序规则
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * 获取内容状态
     *
     * @return status - 内容状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置内容状态
     *
     * @param status 内容状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return createtime - 创建时间
     */
    public Integer getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取更新时间
     *
     * @return updatetime - 更新时间
     */
    public Integer getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置更新时间
     *
     * @param updatetime 更新时间
     */
    public void setUpdatetime(Integer updatetime) {
        this.updatetime = updatetime;
    }
}