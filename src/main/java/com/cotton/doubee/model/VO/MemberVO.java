package com.cotton.doubee.model.VO;

/**
 * Created by Administrator on 2017-06-02.
 */
public class MemberVO extends MemberBaseVO{

    public MemberVO() {
        this.shared = "no";
        this.autoPlay = "no";
    }

    /**
     * 是否分享过（yes|no）
     */
    private String shared;

    /**
     * 是否自动播放（yes|no）
     */
    private String autoPlay;


    public String getShared() {
        return shared;
    }

    public void setShared(String shared) {
        this.shared = shared;
    }

    public String getAutoPlay() {
        return autoPlay;
    }

    public void setAutoPlay(String autoPlay) {
        this.autoPlay = autoPlay;
    }
}
