package com.first.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "news_type")
public class NewsType {
    /**
     * 新闻类型id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 新闻类型
     */
    private String ntype;

    /**
     * 新闻类型名称
     */
    @Column(name = "ntype_name")
    private String ntypeName;

    /**
     * 描述
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
     * 获取新闻类型id
     *
     * @return id - 新闻类型id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置新闻类型id
     *
     * @param id 新闻类型id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取新闻类型
     *
     * @return ntype - 新闻类型
     */
    public String getNtype() {
        return ntype;
    }

    /**
     * 设置新闻类型
     *
     * @param ntype 新闻类型
     */
    public void setNtype(String ntype) {
        this.ntype = ntype;
    }

    /**
     * 获取新闻类型名称
     *
     * @return ntype_name - 新闻类型名称
     */
    public String getNtypeName() {
        return ntypeName;
    }

    /**
     * 设置新闻类型名称
     *
     * @param ntypeName 新闻类型名称
     */
    public void setNtypeName(String ntypeName) {
        this.ntypeName = ntypeName;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
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
}