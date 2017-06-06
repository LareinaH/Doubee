package com.cotton.doubee.web.controller;

import com.cotton.base.common.RestResponse;
import com.cotton.base.enumeration.Status;
import com.cotton.doubee.model.*;
import com.cotton.doubee.model.VO.MemberSubscriptionVO;
import com.cotton.doubee.model.VO.MemberVO;
import com.cotton.doubee.model.VO.VideoTagVO;
import com.cotton.doubee.service.*;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/member")
public class MemberController {

    private Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;
    @Autowired
    private VideoTagService videoTagService;
    @Autowired
    private MemberTagService memberTagService;
    @Autowired
    private MemberSubscriptionService memberSubscriptionService;
    @Autowired
    private MemberSettingService memberSettingService;

    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:
        restResponse.setCode(RestResponse.OK);
        return restResponse;

    }

    /**
     * 会员信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/memberInfo")
    public RestResponse<Map<String, Object>> memberInfo() {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        Member member = memberService.getById(1L);

        MemberVO memberVO = new MemberVO();
        BeanUtils.copyProperties(member, memberVO);

        MemberSetting model = new MemberSetting();
        model.setMemberId(member.getId());
        model.setStatus(Status.normal.toString());

        List<MemberSetting> memberSettingList = memberSettingService.queryList(model);

        if(memberSettingList != null && !memberSettingList.isEmpty()) {
            memberVO.setAutoPlay(memberSettingList.get(0).getAutoPlay());
            memberVO.setShared(memberSettingList.get(0).getShared());
        }

        map.put("member", memberVO);

        restResponse.setCode(RestResponse.OK);
        return restResponse;
    }

    /**
     * 我的标签（爱好）
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/videoTags")
    public RestResponse<Map<String, Object>> videoTags() {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:获取当前用户：
        Member member = new Member();
        member.setId(1L);
        //获取全部tags
        VideoTag model = new VideoTag();
        model.setStatus(Status.normal.toString());
        List<VideoTag> videoTagList = videoTagService.queryList(model);

        //获取我关注的tags
        MemberTag model1 = new MemberTag();
        model1.setMemberId(member.getId());
        model1.setStatus(Status.normal.toString());
        List<MemberTag> memberTagList = memberTagService.queryList(model1);

        //遍历我是否关注了该tag
        List<Long> tagIds = new LinkedList<Long>();

        for (MemberTag tag : memberTagList) {
            tagIds.add(tag.getTagId());
        }

        List<VideoTagVO> videoTagVOList = new ArrayList<VideoTagVO>();

        for (VideoTag videoTag : videoTagList) {

            VideoTagVO videoTagVO = new VideoTagVO(videoTag);

            if (tagIds.contains(videoTag.getId())) {
                videoTagVO.setbLike(true);
            }
            videoTagVOList.add(videoTagVO);
        }

        map.put("tags", videoTagVOList);
        restResponse.setCode(RestResponse.OK);

        return restResponse;
    }

    /**
     * 用户设置标签
     *
     * @param tagId      标签id
     * @param likeStatus like-喜欢，cancel-取消喜欢
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editVideoTag")
    public RestResponse<Map<String, Object>> editVideoTags(Long tagId,
                                                           String likeStatus) {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();

        //校验参数
        if (!likeStatus.equals("like") && !likeStatus.equals("cancel")) {
            restResponse.setMessage("likeStatus 只能是 like|cancel！");
            return restResponse;
        }


        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:获取当前用户：
        Member member = new Member();
        member.setId(1L);

        //查看用户之前是否有like操作

        MemberTag model = new MemberTag();
        model.setStatus("normal");
        model.setMemberId(member.getId());
        model.setTagId(tagId);

        List<MemberTag> memberTagList = memberTagService.queryList(model);

        if (memberTagList == null) {
            restResponse.setMessage("查询会员标签异常！");
            return restResponse;
        }

        if (likeStatus.equals("like")) { //关注标签

            if (!memberTagList.isEmpty()) { //如果已经关注
                restResponse.setCode(RestResponse.OK);
                map.put("memberTagId", memberTagList.get(0).getId());
                return restResponse;
            } else { //没有关注过，或者关注过取消了关注，重新添加一条记录

                if (memberTagService.insert(model)) {
                    restResponse.setCode(RestResponse.OK);
                    map.put("memberTagId", model.getId());
                    return restResponse;

                } else {
                    restResponse.setMessage("添加会员标签异常！");
                    return restResponse;
                }
            }

        } else if (likeStatus.equals("cancel")) {

            if (!memberTagList.isEmpty()) {
                for (MemberTag memberTag : memberTagList) {
                    memberTag.setStatus(Status.cancel.toString());
                    memberTagService.update(memberTag);
                }
            }
            restResponse.setCode(RestResponse.OK);
        }
        return restResponse;
    }


    /**
     *  我的订阅
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/memberSubscriptions")
    public RestResponse<Map<String, Object>> memberSubscriptions(int pageNum, int pageSize) {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();

        //TODO:获取当前用户：
        Member member = new Member();
        member.setId(1L);

        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        Map<String,Object> condition = new HashMap<String, Object>();
        condition.put("memberId",member.getId());
        condition.put("status",Status.normal.toString());

        PageInfo<MemberSubscriptionVO> memberSubscriptionPageInfo = memberSubscriptionService.queryVO(pageNum, pageSize, condition);

        if (memberSubscriptionPageInfo != null) {

            restResponse.setCode(RestResponse.OK);
            map.put("pageList", memberSubscriptionPageInfo);

        } else {
            restResponse.setMessage("读取用户订阅失败！");
        }

        restResponse.setCode(RestResponse.OK);
        return restResponse;

    }


    //

    /**
     * 添加取消订阅
     * @param operation add|cancel
     * @param publisherId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editMemberSubscription")
    public RestResponse<Map<String, Object>> memberSubscriptions(String operation, Long publisherId) {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();

        if (StringUtil.isEmpty(operation) || !(operation.equals("add") || operation.equals("cancel"))) {
            restResponse.setMessage("operation 参数不能为空，且只能是add|cancel");
            return restResponse;
        }

        //TODO:获取当前用户：
        Member member = new Member();
        member.setId(1L);

        //查找被订阅者
        Member publisher = memberService.getById(publisherId);
        if (publisher == null || !publisher.getStatus().equals(Status.normal.toString())) {
            restResponse.setMessage("被订阅者不存在，错误的 publisherId");
            return restResponse;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //先查看以前有没有订阅过
        MemberSubscription model = new MemberSubscription();
        model.setMemberId(member.getId());
        model.setPublisherId(publisherId);
        model.setStatus(Status.normal.toString());

        List<MemberSubscription> memberSubscriptionList = memberSubscriptionService.queryList(model);

        if (memberSubscriptionList == null) {
            restResponse.setMessage("查询会员订阅异常！");
            return restResponse;
        }


        if (operation.equals("add")) { //添加订阅记录

            if (!memberSubscriptionList.isEmpty()) { //已经订阅过

                restResponse.setCode(RestResponse.OK);
                map.put("memberSubscriptionId", memberSubscriptionList.get(0).getId());
                return restResponse;

            } else {  //没有订阅过或者已经取消了订阅，直接插入一条

                if (memberSubscriptionService.insert(model)) {
                    restResponse.setCode(RestResponse.OK);
                    map.put("memberSubscriptionId", model.getId());

                    publisher.setSubscribedCount(publisher.getSubscribedCount() + 1);
                    memberService.update(publisher);
                    return restResponse;

                } else {
                    restResponse.setMessage("添加会员订阅异常！");
                    return restResponse;
                }

            }

        } else if (operation.equals("cancel")) { //取消订阅

            if (!memberSubscriptionList.isEmpty()) {
                for (MemberSubscription memberSubscription : memberSubscriptionList) {
                    memberSubscription.setStatus(Status.cancel.toString());
                    memberSubscriptionService.update(memberSubscription);
                }

                publisher.setSubscribedCount(publisher.getSubscribedCount() - memberSubscriptionList.size());
                memberService.update(publisher);
            }
            restResponse.setCode(RestResponse.OK);
        }
        return restResponse;

    }

    //设置
    @ResponseBody
    @RequestMapping(value = "/memberSetting")
    public RestResponse<Map<String, Object>> memberSetting(String shared, String autoPlay) {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();

        if (StringUtil.isEmpty(shared) && StringUtil.isEmpty(autoPlay)) {
            restResponse.setMessage("参数不能为空");
            return restResponse;
        }

        if (StringUtil.isNotEmpty(shared) && !(shared.equals("yes") || shared.equals("no"))) {
            restResponse.setMessage("shared 参数只能是yes|no");
            return restResponse;
        }

        if (StringUtil.isNotEmpty(autoPlay) && !(autoPlay.equals("yes") || autoPlay.equals("no"))) {
            restResponse.setMessage("autoPlay 参数只能是yes|no");
            return restResponse;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:获取当前用户：
        Member member = new Member();
        member.setId(1L);

        //获取用户设置信息
        MemberSetting model = new MemberSetting();
        model.setMemberId(member.getId());
        model.setStatus(Status.normal.toString());

        List<MemberSetting> memberSettingList = memberSettingService.queryList(model);

        if (memberSettingList != null ) {

            MemberSetting memberSetting ;

            boolean bExist = false;

            if(memberSettingList.isEmpty()) {

                memberSetting = new MemberSetting();
                memberSetting.setMemberId(member.getId());
                memberSetting.setStatus(Status.normal.toString());
            }else {
                memberSetting = memberSettingList.get(0);
                bExist = true;
            }

            if (StringUtil.isNotEmpty(shared)) {
                memberSetting.setShared(shared);
            }
            if (StringUtil.isNotEmpty(autoPlay)) {
                memberSetting.setAutoPlay(autoPlay);
            }

            if(!bExist){

                if (memberSettingService.insert(memberSetting)) {
                    restResponse.setCode(RestResponse.OK);
                    return restResponse;
                } else {
                    restResponse.setMessage("修改用户配置失败");
                    return restResponse;
                }

            }else {

                if (memberSettingService.update(memberSetting)) {
                    restResponse.setCode(RestResponse.OK);
                    return restResponse;
                } else {
                    restResponse.setMessage("修改用户配置失败");
                    return restResponse;
                }
            }

        } else {
            restResponse.setMessage("读取用户配置失败");
            return restResponse;
        }
    }
}