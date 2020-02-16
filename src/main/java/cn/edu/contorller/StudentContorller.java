package cn.edu.contorller;

import cn.edu.entity.Student;
import cn.edu.service.StudentService;
import cn.edu.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName StudentContorller
 * @Description TODO
 * @Author wys5
 * @Date 2020/2/15 13:34
 * @Version 1.0
 **/
@Controller
@RequestMapping("/student")
public class StudentContorller {
    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public List<Student>getStudentList(){
        return studentService.getStudentList();
    }
    @PostMapping("/")
    public int insert(@RequestBody Student student){
        return studentService.insert(student);
    }
    @DeleteMapping("/{id}")
    public int delete(@PathVariable String id){
        return studentService.deletet(id);
    }
    @PutMapping("/")
    public int update(@RequestBody Student student){
        return studentService.update(student);
    }

    @GetMapping("/UUID")
    public String getUUID(){
        String id = ApplicationUtils.GUID32();
        System.out.println(id);
        return id;
    }
}
