package cn.edu.service.impl;
/**
 * @ClassName: UserCertificateUrlServiceImpl
 * @Author: lichi
 * @Date: 2020/4/26 21:27
 * @Description:
 * @Version: 1.0
 */

import cn.edu.dao.UserCertificateUrlMapper;
import cn.edu.service.UserCertificateUrlService;
import cn.edu.vo.Classes;
import cn.edu.vo.UserCertificateUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

/**
 *
 *
 *@description:
 *@author: Andy
 *@time: 2020/4/26 21:27
 *
 */
@Service
public class UserCertificateUrlServiceImpl implements UserCertificateUrlService {
    @Autowired
    UserCertificateUrlMapper userCertificateUrlMapper;


    @Override
    public UserCertificateUrl getUrlById(String userId, String certificateId) {
        Assert.hasText(userId,"用户id不能为空");
        Assert.hasText(certificateId,"学生证书id不能为空");
        Example example = new Example(UserCertificateUrl.class);
        example.and().andEqualTo("userId",userId);
        example.and().andEqualTo("certificateId",certificateId);

        return userCertificateUrlMapper.selectOneByExample(example);
    }

    @Override
    public int insertUrlById(UserCertificateUrl userCertificateUrl) {
        Assert.hasText(userCertificateUrl.getUserId(),"用户id不能为空");
        Assert.hasText(userCertificateUrl.getCertificateId(),"证书id不能为空");
        return userCertificateUrlMapper.insert(userCertificateUrl);
    }

    @Override
    public int deleteUserCertificateUrl(String userCertificateUrlId) {
        return userCertificateUrlMapper.deleteByPrimaryKey(userCertificateUrlId);
    }

    @Override
    public int updateUserCertificateUrl(UserCertificateUrl userCertificateUrl) {
        Assert.hasText(userCertificateUrl.getUserId(),"用户id不能为空");
        Assert.hasText(userCertificateUrl.getCertificateId(),"证书id不能为空");

        return userCertificateUrlMapper.updateByPrimaryKeySelective(userCertificateUrl);
    }

    @Override
    public int deleteUserCertificateUrl(String userId, String certificateId) {
        Assert.hasText(userId,"用户id不能为空");
        Assert.hasText(certificateId,"证书id不能为空");
        UserCertificateUrl userCertificateUrl = new UserCertificateUrl();
        userCertificateUrl.setCertificateId(certificateId);
        userCertificateUrl.setUserId(userId);
        userCertificateUrl = userCertificateUrlMapper.selectOne(userCertificateUrl);
        return userCertificateUrlMapper.delete(userCertificateUrl);
    }


}
