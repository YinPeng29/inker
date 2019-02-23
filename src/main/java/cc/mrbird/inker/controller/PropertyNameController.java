package cc.mrbird.inker.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.annotation.Submit;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.inker.domain.PropertyName;
import cc.mrbird.inker.service.propertyName.PropertyNameService;
import cc.mrbird.inker.utils.SysUtil;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Date: 2019-01-22 11:40
 * Author: pinoc
 * Desc: 商品属性名称控制类
 */
@Controller
public class PropertyNameController extends BaseController {
    @Autowired
    private PropertyNameService propertyNameService;

    @Log("进入商品属性名称管理页")
    @RequestMapping("property_name")
    public String index(){
        return "inker/property_manager/property_name";
    }

    @Log("查询商品属性名称列表")
    @ResponseBody
//    @RequiresPermissions("property_name:list")
    @RequestMapping("property_name/list")
    public Map<String,Object> propertyNameList(QueryRequest request,PropertyName propertyName){
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<PropertyName> propertyNames = propertyNameService.selectPropertyNameWithParams(propertyName, null);
        PageInfo<PropertyName> pageInfo = new PageInfo<>(propertyNames);
        return getDataTable(pageInfo);
    }
    @Log("查询所有商品属性名称")
    @ResponseBody
    @RequestMapping("property_name/selectAll")
    public Map<String,Object> selectAll(){
        List<PropertyName> propertyNames = propertyNameService.selectAllPropertyName();
        return ResponseBo.ok(propertyNames);
    }

    @Submit(key = "#pn_add")
    @Log("添加商品属性名称")
    @ResponseBody
//    @RequiresPermissions("property_name:add")
    @RequestMapping("property_name/add")
    public ResponseBo addProeprtyName(PropertyName propertyName,@RequestParam(value = "propertyValueIds[]",required = false) String[] propertyValueIds ){
        try {
            String id = SysUtil.randomUUID();
            propertyName.setId(id);
            int i = propertyNameService.addPropertyName(propertyName);
//            if(i > 0){ //required=false,不传此参数默认null
//                propertyNameService.addPropertyNameValueRelation(id,propertyValueIds);
//            }
            return ResponseBo.ok("添加商品属性名称成功.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("添加商品属性名称成失败，请联系管理员");
        }
    }

    @Submit(key = "#pn_upd")
    @Log("更新修改商品属性名称")
    @ResponseBody
//    @RequiresPermissions("property_name:update")
    @RequestMapping("property_name/update")
    public ResponseBo updatePropertyName(PropertyName propertyName,@RequestParam(value = "propertyValueIds[]",required = false) String[] propertyValueIds ){
        try {
            //首先判断id 是不是存在
            if(StringUtils.isBlank(propertyName.getId()))
                return ResponseBo.error("属性ID为空,无法修改！");
            int i = propertyNameService.updatePropertyName(propertyName);
//            if(i > 0){
//                propertyNameService.addPropertyNameValueRelation(propertyName.getId(),propertyValueIds);
//            }
            return ResponseBo.ok("更新商品属性名称成功.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("更新商品属性名称成失败，请联系管理员");
        }
    }

    @Log("删除商品属性名称")
    @ResponseBody
//    @RequiresPermissions("property_name:delete")
    @RequestMapping("property_name/delete")
    public ResponseBo addProeprtyName(String ids){
        try {
            propertyNameService.deletePropertyName(ids);
            return ResponseBo.ok("删除商品属性名称成功.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("删除商品属性名称成失败，请联系管理员");
        }
    }

    @Log("根据id获取属性")
    @ResponseBody
    @RequestMapping("property_name/getById")
    //    @RequiresPermissions("property_name:getOne")
    public ResponseBo getPropertyNameById(String id){
        if(StringUtils.isBlank(id))
            return ResponseBo.error("属性ID为空,无法修改！");
        try {
            PropertyName propertyName = propertyNameService.findById(id);
            return ResponseBo.ok(propertyName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("查询属性失败，请联系管理员-ID: "+id);
        }
    }
}
