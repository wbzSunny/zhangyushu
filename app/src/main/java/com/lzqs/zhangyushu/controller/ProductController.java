package com.lzqs.zhangyushu.controller;

import com.lzqs.zhangyushu.commomConstant.ReturnMessage;
import com.lzqs.zhangyushu.service.ProductService;
import com.lzqs.zhangyushu.token.VerifyToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    /**
     * 根据用户获取他的作品
     */
    @PostMapping("/listProductByUserId")
    public ReturnMessage listProductByUserId(HttpServletRequest request){
        Map<String ,Object> userIdMap = VerifyToken.getUserId(request);
        Long userId = (Long) userIdMap.get("userId");
        if (userId== null){
            return ReturnMessage.failWithMsg(userIdMap.get("msg").toString());
        }
        return productService.listProductByUserId(userId);
    }
}
