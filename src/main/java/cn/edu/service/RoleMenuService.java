package cn.edu.service;

import cn.edu.vo.RoleMenu;

import java.util.List;

/**
 * @ClassName RoleMenuService
 * @Description TODO 角色菜单中间表接口
 * @Author wys5
 * @Date 2020/2/15 20:45
 * @Version 1.0
 **/
public interface RoleMenuService {
    List<RoleMenu>getRoleMenuListByRoleId(String roleId);
}
