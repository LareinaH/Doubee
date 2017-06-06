package com.cotton.doubee.service.impl;

import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.doubee.mapper.MemberSubscriptionMapper;
import com.cotton.doubee.model.MemberSubscription;
import com.cotton.doubee.model.VO.MemberSubscriptionVO;
import com.cotton.doubee.service.MemberSubscriptionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Administrator on 2017-05-10.
 */

@Service
@Transactional
public class MemberSubscriptionServiceImpl extends BaseServiceImpl<MemberSubscription> implements MemberSubscriptionService {

    @Autowired
    private MemberSubscriptionMapper memberSubscriptionMapper;

    @Override
    public PageInfo<MemberSubscriptionVO> queryVO(int pageNum, int pageSize, Map<String, Object> condition) {
        if (pageSize > 0) {
            PageHelper.startPage(pageNum, pageSize);
        }
        return new PageInfo<MemberSubscriptionVO>(memberSubscriptionMapper.selectVO(condition));
    }
}