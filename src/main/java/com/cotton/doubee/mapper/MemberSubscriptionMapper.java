package com.cotton.doubee.mapper;

import com.cotton.base.mapper.BaseMapper;
import com.cotton.doubee.model.MemberSubscription;
import com.cotton.doubee.model.VO.MemberSubscriptionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MemberSubscriptionMapper extends BaseMapper<MemberSubscription> {

    List<MemberSubscriptionVO> selectVO(@Param("map")Map<String, Object> condition);

}