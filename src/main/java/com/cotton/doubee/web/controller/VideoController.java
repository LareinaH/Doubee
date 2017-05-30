package com.cotton.doubee.web.controller;

import com.cotton.base.common.RestResponse;
import com.cotton.doubee.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Administrator on 2017-05-26.
 */

@Controller
@RequestMapping("video")
public class VideoController {

    private Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private VideoService videoService;


    @ResponseBody
    @RequestMapping(name = "/example")
    public RestResponse<Map<String, Object>> example() {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:

        return restResponse;

    }

    //视频列表（包含视频列表/手气不错/用户上传的列表/收藏的视频列表）

    //视频评论

}