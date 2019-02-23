package cc.mrbird.inker.service.relations.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.inker.domain.PropertyNameValue;
import cc.mrbird.inker.service.relations.PropertyNameValueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by yin on 2019/1/31.
 * Description:
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PropertyNameValueServiceImpl extends BaseService<PropertyNameValue> implements PropertyNameValueService {
    @Override
    public void deleteRelations(String params, String value) {
        this.deleteByParams(params,value,PropertyNameValue.class);
    }

    @Override
    @Transactional
    public void savePropertyNameValueRelation(String propertyNameId, String propertyValueId) {
        PropertyNameValue propertyNameValue = new PropertyNameValue(propertyNameId,propertyValueId);
        this.save(propertyNameValue);
    }

    @Override
    public List<PropertyNameValue> getPropertyNameValueRelation(String propertyNameId) {
        Example example = new Example(PropertyNameValue.class);
        example.createCriteria().andCondition("property_name_id=",propertyNameId);
        List<PropertyNameValue> propertyNameValues = this.selectByExample(example);
        return propertyNameValues;
    }

    @Override
    public List<PropertyNameValue> getPropertyNameValueRelation2(String propertyValueId) {
        Example example = new Example(PropertyNameValue.class);
        example.createCriteria().andCondition("property_value_id=",propertyValueId);
        List<PropertyNameValue> propertyNameValues = this.selectByExample(example);
        return propertyNameValues;
    }

    @Override
    @Transactional
    public void updatePropertyNameValueRelation(String propertyNameId, String propertyValueId) {
        //添加前先删除
        PropertyNameValue propertyNameValue = new PropertyNameValue(propertyNameId,propertyValueId);
        this.updateByParams("property_value_id",propertyValueId,PropertyNameValue.class,propertyNameValue);
    }
}
