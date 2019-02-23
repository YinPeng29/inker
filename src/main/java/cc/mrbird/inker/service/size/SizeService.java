package cc.mrbird.inker.service.size;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.inker.domain.Size;

import java.util.List;

/** 衣服尺码服务
 * @ClassName SizeService
 * @Description 衣服尺码服务
 * @author wuhan
 * @date 2019-01-22 18:00
 */

public interface SizeService extends IService<Size> {

    /** 根据条件查询size列表
      * @params size
      * @Description 根据条件查询size列表,如果
      * @author wuhan
      * @date 2019/1/23,13:46
      * @return
      */
    List<Size> selectSizeWithParams(Size size, QueryRequest request);

    /**
     * @param size 新增对象
     */
    void addSize(Size size);

    /** 通过主键查出
     * @params [id]
     * @Description TODO
     * @author wuhan
     * @date 2019/1/24,11:27
     * @return cc.mrbird.inker.domain.Size
     */
    Size findById(String id);

    void batchDeleteSize(List<String> ids);
}
