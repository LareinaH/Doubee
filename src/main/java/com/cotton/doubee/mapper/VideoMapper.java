package com.cotton.doubee.mapper;

import com.cotton.base.mapper.BaseMapper;
import com.cotton.doubee.model.VO.VideoVO;
import com.cotton.doubee.model.Video;

import java.util.List;

public interface VideoMapper extends BaseMapper<Video> {

    List<VideoVO> goodLuck();
}