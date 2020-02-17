package cn.edu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`menu`")
public class Menu {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`menu_id`")
    private String menuId;

    /**
     * 前端菜单名字
     */
    @Column(name = "`menu_title`")
    private String menuTitle;

    /**
     * 前端菜单路径
     */
    @Column(name = "`menu_path`")
    private String menuPath;

    /**
     * 样式
     */
    @Column(name = "`menu_icon`")
    private String menuIcon;

    /**
     * 父节点id（根节点为-1）
     */
    @Column(name = "`menu_parent_id`")
    private String menuParentId;

    /**
     * 状态标识，N：禁用，Y：启用
     */
    @Column(name = "`menu_status`")
    private String menuStatus;

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
     * @return menu_id - 主键id
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置主键id
     *
     * @param menuId 主键id
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    /**
     * 获取前端菜单名字
     *
     * @return menu_title - 前端菜单名字
     */
    public String getMenuTitle() {
        return menuTitle;
    }

    /**
     * 设置前端菜单名字
     *
     * @param menuTitle 前端菜单名字
     */
    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle == null ? null : menuTitle.trim();
    }

    /**
     * 获取前端菜单路径
     *
     * @return menu_path - 前端菜单路径
     */
    public String getMenuPath() {
        return menuPath;
    }

    /**
     * 设置前端菜单路径
     *
     * @param menuPath 前端菜单路径
     */
    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath == null ? null : menuPath.trim();
    }

    /**
     * 获取样式
     *
     * @return menu_icon - 样式
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * 设置样式
     *
     * @param menuIcon 样式
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    /**
     * 获取父节点id（根节点为-1）
     *
     * @return menu_parent_id - 父节点id（根节点为-1）
     */
    public String getMenuParentId() {
        return menuParentId;
    }

    /**
     * 设置父节点id（根节点为-1）
     *
     * @param menuParentId 父节点id（根节点为-1）
     */
    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId == null ? null : menuParentId.trim();
    }

    /**
     * 获取状态标识，N：禁用，Y：启用
     *
     * @return menu_status - 状态标识，N：禁用，Y：启用
     */
    public String getMenuStatus() {
        return menuStatus;
    }

    /**
     * 设置状态标识，N：禁用，Y：启用
     *
     * @param menuStatus 状态标识，N：禁用，Y：启用
     */
    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus == null ? null : menuStatus.trim();
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