package cn.edu.dao;

import cn.edu.vo.UserLogin;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserLoginMapper extends Mapper<UserLogin> {
    List<UserLogin>getDeleteUserList(UserLogin userLogin);
}