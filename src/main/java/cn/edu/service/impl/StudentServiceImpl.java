package cn.edu.service.impl;

import cn.edu.dao.StudentMapper;
import cn.edu.dto.StudentDto;
import cn.edu.service.*;
import cn.edu.utils.*;
import cn.edu.vo.*;
import cn.edu.vo.Student;
import cn.edu.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private TeacherStudentService teacherStudentService;
    /**
     * @Author wys
     * @ClassName getStudentList
     * @Description //TODO 获取学生信息列表
     * @Date 16:37 2020/2/15
     * @Param []
     * @return java.util.List<cn.edu.vo.Student>
     **/
    @Override
    public List<Student> getStudentListWithConditionAndDeleteStatus(Student student,String deleteStatus) {
        List<String>studentList = null;
        if(deleteStatus.trim().compareTo(Constant.IS_NOT_DELETE)==0){

            studentList = studentMapper.getStudentListByName(student);

        }else if(deleteStatus.trim().compareTo(Constant.IS_DELETE)==0){
            studentList = studentMapper.getDelStudentListByName(student);
        }

        List<Student>list=new ArrayList<>();double start = System.currentTimeMillis();
        studentList.stream().forEach(s->{
            try {
                list.add(getOneStudentById(s));//信息装配
            } catch (Exception e) {
                e.printStackTrace();

            }
        });double end = System.currentTimeMillis();
        System.out.println(end -start);
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
    public Result insert(Student student) throws Exception {
        Result result = new Result();
        Assert.hasText(student.getStudentName(),"学生姓名不能为空");
        Assert.hasText(student.getStudentSex(),"学生性别不能为空");
        Assert.hasText(student.getStudentCode(),"学生学号不能为空");
        Assert.hasText(student.getClassId(),"学生班级id不能为空");
        Assert.hasText(student.getGroupId(),"学生小组id不能为空");
        student.setStudentId(ApplicationUtils.GUID32());
        student.setDeleteStatus(Constant.IS_NOT_DELETE);
        UserLogin userLogin = new UserLogin();
        userLogin.setUserId(student.getStudentId());
        userLogin.setUserCode(student.getStudentCode());
        userLogin.setUserName(student.getStudentName());
        userLogin.setUserType(Constant.IS_STUDENT);
        userLogin.setCreateUser(student.getCreateUser());
        //插入登录表
        String t = userLoginService.insert(userLogin);
        if(t.compareTo("插入成功")!=0){
            result.setMessage(t);
            result.setSuccess(false);
            return result;
        }
        int t1 = studentMapper.insertSelective(student);
        if(t1==0){
            result.setMessage("新增失败");
            result.setSuccess(false);
            return result;
        }
        if(student.getChangeTutorList()!=null &&student.getChangeTutorList().size()>0){
            changeTutorList(student);
        }
        result.setSuccess(true);
        result.setMessage("新增学生成功");
        return result;
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
    public int delete(String id) {
        Assert.hasText(id,"id不能为空");
        Student student = studentMapper.selectByPrimaryKey(id);
        student.setDeleteStatus(Constant.IS_DELETE);
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
        List<TeacherStudent>teacherStudentList = teacherStudentService.getTeacherStudentByStudentId(id);
        for (TeacherStudent ts:teacherStudentList) {
            teacherStudentService.deleteByStudentIdAndTeacherId(ts);
        }
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
    public int update(Student student) throws Exception {
        Assert.hasText(student.getStudentId(),"学生主键不能为空");
        Assert.hasText(student.getStudentCode(),"学生学号不能为空");
        Assert.hasText(student.getStudentName(),"学生姓名不能为空");
        Assert.hasText(student.getStudentSex(),"学生性别不能为空");
        student.setUpdateTime(new Date());
        //更新密码
        userLoginService.updatePasswordByUserCode(student.getStudentId(),student.getPassword());
        //更新学生指导老师-会出现置空情况，因此不做判断
        changeTutorList(student);
        //更新
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
    public Student getOneStudentById(String id) throws Exception {
        Assert.hasText(id, "id不能为空");
        //获取基本信息
        Student student = studentMapper.getOneStudentDetails(id);
        student.setPassword(DESPlus.Decrypt(student.getPassword()));
        //获取其指导老师列表
        List<Teacher>teacherList = teacherStudentService.getTeacherListByStudentId(id);
        student.setTutor(teacherList);
        String name = "";
        for (Teacher t:teacherList) {
            name+=t.getTeacherName();
            name+="、";
        }
        //指导老师名字字符串话
        student.setTutorName(name);
        return student;
    }

    /**
     * @Author wys
     * @ClassName changeTutorList
     * @Description //TODO  新增或更新teacherStudent表
     * @Date 10:28 2020/3/10
     * @Param [student]
     * @return int
     **/
    @Override
    public int changeTutorList(Student student) {
        Assert.hasText(student.getStudentId(),"学生id不能为空");
        List<TeacherStudent>teacherStudents = teacherStudentService.getTeacherStudentByStudentId(student.getStudentId());
        //增加,循环找出未插入
        for (String id:student.getChangeTutorList() ) {//前端获取的小组id列表
            boolean flag = true;
            for (TeacherStudent ts:teacherStudents) {//后台获取teacherStudentList校验
                if(student.getStudentId().compareTo(ts.getStudentId())==0 && id.compareTo(ts.getTeacherId())==0){//教师id与学生id均相等，则已插入
                    flag = false;
                    break;
                }
            }
            if(flag){
                TeacherStudent teacherStudent = new TeacherStudent();
                teacherStudent.setTeacherId(id);
                teacherStudent.setStudentId(student.getStudentId());
                teacherStudent.setCreateUser(student.getCreateUser());
                teacherStudentService.insert(teacherStudent);
            }
        }
        //删除
        for (TeacherStudent ts:teacherStudents) {//对已存在的列表循环
            if(student.getChangeTutorList()==null || student.getChangeTutorList().size()==0){
                teacherStudentService.deleteByStudentIdAndTeacherId(ts);
            }else{
                boolean flag = false;
                for (String id:student.getChangeTutorList()){//若在前端传过来的列表id中没有groupId，则删除
                    if(student.getStudentId().compareTo(ts.getStudentId())==0 && id.compareTo(ts.getTeacherId())==0){//教师id与小组id均相等，则已插入
                        flag = true;
                        break;
                    }
                }
                if(!flag){//在前端传过来的groupId列表中没有这一条，删除
                    teacherStudentService.deleteByStudentIdAndTeacherId(ts);
                }
            }
        }
        return 1;
    }

    /**
     * @Author wys
     * @ClassName Recover
     * @Description //TODO 恢复使用
     * @Date 17:52 2020/3/10
     * @Param [id]
     * @return int
     **/
    @Override
    public int Recover(String id) throws Exception {
        Assert.hasText(id,"学生主键id不能为空");
        Student student = studentMapper.selectByPrimaryKey(id);
        student.setDeleteStatus(Constant.IS_NOT_DELETE);
        return studentMapper.updateByPrimaryKeySelective(student);
    }



    @Override
    public ResponseEntity<byte[]> exportExcelModel() {
        //得到相应数据
        List<Faculty> faculties = facultyService.getAllList();//获取FMC信息
        List<Major> majors = majorService.getMajorList();
        String[] faculty = new String[faculties.size()];
        int i = 0;
        for(Faculty f:faculties){
            faculty[i++] = f.getFacultyName();
        }
        Map<String,String[]> schoolMap = new HashMap<>();
        String[] fatherNameArr = new String[majors.size()+faculties.size()];
        int count = 0;
        for(Faculty f:faculties){
            fatherNameArr[count++] = f.getFacultyName();
            schoolMap.put(f.getFacultyName(),majorService.getMajorsByFaculty(f));
        }

        for(Major m:majors){
            fatherNameArr[count++]=m.getMajorName();
            schoolMap.put(m.getMajorName(),classesService.getClassesNameByMajorId(m));
        }
        List<Groups> groups = groupsService.getGroupList();
        String[] group = new String[groups.size()];
        for(int k = 0;k < groups.size();k++){
            group[k] = groups.get(k).getGroupName();
        }
        ExcelUtils excelUtils = new ExcelUtils();
        return excelUtils.exportExcelStudentModel(faculty,fatherNameArr,schoolMap,group);
    }

    /**
     * @Author wys
     * @ClassName importExcel
     * @Description //TODO  学生信息导入
     * @Date 18:05 2020/4/30
     * @Param [file]
     * @return cn.edu.utils.Result
     **/
    @Override
    public Result importExcel(MultipartFile file) throws Exception {
        Result result = new Result();
        ExcelUtils excelUtils = new ExcelUtils();
        //excel 导入数据demo
        List<List<Object>> dataList;
        List<Student>list = new ArrayList<>();
        dataList = excelUtils.importExcel(file);
        //数据封装格式一，将表格中的数据遍历取出后封装进对象放进List
        for (int i = 0; i < dataList.size(); i++) {
            Student student = new Student();
            //学号校验
            if(dataList.get(i).get(0)!=null&&dataList.get(i).get(0)!=""){
                String studentCode = (String) dataList.get(i).get(0);
                if(!ValidateUtil.isNumeric(studentCode)){
                    return ExcelUtils.setErrorMessage(i+2,"学号",Constant.ROW_VALIDATE_ERROR);
                }
                if(!ValidateUtil.isCode(studentCode)){
                    return ExcelUtils.setErrorMessage(i+2,"学号",Constant.ROW_LENGTH_ERROR);
                }
                if(getStudentIdByStudentCode(studentCode) != null ){
                    return ExcelUtils.setErrorMessage(i+2,"学号",Constant.IS_EXIST);
                }
                student.setStudentCode(studentCode);
            }else{
                return ExcelUtils.setErrorMessage(i+2,"学号",Constant.ROW_IS_EMPTY);
            }
            //姓名校验
            if(dataList.get(i).get(1)!=null&&dataList.get(i).get(1)!=""){
                student.setStudentName((String) dataList.get(i).get(1));
            }else{
                return ExcelUtils.setErrorMessage(i+2,"姓名",Constant.ROW_IS_EMPTY);
            }
            //性别校验
            if(dataList.get(i).get(2)!=null&&dataList.get(i).get(2)!=""){
                student.setStudentSex((String) dataList.get(i).get(2));
            }else{
                return ExcelUtils.setErrorMessage(i+2,"性别",Constant.ROW_IS_EMPTY);
            }
            //班级
            if(dataList.get(i).get(5)!=null&&dataList.get(i).get(5)!=""){
                String classesId = classesService.getIdByName((String) dataList.get(i).get(5));
                if(StringUtils.isBlank(classesId)){
                    return ExcelUtils.setErrorMessage(i+2,"班级",Constant.IS_NOT_EXIST);
                }
                student.setClassId(classesId);
            }else{
                return ExcelUtils.setErrorMessage(i+2,"班级",Constant.ROW_IS_EMPTY);
            }
            //小组
            if(dataList.get(i).get(6)!=null&&dataList.get(i).get(6)!=""){
                String groupId = groupsService.getIdByname((String) dataList.get(i).get(6));
                if(StringUtils.isBlank(groupId)){
                    return ExcelUtils.setErrorMessage(i+2,"小组",Constant.IS_NOT_EXIST);
                }
                student.setGroupId(groupId);
            }else{
                return ExcelUtils.setErrorMessage(i+2,"小组",Constant.ROW_IS_EMPTY);
            }
            //电话号码
            if(dataList.get(i).get(7)!=null && dataList.get(i).get(7)!=""){
                String tel = (String) dataList.get(i).get(7);
                if(!ValidateUtil.isMobileNo(tel)){
                    return ExcelUtils.setErrorMessage(i+2,"电话号码",Constant.ROW_VALIDATE_ERROR);
                }
                student.setStudentTel(tel);
            }
            //QQ号
            if(dataList.get(i).get(8)!=null&&dataList.get(i).get(8)!=""){
                String qq = (String) dataList.get(i).get(8);
                if(!ValidateUtil.isNumeric(qq)){
                    return ExcelUtils.setErrorMessage(i+2,"QQ号",Constant.ROW_VALIDATE_ERROR);
                }
                student.setStudentQq(qq);
            }
            //邮箱
            if(dataList.get(i).get(9)!=null&&dataList.get(i).get(9)!=""){
                String email = (String) dataList.get(i).get(9);
                if(!ValidateUtil.isEmail(email)){
                    return ExcelUtils.setErrorMessage(i+2,"电子邮箱",Constant.ROW_VALIDATE_ERROR);
                }
                student.setStudentEmail(email);
            }
            list.add(student);
        }
        result.setSuccess(true);
        result.setMessage("导入成功");
        for(int i = 0 ; i < list.size(); i++){
            Student s = list.get(i);
            insert(s);
        }
        return result;
    }


    /**
     * @Author lichi
     * @ClassName getStudentIdByStudentCode
     * @Description //TODO 通过学号查找学生
     * @Date 17:52 2020/3/10
     * @Param [id]
     * @return int
     **/
    @Override
    public Student getStudentIdByStudentCode(Student student) {
        Assert.hasText(student.getStudentId(),"studentId 不能为空");
        Example example = new Example(Student.class);
        example.and().andEqualTo("studentCode",student.getStudentCode());
        return studentMapper.selectOneByExample(example);
    }

    @Override
    public Student getStudentIdByStudentCode(String studentCode) {
        Assert.hasText(studentCode,"studentCode 不能为空");
        Example example = new Example(Student.class);
        example.and().andEqualTo("studentCode",studentCode);
        return studentMapper.selectOneByExample(example);
    }

    @Override
    public List<StudentDto> getAllStudentDto() {
        List<StudentDto> studentDtos = studentMapper.getAllStudentDto();
        return studentDtos;
    }


}
