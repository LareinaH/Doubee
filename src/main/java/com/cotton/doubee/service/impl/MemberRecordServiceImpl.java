package com.cotton.doubee.service.impl;

import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.doubee.model.MemberRecord;
import com.cotton.doubee.service.MemberRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017-05-10.
 */

@Service
@Transactional
public class MemberRecordServiceImpl extends BaseServiceImpl<MemberRecord> implements MemberRecordService {
}