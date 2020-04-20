package cn.edu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`user_certificate`")
@Getter
@Setter
public class UserCertificate {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`user_cer_id`")
    private Long userCerId;

    /**
     * 用户表（学生/教师）主键id
     */
    @Column(name = "`user_id`")
    private String userId;

    /**
     * 证书id
     */
    @Column(name = "`certificate_id`")
    private String certificateId;

    /**
     * 用户类型（教师，学生，管理员）
     */
    @Column(name = "`user_type`")
    private String userType;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 修改人
     */
    @Column(name = "`update_user`")
    private String updateUser;

    /**
     * 存放额外信息
     */
    @Transient
    private Object object;
}