package cn.edu.contorller;

import cn.edu.dto.CertificateDto;
import cn.edu.service.*;
import cn.edu.service.impl.FileServiceImpl;
import cn.edu.utils.*;
import cn.edu.vo.Certificate;
import cn.edu.vo.Student;
import cn.edu.vo.Teacher;
import cn.edu.vo.UserCertificate;
import lombok.extern.slf4j.Slf4j;
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
    private OrganizationCertificateService organizationCertificateService;
    @Autowired
    private MatchService matchService;
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
//    @PostMapping("/importExcel")
//    public Result importExcel(MultipartFile file){
//        Result result = new Result();
//        ExcelUtils excelUtils = new ExcelUtils();
//        List<List<Object>> dataList;
//        List<CertificateDto> list = new ArrayList<>();
//        try{
//            dataList = excelUtils.importExcel(file);
//            for(int i = 0;i <dataList.size();i++){
//                CertificateDto certificateDto = new CertificateDto();
//                Student student = new Student();
//                Teacher teacher1 = new Teacher();
//                Teacher teacher2 = new Teacher();
//                Teacher teacher3 = new Teacher();
//                Certificate certificate = new Certificate();
//                if(dataList.get(i).get(0)!= null && "".compareTo((String) dataList.get(i).get(0))!=0){
//                    student.setStudentCode((String) dataList.get(i).get(0));
//                }
//                if(dataList.get(i).get(1)!= null && "".compareTo((String) dataList.get(i).get(1))!=0){
//                    student.setStudentName((String) dataList.get(i).get(1));
//                }
//                if(dataList.get(i).get(2)!= null && "".compareTo((String) dataList.get(i).get(2))!=0){
//                    certificate.setCertificateName((String) dataList.get(i).get(2));
//                }
//                if(dataList.get(i).get(3)!= null && "".compareTo((String) dataList.get(i).get(3))!=0){
//                    teacher1.setTeacherCode((String) dataList.get(i).get(3));
//                }
//                if(dataList.get(i).get(4)!= null && "".compareTo((String) dataList.get(i).get(4))!=0){
//                    teacher2.setTeacherCode((String) dataList.get(i).get(4));
//                }
//                if(dataList.get(i).get(5)!= null && "".compareTo((String) dataList.get(i).get(5))!=0){
//                    teacher3.setTeacherCode((String) dataList.get(i).get(5));
//                }
//                if(dataList.get(i).get(6)!= null && "".compareTo((String) dataList.get(i).get(6))!=0){
//                    certificate.setCertificateName((String) dataList.get(i).get(6));
//                }
//                if(dataList.get(i).get(7)!= null && "".compareTo((String) dataList.get(i).get(7))!=0){
//                    certificate.setCertificateName((String) dataList.get(i).get(7));
//                }
//                certificateDto.setStudent(student);
//                certificateDto.setCertificate(certificate);
//                certificateDto.setTeacher1(teacher1);
//                certificateDto.setTeacher2(teacher2);
//                certificateDto.setTeacher3(teacher3);
//            }
//            result.setSuccess(true);
//            result.setMessage("导入成功");
//            for(CertificateDto c:list){
//                Student student = new Student();
//                Teacher teacher1 = null;
//                Teacher teacher2 = null;
//                Teacher teacher3 = null;
//                Certificate certificate = null;
//                student = c.getStudent();
//                student = studentService.getStudentIdByStudentCode(student);
//                teacher1 = c.getTeacher1();
//                teacher1 = teacherService.getTeacherByTeacherCode(teacher1);
//                teacher2 =c.getTeacher2();
//                teacher2 = teacherService.getTeacherByTeacherCode(teacher2);
//                teacher3 = c.getTeacher3();
//                teacher3 = teacherService.getTeacherByTeacherCode(teacher3);
//                certificate = c.getCertificate();
//                certificate =certificateService.getCertificateByCertificateName(certificate);
//                if(student != null && certificate != null){
//                    boolean isEntry = userCertificateService.getUserCertificate(student.getStudentId(),certificate.getCertificateId());
//                    if(isEntry){
//                        int t = userCertificateService.insert(student,certificate);
//                    }
//                    if(teacher1 != null&&teacher1.getTeacherId()!=null&&teacher1.getTeacherId().compareTo("")!=0){
//                        //判断用户是否存在，若存在则导入数据库
//                        boolean isEntryTeacher = userCertificateService.getUserCertificate(teacher1.getTeacherId(),certificate.getCertificateId());
//                        if(isEntryTeacher){
//                            userCertificateService.insert(teacher1,certificate);
//                        }
//                        boolean isExist =  teacherStudentService.isExistTeacherStudent(teacher1,student);
//                        if(isExist){
//                            teacherStudentService.insert(teacher1,student);
//                        }
//
//                    }
//                    if(teacher2 != null&&teacher2.getTeacherId()!=null&&teacher2.getTeacherId().compareTo("")!=0){
//                        boolean isEntryTeacher = userCertificateService.getUserCertificate(teacher2.getTeacherId(),certificate.getCertificateId());
//                        if(isEntryTeacher){
//                            userCertificateService.insert(teacher2,certificate);
//                        }
//                        boolean isExist =  teacherStudentService.isExistTeacherStudent(teacher2,student);
//                        if(isExist){
//                            teacherStudentService.insert(teacher2,student);
//                        }
//                    }
//                    if(teacher3 != null&&teacher3.getTeacherId()!=null&&teacher3.getTeacherId().compareTo("")!=0){
//                        boolean isEntryTeacher = userCertificateService.getUserCertificate(teacher3.getTeacherId(),certificate.getCertificateId());
//                        if(isEntryTeacher){
//                            userCertificateService.insert(teacher3,certificate);
//                        }
//                        boolean isExist =  teacherStudentService.isExistTeacherStudent(teacher3,student);
//                        if(isExist){
//                            teacherStudentService.insert(teacher3,student);
//                        }
//                    }
//                }
//
//            }
//        }catch (Exception e){
//            result.setMessage(e.getMessage());
//            result.setSuccess(false);
//        }
//        return result;
//    }

    /**
     * @Author lichi
     * @ClassName exportExcel
     * @Description //TODO  导出表格数据
     * @Date 11:30 2020/1/19
     * @Param []
     * @return org.springframework.http.ResponseEntity<byte[]>
     **/
//    @GetMapping("/exportExcel")
//    public ResponseEntity<byte[]> exportExcel() throws IOException {
//
//        //获取数据
//        Student student = new Student();
//        ExcelUtils excelUtils = new ExcelUtils();
//        return excelUtils.exportExcel(certificateService.getCertificateDto(null,null,null), Constant.CERTIFICATE_EXCEL_HEADER,CERTIFICATE_TYPE,"证书列表");
//    }

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
     * @param
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

    @GetMapping("/getCertificate/student/{id}")
    public Result getStudentCertificate(@PathVariable String id){
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("得到数据成功");
        List<CertificateDto> certificateDtos = certificateService.getCertificateDto(id,null,null);
        if(certificateDtos==null){
            result.setSuccess(false);
            result.setMessage("得到数据失败");
        }else{
            result.setObject(certificateDtos);
        }
        return result;
    }

    @GetMapping("/getCertificate/student/")
    public Result getCertificateDto() throws Exception {
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("导入成功");
        List<UserCertificate> userCertificates = userCertificateService.getUserCertificate();
        List<CertificateDto> certificateDtos  = new ArrayList<>();
        for (UserCertificate u: userCertificates) {
            CertificateDto certificateDto = new CertificateDto();
            certificateDto.setId(u.getUserCerId());
            if(u.getStudentId1()!= null && u.getStudentId1().length()!=0){
                certificateDto.setStudentName1(studentService.getOneStudentById(u.getStudentId1()).getStudentName());
            }
            if(u.getStudentId2()!= null && u.getStudentId2().length()!=0){
                certificateDto.setStudentName2(studentService.getOneStudentById(u.getStudentId2()).getStudentName());
            }
            if(u.getStudentId3()!= null && u.getStudentId3().length()!=0){
                certificateDto.setStudentName3(studentService.getOneStudentById(u.getStudentId3()).getStudentName());
            }
            if(u.getStudentId4()!= null && u.getStudentId4().length()!=0){
                certificateDto.setStudentName4(studentService.getOneStudentById(u.getStudentId4()).getStudentName());
            }
            if(u.getStudentId5()!= null && u.getStudentId5().length()!=0){
                certificateDto.setStudentName5(studentService.getOneStudentById(u.getStudentId5()).getStudentName());
            }
            if(u.getStudentId6()!= null && u.getStudentId6().length()!=0){
                certificateDto.setStudentName6(studentService.getOneStudentById(u.getStudentId6()).getStudentName());
            }
            if(u.getStudentId7()!= null && u.getStudentId7().length()!=0){
                certificateDto.setStudentName7(studentService.getOneStudentById(u.getStudentId7()).getStudentName());
            }
            if(u.getStudentId8()!= null && u.getStudentId8().length()!=0){
                certificateDto.setStudentName8(studentService.getOneStudentById(u.getStudentId8()).getStudentName());
            }
            if(u.getStudentId9()!= null && u.getStudentId9().length()!=0){
                certificateDto.setStudentName9(studentService.getOneStudentById(u.getStudentId9()).getStudentName());
            }
            if(u.getStudentId10()!= null && u.getStudentId10().length()!=0){
                certificateDto.setStudentName10(studentService.getOneStudentById(u.getStudentId10()).getStudentName());
            }
            if(u.getTeacherId1()!= null && u.getTeacherId1().length()!=0){
                certificateDto.setTeacherName1(teacherService.getOneTeacherById(u.getTeacherId1()).getTeacherName());
            }
            if(u.getTeacherId2()!= null && u.getTeacherId2().length()!=0){
                certificateDto.setTeacherName2(teacherService.getOneTeacherById(u.getTeacherId2()).getTeacherName());
            }
            if(u.getTeacherId3()!= null && u.getTeacherId3().length()!=0){
                certificateDto.setTeacherName3(teacherService.getOneTeacherById(u.getTeacherId3()).getTeacherName());
            }


            if(u.getStudentId1()!= null && u.getStudentId1().length()!=0){
                certificateDto.setStudent1(studentService.getOneStudentById(u.getStudentId1()).getStudentCode());
            }
            if(u.getStudentId2()!= null && u.getStudentId2().length()!=0){
                certificateDto.setStudent2(studentService.getOneStudentById(u.getStudentId2()).getStudentCode());
            }
            if(u.getStudentId3()!= null && u.getStudentId3().length()!=0){
                certificateDto.setStudent3(studentService.getOneStudentById(u.getStudentId3()).getStudentCode());
            }
            if(u.getStudentId4()!= null && u.getStudentId4().length()!=0){
                certificateDto.setStudent4(studentService.getOneStudentById(u.getStudentId4()).getStudentCode());
            }
            if(u.getStudentId5()!= null && u.getStudentId5().length()!=0){
                certificateDto.setStudent5(studentService.getOneStudentById(u.getStudentId5()).getStudentCode());
            }
            if(u.getStudentId6()!= null && u.getStudentId6().length()!=0){
                certificateDto.setStudent6(studentService.getOneStudentById(u.getStudentId6()).getStudentCode());
            }
            if(u.getStudentId7()!= null && u.getStudentId7().length()!=0){
                certificateDto.setStudent7(studentService.getOneStudentById(u.getStudentId7()).getStudentCode());
            }
            if(u.getStudentId8()!= null && u.getStudentId8().length()!=0){
                certificateDto.setStudent8(studentService.getOneStudentById(u.getStudentId8()).getStudentCode());
            }
            if(u.getStudentId9()!= null && u.getStudentId9().length()!=0){
                certificateDto.setStudent9(studentService.getOneStudentById(u.getStudentId9()).getStudentCode());
            }
            if(u.getStudentId10()!= null && u.getStudentId10().length()!=0){
                certificateDto.setStudent10(studentService.getOneStudentById(u.getStudentId10()).getStudentCode());
            }
            if(u.getTeacherId1()!= null && u.getTeacherId1().length()!=0){
                certificateDto.setTeacher1(teacherService.getOneTeacherById(u.getTeacherId1()).getTeacherCode());
            }
            if(u.getTeacherId2()!= null && u.getTeacherId2().length()!=0){
                certificateDto.setTeacher2(teacherService.getOneTeacherById(u.getTeacherId2()).getTeacherCode());
            }
            if(u.getTeacherId3()!= null && u.getTeacherId3().length()!=0){
                certificateDto.setTeacher3(teacherService.getOneTeacherById(u.getTeacherId3()).getTeacherCode());
            }
            if(u.getUrl()!= null && u.getUrl().length()!=0){
                certificateDto.setUrl(u.getUrl());
            }
            if(u.getCertificateId()!=null && u.getCertificateId().length()!=0){
                certificateDto.setCertificateId(u.getCertificateId());
            }
            if(u.getCertificateId()!=null && u.getCertificateId().length()!=0){
                certificateDto.setCertificateId(u.getCertificateId());
                certificateDto.setCertificateName(certificateService.getOneCertificateById(u.getCertificateId()).getCertificateName());
                certificateDto.setCertificateLevl(certificateService.getOneCertificateById(u.getCertificateId()).getCertificateLevel());
            }
            if(u.getMatchId()!=null && u.getMatchId().length()!=0){
                certificateDto.setMatchId(u.getMatchId());
                certificateDto.setMatchName(matchService.getMatchs(u.getMatchId()).getMatchName());

            }
            if(u.getMatchType()!=null&&u.getMatchType().length()!=0){
                certificateDto.setMatchType(u.getMatchType());
            }
            if(u.getMatchLevelLevel()!=null&&u.getMatchLevelLevel().length()!=0){
                certificateDto.setMatchLevelLevel(u.getMatchLevelLevel());
            }
            if(u.getCertificateLevel()!=null&&u.getCertificateLevel().length()!=0){
                certificateDto.setCertificateLevel(u.getCertificateLevel());
            }

            certificateDtos.add(certificateDto);
        }
        result.setObject(certificateDtos);


        return result;
    }
    @PutMapping("/updateCertificate/student/")
    public Result updateCertificate(UserCertificate userCertificate) throws Exception {
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("修改成功");
        userCertificate.setUpdateTime(new Date());
        int i = userCertificateService.update(userCertificate);
        if(i == 0){
            result.setSuccess(false);
            result.setMessage("修改失败");
        }

        return result;
    }
    @DeleteMapping("/updateCertificate/student/{id}")
    public Result deleteCertificate(@PathVariable String  id) throws Exception {
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("修改成功");
        Long Id = Long.valueOf(id);

        int i = userCertificateService.delete(Id);
        if(i == 0){
            result.setSuccess(false);
            result.setMessage("修改失败");
        }

        return result;
    }


    @GetMapping("/getAllCertificate/")
    public Result getAllCertificate() {
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("添加成功");
        List<Certificate> certificates= organizationCertificateService.getAllCertificate();
        result.setObject(certificates);
        return result;
    }

    /**
     * 插入证书
     * @param certificateDto
     * @return
     */

    @PostMapping("/insertUserCertificate/student/")
    public Result insertUserCertificate(@RequestBody CertificateDto certificateDto){
        Result result = new Result();
        logger.info("certificateDto"+certificateDto);
        result.setSuccess(true);
        result.setMessage("添加成功");
        UserCertificate userCertificate = new UserCertificate();
        if(certificateDto.getId()!=null){
            userCertificate.setUserCerId(certificateDto.getId());
        }
        if(certificateDto.getCertificateId()!=null && certificateDto.getCertificateId().length()!=0){
            userCertificate.setCertificateId(certificateDto.getCertificateId());
        }
        if(certificateDto.getStudent1()!=null && certificateDto.getStudent1().length()!=0){
            userCertificate.setStudentId1(studentService.getStudentIdByStudentCode(certificateDto.getStudent1()).getStudentId());
        }
        if(certificateDto.getStudent2()!=null && certificateDto.getStudent2().length()!=0){
            userCertificate.setStudentId2(studentService.getStudentIdByStudentCode(certificateDto.getStudent2()).getStudentId());
        }
        if(certificateDto.getStudent3()!=null && certificateDto.getStudent3().length()!=0){
            userCertificate.setStudentId3(studentService.getStudentIdByStudentCode(certificateDto.getStudent3()).getStudentId());
        }
        if(certificateDto.getStudent4()!=null && certificateDto.getStudent4().length()!=0){
            userCertificate.setStudentId4(studentService.getStudentIdByStudentCode(certificateDto.getStudent4()).getStudentId());
        }
        if(certificateDto.getStudent5()!=null && certificateDto.getStudent5().length()!=0){
            userCertificate.setStudentId5(studentService.getStudentIdByStudentCode(certificateDto.getStudent5()).getStudentId());
        }
        if(certificateDto.getStudent6()!=null && certificateDto.getStudent6().length()!=0){
            userCertificate.setStudentId6(studentService.getStudentIdByStudentCode(certificateDto.getStudent6()).getStudentId());
        }
        if(certificateDto.getStudent7()!=null && certificateDto.getStudent7().length()!=0){
            userCertificate.setStudentId7(studentService.getStudentIdByStudentCode(certificateDto.getStudent7()).getStudentId());
        }
        if(certificateDto.getStudent8()!=null && certificateDto.getStudent8().length()!=0){
            userCertificate.setStudentId8(studentService.getStudentIdByStudentCode(certificateDto.getStudent8()).getStudentId());
        }
        if(certificateDto.getStudent9()!=null && certificateDto.getStudent9().length()!=0){
            userCertificate.setStudentId9(studentService.getStudentIdByStudentCode(certificateDto.getStudent9()).getStudentId());
        }
        if(certificateDto.getStudent10()!=null && certificateDto.getStudent10().length()!=0){
            userCertificate.setStudentId10(studentService.getStudentIdByStudentCode(certificateDto.getStudent10()).getStudentId());
        }
        if(certificateDto.getTeacher1()!=null && certificateDto.getTeacher1().length()!=0){
            userCertificate.setTeacherId1(teacherService.getTeacherByTeacherCode(certificateDto.getTeacher1()).getTeacherId());
        }
        if(certificateDto.getTeacher2()!=null && certificateDto.getTeacher2().length()!=0){
            userCertificate.setTeacherId2(teacherService.getTeacherByTeacherCode(certificateDto.getTeacher2()).getTeacherId());
        }
        if(certificateDto.getTeacher3()!=null && certificateDto.getTeacher3().length()!=0){
            userCertificate.setTeacherId3(teacherService.getTeacherByTeacherCode(certificateDto.getTeacher3()).getTeacherId());
        }
        if(certificateDto.getUrl()!=null && certificateDto.getUrl().length()!=0){
            userCertificate.setUrl(certificateDto.getUrl());
        }
        if(certificateDto.getCertificateLevel()!=null && certificateDto.getCertificateLevel().length()!=0){
            userCertificate.setCertificateLevel(certificateDto.getCertificateLevel());
        }
        if(certificateDto.getMatchId()!=null && certificateDto.getCertificateLevel().length()!=0){
            userCertificate.setCertificateLevel(certificateDto.getCertificateLevel());
        }
        if(certificateDto.getCertificateLevl()!=null && certificateDto.getCertificateLevl().length()!=0){
            userCertificate.setCertificateLevel(certificateDto.getCertificateLevl());
        }
        if(certificateDto.getCertificateLevl()!=null && certificateDto.getCertificateLevl().length()!=0){
            userCertificate.setCertificateLevel(certificateDto.getCertificateLevl());
        }
        if(certificateDto.getMatchLevelLevel()!=null && certificateDto.getMatchLevelLevel().length()!=0){
            userCertificate.setMatchLevelLevel(certificateDto.getMatchLevelLevel());
        }
        int i = userCertificateService.insert(userCertificate);
        if(i == 0){
            result.setMessage("插入失败");
            result.setSuccess(false);
        }
        return result;
    }

    @PutMapping("/updateUserCertificate/student/")
    public Result updateUserCertificate(@RequestBody CertificateDto certificateDto){
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("添加成功");
        logger.info("Url"+certificateDto.getUrl());
        UserCertificate userCertificate = new UserCertificate();
        if(certificateDto.getId()!=null){
            userCertificate.setUserCerId(certificateDto.getId());
        }
        if(certificateDto.getCertificateId()!=null && certificateDto.getCertificateId().length()!=0){
            userCertificate.setCertificateId(certificateDto.getCertificateId());
        }
        if(certificateDto.getStudent1()!=null && certificateDto.getStudent1().length()!=0){
            userCertificate.setStudentId1(studentService.getStudentIdByStudentCode(certificateDto.getStudent1()).getStudentId());
        }
        if(certificateDto.getStudent2()!=null && certificateDto.getStudent2().length()!=0){
            userCertificate.setStudentId2(studentService.getStudentIdByStudentCode(certificateDto.getStudent2()).getStudentId());
        }
        if(certificateDto.getStudent3()!=null && certificateDto.getStudent3().length()!=0){
            userCertificate.setStudentId3(studentService.getStudentIdByStudentCode(certificateDto.getStudent3()).getStudentId());
        }
        if(certificateDto.getStudent4()!=null && certificateDto.getStudent4().length()!=0){
            userCertificate.setStudentId4(studentService.getStudentIdByStudentCode(certificateDto.getStudent4()).getStudentId());
        }
        if(certificateDto.getStudent5()!=null && certificateDto.getStudent5().length()!=0){
            userCertificate.setStudentId5(studentService.getStudentIdByStudentCode(certificateDto.getStudent5()).getStudentId());
        }
        if(certificateDto.getStudent6()!=null && certificateDto.getStudent6().length()!=0){
            userCertificate.setStudentId6(studentService.getStudentIdByStudentCode(certificateDto.getStudent6()).getStudentId());
        }
        if(certificateDto.getStudent7()!=null && certificateDto.getStudent7().length()!=0){
            userCertificate.setStudentId7(studentService.getStudentIdByStudentCode(certificateDto.getStudent7()).getStudentId());
        }
        if(certificateDto.getStudent8()!=null && certificateDto.getStudent8().length()!=0){
            userCertificate.setStudentId8(studentService.getStudentIdByStudentCode(certificateDto.getStudent8()).getStudentId());
        }
        if(certificateDto.getStudent9()!=null && certificateDto.getStudent9().length()!=0){
            userCertificate.setStudentId9(studentService.getStudentIdByStudentCode(certificateDto.getStudent9()).getStudentId());
        }
        if(certificateDto.getStudent10()!=null && certificateDto.getStudent10().length()!=0){
            userCertificate.setStudentId10(studentService.getStudentIdByStudentCode(certificateDto.getStudent10()).getStudentId());
        }
        if(certificateDto.getTeacher1()!=null && certificateDto.getTeacher1().length()!=0){
            userCertificate.setTeacherId1(teacherService.getTeacherByTeacherCode(certificateDto.getTeacher1()).getTeacherId());
        }
        if(certificateDto.getTeacher2()!=null && certificateDto.getTeacher2().length()!=0){
            userCertificate.setTeacherId2(teacherService.getTeacherByTeacherCode(certificateDto.getTeacher2()).getTeacherId());
        }
        if(certificateDto.getTeacher3()!=null && certificateDto.getTeacher3().length()!=0){
            userCertificate.setTeacherId3(teacherService.getTeacherByTeacherCode(certificateDto.getTeacher3()).getTeacherId());
        }
        if(certificateDto.getUrl()!=null && certificateDto.getUrl().length()!=0){
            userCertificate.setUrl(certificateDto.getUrl());
        }
        if(certificateDto.getCertificateLevel()!=null && certificateDto.getCertificateLevel().length()!=0){
            userCertificate.setCertificateLevel(certificateDto.getCertificateLevel());
        }
        if(certificateDto.getMatchId()!=null && certificateDto.getCertificateLevel().length()!=0){
            userCertificate.setCertificateLevel(certificateDto.getCertificateLevel());
        }
        if(certificateDto.getCertificateLevl()!=null && certificateDto.getCertificateLevl().length()!=0){
            userCertificate.setCertificateLevel(certificateDto.getCertificateLevl());
        }
        if(certificateDto.getCertificateLevl()!=null && certificateDto.getCertificateLevl().length()!=0){
            userCertificate.setCertificateLevel(certificateDto.getCertificateLevl());
        }
        if(certificateDto.getMatchLevelLevel()!=null && certificateDto.getMatchLevelLevel().length()!=0){
            userCertificate.setMatchLevelLevel(certificateDto.getMatchLevelLevel());
        }
        int i = userCertificateService.update(userCertificate);
        if(i == 0){
            result.setMessage("跟新失败");
            result.setSuccess(false);
        }
        return result;
    }
    @PutMapping("/deleteUserCertificate/student/")
    public Result deleteUserCertificate(@RequestBody CertificateDto certificateDto){
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("添加成功");
        UserCertificate userCertificate = new UserCertificate();
        int i = 1;
        if(certificateDto.getId()!=null){
            i = userCertificateService.delete(certificateDto.getId());
        }
        if(i == 0){
            result.setMessage("跟新失败");
            result.setSuccess(false);
        }
        return result;
    }









}
