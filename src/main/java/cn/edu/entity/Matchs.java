package cn.edu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`matchs`")
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
     * 获取赛事id
     *
     * @return match_id - 赛事id
     */
    public String getMatchId() {
        return matchId;
    }

    /**
     * 设置赛事id
     *
     * @param matchId 赛事id
     */
    public void setMatchId(String matchId) {
        this.matchId = matchId == null ? null : matchId.trim();
    }

    /**
     * 获取赛事名称
     *
     * @return match_name - 赛事名称
     */
    public String getMatchName() {
        return matchName;
    }

    /**
     * 设置赛事名称
     *
     * @param matchName 赛事名称
     */
    public void setMatchName(String matchName) {
        this.matchName = matchName == null ? null : matchName.trim();
    }

    /**
     * 获取比赛地点
     *
     * @return match_place - 比赛地点
     */
    public String getMatchPlace() {
        return matchPlace;
    }

    /**
     * 设置比赛地点
     *
     * @param matchPlace 比赛地点
     */
    public void setMatchPlace(String matchPlace) {
        this.matchPlace = matchPlace == null ? null : matchPlace.trim();
    }

    /**
     * 获取赛事开始时间
     *
     * @return match_date - 赛事开始时间
     */
    public Date getMatchDate() {
        return matchDate;
    }

    /**
     * 设置赛事开始时间
     *
     * @param matchDate 赛事开始时间
     */
    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    /**
     * 获取赛事级别id
     *
     * @return match_level_id - 赛事级别id
     */
    public String getMatchLevelId() {
        return matchLevelId;
    }

    /**
     * 设置赛事级别id
     *
     * @param matchLevelId 赛事级别id
     */
    public void setMatchLevelId(String matchLevelId) {
        this.matchLevelId = matchLevelId == null ? null : matchLevelId.trim();
    }

    /**
     * 获取赛事类别id
     *
     * @return match_type_id - 赛事类别id
     */
    public String getMatchTypeId() {
        return matchTypeId;
    }

    /**
     * 设置赛事类别id
     *
     * @param matchTypeId 赛事类别id
     */
    public void setMatchTypeId(String matchTypeId) {
        this.matchTypeId = matchTypeId == null ? null : matchTypeId.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 获取修改日期
     *
     * @return update_time - 修改日期
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改日期
     *
     * @param updateTime 修改日期
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取修改人
     *
     * @return update_user - 修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置修改人
     *
     * @param updateUser 修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }
}