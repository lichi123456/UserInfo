package cn.edu.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "`user_certificate`")
public class UserCertificate {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`user_cer_id`")
    private Long userCerId;

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
     * 用户表（学生/教师）主键id
     */
    @Column(name = "`student_id1`")
    private String studentId1;

    /**
     * 学生
     */
    @Column(name = "`student_id2`")
    private String studentId2;

    /**
     * 学生
     */
    @Column(name = "`student_id3`")
    private String studentId3;

    /**
     * 学生
     */
    @Column(name = "`student_id4`")
    private String studentId4;

    /**
     * 学生
     */
    @Column(name = "`student_id5`")
    private String studentId5;

    /**
     * 学生
     */
    @Column(name = "`student_id6`")
    private String studentId6;

    /**
     * 学生
     */
    @Column(name = "`student_id7`")
    private String studentId7;

    /**
     * 学生
     */
    @Column(name = "`student_id8`")
    private String studentId8;

    /**
     * 学生
     */
    @Column(name = "`student_id9`")
    private String studentId9;

    /**
     * 学生
     */
    @Column(name = "`student_id10`")
    private String studentId10;

    /**
     * 老师

     */
    @Column(name = "`teacher_id1`")
    private String teacherId1;

    /**
     * 老师
     */
    @Column(name = "`teacher_id2`")
    private String teacherId2;

    /**
     * 老师
     */
    @Column(name = "`teacher_id3`")
    private String teacherId3;

    /**
     * 证书地址
     */
    @Column(name = "`url`")
    private String url;

    /**
     * 证书等级
     */
    @Column(name = "`certificate_level`")
    private String certificateLevel;

    /**
     * 赛事Id
     */
    @Column(name = "`match_id`")
    private String matchId;

    /**
     * 赛事类别
     */
    @Column(name = "`match_type`")
    private String matchType;

    /**
     * 赛事等级2段
     */
    @Column(name = "`match_level_level`")
    private String matchLevelLevel;
}