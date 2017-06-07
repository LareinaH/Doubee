package com.cotton.doubee.model;

import com.cotton.base.model.BaseModel;
import java.util.Date;
import javax.persistence.*;
@Table(name = "video")
public class Video extends BaseModel {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 提供者id
     */
    private Long providerId;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subhead;

    /**
     * 视频长度
     */
    private Long length;

    /**
     * 首图地址
     */
    private String posterUrl;

    /**
     * 视频地址
     */
    private String url;

    /**
     * 视频来源
     */
    private String source;

    /**
     * 优酷videoId
     */
    private String videoId;

    /**
     * 优酷appId
     */
    private String appId;

    /**
     * 标签
     */
    private String tags;

    /**
     * 点赞数
     */
    private Long likeCount;

    /**
     * 点踩数
     */
    private Long dislikeCount;

    /**
     * 浏览数
     */
    private Long browseCount;

    /**
     * 评论数
     */
    private Long commentCount;

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
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取副标题
     *
     * @return subhead - 副标题
     */
    public String getSubhead() {
        return subhead;
    }

    /**
     * 设置副标题
     *
     * @param subhead 副标题
     */
    public void setSubhead(String subhead) {
        this.subhead = subhead == null ? null : subhead.trim();
    }

    /**
     * 获取视频长度
     *
     * @return length - 视频长度
     */
    public Long getLength() {
        return length;
    }

    /**
     * 设置视频长度
     *
     * @param length 视频长度
     */
    public void setLength(Long length) {
        this.length = length;
    }

    /**
     * 获取首图地址
     *
     * @return posterUrl - 首图地址
     */
    public String getPosterUrl() {
        return posterUrl;
    }

    /**
     * 设置首图地址
     *
     * @param posterUrl 首图地址
     */
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl == null ? null : posterUrl.trim();
    }

    /**
     * 获取视频地址
     *
     * @return url - 视频地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置视频地址
     *
     * @param url 视频地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取视频来源
     *
     * @return source - 视频来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置视频来源
     *
     * @param source 视频来源
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * 获取优酷videoId
     *
     * @return videoId - 优酷videoId
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * 设置优酷videoId
     *
     * @param videoId 优酷videoId
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId == null ? null : videoId.trim();
    }

    /**
     * 获取优酷appId
     *
     * @return appId - 优酷appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 设置优酷appId
     *
     * @param appId 优酷appId
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * 获取标签
     *
     * @return tags - 标签
     */
    public String getTags() {
        return tags;
    }

    /**
     * 设置标签
     *
     * @param tags 标签
     */
    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    /**
     * 获取点赞数
     *
     * @return likeCount - 点赞数
     */
    public Long getLikeCount() {
        return likeCount;
    }

    /**
     * 设置点赞数
     *
     * @param likeCount 点赞数
     */
    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * 获取点踩数
     *
     * @return dislikeCount - 点踩数
     */
    public Long getDislikeCount() {
        return dislikeCount;
    }

    /**
     * 设置点踩数
     *
     * @param dislikeCount 点踩数
     */
    public void setDislikeCount(Long dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    /**
     * 获取浏览数
     *
     * @return browseCount - 浏览数
     */
    public Long getBrowseCount() {
        return browseCount;
    }

    /**
     * 设置浏览数
     *
     * @param browseCount 浏览数
     */
    public void setBrowseCount(Long browseCount) {
        this.browseCount = browseCount;
    }

    /**
     * 获取评论数
     *
     * @return commentCount - 评论数
     */
    public Long getCommentCount() {
        return commentCount;
    }

    /**
     * 设置评论数
     *
     * @param commentCount 评论数
     */
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
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