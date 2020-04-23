package cn.edu.service.impl;

import cn.edu.dao.MenuMapper;
import cn.edu.utils.Constant;
import cn.edu.vo.Menu;
import cn.edu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Comparator;
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
        List<Menu>menuList = menuMapper.selectAll();
        return getMenuTree(menuList);
    }

    /**
     * @Author wys
     * @ClassName getMenu
     * @Description //TODO 根据Id获取一个菜单
     * @Date 10:22 2020/2/18
     * @Param [menuId]
     * @return cn.edu.vo.Menu
     **/
    @Override
    public Menu getMenuById(String menuId) {
        Assert.hasText(menuId,"菜单id不能为空");
        return menuMapper.selectByPrimaryKey(menuId);
    }

    /**
     * @Author wys
     * @ClassName getChildList
     * @Description //TODO  根据父id获取子list
     * @Date 18:40 2020/4/21
     * @Param [menuParentId]
     * @return java.util.List<cn.edu.vo.Menu>
     **/
    @Override
    public List<Menu> getChildList(String menuParentId) {
        Assert.hasText(menuParentId,"菜单父id不能为空");
        Example example = new Example(Menu.class);
        example.and().andEqualTo("menuParentId",menuParentId);
        return menuMapper.selectByExample(menuParentId);
    }
    /**
     * @Author wys
     * @ClassName getMenuTree
     * @Description //TODO  菜单树组装
     * @Date 18:51 2020/4/21
     * @Param [menuList]
     * @return java.util.List<cn.edu.vo.Menu>
     **/
    @Override
    public List<Menu> getMenuTree(List<Menu>menuList){
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
        menuTree.stream().sorted(Comparator.comparing(Menu::getMenuPath)).forEach(e-> list1.add(e));
        return list1;
    }

    /**
     * @Author wys
     * @ClassName deleteMenu
     * @Description //TODO  禁用菜单
     * @Date 20:41 2020/4/21
     * @Param [menu]
     * @return int
     **/
    @Override
    public int deleteMenu(String menuId) {
        Assert.hasText(menuId,"菜单id不能为空");
        Menu menu = menuMapper.selectByPrimaryKey(menuId);
        menu.setDeleteStatus(Constant.IS_DELETE);
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    /**
     * @Author wys
     * @ClassName recoverMenu
     * @Description //TODO  启用菜单
     * @Date 22:30 2020/4/21
     * @Param [menuId]
     * @return int
     **/
    @Override
    public int recoverMenu(String menuId){
        Assert.hasText(menuId,"菜单id不能为空");
        Menu menu = menuMapper.selectByPrimaryKey(menuId);
        menu.setDeleteStatus(Constant.IS_NOT_DELETE);
        return menuMapper.updateByPrimaryKeySelective(menu);
    }
}
