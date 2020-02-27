package cn.edu.contorller;

import cn.edu.service.GroupsService;
import cn.edu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GroupsController
 * @Description TODO 组别管理接口
 * @Author wys5
 * @Date 2020/2/28 16:46
 * @Version 1.0
 **/
@RestController
@RequestMapping("/groups")
public class GroupsController {
    @Autowired
    private GroupsService groupsService;

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
}
