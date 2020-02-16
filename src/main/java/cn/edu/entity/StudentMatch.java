package cn.edu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`student_match`")
public class StudentMatch {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`stu_mat_id`")
    private Long stuMatId;

    /**
     * 学生表主键id
     */
    @Column(name = "`student_id`")
    private String studentId;

    /**
     * 赛事主键id
     */
    @Column(name = "`match_id`")
    private String matchId;

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
     * @return stu_mat_id - 主键id
     */
    public Long getStuMatId() {
        return stuMatId;
    }

    /**
     * 设置主键id
     *
     * @param stuMatId 主键id
     */
    public void setStuMatId(Long stuMatId) {
        this.stuMatId = stuMatId;
    }

    /**
     * 获取学生表主键id
     *
     * @return student_id - 学生表主键id
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * 设置学生表主键id
     *
     * @param studentId 学生表主键id
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    /**
     * 获取赛事主键id
     *
     * @return match_id - 赛事主键id
     */
    public String getMatchId() {
        return matchId;
    }

    /**
     * 设置赛事主键id
     *
     * @param matchId 赛事主键id
     */
    public void setMatchId(String matchId) {
        this.matchId = matchId == null ? null : matchId.trim();
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