package cc.mrbird.inker.service.bannerinfo;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.inker.domain.BannerInfo;

import java.util.List;

/**
 * @ClassName BannerInfoService
 * @Description banner轮播服务
 * @author wuhan
 * @date 2019-02-19 17:41
 */
public interface BannerInfoService extends IService<BannerInfo> {
    /** 根据条件查询BannerInfo列表
     * @params BannerInfo
     * @Description 根据条件查询BannerInfo列表,如果
     * @author wuhan
     * @date 2019/1/23,13:46
     * @return
     */
    List<BannerInfo> selectBannerInfoWithParams(BannerInfo bannerInfo, QueryRequest request);

}
