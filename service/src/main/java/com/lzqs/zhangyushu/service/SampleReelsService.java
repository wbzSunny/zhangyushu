package com.lzqs.zhangyushu.service;

import com.lzqs.zhangyushu.commomConstant.ReturnMessage;
import com.lzqs.zhangyushu.entity.SampleReels;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 作品集表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-04
 */
public interface SampleReelsService extends IService<SampleReels> {
    // 根据用户id获取用户
    ReturnMessage querylistSampleReelsByUserId(Long userId);
    // 根据作品集id 获取作品
    ReturnMessage querySampleReelsById(Long sampleReelsId);
}
