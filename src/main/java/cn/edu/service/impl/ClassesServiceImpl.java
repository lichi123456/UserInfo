package cn.edu.service.impl;

import cn.edu.dao.ClassesMapper;
import cn.edu.vo.Classes;
import cn.edu.service.ClassesService;
import cn.edu.utils.ApplicationUtils;
import cn.edu.vo.Major;
import cn.edu.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
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
     * @ClassName getOneClassesById
     * @Description //TODO  获取当前学生班级信息
     * @Date 19:29 2020/2/27
     * @Param [classId]
     * @return cn.edu.vo.Classes
     **/
    @Override
    public Classes getOneClassesById(String classId) {
//        Assert.hasText(classId,"班级id不能为空");
        return classesMapper.selectByPrimaryKey(classId);
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
    public int insert(Classes classes) {
        Assert.hasText(classes.getMajorId(),"专业id不能为空");
        Assert.hasText(classes.getClassName(),"班级名称不能为空");
        classes.setClassId(ApplicationUtils.GUID32());
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

    @Override
    public String[] getClassesNameByMajorId(Major major) {
        Assert.hasText(major.getMajorId(),"MajorId 不能为空");
        Example example = new Example(Classes.class);
        example.and().andEqualTo("majorId",major.getMajorId());
        List<Classes> classesList = classesMapper.selectByExample(example);
        String [] classList = new String[classesList.size()];
        for(int i=0;i<classesList.size();i++){
            classList[i]=classesList.get(i).getClassName();
        }
        return classList;
    }

    /**
     * 李翅
     * 通过id获得姓名
     * @param className
     * @return
     */
    @Override
    public String getIdByName(String className) {
        Example example = new Example(Classes.class);
        example.and().andEqualTo("className",className);
        Classes classes = classesMapper.selectOneByExample(example);
        return classes.getClassId();
    }
}
