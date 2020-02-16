package cn.edu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`match_level`")
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
     * 获取赛事级别id
     *
     * @return level_id - 赛事级别id
     */
    public String getLevelId() {
        return levelId;
    }

    /**
     * 设置赛事级别id
     *
     * @param levelId 赛事级别id
     */
    public void setLevelId(String levelId) {
        this.levelId = levelId == null ? null : levelId.trim();
    }

    /**
     * 获取赛事级别（国家级、省级、校级、院级）
     *
     * @return level_name - 赛事级别（国家级、省级、校级、院级）
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * 设置赛事级别（国家级、省级、校级、院级）
     *
     * @param levelName 赛事级别（国家级、省级、校级、院级）
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
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