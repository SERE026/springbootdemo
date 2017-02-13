package com.first.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "comments")
public class Comments {
    /**
     * id 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父级id，第一楼回复第二楼此值不为0
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 新闻id
     */
    @Column(name = "news_id")
    private Integer newsId;

    /**
     * 评论人id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 评论人用户名
     */
    private String username;

    /**
     * 回复人id
     */
    @Column(name = "reply_user_id")
    private Integer replyUserId;

    /**
     * 回复人用户名
     */
    @Column(name = "reply_username")
    private String replyUsername;

    /**
     * 回复条数
     */
    @Column(name = "reply_count")
    private Integer replyCount;

    /**
     * 点赞个数
     */
    @Column(name = "like_count")
    private Integer likeCount;

    /**
     * 不喜欢个数
     */
    @Column(name = "unlike_count")
    private Integer unlikeCount;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 回复时间
     */
    private Date createtime;

    /**
     * 回复类容
     */
    private String content;

    /**
     * 获取id 唯一标识
     *
     * @return id - id 唯一标识
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id 唯一标识
     *
     * @param id id 唯一标识
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取父级id，第一楼回复第二楼此值不为0
     *
     * @return parent_id - 父级id，第一楼回复第二楼此值不为0
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父级id，第一楼回复第二楼此值不为0
     *
     * @param parentId 父级id，第一楼回复第二楼此值不为0
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
     * 获取评论人id
     *
     * @return user_id - 评论人id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置评论人id
     *
     * @param userId 评论人id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取评论人用户名
     *
     * @return username - 评论人用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置评论人用户名
     *
     * @param username 评论人用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取回复人id
     *
     * @return reply_user_id - 回复人id
     */
    public Integer getReplyUserId() {
        return replyUserId;
    }

    /**
     * 设置回复人id
     *
     * @param replyUserId 回复人id
     */
    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    /**
     * 获取回复人用户名
     *
     * @return reply_username - 回复人用户名
     */
    public String getReplyUsername() {
        return replyUsername;
    }

    /**
     * 设置回复人用户名
     *
     * @param replyUsername 回复人用户名
     */
    public void setReplyUsername(String replyUsername) {
        this.replyUsername = replyUsername;
    }

    /**
     * 获取回复条数
     *
     * @return reply_count - 回复条数
     */
    public Integer getReplyCount() {
        return replyCount;
    }

    /**
     * 设置回复条数
     *
     * @param replyCount 回复条数
     */
    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    /**
     * 获取点赞个数
     *
     * @return like_count - 点赞个数
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * 设置点赞个数
     *
     * @param likeCount 点赞个数
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * 获取不喜欢个数
     *
     * @return unlike_count - 不喜欢个数
     */
    public Integer getUnlikeCount() {
        return unlikeCount;
    }

    /**
     * 设置不喜欢个数
     *
     * @param unlikeCount 不喜欢个数
     */
    public void setUnlikeCount(Integer unlikeCount) {
        this.unlikeCount = unlikeCount;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取回复时间
     *
     * @return createtime - 回复时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置回复时间
     *
     * @param createtime 回复时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取回复类容
     *
     * @return content - 回复类容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置回复类容
     *
     * @param content 回复类容
     */
    public void setContent(String content) {
        this.content = content;
    }
}