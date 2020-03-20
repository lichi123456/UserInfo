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
    private Student student;
    private Certificate certificate;
    private Teacher teacher1;
    private Teacher teacher2;
    private Teacher teacher3;

}
