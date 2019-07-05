package com.lzqs.zhangyushu.controller;

import com.lzqs.zhangyushu.commomConstant.ReturnMessage;
import com.lzqs.zhangyushu.paramUtil.ParamCheckUtils;
import com.lzqs.zhangyushu.paramUtil.ParamTransformationUtils;
import com.lzqs.zhangyushu.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class AppLoginController {

    @Resource
    private UserService userService;

    /**
     *微信 登录
     */
    @PostMapping("/login")
    public ReturnMessage login(@RequestBody Map<String ,Object> map){
        String code = ParamTransformationUtils.transformToString(map.get("code"));
        if (ParamCheckUtils.paramIsNull(code)){
            return  ReturnMessage.failWithMsg("code 不能是空");
        }
        return  null;
    }

}
