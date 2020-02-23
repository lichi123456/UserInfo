package cn.edu.service;

import cn.edu.entity.UserRole;

/**
 * @ClassName UserRoleService
 * @Description TODO 用户角色中间表管理接口
 * @Author wys5
 * @Date 2020/2/15 20:43
 * @Version 1.0
 **/
public interface UserRoleService {
    UserRole getUserRoleByUserId(String userId);
}
