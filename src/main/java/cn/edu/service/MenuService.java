package cn.edu.service;

import cn.edu.entity.Menu;

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
}
