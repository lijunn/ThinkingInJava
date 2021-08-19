package com.lj.mybatis;

import com.alibaba.fastjson.JSON;
import com.lj.mybatis.mapper.UserMapper;
import com.lj.mybatis.vo.User;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : LiJun
 * @date : 2021-08-19 10:39
 **/
public class MybatisTest {

    public static String url = "jdbc:mysql://127.0.0.1:3306/mydb?useUnicode=true&characterEncoding=utf8&useSSL=false";
    public static String user = "root";
    public static String pwd = "root";
    public static String driver = "com.mysql.jdbc.Driver";

    public static void main(String[] args) throws SQLException, IOException {
        //jdbc 连接数据库
//        testJdbc();
        //mybatis
        mybatisTest();


//        InputStream resourceAsStream = Resources.getResourceAsStream(MybatisTest.class.getClassLoader(), "com/lj/mybatis/mapper/UserMapper.xml");
//        URL resource2 = MybatisTest.class.getClassLoader().getResource("UserMapper.xml");
//        System.out.println(resource2);
//        System.out.println(resourceAsStream);


    }

    public static void testJdbc() throws SQLException {
        String sql = "select * from user";
        //创建数据源
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(url);

        //获得数据库连接
        Connection conn = dataSource.getConnection(user, pwd);
        //创建预处理语句
        PreparedStatement st = conn.prepareStatement(sql);
        //执行sql获取结果
        ResultSet rs = st.executeQuery();

        //处理结果集
        while (rs.next()){
            String name = rs.getString("name");
            String age = rs.getString("age");
            System.out.println("name="+name+",age="+age);
        }

        //关闭资源
        rs.close();
        st.close();
        conn.close();
    }

    public static void mybatisTest(){
        DataSource dataSource = new UnpooledDataSource(driver,url,user,pwd);

        //初始化 Configuration
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        //配置扫描路径，扫描 Mapper 接口
//        configuration.addMapper(UserMapper.class);
        configuration.addMappers("com.lj.mybatis");

        //获取 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("com.lj.mybatis.mapper.UserMapper.getUserById", 1);

        System.out.println(JSON.toJSONString(user));
    }
}
