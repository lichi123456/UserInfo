package cn.edu.dao;

import cn.edu.vo.Student;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudentMapper extends Mapper<Student> {
    List<Student> getStudentListByName(Student student);
}