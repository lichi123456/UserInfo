package cn.edu.dao;

import cn.edu.dto.StudentDto;
import cn.edu.vo.Student;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudentMapper extends Mapper<Student> {
    List<Student> getStudentListByName(Student student);
    List<Student>getDelStudentListByName(Student student);
    List<StudentDto>getAllStudentDto();
}