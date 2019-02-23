package cc.mrbird.inker.service.relations;

import cc.mrbird.common.service.IService;
import cc.mrbird.inker.domain.PropertyNameValue;

import java.util.List;

/**
 * Created by yin on 2019/1/31.
 * Description:
 */
public interface PropertyNameValueService extends IService<PropertyNameValue> {
    void deleteRelations(String params,String value);

    void savePropertyNameValueRelation(String propertyNameId,String propertyValueId);

    List<PropertyNameValue> getPropertyNameValueRelation(String propertyNameId);

    List<PropertyNameValue> getPropertyNameValueRelation2(String propertyValueId);

    void updatePropertyNameValueRelation(String propertyNameId,String propertyValueId);
}
