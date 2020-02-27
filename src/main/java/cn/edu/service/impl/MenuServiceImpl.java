package cn.edu.service.impl;

import cn.edu.dao.MenuMapper;
import cn.edu.vo.Menu;
import cn.edu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MenuServiceImpl
 * @Description TODO 菜单管理实现类
 * @Author wys5
 * @Date 2020/2/15 20:45
 * @Version 1.0
 **/
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    /**
     * @Author wys
     * @ClassName getMenuList
     * @Description //TODO  获取全部menu
     * @Date 10:16 2020/2/18
     * @Param []
     * @return java.util.List<cn.edu.vo.Menu>
     **/
    @Override
    public List<Menu> getMenuList() {
        return menuMapper.selectAll();
    }

    @Override
    public Menu getMenuById(String menuId) {
        /**
         * @Author wys
         * @ClassName getMenu
         * @Description //TODO 根据Id获取一个菜单
         * @Date 10:22 2020/2/18
         * @Param [menuId]
         * @return cn.edu.vo.Menu
         **/
        return menuMapper.selectByPrimaryKey(menuId);
    }
}
