package cn.edu.contorller;

import cn.edu.service.GroupsService;
import cn.edu.utils.Result;
import cn.edu.vo.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName GroupsController
 * @Description TODO 组别管理接口
 * @Author wys5
 * @Date 2020/2/28 16:46
 * @Version 1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/groups")
public class GroupsController {
    @Autowired
    private GroupsService groupsService;

    /**
     * @Author wys
     * @ClassName getStudentGroupByGroupId
     * @Description //TODO  通过小组id获取小组信息
     * @Date 10:20 2020/3/6
     * @Param [groupId]
     * @return cn.edu.utils.Result
     **/
    @GetMapping("/{groupId}")
    public Result getStudentGroupByGroupId(@PathVariable String groupId){
        Result result = new Result();
        try{
            result.setMessage("获取小组信息成功");
            result.setSuccess(true);
            result.setObject(groupsService.getOneGroupById(groupId));
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName getGroupsList
     * @Description //TODO  获取小组列表
     * @Date 10:21 2020/3/6
     * @Param []
     * @return cn.edu.utils.Result
     **/
    @GetMapping("/list/")
    public Result getGroupsList(){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("获取小组列表成功");
            result.setObject(groupsService.getGroupList());
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
     * @Date 10:21 2020/3/15
     * @Param [groups]
     * @return cn.edu.utils.Result
     **/
    @PostMapping("/")
    public Result insert(@RequestBody Groups groups){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("新增小组信息成功");
            result.setObject(groupsService.insert(groups));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName delete
     * @Description //TODO  删除
     * @Date 10:22 2020/3/15
     * @Param [groupId]
     * @return cn.edu.utils.Result
     **/
    @DeleteMapping("/{groupId}")
    public Result delete(@PathVariable String groupId){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("删除小组信息成功");
            result.setObject(groupsService.delete(groupId));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName update
     * @Description //TODO 修改
     * @Date 10:22 2020/3/15
     * @Param [groups]
     * @return cn.edu.utils.Result
     **/
    @PutMapping("/")
    public Result update(@RequestBody Groups groups){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("修改小组信息成功");
            result.setObject(groupsService.update(groups));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
}
