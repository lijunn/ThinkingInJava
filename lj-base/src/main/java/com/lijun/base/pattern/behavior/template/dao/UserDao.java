package com.lijun.base.pattern.behavior.template.dao;

import com.lijun.base.pattern.behavior.template.JdbcTemplate;
import com.lijun.base.pattern.behavior.template.User;
import com.lijun.base.pattern.behavior.template.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author : LiJun
 * @date : 2020-04-07 18:03
 **/
public class UserDao extends JdbcTemplate<User>  {

    public UserDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<User> selectAll(){
        String sql = "select * from user";

        List<User> users =  executeQuery(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws Exception {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setAvatarPath(rs.getString("avatar_path"));
                user.setEmail(rs.getString("email"));
                user.setEnabled(rs.getBoolean("enabled"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                user.setStoreId(rs.getLong("store_id"));
                user.setPhone(rs.getString("phone"));
                user.setJobId(rs.getLong("job_id"));
                user.setCreateTime(rs.getDate("create_time"));
                user.setLastPasswordResetTime(rs.getDate("last_password_reset_time"));
                return user;
            }
        }, null);

        return users;
    }
}
