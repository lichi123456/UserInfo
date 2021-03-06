package cn.edu.service;

import cn.edu.vo.Menu;

import java.util.List;

/**
 * @ClassName MenuService
 * @Description TODO 菜单管理接口
 * @Author wys5
 * @Date 2020/2/15 20:45
 * @Version 1.0
 **/
public interface MenuService {
    List<Menu> getMenuList();
    Menu getMenuById(String menuId);
    List<Menu> getChildList(String menuParentId);
    List<Menu> getMenuTree(List<Menu>menuList);
    int deleteMenu(String menuId);
    int recoverMenu(String menuId);
}
