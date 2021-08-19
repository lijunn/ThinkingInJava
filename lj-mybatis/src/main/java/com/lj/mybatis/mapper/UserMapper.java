package com.lj.mybatis.mapper;

import com.lj.mybatis.vo.User;

import java.util.List;

/**
 * @author : LiJun
 * @date : 2021-08-19 16:11
 **/
public interface UserMapper {

    User getUserById(Long id);
}
