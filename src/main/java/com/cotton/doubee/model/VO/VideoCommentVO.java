package com.cotton.doubee.model.VO;

/**
 * Created by Administrator on 2017-06-06.
 */
public class VideoCommentVO {

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
     * 姓名
     */
    private String commentMemberName;


    /**
     * 头像
     */
    private String commentMemberHeadPortrait;


    /**
     * 评论内容
     */
    private String commentText;


    /**
     * 点赞数
     */
    private Long likeCount;

    /**
     * 点踩的个数
     */
    private Long dislikeCount;


    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getCommentMemberId() {
        return commentMemberId;
    }

    public void setCommentMemberId(Long commentMemberId) {
        this.commentMemberId = commentMemberId;
    }

    public String getCommentMemberName() {
        return commentMemberName;
    }

    public void setCommentMemberName(String commentMemberName) {
        this.commentMemberName = commentMemberName;
    }

    public String getCommentMemberHeadPortrait() {
        return commentMemberHeadPortrait;
    }

    public void setCommentMemberHeadPortrait(String commentMemberHeadPortrait) {
        this.commentMemberHeadPortrait = commentMemberHeadPortrait;
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
}
