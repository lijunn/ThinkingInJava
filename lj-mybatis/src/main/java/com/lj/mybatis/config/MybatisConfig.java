package com.lj.mybatis.config;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author : LiJun
 * @date : 2021-08-20 11:28
 **/
@Configuration
public class MybatisConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;


    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        DataSource dataSource = new UnpooledDataSource(driver,url,username,password);
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //设置数据源
        factoryBean.setDataSource(dataSource);
        //设置 Mapper.xml 资源位置
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperResources = patternResolver.getResources("classpath*:mapper/*");
        factoryBean.setMapperLocations(mapperResources);

        return factoryBean.getObject();
    }
}
