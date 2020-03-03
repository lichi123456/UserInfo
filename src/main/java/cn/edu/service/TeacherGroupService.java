package cn.edu.service;

import cn.edu.vo.Groups;
import cn.edu.vo.Teacher;
import cn.edu.vo.TeacherGroup;

import java.util.List;

/**
 * @ClassName TeacherGroupService
 * @Description TODO 教师组别中间表管理接口
 * @Author wys5
 * @Date 2020/2/15 20:41
 * @Version 1.0
 **/
public interface TeacherGroupService {
    List<Groups>getGroupListByTeacherId(String teacherId);
    List<Teacher>getTeacherListByGroupId(String groupId);
}
