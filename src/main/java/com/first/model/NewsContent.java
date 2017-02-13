package com.first.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "news_content")
public class NewsContent {
    /**
     * 新闻正文ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 新闻摘要ID
     */
    @Column(name = "news_id")
    private Integer newsId;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 新闻正文
     */
    private String content;

    /**
     * 获取新闻正文ID
     *
     * @return id - 新闻正文ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置新闻正文ID
     *
     * @param id 新闻正文ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取新闻摘要ID
     *
     * @return news_id - 新闻摘要ID
     */
    public Integer getNewsId() {
        return newsId;
    }

    /**
     * 设置新闻摘要ID
     *
     * @param newsId 新闻摘要ID
     */
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
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

    /**
     * 获取更新时间
     *
     * @return updatetime - 更新时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置更新时间
     *
     * @param updatetime 更新时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取新闻正文
     *
     * @return content - 新闻正文
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置新闻正文
     *
     * @param content 新闻正文
     */
    public void setContent(String content) {
        this.content = content;
    }
}