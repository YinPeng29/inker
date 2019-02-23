package cc.mrbird.inker.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.inker.domain.PropertyName;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Date: 2019-01-22 10:58
 * Author: pinoc
 * Desc:
 */
@Mapper
public interface PropertyNameMapper extends MyMapper<PropertyName> {
    List<PropertyName> selectPropertyNameByParams(PropertyName propertyName);
}
