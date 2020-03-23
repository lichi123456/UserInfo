package cn.edu.service.impl;

import cn.edu.dao.CertificateMapper;
import cn.edu.dto.CertificateDto;
import cn.edu.service.CertificateService;
import cn.edu.service.StudentService;
import cn.edu.utils.ApplicationUtils;
import cn.edu.utils.Constant;
import cn.edu.utils.Result;
import cn.edu.vo.Certificate;
import cn.edu.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Assert.hasText(certificate.getCertificateDate().toString(),"证书获取时间不能为空");
        Assert.hasText(certificate.getMatchId(),"赛事id不能为空");
        certificate.setCertificateId(ApplicationUtils.GUID32());
        certificate.setDeleteStatus(Constant.IS_NOT_DELETE);
        int t = certificateMapper.insert(certificate);
        if(t == 0){
            result.setMessage(certificate.getCertificateName()+"新增失败");
            result.setSuccess(false);
            return result;
        }
        result.setObject(true);
        result.setMessage("新增成功");
        return result;
    }

    @Override
    public List<CertificateDto> getCertificateDto(String studentId, String teacherId, String CertificateId) {
        Map<String,Object> params = new HashMap<>(3);
        params.put("studentId",studentId);
        params.put("teacherId",teacherId);
        params.put("CertificateId",CertificateId);
        return certificateMapper.getCertificateDto(params);
    }

    @Override
    public List<CertificateDto> getCertificateDtoList(String studentId, String teacherId, String certificateId ){
        Student student = new Student();
        if(studentId!=null){
            student.setStudentId(studentId);
        }
        Certificate certificate = new Certificate();
        if(certificateId !=null){
            certificate.setCertificateId(certificateId);
        }
        List<CertificateDto> certificateDtoList = new ArrayList<>();
        List<Student> students = studentService.getStudentListWithConditionAndDeleteStatus(student,Constant.IS_NOT_DELETE);
        List<Certificate> certificates = getCertificateListWithConditionAndDeleteStatus(certificate,Constant.IS_NOT_DELETE);
        for (Student s:students) {
            for(Certificate c : certificates){
                if(isExistStudentAndCertificate(s.getStudentId(),null,certificateId)){
                    List<CertificateDto> certificateDtos = getCertificateDto(s.getStudentId(),null,certificateId);
                    CertificateDto certificateDto = certificateDtos.get(0);
                    if(certificateDtos.size()<=2){
                        certificateDto.setTeacher2(certificateDtos.get(1).getTeacher1());
                    }
                    if(certificateDtos.size()<=3){
                        certificateDto.setTeacher3(certificateDtos.get(2).getTeacher1());
                    }
                    certificateDtoList.add(certificateDto);
                }

            }
        }
        return certificateDtoList;
    }

    @Override
    public List<Certificate> getCertificateListWithConditionAndDeleteStatus(Certificate certificate, String deleteStatus) {
        List<Certificate> certificateList = null;
        if(deleteStatus.trim().compareTo(Constant.IS_NOT_DELETE)==0){
            certificateList = certificateMapper.getCertificateListN(certificate);
        }else if(deleteStatus.trim().compareTo(Constant.IS_NOT_DELETE)==0){
            certificateList = certificateMapper.getCertificateListY(certificate);
        }
        List<Certificate>list=new ArrayList<>();
        certificateList.stream().forEach(s->{
            list.add(getOneCertificateById(s.getCertificateId()));
        });
        return list;
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
