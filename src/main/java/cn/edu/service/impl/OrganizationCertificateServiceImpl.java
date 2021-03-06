package cn.edu.service.impl;

import cn.edu.dao.OrganizationCertificateMapper;
import cn.edu.service.CertificateService;
import cn.edu.service.MatchService;
import cn.edu.service.OrganizationCertificateService;
import cn.edu.service.OrganizationService;
import cn.edu.utils.ApplicationUtils;
import cn.edu.utils.Constant;
import cn.edu.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
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
    @Autowired
    private CertificateService certificateService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private MatchService matchService;
    @Override
    public List<OrganizationCertificate> getOrganizationByCertificateId(String certificateId) {

        Assert.hasText(certificateId,"证书id不能为空");
        Example example = new Example(OrganizationCertificate.class);
        example.and().andEqualTo("certificateId",certificateId);
        List<OrganizationCertificate> organizationList = organizationCertificateMapper.selectByExample(example);
        return organizationList;
    }

    @Override
    public int insert(OrganizationCertificate organizationCertificate) {
        return  organizationCertificateMapper.insert(organizationCertificate);

    }

    @Override
    public int insert(String certificateId, String organizationId) {
        Assert.hasText(certificateId,"证书Id不能为空");
        Assert.hasText(organizationId,"组织ID不能为空");
        OrganizationCertificate organizationCertificate = new OrganizationCertificate();
        organizationCertificate.setCertificateId(certificateId);
        organizationCertificate.setOrganizationId(organizationId);

        return  organizationCertificateMapper.insert(organizationCertificate);
    }

    @Override
    public int delete(String certificateId) {
        Example example = new Example(OrganizationCertificate.class);
        example.and().andEqualTo("certificateId",certificateId);

        return organizationCertificateMapper.deleteByExample(example);
    }

    @Override
    public List<Certificate> getAllCertificate(Certificate certificate) {
        List<Certificate> certificates = certificateService.getCertificates(certificate, Constant.IS_NOT_DELETE);
        for(Certificate c:certificates){
            Example example = new Example(OrganizationCertificate.class);
            example.and().andEqualTo("certificateId",c.getCertificateId());
           List<OrganizationCertificate> organizationCertificates = organizationCertificateMapper.selectByExample(example);
            List<Organization> organizations = new ArrayList<>();
            int flag = 0;
            String name ="";
            for (OrganizationCertificate org:organizationCertificates) {
                String orgId = org.getOrganizationId();
                Organization organization = organizationService.getOrganizationById(orgId);

                if(flag == 0){
                    name = ""+name+organization.getOrganizationName();
                    flag = 1;
                }else{
                   name = name+ ","+organization.getOrganizationName();
                }

                organizations.add(organization);
            }
            c.setOrganizationNames(name);
            c.setOrganizations(organizations);
            if(c.getCertificateId()!=null && c.getCertificateId().length()!=0 ){
                if(matchService.getMatchs(c.getMatchId())!=null&&matchService.getMatchs(c.getMatchId()).getMatchName()!=null&&matchService.getMatchs(c.getMatchId()).getMatchName().length()!=0){
                    c.setMatchName(matchService.getMatchs(c.getMatchId()).getMatchName());
                }
            }

        }

        return certificates;
    }


}
