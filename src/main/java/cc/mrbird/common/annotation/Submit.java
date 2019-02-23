package cc.mrbird.common.annotation;

import java.lang.annotation.*;

/**
 * Created by yin on 2019/1/31.
 * Description: 自定义注解 控制表单重复提交
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Submit {
    String key() default "";
}
