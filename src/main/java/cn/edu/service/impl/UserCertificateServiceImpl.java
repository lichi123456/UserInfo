package cn.edu.service.impl;

import cn.edu.dao.UserCertificateMapper;
import cn.edu.dao.UserCertificateUrlMapper;
import cn.edu.service.CertificateService;
import cn.edu.service.UserCertificateService;
import cn.edu.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private UserCertificateUrlMapper userCertificateUrlMapper;
    @Override
    public List<Certificate> getCertificateListOfId(String id) {
        Assert.hasText(id,"证书id不能为空");
      //  List<String> ids = userCertificateMapper.getCertificateIdListOfId(id);
        List<Certificate> certificates = new ArrayList<>();
//        for (String certificateId:ids) {
//            certificates.add(certificateService.getOneCertificateById(id));
//        }
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
       // List<UserCertificate> userCertificates = userCertificateMapper.getListofCertificate(certificateId);
        return null;
    }

    @Override
    public boolean getUserCertificate(String userId, String certificateId) {
        Example example = new Example(UserCertificate.class);
        example.and().andEqualTo("student_id1",userId);
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
    public UserCertificate getUserCertificate(Long id) {
        Assert.hasText(id.toString(),"证书id不能为空");
        Example example = new Example(UserCertificate.class);
        example.and().andEqualTo("userCerId",id);

        return userCertificateMapper.selectOneByExample(example);
    }

    /**
     * 得到所用的用户证书列表
     * @return
     */
    @Override
    public List<UserCertificate> getUserCertificate() {

        return userCertificateMapper.selectAll();
    }


    @Override
    public int insert(UserCertificate userCertificate) {

        return userCertificateMapper.insertSelective(userCertificate);
    }

    @Override
    public int deleteByCertificateAndUser(UserCertificate userCertificate) {

        Example example =new Example(UserCertificate.class);
        return  0;
    }

    @Override
    public int insert(Teacher teacher, Certificate certificate) {
        Assert.hasText(teacher.getTeacherId(),"userId 不能为空");
        Assert.hasText(certificate.getCertificateId(),"certificateId不能为空");
        UserCertificate userCertificate = new UserCertificate();
        return  0;
    }

    @Override
    public int insert(Student student, Certificate certificate) {
        Assert.hasText(student.getStudentId(),"userId 不能为空");
        Assert.hasText(certificate.getCertificateId(),"certificateId不能为空");
        UserCertificate userCertificate = new UserCertificate();

        return userCertificateMapper.insertSelective(userCertificate);
    }

    @Override
    public int update(UserCertificate userCertificate) {
        Assert.hasText(userCertificate.getUserCerId().toString(),"用户证书id不能为空");
        userCertificate.setUpdateTime(new Date());
        return userCertificateMapper.updateByPrimaryKey(userCertificate);

    }

    @Override
    public int delete(Long id) {
        return userCertificateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UserCertificate> getPersonCertificate() {
        List<UserCertificate> userCertificates = new ArrayList<>();
        userCertificates = userCertificateMapper.getPersonCertificateList();
        return userCertificates;
    }

    @Override
    public List<UserCertificate> getPersonCertificateListOfTeacher() {
        List<UserCertificate> userCertificates = new ArrayList<>();
        userCertificates = userCertificateMapper.getPersonCertificateListOfTeacher();
        return userCertificates;
    }


}
