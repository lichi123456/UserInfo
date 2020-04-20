package cn.edu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`matchs`")
@Getter
@Setter
public class Matchs {
    /**
     * 赛事id
     */
    @Id
    @Column(name = "`match_id`")
    private String matchId;

    /**
     * 赛事名称
     */
    @Column(name = "`match_name`")
    private String matchName;

    /**
     * 比赛地点
     */
    @Column(name = "`match_place`")
    private String matchPlace;

    /**
     * 赛事开始时间
     */
    @Column(name = "`match_date`")
    private Date matchDate;

    /**
     * 赛事级别id
     */
    @Column(name = "`match_level_id`")
    private String matchLevelId;

    /**
     * 赛事类别id
     */
    @Column(name = "`match_type_id`")
    private String matchTypeId;

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