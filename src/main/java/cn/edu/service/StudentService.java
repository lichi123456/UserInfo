package cn.edu.service;

import cn.edu.entity.Student;

import java.util.List;

/**
 * @ClassName StudentService
 * @Description TODO 学生管理接口
 * @Author wys5
 * @Date 2020/2/14 17:17
 * @Version 1.0
 **/
public interface StudentService {
    List<Student>getStudentList();
    int insert(Student student);
    int deletet(String id);
    int update(Student student);
    Student search(String id);
}
