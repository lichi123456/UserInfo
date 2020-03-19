package cn.edu.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "`classes`")
@Getter
@Setter
public class Classes {
    /**
     * 班级id
     */
    @Id
    @Column(name = "`class_id`")
    private String classId;

    /**
     * 班级名称
     */
    @Column(name = "`class_name`")
    private String className;

    /**
     * 专业id
     */
    @Column(name = "`major_id`")
    private String majorId;

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
     * 存放studentList
     */
    @Transient
    private List<Student>studentList;
    /**
     * 存放额外信息
     */
    @Transient
    private Object object;
}