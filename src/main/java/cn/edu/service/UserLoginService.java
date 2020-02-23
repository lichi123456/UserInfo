package cn.edu.service;

import cn.edu.entity.UserLogin;
import cn.edu.utils.Result;
import org.apache.ibatis.mapping.ResultMap;

/**
 * @ClassName UserLoginService
 * @Description TODO 用户登录接口
 * @Author wys5
 * @Date 2020/2/15 20:43
 * @Version 1.0
 **/
public interface UserLoginService {
    Result getLoginUser(String code, String password);

}
