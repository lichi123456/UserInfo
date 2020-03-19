package cn.edu.service;

import cn.edu.utils.Result;
import cn.edu.vo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @ClassName TeacherService
 * @Description TODO 教师管理接口
 * @Author wys5
 * @Date 2020/2/15 20:40
 * @Version 1.0
 **/
public interface TeacherService {
    List<Teacher>getTeacherListWithConditionAndDeleteStatus(Teacher teacher,String deleteStatus);
    List<Teacher>getTeacherList();
    Teacher getOneTeacherById(String id);
    Result insert(Teacher teacher);
    int update(Teacher teacher);
    int delete(String id);
    int realDel(String id);
    int changeTeacherGroupList(Teacher teacher);
    int Recover(String id);
}
