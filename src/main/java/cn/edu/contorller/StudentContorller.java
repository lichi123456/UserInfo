package cn.edu.contorller;

import cn.edu.entity.Student;
import cn.edu.service.StudentService;
import cn.edu.utils.ApplicationUtils;
import cn.edu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName StudentContorller
 * @Description TODO 学生信息管理
 * @Author wys5
 * @Date 2020/2/15 13:34
 * @Version 1.0
 **/
@RestController
@RequestMapping("/student")
public class StudentContorller {
    @Autowired
    private StudentService studentService;

    /**
     * @Author wys
     * @ClassName getStudentList
     * @Description //TODO  获取学生信息列表
     * @Date 11:02 2020/2/22
     * @Param []
     * @return result
     **/
    @GetMapping("/list")
    public Result getStudentList(){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("获取学生信息列表成功");
            result.setObject(studentService.getStudentList());
        }catch(Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName insert
     * @Description //TODO  新增学生信息
     * @Date 11:03 2020/2/22
     * @Param [student]
     * @return result
     **/
    @PostMapping("/")
    public Result insert(@RequestBody Student student){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("学生信息插入成功");
            result.setObject(studentService.insert(student));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName delete
     * @Description //TODO  根据id删除学生信息（假删除）Y->N
     * @Date 11:03 2020/2/22
     * @Param [id]
     * @return result
     **/
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("学生信息放入禁用库中");
            result.setObject(studentService.deletet(id));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName realDelete
     * @Description //TODO 真删除
     * @Date 11:06 2020/2/22
     * @Param [id]
     * @return int
     **/
    @DeleteMapping("/realDel/{id}")
    public Result realDelete(@PathVariable String id){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("删除学生信息成功");
            result.setObject(studentService.realDel(id));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName update
     * @Description //TODO 更新
     * @Date 11:06 2020/2/22
     * @Param [student]
     * @return int
     **/
    @PutMapping("/")
    public Result update(@RequestBody Student student){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("更新学生信息成功");
            result.setObject(studentService.update(student));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * @Author wys
     * @ClassName getUUID
     * @Description //TODO  只是为了方便插入数据库写的方法
     * @Date 11:07 2020/2/22
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/UUID")
    public String getUUID(){
        String id = ApplicationUtils.GUID32();
        System.out.println(id);
        return id;
    }
}
