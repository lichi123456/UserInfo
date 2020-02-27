package cn.edu.service.impl;

import cn.edu.dao.ClassesMapper;
import cn.edu.vo.Classes;
import cn.edu.service.ClassesService;
import cn.edu.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ClassesServiceImpl
 * @Description TODO 班级管理实现类
 * @Author wys5
 * @Date 2020/2/15 17:05
 * @Version 1.0
 **/
@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesMapper classesMapper;

    /**
     * @Author wys
     * @ClassName getClassesList
     * @Description //TODO  获取班级列表
     * @Date 17:08 2020/2/15
     * @Param []
     * @return java.util.List<cn.edu.vo.Classes>
     **/
    @Override
    public List<Classes> getClassesList() {
        return classesMapper.selectAll();
    }

    /**
     * @Author wys
     * @ClassName insert
     * @Description //TODO  新增班级
     * @Date 17:09 2020/2/15
     * @Param [classes, majorId]
     * @return int
     **/
    @Override
    public int insert(Classes classes, String majorId) {
        classes.setClassId(ApplicationUtils.GUID32());
        classes.setMajorId(majorId);
//        classes.setCreateUser();
        return classesMapper.insert(classes);
    }

    /**
     * @Author wys
     * @ClassName update
     * @Description //TODO  更新
     * @Date 17:09 2020/2/15
     * @Param [classes]
     * @return int
     **/
    @Override
    public int update(Classes classes) {
//        classes.setUpdateUser();
        classes.setUpdateTime(new Date());
        return classesMapper.updateByPrimaryKeySelective(classes);
    }
    /**
     * @Author wys
     * @ClassName delete
     * @Description //TODO  真删除
     * @Date 17:09 2020/2/15
     * @Param [id]
     * @return int
     **/
    @Override
    public int delete(String id) {
        return classesMapper.deleteByPrimaryKey(id);
    }
}
