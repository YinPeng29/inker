package cc.mrbird.inker.service.category.impl;/**
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @author wuhan
 * @Date 2019-01-29 15:44
 */

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.inker.dao.CategoryMapper;
import cc.mrbird.inker.domain.Category;
import cc.mrbird.inker.domain.Size;
import cc.mrbird.inker.service.category.CategoryService;
import cc.mrbird.system.domain.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Description 类别服务实现类
 * @author wuhan
 * @date 2019-01-29 15:44
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class CategoryServiceImpl extends BaseService<Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectCategoryWithParams(Category category, QueryRequest request) {
        return categoryMapper.selectAll();
    }

    @Override
    public void addSize(Category category) {
        this.categoryMapper.insert(category);
    }

    @Override
    public Category findById(String id) {
        Example example = new Example(Category.class);
        example.createCriteria().andCondition("id=",id);
        List<Category> categories = this.selectByExample(example);
        return categories.isEmpty()?null:categories.get(0);
    }
}
