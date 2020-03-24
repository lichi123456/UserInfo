package cn.edu.contorller;

import cn.edu.service.TeacherGroupService;
import cn.edu.service.TeacherService;
import cn.edu.service.TeacherStudentService;
import cn.edu.utils.Constant;
import cn.edu.utils.ExcelUtils;
import cn.edu.utils.Result;
import cn.edu.vo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TeacherController
 * @Description TODO 教师基础信息管理类
 * @Author wys5
 * @Date 2020/3/2 15:17
 * @Version 1.0
 **/
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherStudentService teacherStudentService;

    @Autowired
    private TeacherGroupService teacherGroupService;

    /**
     * @Author wys
     * @ClassName getTeacherListWithCondition
     * @Description //TODO  获取教师列表-非禁用-带模糊查询
     * @Date 15:44 2020/3/2
     * @Param []
     * @return cn.edu.utils.Result
     **/
    @PostMapping("/usingList/")
    public Result getTeacherListWithCondition(@RequestBody Teacher teacher){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("获取教师非禁用列表成功");
            result.setObject(teacherService.getTeacherListWithConditionAndDeleteStatus(teacher, Constant.IS_NOT_DELETE));
        }catch(Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName getDelTeacherList
     * @Description //TODO  获取教师禁用列表-带模糊查询
     * @Date 11:29 2020/3/7
     * @Param [teacher]
     * @return cn.edu.utils.Result
     **/
    @PostMapping("/deleteList/")
    public Result getDelTeacherListWithCondition(@RequestBody Teacher teacher){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("获取教师禁用列表成功");
            result.setObject(teacherService.getTeacherListWithConditionAndDeleteStatus(teacher, Constant.IS_DELETE));
        }catch(Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName getTeacherList
     * @Description //TODO  只获取非禁用教师列表，不带查询
     * @Date 19:04 2020/3/9
     * @Param []
     * @return cn.edu.utils.Result
     **/
    @GetMapping("/list/")
    public Result getTeacherList(){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("获取教师列表成功");
            result.setObject(teacherService.getTeacherList());
        }catch(Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName insert
     * @Description //TODO  新增
     * @Date 15:44 2020/3/2
     * @Param [teacher]
     * @return cn.edu.utils.Result
     **/
    @PostMapping("/")
    public Result insert(@RequestBody Teacher teacher){
        Result result = new Result();
        try{
            result = teacherService.insert(teacher);
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName delete
     * @Description //TODO 假删除 N->Y
     * @Date 15:45 2020/3/2
     * @Param [id]
     * @return cn.edu.utils.Result
     **/
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("教师信息放入禁用库中");
            result.setObject(teacherService.delete(id));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName realDelete
     * @Description //TODO  真删除
     * @Date 15:45 2020/3/2
     * @Param [id]
     * @return cn.edu.utils.Result
     **/
    @DeleteMapping("/realDel/{id}")
    public Result realDelete(@PathVariable String id){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("删除教师信息成功");
            result.setObject(teacherService.realDel(id));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName update
     * @Description //TODO  更新
     * @Date 15:45 2020/3/2
     * @Param [teacher]
     * @return cn.edu.utils.Result
     **/
    @PutMapping("/")
    public Result update(@RequestBody Teacher teacher){
        Result result = new Result();
        try{
            result.setSuccess(true);
            result.setMessage("更新教师信息成功");
            result.setObject(teacherService.update(teacher));
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * @Author wys
     * @ClassName getOneTeacher
     * @Description //TODO  获取一个教师信息
     * @Date 15:45 2020/3/2
     * @Param [userId]
     * @return cn.edu.utils.Result
     **/
    @GetMapping("/{userId}")
    public Result getOneTeacher(@PathVariable String userId){
        Result result = new Result();
        try{
            result.setMessage("获取教师信息成功");
            result.setSuccess(true);
            result.setObject(teacherService.getOneTeacherById(userId));
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * @Author wys
     * @ClassName getTeacherGuide
     * @Description //TODO  获取被当前教师指导的所有学生信息
     * @Date 15:46 2020/3/2
     * @Param [userId]
     * @return cn.edu.utils.Result
     **/
    @GetMapping("/leader/{userId}")
    public Result getTeacherGuide(@PathVariable String userId){
        Result result = new Result();
        try{
            result.setMessage("获取老师指导的列表成功");
            result.setSuccess(true);
            result.setObject(teacherStudentService.getStudentListByTeacherId(userId));
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("/group/{teacherId}")
    public Result getTeacherGroupByTeacherId(@PathVariable String teacherId){
        Result result = new Result();
        try{
            result.setMessage("获取老师指导的小组列表成功");
            result.setSuccess(true);
            result.setObject(teacherGroupService.getGroupListByTeacherId(teacherId));
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
    @PostMapping("/importExcel")
    public Result importExcel(MultipartFile file){
        /**
         * @Author lichi
         * @ClassName importExcel
         * @Description //TODO  导入表格数据
         * @Date 11:30 2020/1/19
         * @Param [file]
         * @return cn.edu.utils.Result
         **/
        Result result = new Result();
        ExcelUtils excelUtils = new ExcelUtils();
        //excel 导入数据demo
        List<List<Object>> dataList;
        List<Teacher> list = new ArrayList<>();
        try {
            dataList = excelUtils.importExcel(file);
            //数据封装格式一，将表格中的数据遍历取出后封装进对象放进List
            for (int i = 0; i < dataList.size(); i++) {
                Teacher teacher = new Teacher();
                if(dataList.get(i).get(0)!=null&&(String) dataList.get(i).get(0)!=""){
                    teacher.setTeacherCode((String) dataList.get(i).get(0));
                }
                if(dataList.get(i).get(1)!=null&&(String) dataList.get(i).get(1)!=""){
                    teacher.setTeacherName((String) dataList.get(i).get(1));
                }
                if(dataList.get(i).get(2)!=null&&(String) dataList.get(i).get(2)!=""){
                    teacher.setTeacherSex((String) dataList.get(i).get(2));
                }
                if(dataList.get(i).get(3)!=null&&(String) dataList.get(i).get(3)!=""){
                    teacher.setTeacherTel((String) dataList.get(i).get(3));
                }
                if(dataList.get(i).get(4)!=null&&(String) dataList.get(i).get(4)!=""){
                    teacher.setTeacherQq((String) dataList.get(i).get(4));
                }

                if(dataList.get(i).get(5)!=null&&(String) dataList.get(i).get(5)!=""){
                    teacher.setTeacherEmail((String) dataList.get(i).get(5));
                }
                list.add(teacher);
            }
            result.setSuccess(true);
            result.setMessage("导入成功");
            for(Teacher s:list){
                if(s.getTeacherCode()!=""){
                    teacherService.insert(s);
                }
            }
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * 道出数据
     * lichi
     * @return
     * @throws IOException
     */
    @GetMapping("/exportExcel")
    public ResponseEntity<byte[]> exportExcel() throws IOException {
        ExcelUtils excelUtils = new ExcelUtils();
        return excelUtils.exportExcelTeacher(teacherService.getTeacherList());
    }

    /**
     * 道出模板
     * @return
     * @throws IOException
     */
    @GetMapping("/exportExcelModel")
    public ResponseEntity<byte[]> exportExcelModel() throws IOException {
        ExcelUtils excelUtils = new ExcelUtils();
        return excelUtils.exportExcelTeacherModel();
    }
}
