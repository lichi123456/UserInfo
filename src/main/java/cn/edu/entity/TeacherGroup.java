package cn.edu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`teacher_group`")
public class TeacherGroup {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`tea_gro_id`")
    private Long teaGroId;

    /**
     * 教师主键id
     */
    @Column(name = "`teacher_id`")
    private String teacherId;

    /**
     * 小组id
     */
    @Column(name = "`group_id`")
    private String groupId;

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
     * 获取主键id
     *
     * @return tea_gro_id - 主键id
     */
    public Long getTeaGroId() {
        return teaGroId;
    }

    /**
     * 设置主键id
     *
     * @param teaGroId 主键id
     */
    public void setTeaGroId(Long teaGroId) {
        this.teaGroId = teaGroId;
    }

    /**
     * 获取教师主键id
     *
     * @return teacher_id - 教师主键id
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * 设置教师主键id
     *
     * @param teacherId 教师主键id
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

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