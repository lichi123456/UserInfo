package cn.edu.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`groups`")
@Getter
@Setter
public class Groups {
    /**
     * 小组id
     */
    @Id
    @Column(name = "`group_id`")
    private String groupId;

    /**
     * 小组名称
     */
    @Column(name = "`group_name`")
    private String groupName;

    /**
     * 职位（成员，组长，财务等）
     */
    @Column(name = "`group_position`")
    private String groupPosition;

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