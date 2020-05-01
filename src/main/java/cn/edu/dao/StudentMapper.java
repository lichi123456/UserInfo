package cn.edu.dao;

import cn.edu.dto.StudentDto;
import cn.edu.vo.Student;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudentMapper extends Mapper<Student> {
    List<String> getStudentListByName(Student student);
    List<String>getDelStudentListByName(Student student);
    List<StudentDto>getAllStudentDto();
    Student getOneStudentDetails(String id);
}