package cn.edu.service.impl;

import cn.edu.dao.StudentMapper;
import cn.edu.service.*;
import cn.edu.vo.*;
import cn.edu.vo.Student;
import cn.edu.service.StudentService;
import cn.edu.utils.Constant;
import cn.edu.utils.ApplicationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Description TODO 用户信息管理实现类
 * @Author wys5
 * @Date 2020/2/14 17:17
 * @Version 1.0
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private GroupsService groupsService;

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private TeacherStudentService teacherStudentService;
    /**
     * @Author wys
     * @ClassName getStudentList
     * @Description //TODO 获取学生信息列表
     * @Date 16:37 2020/2/15
     * @Param []
     * @return java.util.List<cn.edu.vo.Student>
     **/
    @Override
    public List<Student> getStudentListWithConditionAndDeleteStatus(Student student,String deleteStatus) {
        List<Student>studentList = null;
        if(deleteStatus.trim().compareTo(Constant.isNotDelete)==0){
            studentList = studentMapper.getStudentListByName(student);
        }else if(deleteStatus.trim().compareTo(Constant.isDelete)==0){
            studentList = studentMapper.getDelStudentListByName(student);
        }

        List<Student>list=new ArrayList<>();
        studentList.stream().forEach(s->{
                list.add(getOneStudentById(s.getStudentId()));
        });
        return list;
    }

    /**
     * @Author wys
     * @ClassName insert
     * @Description //TODO 新增
     * @Date 16:38 2020/2/15
     * @Param [student]
     * @return int
     **/
    @Override
    public int insert(Student student) {
        student.setStudentId(ApplicationUtils.GUID32());
        student.setDeleteStatus(Constant.isNotDelete);
//        student.setCreateUser();

        UserLogin userLogin = new UserLogin();
        userLogin.setUserId(student.getStudentId());
        userLogin.setUserCode(student.getStudentCode());
        userLogin.setUserName(student.getStudentName());
        userLogin.setUserType(Constant.isStudent);
        //插入登录表
        String t = userLoginService.insert(userLogin);
        if(t.compareTo("插入成功")!=0){
            return 0;
        }
        int t1 = studentMapper.insertSelective(student);
        if(t1==0)return 0;
        if(student.getChangeTutorList()!=null &&student.getChangeTutorList().size()>0){
            changeTutorList(student);
        }
        return 1;
    }

    /**
     * @Author wys
     * @ClassName deletet
     * @Description //TODO 假删除  将N置为Y
     * @Date 16:38 2020/2/15
     * @Param [id]
     * @return int
     **/
    @Override
    public int delete(String id) {
        Assert.hasText(id,"id不能为空");
        Student student = studentMapper.selectByPrimaryKey(id);
        student.setDeleteStatus(Constant.isDelete);
        student.setUpdateTime(new Date());
//        student.setUpdateUser();
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    /**
     * @Author wys
     * @ClassName realDel
     * @Description //TODO  真删除
     * @Date 11:06 2020/2/22
     * @Param [id]
     * @return int
     **/
    @Override
    public int realDel(String id) {
        Assert.hasText(id,"id不能为空");
        return studentMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Author wys
     * @ClassName update
     * @Description //TODO 更新
     * @Date 16:38 2020/2/15
     * @Param [student]
     * @return int
     **/
    @Override
    public int update(Student student) {
        Assert.hasText(student.getStudentId(),"学生主键不能为空");
        Assert.hasText(student.getStudentCode(),"学生学号不能为空");
        Assert.hasText(student.getStudentName(),"学生姓名不能为空");
        Assert.hasText(student.getStudentSex(),"学生性别不能为空");
        student.setUpdateTime(new Date());
        //更新密码
        userLoginService.updatePasswordByUserCode(student.getStudentId(),student.getPassword());
        //更新学生指导老师-会出现置空情况，因此不做判断
        changeTutorList(student);
        //更新
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    /**
     * @Author wys
     * @ClassName getOneStudentById
     * @Description //TODO  获取一个学生的所有信息
     * @Date 16:39 2020/2/15
     * @Param [id]
     * @return cn.edu.vo.Student
     **/
    @Override
    public Student getOneStudentById(String id) {
        Assert.hasText(id, "id不能为空");
        //获取基本信息
        Student student = studentMapper.selectByPrimaryKey(id);
        Faculty faculty = new Faculty();
        Major major = new Major();
        Classes classes = new Classes();
        Groups groups = new Groups();
        //组装院系信息
        if (StringUtils.isNotEmpty(student.getClassId()) && StringUtils.isNoneBlank(student.getClassId())) {
            classes = classesService.getOneClassesById(student.getClassId());
            student.setClassName(classes.getClassName());
            if (StringUtils.isNotEmpty(classes.getMajorId()) && StringUtils.isNoneBlank(classes.getMajorId())) {
                major = majorService.getOneMajorById(classes.getMajorId());
                student.setMajorId(major.getMajorId());
                student.setMajorName(major.getMajorName());
                if (StringUtils.isNotEmpty(major.getFacultyId()) && StringUtils.isNoneBlank(major.getFacultyId())) {
                    faculty = facultyService.getFacultyById(major.getFacultyId());
                    student.setFacultyId(faculty.getFacultyId());
                    student.setFacultyName(faculty.getFacultyName());
                }
            }
        }
        //组装小组信息
        if (StringUtils.isNotEmpty(student.getGroupId()) && StringUtils.isNoneBlank(student.getGroupId())) {
            groups = groupsService.getOneGroupById(student.getGroupId());
            student.setGroupName(groups.getGroupName());
        }
        //获取密码
        student.setPassword(userLoginService.getPasswordById(id));
        //获取其指导老师列表
        List<Teacher>teacherList = teacherStudentService.getTeacherListByStudentId(id);
        student.setTutor(teacherList);
        String name = "";
        for (Teacher t:teacherList) {
            name+=t.getTeacherName();
            name+="、";
        }
        //指导老师名字字符串话
        student.setTutorName(name);
        return student;
    }

    /**
     * @Author wys
     * @ClassName changeTutorList
     * @Description //TODO  新增或更新teacherStudent表
     * @Date 10:28 2020/3/10
     * @Param [student]
     * @return int
     **/
    @Override
    public int changeTutorList(Student student) {
        Assert.hasText(student.getStudentId(),"学生id不能为空");
        List<TeacherStudent>teacherStudents = teacherStudentService.getTeacherStudentByStudentId(student.getStudentId());
        //增加,循环找出未插入
        for (String id:student.getChangeTutorList() ) {//前端获取的小组id列表
            boolean flag = true;
            for (TeacherStudent ts:teacherStudents) {//后台获取teacherStudentList校验
                if(student.getStudentId().compareTo(ts.getStudentId())==0 && id.compareTo(ts.getTeacherId())==0){//教师id与学生id均相等，则已插入
                    flag = false;
                    break;
                }
            }
            if(flag){
                TeacherStudent teacherStudent = new TeacherStudent();
                teacherStudent.setTeacherId(id);
                teacherStudent.setStudentId(student.getStudentId());
                teacherStudentService.insert(teacherStudent);
            }
        }
        //删除
        for (TeacherStudent ts:teacherStudents) {//对已存在的列表循环
            if(student.getChangeTutorList()==null || student.getChangeTutorList().size()==0){
                teacherStudentService.deleteByStudentIdAndTeacherId(ts);
            }else{
                boolean flag = false;
                for (String id:student.getChangeTutorList()){//若在前端传过来的列表id中没有groupId，则删除
                    if(student.getStudentId().compareTo(ts.getStudentId())==0 && id.compareTo(ts.getTeacherId())==0){//教师id与小组id均相等，则已插入
                        flag = true;
                        break;
                    }
                }
                if(!flag){//在前端传过来的groupId列表中没有这一条，删除
                    teacherStudentService.deleteByStudentIdAndTeacherId(ts);
                }
            }
        }
        return 1;
    }

    /**
     * @Author wys
     * @ClassName Recover
     * @Description //TODO 恢复使用
     * @Date 17:52 2020/3/10
     * @Param [id]
     * @return int
     **/
    @Override
    public int Recover(String id) {
        Assert.hasText(id,"学生主键id不能为空");
        Student student = getOneStudentById(id);
        student.setDeleteStatus(Constant.isNotDelete);
        return studentMapper.updateByPrimaryKeySelective(student);
    }

}
