package com.first.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "news")
public class News {
    /**
     * 新闻ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 新闻类型ID
     */
    @Column(name = "newstype_id")
    private Integer newstypeId;

    /**
     * 栏目ID
     */
    @Column(name = "section_id")
    private Integer sectionId;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 标题颜色
     */
    private String titlecolor;

    /**
     * 缩略图
     */
    private String thumb;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 简介
     */
    private String description;

    /**
     * 展示位置
     */
    @Column(name = "position_id")
    private String positionId;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 来源
     */
    private String source;

    /**
     * 作者
     */
    private String author;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 阅读量
     */
    private Integer count;

    /**
     * 获取新闻ID
     *
     * @return id - 新闻ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置新闻ID
     *
     * @param id 新闻ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取新闻类型ID
     *
     * @return newstype_id - 新闻类型ID
     */
    public Integer getNewstypeId() {
        return newstypeId;
    }

    /**
     * 设置新闻类型ID
     *
     * @param newstypeId 新闻类型ID
     */
    public void setNewstypeId(Integer newstypeId) {
        this.newstypeId = newstypeId;
    }

    /**
     * 获取栏目ID
     *
     * @return section_id - 栏目ID
     */
    public Integer getSectionId() {
        return sectionId;
    }

    /**
     * 设置栏目ID
     *
     * @param sectionId 栏目ID
     */
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取副标题
     *
     * @return subtitle - 副标题
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * 设置副标题
     *
     * @param subtitle 副标题
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * 获取标题颜色
     *
     * @return titlecolor - 标题颜色
     */
    public String getTitlecolor() {
        return titlecolor;
    }

    /**
     * 设置标题颜色
     *
     * @param titlecolor 标题颜色
     */
    public void setTitlecolor(String titlecolor) {
        this.titlecolor = titlecolor;
    }

    /**
     * 获取缩略图
     *
     * @return thumb - 缩略图
     */
    public String getThumb() {
        return thumb;
    }

    /**
     * 设置缩略图
     *
     * @param thumb 缩略图
     */
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    /**
     * 获取关键字
     *
     * @return keywords - 关键字
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置关键字
     *
     * @param keywords 关键字
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 获取简介
     *
     * @return description - 简介
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置简介
     *
     * @param description 简介
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取展示位置
     *
     * @return position_id - 展示位置
     */
    public String getPositionId() {
        return positionId;
    }

    /**
     * 设置展示位置
     *
     * @param positionId 展示位置
     */
    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    /**
     * 获取排序
     *
     * @return order - 排序
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * 设置排序
     *
     * @param order 排序
     */
    public void setOrder(Integer order) {
        this.order = order;
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
     * 获取来源
     *
     * @return source - 来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置来源
     *
     * @param source 来源
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
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
     * 获取阅读量
     *
     * @return count - 阅读量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置阅读量
     *
     * @param count 阅读量
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}