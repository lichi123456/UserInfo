package cn.edu.service.impl;

import cn.edu.Application;
import cn.edu.dao.OrganizationMapper;
import cn.edu.service.OrganizationService;
import cn.edu.utils.ApplicationUtils;
import cn.edu.vo.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * @ClassName OrganizationServiceImpl
 * @Description TODO 发证机关管理实现类
 * @Author wys5
 * @Date 2020/2/15 20:49
 * @Version 1.0
 **/
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public Organization getOrganizationById(String id) {
        Assert.hasText(id,"组织id不能为空");
        Organization organization = organizationMapper.selectByPrimaryKey(id);
        return organization;
    }

    @Override
    public int insert(Organization organization) {
        organization.setOrganizationId(ApplicationUtils.GUID32());
        int t = organizationMapper.insertSelective(organization);
        if(t == 0){
            return 0;
        }
        return 1;
    }

    @Override
    public int update(Organization organization) {
        Assert.hasText(organization.getOrganizationId(),"发证机关id不能为空");
        Assert.hasText(organization.getOrganizationName(),"发证机关名称不能为空");
        organization.setCreateTime(new Date());
        return organizationMapper.updateByPrimaryKeySelective(organization);
    }

    @Override
    public int delete(String id) {
        Assert.hasText(id,"发证机关id不能为空");
        Organization organization = getOrganizationById(id);
        return organizationMapper.deleteByPrimaryKey(id);
    }
}
