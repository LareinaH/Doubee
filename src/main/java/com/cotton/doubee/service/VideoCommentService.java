package com.cotton.doubee.service;

import com.cotton.base.service.BaseService;
import com.cotton.doubee.model.VO.VideoCommentVO;
import com.cotton.doubee.model.VideoComment;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Created by Administrator on 2017-05-10.
 */

public interface VideoCommentService extends BaseService<VideoComment> {

    boolean addComment(VideoComment videoComment);

    PageInfo<VideoCommentVO> queryVO(int pageNum, int pageSize, Map<String,Object> condition);
}