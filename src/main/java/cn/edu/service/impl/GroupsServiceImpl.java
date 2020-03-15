package cn.edu.service.impl;

import cn.edu.Application;
import cn.edu.dao.GroupsMapper;
import cn.edu.service.GroupsService;
import cn.edu.utils.ApplicationUtils;
import cn.edu.vo.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @ClassName GroupsServiceImpl
 * @Description TODO 组别管理实现类
 * @Author wys5
 * @Date 2020/2/15 20:42
 * @Version 1.0
 **/
@Service
public class
GroupsServiceImpl implements GroupsService {
    @Autowired
    private GroupsMapper groupsMapper;
    @Override
    public List<Groups> getGroupList() {
        return groupsMapper.selectAll();
    }

    @Override
    public Groups getOneGroupById(String groupId) {
        /**
         * @Author wys
         * @ClassName getOneGroupById
         * @Description //TODO  根据小组id获取小组信息
         * @Date 10:12 2020/3/6
         * @Param [groupId]
         * @return cn.edu.vo.Groups
         **/
        Assert.hasText(groupId,"小组id不能为空");
        return groupsMapper.selectByPrimaryKey(groupId);
    }

    /**
     * @Author wys
     * @ClassName insert
     * @Description //TODO 新增
     * @Date 10:16 2020/3/15
     * @Param [groups]
     * @return int
     **/
    @Override
    public int insert(Groups groups) {
        Assert.hasText(groups.getGroupName(),"小组名称不能为空");
        groups.setGroupId(ApplicationUtils.GUID32());
        return groupsMapper.insertSelective(groups);
    }

    /**
     * @Author wys
     * @ClassName delete
     * @Description //TODO  删除
     * @Date 10:16 2020/3/15
     * @Param [groupId]
     * @return int
     **/
    @Override
    public int delete(String groupId) {
        Assert.hasText(groupId,"小组id不能为空");
        return groupsMapper.deleteByPrimaryKey(groupId);
    }

    /**
     * @Author wys
     * @ClassName 修改
     * @Description //TODO
     * @Date 10:16 2020/3/15
     * @Param [groups]
     * @return int
     **/
    @Override
    public int update(Groups groups) {
        Assert.hasText(groups.getGroupId(),"小组id不能为空");
        Assert.hasText(groups.getGroupName(),"小组名称不能为空");
        return groupsMapper.updateByPrimaryKeySelective(groups);
    }
}
