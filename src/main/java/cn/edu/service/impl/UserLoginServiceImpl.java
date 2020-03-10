package cn.edu.service.impl;

import cn.edu.dao.UserLoginMapper;
import cn.edu.service.*;
import cn.edu.utils.ApplicationUtils;
import cn.edu.utils.Constant;
import cn.edu.vo.*;
import cn.edu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserLoginServiceImpl
 * @Description TODO 用户登录管理实现类
 * @Author wys5
 * @Date 2020/2/15 20:43
 * @Version 1.0
 **/
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;
    /**
     * @Author wys
     * @ClassName getLoginUser
     * @Description //TODO  登录验证
     * @Date 15:49 2020/2/23
     * @Param [code, password]
     * @return cn.edu.utils.Result
     **/
    @Override
    public Result getLoginUser(String code, String password) {
        Assert.hasText(code,"账号不能为空");
        Assert.hasText(password,"密码不能为空");
        //根据code获取登录信息表信息
        Example example = new Example(UserLogin.class);
        example.and().andEqualTo("userCode",code);
        UserLogin userLogin = userLoginMapper.selectOneByExample(example);

        Result result = new Result();
        if(userLogin!=null &&userLogin.getUserType()!=null){
            if(userLogin.getUserType().compareTo(Constant.isTeacher)==0){
                Teacher teacher = teacherService.getOneTeacherById(userLogin.getUserId());
                if(teacher.getDeleteStatus().compareTo(Constant.isDelete)==0 || teacher.getDeleteStatus()==null){
                    result.setMessage("当前账号已被禁用");
                    result.setSuccess(false);
                    return result;
                }
            }else if(userLogin.getUserType().compareTo(Constant.isStudent)==0){
                Student student = studentService.getOneStudentById(userLogin.getUserId());
                if(student.getDeleteStatus().compareTo(Constant.isDelete)==0 ||student.getDeleteStatus()==null){
                    result.setMessage("当前账号已被禁用");
                    result.setSuccess(false);
                    return result;
                }
            }else if(userLogin.getUserType().compareTo(Constant.isAdmin)==0){

            }
        }
        if(StringUtils.isEmpty(userLogin)){
            result.setSuccess(false);
            result.setMessage("没有此账号");
        }else{
            if (userLogin.getUserPassword().compareTo(password)!=0){
                result.setSuccess(false);
                result.setMessage("密码错误");
            }else{
                result.setSuccess(true);
                result.setObject(userLogin);
                result.setMessage("密码正确");
            }
        }
        return result;
    }

    /**
     * @Author wys
     * @ClassName insert
     * @Description //TODO  登录表与用户角色关系表插入
     * @Date 18:04 2020/3/5
     * @Param [userLogin]
     * @return int
     **/
    @Override
    public String insert(UserLogin userLogin) {
        Example example = new Example(UserLogin.class);
        example.and().andEqualTo("userCode",userLogin.getUserCode());
        UserLogin userLogin1 = userLoginMapper.selectOneByExample(example);
        if(userLogin1 != null){
            if(userLogin1.getUserId()!=null){
                return "该账号已注册，无法再次插入";
            }
        }
        userLogin.setUserPassword("123456");
        userLogin.setDeleteStatus(Constant.isNotDelete);
        List<Role>roleList = roleService.getRoleList();
        String roleId = null;
        for (Role r:roleList) {
            if (userLogin.getUserType().compareTo(r.getRoleType())==0){
                roleId = r.getRoleId();
                break;
            }
        }
        UserRole userRole = new UserRole();
        if(roleId == null || roleId.trim().compareTo("")==0){
            return "没有当前类型的角色";
        }
        userRole.setRoleId(roleId);
        userRole.setUserId(userLogin.getUserId());
        int t = userLoginMapper.insertSelective(userLogin);
        if(t==0){
            return "插入登录表信息失败";
        }
        int t1 = userRoleService.insert(userRole);
        if(t == 0){
            return "插入用户角色失败";
        }
        return "插入成功";
    }

    /**
     * @Author wys
     * @ClassName update
     * @Description //TODO 更新密码
     * @Date 11:13 2020/3/9
     * @Param [userLogin]
     * @return int
     **/
    @Override
    public int updatePasswordByUserCode(String userId,String newPassword) {
        Assert.hasText(userId,"用户id不能为空");
        Assert.hasText(newPassword,"新密码不能为空");
        UserLogin userLogin = userLoginMapper.selectByPrimaryKey(userId);
        if(userLogin!=null && userLogin.getUserId()!=null){
            userLogin.setUserPassword(newPassword);
        }
        return userLoginMapper.updateByPrimaryKeySelective(userLogin);
    }

    /**
     * @Author wys
     * @ClassName getPasswordById
     * @Description //TODO  根据当前人id获取其密码
     * @Date 13:34 2020/3/9
     * @Param [id]
     * @return java.lang.String
     **/
    @Override
    public String getPasswordById(String id) {
        Assert.hasText(id,"id不能为空");
        return userLoginMapper.selectByPrimaryKey(id).getUserPassword();
    }

    /**
     * @Author wys
     * @ClassName getDeleteUserList
     * @Description //TODO  获取禁用信息列表
     * @Date 17:38 2020/3/10
     * @Param [userLogin]
     * @return java.util.List<cn.edu.vo.UserLogin>
     **/
    @Override
    public List<UserLogin> getDeleteUserList(UserLogin userLogin) {
       List<UserLogin>userLoginList = userLoginMapper.getDeleteUserList(userLogin);
       for(UserLogin u:userLoginList){
           if(u.getUserType().compareTo(Constant.isStudent)==0){
               Student student = studentService.getOneStudentById(u.getUserId());
               u.setUserTypeName("学生");
               u.setTel(student.getStudentTel());
               u.setQq(student.getStudentQq());
               u.setEmail(student.getStudentEmail());
           }else if(u.getUserType().compareTo(Constant.isTeacher)==0){
                Teacher teacher = teacherService.getOneTeacherById(u.getUserId());
                u.setUserTypeName("教师");
                u.setTel(teacher.getTeacherTel());
                u.setQq(teacher.getTeacherQq());
                u.setEmail(teacher.getTeacherEmail());
           }else{

           }
       }
        return userLoginList;
    }

    /**
     * @Author wys
     * @ClassName RecoverUser
     * @Description //TODO  恢复Y->N
     * @Date 16:43 2020/3/10
     * @Param [userLogin]
     * @return int
     **/
    @Override
    public int RecoverUser(UserLogin userLogin) {
        Assert.hasText(userLogin.getUserId(),"主键id不能为空");
        Assert.hasText(userLogin.getUserType(),"角色类型不能为空");
        if(userLogin.getUserType().compareTo(Constant.isStudent)==0){
            return studentService.Recover(userLogin.getUserId());
        }else if (userLogin.getUserType().compareTo(Constant.isTeacher)==0){
            return teacherService.Recover(userLogin.getUserId());
        }else{

        }
        return 0;
    }

    /**
     * @Author wys
     * @ClassName getUserLoginById
     * @Description //TODO  根据主键id获取其数据
     * @Date 16:55 2020/3/10
     * @Param [id]
     * @return cn.edu.vo.UserLogin
     **/
    @Override
    public UserLogin getUserLoginById(String id) {
        Assert.hasText(id,"主键id不能为空");
        return userLoginMapper.selectByPrimaryKey(id);
    }

    /**
     * @Author wys
     * @ClassName RealDeleteUser
     * @Description //TODO  真删除
     * @Date 19:00 2020/3/10
     * @Param [userLogin]
     * @return int
     **/
    @Override
    public int RealDeleteUser(UserLogin userLogin) {
        Assert.hasText(userLogin.getUserType(),"角色类型不能为空");
        Assert.hasText(userLogin.getUserId(),"角色主键id不能为空");
        if(userLogin.getUserType().compareTo(Constant.isStudent)==0){
            return studentService.realDel(userLogin.getUserId());
        }else if(userLogin.getUserType().compareTo(Constant.isTeacher)==0){
            return teacherService.realDel(userLogin.getUserId());
        }else{

        }
        return 0;
    }
}
