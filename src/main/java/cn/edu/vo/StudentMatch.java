package cn.edu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`student_match`")
@Getter
@Setter
public class StudentMatch {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`stu_mat_id`")
    private Long stuMatId;

    /**
     * 学生表主键id
     */
    @Column(name = "`student_id`")
    private String studentId;

    /**
     * 赛事主键id
     */
    @Column(name = "`match_id`")
    private String matchId;

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
}