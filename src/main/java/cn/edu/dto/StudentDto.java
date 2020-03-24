package cn.edu.dto;
/**
 * @ClassName: studentDto
 * @Author: lichi
 * @Date: 2020/3/23 22:21
 * @Description:
 * @Version: 1.0
 */

import cn.edu.vo.Student;
import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 *@description:
 *@author: Andy
 *@time: 2020/3/23 22:21
 *
 */
@Data
public class StudentDto extends Student implements Serializable{
    private String majorName;
    private String facultyName;
    private String classesName;
}
