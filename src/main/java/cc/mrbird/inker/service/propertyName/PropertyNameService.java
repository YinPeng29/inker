package cc.mrbird.inker.service.propertyName;

import cc.mrbird.common.annotation.Submit;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.inker.domain.PropertyName;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * Date: 2019-01-22 11:02
 * Author: pinoc
 * Desc:
 */
public interface PropertyNameService extends IService<PropertyName> {

    List<PropertyName> selectAllPropertyName();

    /**
     * 查询属性名称列表 有参数的根据参数查询
     * @param propertyName
     * @param request
     * @return
     */
    List<PropertyName> selectPropertyNameWithParams(PropertyName propertyName,QueryRequest request);
    /**
     * 添加商品属性名称
     * @param propertyName
     */
    int addPropertyName(PropertyName propertyName);

    /**
     * 根据id 删除商品属性名称
     * @param ids
     */
    void deletePropertyName(String ids);

    /**
     * 修改更新商品属性名称
     * @param propertyName
     */
    int updatePropertyName(PropertyName propertyName);

    /**
     *根据id查询商品属性
     * @param id
     * @return
     */
    PropertyName findById(String id);

/*---------------------------------------设置property_name_value----------------------------*/
    void addPropertyNameValueRelation(String propertyNameId,String[] propertyValueIds);

}
