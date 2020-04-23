package cn.edu.contorller;

import cn.edu.service.MenuService;
import cn.edu.utils.Result;
import cn.edu.vo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName MenuController
 * @Description TODO
 * @Author wys5
 * @Date 2020/4/21 16:19
 * @Version 1.0
 **/
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * @Author wys
     * @ClassName getMenuList
     * @Description //TODO  获取所有菜单列表
     * @Date 22:28 2020/4/21
     * @Param []
     * @return cn.edu.utils.Result
     **/
    @GetMapping("/list/")
    public Result getMenuList(){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("获取菜单列表成功");
            result.setObject(menuService.getMenuList());
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName getChildMenuList
     * @Description //TODO  获取子菜单
     * @Date 22:29 2020/4/21
     * @Param [menuParentId]
     * @return cn.edu.utils.Result
     **/
    @PostMapping("/{menuParentId}")
    public Result getChildMenuList(@PathVariable String menuParentId){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("获取子菜单成功");
            result.setObject(menuService.getChildList(menuParentId));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * @Author wys
     * @ClassName changeMenuDeleteStatus
     * @Description //TODO  禁用当前菜单
     * @Date 22:29 2020/4/21
     * @Param [menuId]
     * @return cn.edu.utils.Result
     **/
    @PutMapping("/delete/{menuId}")
    public Result changeMenuDeleteStatus(@PathVariable String menuId){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("禁用菜单成功");
            result.setObject(menuService.deleteMenu(menuId));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    @PutMapping("/recover/{menuId}")
    public Result recoverMenu(@PathVariable String menuId){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("启用菜单成功");
            result.setObject(menuService.recoverMenu(menuId));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
}
