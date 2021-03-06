package cn.edu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "`role_menu`")
@Getter
@Setter
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
     * 将要操作的菜单列表
     */
   @Transient
    private List<Menu>menuList;

    /**
     * 操作人（待使用）
     */
   @Transient
    private String operator;
    /**
     * 存放额外信息
     */
    @Transient
    private Object object;
}