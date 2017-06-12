package com.cotton.doubee.web.controller;

import com.cotton.base.common.RestResponse;
import com.cotton.base.controller.BaseController;
import com.cotton.base.enumeration.Status;
import com.cotton.doubee.enumeration.RecordOperation;
import com.cotton.doubee.enumeration.RecordSelector;
import com.cotton.doubee.model.*;
import com.cotton.doubee.model.VO.VideoCommentVO;
import com.cotton.doubee.model.VO.VideoVO;
import com.cotton.doubee.service.*;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by Administrator on 2017-05-26.
 */

@Controller
@RequestMapping("/video")
public class VideoController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoCommentService videoCommentService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberFavoriteService memberFavoriteService;
    @Autowired
    private MemberRecordService memberRecordService;
    @Autowired
    private MemberSubscriptionService memberSubscriptionService;

    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:

        return restResponse;

    }


    /**
     * 视频列表（包含视频列表/手气不错/用户上传的列表/收藏的视频列表）
     *
     * @param type      list-视频列表 goodluck-手气不错 upload-用户上传 favourite-收藏列表
     * @param direction up|down
     * @param start     起始位置
     * @param memberId  订阅的会员id
     * @param pageNum   当前页（从1开始）-仅在type=favourite|upload的时候有效
     * @param pageSize  页大小-仅在type=list|favourite|upload的时候有效
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/videos")
    public RestResponse<Map<String, Object>> videos(@RequestParam() String type,
                                                    @RequestParam(defaultValue = "up") String direction,
                                                    @RequestParam(defaultValue = "0") long start,
                                                    @RequestParam(defaultValue = "0") long memberId,
                                                    @RequestParam(defaultValue = "0") int pageNum,
                                                    @RequestParam(defaultValue = "0") int pageSize) {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();

        //校验参数
        if (!(type.equals("list") ||
                type.equals("goodluck") ||
                type.equals("upload") ||
                type.equals("favourite"))) {
            restResponse.setMessage("type 不能为空，并且只能是 list | goodluck|upload|favourite");
            return restResponse;
        }

        if (type.equals("list")) {

            if (!(direction.equals("up") | direction.equals("down"))) {
                restResponse.setMessage("direction 不能为空，并且只能是 up | down ");
                return restResponse;
            }

            if (pageSize <= 0) {
                restResponse.setMessage("pageSize 不能为空，并且大于0！");
                return restResponse;
            }
        }

        if (type.equals("upload") || type.equals("favourite")) {

            if (pageNum <= 0) {
                restResponse.setMessage("pageNum 不能为空，并且大于0！");
                return restResponse;
            }

            if (pageSize <= 0) {
                restResponse.setMessage("pageSize 不能为空，并且大于0！");
                return restResponse;
            }
        }

        //TODO:获取当前用户：
        Member member = new Member();
        member.setId(1L);

        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);
        ;
        if (type.equals("list")) {

            //构建查询条件
            Map<String,Object> condition = new HashMap<String, Object>();

            if (direction.equals("up")) {
                condition.put("id_greaterThan",start);
                condition.put("orderBy","a.id ASC");
            } else {
                if(start != 0) {
                    condition.put("id_lessThan", start);
                }
                condition.put("orderBy","a.id DESC");
            }
            condition.put("status", Status.normal.toString());

            PageInfo<VideoVO> pageInfo = videoService.queryVO(1, pageSize, condition);

            if (pageInfo == null) {
                restResponse.setMessage("读取视频列表失败！");

            } else {

                if(pageInfo.getList().size() < pageSize){
                    PageInfo<VideoVO> extPageInfo;
                    if(direction.equals("down")){
                        condition.remove("id_lessThan");
                        extPageInfo = videoService.queryVO(1, pageSize-pageInfo.getList().size(), condition);

                    }else {
                        extPageInfo = videoService.goodLuck(pageSize-pageInfo.getList().size());
                    }
                    if(extPageInfo != null && extPageInfo.getList()!= null){
                        pageInfo.getList().addAll(extPageInfo.getList());
                    }
                }
                buildExInfo4VideoVOList(pageInfo.getList(),memberId);
                map.put("pageList", pageInfo);
                restResponse.setCode(RestResponse.OK);
            }

        } else if (type.equals("goodluck")) {

            PageInfo<VideoVO> videoVOPageInfo = videoService.goodLuck(5);

            if (videoVOPageInfo == null) {
                restResponse.setMessage("读取视频列表失败！");

            } else {
                buildExInfo4VideoVOList(videoVOPageInfo.getList(),memberId);
                map.put("pageList", videoVOPageInfo);
                restResponse.setCode(RestResponse.OK);
            }

        } else if (type.equals("upload")) {

            //查找上传的用户是否存在
            Member uploadMember = memberService.getById(memberId);

            if (uploadMember == null || !uploadMember.getStatus().equals(Status.normal.toString())) {
                restResponse.setMessage("id为：" + memberId + "的用户不存在！");
                return restResponse;
            }


            //构建查询条件
            Map<String,Object> condition = new HashMap<String, Object>();

            condition.put("memberId", memberId);
            condition.put("status", Status.normal.toString());
            condition.put("orderBy","a.id desc");

            PageInfo<VideoVO> pageInfo = videoService.queryVO(pageNum, pageSize, condition);

            if (pageInfo == null) {
                restResponse.setMessage("读取视频列表失败！");

            } else {
                buildExInfo4VideoVOList(pageInfo.getList(),memberId);
                map.put("pageList", pageInfo);
                restResponse.setCode(RestResponse.OK);
            }

        } else if (type.equals("favourite")) {

            //获取我喜欢的列表
            PageInfo<VideoVO> videoVOPageInfo = videoService.myFavourite(pageNum,pageSize,member.getId());

            if (videoVOPageInfo == null) {
                restResponse.setMessage("读取视频列表失败！");

            } else {
                buildExInfo4VideoVOList(videoVOPageInfo.getList(),memberId);
                map.put("pageList", videoVOPageInfo);
                restResponse.setCode(RestResponse.OK);

            }

        }

        return restResponse;

    }

    /**
     * 视频评论
     *
     * @param type     latest-选取最热的五条（和一条当前用户的） list-分页显示
     * @param videoId  视频id
     * @param pageNum  当前页（从1开始）-仅在type=list的时候有效
     * @param pageSize 页大小-仅在type=list的时候有效
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/videoComments")
    public RestResponse<Map<String, Object>> videoComments(@RequestParam(required = true) String type,
                                                           @RequestParam(required = true) long videoId,
                                                           @RequestParam(defaultValue = "0") int pageNum,
                                                           @RequestParam(defaultValue = "0") int pageSize) {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();

        if (StringUtils.isEmpty(type) || !(type.equals("latest") || type.equals("list"))) {
            restResponse.setMessage("type 不能为空，并且只能是 latest | list");
            return restResponse;
        }

        if (type.equals("list")) {

            if (pageNum == 0 || pageSize == 0) {
                restResponse.setMessage("pageNum 和pageSize 不能为空");
                return restResponse;
            }

        } else {
            pageNum = 1;
            pageSize = 5;
        }

        //校验参数
        Video video = videoService.getById(videoId);
        if (video == null || !video.getStatus().equals(Status.normal.toString())) {
            restResponse.setMessage("无效的videoId");
            return restResponse;
        }

        //TODO:获取当前用户：
        Member member = new Member();
        member.setId(1L);


        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //查询评论列表
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("videoId", videoId);
        condition.put("status", Status.normal.toString());

        if (type.equals("list")) {
            condition.put("orderBy", "a.createdAt DESC");
        } else {
            condition.put("orderBy", "a.likeCount DESC, a.createdAt DESC");
        }

        PageInfo<VideoCommentVO> videoCommentPageInfo = videoCommentService.queryVO(pageNum, pageSize, condition);

        if (videoCommentPageInfo != null && videoCommentPageInfo.getList() != null) {

            //取出一条当前用户的一条最新的视频评论
            if (type.equals("latest")) {

                pageNum = 1;
                pageSize = 1;
                condition.put("commentMemberId", member.getId());
                condition.put("orderBy", "a.createdAt DESC");

                List<Long> excludeCommentIdList = new LinkedList<Long>();

                for (VideoCommentVO videoCommentVO : videoCommentPageInfo.getList()) {
                    excludeCommentIdList.add(videoCommentVO.getId());

                }
                condition.put("excludeCommentIdList", excludeCommentIdList);

                PageInfo<VideoCommentVO> currentUserCommentPageInfo = videoCommentService.queryVO(pageNum, pageSize, condition);

                if (currentUserCommentPageInfo != null
                        && currentUserCommentPageInfo.getList() != null
                        && !currentUserCommentPageInfo.getList().isEmpty()) {
                    videoCommentPageInfo.getList().add(currentUserCommentPageInfo.getList().get(0));
                }
            }

            restResponse.setCode(RestResponse.OK);
            buildExInfo4VideoCommentVOList(videoCommentPageInfo.getList(),member.getId());
            map.put("pageList", videoCommentPageInfo);
        } else {
            restResponse.setCode(RestResponse.ERROR);
            restResponse.setMessage("读取视频评论列表失败！");
        }

        return restResponse;

    }

    /**
     * 添加评论
     *
     * @param videoId     视频id
     * @param commentText 评论内容
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addVideoComment")
    public RestResponse<Map<String, Object>> addVideoComment(@RequestParam(required = true) long videoId,
                                                             @RequestParam(required = true) String commentText) {

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
     *
     * @param id        视频/评论id
     * @param type      video/comment
     * @param operation 操作：（favourite/cancelFavourite/like/unlike/accuse）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/operation/{id}")
    public RestResponse<Map<String, Object>> operation(@PathVariable Long id,
                                                       @RequestParam(required = true) String type,
                                                       @RequestParam(required = true) String operation) {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();

        //参数校验
        if (!(type.equals("video") || type.equals("videoComment"))) {
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

        //收藏/取消收藏
        if (operation.equals("favourite") || operation.equals("cancelFavourite")) {

            if (videoFavouriteOperate(member.getId(), id, operation)) {
                restResponse.setCode(RestResponse.OK);
            } else {
                restResponse.setMessage(operation + "视频失败！");
            }


        } else if (operation.equals("like") || operation.equals("unlike") || operation.equals("accuse")) {

            if (videoRecordOperate(member.getId(), id, type, operation)) {
                restResponse.setCode(RestResponse.OK);
            } else {
                restResponse.setMessage(operation + " " + type + "操作失败！");
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

        if (type.equals("video")) {
            model.setVideoId(id);
        } else if (type.equals("videoComment")) {
            VideoComment videoComment = videoCommentService.getById(id);

            if (videoComment != null) {
                model.setVideoId(videoComment.getVideoId());
                model.setCommentId(id);
            } else {
                return false;
            }
        } else {
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

    private void  buildExInfo4VideoVOList(List<VideoVO> videoVOList,long memberId){
        if(videoVOList!= null && !videoVOList.isEmpty()){

            for (VideoVO videoVO: videoVOList) {

                buildExInfo4VideoVO(videoVO,memberId);
            }
        }
    }

    private void buildExInfo4VideoVO(VideoVO videoVO, long memberId){

        //查看用户是否点赞过
        MemberRecord memberRecord = new MemberRecord();
        memberRecord.setVideoId(videoVO.getId());
        memberRecord.setMemberId(memberId);
        memberRecord.setSelector(RecordSelector.video.toString());
        memberRecord.setStatus(Status.normal.toString());

        List<MemberRecord> memberRecordList = memberRecordService.queryList(memberRecord);

        if(memberRecordList != null  || !memberRecordList.isEmpty()){

            for(MemberRecord memberRecord1 : memberRecordList){
                if(memberRecord1.getOperation().equals(RecordOperation.like.toString())){
                    videoVO.setbLike(true);
                }else if(memberRecord1.getOperation().equals(RecordOperation.dislike.toString())){
                    videoVO.setbDislike(true);
                }
            }

        }
        //查看用户是否订阅了该作者
        MemberSubscription memberSubscription = new MemberSubscription();
        memberSubscription.setMemberId(memberId);
        memberSubscription.setPublisherId(videoVO.getMemberId());
        memberSubscription.setStatus(Status.normal.toString());

        List<MemberSubscription> memberSubscriptionList = memberSubscriptionService.queryList(memberSubscription);

        if(memberSubscriptionList != null || !memberSubscriptionList.isEmpty()){
            videoVO.setbSubscribed(true);
        }
    }


    private void  buildExInfo4VideoCommentVOList(List<VideoCommentVO> videoCommentVOList,long memberId){
        if(videoCommentVOList!= null && !videoCommentVOList.isEmpty()){

            for (VideoCommentVO videoCommentVO: videoCommentVOList) {

                buildExInfo4VideoCommentVO(videoCommentVO,memberId);
            }
        }
    }


    private void buildExInfo4VideoCommentVO(VideoCommentVO videoCommentVO, long memberId){

        MemberRecord memberRecord = new MemberRecord();
        memberRecord.setVideoId(videoCommentVO.getVideoId());
        memberRecord.setCommentId(videoCommentVO.getId());
        memberRecord.setMemberId(memberId);
        memberRecord.setSelector(RecordSelector.comment.toString());
        memberRecord.setStatus(Status.normal.toString());

        List<MemberRecord> memberRecordList = memberRecordService.queryList(memberRecord);

        if(memberRecordList != null  || !memberRecordList.isEmpty()){

            for(MemberRecord memberRecord1 : memberRecordList){
                if(memberRecord1.getOperation().equals(RecordOperation.like.toString())){
                    videoCommentVO.setbLike(true);
                }else if(memberRecord1.getOperation().equals(RecordOperation.dislike.toString())){
                    videoCommentVO.setbDislike(true);
                }
            }

        }

    }
}