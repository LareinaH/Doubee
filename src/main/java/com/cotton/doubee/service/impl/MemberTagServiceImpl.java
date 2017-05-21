package com.cotton.doubee.service.impl;

import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.doubee.model.MemberTag;
import com.cotton.doubee.service.MemberTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017-05-10.
 */

@Service
@Transactional
public class MemberTagServiceImpl extends BaseServiceImpl<MemberTag> implements MemberTagService {
}