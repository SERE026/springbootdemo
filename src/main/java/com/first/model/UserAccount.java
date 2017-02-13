package com.first.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "user_account")
public class UserAccount {
    /**
     * 标识id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 客户编号
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 账户编号
     */
    @Column(name = "account_num")
    private String accountNum;

    /**
     * 账户类型（1.基本结算户 2.积分）
     */
    private Integer type;

    /**
     * 变更前余额
     */
    @Column(name = "before_amount")
    private BigDecimal beforeAmount;

    /**
     * 账户金额
     */
    private BigDecimal amount;

    /**
     * 状态(0.正常，1.冻结)
     */
    private Integer status;

    /**
     * 账户描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createtime;

    private Date updatetime;

    /**
     * 获取标识id
     *
     * @return id - 标识id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置标识id
     *
     * @param id 标识id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取客户编号
     *
     * @return user_id - 客户编号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置客户编号
     *
     * @param userId 客户编号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取账户编号
     *
     * @return account_num - 账户编号
     */
    public String getAccountNum() {
        return accountNum;
    }

    /**
     * 设置账户编号
     *
     * @param accountNum 账户编号
     */
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    /**
     * 获取账户类型（1.基本结算户 2.积分）
     *
     * @return type - 账户类型（1.基本结算户 2.积分）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置账户类型（1.基本结算户 2.积分）
     *
     * @param type 账户类型（1.基本结算户 2.积分）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取变更前余额
     *
     * @return before_amount - 变更前余额
     */
    public BigDecimal getBeforeAmount() {
        return beforeAmount;
    }

    /**
     * 设置变更前余额
     *
     * @param beforeAmount 变更前余额
     */
    public void setBeforeAmount(BigDecimal beforeAmount) {
        this.beforeAmount = beforeAmount;
    }

    /**
     * 获取账户金额
     *
     * @return amount - 账户金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置账户金额
     *
     * @param amount 账户金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取状态(0.正常，1.冻结)
     *
     * @return status - 状态(0.正常，1.冻结)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态(0.正常，1.冻结)
     *
     * @param status 状态(0.正常，1.冻结)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取账户描述
     *
     * @return description - 账户描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置账户描述
     *
     * @param description 账户描述
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
     * @return updatetime
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}