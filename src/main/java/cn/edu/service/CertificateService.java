package cn.edu.service;

import cn.edu.dto.CertificateDto;
import cn.edu.utils.Result;
import cn.edu.vo.Certificate;
import cn.edu.vo.Student;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CertificateService
 * @Description TODO 证书管理接口类
 * @Author lichi
 * @Date 2020/2/15 20:37
 * @Version 1.0
 **/
public interface CertificateService {
    /**
     * 通过id获得证书
     * @Author lichi
     * @param certificateId
     * @return
     */
    Certificate getOneCertificateById(String certificateId);

    /**
     * 通过证书名称得到证书信息
     * @param certificate
     * @return
     */
    Certificate getCertificateByCertificateName(Certificate certificate);

    /**
     * 插入数据
     * @param certificate
     * @return
     */
    Result insert(Certificate certificate);

    /**
     * 导出数据
     * @return
     */
    List<CertificateDto> getCertificateDto(String studentId,String teacherId,String CertificateId);

    boolean  isExistStudentAndCertificate(String studentId,String teacherId,String CertificateId);


    List<CertificateDto> getCertificateDtoList(String studentId, String teacherId, String certificateId );

    /**
     * 查找数据
     * @param certificate
     * @param deleteStatus
     * @return
     */
    List<Certificate>getCertificateListWithConditionAndDeleteStatus(Certificate certificate, String deleteStatus);

}
