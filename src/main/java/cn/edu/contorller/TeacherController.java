package cn.edu.contorller;

import cn.edu.service.TeacherGroupService;
import cn.edu.service.TeacherService;
import cn.edu.service.TeacherStudentService;
import cn.edu.utils.Constant;
import cn.edu.utils.Result;
import cn.edu.vo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TeacherController
 * @Description TODO 教师基础信息管理类
 * @Author wys5
 * @Date 2020/3/2 15:17
 * @Version 1.0
 **/
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherStudentService teacherStudentService;

    @Autowired
    private TeacherGroupService teacherGroupService;

    /**
     * @Author wys
     * @ClassName getTeacherList
     * @Description //TODO  获取教师列表-非禁用
     * @Date 15:44 2020/3/2
     * @Param []
     * @return cn.edu.utils.Result
     **/
    @PostMapping("/list/")
    public Result getTeacherList(@RequestBody Teacher teacher){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("获取教师非禁用列表成功");
            result.setObject(teacherService.getTeacherList(teacher, Constant.isNotDelete));
        }catch(Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName getDelTeacherList
     * @Description //TODO  获取教师禁用列表
     * @Date 11:29 2020/3/7
     * @Param [teacher]
     * @return cn.edu.utils.Result
     **/
    @PostMapping("/deleteList/")
    public Result getDelTeacherList(@RequestBody Teacher teacher){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("获取教师禁用列表成功");
            result.setObject(teacherService.getTeacherList(teacher, Constant.isDelete));
        }catch(Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName insert
     * @Description //TODO  新增
     * @Date 15:44 2020/3/2
     * @Param [teacher]
     * @return cn.edu.utils.Result
     **/
    @PostMapping("/")
    public Result insert(@RequestBody Teacher teacher){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("教师信息新增成功");
            result.setObject(teacherService.insert(teacher));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName delete
     * @Description //TODO 假删除 N->Y
     * @Date 15:45 2020/3/2
     * @Param [id]
     * @return cn.edu.utils.Result
     **/
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("教师信息放入禁用库中");
            result.setObject(teacherService.delete(id));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName realDelete
     * @Description //TODO  真删除
     * @Date 15:45 2020/3/2
     * @Param [id]
     * @return cn.edu.utils.Result
     **/
    @DeleteMapping("/realDel/{id}")
    public Result realDelete(@PathVariable String id){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("删除教师信息成功");
            result.setObject(teacherService.realDel(id));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName update
     * @Description //TODO  更新
     * @Date 15:45 2020/3/2
     * @Param [teacher]
     * @return cn.edu.utils.Result
     **/
    @PutMapping("/")
    public Result update(@RequestBody Teacher teacher){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("更新教师信息成功");
            result.setObject(teacherService.update(teacher));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName getOneTeacher
     * @Description //TODO  获取一个教师信息
     * @Date 15:45 2020/3/2
     * @Param [userId]
     * @return cn.edu.utils.Result
     **/
    @GetMapping("/{userId}")
    public Result getOneTeacher(@PathVariable String userId){
        Result result = new Result();
        try{
            result.setMessage("获取教师信息成功");
            result.setSuccess(true);
            result.setObject(teacherService.getOneTeacherById(userId));
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * @Author wys
     * @ClassName getTeacherGuide
     * @Description //TODO  获取被当前教师指导的所有学生信息
     * @Date 15:46 2020/3/2
     * @Param [userId]
     * @return cn.edu.utils.Result
     **/
    @GetMapping("/leader/{userId}")
    public Result getTeacherGuide(@PathVariable String userId){
        Result result = new Result();
        try{
            result.setMessage("获取老师指导的列表成功");
            result.setSuccess(true);
            result.setObject(teacherStudentService.getStudentListByTeacherId(userId));
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("/group/{teacherId}")
    public Result getTeacherGroupByTeacherId(@PathVariable String teacherId){
        Result result = new Result();
        try{
            result.setMessage("获取老师指导的小组列表成功");
            result.setSuccess(true);
            result.setObject(teacherGroupService.getGroupListByTeacherId(teacherId));
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
