package cn.edu.service.impl;

import cn.edu.dao.CertificateMapper;
import cn.edu.dto.CertificateDto;
import cn.edu.service.CertificateService;
import cn.edu.service.OrganizationCertificateService;
import cn.edu.service.StudentService;
import cn.edu.utils.ApplicationUtils;
import cn.edu.utils.Constant;
import cn.edu.utils.Result;
import cn.edu.vo.Certificate;
import cn.edu.vo.Organization;
import cn.edu.vo.Student;
import cn.edu.vo.TeacherStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.io.ObjectStreamClass;
import java.util.*;

/**
 * @ClassName CertificateServiceImpl
 * @Description TODO 证书管理实现类
 * @Author lichi
 * @Date 2020/2/15 20:38
 * @Version 1.0
 **/
@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CertificateMapper certificateMapper;
    @Autowired
    private StudentService studentService;
    @Autowired
    private OrganizationCertificateService organizationCertificateService;


    @Override
    public Certificate getOneCertificateById(String certificateId) {
        Assert.hasText(certificateId,"证书id不能为空");
        Certificate certificate = certificateMapper.selectByPrimaryKey(certificateId);
        return certificate;
    }

    @Override
    public Certificate getCertificateByCertificateName(Certificate certificate) {
        Assert.hasText(certificate.getCertificateName(),"证书名称不能为空");
        Example example = new Example(Certificate.class);
        example.and().andEqualTo("certificateName",certificate.getCertificateName());
        return certificateMapper.selectOneByExample(example);
    }
    /**
     *
     *
     * @description: 插入证书数据
     * @param certificate
     * @return: int
     * @author: 李翅
     * @time: 2020/3/19 21:05
     */
    @Override
    public Result insert(Certificate certificate) {
        Result result = new Result();
        Assert.hasText(certificate.getCertificateName(),"证书名称不能为空");
        Assert.hasText(certificate.getCertificateId(),"证书id不能为空");
        Assert.hasText(certificate.getCertificateLevel(),"证书等级不能为空");
        certificate.setDeleteStatus(Constant.IS_NOT_DELETE);
        int t = certificateMapper.insert(certificate);

        if(certificate.getOrganizationIds()!=null&&certificate.getOrganizationIds().size()!=0){
            for (String o :certificate.getOrganizationIds()) {
                organizationCertificateService.insert(certificate.getCertificateId(),o);
            }
        }

        if(t == 0){
            result.setMessage(certificate.getCertificateName()+"新增失败");
            result.setSuccess(false);
            return result;
        }
        result.setObject(certificate);
        result.setSuccess(true);
        result.setMessage("新增成功");
        return result;
    }

    @Override
    public List<CertificateDto> getCertificateDto(String studentId, String teacherId, String CertificateId) {
        Map<String,Object> params = new HashMap<>(3);
        params.put("studentId",studentId);
        params.put("teacherId",teacherId);
        params.put("CertificateId",CertificateId);
        List<CertificateDto>list = certificateMapper.getCertificateDto(params);
        return list;
    }





    @Override
    public int update(Certificate certificate) {
        Assert.hasText(certificate.getCertificateName(),"证书名称不能为空");
        Assert.hasText(certificate.getCertificateId(),"证书id不能为空");
        Assert.hasText(certificate.getCertificateLevel(),"证书等级不能为空");
        Assert.hasText(certificate.getMatchId(),"赛事id不能为空");
        certificate.setUpdateTime(new Date());
        return certificateMapper.updateByPrimaryKeySelective(certificate);
    }

    @Override
    public int delete(String id) {
        Assert.hasText(id,"id不能为空");
        Certificate certificate = certificateMapper.selectByPrimaryKey(id);
        certificate.setDeleteStatus(Constant.IS_DELETE);
        certificate.setUpdateTime(new Date());
        return certificateMapper.updateByPrimaryKeySelective(certificate);
    }

    @Override
    public int realDel(String id) {
        Assert.hasText(id,"id不能为空");

        return certificateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Certificate> getCertificates(Certificate certificate,String flag) {

        if(flag.equals(Constant.IS_NOT_DELETE ) ){
            return certificateMapper.getCertificateListN(certificate);
        }else{
            return certificateMapper.getCertificateListY(certificate);
        }

    }


    @Override
    public boolean isExistStudentAndCertificate(String studentId, String teacherId, String CertificateId) {
        Map<String,Object> params = new HashMap<>(3);
        params.put("studentId",studentId);
        params.put("teacherId",teacherId);
        params.put("CertificateId",CertificateId);
        int t = certificateMapper.isExistStudentAndCertificate(params);

        if(t>0){
            return true;
        }
        return false;
    }




}
