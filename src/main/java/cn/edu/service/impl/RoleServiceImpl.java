package cn.edu.service.impl;

import cn.edu.dao.RoleMapper;
import cn.edu.entity.Menu;
import cn.edu.entity.Role;
import cn.edu.entity.RoleMenu;
import cn.edu.service.MenuService;
import cn.edu.service.RoleMenuService;
import cn.edu.service.RoleService;
import cn.edu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sun.reflect.generics.tree.Tree;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

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
     * @return cn.edu.entity.Role
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
    public List<Menu> getRoleByroleId(String id) {
        Assert.hasText(id,"角色id不能为空");
        List<RoleMenu>roleMenuList = roleMenuService.getRoleMenuListByRoleId(id);//获取角色对应菜单id
        List<Menu>menuList = new ArrayList<>();
        for (RoleMenu m:roleMenuList
             ) {
            Menu menu = menuService.getMenuById(m.getMenuId());
            menuList.add(menu);
        }
        List<Menu>menuTree = new ArrayList<>();
        for (Menu m:menuList ) {
            if(m.getMenuParentId().compareTo("-1")==0){//当当前节点为root节点时
                List<Menu>list1 =new ArrayList<>();
                for(Menu mc:menuList){
                    if(mc.getMenuParentId().compareTo(m.getMenuId())==0){//子节点的父节点为当前节点，则进入当前节点列表
                        list1.add(mc);
                    }else if(mc.getMenuParentId().compareTo("-1")==0){
                        continue;
                    }
                }
                m.setMenuList(list1);
                menuTree.add(m);
            }
        }
        List<Menu>list1 = new ArrayList<>();
        //排序
        menuTree.stream().sorted(Comparator.comparing(Menu::getMenuPath)).forEach(e->{
            list1.add(e);
        });
        return list1;
    }
}
