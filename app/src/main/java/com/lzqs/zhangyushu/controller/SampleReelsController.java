package com.lzqs.zhangyushu.controller;

import com.lzqs.zhangyushu.commomConstant.ReturnMessage;
import com.lzqs.zhangyushu.entity.SampleReels;
import com.lzqs.zhangyushu.paramUtil.ParamTransformationUtils;
import com.lzqs.zhangyushu.service.SampleReelsService;
import com.lzqs.zhangyushu.token.VerifyToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/sampleReels")
public class SampleReelsController {


    @Resource
    private SampleReelsService sampleReelsService;

    /**
     * 获取我的作品集列表
     */
    @PostMapping("/listMySampleReels")
    public ReturnMessage listMySampleReels(HttpServletRequest request , @RequestBody Map<String,Object> map){
        try {
            Map<String ,Object> userIdMap = VerifyToken.getUserId(request);
            Long userId  = (Long) userIdMap.get("userId");
            if(userId == null){
                return ReturnMessage.failWithMsg(userIdMap.get("msg").toString());
            }

            return sampleReelsService.querylistSampleReelsByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.failWithMsg("系统错误");
        }
    }

    /**
     * 获取作品集详情
     */
    @PostMapping("querySampleReelsById")
    public ReturnMessage querySampleReelsById(HttpServletRequest request,@RequestBody Map<String,Object> map){
        Map<String ,Object> userIdMap = VerifyToken.getUserId(request);
        Long userId  = (Long) userIdMap.get("userId");
        if(userId == null){
            return ReturnMessage.failWithMsg(userIdMap.get("msg").toString());
        }
        Long sampleReelsId = ParamTransformationUtils.transformToNonNegativeLong(map.get("sampleReelsId"));
        return sampleReelsService.querySampleReelsById(sampleReelsId);
    }

//    /**
//     * 点赞
//     */
//    @PostMapping("/")

}
