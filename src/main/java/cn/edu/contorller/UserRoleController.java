package cn.edu.contorller;

import cn.edu.service.UserRoleService;
import cn.edu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserRoleController
 * @Description TODO 用户角色中间表控制类
 * @Author wys5
 * @Date 2020/2/23 19:05
 * @Version 1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/userRole")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;
    /**
     * @Author wys
     * @ClassName getRoleIdByUserId
     * @Description //TODO  根据userId获取当前用户角色id
     * @Date 19:25 2020/2/23
     * @Param [userId]
     * @return cn.edu.utils.Result
     **/
    @GetMapping("/{userId}")
    public Result getRoleIdByUserId(@PathVariable String userId){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("获取roleId成功");
            result.setObject(userRoleService.getUserRoleByUserId(userId));
        }catch(Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
}
