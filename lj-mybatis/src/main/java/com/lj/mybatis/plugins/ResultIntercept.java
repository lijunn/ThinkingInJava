package com.lj.mybatis.plugins;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.List;

/**
 * @author : LiJun
 * @date : 2022-11-28 18:11
 **/
@Intercepts(@Signature(type = ResultSetHandler.class,method = "handleResultSets",args = {Statement.class}))
public class ResultIntercept implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println("ResultSetHandler---执行前："+startTime);

        Object result = invocation.proceed();



        List<?> resultList = (List<?>) result;
        Class<?> resultType = resultList.get(0).getClass();

        for (Method method : resultType.getMethods()) {
            System.out.println("方法名："+method.getName());
        }

        for (Object o : resultList) {
            Method method = resultType.getMethod("setName",String.class);
            method.invoke(o,"测试~~");
        }


        long endTime = System.currentTimeMillis();
        System.out.println("ResultSetHandler---执行后："+endTime);
        System.out.println("ResultSetHandler---执行耗时："+(endTime-startTime)+ "ms");

        return result;
    }
}
