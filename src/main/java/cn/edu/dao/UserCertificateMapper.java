package cn.edu.dao;

import cn.edu.vo.Certificate;
import cn.edu.vo.UserCertificate;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserCertificateMapper extends Mapper<UserCertificate> {
    /**
     * 根据学生或老师的id查找证书
     * @param id 学生或老师id
     * @return
     */
    List<String> getCertificateIdListOfId(String id);
    /**
     * 根据证书ID来查找出对应的学生和老师
     * @param certificateId
     * @return
     */
    List<UserCertificate> getListofCertificate(String certificateId);
    /**
     * 删除来查找出对应的用户
     * @param certificateId
     * @return
     */
    void deleteByCertificate(String certificateId,String userId);

}