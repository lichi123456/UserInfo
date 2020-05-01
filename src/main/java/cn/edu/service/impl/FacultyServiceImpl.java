package cn.edu.service.impl;

import cn.edu.dao.FacultyMapper;
import cn.edu.service.ClassesService;
import cn.edu.service.MajorService;
import cn.edu.service.StudentService;
import cn.edu.utils.Constant;
import cn.edu.vo.*;
import cn.edu.service.FacultyService;
import cn.edu.utils.ApplicationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName FacultyServiceImpl
 * @Description TODO 院系管理实现类
 * @Author wys5
 * @Date 2020/2/15 16:41
 * @Version 1.0
 **/
@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyMapper facultyMapper;

    @Autowired
    private MajorService majorService;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private StudentService studentService;
    /**
     * @Author wys
     * @ClassName getFacultyList
     * @Description //TODO  获取院系列表
     * @Date 16:50 2020/2/15
     * @Param []
     * @return java.util.List<cn.edu.vo.Faculty>
     **/
    @Override
    public List<Faculty> getFacultyList() {
        return facultyMapper.selectAll();
    }

    /**
     * @Author wys
     * @ClassName getFacultyById
     * @Description //TODO  获取当前专业对应院系信息
     * @Date 20:20 2020/2/27
     * @Param [facultyId]
     * @return cn.edu.vo.Faculty
     **/
    @Override
    public Faculty getFacultyById(String facultyId) {
        return facultyMapper.selectByPrimaryKey(facultyId);
    }

    /**
     * @Author wys
     * @ClassName insert
     * @Description //TODO  新增院系
     * @Date 16:50 2020/2/15
     * @Param [faculty]
     * @return int
     **/
    @Override
    public int insert(Faculty faculty) {
        faculty.setFacultyId(ApplicationUtils.GUID32());
        return facultyMapper.insert(faculty);
    }

    /**
     * @Author wys
     * @ClassName update
     * @Description //TODO  更新院系
     * @Date 16:51 2020/2/15
     * @Param [faculty]
     * @return int
     **/
    @Override
    public int update(Faculty faculty) {
        faculty.setUpdateTime(new Date());
        return facultyMapper.updateByPrimaryKeySelective(faculty);
    }

    /**
     * @Author wys
     * @ClassName delete
     * @Description //TODO  删除院系,真删除
     * @Date 16:51 2020/2/15
     * @Param [id]
     * @return int
     **/
    @Override
    public int delete(String id) {
        return facultyMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Author wys
     * @ClassName getAllList
     * @Description //TODO
     * @Date 16:51 2020/2/15 院系-专业-班级-学生信息组装
     * @Param []
     * @return java.util.List<cn.edu.vo.Faculty>
     **/
    @Override
    public List<Faculty> getAllList() {
        //院系，专业，班级信息获取
        List<Faculty>facultyList = getFacultyList();
        List<Major>majorList = majorService.getMajorList();
        List<Classes>classesList = classesService.getClassesList();
        //信息组装

        List<Faculty>list=new ArrayList<>();//院系
        for (Faculty f:facultyList ) {
            List<Major>m1 = new ArrayList<>();//专业
            for (Major m:majorList) {
                List<Classes>c1 = new ArrayList<>();//班级
                for (Classes c:classesList) {
                    if(c.getMajorId().compareTo(m.getMajorId())==0){
                        c1.add(c);
                    }
                }
                m.setClassesList(c1);
                if(m.getFacultyId().compareTo(f.getFacultyId())==0){
                    m1.add(m);
                }
            }
            f.setMajorList(m1);
            list.add(f);
        }
        return list;
    }

    @Override
    public Faculty getFacultyByName(String facultyName) {
        Assert.hasText(facultyName,"facultyName 不能为空");
        Example example = new Example(Faculty.class);
        example.and().andEqualTo("facultyName",facultyName);
        return facultyMapper.selectOneByExample(example);
    }

}