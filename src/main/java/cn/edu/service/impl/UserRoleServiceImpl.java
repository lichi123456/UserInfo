package cn.edu.service.impl;

import cn.edu.dao.UserRoleMapper;
import cn.edu.vo.UserRole;
import cn.edu.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName UserRoleServiceImpl
 * @Description TODO 用户角色中间表管理实现类
 * @Author wys5
 * @Date 2020/2/15 20:44
 * @Version 1.0
 **/
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public UserRole getUserRoleByUserId(String userId) {
        Assert.hasText(userId,"userId不能为空");
        Example example = new Example(UserRole.class);
        example.and().andEqualTo("userId",userId);
        return userRoleMapper.selectOneByExample(example);
    }
}
