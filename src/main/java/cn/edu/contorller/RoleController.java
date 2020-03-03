package cn.edu.contorller;

import cn.edu.service.MenuService;
import cn.edu.service.RoleMenuService;
import cn.edu.service.RoleService;
import cn.edu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RoleController
 * @Description TODO 角色管理
 * @Author wys5
 * @Date 2020/2/18 10:09
 * @Version 1.0
 **/
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private MenuService menuService;

    /**
     * @Author wys
     * @ClassName getRole
     * @Description //TODO  根据id获取权限配置，当前为测试阶段
     * @Date 10:26 2020/2/18
     * @Param []
     * @return cn.edu.utils.Result
     **/
    @GetMapping("/{roleId}")
    public Result getRoleByRoleId(@PathVariable String roleId){
        Result result = new Result();
        try{
            /**
             * 12A4803ED6CE4FA5B0643E682A219105 admin
             * ECA8953FA7134ADA8EFC76584EF4658D 学生
             * 662B5AC9668C4241977B0B9E67AF4ED1 教师
             */
            result.setObject(roleService.getMenuByroleId(roleId));
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }
}
