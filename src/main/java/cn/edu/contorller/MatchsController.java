package cn.edu.contorller;
/**
 * @ClassName: MatchsService
 * @Author: lichi
 * @Date: 2020/4/11 9:22
 * @Description:
 * @Version: 1.0
 */

import cn.edu.service.MatchService;
import cn.edu.utils.Result;
import cn.edu.vo.Matchs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 *
 *@description:
 *@author: Andy
 *@time: 2020/4/11 9:22
 *
 */
@RestController
@RequestMapping("/match")
public class MatchsController {
    @Autowired
    private MatchService matchService;

    @GetMapping("/")
    public Result getAll(){
        Result result = new Result();
        List<Matchs> matchs = matchService.getAll();
        if(matchs!=null){
            result.setSuccess(true);
            result.setMessage("获取赛事数据成功");
            result.setObject(matchs);
        }else{
            result.setSuccess(false);
            result.setMessage("获取赛事数据失败");
        }
        return result;
    }

}
