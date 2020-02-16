package cn.edu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`teacher`")
public class Teacher {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`teacher_id`")
    private String teacherId;

    /**
     * 教师工号
     */
    @Column(name = "`teacher_code`")
    private String teacherCode;

    /**
     * 教师姓名
     */
    @Column(name = "`teacher_name`")
    private String teacherName;

    /**
     * 性别(男，女，未设置)
     */
    @Column(name = "`teacher_sex`")
    private String teacherSex;

    /**
     * 电话号码
     */
    @Column(name = "`teacher_tel`")
    private String teacherTel;

    /**
     * qq
     */
    @Column(name = "`teacher_qq`")
    private String teacherQq;

    /**
     * 邮箱
     */
    @Column(name = "`teacher_mail`")
    private String teacherMail;

    /**
     * 照片/头像地址
     */
    @Column(name = "`teacher_picture_url`")
    private String teacherPictureUrl;

    /**
     * 假删除标识（启用：Y，禁用：N）
     */
    @Column(name = "`delete_status`")
    private String deleteStatus;

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
     * @return teacher_id - 主键id
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * 设置主键id
     *
     * @param teacherId 主键id
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    /**
     * 获取教师工号
     *
     * @return teacher_code - 教师工号
     */
    public String getTeacherCode() {
        return teacherCode;
    }

    /**
     * 设置教师工号
     *
     * @param teacherCode 教师工号
     */
    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode == null ? null : teacherCode.trim();
    }

    /**
     * 获取教师姓名
     *
     * @return teacher_name - 教师姓名
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * 设置教师姓名
     *
     * @param teacherName 教师姓名
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    /**
     * 获取性别(男，女，未设置)
     *
     * @return teacher_sex - 性别(男，女，未设置)
     */
    public String getTeacherSex() {
        return teacherSex;
    }

    /**
     * 设置性别(男，女，未设置)
     *
     * @param teacherSex 性别(男，女，未设置)
     */
    public void setTeacherSex(String teacherSex) {
        this.teacherSex = teacherSex == null ? null : teacherSex.trim();
    }

    /**
     * 获取电话号码
     *
     * @return teacher_tel - 电话号码
     */
    public String getTeacherTel() {
        return teacherTel;
    }

    /**
     * 设置电话号码
     *
     * @param teacherTel 电话号码
     */
    public void setTeacherTel(String teacherTel) {
        this.teacherTel = teacherTel == null ? null : teacherTel.trim();
    }

    /**
     * 获取qq
     *
     * @return teacher_qq - qq
     */
    public String getTeacherQq() {
        return teacherQq;
    }

    /**
     * 设置qq
     *
     * @param teacherQq qq
     */
    public void setTeacherQq(String teacherQq) {
        this.teacherQq = teacherQq == null ? null : teacherQq.trim();
    }

    /**
     * 获取邮箱
     *
     * @return teacher_mail - 邮箱
     */
    public String getTeacherMail() {
        return teacherMail;
    }

    /**
     * 设置邮箱
     *
     * @param teacherMail 邮箱
     */
    public void setTeacherMail(String teacherMail) {
        this.teacherMail = teacherMail == null ? null : teacherMail.trim();
    }

    /**
     * 获取照片/头像地址
     *
     * @return teacher_picture_url - 照片/头像地址
     */
    public String getTeacherPictureUrl() {
        return teacherPictureUrl;
    }

    /**
     * 设置照片/头像地址
     *
     * @param teacherPictureUrl 照片/头像地址
     */
    public void setTeacherPictureUrl(String teacherPictureUrl) {
        this.teacherPictureUrl = teacherPictureUrl == null ? null : teacherPictureUrl.trim();
    }

    /**
     * 获取假删除标识（启用：Y，禁用：N）
     *
     * @return delete_status - 假删除标识（启用：Y，禁用：N）
     */
    public String getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置假删除标识（启用：Y，禁用：N）
     *
     * @param deleteStatus 假删除标识（启用：Y，禁用：N）
     */
    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus == null ? null : deleteStatus.trim();
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