package cn.edu.contorller;
/**
 * @ClassName: OrganizationController
 * @Author: lichi
 * @Date: 2020/3/19 15:00
 * @Description:
 * @Version: 1.0
 */

import cn.edu.service.OrganizationService;
import cn.edu.utils.Result;
import cn.edu.vo.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CertificateController
 * @Description 发证机关管理接口
 * @Author lichi
 * @Date 2020/2/28 16:44
 * @Version 1.0
 **/
@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("删除成功");
        int i = organizationService.delete(id);
        if(i == 0){
            result.setSuccess(false);
            result.setMessage("删除失败");
        }
        return result;
    }
    @PostMapping("/")
    public Result insert(@RequestBody Organization organization){
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("新增成功");
        int i = organizationService.insert(organization);
        if(i == 0){
            result.setSuccess(false);
            result.setMessage("新增失败");
        }
        return result;
    }
    @PutMapping("/")
    public Result Update(@RequestBody Organization organization){
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("修改成功");
        int i = organizationService.update(organization);
        if(i == 0){
            result.setSuccess(false);
            result.setMessage("修改失败");
        }
        return result;
    }
}
