package com.lijun.pattern.template;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : LiJun
 * @date : 2020-04-07 16:41
 **/
public abstract class JdbcTemplate<T> {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     *
     * @param sql sql语句，参数使用占位符代替
     * @param rowMapper 数据库 ORM 映射
     * @param values 参数列表
     * @return
     */
    public List<T> executeQuery(String sql, RowMapper<T> rowMapper,Object[] values){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            //1.获得数据库连接
            conn = this.getConnection();

            //2.创建预处理语句集
            pst =  this.createPreparedStatement(conn,sql);

            //3.执行语句集
            rs = this.executeQuery(pst,values);

            //4.处理结果集
            List<T> result = parseResultSet(rs,rowMapper);

            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //5.关闭结果集
                closeResultSet(rs);
                //6.关闭语句集
                closeStatement(pst);
                //7.关闭连接
                closeConnection(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    protected void closeConnection(Connection conn) throws Exception {
        //如果使用数据库连接池的话，则是数据库连接池关闭，我们不是关闭
        if (conn != null){
            conn.close();
        }
    }

    protected void closeStatement(PreparedStatement pstm) throws Exception {
        if (pstm != null){
            pstm.close();
        }
    }

    protected void closeResultSet(ResultSet rs) throws Exception {
        if (rs != null){
            rs.close();
        }
    }


    protected List<T> parseResultSet(ResultSet rs, RowMapper<T> rowMapper) throws Exception {

        ArrayList<T> list = new ArrayList<>();
        int rowNum = 1;
        while (rs.next()){
            T obj = rowMapper.mapRow(rs, rowNum++);
            list.add(obj);
        }
        return list;
    }

    protected ResultSet executeQuery(PreparedStatement pst, Object[] values) throws SQLException {
        //将语句集中的占位符替换为参数
        if (null != values){
            for (int i = 0;i < values.length;i++){
                pst.setObject(i,values[i]);
            }
        }
        return pst.executeQuery();
    }

    protected PreparedStatement createPreparedStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
