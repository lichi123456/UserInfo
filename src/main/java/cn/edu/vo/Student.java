package cn.edu.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
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