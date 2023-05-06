package com.lijun.pattern.behavior.template;

import java.sql.ResultSet;

/**
 * @author : LiJun
 * @date : 2020-04-07 17:23
 * ORM映射定制化的接口，将查询结果映射为java对象
 **/
public interface RowMapper<T> {

    T mapRow(ResultSet resultSet,int rowNum) throws Exception;
}
