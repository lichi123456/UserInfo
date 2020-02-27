package cn.edu.service.impl;

import cn.edu.dao.UserLoginMapper;
import cn.edu.vo.UserLogin;
import cn.edu.service.UserLoginService;
import cn.edu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName UserLoginServiceImpl
 * @Description TODO 用户登录管理实现类
 * @Author wys5
 * @Date 2020/2/15 20:43
 * @Version 1.0
 **/
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginMapper userLoginMapper;

    /**
     * @Author wys
     * @ClassName getLoginUser
     * @Description //TODO  登录验证
     * @Date 15:49 2020/2/23
     * @Param [code, password]
     * @return cn.edu.utils.Result
     **/
    @Override
    public Result getLoginUser(String code, String password) {
        Assert.hasText(code,"账号不能为空");
        Assert.hasText(password,"密码不能为空");
        //根据code获取登录信息表信息
        Example example = new Example(UserLogin.class);
        example.and().andEqualTo("userCode",code);
        UserLogin userLogin = userLoginMapper.selectOneByExample(example);

        Result result = new Result();
        if(StringUtils.isEmpty(userLogin)){
            result.setSuccess(false);
            result.setMessage("没有此账号");
        }else{
            if (userLogin.getUserPassword().compareTo(password)!=0){
                result.setSuccess(false);
                result.setMessage("密码错误");
            }else{
                result.setSuccess(true);
                result.setObject(userLogin);
                result.setMessage("密码正确");
            }
        }
        return result;
    }
}
