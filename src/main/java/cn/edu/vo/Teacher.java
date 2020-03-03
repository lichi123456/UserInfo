package cn.edu.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Getter
@Setter
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
    @Column(name = "`teacher_email`")
    private String teacherEmail;

    /**
     * 照片/头像地址
     */
    @Column(name = "`teacher_picture_url`")
    private String teacherPictureUrl;

    /**
     * 假删除标识（已删除：Y，使用：N）
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
     * 存放额外信息
     */
    @Transient
    private Object object;
}