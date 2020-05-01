package cn.edu.dao;

import cn.edu.vo.UserCertificate;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserCertificateMapper extends Mapper<UserCertificate> {
    /**
     * 得到所有的个人证书列表
     * @return
     */
    List<UserCertificate> getPersonCertificateList();
    List<UserCertificate> getPersonCertificateListOfTeacher();
}