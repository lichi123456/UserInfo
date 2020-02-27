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
}
