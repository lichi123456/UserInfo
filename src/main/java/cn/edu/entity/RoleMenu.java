package cn.edu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`role_menu`")
public class RoleMenu {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`role_menu_id`")
    private Long roleMenuId;

    /**
     * 角色主键id
     */
    @Column(name = "`role_id`")
    private String roleId;

    /**
     * 菜单主键id
     */
    @Column(name = "`menu_id`")
    private String menuId;

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
     * @return role_menu_id - 主键id
     */
    public Long getRoleMenuId() {
        return roleMenuId;
    }

    /**
     * 设置主键id
     *
     * @param roleMenuId 主键id
     */
    public void setRoleMenuId(Long roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    /**
     * 获取角色主键id
     *
     * @return role_id - 角色主键id
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色主键id
     *
     * @param roleId 角色主键id
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * 获取菜单主键id
     *
     * @return menu_id - 菜单主键id
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单主键id
     *
     * @param menuId 菜单主键id
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
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