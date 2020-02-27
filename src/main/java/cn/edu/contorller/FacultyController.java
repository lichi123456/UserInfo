package cn.edu.contorller;

import cn.edu.service.FacultyService;
import cn.edu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            result.setObject(facultyService.getFacultyBuId(facultyId));
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
