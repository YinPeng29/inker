package cc.mrbird.inker.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.inker.domain.ProductPropertyValueRelations;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by yin on 2019/2/13.
 * Description:
 */
@Mapper
public interface ProductPropertyValueMapper extends MyMapper<ProductPropertyValueRelations> {
}
