package com.cotton.doubee.web.controller;

import com.cotton.base.common.RestResponse;
import com.cotton.base.enumeration.Status;
import com.cotton.doubee.model.*;
import com.cotton.doubee.service.MemberFavoriteService;
import com.cotton.doubee.service.MemberRecordService;
import com.cotton.doubee.service.VideoCommentService;
import com.cotton.doubee.service.VideoService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Administrator on 2017-05-26.
 */

@Controller
@RequestMapping("/video")
public class VideoController {

    private Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoCommentService videoCommentService;
    @Autowired
    private MemberFavoriteService memberFavoriteService;
    @Autowired
    private MemberRecordService memberRecordService;


    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:

        return restResponse;

    }

    //视频列表（包含视频列表/手气不错/用户上传的列表/收藏的视频列表）
    @ResponseBody
    @RequestMapping(value = "/videos")
    public RestResponse<Map<String, Object>> videos(int pageNum, int pageSize, String type, String direction) {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:获取当前用户：
        Member member = new Member();
        member.setId(1L);

        Video model = new Video();
        PageInfo<Video> pageInfo = videoService.query(pageNum, pageSize, model);

        if (pageInfo == null) {
            restResponse.setMessage("读取视频列表失败！");

        } else {
            restResponse.setCode(RestResponse.OK);
            map.put("pageList", pageInfo);
        }

        return restResponse;

    }

    //视频评论
    @ResponseBody
    @RequestMapping(value = "/videoComments")
    public RestResponse<Map<String, Object>> videoComments(int pageNum, int pageSize, long videoId) {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //校验参数
        Video video = videoService.getById(videoId);
        if (video == null || !video.getStatus().equals(Status.normal.toString())) {
            restResponse.setMessage("无效的videoId");
            return restResponse;
        }

        //查询评论列表
        VideoComment model = new VideoComment();
        model.setStatus(Status.normal.toString());
        model.setVideoId(videoId);

        PageInfo<VideoComment> videoCommentPageInfo = videoCommentService.query(pageNum, pageSize, model);

        if (videoCommentPageInfo != null) {
            restResponse.setCode(RestResponse.OK);
            map.put("pageList", videoCommentPageInfo);
        } else {
            restResponse.setCode(RestResponse.ERROR);
            restResponse.setMessage("读取视频评论列表失败！");
        }

        return restResponse;

    }

    @ResponseBody
    @RequestMapping(value = "/addVideoComment")
    public RestResponse<Map<String, Object>> addVideoComment(long videoId, String commentText) {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //校验参数
        Video video = videoService.getById(videoId);
        if (video == null || !video.getStatus().equals(Status.normal.toString())) {
            restResponse.setMessage("无效的videoId");
            return restResponse;
        }

        //TODO:获取当前用户：
        Member member = new Member();
        member.setId(1L);

        VideoComment videoComment = new VideoComment();
        videoComment.setVideoId(videoId);
        videoComment.setCommentMemberId(member.getId());
        videoComment.setCommentText(commentText);
        videoComment.setCreatedAt(new Date());
        videoComment.setStatus(Status.normal.toString());

        if (videoCommentService.addComment(videoComment)) {
            map.put("videoCommentId", videoComment.getId());
            restResponse.setCode(RestResponse.OK);
        } else {
            restResponse.setMessage("添加视频评论失败!");
        }

        return restResponse;

    }

    /**
     * 视频/评论的操作
     * @param id 视频/评论id
     * @param type video/comment
     * @param operation 操作：（favourite/cancelFavourite/like/unlike/accuse）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/operation/{id}")
    public RestResponse<Map<String, Object>> operation(@PathVariable Long id,String type, String operation) {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();

        if(!(type.equals("video") || type.equals("videoComment"))){
            restResponse.setMessage("参数错误，无效的type");
            return restResponse;
        }

        if (StringUtils.isEmpty(operation) ||
                !(operation.equals("favourite") || operation.equals("cancelFavourite") ||
                        operation.equals("like") || operation.equals("unlike") || operation.equals("accuse"))) {

            restResponse.setMessage("参数错误，无效的 operation");
            return restResponse;

        }
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:获取当前用户：
        Member member = new Member();
        member.setId(1L);

        if (operation.equals("favourite") || operation.equals("cancelFavourite")) {

            if (videoFavouriteOperate(member.getId(), id, operation)) {
                restResponse.setCode(RestResponse.OK);
            } else {
                restResponse.setMessage( operation + "视频失败！");
            }


        } else if (operation.equals("like") || operation.equals("unlike") || operation.equals("accuse")) {

            if (videoRecordOperate(member.getId(), id,type, operation)) {
                restResponse.setCode(RestResponse.OK);
            } else {
                restResponse.setMessage(operation +" " + type + "操作失败！");
            }
        }

        return restResponse;
    }

    private boolean videoRecordOperate(long memberId, long id, String type, String operation) {

        //获取用户的记录视频信息
        MemberRecord model = new MemberRecord();
        model.setMemberId(memberId);
        model.setSelector(type);
        model.setOperation(operation);
        model.setStatus(Status.normal.toString());

        if(type.equals("video")) {
            model.setVideoId(id);
        }else if(type.equals("videoComment")){
            VideoComment videoComment = videoCommentService.getById(id);

            if(videoComment != null) {
                model.setVideoId(videoComment.getVideoId());
                model.setCommentId(id);
            }else {
                return false;
            }
        }else {
            return false;
        }

        List<MemberRecord> memberFavoriteList = memberRecordService.queryList(model);

        if (memberFavoriteList == null) {
            return false;

        }

        //如果已经存在了记录，不在添加
        if (!memberFavoriteList.isEmpty()) {
            return true;
        }

        //添加记录
        model.setCreatedAt(new Date());

        if (memberRecordService.addRecord(model)) {
            return true;
        }

        return false;
    }

    private boolean videoFavouriteOperate(long memberId, long videoId, String operation) {

        //获取用户的收藏视频信息
        MemberFavorite model = new MemberFavorite();
        model.setMemberId(memberId);
        model.setVideoId(videoId);
        model.setStatus(Status.normal.toString());

        List<MemberFavorite> memberFavoriteList = memberFavoriteService.queryList(model);

        if (memberFavoriteList == null) {
            return false;

        }

        if (operation.equals("favourite")) { //关注标签

            if (!memberFavoriteList.isEmpty()) { //如果已经关注
                return true;
            } else { //没有收藏过，或者收藏过取消了收藏，重新添加一条记录

                if (memberFavoriteService.insert(model)) {
                    return true;

                } else {
                    return false;
                }
            }

        } else if (operation.equals("cancelFavourite")) {

            if (!memberFavoriteList.isEmpty()) {
                for (MemberFavorite memberFavorite : memberFavoriteList) {
                    memberFavorite.setStatus(Status.cancel.toString());
                    memberFavoriteService.update(memberFavorite);
                }
            }
            return true;
        }
        return false;
    }

}