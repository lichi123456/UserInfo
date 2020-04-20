package cn.edu.dao;

import cn.edu.vo.Teacher;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TeacherMapper extends Mapper<Teacher> {
    List<String> getTeacherListByName(Teacher teacher);
    List<String>getDelTeacherListByName(Teacher teacher);
}