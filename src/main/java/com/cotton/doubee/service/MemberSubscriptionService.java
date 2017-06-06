package com.cotton.doubee.service;

import com.cotton.base.service.BaseService;
import com.cotton.doubee.model.MemberSubscription;
import com.cotton.doubee.model.VO.MemberSubscriptionVO;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Created by Administrator on 2017-05-10.
 */

public interface MemberSubscriptionService extends BaseService<MemberSubscription> {

    PageInfo<MemberSubscriptionVO> queryVO(int pageNum, int pageSize, Map<String,Object> condition);
}

