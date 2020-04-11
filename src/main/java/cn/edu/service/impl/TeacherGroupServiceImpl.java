package cn.edu.service.impl;

import cn.edu.dao.TeacherGroupMapper;
import cn.edu.service.GroupsService;
import cn.edu.service.TeacherGroupService;
import cn.edu.service.TeacherService;
import cn.edu.vo.Groups;
import cn.edu.vo.Teacher;
import cn.edu.vo.TeacherGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TeacherGroupServiceImpl
 * @Description TODO 教师小组中间表管理实现类
 * @Author wys5
 * @Date 2020/2/15 20:41
 * @Version 1.0
 **/
@Service
public class TeacherGroupServiceImpl implements TeacherGroupService {
    @Autowired
    private TeacherGroupMapper teacherGroupMapper;

    @Autowired
    private GroupsService groupsService;

    @Autowired
    private TeacherService teacherService;
    /**
     * @Author wys
     * @ClassName getGroupListByTeacherId
     * @Description //TODO  通过教师id获取其指导的小组
     * @Date 9:39 2020/3/3
     * @Param [teacherId]
     * @return java.util.List<cn.edu.vo.TeacherGroup>
     **/
    @Override
    public List<Groups> getGroupListByTeacherId(String teacherId) {
        Assert.hasText(teacherId,"教师id不能为空");
        List<TeacherGroup>teacherGroups = getTeacherGroupByTeacherId(teacherId);
        List<Groups>groupsList = new ArrayList<>();
        for (TeacherGroup tg: teacherGroups) {
            groupsList.add(groupsService.getOneGroupById(tg.getGroupId()));
        }
        return groupsList;
    }

    /**
     * @Author wys
     * @ClassName getTeacherListByGroupId
     * @Description //TODO  通过组别id获取其指导老师id
     * @Date 9:40 2020/3/3
     * @Param [groupId]
     * @return java.util.List<cn.edu.vo.TeacherGroup>
     **/
    @Override
    public List<Teacher> getTeacherListByGroupId(String groupId) throws Exception {
        Assert.hasText(groupId,"组别id不能为空");
        Example example = new Example(TeacherGroup.class);
        example.and().andEqualTo("groupId",groupId);
        List<TeacherGroup>teacherGroups = teacherGroupMapper.selectByExample(example);
        List<Teacher>teachers = new ArrayList<>();
        for (TeacherGroup tg:teacherGroups) {
            teachers.add(teacherService.getOneTeacherById(tg.getTeacherId()));
        }
        return teachers;
    }


    /**
     * @Author wys
     * @ClassName insert
     * @Description //TODO 新增
     * @Date 13:09 2020/3/8
     * @Param [teacherGroup]
     * @return int
     **/
    @Override
    public int insert(TeacherGroup teacherGroup) {
        Assert.hasText(teacherGroup.getGroupId(),"组别id不能为空");
        Assert.hasText(teacherGroup.getTeacherId(),"教师id不能为空");
        return teacherGroupMapper.insertSelective(teacherGroup);
    }

    /**
     * @Author wys
     * @ClassName delete
     * @Description //TODO 删除
     * @Date 12:00 2020/3/8
     * @Param [id]
     * @return int
     **/
    @Override
    public int delete(Long id) {
        return teacherGroupMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Author wys
     * @ClassName getTeacherGroupByTeacherId
     * @Description //TODO  根据teacherId获取teacherGroup列表
     * @Date 13:14 2020/3/8
     * @Param [teacherId]
     * @return java.util.List<cn.edu.vo.TeacherGroup>
     **/
    @Override
    public List<TeacherGroup> getTeacherGroupByTeacherId(String teacherId) {
        Example example = new Example(TeacherGroup.class);
        example.and().andEqualTo("teacherId",teacherId);
        return teacherGroupMapper.selectByExample(example);
    }

    /**
     * @Author wys
     * @ClassName deleteByTeacherIdAndGroupId
     * @Description //TODO  根据教师id与组别id删除
     * @Date 18:10 2020/3/8
     * @Param [teacherGroup]
     * @return int
     **/
    @Override
    public int deleteByTeacherIdAndGroupId(TeacherGroup teacherGroup) {
        Assert.hasText(teacherGroup.getTeacherId(),"教师id不能为空");
        Assert.hasText(teacherGroup.getGroupId(),"小组id不能为空");
        Example example = new Example(TeacherGroup.class);
        example.and().andEqualTo("teacherId",teacherGroup.getTeacherId());
        example.and().andEqualTo("groupId",teacherGroup.getGroupId());

        return teacherGroupMapper.deleteByExample(example);
    }


}
