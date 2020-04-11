package cn.edu.service;

import cn.edu.vo.Student;
import cn.edu.vo.Teacher;
import cn.edu.vo.TeacherStudent;

import java.util.List;

/**
 * @ClassName TeacherStudentService
 * @Description TODO 教师学生中间表接口
 * @Author wys5
 * @Date 2020/2/15 20:42
 * @Version 1.0
 **/
public interface TeacherStudentService {
    List<Teacher>getTeacherListByStudentId(String id) throws Exception;
    List<Student>getStudentListByTeacherId(String id) throws Exception;
    int insert(TeacherStudent teacherStudent);
    int deleteByStudentIdAndTeacherId(TeacherStudent teacherStudent);
    List<TeacherStudent>getTeacherStudentByStudentId(String studentId);
    List<TeacherStudent>getTeacherStudentByTeacherId(String teacherId);

    /**
     * @Author lichi
     * 判断是否存在
     * @param teacher
     * @param student
     * @return
     */
    boolean isExistTeacherStudent(Teacher teacher,Student student);
    /**
     * @Author lichi
     * 通过老师和学生插入数据
     * @param teacher
     * @param student
     * @return
     */
    int insert(Teacher teacher,Student student);


}
