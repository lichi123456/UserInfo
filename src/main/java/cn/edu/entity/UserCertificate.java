package cn.edu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`user_certificate`")
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

    /**
     * 获取主键id
     *
     * @return user_cer_id - 主键id
     */
    public Long getUserCerId() {
        return userCerId;
    }

    /**
     * 设置主键id
     *
     * @param userCerId 主键id
     */
    public void setUserCerId(Long userCerId) {
        this.userCerId = userCerId;
    }

    /**
     * 获取用户表（学生/教师）主键id
     *
     * @return user_id - 用户表（学生/教师）主键id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户表（学生/教师）主键id
     *
     * @param userId 用户表（学生/教师）主键id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取证书id
     *
     * @return certificate_id - 证书id
     */
    public String getCertificateId() {
        return certificateId;
    }

    /**
     * 设置证书id
     *
     * @param certificateId 证书id
     */
    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId == null ? null : certificateId.trim();
    }

    /**
     * 获取用户类型（教师，学生，管理员）
     *
     * @return user_type - 用户类型（教师，学生，管理员）
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 设置用户类型（教师，学生，管理员）
     *
     * @param userType 用户类型（教师，学生，管理员）
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 获取修改日期
     *
     * @return update_time - 修改日期
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改日期
     *
     * @param updateTime 修改日期
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取修改人
     *
     * @return update_user - 修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置修改人
     *
     * @param updateUser 修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }
}