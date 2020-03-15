package cn.edu.service;

import cn.edu.vo.Groups;

import java.util.List;

/**
 * @ClassName GroupsService
 * @Description TODO 分组管理接口
 * @Author wys5
 * @Date 2020/2/15 20:41
 * @Version 1.0
 **/
public interface GroupsService {
    List<Groups>getGroupList();
    Groups getOneGroupById(String groupId);
    int insert(Groups groups);
    int delete(String groupId);
    int update(Groups groups);
}
