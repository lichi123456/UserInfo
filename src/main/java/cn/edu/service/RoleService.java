package cn.edu.service;

import cn.edu.entity.Menu;
import cn.edu.entity.Role;
import cn.edu.utils.Result;

import java.util.List;

/**
 * @ClassName RoleService
 * @Description TODO 角色管理接口
 * @Author wys5
 * @Date 2020/2/15 20:44
 * @Version 1.0
 **/
public interface RoleService {
    Role getOneRole(String id);

    List<Menu> getRoleByroleId(String id);
}
