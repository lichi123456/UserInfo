package cn.edu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
     * 用户类型名称
     */
    @Transient
    private String userTypeName;
    /**
     * 电话号码
     */
    @Transient
    private String tel;

    /**
     * qq
     */
    @Transient
    private String qq;

    /**
     * 邮箱
     */
    @Transient
    private String email;
    /**
     * 最后一次登录时间
     */
    @Column(name = "`last_login`")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastLogin;

    /**
     * 假删除标识（已删除：Y，使用：N）
     */
    @Column(name = "`delete_status`")
    private String deleteStatus;
    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 修改人
     */
    @Column(name = "`update_user`")
    private String updateUser;

    /**
     * 模糊查询
     */
    @Transient
    private String condition;
    /**
     * 存放额外信息
     */
    @Transient
    private Object object;
}