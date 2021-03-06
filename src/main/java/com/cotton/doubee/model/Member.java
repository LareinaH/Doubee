package com.cotton.doubee.model;

import com.cotton.base.model.BaseModel;
import java.util.Date;
import javax.persistence.*;
@Table(name = "member")
public class Member extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 凭证
     */
    private String ticket;

    /**
     * 是否是视频提供者 （0-不是,1-是）
     */
    private Integer bProvider;

    /**
     * 提供者id
     */
    private Long providerId;

    /**
     * 微信标识
     */
    private String openId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 描述
     */
    private String discription;

    /**
     * 电话
     */
    private String cellphone;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别(boy|girl|unknown）
     */
    private String sex;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 被订阅数
     */
    private Integer subscribedCount;

    /**
     * 会员状态【normal-正常,cancle-注销,delete-删除】
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updateAt;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取凭证
     *
     * @return ticket - 凭证
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * 设置凭证
     *
     * @param ticket 凭证
     */
    public void setTicket(String ticket) {
        this.ticket = ticket == null ? null : ticket.trim();
    }

    /**
     * 获取是否是视频提供者 （0-不是,1-是）
     *
     * @return bProvider - 是否是视频提供者 （0-不是,1-是）
     */
    public Integer getbProvider() {
        return bProvider;
    }

    /**
     * 设置是否是视频提供者 （0-不是,1-是）
     *
     * @param bProvider 是否是视频提供者 （0-不是,1-是）
     */
    public void setbProvider(Integer bProvider) {
        this.bProvider = bProvider;
    }

    /**
     * 获取提供者id
     *
     * @return providerId - 提供者id
     */
    public Long getProviderId() {
        return providerId;
    }

    /**
     * 设置提供者id
     *
     * @param providerId 提供者id
     */
    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    /**
     * 获取微信标识
     *
     * @return openId - 微信标识
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置微信标识
     *
     * @param openId 微信标识
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取描述
     *
     * @return discription - 描述
     */
    public String getDiscription() {
        return discription;
    }

    /**
     * 设置描述
     *
     * @param discription 描述
     */
    public void setDiscription(String discription) {
        this.discription = discription == null ? null : discription.trim();
    }

    /**
     * 获取电话
     *
     * @return cellphone - 电话
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * 设置电话
     *
     * @param cellphone 电话
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取性别(boy|girl|unknown）
     *
     * @return sex - 性别(boy|girl|unknown）
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别(boy|girl|unknown）
     *
     * @param sex 性别(boy|girl|unknown）
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 获取头像
     *
     * @return headPortrait - 头像
     */
    public String getHeadPortrait() {
        return headPortrait;
    }

    /**
     * 设置头像
     *
     * @param headPortrait 头像
     */
    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait == null ? null : headPortrait.trim();
    }

    /**
     * 获取被订阅数
     *
     * @return subscribedCount - 被订阅数
     */
    public Integer getSubscribedCount() {
        return subscribedCount;
    }

    /**
     * 设置被订阅数
     *
     * @param subscribedCount 被订阅数
     */
    public void setSubscribedCount(Integer subscribedCount) {
        this.subscribedCount = subscribedCount;
    }

    /**
     * 获取会员状态【normal-正常,cancle-注销,delete-删除】
     *
     * @return status - 会员状态【normal-正常,cancle-注销,delete-删除】
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置会员状态【normal-正常,cancle-注销,delete-删除】
     *
     * @param status 会员状态【normal-正常,cancle-注销,delete-删除】
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取创建时间
     *
     * @return createdAt - 创建时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置创建时间
     *
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取修改时间
     *
     * @return updateAt - 修改时间
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * 设置修改时间
     *
     * @param updateAt 修改时间
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}