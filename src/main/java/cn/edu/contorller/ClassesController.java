package cn.edu.contorller;

import cn.edu.service.ClassesService;
import cn.edu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ClassesController
 * @Description TODO 班级信息管理接口
 * @Author wys5
 * @Date 2020/2/28 16:43
 * @Version 1.0
 **/
@RestController
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
}
