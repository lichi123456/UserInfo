package cn.edu.contorller;

import cn.edu.entity.Menu;
import cn.edu.entity.Role;
import cn.edu.entity.RoleMenu;
import cn.edu.service.MenuService;
import cn.edu.service.RoleMenuService;
import cn.edu.service.RoleService;
import cn.edu.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName RoleMenuController
 * @Description TODO 用来插入角色与菜单之间的id联系
 * @Author wys5
 * @Date 2020/2/17 15:07
 * @Version 1.0
 **/
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;


}
