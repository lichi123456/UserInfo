package cn.edu.dao;

import cn.edu.vo.Organization;
import cn.edu.vo.OrganizationCertificate;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrganizationCertificateMapper extends Mapper<OrganizationCertificate> {
    /**
     * 通过证书id获得发证机关
     * @param certificateId
     * @return
     */
    List<Organization> getOrganization(String certificateId);
    /**
     * 删除通过id
     * @param id
     * @return
     */
    void delete(String id);
}