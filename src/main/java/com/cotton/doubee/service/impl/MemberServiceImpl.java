package com.cotton.doubee.service.impl;

import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.doubee.model.Member;

import com.cotton.doubee.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017-05-10.
 */

@Service
@Transactional
public class MemberServiceImpl extends BaseServiceImpl<Member> implements MemberService {

}