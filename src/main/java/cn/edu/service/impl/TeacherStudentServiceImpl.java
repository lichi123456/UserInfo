package cn.edu.service.impl;

import cn.edu.dao.TeacherStudentMapper;
import cn.edu.service.StudentService;
import cn.edu.service.TeacherService;
import cn.edu.service.TeacherStudentService;
import cn.edu.vo.Student;
import cn.edu.vo.Teacher;
import cn.edu.vo.TeacherStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TeacherStudentServiceImpl
 * @Description TODO 教师学生中间表管理实现类
 * @Author wys5
 * @Date 2020/2/15 20:42
 * @Version 1.0
 **/
@Service
public class TeacherStudentServiceImpl implements TeacherStudentService {
    @Autowired
    private TeacherStudentMapper teacherStudentMapper;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    /**
     * @Author wys
     * @ClassName getTeacherListByStudentId
     * @Description //TODO  根据学生id获取其指导老师列表
     * @Date 16:00 2020/3/1
     * @Param [id]
     * @return java.util.List<cn.edu.vo.Teacher>
     **/
    @Override
    public List<Teacher> getTeacherListByStudentId(String id) {
        Example example = new Example(TeacherStudent.class);
        example.and().andEqualTo("studentId",id);
        List<TeacherStudent> teacherStudent = teacherStudentMapper.selectByExample(example);
        List<Teacher>teacherList=new ArrayList<>();
        for (TeacherStudent ts:teacherStudent) {
            teacherList.add(teacherService.getOneTeacherById(ts.getTeacherId()));
        }
        return teacherList;
    }

    @Override
    public List<Student> getStudentListByTeacherId(String id) {
        /**
         * @Author wys
         * @ClassName getStudentListByTeacherId
         * @Description //TODO  根据教师id获取其被其指导的学生列表
         * @Date 16:02 2020/3/1
         * @Param [id]
         * @return java.util.List<cn.edu.vo.Student>
         **/
        Example example = new Example(TeacherStudent.class);
        example.and().andEqualTo("teacherId",id);
        List<TeacherStudent> teacherStudent = teacherStudentMapper.selectByExample(example);
        List<Student>studentList=new ArrayList<>();
        for (TeacherStudent ts:teacherStudent
        ) {
            studentList.add(studentService.getOneStudentById(ts.getStudentId()));
        }
        return studentList;
    }

    /**
     * @Author wys
     * @ClassName insert
     * @Description //TODO 新增
     * @Date 10:21 2020/3/10
     * @Param [teacherStudent]
     * @return int
     **/
    @Override
    public int insert(TeacherStudent teacherStudent) {
        Assert.hasText(teacherStudent.getStudentId(),"学生id不能为空");
        Assert.hasText(teacherStudent.getTeacherId(),"教师id不能为空");
        return teacherStudentMapper.insertSelective(teacherStudent);
    }

    /**
     * @Author wys
     * @ClassName deleteByStudentIdAndTeacherId
     * @Description //TODO  根据学生id与教师id删除中间表数据
     * @Date 10:23 2020/3/10
     * @Param [teacherStudent]
     * @return int
     **/
    @Override
    public int deleteByStudentIdAndTeacherId(TeacherStudent teacherStudent) {
        Assert.hasText(teacherStudent.getTeacherId(),"教师id不能为空");
        Assert.hasText(teacherStudent.getStudentId(),"学生id不能为空");
        Example example = new Example(TeacherStudent.class);
        example.and().andEqualTo("teacherId",teacherStudent.getTeacherId());
        example.and().andEqualTo("studentId",teacherStudent.getStudentId());
        return teacherStudentMapper.deleteByExample(example);
    }

    /**
     * @Author wys
     * @ClassName getTeacherStudentByStudentId
     * @Description //TODO  根据学生id获取teacherStudent中间表相关数据
     * @Date 10:13 2020/3/10
     * @Param [studentId]
     * @return java.util.List<cn.edu.vo.TeacherStudent>
     **/
    @Override
    public List<TeacherStudent> getTeacherStudentByStudentId(String studentId) {
        Assert.hasText(studentId,"学生id不能为空");
        Example example = new Example(TeacherStudent.class);
        example.and().andEqualTo("studentId",studentId);
        return teacherStudentMapper.selectByExample(example);
    }

    /**
     * @Author wys
     * @ClassName getTeacherStudentByTeacherId
     * @Description //TODO  根据教师id获取teacherStudent中间表相关数据
     * @Date 16:51 2020/3/11
     * @Param [teacherId]
     * @return java.util.List<cn.edu.vo.TeacherStudent>
     **/
    @Override
    public List<TeacherStudent> getTeacherStudentByTeacherId(String teacherId) {
        Assert.hasText(teacherId,"教师id不能为空");
        Example example = new Example(TeacherStudent.class);
        example.and().andEqualTo("teacherId",teacherId);
        return teacherStudentMapper.selectByExample(example);
    }
    /**
     *判断是否存在
     *
     * @description:
     * @param teacher
     * @param student
     * @return: boolean
     * @author: 李翅
     * @time: 2020/3/20 16:12
     */
    @Override
    public boolean isExistTeacherStudent(Teacher teacher, Student student) {

        Assert.hasText(teacher.getTeacherId(),"教师id不能为空");
        Assert.hasText(student.getStudentId(),"学生id不能为空");
        //true 为空
        boolean isExist = true;
        Example example = new Example(TeacherStudent.class);
        example.and().andEqualTo("teacherId",teacher.getTeacherId());
        example.and().andEqualTo("studentId",student.getStudentId());
        TeacherStudent teacherStudent = teacherStudentMapper.selectOneByExample(example);
        if(teacherStudent != null){
            isExist = false;
        }


        return isExist;
    }
    /**
     *
     *
     * @description: 通过老师和学生插入中间关系
     * @param teacher
     * @param student
     * @return: int
     * @author: 李翅
     * @time: 2020/3/20 16:15
     */
    @Override
    public int insert(Teacher teacher, Student student) {
        Assert.hasText(teacher.getTeacherId(),"教师id不能为空");
        Assert.hasText(student.getStudentId(),"学生id不能为空");
        TeacherStudent teacherStudent = new TeacherStudent();
        teacherStudent.setTeacherId(teacher.getTeacherId());
        teacherStudent.setStudentId(student.getStudentId());
        return teacherStudentMapper.insertSelective(teacherStudent);
    }

}
