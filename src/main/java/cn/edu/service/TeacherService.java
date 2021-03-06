package cn.edu.service;

import cn.edu.utils.Result;
import cn.edu.vo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

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
    Teacher getOneTeacherById(String id) throws Exception;
    Result insert(Teacher teacher) throws Exception;
    int update(Teacher teacher) throws Exception;
    int delete(String id);
    int realDel(String id);
    int changeTeacherGroupList(Teacher teacher);
    int Recover(String id) throws Exception;
    Result importExcel(MultipartFile file) throws Exception;
    Result setErrorMessage(Teacher teacher);
    /**
     * @Author lichi
     * @param teacher
     * @return
     */
    Teacher getTeacherByTeacherCode(Teacher teacher);
    Teacher getTeacherByTeacherCode(String teacerCode);
}
