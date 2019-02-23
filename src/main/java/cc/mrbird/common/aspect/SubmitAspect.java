package cc.mrbird.common.aspect;

import cc.mrbird.common.annotation.Submit;
import cc.mrbird.common.domain.ResponseBo;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by yin on 2019/1/31.
 * Description: 拦截器
 */
@Aspect
@Component
public class SubmitAspect {
    private static final Cache<String, Object> CACHES = CacheBuilder.newBuilder()
            .maximumSize(100)      //最大缓存数量
            .expireAfterWrite(2, TimeUnit.SECONDS) //两秒后可操作，即缓存的过期时间
            .build();

    @Pointcut("execution(public * cc.mrbird.*.controller..*(..)) && @annotation(cc.mrbird.common.annotation.Submit)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object interceptor(ProceedingJoinPoint pjp){
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        Submit annotation = method.getAnnotation(Submit.class);
        String key = annotation.key();
        if(!StringUtils.isEmpty(key)){
            if(CACHES.getIfPresent(key)!= null){
                return ResponseBo.error("请勿重复提交！");
            }
            CACHES.put(key,key);
        }
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
//            throw new RuntimeException("服务器异常");
            return ResponseBo.error("服务器异常！");
        }finally {
            CACHES.invalidate(key);
        }
    }
}
