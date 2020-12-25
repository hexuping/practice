package com.example.core.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

@Aspect
@Component
public class SetUserInfoAspect {

    @Autowired
    HttpServletRequest request;

    @Pointcut("@annotation(com.example.core.anno.SetUserInfo)")
    private void pointCut() {}

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap.containsKey("name"));
        Object[] args = joinPoint.getArgs();
        Arrays.asList(args).forEach(arg -> {
            Field[] fields = arg.getClass().getDeclaredFields();
            Arrays.asList(fields).forEach(field -> {
                try {
                    field.setAccessible(true);
                    if(field.getName().equals("name")) {
                        field.set(arg, "Test");
                    }
                    if(field.getName().equals("phone") && field.get(arg) == null) {
                        field.set(arg, "18523918039");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        });
    }
}
