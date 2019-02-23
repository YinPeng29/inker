package cc.mrbird.inker.service.product;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.inker.domain.Product;
import cc.mrbird.inker.domain.ProductManagerListDto;

import java.util.List;

/**
 * Date: 2019-01-14 22:51
 * Author: pinoc
 * Description:
 */
public interface ProductService extends IService<Product> {

    /**
     * 根据筛选条件查询商品
     * @param product
     * @param request
     * @return
     */
    List<Product> selectProductWithParams(Product product,QueryRequest request);

    /**
     * 添加商品
     * @param product
     */
    String addProduct(Product product);

    /**
     * 删除商品
     * @param id
     */
    void deleteProduct(String id);

    /**
     * 根据筛选条件查询商品列表信息
     * @param productManagerListDto
     * @param request
     * @return
     */
    List<ProductManagerListDto> selectProductManagerListDto(ProductManagerListDto productManagerListDto, QueryRequest request);
}
