package cc.mrbird.inker.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.inker.domain.Product;
import cc.mrbird.inker.domain.ProductManagerListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Date: 2019-01-14 22:50
 * Author: pinoc
 * Description: 商品dao
 */
@Mapper
public interface ProductMapper extends MyMapper<Product> {
    List<Product> findProductWithParams(Product product);

    List<ProductManagerListDto> selectProductManagerListDto();
}
