package com.lzqs.zhangyushu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzqs.zhangyushu.Constant.UserConstant;
import com.lzqs.zhangyushu.entity.User;
import com.lzqs.zhangyushu.dao.UserMapper;
import com.lzqs.zhangyushu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Resource
    private UserMapper userMapper;
    /**
     * 登录时根据openID 获取用户信息 不存在 就添加用户
     * @param openid
     * @return
     */
    @Override
    public User queryUserByOpenId(String openid) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("open_id",openid));
        if (user == null){
            User user1 = new User();
            user.setAddTime(LocalDateTime.now());
            user.setOpenId(openid);
            user.setStatus(UserConstant.COMMON);
        }
        return null;
    }
}
