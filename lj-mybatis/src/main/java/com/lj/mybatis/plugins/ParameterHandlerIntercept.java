package com.lj.mybatis.plugins;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.PreparedStatement;

/**
 * @author : LiJun
 * @date : 2022-11-28 18:11
 **/
@Intercepts(@Signature(type = ParameterHandler.class,method = "setParameters",args = {PreparedStatement.class}))
public class ParameterHandlerIntercept implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        DefaultParameterHandler target = (DefaultParameterHandler) invocation.getTarget();
        Object parameterObject = target.getParameterObject();
        System.out.println(parameterObject);

        PreparedStatementLogger ps = (PreparedStatementLogger) invocation.getArgs()[0];
        ClientPreparedStatement clientStatement = (ClientPreparedStatement) ps.getPreparedStatement();
        String sqlForBatch = clientStatement.getQueryInfo().getSqlForBatch();

        System.out.println("Executor.query---执行后："+sqlForBatch);


        Object result = invocation.proceed();



        return result;
    }
}
