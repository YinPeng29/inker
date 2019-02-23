package cc.mrbird.inker.service.propertyValue;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.inker.domain.PropertyValue;

import java.util.List;

/**
 * Date: 2019-01-22 11:28
 * Author: pinoc
 * Desc:
 */
public interface PropertyValueService extends IService<PropertyValue> {

    List<PropertyValue> selectAllPropertyValue();

    /**
     * 查询商品属性值列表 有参数根据参数查询
     * @param propertyValue
     * @param request
     * @return
     */
    List<PropertyValue> selectPropertyValueByParams(PropertyValue propertyValue,QueryRequest request);
    /**
     * 添加商品属性值
     * @param propertyValue
     */
    int addPropertyValue(PropertyValue propertyValue);

    /**
     * 根据ids 删除商品属性值
     * @param ids
     */
    void deletePropertyValue(String ids);

    /**
     * 更新商品属性值根据ids 多个id 逗号分开
     * @param propertyValue
     */
    int updatePropertyValue(PropertyValue propertyValue);

    /**
     * 根据id 查询商品属性值
     * @param id
     * @return
     */
    PropertyValue findById(String id);

    /*---------------------------------------设置property_name_value----------------------------*/
    void addPropertyNameValueRelation(String propertyNameId,String propertyValueId);

    void updatePropertyNameValueRelation(String propertyNameId , String propertyValueId);
}
