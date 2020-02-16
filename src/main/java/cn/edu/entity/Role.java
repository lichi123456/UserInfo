package cn.edu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`role`")
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
     * 角色类型（1：学生，2：教师，0：管理员）
     */
    @Column(name = "`role_type`")
    private Integer roleType;

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
     * 获取主键id
     *
     * @return role_id - 主键id
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置主键id
     *
     * @param roleId 主键id
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * 获取角色类型名称:学生、教师、管理员..
     *
     * @return role_name - 角色类型名称:学生、教师、管理员..
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色类型名称:学生、教师、管理员..
     *
     * @param roleName 角色类型名称:学生、教师、管理员..
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 获取角色类型（1：学生，2：教师，0：管理员）
     *
     * @return role_type - 角色类型（1：学生，2：教师，0：管理员）
     */
    public Integer getRoleType() {
        return roleType;
    }

    /**
     * 设置角色类型（1：学生，2：教师，0：管理员）
     *
     * @param roleType 角色类型（1：学生，2：教师，0：管理员）
     */
    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
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