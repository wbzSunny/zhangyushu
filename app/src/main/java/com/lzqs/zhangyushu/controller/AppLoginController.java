package com.lzqs.zhangyushu.controller;

import com.lzqs.zhangyushu.commomConstant.ReturnMessage;
import com.lzqs.zhangyushu.entity.User;
import com.lzqs.zhangyushu.paramUtil.ParamCheckUtils;
import com.lzqs.zhangyushu.paramUtil.ParamTransformationUtils;
import com.lzqs.zhangyushu.service.UserService;
import com.lzqs.zhangyushu.token.JwtUtil;
import com.lzqs.zhangyushu.token.VerifyToken;
import com.lzqs.zhangyushu.wxLogin.WeChatLogin;
import com.lzqs.zhangyushu.wxLogin.WxLoginVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
        try {
            String code = ParamTransformationUtils.transformToString(map.get("code"));
            if (ParamCheckUtils.paramIsNull(code)){
                return  ReturnMessage.failWithMsg("code 不能是空");
            }
            WxLoginVo wxLoginVo = WeChatLogin.login(code);
            if(wxLoginVo.getOpenid() != null){
                System.out.println("====================微信的 openId============"+wxLoginVo.getOpenid());
                User user = userService.queryUserByOpenId(wxLoginVo.getOpenid());

                    String token = JwtUtil.sign(user.getUserId().toString());
                    user.setToken(token);
                    return  ReturnMessage.success().add("user",user);

            }else {
                return ReturnMessage.failWithMsg("code 错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.failWithMsg("系统错误");
        }


    }
    /**
     * 从前端获取 用户信息 更新用户信息
     */
    @PostMapping("/updateUserInfo")
    public  ReturnMessage updateUserInfo(HttpServletRequest request, @RequestBody Map<String, Object> map){
        try {
            Map<String,Object> userIdMap = VerifyToken.getUserId(request);
            Long userId = (Long) userIdMap.get("userId");
            if (userId== null){
                return ReturnMessage.failWithMsg(map.get("msg").toString());
            }
            String userHead = ParamTransformationUtils.transformToString(map.get("userHead"));
            String nickName = ParamTransformationUtils.transformToString(map.get("nickName"));
            Integer gender = ParamTransformationUtils.transformToInteger(map.get("gender"));
            return userService.updateByInfo(userId,userHead,nickName,gender);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.failWithMsg("系统错误");
        }

    }

}
