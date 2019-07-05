package com.lzqs.zhangyushu.service;

import com.lzqs.zhangyushu.commomConstant.ReturnMessage;
import com.lzqs.zhangyushu.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 作品表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-04
 */
public interface ProductService extends IService<Product> {
    // 根据用户id获取他的作品list
    ReturnMessage listProductByUserId(Long userId);
}
