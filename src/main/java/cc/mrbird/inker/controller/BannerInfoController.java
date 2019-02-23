package cc.mrbird.inker.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.inker.domain.BannerInfo;
import cc.mrbird.inker.service.bannerinfo.BannerInfoService;
import cc.mrbird.inker.utils.SysUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Date: 2019-01-14 22:49
 * Author: pinoc
 * Description: bannner轮播控制类
 */
@Controller
@RequestMapping("/bannerInfo")
public class BannerInfoController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(BannerInfoController.class);


    @Autowired
    private BannerInfoService bannerInfoService;

    @Log("进入首页轮播管理页面")
    @RequestMapping("/tobannerInfoIndex")
    public String tobannerInfoIndex() {
        return "inker/manager/bannerInfo";
    }


    @Log("获取首页轮播列表")
    @RequestMapping("/bannerInfoList")
    @ResponseBody
    public Map<String,Object> bannerInfoList(QueryRequest request, BannerInfo bannerInfo) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<BannerInfo> bannerInfos = bannerInfoService.selectBannerInfoWithParams(bannerInfo, request);
        PageInfo<BannerInfo> pageInfo = new PageInfo<>(bannerInfos);
        return getDataTable(pageInfo);
    }

    @Log("新增首页轮播")
    @RequestMapping("/addbannerInfo")
    @ResponseBody
    public ResponseBo addbannerInfo(QueryRequest request, BannerInfo bannerInfo){
        try {
            String id = SysUtil.randomUUID();
            bannerInfo.setId(id);
            bannerInfoService.save(bannerInfo);
            return ResponseBo.ok("新增首页轮播成功");
        }catch (Exception e){
            logger.error("新增首页轮播失败--e={}",e);
            return  ResponseBo.error("新增首页轮播失败");
        }
    }

}
