package cc.mrbird.inker.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.annotation.Submit;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.inker.domain.Size;
import cc.mrbird.inker.service.size.SizeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Date: 2019-01-14 22:49
 * Author: wuhan
 * Description: 尺寸控制类
 */
@Controller
@RequestMapping("/size")
public class WSizeController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(WSizeController.class);

    @Resource
    private SizeService sizeService;

    @Log("进入尺寸管理页面")
    @RequestMapping("/toSizeIndex")
    public String toSizeIndex() {
        return "inker/manager/size";
    }

    @Log("获取尺寸管理列表")
    @RequestMapping("/getList")
    @ResponseBody
    public Map<String,Object> getList(QueryRequest request, Size size) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Size> sizes = sizeService.selectSizeWithParams(size, null);
        PageInfo<Size> pageInfo = new PageInfo<>(sizes);
        return getDataTable(pageInfo);
    }

    @Submit(key = "#addSize")
    @Log("新增尺寸")
    @RequestMapping("/addSize")
    @ResponseBody
    public ResponseBo addSize(Size size) {
        String sizeVaele = size.getSize();
        if (StringUtils.isBlank(sizeVaele)){
            logger.error("新增尺寸-----输入的尺寸错误");
            return ResponseBo.error("请添加正确的尺寸");
        }
        try {
            this.sizeService.addSize(size);
            return ResponseBo.ok("新增尺寸成功！");
        } catch (Exception e) {
            logger.error("新增尺寸失败", e);
            return ResponseBo.error("新增尺寸失败，请联系网站管理员！");
        }
    }


    @Log("根据id获取尺寸")
    @RequestMapping("/getSizeById")
    @ResponseBody
    public ResponseBo getSizeById(String id) {
        if (StringUtils.isBlank(id)){
            logger.error("根据id获取尺寸，id=null");
            return ResponseBo.error("尺寸id为null");
        }
        try {
            Size size = sizeService.findById(id);
            return ResponseBo.ok(size);
        } catch (Exception e) {
            logger.error("获取尺寸失败", e);
            return ResponseBo.error("获取尺寸失败，请联系网站管理员！");
        }
    }
    @Submit(key = "#updateSize")
    @Log("修改尺寸")
    @RequestMapping("/updateSize")
    @ResponseBody
    public ResponseBo updateSize(Size size) {

        if (StringUtils.isBlank(size.getSize())){
            logger.error("修改尺寸，size不能为null");
            return ResponseBo.error("尺寸size为null");
        }
        try {
            this.sizeService.updateNotNull(size);
            logger.info("修改尺寸成功,id={},size={}",size.getId(),size.getSize());
            return ResponseBo.ok("修改尺寸成功");
        } catch (Exception e) {
            logger.error("修改失败", e);
            return ResponseBo.error("修改尺寸失败，请联系网站管理员！");
        }
    }
    @Submit(key = "#deleteSizeByIds")
    @Log("批量删除尺寸")
    @RequestMapping("/deleteSizeByIds")
    @ResponseBody
    public ResponseBo deleteSizeByIds(String sizeIds) {
        if (StringUtils.isBlank(sizeIds)){
            logger.error("批量删除尺寸失败，sizeIds = 空");
            return ResponseBo.error("请选择想删除的尺寸");
        }
        List<String> Ids = Arrays.asList(sizeIds.split(","));

        if (ListUtils.isEqualList(ListUtils.EMPTY_LIST,Ids)){
            logger.error("删除尺寸，sizeIds不能为null");
            return ResponseBo.error("请选择尺寸");
        }
        sizeService.batchDeleteSize(Ids);
        return ResponseBo.ok("批量删除成功");
    }
}
