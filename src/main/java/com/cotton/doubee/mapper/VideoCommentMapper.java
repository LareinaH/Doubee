package com.cotton.doubee.mapper;

import com.cotton.base.mapper.BaseMapper;
import com.cotton.doubee.model.VO.VideoCommentVO;
import com.cotton.doubee.model.VideoComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VideoCommentMapper extends BaseMapper<VideoComment> {

    List<VideoCommentVO> selectVO(@Param("map")Map<String, Object> condition);
}