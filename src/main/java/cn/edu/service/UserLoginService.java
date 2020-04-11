package cn.edu.service;

import cn.edu.utils.Result;
import cn.edu.vo.UserLogin;

import java.util.List;

/**
 * @ClassName UserLoginService
 * @Description TODO 用户登录接口
 * @Author wys5
 * @Date 2020/2/15 20:43
 * @Version 1.0
 **/
public interface UserLoginService {
    Result getLoginUser(String code, String password) throws Exception;
    String insert(UserLogin userLogin) throws Exception;
    int updatePasswordByUserCode(String userId,String newPassword) throws Exception;
    String getPasswordById(String id) throws Exception;
    List<UserLogin> getDeleteUserList(UserLogin userLogin) throws Exception;
    int RecoverUser(UserLogin userLogin) throws Exception;
    UserLogin getUserLoginById(String id) throws Exception;
    int RealDeleteUser(UserLogin userLogin);
}
