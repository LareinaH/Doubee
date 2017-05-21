package com.cotton.doubee.model;

import com.cotton.base.model.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "member_setting")
public class MemberSetting extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联的会员id
     */
    private Long memberId;

    /**
     * 是否分享过（yes|no）
     */
    private String shared;

    /**
     * 是否自动播放（yes|no）
     */
    private String autoPlay;

    /**
     * 状态【normal-正常，delete-删除】
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
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
     * 获取关联的会员id
     *
     * @return memberId - 关联的会员id
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * 设置关联的会员id
     *
     * @param memberId 关联的会员id
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取是否分享过（yes|no）
     *
     * @return shared - 是否分享过（yes|no）
     */
    public String getShared() {
        return shared;
    }

    /**
     * 设置是否分享过（yes|no）
     *
     * @param shared 是否分享过（yes|no）
     */
    public void setShared(String shared) {
        this.shared = shared == null ? null : shared.trim();
    }

    /**
     * 获取是否自动播放（yes|no）
     *
     * @return autoPlay - 是否自动播放（yes|no）
     */
    public String getAutoPlay() {
        return autoPlay;
    }

    /**
     * 设置是否自动播放（yes|no）
     *
     * @param autoPlay 是否自动播放（yes|no）
     */
    public void setAutoPlay(String autoPlay) {
        this.autoPlay = autoPlay == null ? null : autoPlay.trim();
    }

    /**
     * 获取状态【normal-正常，delete-删除】
     *
     * @return status - 状态【normal-正常，delete-删除】
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态【normal-正常，delete-删除】
     *
     * @param status 状态【normal-正常，delete-删除】
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
     * 获取更新时间
     *
     * @return updateAt - 更新时间
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * 设置更新时间
     *
     * @param updateAt 更新时间
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}