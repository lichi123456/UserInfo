package cn.edu.service.impl;

import cn.edu.dao.FacultyMapper;
import cn.edu.service.ClassesService;
import cn.edu.service.MajorService;
import cn.edu.vo.Classes;
import cn.edu.vo.Faculty;
import cn.edu.service.FacultyService;
import cn.edu.utils.ApplicationUtils;
import cn.edu.vo.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//        faculty.setCreateUser();
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
//        faculty.setUpdateUser();
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

    @Override
    public List<Faculty> getAllList() {
        List<Faculty>facultyList = getFacultyList();
        List<Major>majorList = majorService.getMajorList();
        List<Classes>classesList = classesService.getClassesList();
        List<Faculty>list=new ArrayList<>();
        for (Faculty f:facultyList ) {
            List<Major>m1 = new ArrayList<>();
            for (Major m:majorList) {
                List<Classes>c1 = new ArrayList<>();
                for (Classes c:classesList) {
                    if(c.getMajorId().compareTo(m.getMajorId())==0){
                        c1.add(c);
                    }
                }
                m.setObject(c1);
                if(m.getFacultyId().compareTo(f.getFacultyId())==0){
                    m1.add(m);
                }
            }
            f.setObject(m1);
            list.add(f);
        }
        return list;
    }
}
