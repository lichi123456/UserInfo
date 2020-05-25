package cn.edu.contorller;

import cn.edu.service.MajorService;
import cn.edu.utils.Result;
import cn.edu.vo.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName MajorController
 * @Description TODO 专业管理接口
 * @Author wys5
 * @Date 2020/2/28 16:46
 * @Version 1.0
 **/
@RestController
@CrossOrigin
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

    /**
     * @Author wys
     * @ClassName insert
     * @Description //TODO  新增专业
     * @Date 17:03 2020/3/14
     * @Param [major]
     * @return cn.edu.utils.Result
     **/
    @PostMapping("/")
    public Result insert(@RequestBody Major major){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setObject(majorService.insert(major));
            result.setMessage("新增专业成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * @Author wys
     * @ClassName update
     * @Description //TODO  更新
     * @Date 17:03 2020/3/14
     * @Param [major]
     * @return cn.edu.utils.Result
     **/
    @PutMapping("/")
    public Result update(@RequestBody Major major){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setObject(majorService.update(major));
            result.setMessage("更新专业成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @DeleteMapping("/{majorId}")
    public Result delete(@PathVariable String majorId){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setObject(majorService.delete(majorId));
            result.setMessage("删除专业成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
