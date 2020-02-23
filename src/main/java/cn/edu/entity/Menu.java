package cn.edu.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "`menu`")
@Getter
@Setter
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
     * 子菜单
     */
    @Transient
    private List<Menu> menuList;

}