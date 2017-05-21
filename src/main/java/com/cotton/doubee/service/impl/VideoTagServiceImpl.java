package com.cotton.doubee.service.impl;

import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.doubee.model.VideoTag;
import com.cotton.doubee.service.VideoTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017-05-10.
 */

@Service
@Transactional
public class VideoTagServiceImpl extends BaseServiceImpl<VideoTag> implements VideoTagService {
}