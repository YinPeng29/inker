package cc.mrbird.inker.service.propertyValue.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.inker.dao.PropertyValueMapper;
import cc.mrbird.inker.domain.PropertyValue;
import cc.mrbird.inker.service.propertyValue.PropertyValueService;
import cc.mrbird.inker.service.relations.PropertyNameValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Date: 2019-01-22 11:28
 * Author: pinoc
 * Desc:
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PropertyValueServiceImpl extends BaseService<PropertyValue> implements PropertyValueService {

    @Autowired
    private PropertyValueMapper propertyValueMapper;
    @Autowired
    private PropertyNameValueService propertyNameValueService;

    @Override
    public List<PropertyValue> selectAllPropertyValue() {
        return this.selectAll();
    }

    @Override
    public List<PropertyValue> selectPropertyValueByParams(PropertyValue propertyValue, QueryRequest request) {
        List<PropertyValue> propertyValues = propertyValueMapper.selectPropertyValueByParams(propertyValue);
        return propertyValues;
    }

    @Override
    @Transactional
    public int addPropertyValue(PropertyValue propertyValue) {
        int save = this.save(propertyValue);
        return save>0 ? save : -1;
    }

    @Override
    @Transactional
    public void deletePropertyValue(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list,"id",PropertyValue.class);
    }

    @Override
    @Transactional
    public int updatePropertyValue(PropertyValue propertyValue) {
        int i = this.updateNotNull(propertyValue);
        return i>0 ? i : -1 ;
    }


    @Override
    public PropertyValue findById(String id) {
        Example example = new Example(PropertyValue.class);
        example.createCriteria().andCondition("id=",id);
        List<PropertyValue> propertyValues = this.selectByExample(example);
        return propertyValues.isEmpty() ? null : propertyValues.get(0);
    }

    @Override
    public void addPropertyNameValueRelation(String propertyNameId, String propertyValueId) {
        propertyNameValueService.savePropertyNameValueRelation(propertyNameId,propertyValueId);
    }

    @Override
    public void updatePropertyNameValueRelation(String propertyNameId,String propertyValueId) {
        propertyNameValueService.updatePropertyNameValueRelation(propertyNameId,propertyValueId);
    }
}
