package cn.edu.service.impl;

import cn.edu.dao.GroupsMapper;
import cn.edu.service.GroupsService;
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
public class GroupsServiceImpl implements GroupsService {
    @Autowired
    private GroupsMapper groupsMapper;
    @Override
    public List<Groups> getGroupList() {
        return groupsMapper.selectAll();
    }

    @Override
    public Groups getOneGroupById(String groupId) {
        Assert.hasText(groupId,"小组id不能为空");
        return groupsMapper.selectByPrimaryKey(groupId);
    }
}
