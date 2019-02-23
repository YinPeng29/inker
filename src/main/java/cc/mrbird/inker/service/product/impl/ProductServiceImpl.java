package cc.mrbird.inker.service.product.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.inker.dao.ProductMapper;
import cc.mrbird.inker.domain.Product;
import cc.mrbird.inker.domain.ProductManagerListDto;
import cc.mrbird.inker.service.product.ProductService;
import cc.mrbird.inker.utils.SysUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 2019-01-14 22:51
 * Author: pinoc
 * Description:
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ProductServiceImpl extends BaseService<Product> implements ProductService {
    private Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> selectProductWithParams(Product product, QueryRequest request) {
        try {
            List<Product> productList = productMapper.selectAll();
            return productList;
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public String addProduct(Product product) {
        // TODO: 2019/2/13 新增商品id手动生成
        String id = SysUtil.randomUUID();
        product.setId(id);
        int save = this.save(product);
        return save>0 ? product.getId() : null;
    }

    @Override
    public void deleteProduct(String id) {

    }

    @Override
    public List<ProductManagerListDto> selectProductManagerListDto(ProductManagerListDto productManagerListDto, QueryRequest request) {

        List<ProductManagerListDto> list =  productMapper.selectProductManagerListDto();

        return list;
    }
}
