package com.cotton.doubee.service.impl;

import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.doubee.enumeration.RecordOperation;
import com.cotton.doubee.enumeration.RecordSelector;
import com.cotton.doubee.mapper.VideoCommentMapper;
import com.cotton.doubee.mapper.VideoMapper;
import com.cotton.doubee.model.MemberRecord;
import com.cotton.doubee.model.Video;
import com.cotton.doubee.model.VideoComment;
import com.cotton.doubee.service.MemberRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Administrator on 2017-05-10.
 */

@Service
@Transactional
public class MemberRecordServiceImpl extends BaseServiceImpl<MemberRecord> implements MemberRecordService {

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private VideoCommentMapper videoCommentMapper;

    @Override
    public boolean addRecord(MemberRecord memberRecord) {

        if(insert(memberRecord)){
            if(memberRecord.getSelector().equals(RecordSelector.video.toString())){
                Video video = videoMapper.selectByPrimaryKey(memberRecord.getVideoId());

                if(video != null){
                    if(memberRecord.getOperation().equals(RecordOperation.like.toString())){
                        video.setLikeCount(video.getLikeCount()+1);
                    }else if(memberRecord.getOperation().equals(RecordOperation.dislike.toString())){
                        video.setDislikeCount(video.getDislikeCount()+1);
                    }else if(memberRecord.getOperation().equals(RecordOperation.accuse.toString())){
                        //
                    }

                    video.setUpdateAt(new Date());
                    videoMapper.updateByPrimaryKeySelective(video);
                    return true;
                }
            }else if(memberRecord.getSelector().equals(RecordSelector.comment.toString())){
                VideoComment videoComment = videoCommentMapper.selectByPrimaryKey(memberRecord.getCommentId());

                if(videoComment != null){
                    if(memberRecord.getOperation().equals(RecordOperation.like.toString())){
                        videoComment.setLikeCount(videoComment.getLikeCount()+1);
                    }else if(memberRecord.getOperation().equals(RecordOperation.dislike.toString())){
                        videoComment.setDislikeCount(videoComment.getDislikeCount()+1);
                    }else if(memberRecord.getOperation().equals(RecordOperation.accuse.toString())){
                        //
                    }

                    videoComment.setUpdateAt(new Date());

                    videoCommentMapper.updateByPrimaryKeySelective(videoComment);

                    return true;
                }

            }
        }
        return false;
    }
}