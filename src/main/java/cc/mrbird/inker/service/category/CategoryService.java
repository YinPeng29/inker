package cc.mrbird.inker.service.category;/**
 * @ClassName CategoryService
 * @Description TODO
 * @author wuhan
 * @Date 2019-01-29 15:42
 */

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.inker.domain.Category;

import java.util.List;

/**
 * @ClassName CategoryService
 * @Description 类别服务
 * @author wuhan
 * @date 2019-01-29 15:42
 */
public interface CategoryService extends IService<Category> {

    /** 根据条件查询Category列表
     * @params Category
     * @Description 根据条件查询Category列表,如果
     * @author wuhan
     * @date 2019/1/23,13:46
     * @return
     */
    List<Category> selectCategoryWithParams(Category category, QueryRequest request);

    void addSize(Category category);

    Category findById(String id);

}
