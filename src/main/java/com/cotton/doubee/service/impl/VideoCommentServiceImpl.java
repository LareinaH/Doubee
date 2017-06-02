package com.cotton.doubee.service.impl;

import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.doubee.mapper.VideoMapper;
import com.cotton.doubee.model.Video;
import com.cotton.doubee.model.VideoComment;
import com.cotton.doubee.service.VideoCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Administrator on 2017-05-10.
 */

@Service
@Transactional
public class VideoCommentServiceImpl extends BaseServiceImpl<VideoComment> implements VideoCommentService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public boolean addComment(VideoComment videoComment) {

        if(insert(videoComment)){
            Video video = videoMapper.selectByPrimaryKey(videoComment.getVideoId());

            if(video != null){

                video.setCommentCount(video.getCommentCount()+1);
                video.setUpdateAt(new Date());
                videoMapper.updateByPrimaryKeySelective(video);

                return true;
            }
        }


        return false;
    }
}