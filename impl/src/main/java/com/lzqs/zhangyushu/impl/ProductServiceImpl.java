package com.lzqs.zhangyushu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzqs.zhangyushu.commomConstant.ReturnMessage;
import com.lzqs.zhangyushu.dao.ProductImgMapper;
import com.lzqs.zhangyushu.entity.Product;
import com.lzqs.zhangyushu.dao.ProductMapper;
import com.lzqs.zhangyushu.entity.ProductImg;
import com.lzqs.zhangyushu.filepathUtil.ConfigBeanProp;
import com.lzqs.zhangyushu.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 作品表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-04
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Resource
    private  ProductMapper productMapper;
    @Resource
    private ProductImgMapper productImgMapper;
    @Resource
    private ConfigBeanProp configBeanProp;

    /**
     * 根据用户的id  获取他的作品列表
     * @param userId
     * @return
     */
    @Override
    public ReturnMessage listProductByUserId(Long userId) {
        List<Product> productList = productMapper.selectList(new QueryWrapper<Product>().eq("userId",userId));
        for (Product product : productList){
            List<ProductImg> productImgList  =  productImgMapper.selectList(new QueryWrapper<ProductImg>().eq("product_id",product.getProductionId()));
            //给前端添加url
            for (ProductImg productImg : productImgList){
                productImg.setImgUrl(configBeanProp.getImageUrl()+productImg.getProductImg());
            }
            product.setProductImgList(productImgList);
        }
        return ReturnMessage.success().add("productList",productList);
    }
}
