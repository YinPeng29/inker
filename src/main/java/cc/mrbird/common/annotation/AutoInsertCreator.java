package cc.mrbird.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yin on 2019/1/8.
 * Description: 自定义注解 自动插入创建时间
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.ANNOTATION_TYPE})
public @interface AutoInsertCreator {
    String value() default "";
}