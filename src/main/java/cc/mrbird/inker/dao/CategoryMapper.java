package cc.mrbird.inker.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.inker.domain.Category;

/** Category的持久层服务
 * @ClassName CategoryMapper
 * @Description Category的持久层服务
 * @author wuhan
 * @date 2019-01-23 10:10
 */
public interface CategoryMapper extends MyMapper<Category> {

    /** 更新category对象中不为null的值
     * @Description 更新category对象中不为null的值
     * @author wuhan
     * @date 2019/1/28,11:05
     * @return int
     * @param category
     */
    int updateCategoryNotNull(Category category);
}
