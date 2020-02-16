package cn.edu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`match_type`")
public class MatchType {
    /**
     * 赛事类别id
     */
    @Id
    @Column(name = "`type_id`")
    private String typeId;

    /**
     * 赛事类别(A类、B类、C类)
     */
    @Column(name = "`type_name`")
    private String typeName;

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
     * 获取赛事类别id
     *
     * @return type_id - 赛事类别id
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * 设置赛事类别id
     *
     * @param typeId 赛事类别id
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    /**
     * 获取赛事类别(A类、B类、C类)
     *
     * @return type_name - 赛事类别(A类、B类、C类)
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置赛事类别(A类、B类、C类)
     *
     * @param typeName 赛事类别(A类、B类、C类)
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
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