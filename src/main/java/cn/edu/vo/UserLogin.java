package cn.edu.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`user_login`")
@Getter
@Setter
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
    /**
     * 密码
     */
    @Column(name = "`user_password`")
    private String userPassword;
    /**
     * 用户名
     */
    @Column(name = "`user_name`")
    private String userName;
    /**
     * 用户类型
     */
    @Column(name = "`user_type`")
    private String userType;
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
     * 存放额外信息
     */
    @Transient
    private Object object;
}