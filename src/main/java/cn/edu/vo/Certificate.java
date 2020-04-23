package cn.edu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "`certificate`")
@Getter
@Setter
public class Certificate {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`certificate_id`")
    private String certificateId;

    /**
     * 证书获取时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "`certificate_date`")
    private Date certificateDate;

    /**
     * 证书名
     */
    @Column(name = "`certificate_name`")
    private String certificateName;

    /**
     * 证书等级（特等奖、一等奖、二等奖、三等奖、参与奖。。）
     */
    @Column(name = "`certificate_level`")
    private String certificateLevel;

    /**
     * 证书相片url地址
     */
    @Column(name = "`certificate_picture_url`")
    private String certificatePictureUrl;

    /**
     * 赛事id
     */
    @Column(name = "`match_id`")
    private String matchId;

    /**
     * 假删除标识（已删除：Y，使用：N）
     */
    @Column(name = "`delete_status`")
    private String deleteStatus;

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
    /**
     * 存放额外信息
     */
    @Transient
    private List<Organization> organizations;
    @Transient
    private List<String> organizationIds;
    /**
     * 存放组织名字
     */
    @Transient
    private String organizationNames;
    @Transient
    private String matchName;

}