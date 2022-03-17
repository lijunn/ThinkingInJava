package com.lj.mybatis.mapper;

import com.lj.mybatis.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : LiJun
 * @date : 2021-08-19 16:11
 **/
//@Mapper
public interface UserMapper {

    User getUserById(Long id);
}