package cn.edu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`groups`")
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
     * 获取小组id
     *
     * @return group_id - 小组id
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置小组id
     *
     * @param groupId 小组id
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     * 获取小组名称
     *
     * @return group_name - 小组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置小组名称
     *
     * @param groupName 小组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    /**
     * 获取职位（成员，组长，财务等）
     *
     * @return group_position - 职位（成员，组长，财务等）
     */
    public String getGroupPosition() {
        return groupPosition;
    }

    /**
     * 设置职位（成员，组长，财务等）
     *
     * @param groupPosition 职位（成员，组长，财务等）
     */
    public void setGroupPosition(String groupPosition) {
        this.groupPosition = groupPosition == null ? null : groupPosition.trim();
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