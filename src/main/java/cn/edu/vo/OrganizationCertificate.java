package cn.edu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`organization_certificate`")
@Getter
@Setter
public class OrganizationCertificate {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`org_cer_id`")
    private Long orgCerId;

    /**
     * 发证机关id
     */
    @Column(name = "`organization_id`")
    private String organizationId;

    /**
     * 证书id
     */
    @Column(name = "`certificate_id`")
    private String certificateId;

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