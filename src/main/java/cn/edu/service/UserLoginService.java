package cn.edu.service;

import cn.edu.utils.Result;
import cn.edu.vo.UserLogin;

/**
 * @ClassName UserLoginService
 * @Description TODO 用户登录接口
 * @Author wys5
 * @Date 2020/2/15 20:43
 * @Version 1.0
 **/
public interface UserLoginService {
    Result getLoginUser(String code, String password);
    String insert(UserLogin userLogin);
    int updatePasswordByUserCode(String userId,String newPassword);
    String getPasswordById(String id);
}
