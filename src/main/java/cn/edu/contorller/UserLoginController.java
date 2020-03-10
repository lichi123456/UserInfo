package cn.edu.contorller;

import cn.edu.vo.UserLogin;
import cn.edu.service.UserLoginService;
import cn.edu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserLoginController
 * @Description TODO 用户登录
 * @Author wys5
 * @Date 2020/2/18 9:58
 * @Version 1.0
 **/
@RestController
@RequestMapping("/userLogin")
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;

    /**
     * @Author wys
     * @ClassName getLoginUser
     * @Description //TODO  登录人校验
     * @Date 16:25 2020/3/10
     * @Param [userLogin]
     * @return cn.edu.utils.Result
     **/
    @PostMapping("/")
    public Result getLoginUser(@RequestBody UserLogin userLogin){
        Result result = new Result();
        try{
            result = userLoginService.getLoginUser(userLogin.getUserCode(),userLogin.getUserPassword());
        }catch(Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName getDeleteUser
     * @Description //TODO  获取禁用信息列表-带查询
     * @Date 17:23 2020/3/10
     * @Param [userLogin]
     * @return cn.edu.utils.Result
     **/
    @PostMapping("/deleteList/")
    public Result getDeleteUser(@RequestBody UserLogin userLogin){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("获取禁用信息列表成功");
            result.setObject(userLoginService.getDeleteUserList(userLogin));
        }catch(Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    @PutMapping("/")
    public Result Recover(@RequestBody UserLogin userLogin){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("恢复成功");
            result.setObject(userLoginService.RecoverUser(userLogin));
        }catch(Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    @DeleteMapping("/")
    public Result realDeleteUser(@RequestBody UserLogin userLogin){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("已从数据库删除成功");
            result.setObject(userLoginService.RealDeleteUser(userLogin));
        }catch(Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

}
