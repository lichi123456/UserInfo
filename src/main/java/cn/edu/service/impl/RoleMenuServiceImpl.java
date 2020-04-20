package cn.edu.service.impl;

import cn.edu.dao.RoleMenuMapper;
import cn.edu.vo.RoleMenu;
import cn.edu.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName RoleMenuServiceImpl
 * @Description TODO 角色菜单管理实现类
 * @Author wys5
 * @Date 2020/2/15 20:45
 * @Version 1.0
 **/
@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     * @Author wys
     * @ClassName getRoleMenuListByRoleId
     * @Description //TODO  根据角色id获取其菜单id
     * @Date 10:15 2020/2/18
     * @Param [roleId]
     * @return java.util.List<cn.edu.vo.RoleMenu>
     **/
    @Override
    public List<RoleMenu> getRoleMenuListByRoleId(String roleId) {
        Assert.hasText(roleId,"角色id不能为空");
        Example example = new Example(RoleMenu.class);
        example.and().andEqualTo("roleId",roleId);
        return roleMenuMapper.selectByExample(example);
    }

}
