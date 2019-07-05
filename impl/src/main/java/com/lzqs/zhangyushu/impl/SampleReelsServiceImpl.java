package com.lzqs.zhangyushu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzqs.zhangyushu.commomConstant.ReturnMessage;
import com.lzqs.zhangyushu.dao.ProductImgMapper;
import com.lzqs.zhangyushu.dao.ProductMapper;
import com.lzqs.zhangyushu.entity.Product;
import com.lzqs.zhangyushu.entity.ProductImg;
import com.lzqs.zhangyushu.entity.SampleReels;
import com.lzqs.zhangyushu.dao.SampleReelsMapper;
import com.lzqs.zhangyushu.filepathUtil.ConfigBeanProp;
import com.lzqs.zhangyushu.service.SampleReelsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 作品集表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-04
 */
@Service
public class SampleReelsServiceImpl extends ServiceImpl<SampleReelsMapper, SampleReels> implements SampleReelsService {

    @Resource
    private SampleReelsMapper sampleReelsMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductImgMapper productImgMapper;

    @Resource
    private ConfigBeanProp configBeanProp;

    /**
     * 根据用户i的获取该用户的作品集列表
     * @param userId
     * @return
     */
    @Override
    public ReturnMessage querylistSampleReelsByUserId(Long userId) {
        List<SampleReels> sampleReelsList = sampleReelsMapper.selectList(new QueryWrapper<SampleReels>().eq("user_id",userId));

        return ReturnMessage.success().add("sampleReelsList",sampleReelsList);
    }

    /**
     * 根据做评级id获取作品集详情
     * @param sampleReelsId
     * @return
     */
    @Override
    public ReturnMessage querySampleReelsById(Long sampleReelsId) {
        SampleReels sampleReels =sampleReelsMapper.selectById(sampleReelsId);
        List<Product> productList = productMapper.selectList(new QueryWrapper<Product>().eq("sample_reels_id",sampleReelsId));
        for (Product product : productList){
            List<ProductImg> productImgList  =  productImgMapper.selectList(new QueryWrapper<ProductImg>().eq("product_id",product.getProductionId()));
            //给前端添加url
            System.out.println();
            for (ProductImg productImg : productImgList){
                productImg.setImgUrl(configBeanProp.getImageUrl()+productImg.getProductImg());
            }
            product.setProductImgList(productImgList);
        }
        return ReturnMessage.success().add("sampleReels",sampleReels).add("productList",productList);
    }
}
