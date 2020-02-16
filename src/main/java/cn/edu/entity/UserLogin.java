package cn.edu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`user_login`")
public class UserLogin {
    /**
     * 用户登录表主键id
     */
    @Id
    @Column(name = "`user_id`")
    private String userId;

    /**
     * 学号/工号
     */
    @Column(name = "`user_code`")
    private String userCode;

    @Column(name = "`user_password`")
    private String userPassword;

    @Column(name = "`user_name`")
    private String userName;

    /**
     * 最后一次登录时间
     */
    @Column(name = "`last_login`")
    private Date lastLogin;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "`create_user`")
    private String createUser;

    /**
     * 修改日期
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 修改人
     */
    @Column(name = "`update_user`")
    private String updateUser;

    /**
     * 获取用户登录表主键id
     *
     * @return user_id - 用户登录表主键id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户登录表主键id
     *
     * @param userId 用户登录表主键id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取学号/工号
     *
     * @return user_code - 学号/工号
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置学号/工号
     *
     * @param userCode 学号/工号
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * @return user_password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取最后一次登录时间
     *
     * @return last_login - 最后一次登录时间
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     * 设置最后一次登录时间
     *
     * @param lastLogin 最后一次登录时间
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 获取修改日期
     *
     * @return update_time - 修改日期
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改日期
     *
     * @param updateTime 修改日期
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取修改人
     *
     * @return update_user - 修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置修改人
     *
     * @param updateUser 修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }
}