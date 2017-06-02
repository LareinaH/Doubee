package com.cotton.doubee.service;

import com.cotton.base.service.BaseService;
import com.cotton.doubee.model.MemberRecord;

/**
 * Created by Administrator on 2017-05-10.
 */

public interface MemberRecordService extends BaseService<MemberRecord> {

    boolean addRecord(MemberRecord memberRecord);
}