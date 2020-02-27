package cn.edu.contorller;

import cn.edu.service.MajorService;
import cn.edu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MajorController
 * @Description TODO 专业管理接口
 * @Author wys5
 * @Date 2020/2/28 16:46
 * @Version 1.0
 **/
@RestController
@RequestMapping("/major")
public class MajorController {
    @Autowired
    private MajorService majorService;

    /**
     * @Author wys
     * @ClassName getStudentMajorByMajorId
     * @Description //TODO  通过专业id获取专业信息
     * @Date 16:57 2020/2/28
     * @Param [majorId]
     * @return cn.edu.utils.Result
     **/
    @GetMapping("/{majorId}")
    public Result getStudentMajorByMajorId(@PathVariable String majorId){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setObject(majorService.getOneMajorById(majorId));
            result.setMessage("获取专业信息成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
