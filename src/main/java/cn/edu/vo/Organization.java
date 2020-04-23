package cn.edu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "`organization`")
public class Organization {
    /**
     * 发证机关id
     */
    @Id
    @Column(name = "`organization_id`")
    private String organizationId;

    /**
     * 发证机关名称
     */
    @Column(name = "`organization_name`")
    private String organizationName;

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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 修改人
     */
    @Column(name = "`update_user`")
    private String updateUser;

    /**
     * 地点
     */
    @Column(name = "`organization_location`")
    private String organizationLocation;

    /**
     * 电话
     */
    @Column(name = "`organization_tel`")
    private String organizationTel;


}