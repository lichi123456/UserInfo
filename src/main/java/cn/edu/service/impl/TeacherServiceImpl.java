package cn.edu.service.impl;

import cn.edu.dao.TeacherMapper;
import cn.edu.service.TeacherGroupService;
import cn.edu.service.TeacherService;
import cn.edu.service.UserLoginService;
import cn.edu.utils.ApplicationUtils;
import cn.edu.utils.Constant;
import cn.edu.vo.Groups;
import cn.edu.vo.Teacher;
import cn.edu.vo.TeacherGroup;
import cn.edu.vo.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

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

    @Autowired
    private UserLoginService userLoginService;
    /**
     * @Author wys
     * @ClassName getTeacherList
     * @Description //TODO  获取教师信息列表带模糊查询
     * @Date 15:13 2020/2/29
     * @Param []
     * @return java.util.List<cn.edu.vo.Teacher>
     **/
    @Override
    public List<Teacher> getTeacherListWithConditionAndDeleteStatus(Teacher teacher,String deleteStatus) {
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

    @Override
    public List<Teacher> getTeacherList() {
        Example example = new Example(Teacher.class);
        example.and().andEqualTo("deleteStatus",Constant.isNotDelete);
        return teacherMapper.selectByExample(example);
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
        teacher.setPassword(userLoginService.getPasswordById(id));
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
        UserLogin userLogin = new UserLogin();
        userLogin.setUserId(teacher.getTeacherId());
        userLogin.setUserCode(teacher.getTeacherCode());
        userLogin.setUserName(teacher.getTeacherName());
        userLogin.setUserType(Constant.isTeacher);
        String t = userLoginService.insert(userLogin);
        if(t.compareTo("插入成功")!=0){//插入登录表失败
            return 0;
        }
        int t1 = teacherMapper.insertSelective(teacher);
        if(t1==0)return 0;
        if(teacher.getChangeGroupList()!=null &&teacher.getChangeGroupList().size()>0){
            changeTeacherGroupList(teacher);
        }
        return  1;
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
        Assert.hasText(teacher.getTeacherId(),"教师主键不能为空");
        Assert.hasText(teacher.getTeacherCode(),"教师工号不能为空");
        Assert.hasText(teacher.getTeacherName(),"教师姓名不能为空");
        Assert.hasText(teacher.getTeacherSex(),"教师性别不能为空");
        teacher.setUpdateTime(new Date());
        //更新教师指导小组情况-会出现置空情况，因此不做判断
        changeTeacherGroupList(teacher);
        userLoginService.updatePasswordByUserCode(teacher.getTeacherId(),teacher.getPassword());
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
        List<TeacherGroup>teacherGroupList = teacherGroupService.getTeacherGroupByTeacherId(id);
        for (TeacherGroup tg:teacherGroupList) {
            teacherGroupService.delete(tg.getTeaGroId());
        }
        return teacherMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Author wys
     * @ClassName changeTeacherGroupList
     * @Description //TODO  修改教师指导小组信息
     * @Date 10:32 2020/3/9
     * @Param [teacher]
     * @return int
     **/
    @Override
    public int changeTeacherGroupList(Teacher teacher) {
        List<TeacherGroup>teacherGroupList = teacherGroupService.getTeacherGroupByTeacherId(teacher.getTeacherId());
        //增加,循环找出未插入
        for (String id:teacher.getChangeGroupList() ) {//前端获取的小组id列表
            boolean flag = true;
            for (TeacherGroup tg:teacherGroupList) {//后台获取teacherGroupList校验
                if(teacher.getTeacherId().compareTo(tg.getTeacherId())==0 && id.compareTo(tg.getGroupId())==0){//教师id与小组id均相等，则已插入
                    flag = false;
                    break;
                }
            }
            if(flag){
                TeacherGroup teacherGroup = new TeacherGroup();
                teacherGroup.setTeacherId(teacher.getTeacherId());
                teacherGroup.setGroupId(id);
                teacherGroupService.insert(teacherGroup);
            }
        }
        //删除
        for (TeacherGroup tg:teacherGroupList) {//对已存在的列表循环
            if(teacher.getChangeGroupList()==null || teacher.getChangeGroupList().size()==0){
                teacherGroupService.deleteByTeacherIdAndGroupId(tg);
            }else{
                boolean flag = false;
                for (String id:teacher.getChangeGroupList()){//若在前端传过来的列表id中没有groupId，则删除
                    if(teacher.getTeacherId().compareTo(tg.getTeacherId())==0 && id.compareTo(tg.getGroupId())==0){//教师id与小组id均相等，则已插入
                        flag = true;
                        break;
                    }
                }
                if(!flag){//在前端传过来的groupId列表中没有这一条，删除
                    teacherGroupService.deleteByTeacherIdAndGroupId(tg);
                }
            }
        }
        return 1;
    }

    /**
     * @Author wys
     * @ClassName Recover
     * @Description //TODO 恢复使用
     * @Date 17:51 2020/3/10
     * @Param [id]
     * @return int
     **/
    @Override
    public int Recover(String id) {
        Assert.hasText(id,"教师主键id不能为空");
        Teacher teacher = getOneTeacherById(id);
        teacher.setDeleteStatus(Constant.isNotDelete);
        return teacherMapper.updateByPrimaryKeySelective(teacher);
    }
}
