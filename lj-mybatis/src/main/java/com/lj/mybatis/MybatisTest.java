package com.lj.mybatis;

import com.alibaba.fastjson.JSON;
import com.lj.mybatis.mapper.UserMapper;
import com.lj.mybatis.vo.User;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
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

    public static void mybatisTest() throws IOException {
        //初始化数据源
        DataSource dataSource = new UnpooledDataSource(driver,url,user,pwd);
        //初始化 Configuration
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);

        //配置扫描路径，扫描解析 Mapper 接口
        configuration.addMappers("com.lj.mybatis.mapper");
        //扫描解析 Mapper.xml 文件
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperResources = patternResolver.getResources("classpath*:mapper/*");
        for (Resource resource : mapperResources) {
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(resource.getInputStream(),
                    configuration, resource.toString(), configuration.getSqlFragments());
            xmlMapperBuilder.parse();
        }

        //创建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        //获取 session 、Mapper 代理对象，执行方法
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.getMapper(UserMapper.class).getUserById(1L);

        System.out.println(JSON.toJSONString(user));
    }
}
