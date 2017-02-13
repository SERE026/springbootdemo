package com.first.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "news_statitics")
public class NewsStatitics {
    /**
     * 唯一标记
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
     * 点赞次数
     */
    @Column(name = "like_count")
    private Integer likeCount;

    /**
     * 不喜欢次数
     */
    @Column(name = "unlike_count")
    private Integer unlikeCount;

    /**
     * 举报次数
     */
    @Column(name = "complain_count")
    private Integer complainCount;

    /**
     * 分享次数
     */
    @Column(name = "share_count")
    private Integer shareCount;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 获取唯一标记
     *
     * @return id - 唯一标记
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置唯一标记
     *
     * @param id 唯一标记
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
     * 获取点赞次数
     *
     * @return like_count - 点赞次数
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * 设置点赞次数
     *
     * @param likeCount 点赞次数
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * 获取不喜欢次数
     *
     * @return unlike_count - 不喜欢次数
     */
    public Integer getUnlikeCount() {
        return unlikeCount;
    }

    /**
     * 设置不喜欢次数
     *
     * @param unlikeCount 不喜欢次数
     */
    public void setUnlikeCount(Integer unlikeCount) {
        this.unlikeCount = unlikeCount;
    }

    /**
     * 获取举报次数
     *
     * @return complain_count - 举报次数
     */
    public Integer getComplainCount() {
        return complainCount;
    }

    /**
     * 设置举报次数
     *
     * @param complainCount 举报次数
     */
    public void setComplainCount(Integer complainCount) {
        this.complainCount = complainCount;
    }

    /**
     * 获取分享次数
     *
     * @return share_count - 分享次数
     */
    public Integer getShareCount() {
        return shareCount;
    }

    /**
     * 设置分享次数
     *
     * @param shareCount 分享次数
     */
    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    /**
     * 获取创建时间
     *
     * @return createtime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}