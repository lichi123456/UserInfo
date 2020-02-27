package cn.edu.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`match_level`")
@Getter
@Setter
public class MatchLevel {
    /**
     * 赛事级别id
     */
    @Id
    @Column(name = "`level_id`")
    private String levelId;

    /**
     * 赛事级别（国家级、省级、校级、院级）
     */
    @Column(name = "`level_name`")
    private String levelName;

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