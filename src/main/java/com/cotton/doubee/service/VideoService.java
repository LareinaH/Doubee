package com.cotton.doubee.service;

import com.cotton.base.service.BaseService;
import com.cotton.doubee.model.VO.VideoCommentVO;
import com.cotton.doubee.model.VO.VideoVO;
import com.cotton.doubee.model.Video;
import com.github.pagehelper.PageInfo;

/**
 * Created by Administrator on 2017-05-10.
 */

public interface VideoService extends BaseService<Video> {

    PageInfo<VideoVO> goodLuck(int pageSize);
}