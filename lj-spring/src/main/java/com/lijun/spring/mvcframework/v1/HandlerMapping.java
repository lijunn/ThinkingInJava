package com.lijun.spring.mvcframework.v1;

import com.lijun.spring.mvcframework.annotation.GPRequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : LiJun
 * @date : 2020-04-17 18:43
 **/
public class HandlerMapping {

    /**路径*/
    private String url;
    private Method method;
    private Object target;

    /**参数列表位置映射*/
    private Map<Integer, String> paramIndex = new HashMap<Integer, String>();

    public HandlerMapping(String url, Method method, Object target) {
        this.url = url;
        this.method = method;
        this.target = target;

        //方法参数注解，会有多个参数，每个参数有多个注解所以是二维数组
        Annotation[][] pa1 = method.getParameterAnnotations();
        Class<?>[] parameterTypes = method.getParameterTypes();

        for (int i = 0; i< parameterTypes.length ;i++){

            Annotation[] pa2 = pa1[i];
            for (Annotation annotation : pa2) {
                if (annotation.annotationType() == GPRequestParam.class) {
                    String annotationParamName = ((GPRequestParam) annotation).value();
                    paramIndex.put(i,annotationParamName);
                }
            }
        }

    }

    public String getUrl() {
        return url;
    }

    public Method getMethod() {
        return method;
    }

    public Object getTarget() {
        return target;
    }

    public Map<Integer, String> getParamIndex() {
        return paramIndex;
    }
}
