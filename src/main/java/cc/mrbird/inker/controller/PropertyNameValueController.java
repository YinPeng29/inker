package cc.mrbird.inker.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.inker.domain.PropertyName;
import cc.mrbird.inker.domain.PropertyNameValue;
import cc.mrbird.inker.domain.PropertyValue;
import cc.mrbird.inker.service.propertyName.PropertyNameService;
import cc.mrbird.inker.service.propertyValue.PropertyValueService;
import cc.mrbird.inker.service.relations.PropertyNameValueService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by yin on 2019/1/31.
 * Description:
 */
@Controller
public class PropertyNameValueController extends BaseController {

    @Autowired
    private PropertyNameValueService propertyNameValueService;
    @Autowired
    private PropertyValueService propertyValueService;
    @Autowired
    private PropertyNameService propertyNameService;


    @Log("获取选中的属性值")
    @ResponseBody
    @RequestMapping("property_name_value/getSelectedValue")
    //    @RequiresPermissions("property_name:getOne")
    public ResponseBo getSelectedPropertyValueOfAll(String propertyNameId){
        if(StringUtils.isBlank(propertyNameId))
            return ResponseBo.error("属性值ID为空");
        try {
            List<PropertyNameValue> relationList = propertyNameValueService.getPropertyNameValueRelation(propertyNameId);
            List<PropertyValue> propertyValues = propertyValueService.selectAll();
            for (PropertyValue p : propertyValues) {
                for (PropertyNameValue p2: relationList) {
                    if(p.getId().equals(p2.getPropertyValueId()))
                        p.setCreator(p.getId());
                }
            }
            return ResponseBo.ok(propertyValues);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("查询属性关联关系失败，请联系管理员");
        }
    }

    @Log("获取选中的属性名称")
    @ResponseBody
    @RequestMapping("property_name_value/getSelectedName")
    //    @RequiresPermissions("property_name:getOne")
    public ResponseBo getSelectedPropertyNameFromAll(String propertyValueId){
        if(StringUtils.isBlank(propertyValueId))
            return ResponseBo.error("属性值ID为空");
        try {
            //获取所有的属性名称
            List<PropertyNameValue> relationList = propertyNameValueService.getPropertyNameValueRelation2(propertyValueId);
            List<PropertyName> propertyNames = propertyNameService.selectAll();
            for (PropertyName p : propertyNames) {
                for (PropertyNameValue p2: relationList) {
                    if(p.getId().equals(p2.getPropertyNameId()))
                        p.setCreator(p.getId());
                }
            }
            return ResponseBo.ok(propertyNames);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("查询属性关联关系失败，请联系管理员");
        }
    }
}
