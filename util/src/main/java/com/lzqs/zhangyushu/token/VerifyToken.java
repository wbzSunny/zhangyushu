package com.lzqs.zhangyushu.token;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class VerifyToken {
    public static Map<String,Object> getUserId(HttpServletRequest request ){
        Map<String,Object> map = new HashMap<>();
        String token  = request.getHeader("token");
        if (token == null){
            map.put("msg","token为空");
            return map;
        }
        Long userId = JwtUtil.getUserId(token);
        if (userId == null){
            map.put("msg","token 错误");
            return map;
        }
        map.put("userId",userId);
        return map;
    }
}
