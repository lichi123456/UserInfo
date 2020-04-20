package cn.edu.dto;

import cn.edu.vo.Certificate;
import cn.edu.vo.Student;
import cn.edu.vo.Teacher;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: certificateDto
 * @Author: lichi
 * @Date: 2020/3/17 3:40
 * @Description:
 * @Version: 1.0
 */
@Data
public class CertificateDto implements Serializable {
    //student 表示学号
    //student_name 表示姓名
    private Long Id;
    private String student1;
    private String student2;
    private String student3;
    private String student4;
    private String student5;
    private String student6;
    private String student7;
    private String student8;
    private String student9;
    private String student10;
    private String studentName1;
    private String studentName2;
    private String studentName3;
    private String studentName4;
    private String studentName5;
    private String studentName6;
    private String studentName7;
    private String studentName8;
    private String studentName9;
    private String studentName10;
    private String certificateId;
    private String certificateName;
    private String certificateLevl;
    private String teacher1;
    private String teacher2;
    private String teacher3;
    private String teacherName1;
    private String teacherName2;
    private String teacherName3;
    private String url;
    private String matchId;
    private String matchName;
    private String matchType;
    private String certificateLevel;
    private String matchLevelLevel;

}
