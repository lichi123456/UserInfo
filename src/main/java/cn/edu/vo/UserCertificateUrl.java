package cn.edu.vo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "`user_certificate_url`")
public class UserCertificateUrl {
    /**
     * 用户证书地址id
     */
    @Id
    @Column(name = "`user_certificate_url_id`")
    private Long userCertificateUrlId;

    /**
     * 用户id
     */
    @Column(name = "`user_id`")
    private String userId;

    /**
     * 用户证书图片地址
     */
    @Column(name = "`user_certificate_url`")
    private String userCertificateUrl;

    /**
     * 证书id
     */
    @Column(name = "`certificate_id`")
    private String certificateId;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "`create_user`")
    private String createUser;

    /**
     * 修改日期
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 修改人
     */
    @Column(name = "`update_user`")
    private String updateUser;


}