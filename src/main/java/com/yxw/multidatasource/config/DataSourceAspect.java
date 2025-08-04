package com.yxw.multidatasource.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
public class DataSourceAspect {

    @Pointcut("execution(* com.yxw.multidatasource.service.*.*(..))")
    public void pointcut() {}

    @Before("pointcut()")
    public void before(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(TargetDataSource.class)) {
            TargetDataSource dataSource = method.getAnnotation(TargetDataSource.class);
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value());
        }
    }

    @After("pointcut()")
    public void after() {
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
