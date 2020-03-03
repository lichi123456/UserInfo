package cn.edu.service.impl;

import cn.edu.dao.StudentMapper;
import cn.edu.service.*;
import cn.edu.vo.*;
import cn.edu.vo.Student;
import cn.edu.service.StudentService;
import cn.edu.utils.Constant;
import cn.edu.utils.ApplicationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
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

    @Autowired
    private ClassesService classesService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private GroupsService groupsService;
    /**
     * @Author wys
     * @ClassName getStudentList
     * @Description //TODO 获取学生信息列表
     * @Date 16:37 2020/2/15
     * @Param []
     * @return java.util.List<cn.edu.vo.Student>
     **/
    @Override
    public List<Student> getStudentList() {
        List<Student>studentList = studentMapper.selectAll();
        List<Student>list=new ArrayList<>();
        studentList.stream().forEach(s->{
                list.add(getOneStudentById(s.getStudentId()));
        });
        return list;
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
        return studentMapper.insertSelective(student);
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
        Assert.hasText(id,"id不能为空");
        Student student = studentMapper.selectByPrimaryKey(id);
        student.setDeleteStatus(Constant.isDelete);
        student.setUpdateTime(new Date());
//        student.setUpdateUser();
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    /**
     * @Author wys
     * @ClassName realDel
     * @Description //TODO  真删除
     * @Date 11:06 2020/2/22
     * @Param [id]
     * @return int
     **/
    @Override
    public int realDel(String id) {
        Assert.hasText(id,"id不能为空");
        return studentMapper.deleteByPrimaryKey(id);
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
     * @ClassName getOneStudentById
     * @Description //TODO  获取一个学生的所有信息
     * @Date 16:39 2020/2/15
     * @Param [id]
     * @return cn.edu.vo.Student
     **/
    @Override
    public Student getOneStudentById(String id) {
        Assert.hasText(id, "id不能为空");
        Student student = studentMapper.selectByPrimaryKey(id);
        Faculty faculty = new Faculty();
        Major major = new Major();
        Classes classes = new Classes();
        Groups groups = new Groups();
        if (StringUtils.isNotEmpty(student.getClassId()) && StringUtils.isNoneBlank(student.getClassId())) {
            classes = classesService.getOneClassesById(student.getClassId());
            student.setClassName(classes.getClassName());
            if (StringUtils.isNotEmpty(classes.getMajorId()) && StringUtils.isNoneBlank(classes.getMajorId())) {
                major = majorService.getOneMajorById(classes.getMajorId());
                student.setMajorId(major.getMajorId());
                student.setMajorName(major.getMajorName());
                if (StringUtils.isNotEmpty(major.getFacultyId()) && StringUtils.isNoneBlank(major.getFacultyId())) {
                    faculty = facultyService.getFacultyById(major.getFacultyId());
                    student.setFacultyId(faculty.getFacultyId());
                    student.setFacultyName(faculty.getFacultyName());
                }
            }
        }
        if (StringUtils.isNotEmpty(student.getGroupId()) && StringUtils.isNoneBlank(student.getGroupId())) {
            groups = groupsService.getOneGroupById(student.getGroupId());
            student.setGroupName(groups.getGroupName());
        }

        return student;
    }

}
