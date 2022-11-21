package com.lj.mybatis.service;

import com.lj.mybatis.vo.User;

/**
 * @author : LiJun
 * @date : 2022-11-21 17:01
 **/
public interface UserService {

    User getUserById(Long id);

    void addUser(User user) ;
}
