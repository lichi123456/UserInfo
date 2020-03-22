package cn.edu.service;

import cn.edu.utils.Result;
import cn.edu.vo.Student;

import java.util.List;

/**
 * @ClassName StudentService
 * @Description TODO 学生管理接口
 * @Author wys5
 * @Date 2020/2/14 17:17
 * @Version 1.0
 **/
public interface StudentService {
    List<Student>getStudentListWithConditionAndDeleteStatus(Student student,String deleteStatus);
    Result insert(Student student);
    int delete(String id);
    int realDel(String id);
    int update(Student student);
    Student getOneStudentById(String id);
    int changeTutorList(Student student);
    int Recover(String id);

    /**
     * 根据学生学号查找学生id
     * @param student
     * @return
     */
    Student getStudentIdByStudentCode(Student student);



}
