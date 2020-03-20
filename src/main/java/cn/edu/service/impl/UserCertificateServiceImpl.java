package cn.edu.service.impl;

import cn.edu.dao.CertificateMapper;
import cn.edu.dao.UserCertificateMapper;
import cn.edu.service.CertificateService;
import cn.edu.service.UserCertificateService;
import cn.edu.utils.ApplicationUtils;
import cn.edu.utils.Constant;
import cn.edu.vo.Certificate;
import cn.edu.vo.Student;
import cn.edu.vo.Teacher;
import cn.edu.vo.UserCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserCertificateServiceImpl
 * @Description TODO 用户证书中间表管理实现类
 * @Author wys5
 * @Date 2020/2/15 20:37
 * @Version 1.0
 **/
@Service
public class UserCertificateServiceImpl implements UserCertificateService {
    @Autowired
    private UserCertificateMapper userCertificateMapper;
    @Autowired
    private CertificateService certificateService;
    @Override
    public List<Certificate> getCertificateListOfId(String id) {
        Assert.hasText(id,"证书id不能为空");
        List<String> ids = userCertificateMapper.getCertificateIdListOfId(id);
        List<Certificate> certificates = new ArrayList<>();
        for (String certificateId:ids) {
            certificates.add(certificateService.getOneCertificateById(id));
        }
        return certificates;
    }

    /**
     *
     * @param certificateId
     * @return
     */
    @Override
    public List<UserCertificate> getListofCertificate(String certificateId) {
        Assert.hasText(certificateId,"证书id不能为空");
        List<UserCertificate> userCertificates = userCertificateMapper.getListofCertificate(certificateId);
        return userCertificates;
    }

    @Override
    public boolean getUserCertificate(String userId, String certificateId) {
        Assert.hasText(userId,"userid 不能为空");
        Assert.hasText(certificateId,"certificated 不能为空");
        Example example = new Example(UserCertificate.class);
        example.and().andEqualTo("userId",userId);
        example.and().andEqualTo("certificateId",certificateId);
        List<UserCertificate> userCertificates = userCertificateMapper.selectByExample(example);
        //true 为空
        boolean isEntry = true;
        if(userCertificates.size()>0){
            isEntry =false;
        }
        return isEntry;
    }


    @Override
    public int insert(UserCertificate userCertificate) {
        Assert.hasText(userCertificate.getUserId(),"用户id不能为空");
        Assert.hasText(userCertificate.getCertificateId(),"证书id不能为空");
        Assert.hasText(userCertificate.getUserType(),"数据类型不能为空");
        return userCertificateMapper.insertSelective(userCertificate);
    }

    @Override
    public int deleteByCertificateAndUser(UserCertificate userCertificate) {
        Assert.hasText(userCertificate.getUserId(),"userId 不能为空");
        Assert.hasText(userCertificate.getCertificateId(),"certificateId不能为空");
        Example example =new Example(UserCertificate.class);
        example.and().andEqualTo("userId",userCertificate.getUserId());
        example.and().andEqualTo("certificateId",userCertificate.getCertificateId());
        return userCertificateMapper.deleteByExample(example);
    }

    @Override
    public int insert(Teacher teacher, Certificate certificate) {
        Assert.hasText(teacher.getTeacherId(),"userId 不能为空");
        Assert.hasText(certificate.getCertificateId(),"certificateId不能为空");
        UserCertificate userCertificate = new UserCertificate();
        userCertificate.setUserId(teacher.getTeacherId());
        userCertificate.setCertificateId(certificate.getCertificateId());
        userCertificate.setUserType(Constant.isTeacher);
        return userCertificateMapper.insertSelective(userCertificate);
    }

    @Override
    public int insert(Student student, Certificate certificate) {
        Assert.hasText(student.getStudentId(),"userId 不能为空");
        Assert.hasText(certificate.getCertificateId(),"certificateId不能为空");
        UserCertificate userCertificate = new UserCertificate();
        userCertificate.setUserId(student.getStudentId());
        userCertificate.setCertificateId(certificate.getCertificateId());
        userCertificate.setUserType(Constant.isStudent);
        return userCertificateMapper.insertSelective(userCertificate);
    }


}
