package com.cotton.doubee.model;

import com.cotton.base.model.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "member_favorite")
public class MemberFavorite extends BaseModel {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联的会员id
     */
    private Long memberId;

    /**
     * 视频Id
     */
    private Long videoId;

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
     * 获取ID
     *
     * @return id - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
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
     * 获取视频Id
     *
     * @return videoId - 视频Id
     */
    public Long getVideoId() {
        return videoId;
    }

    /**
     * 设置视频Id
     *
     * @param videoId 视频Id
     */
    public void setVideoId(Long videoId) {
        this.videoId = videoId;
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