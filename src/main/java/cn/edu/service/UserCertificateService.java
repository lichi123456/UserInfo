package cn.edu.service;

import cn.edu.dao.UserCertificateMapper;
import cn.edu.vo.Certificate;
import cn.edu.vo.Student;
import cn.edu.vo.Teacher;
import cn.edu.vo.UserCertificate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName UserCertificateService
 * @Description TODO 用户证书中间表接口
 * @Author wys5
 * @Date 2020/2/15 20:37
 * @Version 1.0
 **/
public interface UserCertificateService {
    /**
     * 根据学生或老师的id查找证书
     * @param id 学生或老师id
     * @return
     */
    List<Certificate> getCertificateListOfId(String id);

    /**
     * 根据证书ID来查找出对应的学生和老师
     * @param certificateId
     * @return
     */
    List<UserCertificate> getListofCertificate(String certificateId);

    /**
     * 判断数据库中是否有数据
     * @param userId
     * @param certificateId
     * @return
     */
    boolean getUserCertificate(String userId,String certificateId);

    /**
     * 插入相对应的数据
     * @param userCertificate
     */
    int insert(UserCertificate userCertificate);
    /**
     * 根据证书ID删除
     * @param userCertificate
     * @return
     */
    int deleteByCertificateAndUser(UserCertificate userCertificate);

    /**
     * 插入老师和证书
     * @param teacher
     * @param certificate
     * @return
     */
    int insert(Teacher teacher ,Certificate certificate);

    /**
     * 插入学生和证书
     * @param student
     * @param certificate
     * @return
     */
    int insert(Student student,Certificate certificate);


}
