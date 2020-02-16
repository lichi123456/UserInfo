package cn.edu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`student`")
public class Student {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`student_id`")
    private String studentId;

    /**
     * 学号
     */
    @Column(name = "`student_code`")
    private String studentCode;

    /**
     * 学生姓名
     */
    @Column(name = "`student_name`")
    private String studentName;

    /**
     * 性别(男，女，未设置)
     */
    @Column(name = "`student_sex`")
    private String studentSex;

    /**
     * 电话号码
     */
    @Column(name = "`student_tel`")
    private String studentTel;

    /**
     * qq
     */
    @Column(name = "`student_qq`")
    private String studentQq;

    /**
     * 邮箱
     */
    @Column(name = "`student_email`")
    private String studentEmail;

    /**
     * 照片/头像的url地址
     */
    @Column(name = "`student_picture_url`")
    private String studentPictureUrl;

    /**
     * 班级id
     */
    @Column(name = "`class_id`")
    private String classId;

    /**
     * 分组id
     */
    @Column(name = "`group_id`")
    private String groupId;

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
     * @return student_id - 主键id
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * 设置主键id
     *
     * @param studentId 主键id
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    /**
     * 获取学号
     *
     * @return student_code - 学号
     */
    public String getStudentCode() {
        return studentCode;
    }

    /**
     * 设置学号
     *
     * @param studentCode 学号
     */
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode == null ? null : studentCode.trim();
    }

    /**
     * 获取学生姓名
     *
     * @return student_name - 学生姓名
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * 设置学生姓名
     *
     * @param studentName 学生姓名
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    /**
     * 获取性别(男，女，未设置)
     *
     * @return student_sex - 性别(男，女，未设置)
     */
    public String getStudentSex() {
        return studentSex;
    }

    /**
     * 设置性别(男，女，未设置)
     *
     * @param studentSex 性别(男，女，未设置)
     */
    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex == null ? null : studentSex.trim();
    }

    /**
     * 获取电话号码
     *
     * @return student_tel - 电话号码
     */
    public String getStudentTel() {
        return studentTel;
    }

    /**
     * 设置电话号码
     *
     * @param studentTel 电话号码
     */
    public void setStudentTel(String studentTel) {
        this.studentTel = studentTel == null ? null : studentTel.trim();
    }

    /**
     * 获取qq
     *
     * @return student_qq - qq
     */
    public String getStudentQq() {
        return studentQq;
    }

    /**
     * 设置qq
     *
     * @param studentQq qq
     */
    public void setStudentQq(String studentQq) {
        this.studentQq = studentQq == null ? null : studentQq.trim();
    }

    /**
     * 获取邮箱
     *
     * @return student_email - 邮箱
     */
    public String getStudentEmail() {
        return studentEmail;
    }

    /**
     * 设置邮箱
     *
     * @param studentEmail 邮箱
     */
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail == null ? null : studentEmail.trim();
    }

    /**
     * 获取照片/头像的url地址
     *
     * @return student_picture_url - 照片/头像的url地址
     */
    public String getStudentPictureUrl() {
        return studentPictureUrl;
    }

    /**
     * 设置照片/头像的url地址
     *
     * @param studentPictureUrl 照片/头像的url地址
     */
    public void setStudentPictureUrl(String studentPictureUrl) {
        this.studentPictureUrl = studentPictureUrl == null ? null : studentPictureUrl.trim();
    }

    /**
     * 获取班级id
     *
     * @return class_id - 班级id
     */
    public String getClassId() {
        return classId;
    }

    /**
     * 设置班级id
     *
     * @param classId 班级id
     */
    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    /**
     * 获取分组id
     *
     * @return group_id - 分组id
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置分组id
     *
     * @param groupId 分组id
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
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