package com.cotton.doubee.model.VO;

/**
 * Created by Administrator on 2017-06-02.
 */
public class MemberBaseVO {

    /**
     * ID
     */
    private Long id;

    /**
     * 是否是视频提供者 （0-不是,1-是）
     */
    private Integer bProvider;

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


    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getbProvider() {
        return bProvider;
    }

    public void setbProvider(Integer bProvider) {
        this.bProvider = bProvider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
