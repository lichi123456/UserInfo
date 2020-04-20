package cn.edu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "`teacher_group`")
@Getter
@Setter
public class TeacherGroup {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`tea_gro_id`")
    private Long teaGroId;

    /**
     * 教师主键id
     */
    @Column(name = "`teacher_id`")
    private String teacherId;

    /**
     * 小组id
     */
    @Column(name = "`group_id`")
    private String groupId;

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
     * 修改使用的groupIdList
     */
    @Transient
    private List<String>groupIdList;
    /**
     * 存放额外信息
     */
    @Transient
    private Object object;
}