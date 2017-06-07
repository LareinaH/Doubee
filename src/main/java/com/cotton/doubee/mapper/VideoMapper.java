package com.cotton.doubee.mapper;

import com.cotton.base.mapper.BaseMapper;
import com.cotton.doubee.model.VO.VideoVO;
import com.cotton.doubee.model.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VideoMapper extends BaseMapper<Video> {

    List<VideoVO> goodLuck();

    List<VideoVO> myFavourite(@Param("memberId") long memberId);

    List<VideoVO> selectVO(@Param("map")Map<String,Object> map);

}