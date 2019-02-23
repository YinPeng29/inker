/**
 * @Title: EnumBool.java
 * @Package com.ea.p2p.constants
 * @Description: 常量或枚举包
 * Copyright: Copyright (c) 2011 
 * Company:深圳市宇商科技有限公司
 * 
 * @author Comsys-xianqiao.gu
 * @date 2016年4月14日 下午6:48:34
 * @version V1.0
 */


package cc.mrbird.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @ClassName EnumBool
 * @Description 布尔值枚举
 * @author wuhan
 * @date 2019/2/15,17:41
 */
public enum EnumBool {
	/** 0-否 */
    NO(0,"否"),
    /** 1-是 */
    YES(1,"是");
 
    private int code;
    private String msg;
    private boolean display;
    
    EnumBool(int code, String msg) {
      this.code = code;
      this.msg = msg;
      this.display = true;
    }
    
    public int getCode() {
      return code;
    }
    
    public void setCode(int code) {
      this.code = code;
    }
    
    public String getMsg() {
      return msg;
    }
    
    public void setMsg(String msg) {
      this.msg = msg;
    }
    
    public boolean isDisplay() {
      return display;
    }
    
    public void setDisplay(boolean display) {
      this.display = display;
    }
    
    /**
     * 根据枚举的code返回枚举对象
     * @param code  枚举code值
     * @return  枚举对象
     */
    public static EnumBool getEnumByCode(int code) {
      for (EnumBool type : values()) {
          if (type.getCode()==code)
              return type;
      }
      return null;
    }
    
    /**
     * 将枚举转换成code-msg形式的集合
     * 可通过<code>EnumXXX.setDisplay(false);</code>的方式将不需要的枚举类型不转换成map
     * @return  转换后的map集合
     */
    public static Map<Integer, String> toMap() {
      Map<Integer, String> enumDataMap = new LinkedHashMap<Integer, String>();
      for (EnumBool type : EnumBool.values()) {
          if (type.isDisplay()) enumDataMap.put(type.getCode(), type.getMsg());
      }
      return enumDataMap;
    }
    
    /**
     * 将枚举转换成code-code-msg形式的集合
     * 可通过<code>EnumXXX.setDisplay(false);</code>的方式将不需要的枚举类型不转换成map
     * @return  转换后的map集合
     */
    public static Map<Integer, String> toMixMap() {
      Map<Integer, String> enumDataMap = new LinkedHashMap<Integer, String>();
      for (EnumBool type : EnumBool.values()) {
          if (type.isDisplay()) enumDataMap.put(type.getCode(), type.getCode() + "-" + type.getMsg());
      }
      return enumDataMap;
    }
}
