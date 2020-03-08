package cn.edu.service.impl;

import cn.edu.dao.TeacherMapper;
import cn.edu.service.TeacherGroupService;
import cn.edu.service.TeacherService;
import cn.edu.utils.ApplicationUtils;
import cn.edu.utils.Constant;
import cn.edu.vo.Groups;
import cn.edu.vo.Teacher;
import cn.edu.vo.TeacherGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TeacherServiceImpl
 * @Description TODO 教师管理实现类
 * @Author wys5
 * @Date 2020/2/15 20:40
 * @Version 1.0
 **/
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TeacherGroupService teacherGroupService;

    /**
     * @Author wys
     * @ClassName getTeacherList
     * @Description //TODO  获取教师信息列表
     * @Date 15:13 2020/2/29
     * @Param []
     * @return java.util.List<cn.edu.vo.Teacher>
     **/
    @Override
    public List<Teacher> getTeacherList(Teacher teacher,String deleteStatus) {
        List<Teacher>teacherList = null;
        if(deleteStatus.trim().compareTo(Constant.isNotDelete)==0){
            teacherList = teacherMapper.getTeacherListByName(teacher);
        }else if(deleteStatus.trim().compareTo(Constant.isDelete)==0){
            teacherList = teacherMapper.getDelTeacherListByName(teacher);
        }

        List<Teacher>list=new ArrayList<>();
        teacherList.stream().forEach(s->{
            list.add(getOneTeacherById(s.getTeacherId()));
        });
        return list;
    }

    /**
     * @Author wys
     * @ClassName getOneTeacherById
     * @Description //TODO  获取一个教师信息
     * @Date 15:12 2020/2/29
     * @Param [id]
     * @return cn.edu.vo.Teacher
     **/
    @Override
    public Teacher getOneTeacherById(String id) {
        Assert.hasText(id,"教师id不能为空");
        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        List<Groups> groupList = teacherGroupService.getGroupListByTeacherId(id);
        teacher.setGroupsList(groupList);
        String groupName = "";
        for (Groups g:groupList) {
            groupName+=g.getGroupName();
            groupName+="、";
        }
        teacher.setGroupName(groupName);
        return teacher;
    }

    /**
     * @Author wys
     * @ClassName insert
     * @Description //TODO  新增
     * @Date 15:12 2020/2/29
     * @Param [teacher]
     * @return int
     **/
    @Override
    public int insert(Teacher teacher) {
        teacher.setTeacherId(ApplicationUtils.GUID32());
        teacher.setDeleteStatus(Constant.isNotDelete);
        return teacherMapper.insertSelective(teacher);
    }

    /**
     * @Author wys
     * @ClassName update
     * @Description //TODO  更新
     * @Date 15:12 2020/2/29
     * @Param [teacher]
     * @return int
     **/
    @Override
    public int update(Teacher teacher) {
        teacher.setUpdateTime(new Date());
        return teacherMapper.updateByPrimaryKeySelective(teacher);
    }

    /**
     * @Author wys
     * @ClassName delete
     * @Description //TODO  假删除
     * @Date 15:12 2020/2/29
     * @Param [id]
     * @return int
     **/
    @Override
    public int delete(String id) {
        Assert.hasText(id,"教师id不能为空");
        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        teacher.setUpdateTime(new Date());
        teacher.setDeleteStatus(Constant.isDelete);
        return teacherMapper.updateByPrimaryKeySelective(teacher);
    }

    /**
     * @Author wys
     * @ClassName realDel
     * @Description //TODO  真删除
     * @Date 15:12 2020/2/29
     * @Param [id]
     * @return int
     **/
    @Override
    public int realDel(String id) {
        Assert.hasText(id,"id不能为空");
        return teacherMapper.deleteByPrimaryKey(id);
    }
}
