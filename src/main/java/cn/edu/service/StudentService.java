package cn.edu.service;

import cn.edu.dto.StudentDto;
import cn.edu.utils.Result;
import cn.edu.vo.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName StudentService
 * @Description TODO 学生管理接口
 * @Author wys5
 * @Date 2020/2/14 17:17
 * @Version 1.0
 **/
@Service
public interface StudentService {
    List<Student>getStudentListWithConditionAndDeleteStatus(Student student,String deleteStatus);
    Result insert(Student student);
    int delete(String id);
    int realDel(String id);
    int update(Student student);
    Student getOneStudentById(String id);
    int changeTutorList(Student student);
    int Recover(String id);
    ResponseEntity<byte[]> exportExcelModel();
    /**
     * 根据学生学号查找学生id
     * @param student
     * @return
     */
    Student getStudentIdByStudentCode(Student student);

    /**
     * 得到所有的studentDto
     * @return
     */
    List<StudentDto> getAllStudentDto();


}
