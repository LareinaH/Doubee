package com.cotton.doubee.service.impl;

import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.doubee.model.VideoComment;
import com.cotton.doubee.service.VideoCommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017-05-10.
 */

@Service
@Transactional
public class VideoCommentServiceImpl extends BaseServiceImpl<VideoComment> implements VideoCommentService {
}