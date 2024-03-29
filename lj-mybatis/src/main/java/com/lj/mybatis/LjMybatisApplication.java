package com.lj.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author lijun
 */
@MapperScan({"com.lj.mybatis.mapper"})
@EnableTransactionManagement
@SpringBootApplication
public class LjMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(LjMybatisApplication.class, args);
    }

    @Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager) {
        System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
        return new Object();
    }
}
