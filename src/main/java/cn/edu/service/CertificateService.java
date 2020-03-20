package cn.edu.service;

import cn.edu.utils.Result;
import cn.edu.vo.Certificate;

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




}
