package cn.edu.service.impl;

import cn.edu.dao.OrganizationCertificateMapper;
import cn.edu.service.OrganizationCertificateService;
import cn.edu.utils.ApplicationUtils;
import cn.edu.vo.Organization;
import cn.edu.vo.OrganizationCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @ClassName OrganizationCertificateServiceImpl
 * @Description TODO 发证机关与证书中间表管理类
 * @Author wys5
 * @Date 2020/2/15 20:38
 * @Version 1.0
 **/
@Service
public class OrganizationCertificateServiceImpl implements OrganizationCertificateService {
    @Autowired
    private OrganizationCertificateMapper organizationCertificateMapper;
    @Override
    public List<Organization> getOrganizationByCertificateId(String certificateId) {

        Assert.hasText(certificateId,"证书id不能为空");
        List<Organization> organizationList = organizationCertificateMapper.getOrganization(certificateId);
        return organizationList;
    }

    @Override
    public int insert(OrganizationCertificate organizationCertificate) {
        organizationCertificate.setCertificateId(ApplicationUtils.GUID32());
        return  organizationCertificateMapper.insert(organizationCertificate);

    }

    @Override
    public int delete(String id) {
        Assert.hasText(id,"id不能为空");
        organizationCertificateMapper.delete(id);
        return 0;
    }
}
