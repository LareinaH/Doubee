package com.cotton.doubee.web.controller;

import com.cotton.base.common.RestResponse;
import com.cotton.doubee.service.MemberService;
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
@RequestMapping("member")
public class MemberController {

    private Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @ResponseBody
    @RequestMapping(name = "/example")
    public RestResponse<Map<String, Object>> example() {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:

        return restResponse;

    }


    //我的标签（爱好）

    //我的订阅

    //设置
}