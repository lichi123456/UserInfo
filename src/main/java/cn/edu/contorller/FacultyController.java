package cn.edu.contorller;

import cn.edu.service.FacultyService;
import cn.edu.utils.Result;
import cn.edu.vo.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName FacultyController
 * @Description TODO 院系管理接口
 * @Author wys5
 * @Date 2020/2/28 16:44
 * @Version 1.0
 **/
@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    /**
     * @Author wys
     * @ClassName getStudentFacultyByFacultyId
     * @Description //TODO  通过院系id获取院系信息
     * @Date 16:56 2020/2/28
     * @Param [facultyId]
     * @return cn.edu.utils.Result
     **/
    @GetMapping("/{facultyId}")
    public Result getStudentFacultyByFacultyId(@PathVariable String facultyId){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("获取院系信息成功");
            result.setObject(facultyService.getFacultyById(facultyId));
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName getAllList
     * @Description //TODO  获取获取院系-专业-班级列表成功
     * @Date 23:17 2020/3/3
     * @Param []
     * @return cn.edu.utils.Result
     **/
    @GetMapping("/all/")
    public Result getAllList(){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("获取院系-专业-班级列表成功");
            result.setObject(facultyService.getAllList());
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * @Author wys
     * @ClassName insert
     * @Description //TODO  新增
     * @Date 17:05 2020/3/14
     * @Param [faculty]
     * @return cn.edu.utils.Result
     **/
    @PostMapping("/")
    public Result insert(@RequestBody Faculty faculty){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setObject(facultyService.insert(faculty));
            result.setMessage("新增院系成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * @Author wys
     * @ClassName update
     * @Description //TODO  更新院系
     * @Date 17:05 2020/3/14
     * @Param [faculty]
     * @return cn.edu.utils.Result
     **/
    @PutMapping("/")
    public Result update(@RequestBody Faculty faculty){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setObject(facultyService.update(faculty));
            result.setMessage("更新院系成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @DeleteMapping("/{facultyId}")
    public Result delete(@PathVariable String facultyId){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setObject(facultyService.delete(facultyId));
            result.setMessage("删除院系成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
