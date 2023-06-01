package com.lijun.base.pattern.behavior.template;

import java.util.Date;

/**
 * Created by Tom on 2019/3/16.
 */
public class User {

    //id
    private Long id;

    //头像
    private String avatarPath;

    //邮箱
    private String email;

    //"状态：1启用、0禁用"
    private Boolean enabled;

    //密码
    private String password;

    //用户名
    private String username;

    //诊所id
    private Long storeId;

    //手机号
    private String phone;

    //岗位id，关联job表
    private Long jobId;

    //创建日期
    private Date createTime;

    //最后修改密码的日期
    private Date lastPasswordResetTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastPasswordResetTime() {
        return lastPasswordResetTime;
    }

    public void setLastPasswordResetTime(Date lastPasswordResetTime) {
        this.lastPasswordResetTime = lastPasswordResetTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", avatarPath='" + avatarPath + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", storeId=" + storeId +
                ", phone='" + phone + '\'' +
                ", jobId=" + jobId +
                ", createTime=" + createTime +
                ", lastPasswordResetTime=" + lastPasswordResetTime +
                '}';
    }
}
