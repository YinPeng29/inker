package cc.mrbird.common.aspect;

import cc.mrbird.common.annotation.AutoInsertCreateTime;
import cc.mrbird.common.annotation.AutoInsertCreator;
import cc.mrbird.common.annotation.AutoInsertModifier;
import cc.mrbird.common.annotation.AutoInsertModifyTime;
import cc.mrbird.system.domain.User;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

/**
 * Created by yin on 2019/1/8.
 * Description: 自定义mybatis 拦截器
 */
@Component
@Intercepts({ @Signature(type = Executor.class,method="update",args = {MappedStatement.class,Object.class}) })
public class CustomInterceptor implements Interceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        //获取sql 命令
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        //获取参数
        Object parameter = invocation.getArgs()[1];
        //获取私有成员变量
        Field[] declaredFields = parameter.getClass().getDeclaredFields();
        String username = "";
        try {
            username = ((User) SecurityUtils.getSubject().getPrincipal()).getUsername();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取session失败==> ",e);
            username = null;
        }
        for (Field field :declaredFields) {
            if(SqlCommandType.INSERT.equals(sqlCommandType)){ //insert
                if(field.getAnnotation(AutoInsertCreateTime.class) != null){
                    logger.info("insert 语句插入创建时间..");
                    field.setAccessible(true);
                    field.set(parameter,new Date());
                    field.setAccessible(false);
                }
                if(field.getAnnotation(AutoInsertCreator.class) != null){
                    logger.info("insert 语句插入创建人...");
                    field.setAccessible(true);
                    field.set(parameter,username);
                    field.setAccessible(false);
                }

            }
            if(SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)){ //insert || update
                if(field.getAnnotation(AutoInsertModifyTime.class) != null){
                    logger.info("insert||update 语句插入更新时间..");
                    field.setAccessible(true);
                    field.set(parameter,new Date());
                    field.setAccessible(false);
                }
                if(field.getAnnotation(AutoInsertModifier.class) != null){
                    logger.info("insert 语句插入修改人...");
                    field.setAccessible(true);
                    field.set(parameter,username);
                    field.setAccessible(false);
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
