package cn.edu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`role`")
@Getter
@Setter
public class Role {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`role_id`")
    private String roleId;

    /**
     * 角色类型名称:学生、教师、管理员..
     */
    @Column(name = "`role_name`")
    private String roleName;

    /**
     * 0:管理员，1：学生，2：教师
     */
    @Column(name = "`role_type`")
    private String roleType;
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
     * 存放额外信息
     */
    @Transient
    private Object object;
}