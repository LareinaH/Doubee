package com.cotton.doubee.model.VO;

import com.cotton.doubee.model.VideoTag;

/**
 * Created by Administrator on 2017-05-26.
 */
public class VideoTagVO {

    private Long id;

    /**
     * 标签名字
     */
    private String name;

    /**
     * 是否关注
     */
    private boolean bLike;

    public VideoTagVO(VideoTag videoTag) {
        this.id = videoTag.getId();
        this.name = videoTag.getName();
        this.bLike = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isbLike() {
        return bLike;
    }

    public void setbLike(boolean bLike) {
        this.bLike = bLike;
    }
}
