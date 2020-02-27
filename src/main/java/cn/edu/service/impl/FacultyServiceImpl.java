package cn.edu.service.impl;

import cn.edu.dao.FacultyMapper;
import cn.edu.vo.Faculty;
import cn.edu.service.FacultyService;
import cn.edu.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
