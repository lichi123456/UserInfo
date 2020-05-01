package cn.edu.service;

import cn.edu.vo.UserCertificateUrl;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserCertificateUrlService
 * @Author: lichi
 * @Date: 2020/4/26 21:14
 * @Description:
 * @Version: 1.0
 */
@Service
public interface UserCertificateUrlService {
    /**
     * 通过用户id和证书id获得
     * @param userId
     * @param certificateId
     * @return
     */
    UserCertificateUrl getUrlById(String userId, String certificateId);
    /**
     * 通过插入
     * @param userCertificateUrl
     * @return
     */
    int insertUrlById(UserCertificateUrl userCertificateUrl);

    /**
     * 删除用户信息
     * @param userCertificateUrlId
     * @return
     */
    int deleteUserCertificateUrl(String userCertificateUrlId);

    /**
     * 修改用户URL
     * @param userCertificateUrl
     * @return
     */
    int updateUserCertificateUrl(UserCertificateUrl userCertificateUrl);
    /**
     * 删除用户信息
     * @param userId
     * *@param certificateId
     * @return
     */
    int deleteUserCertificateUrl(String userId,String certificateId);
}
