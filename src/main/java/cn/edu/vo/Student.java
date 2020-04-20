package cn.edu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Data
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
     * 班级名称
     */
    @Transient
    private String className;
    /**
     * 专业id
     */
    @Transient
    private String majorId;
    /**
     * 专业名称
     */
    @Transient
    private String majorName;
    /**
     * 院系id
     */
    @Transient
    private String facultyId;
    /**
     * 院系名称
     */
    @Transient
    private String facultyName;
    /**
     * 分组id
     */
    @Column(name = "`group_id`")
    private String groupId;
    /**
     * 分组名称
     */
    @Transient
    private String groupName;
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
     * 模糊查询
     */
    @Transient
    private String condition;

    /**
     * 当前学生的密码
     */
    @Transient
    private String password;
    /**
     * 指导老师信息组
     */
    @Transient
    private List<Teacher> tutor;
    /**
     * 指导老师姓名组合
     */
    @Transient
    private String tutorName;
    /**
     * 插入或修改的小组列表
     */
    @Transient
    private List<String>changeTutorList;
    /**
     * 存放额外信息
     */
    @Transient
    private Object object;

}