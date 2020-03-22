package cn.edu.service;

import cn.edu.vo.*;

import java.util.List;

/**
 * @ClassName OrganizationCertificateService
 * @Description TODO 发证机关与证书中间表管理接口
 * @Author lichi
 * @Date 2020/2/15 20:38
 * @Version 1.0
 **/
public interface OrganizationCertificateService {
    /**
     * 通过证书id获得发证机关
     * @param certificateId
     * @return
     */
    List<OrganizationCertificate> getOrganizationByCertificateId(String certificateId);

    /**
     * 插入数据
     * @param organizationCertificate 证书与发证机关关联
     * @return
     */
    int insert(OrganizationCertificate organizationCertificate);



}
