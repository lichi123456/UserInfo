package cn.edu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "`major`")
@Getter
@Setter
public class Major {
    /**
     * 专业id
     */
    @Id
    @Column(name = "`major_id`")
    private String majorId;

    /**
     * 专业名称
     */
    @Column(name = "`major_name`")
    private String majorName;

    /**
     * 学院id
     */
    @Column(name = "`faculty_id`")
    private String facultyId;

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
     * 存放classList
     */
    @Transient
    private List<Classes> classesList;
    /**
     * 存放额外信息
     */
    @Transient
    private Object object;
}