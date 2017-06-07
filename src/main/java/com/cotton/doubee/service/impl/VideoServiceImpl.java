package com.cotton.doubee.service.impl;

import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.doubee.mapper.VideoMapper;
import com.cotton.doubee.model.VO.MemberSubscriptionVO;
import com.cotton.doubee.model.VO.VideoVO;
import com.cotton.doubee.model.Video;
import com.cotton.doubee.service.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017-05-10.
 */

@Service
@Transactional
public class VideoServiceImpl extends BaseServiceImpl<Video> implements VideoService {

    @Autowired
    private VideoMapper videoMapper;
    @Override
    public PageInfo<VideoVO> goodLuck(int pageSize) {

        if (pageSize > 0) {
            PageHelper.startPage(1, pageSize);
        }
        return new PageInfo<VideoVO>(videoMapper.goodLuck());
    }
}