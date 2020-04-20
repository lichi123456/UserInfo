package cn.edu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
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
     * 存放MajorList
     */
    @Transient
    private List<Major> majorList;
    /**
     * 存放额外信息
     */
    @Transient
    private Object object;
}