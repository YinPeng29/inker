package cc.mrbird.inker.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.annotation.Submit;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.inker.domain.PropertyValue;
import cc.mrbird.inker.service.propertyValue.PropertyValueService;
import cc.mrbird.inker.service.relations.PropertyNameValueService;
import cc.mrbird.inker.utils.SysUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Date: 2019-01-22 11:40
 * Author: pinoc
 * Desc: 商品属性值控制类
 */
@Controller
public class PropertyValueController extends BaseController {
    @Autowired
    private PropertyValueService propertyValueService;
    @Autowired
    private PropertyNameValueService propertyNameValueService;

    @Log("进入商品属性值管理页")
    @RequestMapping("property_value")
    public String index(){
        return "inker/property_manager/property_value";
    }

    @Log("商品属性值查询列表")
    @ResponseBody
    @RequestMapping("property_value/list")
    public Map<String,Object> selectPropertyValueByParams(QueryRequest request, PropertyValue propertyValue){
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<PropertyValue> propertyValues = propertyValueService.selectPropertyValueByParams(propertyValue, null);
        PageInfo<PropertyValue> pageInfo = new PageInfo<>(propertyValues);
        return getDataTable(pageInfo);
    }

    @Log("查询所有属性值")
    @ResponseBody
    @RequestMapping("property_value/selectAll")
    public Map<String,Object> selectAll(){
        List<PropertyValue> propertyValues = propertyValueService.selectAllPropertyValue();
        return ResponseBo.ok(propertyValues);
    }

    @Submit(key = "#pv_add")
    @Log("添加商品属性值")
    @ResponseBody
    @RequestMapping("property_value/add")
    public ResponseBo addPropertyValue(PropertyValue propertyValue, @RequestParam(value = "propertyNameId",required = false) String propertyNameId){
        try {
            if(StringUtils.isBlank(propertyNameId))
                return ResponseBo.error("请勾选属性名称!");
            String id = SysUtil.randomUUID();
            propertyValue.setId(id);
            int i = propertyValueService.addPropertyValue(propertyValue);
            if(i > 0){ //required=false,不传此参数默认null
                //添加
                propertyValueService.addPropertyNameValueRelation(propertyNameId,id);
            }
            return ResponseBo.ok("添加商品属性值成功.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("添加商品属性值失败,请联系管理员");
        }
    }


    @Log("删除商品属性值")
    @ResponseBody
    @RequestMapping("property_value/delete")
    public ResponseBo deletePropertyValue(String ids){
        try {
            propertyValueService.deletePropertyValue(ids);
            return ResponseBo.ok("删除商品属性值成功.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("删除商品属性值失败,请联系管理员");
        }
    }

    @Submit(key = "#pv_upd")
    @Log("更新商品属性值")
    @ResponseBody
    @RequestMapping("property_value/update")
    public ResponseBo updatePropertyValue(PropertyValue propertyValue,@RequestParam(value = "propertyNameId",required = false) String propertyNameId){
        try {
            if(StringUtils.isBlank(propertyNameId))
                return ResponseBo.error("请勾选属性名称!");
            if(StringUtils.isBlank(propertyValue.getId()))
                return ResponseBo.error("属性值ID为空,无法修改！");
            int i = propertyValueService.updatePropertyValue(propertyValue);
            if(i>0){
                propertyValueService.updatePropertyNameValueRelation(propertyNameId,propertyValue.getId());
            }
            return ResponseBo.ok("更新商品属性值成功.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("更新商品属性值失败,请联系管理员");
        }
    }

    @Log("根据id获取属性值")
    @ResponseBody
    @RequestMapping("property_value/getById")
    public ResponseBo getPropertyValueById(String id){
        if(StringUtils.isBlank(id))
            return ResponseBo.error("属性值ID为空,无法修改！");
        try {
            PropertyValue propertyValue = propertyValueService.findById(id);
            return ResponseBo.ok(propertyValue);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("查询属性值失败，请联系管理员-ID: "+id);
        }
    }

}
