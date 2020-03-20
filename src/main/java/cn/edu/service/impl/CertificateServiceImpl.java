package cn.edu.service.impl;

import cn.edu.dao.CertificateMapper;
import cn.edu.service.CertificateService;
import cn.edu.utils.ApplicationUtils;
import cn.edu.utils.Constant;
import cn.edu.utils.Result;
import cn.edu.vo.Certificate;
import cn.edu.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

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
        certificate.setDeleteStatus(Constant.isNotDelete);
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

}
