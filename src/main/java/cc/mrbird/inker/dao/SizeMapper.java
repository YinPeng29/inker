package cc.mrbird.inker.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.inker.domain.Size;

/** size的持久层服务
 * @ClassName SizeMapper
 * @Description size的持久层服务
 * @author wuhan
 * @date 2019-01-23 10:10
 */
public interface SizeMapper extends MyMapper<Size> {

    /** 通过主键查
     * @param id :主键
     * @author wuhan
     * @date 2019/1/24,11:47
     * @return cc.mrbird.inker.domain.Size
     */
    Size queryById(String id);

    /** 更新size对象中不为null的值
     * @Description 更新size对象中不为null的值
     * @author wuhan
     * @date 2019/1/28,11:05
     * @return int
     * @param size
     */
    int updateSizeNotNull(Size size);

}
