package com.example.demo;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestHandle {

    @Pointcut("@annotation(com.example.demo.Sign)")
    public void requestHandle(){
        System.out.println("AAA");
    }

    /**
     * 拦截所有请求
     */
    @Before("requestHandle()")
    public void doBefore(JoinPoint joinPoint) {
        // 访问鉴权
        unsign();
    }

    private void unsign(){
        System.out.println(">>>");
    }
    
    /**
     * 修改@RequestBody String中""aaa""为"aaa"
     * @param joinPoint
     * @return
     */
    @Around("execution(public * com.example.demo.*.*(..))")
    public Object doAroundStringJson(ProceedingJoinPoint joinPoint) {
        Object[] paramsArray = joinPoint.getArgs();
        if (paramsArray != null && paramsArray.length == 1){
            Object obj = paramsArray[0];
            if (obj instanceof String){
                String str = (String)obj;
                if (StringUtils.isNotEmpty(str)) {
                    if (str.charAt(0) == '"' && str.charAt(str.length() - 1) == '"'){
                        str = str.substring(1, str.length() - 1);
                        paramsArray[0] = str;
                    }
                }

            }
        }

        Object obj = null;
        try {
            obj = joinPoint.proceed(paramsArray);
        } catch (Throwable throwable) {
            return null;

        }
        return obj;
    }


}
