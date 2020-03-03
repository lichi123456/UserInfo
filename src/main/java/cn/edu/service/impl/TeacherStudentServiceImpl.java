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
}
