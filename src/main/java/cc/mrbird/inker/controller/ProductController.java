package cc.mrbird.inker.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.annotation.Submit;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.inker.domain.Product;
import cc.mrbird.inker.service.product.ProductService;
import cc.mrbird.inker.utils.SysUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;

    @Log("获取商品列表")
    @RequestMapping("product/list")
    @ResponseBody
    public Map<String,Object> productList(QueryRequest request,Product product) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Product> products = productService.selectProductWithParams(product,null);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        return getDataTable(pageInfo);
    }

    /**
     * 保存商品 其中包含各种关联信息 图片另外单独上传
     * @param product
     * @return
     */
    @Submit(key = "#product_add")
    @Log("保存商品")
    @RequestMapping("product/add")
    @ResponseBody
    public ResponseBo productAdd(Product product){
        try {
            //商品id
            String id = productService.addProduct(product);

            return ResponseBo.ok("新增商品成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("新增商品失败，请联系管理员");
        }
    }

    @Log("更新商品")
    public ResponseBo productUpdate(Product product){
        try {
            return ResponseBo.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("更新商品成功，请联系管理员");
        }
    }


    @Log("删除商品")
    public ResponseBo productDelete(@PathVariable("id") String id){
        return ResponseBo.ok();
    }



}
