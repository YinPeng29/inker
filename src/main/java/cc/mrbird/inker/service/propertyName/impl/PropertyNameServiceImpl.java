package cc.mrbird.inker.service.propertyName.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.inker.dao.PropertyNameMapper;
import cc.mrbird.inker.domain.PropertyName;
import cc.mrbird.inker.domain.PropertyNameValue;
import cc.mrbird.inker.service.propertyName.PropertyNameService;
import cc.mrbird.inker.service.relations.PropertyNameValueService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Date: 2019-01-22 11:02
 * Author: pinoc
 * Desc:
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PropertyNameServiceImpl extends BaseService<PropertyName> implements PropertyNameService {

    @Autowired
    private PropertyNameMapper propertyNameMapper;
    @Autowired
    private PropertyNameValueService propertyNameValueService;

    @Override
    public List<PropertyName> selectAllPropertyName() {
        return this.selectAll();
    }

    @Override
    public List<PropertyName> selectPropertyNameWithParams(PropertyName propertyName, QueryRequest request) {
        List<PropertyName> propertyNames = propertyNameMapper.selectPropertyNameByParams(propertyName);
        return propertyNames;
    }

    @Override
    @Transactional
    public int addPropertyName(PropertyName propertyName) {
        int save = this.save(propertyName);
        return save>0 ? save : -1;
    }

    @Override
    @Transactional
    public void deletePropertyName(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list,"id",PropertyName.class); //id: 实体类字段
    }

    @Override
    @Transactional
    public int updatePropertyName(PropertyName propertyName) {
        int i = this.updateNotNull(propertyName);
        return i>0 ? i : -1;
    }

    @Override
    public PropertyName findById(String id) {
        Example example = new Example(PropertyName.class);
        example.createCriteria().andCondition("id=",id);
        List<PropertyName> propertyNames = this.selectByExample(example);
        return propertyNames.isEmpty() ? null : propertyNames.get(0);
    }

    @Override
    @Transactional
    public void addPropertyNameValueRelation(String propertyNameId, String[] propertyValueIds) {
        //添加前先删除
        propertyNameValueService.deleteRelations("property_name_id",propertyNameId);
        if(propertyValueIds !=null ){
            for (String id : propertyValueIds) {
                propertyNameValueService.savePropertyNameValueRelation(propertyNameId,id);
            }
        }
    }
}
