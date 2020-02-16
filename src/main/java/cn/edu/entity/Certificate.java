package cn.edu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`certificate`")
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
     * @return certificate_id - 主键id
     */
    public String getCertificateId() {
        return certificateId;
    }

    /**
     * 设置主键id
     *
     * @param certificateId 主键id
     */
    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId == null ? null : certificateId.trim();
    }

    /**
     * 获取证书获取时间
     *
     * @return certificate_date - 证书获取时间
     */
    public Date getCertificateDate() {
        return certificateDate;
    }

    /**
     * 设置证书获取时间
     *
     * @param certificateDate 证书获取时间
     */
    public void setCertificateDate(Date certificateDate) {
        this.certificateDate = certificateDate;
    }

    /**
     * 获取证书名
     *
     * @return certificate_name - 证书名
     */
    public String getCertificateName() {
        return certificateName;
    }

    /**
     * 设置证书名
     *
     * @param certificateName 证书名
     */
    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName == null ? null : certificateName.trim();
    }

    /**
     * 获取证书等级（特等奖、一等奖、二等奖、三等奖、参与奖。。）
     *
     * @return certificate_level - 证书等级（特等奖、一等奖、二等奖、三等奖、参与奖。。）
     */
    public String getCertificateLevel() {
        return certificateLevel;
    }

    /**
     * 设置证书等级（特等奖、一等奖、二等奖、三等奖、参与奖。。）
     *
     * @param certificateLevel 证书等级（特等奖、一等奖、二等奖、三等奖、参与奖。。）
     */
    public void setCertificateLevel(String certificateLevel) {
        this.certificateLevel = certificateLevel == null ? null : certificateLevel.trim();
    }

    /**
     * 获取证书相片url地址
     *
     * @return certificate_picture_url - 证书相片url地址
     */
    public String getCertificatePictureUrl() {
        return certificatePictureUrl;
    }

    /**
     * 设置证书相片url地址
     *
     * @param certificatePictureUrl 证书相片url地址
     */
    public void setCertificatePictureUrl(String certificatePictureUrl) {
        this.certificatePictureUrl = certificatePictureUrl == null ? null : certificatePictureUrl.trim();
    }

    /**
     * 获取赛事id
     *
     * @return match_id - 赛事id
     */
    public String getMatchId() {
        return matchId;
    }

    /**
     * 设置赛事id
     *
     * @param matchId 赛事id
     */
    public void setMatchId(String matchId) {
        this.matchId = matchId == null ? null : matchId.trim();
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