package cn.edu.contorller;

import cn.edu.service.ClassesService;
import cn.edu.utils.Result;
import cn.edu.vo.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ClassesController
 * @Description TODO 班级信息管理接口
 * @Author wys5
 * @Date 2020/2/28 16:43
 * @Version 1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/classes")
public class ClassesController {
    @Autowired
    private ClassesService classesService;

    /**
     * @Author wys
     * @ClassName getStudentClassByClassId
     * @Description //TODO  通过班级id获取班级信息
     * @Date 16:57 2020/2/28
     * @Param [classId]
     * @return cn.edu.utils.Result
     **/
    @GetMapping("/{classId}")
    public Result getStudentClassByClassId(@PathVariable String classId){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setObject(classesService.getOneClassesById(classId));
            result.setMessage("获取班级信息成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName insert
     * @Description //TODO  新增班级
     * @Date 9:58 2020/3/14
     * @Param [classes]
     * @return cn.edu.utils.Result
     **/
    @PostMapping("/")
    public Result insert(@RequestBody Classes classes){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setObject(classesService.insert(classes));
            result.setMessage("新增班级成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName update
     * @Description //TODO  更新班级信息
     * @Date 9:59 2020/3/14
     * @Param [classes]
     * @return cn.edu.utils.Result
     **/
    @PutMapping("/")
    public Result update(@RequestBody Classes classes){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setObject(classesService.update(classes));
            result.setMessage("更新班级成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName delete
     * @Description //TODO 删除班级
     * @Date 9:53 2020/3/14
     * @Param [classId]
     * @return cn.edu.utils.Result
     **/
    @DeleteMapping("/{classId}")
    public Result delete(@PathVariable String classId){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setObject(classesService.delete(classId));
            result.setMessage("删除班级成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
