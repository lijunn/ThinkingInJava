package com.lj.mybatis.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : LiJun
 * @date : 2021-08-19 16:13
 **/
@Data
public class User implements Serializable {

    String name;
    Integer age;
}
