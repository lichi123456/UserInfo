package cn.edu.dao;

import cn.edu.dto.CertificateDto;
import cn.edu.vo.Certificate;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface CertificateMapper extends Mapper<Certificate> {
    /**
     * 导出数据
     * @return
     */
    List<CertificateDto> getCertificateDto(Map<String,Object> params);

    /**
     * 查看数据
     * @param params
     * @return
     */
    int isExistStudentAndCertificate(Map<String,Object>  params);

    //获取所有的删除Certificate列表。
    List<Certificate> getCertificateListY(Certificate certificate);

    /**
     *取所有的未删除Certificate列表。
     * @return
     */
    List<Certificate> getCertificateListN(Certificate certificate);


}