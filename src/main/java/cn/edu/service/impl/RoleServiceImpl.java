package cn.edu.service.impl;

import cn.edu.dao.RoleMapper;
import cn.edu.utils.Constant;
import cn.edu.vo.Menu;
import cn.edu.vo.Role;
import cn.edu.vo.RoleMenu;
import cn.edu.service.MenuService;
import cn.edu.service.RoleMenuService;
import cn.edu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Description TODO 角色管理实现类
 * @Author wys5
 * @Date 2020/2/15 20:44
 * @Version 1.0
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MenuService menuService;
    /**
     * @Author wys
     * @ClassName getRole
     * @Description //TODO  根据角色id获取一个角色
     * @Date 16:11 2020/2/17
     * @Param [id]
     * @return cn.edu.vo.Role
     **/
    @Override
    public Role getOneRole(String id) {
        Assert.hasText(id,"角色id不能为空");
        return roleMapper.selectByPrimaryKey(id);
    }

    /**
     * @Author wys
     * @ClassName getRoleByType
     * @Description //TODO  根据角色id获取用户菜单列表
     * @Date 10:36 2020/2/18
     * @Param [roleType]
     * @return cn.edu.utils.Result
     **/
    @Override
    public List<Menu> getMenuByRoleId(String id) {
        Assert.hasText(id,"角色id不能为空");
        List<RoleMenu>roleMenuList = roleMenuService.getRoleMenuListByRoleId(id);//获取角色对应菜单id
        List<Menu>menuList = new ArrayList<>();
        for (RoleMenu m:roleMenuList) {
            Menu menu = menuService.getMenuById(m.getMenuId());
            if(menu.getDeleteStatus().compareTo(Constant.IS_NOT_DELETE)==0){
                menuList.add(menu);
            }
        }
        List<Menu>menuTree = menuService.getMenuTree(menuList);

        return menuTree;
    }


    @Override
    public List<Role> getRoleList() {
        return roleMapper.selectAll();
    }
}
