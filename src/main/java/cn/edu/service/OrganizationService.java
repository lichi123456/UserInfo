package cn.edu.service;

import cn.edu.vo.Classes;
import cn.edu.vo.Organization;

import java.util.List;

/**
 * @ClassName OrganizationService
 * @Description TODO 发证机关管理接口
 * @Author lichi
 * @Date 2020/2/15 20:49
 * @Version 1.0
 **/
public interface OrganizationService {
    /**
     * 通过id得到组织
     * @param id
     * @return
     */
    Organization getOrganizationById(String id);

    /**
     * 插入证书机关
     * @Author lichi
     * @param organization
     * @return
     */
    int insert(Organization organization);
    /**
     * 修改证书机关
     * @Author lichi
     * @param organization
     * @return
     */
    int update(Organization organization);

    /**
     * 删除证书机关
     * @Author lichi
     * @param id
     * @return
     */
    int delete(String id);
}
