package com.cotton.doubee.model;

import com.cotton.base.model.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "video_comment")
public class VideoComment extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联的视频Id
     */
    private Long videoId;

    /**
     * 父评论的Id
     */
    private Long parentId;

    /**
     * 评论用户
     */
    private Long commentMemberId;

    /**
     * 状态【normal:正常 delete 删除】
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
     * 评论内容
     */
    private String commentText;

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
     * 获取关联的视频Id
     *
     * @return videoId - 关联的视频Id
     */
    public Long getVideoId() {
        return videoId;
    }

    /**
     * 设置关联的视频Id
     *
     * @param videoId 关联的视频Id
     */
    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取父评论的Id
     *
     * @return parentId - 父评论的Id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父评论的Id
     *
     * @param parentId 父评论的Id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取评论用户
     *
     * @return commentMemberId - 评论用户
     */
    public Long getCommentMemberId() {
        return commentMemberId;
    }

    /**
     * 设置评论用户
     *
     * @param commentMemberId 评论用户
     */
    public void setCommentMemberId(Long commentMemberId) {
        this.commentMemberId = commentMemberId;
    }

    /**
     * 获取状态【normal:正常 delete 删除】
     *
     * @return status - 状态【normal:正常 delete 删除】
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态【normal:正常 delete 删除】
     *
     * @param status 状态【normal:正常 delete 删除】
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

    /**
     * 获取评论内容
     *
     * @return commentText - 评论内容
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     * 设置评论内容
     *
     * @param commentText 评论内容
     */
    public void setCommentText(String commentText) {
        this.commentText = commentText == null ? null : commentText.trim();
    }
}