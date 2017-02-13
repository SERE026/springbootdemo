package com.first.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "news_section")
public class NewsSection {
    /**
     * 新闻版块ID
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 版块名称
     */
    private String sectionname;

    /**
     * 版块描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 创建者
     */
    @Column(name = "create_user_id")
    private Integer createUserId;

    /**
     * 获取新闻版块ID
     *
     * @return id - 新闻版块ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置新闻版块ID
     *
     * @param id 新闻版块ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取版块名称
     *
     * @return sectionname - 版块名称
     */
    public String getSectionname() {
        return sectionname;
    }

    /**
     * 设置版块名称
     *
     * @param sectionname 版块名称
     */
    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    /**
     * 获取版块描述
     *
     * @return description - 版块描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置版块描述
     *
     * @param description 版块描述
     */
    public void setDescription(String description) {
        this.description = description;
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
     * 获取创建者
     *
     * @return create_user_id - 创建者
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建者
     *
     * @param createUserId 创建者
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }
}