package com.first.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "comments_vote")
public class CommentsVote {
    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 评论id
     */
    @Column(name = "comments_id")
    private Integer commentsId;

    /**
     * 投票人id
     */
    @Column(name = "vote_user_id")
    private Integer voteUserId;

    /**
     * 投票人用户名
     */
    @Column(name = "vote_username")
    private String voteUsername;

    /**
     * 1：喜欢 2：不喜欢 3：举报
     */
    @Column(name = "vote_type")
    private Integer voteType;

    /**
     * 原因
     */
    private String reson;

    /**
     * 创建时间
     */
    private Date createtime;

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
     * 获取评论id
     *
     * @return comments_id - 评论id
     */
    public Integer getCommentsId() {
        return commentsId;
    }

    /**
     * 设置评论id
     *
     * @param commentsId 评论id
     */
    public void setCommentsId(Integer commentsId) {
        this.commentsId = commentsId;
    }

    /**
     * 获取投票人id
     *
     * @return vote_user_id - 投票人id
     */
    public Integer getVoteUserId() {
        return voteUserId;
    }

    /**
     * 设置投票人id
     *
     * @param voteUserId 投票人id
     */
    public void setVoteUserId(Integer voteUserId) {
        this.voteUserId = voteUserId;
    }

    /**
     * 获取投票人用户名
     *
     * @return vote_username - 投票人用户名
     */
    public String getVoteUsername() {
        return voteUsername;
    }

    /**
     * 设置投票人用户名
     *
     * @param voteUsername 投票人用户名
     */
    public void setVoteUsername(String voteUsername) {
        this.voteUsername = voteUsername;
    }

    /**
     * 获取1：喜欢 2：不喜欢 3：举报
     *
     * @return vote_type - 1：喜欢 2：不喜欢 3：举报
     */
    public Integer getVoteType() {
        return voteType;
    }

    /**
     * 设置1：喜欢 2：不喜欢 3：举报
     *
     * @param voteType 1：喜欢 2：不喜欢 3：举报
     */
    public void setVoteType(Integer voteType) {
        this.voteType = voteType;
    }

    /**
     * 获取原因
     *
     * @return reson - 原因
     */
    public String getReson() {
        return reson;
    }

    /**
     * 设置原因
     *
     * @param reson 原因
     */
    public void setReson(String reson) {
        this.reson = reson;
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