package com.lj.mybatis.plugins;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * @author : LiJun
 * @date : 2022-11-28 18:11
 **/
@Intercepts(@Signature(type = Executor.class,method = "query",args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class ExecutorIntercept implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println("Executor.query---执行前："+startTime);

        Object result = invocation.proceed();

        long endTime = System.currentTimeMillis();
        System.out.println("Executor.query---执行后："+endTime);
        System.out.println("Executor.query---执行耗时："+(endTime-startTime)+ "ms");

        return result;
    }
}
