package cn.edu.service.impl;

import cn.edu.dao.StudentMapper;
import cn.edu.entity.Student;
import cn.edu.service.StudentService;
import cn.edu.utils.Constant;
import cn.edu.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Description TODO 用户信息管理实现类
 * @Author wys5
 * @Date 2020/2/14 17:17
 * @Version 1.0
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * @Author wys
     * @ClassName getStudentList
     * @Description //TODO 获取学生信息列表
     * @Date 16:37 2020/2/15
     * @Param []
     * @return java.util.List<cn.edu.entity.Student>
     **/
    @Override
    public List<Student> getStudentList() {
        return studentMapper.selectAll();
    }

    /**
     * @Author wys
     * @ClassName insert
     * @Description //TODO 新增
     * @Date 16:38 2020/2/15
     * @Param [student]
     * @return int
     **/
    @Override
    public int insert(Student student) {
        student.setStudentId(ApplicationUtils.GUID32());
        student.setDeleteStatus(Constant.isNotDelete);
//        student.setCreateUser();
        return studentMapper.insert(student);
    }

    /**
     * @Author wys
     * @ClassName deletet
     * @Description //TODO 假删除  将N置为Y
     * @Date 16:38 2020/2/15
     * @Param [id]
     * @return int
     **/
    @Override
    public int deletet(String id) {
        Student student = studentMapper.selectByPrimaryKey(id);
        student.setDeleteStatus(Constant.isDelete);
        student.setUpdateTime(new Date());
//        student.setUpdateUser();
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    /**
     * @Author wys
     * @ClassName update
     * @Description //TODO 更新
     * @Date 16:38 2020/2/15
     * @Param [student]
     * @return int
     **/
    @Override
    public int update(Student student) {
        student.setUpdateTime(new Date());
//        student.setUpdateUser();
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    /**
     * @Author wys
     * @ClassName search
     * @Description //TODO  搜索一个学生
     * @Date 16:39 2020/2/15
     * @Param [id]
     * @return cn.edu.entity.Student
     **/
    @Override
    public Student search(String id) {
        return studentMapper.selectByPrimaryKey(id);
    }
}
