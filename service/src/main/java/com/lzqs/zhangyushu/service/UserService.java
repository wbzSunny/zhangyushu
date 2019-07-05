package com.lzqs.zhangyushu.service;

import com.lzqs.zhangyushu.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-04
 */
public interface UserService extends IService<User> {
    //登录时根据openID 获取用户信息 不存在 就添加用户
    User queryUserByOpenId(String openid);
}
