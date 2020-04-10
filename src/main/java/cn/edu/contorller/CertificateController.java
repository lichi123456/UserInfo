package cn.edu.contorller;

import cn.edu.Application;
import cn.edu.dto.CertificateDto;
import cn.edu.service.*;
import cn.edu.service.impl.FileServiceImpl;
import cn.edu.utils.*;
import cn.edu.vo.Certificate;
import cn.edu.vo.Student;
import cn.edu.vo.Teacher;
import cn.edu.vo.UserCertificate;
import jdk.nashorn.internal.codegen.TypeMap;
import jdk.nashorn.internal.ir.RuntimeNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.impl.jam.JMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static cn.edu.utils.Constant.CERTIFICATE_TYPE;

/**
 * @ClassName CertificateController
 * @Description TODO 证书管理接口
 * @Author wys5
 * @Date 2020/2/28 16:44
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/certificate")
public class CertificateController {
    //导入学生服务
    @Autowired
    private StudentService studentService;
    //导入证书管理服务
    @Autowired
    private CertificateService certificateService;
    //导入老师服务
    @Autowired
    private TeacherService teacherService;
    //用户证书关联服务
    @Autowired
    private UserCertificateService userCertificateService;
    @Autowired
    private TeacherStudentService teacherStudentService;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(CertificateController.class);
    @Autowired
    private FileServiceImpl fileService;
    @Value("${webappfile.uploadPath}")
    private String uploadPath;
    @Value("${server.port}")
    private String port;
    /**
     *
     *
     * @description: Excel导入数据库
     * @param file
     * @return: cn.edu.utils.Result
     * @author: 李翅
     * @time: 2020/3/20 11:05
     */
    @PostMapping("/importExcel")
    public Result importExcel(MultipartFile file){
        Result result = new Result();
        ExcelUtils excelUtils = new ExcelUtils();
        List<List<Object>> dataList;
        List<CertificateDto> list = new ArrayList<>();
        try{
            dataList = excelUtils.importExcel(file);
            for(int i = 0;i <dataList.size();i++){
                CertificateDto certificateDto = new CertificateDto();
                Student student = new Student();
                Teacher teacher1 = new Teacher();
                Teacher teacher2 = new Teacher();
                Teacher teacher3 = new Teacher();
                Certificate certificate = new Certificate();
                if(dataList.get(i).get(0)!= null && "".compareTo((String) dataList.get(i).get(0))!=0){
                    student.setStudentCode((String) dataList.get(i).get(0));
                }
                if(dataList.get(i).get(1)!= null && "".compareTo((String) dataList.get(i).get(1))!=0){
                    student.setStudentName((String) dataList.get(i).get(1));
                }
                if(dataList.get(i).get(2)!= null && "".compareTo((String) dataList.get(i).get(2))!=0){
                    certificate.setCertificateName((String) dataList.get(i).get(2));
                }
                if(dataList.get(i).get(3)!= null && "".compareTo((String) dataList.get(i).get(3))!=0){
                    teacher1.setTeacherCode((String) dataList.get(i).get(3));
                }
                if(dataList.get(i).get(4)!= null && "".compareTo((String) dataList.get(i).get(4))!=0){
                    teacher2.setTeacherCode((String) dataList.get(i).get(4));
                }
                if(dataList.get(i).get(5)!= null && "".compareTo((String) dataList.get(i).get(5))!=0){
                    teacher3.setTeacherCode((String) dataList.get(i).get(5));
                }
                if(dataList.get(i).get(6)!= null && "".compareTo((String) dataList.get(i).get(6))!=0){
                    certificate.setCertificateName((String) dataList.get(i).get(6));
                }
                if(dataList.get(i).get(7)!= null && "".compareTo((String) dataList.get(i).get(7))!=0){
                    certificate.setCertificateName((String) dataList.get(i).get(7));
                }
                certificateDto.setStudent(student);
                certificateDto.setCertificate(certificate);
                certificateDto.setTeacher1(teacher1);
                certificateDto.setTeacher2(teacher2);
                certificateDto.setTeacher3(teacher3);
            }
            result.setSuccess(true);
            result.setMessage("导入成功");
            for(CertificateDto c:list){
                Student student = new Student();
                Teacher teacher1 = null;
                Teacher teacher2 = null;
                Teacher teacher3 = null;
                Certificate certificate = null;
                student = c.getStudent();
                student = studentService.getStudentIdByStudentCode(student);
                teacher1 = c.getTeacher1();
                teacher1 = teacherService.getTeacherByTeacherCode(teacher1);
                teacher2 =c.getTeacher2();
                teacher2 = teacherService.getTeacherByTeacherCode(teacher2);
                teacher3 = c.getTeacher3();
                teacher3 = teacherService.getTeacherByTeacherCode(teacher3);
                certificate = c.getCertificate();
                certificate =certificateService.getCertificateByCertificateName(certificate);
                if(student != null && certificate != null){
                    boolean isEntry = userCertificateService.getUserCertificate(student.getStudentId(),certificate.getCertificateId());
                    if(isEntry){
                        int t = userCertificateService.insert(student,certificate);
                    }
                    if(teacher1 != null&&teacher1.getTeacherId()!=null&&teacher1.getTeacherId().compareTo("")!=0){
                        //判断用户是否存在，若存在则导入数据库
                        boolean isEntryTeacher = userCertificateService.getUserCertificate(teacher1.getTeacherId(),certificate.getCertificateId());
                        if(isEntryTeacher){
                            userCertificateService.insert(teacher1,certificate);
                        }
                        boolean isExist =  teacherStudentService.isExistTeacherStudent(teacher1,student);
                        if(isExist){
                            teacherStudentService.insert(teacher1,student);
                        }

                    }
                    if(teacher2 != null&&teacher2.getTeacherId()!=null&&teacher2.getTeacherId().compareTo("")!=0){
                        boolean isEntryTeacher = userCertificateService.getUserCertificate(teacher2.getTeacherId(),certificate.getCertificateId());
                        if(isEntryTeacher){
                            userCertificateService.insert(teacher2,certificate);
                        }
                        boolean isExist =  teacherStudentService.isExistTeacherStudent(teacher2,student);
                        if(isExist){
                            teacherStudentService.insert(teacher2,student);
                        }
                    }
                    if(teacher3 != null&&teacher3.getTeacherId()!=null&&teacher3.getTeacherId().compareTo("")!=0){
                        boolean isEntryTeacher = userCertificateService.getUserCertificate(teacher3.getTeacherId(),certificate.getCertificateId());
                        if(isEntryTeacher){
                            userCertificateService.insert(teacher3,certificate);
                        }
                        boolean isExist =  teacherStudentService.isExistTeacherStudent(teacher3,student);
                        if(isExist){
                            teacherStudentService.insert(teacher3,student);
                        }
                    }
                }

            }
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * @Author lichi
     * @ClassName exportExcel
     * @Description //TODO  导出表格数据
     * @Date 11:30 2020/1/19
     * @Param []
     * @return org.springframework.http.ResponseEntity<byte[]>
     **/
    @GetMapping("/exportExcel")
    public ResponseEntity<byte[]> exportExcel() throws IOException {

        //获取数据
        Student student = new Student();
        ExcelUtils excelUtils = new ExcelUtils();
        return excelUtils.exportExcel(certificateService.getCertificateDto(null,null,null), Constant.CERTIFICATE_EXCEL_HEADER,CERTIFICATE_TYPE,"证书列表");
    }

    @PostMapping("/addImage")
    public Result addImage(@RequestParam(name = "file", required = false) MultipartFile file,HttpServletRequest request, HttpServletResponse response) {
        //项目存放路径
        String str=uploadPath;
        String webPath = "account/img";
        logger.info("webPath = "+webPath);
        //得到上传时的文件名字
        String originalname=file.getOriginalFilename();
        //substring(originalname.lastIndexOf(".")截取图片名后缀
        String newName= originalname.substring(originalname.lastIndexOf("."));
        //利用UUidUtil工具类生成新的文件名字
        newName =ApplicationUtils.GUID32()+newName;
        String webFilePath = PathUtil.appendWebPath(webPath,newName);
        logger.info("newname ="+newName);
        logger.info("webFilePath ="+webFilePath);
        Result result = new Result();
        String filePath = PathUtil.appendWebPath(uploadPath, webFilePath);
        Map<String, String> result2 = fileService.uploadReal(filePath, file);
        result2.put("webUrl", webFilePath);
        result.setSuccess(true);
        String path = webPath+"/"+newName;
        result.setObject(path);
        return result;
    }

    /**
     *
     * @param studentId
     * @return
     */
    @PostMapping("/addCertificate/")
    public Result insertCertificate(@RequestBody Certificate certificate){
        Result result = new Result();

        String id =  ApplicationUtils.GUID32();
        if(certificate!=null){
            certificate.setCertificateId(id);
            certificate.setCertificateDate(new Date());
//            logger.info(certificate.getCertificateName());
//            logger.info(certificate.getMatchId());
            logger.info("CertificateId"+certificate.getCertificateId());
            result = certificateService.insert(certificate);
            result.setSuccess(true);
            result.setObject(certificate.getCertificateId());

        }else{
            result.setSuccess(false);
            result.setMessage("传入证书为空");
//            logger.info("传入证书为空");
        }

        return result;
    }
    @PostMapping("/addUserCertificate/")
    public Result insertUserCertificate(@RequestBody UserCertificate userCertificate){
        Result result = new Result();
        int i = userCertificateService.insert(userCertificate);
        if(i!=0){
            result.setMessage("导入成功");
            result.setSuccess(true);

        }else{
            result.setSuccess(false);
            result.setMessage("导入失败");
        }

        return result;
    }


}
