package cn.edu.service;

import cn.edu.vo.Classes;
import cn.edu.vo.Major;

import java.util.List;
/**
 * @Author wys
 * @ClassName ClassesService
 * @Description //TODO 班级管理接口
 * @Date 20:33 2020/2/15
 * @Param
 * @return
 **/
public interface ClassesService {
    List<Classes> getClassesList();
    Classes getOneClassesById(String classId);
    int insert(Classes classes);
    int update(Classes classes);
    int delete(String id);
    String[] getClassesNameBy(Major major);
    String getIdByName(String className);
}
