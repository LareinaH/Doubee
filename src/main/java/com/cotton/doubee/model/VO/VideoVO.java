package com.cotton.doubee.model.VO;

import java.util.Date;

/**
 * Created by Administrator on 2017-06-07.
 */
public class VideoVO {

    private Long id;

    /**
     * 提供者id
     */
    private Long memberId;

    /**
     * 提供者
     */
    private String providerName;

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
     * 创建时间
     */
    private Date createdAt;

    /**
     * 用户是否点赞过
     */
    boolean bLike;

    /**
     * 用户是否点踩过
     */
    boolean bDislike;

    /**
     * 用户是否订阅该作者
     * @return
     */
    boolean bSubscribed;

    public boolean isbSubscribed() {
        return bSubscribed;
    }

    public void setbSubscribed(boolean bSubscribed) {
        this.bSubscribed = bSubscribed;
    }

    public boolean isbLike() {
        return bLike;
    }

    public void setbLike(boolean bLike) {
        this.bLike = bLike;
    }

    public boolean isbDislike() {
        return bDislike;
    }

    public void setbDislike(boolean bDislike) {
        this.bDislike = bDislike;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Long dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public Long getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(Long browseCount) {
        this.browseCount = browseCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
