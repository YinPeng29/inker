package cc.mrbird.inker.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.annotation.Submit;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.inker.domain.Category;
import cc.mrbird.inker.service.category.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @ClassName WCategoryController
 * @Description 衣服格式（T恤等）
 * @author wuhan
 * @date 2019-01-29 11:45
 */
@Controller
@RequestMapping("/category")
public class WCategoryController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(WCategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @Log("进入衣服类别管理页面")
    @RequestMapping("/toCategoryIndex")
    public String toCategoryIndex() {
        return "inker/manager/category";
    }

    @Log("获取类别管理列表")
    @RequestMapping("/getList")
    @ResponseBody
    public Map<String,Object> getList(QueryRequest request, Category category) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Category> categorys = categoryService.selectCategoryWithParams(category, null);
        PageInfo<Category> pageInfo = new PageInfo<>(categorys);
        return getDataTable(pageInfo);
    }

    @Log("获取类别管理列表")
    @RequestMapping("/getAllList")
    @ResponseBody
    public Map<String,Object> getAllList(QueryRequest request, Category category) {
        List<Category> categorys = categoryService.selectCategoryWithParams(category, null);
        PageInfo<Category> pageInfo = new PageInfo<>(categorys);
        return getDataTable(pageInfo);
    }
    @Submit(key = "#addCategory")
    @Log("新增类别")
    @RequestMapping("/addCategory")
    @ResponseBody
    public ResponseBo addCategory(Category category) {
        String categoryName = category.getName();
        if (StringUtils.isBlank(categoryName)){
            logger.error("新增类别-----输入的类别错误");
            return ResponseBo.error("请添加正确的类别");
        }
        try {

            this.categoryService.addSize(category);
            return ResponseBo.ok("新增类别成功！");
        } catch (Exception e) {
            logger.error("新增类别失败", e);
            return ResponseBo.error("新增类别失败，请联系网站管理员！");
        }
    }

    @Log("根据id获取类别")
    @RequestMapping("/getCategoryById")
    @ResponseBody
    public ResponseBo getCategoryById(String id) {
        if (StringUtils.isBlank(id)){
            logger.error("根据id获取类别，id=null");
            return ResponseBo.error("类别id为null");
        }
        try {
            Category category = categoryService.findById(id);
            return ResponseBo.ok(category);
        } catch (Exception e) {
            logger.error("获取类别失败", e);
            return ResponseBo.error("获取类别失败，请联系网站管理员！");
        }
    }

    @Log("修改类别")
    @RequestMapping("/updateCategory")
    @ResponseBody
    public ResponseBo updateCategory(Category category) {

        if (StringUtils.isBlank(category.getName())){
            logger.error("修改类别，name不能为null");
            return ResponseBo.error("类别不能为null");
        }
        try {
            this.categoryService.updateNotNull(category);
            logger.info("修改类别成功,id={},name={}",category.getId(),category.getName());
            return ResponseBo.ok("修改类别成功");
        } catch (Exception e) {
            logger.error("修改类别失败", e);
            return ResponseBo.error("修改类别失败，请联系网站管理员！");
        }
    }
}
