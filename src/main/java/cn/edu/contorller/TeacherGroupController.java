package cn.edu.contorller;

import cn.edu.service.TeacherGroupService;
import cn.edu.utils.Result;
import cn.edu.vo.TeacherGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName TeacherGroupController
 * @Description TODO 教师组别管理类
 * @Author wys5
 * @Date 2020/3/8 11:33
 * @Version 1.0
 **/
@RestController
@RequestMapping("/teacherGroup")
public class TeacherGroupController {
    @Autowired
    private TeacherGroupService teacherGroupService;

}
