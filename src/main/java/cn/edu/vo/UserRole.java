package cn.edu.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`user_role`")
@Getter
@Setter
public class UserRole {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`user_role_id`")
    private Long userRoleId;

    /**
     * 用户登录表主键id
     */
    @Column(name = "`user_id`")
    private String userId;

    /**
     * 角色表id
     */
    @Column(name = "`role_id`")
    private String roleId;

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