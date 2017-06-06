package com.cotton.doubee.model.VO;

/**
 * Created by Administrator on 2017-06-06.
 */
public class MemberSubscriptionVO {

    /**
     * 关联的会员id
     */
    private Long memberId;

    /**
     * 被订阅者Id
     */
    private Long publisherId;


    /**
     * 姓名
     */
    private String name;

    /**
     * 描述
     */
    private String discription;


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


    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Integer getSubscribedCount() {
        return subscribedCount;
    }

    public void setSubscribedCount(Integer subscribedCount) {
        this.subscribedCount = subscribedCount;
    }
}
