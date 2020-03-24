package cn.edu.service;

import cn.edu.vo.Faculty;

import java.util.List;
/**
 * @Author wys
 * @ClassName FacultyService
 * @Description //TODO 院系管理接口
 * @Date 20:33 2020/2/15
 * @Param
 * @return
 **/
public interface FacultyService {

    List<Faculty> getFacultyList();
    Faculty getFacultyById(String facultyId);
    int insert(Faculty faculty);
    int update(Faculty faculty);
    int delete(String id);
    List<Faculty>getAllList();
    Faculty getFacultyByName(String facultyName);

}
