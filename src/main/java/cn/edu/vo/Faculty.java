package cn.edu.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`faculty`")
@Getter
@Setter
public class Faculty {
    /**
     * 学院id
     */
    @Id
    @Column(name = "`faculty_id`")
    private String facultyId;

    /**
     * 学院名
     */
    @Column(name = "`faculty_name`")
    private String facultyName;

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