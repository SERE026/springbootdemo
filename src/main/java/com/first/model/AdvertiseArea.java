package com.first.model;

import javax.persistence.*;

@Table(name = "advertise_area")
public class AdvertiseArea {
    /**
     * 区域ID号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 区域状态
     */
    private Integer status;

    /**
     * 区域描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Integer createtime;

    /**
     * 更新时间
     */
    private Integer updatetime;

    /**
     * 获取区域ID号
     *
     * @return id - 区域ID号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置区域ID号
     *
     * @param id 区域ID号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取区域名称
     *
     * @return name - 区域名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置区域名称
     *
     * @param name 区域名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取区域状态
     *
     * @return status - 区域状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置区域状态
     *
     * @param status 区域状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取区域描述
     *
     * @return description - 区域描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置区域描述
     *
     * @param description 区域描述
     */
    public void setDescription(String description) {
        this.description = description;
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