package cn.edu.service;

import cn.edu.dto.CertificateDto;
import cn.edu.utils.Result;
import cn.edu.vo.Certificate;
import java.util.List;

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






    /**
     * 修改数据
     * @param certificate
     * @return
     */
    int update(Certificate certificate);
    /**
     * 删除数据
     * @param id
     * @return
     */
    int delete(String id);
    int realDel(String id);

    /**
     * 得到所有的证书
     * @return
     */
    List<Certificate> getCertificates(Certificate certificate,String flag);

}
