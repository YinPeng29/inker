package cc.mrbird.inker.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.inker.domain.ProductManagerListDto;
import cc.mrbird.inker.service.product.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Date: 2019-01-14 22:49
 * Author: pinoc
 * Description: 商品控制类ces
 */
@Controller
@RequestMapping("/product2")
public class ProductController2 extends BaseController {
    private Logger logger = LoggerFactory.getLogger(ProductController2.class);

    @Autowired
    private ProductService productService;


    @Log("进入尺寸管理页面")
    @RequestMapping("/toList")
    public String toSizeIndex() {
        return "inker/manager/productManager";
    }

    @Log("获取商品信息列表")
    @RequestMapping("/productList")
    @ResponseBody
    public Map<String,Object> productList(QueryRequest request,ProductManagerListDto productManagerListDto) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
//        List<Product> products = productService.selectProductWithParams(product,null);
        try {
            List<ProductManagerListDto> list = productService.selectProductManagerListDto(productManagerListDto,request);
            PageInfo<ProductManagerListDto> pageInfo = new PageInfo<>(list);
            return getDataTable(pageInfo);
        }catch (Exception e){
            logger.error("1111111"+e);
            return null;
        }
    }
}
