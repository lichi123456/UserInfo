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
    Result insert(Student student) throws Exception;
    int delete(String id);
    int realDel(String id);
    int update(Student student) throws Exception;
    Student getOneStudentById(String id) throws Exception;
    int changeTutorList(Student student);
    int Recover(String id) throws Exception;
    ResponseEntity<byte[]> exportExcelModel();
    Result importExcel(MultipartFile file) throws Exception;
    Result setErrorMessage(Student student);
    /**
     * 根据学生学号查找学生id
     * @param student
     * @return
     */
    Student getStudentIdByStudentCode(Student student);
    Student getStudentIdByStudentCode(String studentCode);

    /**
     * 得到所有的studentDto
     * @return
     */
    List<StudentDto> getAllStudentDto();


}
